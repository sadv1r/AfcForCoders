package ru.sadv1r.afc.ideaPlugin.achievements;

/**
 * Created on 12/7/15.
 *
 * @author sadv1r
 * @version 0.1
 */
public class TypedSymbols {
    private static int typedSymbols = 0;

    private static final int[] SYMBOLS_FOR_ACHIEVEMENTS = {1000000, 100000, 10000, 1000, 100, 10};

    public static int newSymbol() {
        typedSymbols++;
        for (int SYMBOLS_FOR_ACHIEVEMENT : SYMBOLS_FOR_ACHIEVEMENTS) {
            if (typedSymbols == SYMBOLS_FOR_ACHIEVEMENT) {
                return SYMBOLS_FOR_ACHIEVEMENT;
            }
        }
        return 0;
    }
}
