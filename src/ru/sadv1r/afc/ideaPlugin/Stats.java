package ru.sadv1r.afc.ideaPlugin;

import com.intellij.openapi.components.*;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by sadvr on 12/10/15.
 */
//@State(name = "AfcStats", storages = {@Storage(id = "default", file = StoragePathMacros.PROJECT_CONFIG_DIR + "/other.xml", scheme = StorageScheme.DIRECTORY_BASED)})
@State(
        name = "AfcStats",
        storages = {
                @Storage(id = "other", file = "$APP_CONFIG$/AfcStats.xml")
        }
)
public class Stats implements PersistentStateComponent<Stats>, ApplicationComponent {
    public int symbolsTyped;

    public int getSymbolsTyped() {
        return symbolsTyped;
    }

    public void symbolTyped() {
        symbolsTyped++;
    }

    @Nullable
    @Override
    public Stats getState() {
        return this;
    }

    @Override
    public void loadState(Stats state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    @Override
    public void initComponent() {

    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return "AfcStats";
    }
}
