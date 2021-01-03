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
package org.openhab.binding.airconwithme.internal.job;

import org.openhab.binding.airconwithme.internal.AirConWithMeBindingConstants;
import org.openhab.binding.airconwithme.internal.client.AirConClient;
import org.openhab.core.scheduler.SchedulerRunnable;

/**
 * The {@link StatusJob} is responsible for all communication with the rest api
 * handlers.
 *
 * @author SkyRaVeR - initial contribution
 */
public class StatusJob implements SchedulerRunnable, Runnable {

    private AirConClient client;
    private String uid;

    public StatusJob(AirConClient client) {
        this.client = client;
        this.uid = "all";
    }

    @Override
    public void run() {
        client.getData(uid);
        // private api which is not covered by "all"...
        client.getData(String.valueOf(AirConWithMeBindingConstants.UID_QUIETMODE));
    }
}
