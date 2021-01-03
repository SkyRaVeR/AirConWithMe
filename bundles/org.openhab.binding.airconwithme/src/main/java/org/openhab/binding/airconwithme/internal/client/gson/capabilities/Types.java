package org.openhab.binding.airconwithme.internal.client.gson.capabilities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Types {

    @SerializedName("int")
    @Expose
    private Integer _int;
    @SerializedName("enum")
    @Expose
    private Integer _enum;
    @SerializedName("temp")
    @Expose
    private Integer temp;

    public Integer getInt() {
        return _int;
    }

    public void setInt(Integer _int) {
        this._int = _int;
    }

    public Integer getEnum() {
        return _enum;
    }

    public void setEnum(Integer _enum) {
        this._enum = _enum;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }
}
