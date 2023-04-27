package ru.sadv1r.afc.ideaPlugin

import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import org.jetbrains.annotations.Nullable
import javax.swing.JComponent

class PluginConfigurable : Configurable {
    private val settingsForm = PluginSettingsForm()
    private val pluginSettings = PluginSettings()


    @Nls
    override fun getDisplayName(): String {
        return "Afc For Coders"
    }

    override fun createComponent(): JComponent {
        return settingsForm.createComponent()
    }

    override fun isModified(): Boolean {
        return settingsForm.isModified(pluginSettings)
    }

    override fun apply() {
        settingsForm.copyTo(pluginSettings)
    }

    @Nullable
    override fun getHelpTopic(): String? {
        return null
    }

    override fun reset() {
        settingsForm.copyFrom(pluginSettings);
    }

    override fun disposeUIResources() {

    }
}