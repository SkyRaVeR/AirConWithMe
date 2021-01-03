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
package org.openhab.binding.airconwithme.internal.client.gson;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The {@link Data} is responsible for all communication with the rest api
 * handlers.
 *
 * @author SkyRaVeR - initial contribution
 */
public class Dp {

    @SerializedName("datapoints")
    @Expose
    private List<Datapoint> datapoints = null;

    public List<Datapoint> getDatapoints() {
        return datapoints;
    }

    public void setDatapoints(List<Datapoint> datapoints) {
        this.datapoints = datapoints;
    }
}
