package org.openhab.binding.airconwithme.internal.client.gson.capabilities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Capabilities {

    @SerializedName("api")
    @Expose
    private Api api;
    @SerializedName("info")
    @Expose
    private Info info;
    @SerializedName("types")
    @Expose
    private Types types;
    @SerializedName("acStatus")
    @Expose
    private AcStatus acStatus;
    @SerializedName("lastError")
    @Expose
    private LastError lastError;
    @SerializedName("wifiConfig")
    @Expose
    private WifiConfig wifiConfig;
    @SerializedName("signals")
    @Expose
    private Signals signals;

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Types getTypes() {
        return types;
    }

    public void setTypes(Types types) {
        this.types = types;
    }

    public AcStatus getAcStatus() {
        return acStatus;
    }

    public void setAcStatus(AcStatus acStatus) {
        this.acStatus = acStatus;
    }

    public LastError getLastError() {
        return lastError;
    }

    public void setLastError(LastError lastError) {
        this.lastError = lastError;
    }

    public WifiConfig getWifiConfig() {
        return wifiConfig;
    }

    public void setWifiConfig(WifiConfig wifiConfig) {
        this.wifiConfig = wifiConfig;
    }

    public Signals getSignals() {
        return signals;
    }

    public void setSignals(Signals signals) {
        this.signals = signals;
    }
}
