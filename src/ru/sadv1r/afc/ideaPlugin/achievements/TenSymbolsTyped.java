package ru.sadv1r.afc.ideaPlugin.achievements;

import ru.sadv1r.afc.ideaPlugin.Achievable;

/**
 * Created on 12/10/15.
 *
 * @author sadv1r
 * @version 0.1
 */
public class TenSymbolsTyped implements Achievable {
    private int finish = 10;
    private final String name = "Найти клавиатуру";
    private final String text = "Напечатать " + getFinish() + " символов";

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
