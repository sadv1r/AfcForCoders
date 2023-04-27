package ru.sadv1r.afc.ideaPlugin

import com.intellij.openapi.components.ServiceManager

abstract class Achievable(
    open val name: String,
    open val text: String,
    open val progress: Int = 0,
    open val finish: Int
){
    companion object{
        val stats: Stats = ServiceManager.getService(Stats::class.java)
    }
}