package ru.sadv1r.afc.ideaPlugin;

import com.intellij.openapi.components.*;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created on 12/10/15.
 *
 * @author sadv1r
 * @version 0.1
 */
@State(
        name = "AfcStats",
        storages = {
                @Storage(id = "other", file = "$APP_CONFIG$/AfcStats.xml")
        }
)
public class Stats implements PersistentStateComponent<Stats>, ApplicationComponent {
    //TenSymbolsTyped, ThousandSymbolsTyped
    public int symbolsTyped;

    //HelloWorld
    public int helloWorldAchieved;

    //WeekWithoutNullPointerException
    public int daysWithoutNullPointer;
    public long lastDateWithoutNullPointer;

    public int getSymbolsTyped() {
        return symbolsTyped;
    }

    public void symbolTyped() {
        symbolsTyped++;
    }

    public int getHelloWorldAchieved() {
        return helloWorldAchieved;
    }

    public void setHelloWorldAchieved() {
        this.helloWorldAchieved = 1;
    }

    public int getDaysWithoutNullPointer() {
        return daysWithoutNullPointer;
    }

    public void addDayWithoutNullPointer() {
        this.daysWithoutNullPointer++;
    }

    public void resetDaysWithoutNullPointer() {
        this.daysWithoutNullPointer = 0;
    }

    public long getLastDateWithoutNullPointer() {
        return lastDateWithoutNullPointer;
    }

    public void setLastDateWithoutNullPointer(long lastDateWithoutNullPointer) {
        this.lastDateWithoutNullPointer = lastDateWithoutNullPointer;
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
