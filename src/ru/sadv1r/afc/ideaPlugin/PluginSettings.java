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
        if (properties.isValueSet("afc_token")) {
            return properties.getValue("afc_token");
        } else {
            return "";
        }
    }

    public void setToken(String token) {
        properties.setValue("afc_token", token);
    }

    public boolean isPlayOffline() {
        return properties.getBoolean("afc_playOffline");
    }

    public void setPlayOffline(boolean playOffline) {
        properties.setValue("afc_playOffline" , playOffline);
    }

}
