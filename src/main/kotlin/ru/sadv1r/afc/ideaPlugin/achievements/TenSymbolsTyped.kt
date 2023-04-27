package ru.sadv1r.afc.ideaPlugin.achievements

import ru.sadv1r.afc.ideaPlugin.Achievable

class TenSymbolsTyped(
    name: String = "Find the keyboard",
    progress: Int = 0,
    finish: Int = 10,
    text: String = "Type $finish symbols"
): Achievable(name, text, progress, finish) {
    override val progress: Int
        get() = if (stats.symbolsTyped >= finish) finish else stats.symbolsTyped
}