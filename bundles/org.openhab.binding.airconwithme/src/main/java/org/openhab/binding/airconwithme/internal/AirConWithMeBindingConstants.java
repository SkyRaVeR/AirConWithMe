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

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.core.thing.ThingTypeUID;

/**
 * The {@link AirConWithMeBindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Martin Klama - Initial contribution
 */
@NonNullByDefault
public class AirConWithMeBindingConstants {

    private static final String BINDING_ID = "airconwithme";
    public static final String AIRCON_THING_NAME = "airconwithme";
    public static final String LOGIN = "operator";

    public static final int POLLINGINTERVAL_DEFAULT = 60;
    public static final int POLLINGINTERVAL_MIN = 1;

    public static int UID_POWERSTATUS = 1;
    public static int UID_USERMODE = 2;
    public static int UID_FANSPEED = 4;
    public static int UID_VANEPOS = 5;
    public static int UID_SETPOINTTEMP = 9;
    public static int UID_CURRENTROOMTEMP = 10;
    public static int UID_OPERATINGTIME = 13;
    public static int UID_CURRENTOUTDOORTEMP = 37;
    public static int UID_QUIETMODE = 34;

    public static int UID_ALARMSTATUS = 14;
    public static int UID_ERRORCODE = 15;

    // List of all Thing Type UIDs
    public static final ThingTypeUID THING_TYPE_AIRCON = new ThingTypeUID(BINDING_ID, AIRCON_THING_NAME);

    // List of all Channel ids
    public static final String CHANNEL_AIRCON_ONOFF = "status";
    public static final String CHANNEL_AIRCON_QUIETMODE = "quietmode";
    public static final String CHANNEL_AIRCON_UMODE = "usermode";
    public static final String CHANNEL_AIRCON_FANSPEED = "fanspeed";
    public static final String CHANNEL_AIRCON_VANEPOS = "vaneposition";
    public static final String CHANNEL_AIRCON_TEMPDESIRED = "desiredtemp";
    public static final String CHANNEL_AIRCON_TEMPCURRENT = "currenttemp";
    public static final String CHANNEL_AIRCON_TEMPOUTDOOR = "outdoortemp";
    public static final String CHANNEL_AIRCON_ALARM = "alarmstatus";
    public static final String CHANNEL_AIRCON_ONTIME = "ontime";
}
