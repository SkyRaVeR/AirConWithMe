package org.openhab.binding.airconwithme.internal.client.gson.capabilities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WifiConfig {

    @SerializedName("securityType")
    @Expose
    private SecurityType securityType;
    @SerializedName("levelText")
    @Expose
    private LevelText levelText;

    public SecurityType getSecurityType() {
        return securityType;
    }

    public void setSecurityType(SecurityType securityType) {
        this.securityType = securityType;
    }

    public LevelText getLevelText() {
        return levelText;
    }

    public void setLevelText(LevelText levelText) {
        this.levelText = levelText;
    }
}
