package ru.sadv1r.afc.ideaPlugin

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "AfcStats",
    storages = [Storage("\$APP_CONFIG\$/AfcStats.xml")]
)
class Stats(
    //TenSymbolsTyped, ThousandSymbolsTyped
    var symbolsTyped: Int = 0,

    //HelloWorld

    var helloWorldAchieved: Int = 0,

    //WeekWithoutNullPointerException

    var daysWithoutNullPointer: Int = 0,

    var lastDateWithoutNullPointer: Long = 0,

    //DayWithoutCopyPaste

     var dayWithoutCopyPaste: Int = 0,

     var lastDateDayWithoutCopyPaste: Long = 0
) : PersistentStateComponent<Stats> {

    override fun getState(): Stats {
        return this
    }

    override fun loadState(state: Stats) {
        XmlSerializerUtil.copyBean(state, this)
    }

    fun symbolTyped(){ symbolsTyped++ }
    fun resetDaysWithoutNullPointer() {
        daysWithoutNullPointer = 0
    }

    fun addDayWithoutNullPointer() {
        daysWithoutNullPointer++
    }

    fun setHelloWorldAchieved() {
        helloWorldAchieved = 1
    }
}