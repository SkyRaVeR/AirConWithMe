package org.openhab.binding.airconwithme.internal.client.gson.capabilities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Url {

    @SerializedName("getinfo")
    @Expose
    private String getinfo;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("logout")
    @Expose
    private String logout;
    @SerializedName("update_password")
    @Expose
    private String updatePassword;
    @SerializedName("getavailabledatapoints")
    @Expose
    private String getavailabledatapoints;
    @SerializedName("getdatapointvalue")
    @Expose
    private String getdatapointvalue;
    @SerializedName("setdatapointvalue")
    @Expose
    private String setdatapointvalue;
    @SerializedName("getcurrentconfig")
    @Expose
    private String getcurrentconfig;
    @SerializedName("identify")
    @Expose
    private String identify;
    @SerializedName("wpsstart")
    @Expose
    private String wpsstart;
    @SerializedName("getaplist")
    @Expose
    private String getaplist;
    @SerializedName("setdefaults")
    @Expose
    private String setdefaults;
    @SerializedName("setconfig")
    @Expose
    private String setconfig;
    @SerializedName("reboot")
    @Expose
    private String reboot;
    @SerializedName("getavailableservices")
    @Expose
    private String getavailableservices;

    public String getGetinfo() {
        return getinfo;
    }

    public void setGetinfo(String getinfo) {
        this.getinfo = getinfo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogout() {
        return logout;
    }

    public void setLogout(String logout) {
        this.logout = logout;
    }

    public String getUpdatePassword() {
        return updatePassword;
    }

    public void setUpdatePassword(String updatePassword) {
        this.updatePassword = updatePassword;
    }

    public String getGetavailabledatapoints() {
        return getavailabledatapoints;
    }

    public void setGetavailabledatapoints(String getavailabledatapoints) {
        this.getavailabledatapoints = getavailabledatapoints;
    }

    public String getGetdatapointvalue() {
        return getdatapointvalue;
    }

    public void setGetdatapointvalue(String getdatapointvalue) {
        this.getdatapointvalue = getdatapointvalue;
    }

    public String getSetdatapointvalue() {
        return setdatapointvalue;
    }

    public void setSetdatapointvalue(String setdatapointvalue) {
        this.setdatapointvalue = setdatapointvalue;
    }

    public String getGetcurrentconfig() {
        return getcurrentconfig;
    }

    public void setGetcurrentconfig(String getcurrentconfig) {
        this.getcurrentconfig = getcurrentconfig;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getWpsstart() {
        return wpsstart;
    }

    public void setWpsstart(String wpsstart) {
        this.wpsstart = wpsstart;
    }

    public String getGetaplist() {
        return getaplist;
    }

    public void setGetaplist(String getaplist) {
        this.getaplist = getaplist;
    }

    public String getSetdefaults() {
        return setdefaults;
    }

    public void setSetdefaults(String setdefaults) {
        this.setdefaults = setdefaults;
    }

    public String getSetconfig() {
        return setconfig;
    }

    public void setSetconfig(String setconfig) {
        this.setconfig = setconfig;
    }

    public String getReboot() {
        return reboot;
    }

    public void setReboot(String reboot) {
        this.reboot = reboot;
    }

    public String getGetavailableservices() {
        return getavailableservices;
    }

    public void setGetavailableservices(String getavailableservices) {
        this.getavailableservices = getavailableservices;
    }
}
