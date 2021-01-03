package org.openhab.binding.airconwithme.internal.client.gson.capabilities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("deviceModel")
    @Expose
    private String deviceModel;
    @SerializedName("fwVersion")
    @Expose
    private String fwVersion;
    @SerializedName("wlanFwVersion")
    @Expose
    private String wlanFwVersion;
    @SerializedName("sn")
    @Expose
    private String sn;
    @SerializedName("wlanSTAMAC")
    @Expose
    private String wlanSTAMAC;
    @SerializedName("ssid")
    @Expose
    private String ssid;
    @SerializedName("wlanLNK")
    @Expose
    private String wlanLNK;
    @SerializedName("rssi")
    @Expose
    private String rssi;
    @SerializedName("wlanAPMAC")
    @Expose
    private String wlanAPMAC;
    @SerializedName("ownSSID")
    @Expose
    private String ownSSID;
    @SerializedName("tcpServerLNK")
    @Expose
    private String tcpServerLNK;
    @SerializedName("acStatus")
    @Expose
    private String acStatus;
    @SerializedName("lastconfigdatetime")
    @Expose
    private String lastconfigdatetime;
    @SerializedName("localdatetime")
    @Expose
    private String localdatetime;

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getFwVersion() {
        return fwVersion;
    }

    public void setFwVersion(String fwVersion) {
        this.fwVersion = fwVersion;
    }

    public String getWlanFwVersion() {
        return wlanFwVersion;
    }

    public void setWlanFwVersion(String wlanFwVersion) {
        this.wlanFwVersion = wlanFwVersion;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getWlanSTAMAC() {
        return wlanSTAMAC;
    }

    public void setWlanSTAMAC(String wlanSTAMAC) {
        this.wlanSTAMAC = wlanSTAMAC;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getWlanLNK() {
        return wlanLNK;
    }

    public void setWlanLNK(String wlanLNK) {
        this.wlanLNK = wlanLNK;
    }

    public String getRssi() {
        return rssi;
    }

    public void setRssi(String rssi) {
        this.rssi = rssi;
    }

    public String getWlanAPMAC() {
        return wlanAPMAC;
    }

    public void setWlanAPMAC(String wlanAPMAC) {
        this.wlanAPMAC = wlanAPMAC;
    }

    public String getOwnSSID() {
        return ownSSID;
    }

    public void setOwnSSID(String ownSSID) {
        this.ownSSID = ownSSID;
    }

    public String getTcpServerLNK() {
        return tcpServerLNK;
    }

    public void setTcpServerLNK(String tcpServerLNK) {
        this.tcpServerLNK = tcpServerLNK;
    }

    public String getAcStatus() {
        return acStatus;
    }

    public void setAcStatus(String acStatus) {
        this.acStatus = acStatus;
    }

    public String getLastconfigdatetime() {
        return lastconfigdatetime;
    }

    public void setLastconfigdatetime(String lastconfigdatetime) {
        this.lastconfigdatetime = lastconfigdatetime;
    }

    public String getLocaldatetime() {
        return localdatetime;
    }

    public void setLocaldatetime(String localdatetime) {
        this.localdatetime = localdatetime;
    }
}
