package ru.sadv1r.afc.ideaPlugin.achievements

import ru.sadv1r.afc.ideaPlugin.Achievable

class HalfMillionSymbolsTyped(
    name: String = "You'r a serious coder",
    progress: Int,
    finish: Int = 500_000,
    text: String = "Type $finish symbols"
) : Achievable(name, text, progress, finish) {

    override val progress: Int
        get() = if(stats.symbolsTyped >= finish ) finish else stats.symbolsTyped
}