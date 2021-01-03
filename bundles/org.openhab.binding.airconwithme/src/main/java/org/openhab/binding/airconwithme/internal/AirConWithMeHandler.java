/**
 * Copyright (c) 2010-2020 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.airconwithme.internal;

import static org.openhab.binding.airconwithme.internal.AirConWithMeBindingConstants.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jetty.client.HttpClient;
import org.openhab.binding.airconwithme.internal.client.AirConClient;
import org.openhab.binding.airconwithme.internal.client.ResponseCallback;
import org.openhab.binding.airconwithme.internal.client.gson.Dpval;
import org.openhab.binding.airconwithme.internal.client.gson.JSONData;
import org.openhab.binding.airconwithme.internal.client.gson.capabilities.Capabilities;
import org.openhab.binding.airconwithme.internal.job.StatusJob;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.library.types.QuantityType;
import org.openhab.core.library.types.StringType;
import org.openhab.core.library.unit.SIUnits;
import org.openhab.core.thing.Channel;
import org.openhab.core.thing.ChannelUID;
import org.openhab.core.thing.Thing;
import org.openhab.core.thing.ThingStatus;
import org.openhab.core.thing.ThingStatusDetail;
import org.openhab.core.thing.binding.BaseThingHandler;
import org.openhab.core.types.Command;
import org.openhab.core.types.RefreshType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link AirConWithMeHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Martin Klama - Initial contribution
 */
@NonNullByDefault
public class AirConWithMeHandler extends BaseThingHandler implements ResponseCallback {

    private final Logger logger = LoggerFactory.getLogger(AirConWithMeHandler.class);

    private AirConWithMeConfiguration config;
    private AirConClient airconclient;

    private final Lock monitor = new ReentrantLock();
    private final Set<ScheduledFuture<?>> scheduledFutures = new HashSet<>();

    private HttpClient httpclient;

    private int reconnectDelay = 60;

    public AirConWithMeHandler(Thing thing, HttpClient client) {
        super(thing);
        httpclient = client;
        airconclient = new AirConClient(httpclient);
        config = new AirConWithMeConfiguration();
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {

        switch (channelUID.getId()) {
            case CHANNEL_AIRCON_ONOFF:
                handleCMD(channelUID, command, UID_POWERSTATUS);
                break;
            case CHANNEL_AIRCON_QUIETMODE:
                handleCMD(channelUID, command, UID_QUIETMODE);
                break;
            case CHANNEL_AIRCON_UMODE:
                handleCMD(channelUID, command, UID_USERMODE);
                break;
            case CHANNEL_AIRCON_FANSPEED:
                handleCMD(channelUID, command, UID_FANSPEED);
                break;
            case CHANNEL_AIRCON_VANEPOS:
                handleCMD(channelUID, command, UID_VANEPOS);
                break;
            case CHANNEL_AIRCON_TEMPDESIRED:
                handleCMD(channelUID, command, UID_SETPOINTTEMP);
                break;
            case CHANNEL_AIRCON_TEMPCURRENT:
                handleCMD(channelUID, command, UID_SETPOINTTEMP);
                break;
            case CHANNEL_AIRCON_TEMPOUTDOOR:
                handleCMD(channelUID, command, UID_SETPOINTTEMP);
                break;
            case CHANNEL_AIRCON_ONTIME:
                handleCMD(channelUID, command, UID_SETPOINTTEMP);
                break;
            default:
                break;
        }
    }

    @Override
    public void initialize() {
        airconclient = new AirConClient(httpclient);

        config = getConfigAs(AirConWithMeConfiguration.class);

        if (config.getHost().isEmpty()) {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR, "host must not be empty!");
            return;
        }

        if (config.getPollingInterval() < 1) {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR, "polling interval must be > 0 !");
            return;
        }

        logger.debug("config polling {}s host {}", config.getPollingInterval(), config.getHost());

        updateStatus(ThingStatus.UNKNOWN);

        airconclient.setHostname(config.getHost());
        airconclient.registerCallback(this);
        reconnectDelay = config.getReconnectDelay();

        scheduler.execute(() -> {
            // airconclient.retrieveCapabilities();
            if (false == airconclient.isLoginstatus()) {
                airconclient.Login();
            }
        });
    }

    @Override
    public void dispose() {
        airconclient.unregisterCallback();
        stopPolling();
    }

    /**
     * starts polling snapshot data from website
     */
    public void startPolling() {
        monitor.lock();
        try {
            StatusJob statusjob = new StatusJob(airconclient);

            int pollinginterval = 0;
            pollinginterval = config.getPollingInterval();

            if (pollinginterval < POLLINGINTERVAL_MIN) {
                pollinginterval = POLLINGINTERVAL_DEFAULT;
            }

            ScheduledFuture<?> future = scheduler.scheduleWithFixedDelay(statusjob, 0, pollinginterval,
                    TimeUnit.SECONDS);
            scheduledFutures.add(future);
            logger.debug("Scheduled {} every {} seconds", "status job", pollinginterval);

        } catch (Exception ex) {
            logger.error("{}", ex.getMessage(), ex);
        } finally {
            monitor.unlock();
        }
    }

    public void stopPolling() {
        monitor.lock();
        try {
            for (ScheduledFuture<?> future : scheduledFutures) {
                if (!future.isDone()) {
                    future.cancel(true);
                }
            }
            scheduledFutures.clear();
        } catch (Exception e) {
            logger.error("Failed to stop polling jobs!");
        } finally {
            monitor.unlock();
        }
    }

    public void publishChannelIfLinked(ChannelUID channelUID, Object value) {

        if (isLinked(channelUID.getId()) && getThing().isEnabled() && (getThing().getStatus() == ThingStatus.ONLINE)) {

            final Channel channel = getThing().getChannel(channelUID.getId());
            if (null == channel) {
                logger.error("Cannot find channel for {}", channelUID);
                return;
            }

            try {
                if (value instanceof String) {
                    updateState(channelUID, new StringType((String) value));
                } else if (value instanceof Integer) {
                    updateState(channelUID, new DecimalType((int) value));
                } else if (value instanceof QuantityType) {
                    updateState(channelUID, (QuantityType<?>) value);
                } else if (value instanceof OnOffType) {
                    updateState(channelUID, (OnOffType) value);
                }

            } catch (Exception ex) {
                logger.error("Can't update state for channel {} : {}", channelUID, ex.getMessage(), ex);
            }

        }
    }

    @Override
    public void responseCallbackLoggedIn(boolean isloggedin) {

        if (false == isloggedin) {
            stopPolling();
            updateStatus(ThingStatus.OFFLINE);
            // try to re-login after a given amount of time
            scheduler.schedule(() -> {
                if (false == airconclient.isLoginstatus()) {

                    airconclient.Login();
                }
            }, reconnectDelay, TimeUnit.SECONDS);

        } else {
            updateStatus(ThingStatus.ONLINE);
            if (scheduledFutures.isEmpty()) {
                startPolling();
            }
        }
    }

    @Override
    public void responseCallbackCapabilities(Capabilities capabilities) {
        if (null == capabilities.getInfo()) {
            return;
        }
    }

    @Override
    public void responseCallbackData(JSONData statusdata) {

        if (this.getThing().getStatus() != ThingStatus.ONLINE) {
            stopPolling();
            return;
        }

        if (false == statusdata.getSuccess()) {
            stopPolling();
            airconclient.Login();
            return;
        }

        Channel chan_status = getThing().getChannel(CHANNEL_AIRCON_ONOFF);
        Channel chan_quietmode = getThing().getChannel(CHANNEL_AIRCON_QUIETMODE);
        Channel chan_umode = getThing().getChannel(CHANNEL_AIRCON_UMODE);
        Channel chan_fanspeed = getThing().getChannel(CHANNEL_AIRCON_FANSPEED);
        Channel chan_vanepos = getThing().getChannel(CHANNEL_AIRCON_VANEPOS);
        Channel chan_setpointtemp = getThing().getChannel(CHANNEL_AIRCON_TEMPDESIRED);
        Channel chan_currenttemp = getThing().getChannel(CHANNEL_AIRCON_TEMPCURRENT);
        Channel chan_currentouttemp = getThing().getChannel(CHANNEL_AIRCON_TEMPOUTDOOR);
        Channel chan_ontime = getThing().getChannel(CHANNEL_AIRCON_ONTIME);

        int val = 0;
        List<Dpval> dpvalues;

        if (statusdata.getData() != null) {
            dpvalues = statusdata.getData().getDpval();
        } else {
            return;
        }

        for (int i = 0; i < dpvalues.size(); i++) {
            Dpval dpval = dpvalues.get(i);

            if (UID_POWERSTATUS == dpval.getUid() && chan_status != null) {
                publishChannelIfLinked(chan_status.getUID(), OnOffType.from(String.valueOf(dpval.getValue())));
            }
            if (UID_QUIETMODE == dpval.getUid() && chan_quietmode != null) {
                publishChannelIfLinked(chan_quietmode.getUID(), OnOffType.from(String.valueOf(dpval.getValue())));
            }

            if (UID_USERMODE == dpval.getUid() && chan_umode != null) {
                publishChannelIfLinked(chan_umode.getUID(), String.valueOf(dpval.getValue()));
            }
            if (UID_FANSPEED == dpval.getUid() && chan_fanspeed != null) {
                publishChannelIfLinked(chan_fanspeed.getUID(), String.valueOf(dpval.getValue()));
            }
            if (UID_VANEPOS == dpval.getUid() && chan_vanepos != null) {
                publishChannelIfLinked(chan_vanepos.getUID(), String.valueOf(dpval.getValue()));
            }
            if (UID_SETPOINTTEMP == dpval.getUid() && chan_setpointtemp != null) {
                val = dpval.getValue() / 10;
                publishChannelIfLinked(chan_setpointtemp.getUID(), new QuantityType<>(val, SIUnits.CELSIUS));
            }
            if (UID_CURRENTROOMTEMP == dpval.getUid() && chan_currenttemp != null) {
                val = dpval.getValue() / 10;
                publishChannelIfLinked(chan_currenttemp.getUID(), new QuantityType<>(val, SIUnits.CELSIUS));
            }
            if (UID_CURRENTOUTDOORTEMP == dpval.getUid() && chan_currentouttemp != null) {
                val = dpval.getValue() / 10;
                publishChannelIfLinked(chan_currentouttemp.getUID(), new QuantityType<>(val, SIUnits.CELSIUS));
            }
            if (UID_OPERATINGTIME == dpval.getUid() && chan_ontime != null) {
                publishChannelIfLinked(chan_ontime.getUID(), dpval.getValue());
            }
        }
    }

    private void handleCMD(ChannelUID channelUID, Command command, int uid) {

        if (command instanceof RefreshType) {
            airconclient.getData(String.valueOf(uid));
        } else if (command instanceof StringType) {
            airconclient.setData(uid, ((StringType) command).toString());
            updateState(channelUID, ((StringType) command));
        } else if (command instanceof OnOffType) {
            String val = "0";
            if (((OnOffType) command).toString() == "ON") {
                val = "1";
            }
            airconclient.setData(uid, val);
            updateState(channelUID, ((OnOffType) command));
        } else if (command instanceof QuantityType) {
            String val = String.valueOf(((QuantityType<?>) command).intValue() * 10);
            airconclient.setData(uid, val);
            updateState(channelUID, (QuantityType<?>) command);
        }
    }
}
