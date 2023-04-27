package ru.sadv1r.afc.ideaPlugin.achievements

import com.intellij.execution.filters.Filter
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import ru.sadv1r.afc.ideaPlugin.Achievable
import java.util.*

class WeekWithoutNullPointer(
    name: String = "Watch your null's",
    text: String = "Week without NullPointerException",
    progress: Int = 0,
    finish: Int = 7
) : Filter, Achievable(name, text, progress, finish) {
    override val progress: Int
        get() = stats.daysWithoutNullPointer.coerceAtMost(finish)

    override fun applyFilter(line: String, entireLength: Int): Filter.Result? {
        if (stats.daysWithoutNullPointer < 7) {
            println("Дней меньше 7")
            if (line.contains("java.lang.NullPointerException")) {
                println("Детектируем NullPointer")
                stats.resetDaysWithoutNullPointer()
            } else {
                println("NullPointer отсутствует")
                val currentCalendar = Calendar.getInstance()
                val lastCalendarWithoutNullPointer = Calendar.getInstance()
                lastCalendarWithoutNullPointer.timeInMillis = stats.lastDateWithoutNullPointer;

                val sameDay = currentCalendar.get(Calendar.YEAR) == lastCalendarWithoutNullPointer.get(Calendar.YEAR) &&
                        currentCalendar.get(Calendar.DAY_OF_YEAR) == lastCalendarWithoutNullPointer.get(Calendar.DAY_OF_YEAR)

                if (stats.daysWithoutNullPointer == 0) {
                    stats.addDayWithoutNullPointer()
                    stats.lastDateWithoutNullPointer = currentCalendar.timeInMillis
                } else if (!sameDay) {
                    println("Не тот же день NullPointer");
                    stats.lastDateWithoutNullPointer = currentCalendar.timeInMillis
                    stats.addDayWithoutNullPointer()

                    if (stats.daysWithoutNullPointer == 7) {
                        Notifications.Bus.notify(
                            Notification("Achievement",
                            name,
                            text,
                            NotificationType.INFORMATION
                        )
                        )
                    }
                }
            }
        }
        return null
    }
}