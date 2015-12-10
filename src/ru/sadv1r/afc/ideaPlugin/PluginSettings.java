package ru.sadv1r.afc.ideaPlugin;

import com.intellij.ide.util.PropertiesComponent;

/**
 * Created on 12/9/15.
 *
 * @author sadv1r
 * @version 0.1
 */
public class PluginSettings {
    private final PropertiesComponent properties = PropertiesComponent.getInstance();

    public String getToken() {
        if (properties.isValueSet("progTime_token")) {
            return properties.getValue("progTime_token");
        } else {
            return "";
        }
    }

    public void setToken(String token) {
        properties.setValue("progTime_token", token);
    }

    public boolean isPlayOffline() {
        return properties.getBoolean("progTime_playOffline");
    }

    public void setPlayOffline(boolean playOffline) {
        properties.setValue("progTime_playOffline" , playOffline);
    }

}
