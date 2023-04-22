package ru.sadv1r.afc.ideaPlugin;

import com.intellij.credentialStore.CredentialAttributes;
import com.intellij.credentialStore.CredentialAttributesKt;
import com.intellij.credentialStore.Credentials;
import com.intellij.ide.passwordSafe.PasswordSafe;
import com.intellij.ide.util.PropertiesComponent;

import java.util.Optional;

/**
 * Created on 12/9/15.
 *
 * @author sadv1r
 * @version 0.1
 */
public class PluginSettings {

    private final PropertiesComponent properties = PropertiesComponent.getInstance();

    public boolean isPlayOffline() {
        return properties.getBoolean("afc.playOffline");
    }

    public void setPlayOffline(boolean playOffline) {
        properties.setValue("afc.playOffline", playOffline);
    }

    public void storeToken(String token) {
        CredentialAttributes credentialAttributes = createCredentialAttributes("serverId");
        Credentials credentials = new Credentials("token", token);
        PasswordSafe.getInstance().set(credentialAttributes, credentials);
    }

    public Optional<String> getToken() {
        String key = "serverId";
        CredentialAttributes credentialAttributes = createCredentialAttributes(key);
        return Optional.ofNullable(PasswordSafe.getInstance().getPassword(credentialAttributes));

        /*TODO Fix To This
        Credentials credentials = PasswordSafe.getInstance().get(credentialAttributes);
        if (credentials != null) {
            return credentials.getPassword();
        }*/
    }

    private CredentialAttributes createCredentialAttributes(String key) {
        return new CredentialAttributes(CredentialAttributesKt.generateServiceName("MySystem", key));
    }

}
