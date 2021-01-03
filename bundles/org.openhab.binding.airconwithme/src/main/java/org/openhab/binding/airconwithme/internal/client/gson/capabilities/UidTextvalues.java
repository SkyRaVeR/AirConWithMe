package org.openhab.binding.airconwithme.internal.client.gson.capabilities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UidTextvalues {

    @SerializedName("TEXT_VALUES_ONOFF")
    @Expose
    private TEXTVALUESONOFF tEXTVALUESONOFF;
    @SerializedName("TEXT_VALUES_MODES")
    @Expose
    private TEXTVALUESMODES tEXTVALUESMODES;
    @SerializedName("TEXT_VALUES_FANSPEED")
    @Expose
    private TEXTVALUESFANSPEED tEXTVALUESFANSPEED;
    @SerializedName("TEXT_VALUES_VANES")
    @Expose
    private TEXTVALUESVANES tEXTVALUESVANES;
    @SerializedName("TEXT_VALUES_REMOTE")
    @Expose
    private TEXTVALUESREMOTE tEXTVALUESREMOTE;

    public TEXTVALUESONOFF getTEXTVALUESONOFF() {
        return tEXTVALUESONOFF;
    }

    public void setTEXTVALUESONOFF(TEXTVALUESONOFF tEXTVALUESONOFF) {
        this.tEXTVALUESONOFF = tEXTVALUESONOFF;
    }

    public TEXTVALUESMODES getTEXTVALUESMODES() {
        return tEXTVALUESMODES;
    }

    public void setTEXTVALUESMODES(TEXTVALUESMODES tEXTVALUESMODES) {
        this.tEXTVALUESMODES = tEXTVALUESMODES;
    }

    public TEXTVALUESFANSPEED getTEXTVALUESFANSPEED() {
        return tEXTVALUESFANSPEED;
    }

    public void setTEXTVALUESFANSPEED(TEXTVALUESFANSPEED tEXTVALUESFANSPEED) {
        this.tEXTVALUESFANSPEED = tEXTVALUESFANSPEED;
    }

    public TEXTVALUESVANES getTEXTVALUESVANES() {
        return tEXTVALUESVANES;
    }

    public void setTEXTVALUESVANES(TEXTVALUESVANES tEXTVALUESVANES) {
        this.tEXTVALUESVANES = tEXTVALUESVANES;
    }

    public TEXTVALUESREMOTE getTEXTVALUESREMOTE() {
        return tEXTVALUESREMOTE;
    }

    public void setTEXTVALUESREMOTE(TEXTVALUESREMOTE tEXTVALUESREMOTE) {
        this.tEXTVALUESREMOTE = tEXTVALUESREMOTE;
    }
}
