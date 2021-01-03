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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The {@link Descr} is responsible for all communication with the rest api
 * handlers.
 *
 * @author SkyRaVeR - initial contribution
 */
public class Datapoint {

    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("rw")
    @Expose
    private String rw;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("descr")
    @Expose
    private Descr descr;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Descr getDescr() {
        return descr;
    }

    public void setDescr(Descr descr) {
        this.descr = descr;
    }
}
