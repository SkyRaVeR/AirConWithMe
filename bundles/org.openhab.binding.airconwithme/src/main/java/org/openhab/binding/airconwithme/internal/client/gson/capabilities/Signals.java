package org.openhab.binding.airconwithme.internal.client.gson.capabilities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Signals {

    @SerializedName("uid")
    @Expose
    private Uid uid;
    @SerializedName("uidTextvalues")
    @Expose
    private UidTextvalues uidTextvalues;

    public Uid getUid() {
        return uid;
    }

    public void setUid(Uid uid) {
        this.uid = uid;
    }

    public UidTextvalues getUidTextvalues() {
        return uidTextvalues;
    }

    public void setUidTextvalues(UidTextvalues uidTextvalues) {
        this.uidTextvalues = uidTextvalues;
    }
}
