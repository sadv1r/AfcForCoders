package ru.sadv1r.afc.ideaPlugin.achievements

import ru.sadv1r.afc.ideaPlugin.Achievable
import com.intellij.execution.filters.Filter
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications

class HelloWorld(
    name: String = "Say hi to all",
    text: String = "Type traditional Hello World",
    progress: Int = 0,
    finish: Int = 1
) : Filter, Achievable(name, text, progress, finish) {
    override val progress: Int
        get() = stats.helloWorldAchieved

    override fun applyFilter(line: String, entireLength: Int): Filter.Result? {
        if (line.contains("Hello World") && stats.helloWorldAchieved != 1) { //TODO Добавить механизм отслеживания было ли уже выдано достижение ранее. Сейчас != 1 это костыль
            Notifications.Bus.notify( Notification("Achievement", name, text, NotificationType.INFORMATION))

            stats.setHelloWorldAchieved()
        }
        return null
    }
}