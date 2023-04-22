package ru.sadv1r.afc.ideaPlugin;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created on 12/9/15.
 *
 * @author sadv1r
 * @version 0.1
 */
public class PluginConfigurable implements Configurable {
    private final PluginSettingsForm settingsForm = new PluginSettingsForm();
    private final PluginSettings pluginSettings = new PluginSettings();

    @Nls
    @Override
    public String getDisplayName() {
        return "Afc For Coders";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return settingsForm.createComponent();
    }

    @Override
    public boolean isModified() {
        return settingsForm.isModified(pluginSettings);
    }

    @Override
    public void apply() throws ConfigurationException {
        settingsForm.copyTo(pluginSettings);
    }

    @Override
    public void reset() {
        settingsForm.copyFrom(pluginSettings);
    }

    @Override
    public void disposeUIResources() {

    }
}
