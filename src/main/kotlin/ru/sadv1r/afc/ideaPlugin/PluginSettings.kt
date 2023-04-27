package ru.sadv1r.afc.ideaPlugin

import com.intellij.credentialStore.CredentialAttributes
import com.intellij.credentialStore.Credentials
import com.intellij.credentialStore.generateServiceName
import com.intellij.ide.passwordSafe.PasswordSafe
import com.intellij.ide.util.PropertiesComponent
import java.util.*

class PluginSettings {
    private val properties: PropertiesComponent = PropertiesComponent.getInstance()
    fun isPlayOffline(): Boolean {
        return properties.getBoolean("afc.playOffline")
    }

    fun setPlayOffline(playOffline: Boolean) {
        properties.setValue("afc.playOffline", playOffline)
    }

    fun storeToken(token: String) {
        val credentialAttributes = createCredentialAttributes ("serverId")
        val credentials = Credentials("token", token)
        PasswordSafe.instance.set(credentialAttributes, credentials)
    }

    fun getToken(): Optional<String> {
        val key = "serverId"
        val credentialAttributes = createCredentialAttributes(key)
        return Optional.ofNullable(PasswordSafe.instance.getPassword(credentialAttributes))

        /*TODO Fix To This
        Credentials credentials = PasswordSafe.getInstance().get(credentialAttributes);
        if (credentials != null) {
            return credentials.getPassword();
        }*/
    }

    private fun createCredentialAttributes(key: String): CredentialAttributes
    {
        return CredentialAttributes(generateServiceName("MySystem", key))
    }
}