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
public class Data {

    @SerializedName("id")
    @Expose
    private Id id;

    @SerializedName("dpval")
    @Expose
    private List<Dpval> dpval = null;

    @SerializedName("dp")
    @Expose
    private Dp dp;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public List<Dpval> getDpval() {
        return dpval;
    }

    public void setDpval(List<Dpval> dpval) {
        this.dpval = dpval;
    }

    public Dp getDp() {
        return dp;
    }

    public void setDp(Dp dp) {
        this.dp = dp;
    }
}
