package ru.sadv1r.afc.ideaPlugin.achievements;

import ru.sadv1r.afc.ideaPlugin.Achievable;

/**
 * Created on 12/10/15.
 *
 * @author sadv1r
 * @version 0.1
 */
public class ThousandSymbolsTyped implements Achievable {
    private int finish = 1000;
    private final String name = "Try to code something";
    private final String text = "Type " + getFinish() + " symbols";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public int getProgress() {
        return stats.getSymbolsTyped() >= getFinish() ? finish : stats.getSymbolsTyped();
    }

    @Override
    public int getFinish() {
        return finish;
    }
}
