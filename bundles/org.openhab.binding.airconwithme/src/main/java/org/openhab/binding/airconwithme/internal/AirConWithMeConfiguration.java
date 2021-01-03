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

/**
 * The {@link AirConWithMeConfiguration} class contains fields mapping thing configuration parameters.
 *
 * @author Martin Klama - Initial contribution
 */
@NonNullByDefault
public class AirConWithMeConfiguration {

    private Integer pollingInterval = 60;
    private String host = "127.0.0.1";
    private Integer reconnectDelay = 60;

    public Integer getPollingInterval() {
        return pollingInterval;
    }

    public void setPollingInterval(Integer pollingInterval) {
        this.pollingInterval = pollingInterval;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getReconnectDelay() {
        return reconnectDelay;
    }

    public void setReconnectDelay(Integer reconnectDelay) {
        this.reconnectDelay = reconnectDelay;
    }
}
