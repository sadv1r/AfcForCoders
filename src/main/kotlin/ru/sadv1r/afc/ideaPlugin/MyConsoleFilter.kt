package ru.sadv1r.afc.ideaPlugin

import com.intellij.execution.filters.ConsoleFilterProvider
import com.intellij.execution.filters.Filter
import com.intellij.openapi.project.Project
import ru.sadv1r.afc.ideaPlugin.achievements.HelloWorld
import ru.sadv1r.afc.ideaPlugin.achievements.WeekWithoutNullPointer

interface MyConsoleFilter : ConsoleFilterProvider {
    override fun getDefaultFilters(project: Project): Array<Filter> {
        val helloWorld = HelloWorld()
        val weekWithoutNullPointerException = WeekWithoutNullPointer()

        return arrayOf(helloWorld, weekWithoutNullPointerException)
    }
}