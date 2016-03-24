package ru.sadv1r.afc.ideaPlugin.achievements;

import com.intellij.execution.filters.Filter;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.components.ServiceManager;
import org.jetbrains.annotations.Nullable;
import ru.sadv1r.afc.ideaPlugin.Achievable;
import ru.sadv1r.afc.ideaPlugin.Stats;

/**
 * Created by sadvr on 12/16/15.
 */
public class HelloWorld implements Filter, Achievable {
    private final static Stats stats = ServiceManager.getService(Stats.class);
    private int finish = 1;
    private final String name = "Say hi to all";
    private final String text = "Type traditional Hello World";

    @Nullable
    @Override
    public Result applyFilter(String line, int entireLength) {
        if (line.contains("Hello World") && stats.getHelloWorldAchieved() != 1) {
            Notifications.Bus.notify(new Notification("Achievement", getName(), getText(), NotificationType.INFORMATION));

            stats.setHelloWorldAchieved();
        }
        return null;
    }

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
        return stats.getHelloWorldAchieved();
    }

    @Override
    public int getFinish() {
        return finish;
    }
}
