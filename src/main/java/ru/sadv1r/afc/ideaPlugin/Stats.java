package ru.sadv1r.afc.ideaPlugin;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
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
                @Storage("$APP_CONFIG$/AfcStats.xml")
        }
)
public class Stats implements PersistentStateComponent<Stats> {

    //TenSymbolsTyped, ThousandSymbolsTyped
    private int symbolsTyped;

    //HelloWorld
    private int helloWorldAchieved;

    //WeekWithoutNullPointerException
    private int daysWithoutNullPointer;
    private long lastDateWithoutNullPointer;

    //DayWithoutCopyPaste
    private int dayWithoutCopyPaste;
    private long lastDateDayWithoutCopyPaste;

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

    public long getLastDateDayWithoutCopyPaste() {
        return lastDateDayWithoutCopyPaste;
    }

    public void setLastDateDayWithoutCopyPaste(long lastDateDayWithoutCopyPaste) {
        this.lastDateDayWithoutCopyPaste = lastDateDayWithoutCopyPaste;
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

}
