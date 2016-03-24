package ru.sadv1r.afc.ideaPlugin.achievements;

import com.intellij.execution.filters.Filter;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import org.jetbrains.annotations.Nullable;
import ru.sadv1r.afc.ideaPlugin.Achievable;

import java.util.Calendar;

/**
 * Created by sadvr on 12/17/15.
 */
public class WeekWithoutNullPointerException implements Filter, Achievable {
    private int finish = 7;
    private final String name = "Watch your null's";
    private final String text = "Week without NullPointerException";

    @Nullable
    @Override
    public Result applyFilter(String line, int entireLength) {
        if (stats.getDaysWithoutNullPointer() < 7) {
            System.out.println("Дней меньше 7");
            if (line.contains("java.lang.NullPointerException")) {
                System.out.println("Детектируем NullPointer");
                stats.resetDaysWithoutNullPointer();
            } else {
                System.out.println("NullPointer отсутствует");
                Calendar currentCalendar = Calendar.getInstance();
                Calendar lastCalendarWithoutNullPointer = Calendar.getInstance();
                lastCalendarWithoutNullPointer.setTimeInMillis(stats.getLastDateWithoutNullPointer());

                boolean sameDay = currentCalendar.get(Calendar.YEAR) == lastCalendarWithoutNullPointer.get(Calendar.YEAR) &&
                        currentCalendar.get(Calendar.DAY_OF_YEAR) == lastCalendarWithoutNullPointer.get(Calendar.DAY_OF_YEAR);

                if (stats.getDaysWithoutNullPointer() == 0) {
                    stats.addDayWithoutNullPointer();
                    stats.setLastDateWithoutNullPointer(currentCalendar.getTimeInMillis());
                } else if (!sameDay) {
                    System.out.println("Не тот же день NullPointer");
                    stats.setLastDateWithoutNullPointer(currentCalendar.getTimeInMillis());
                    stats.addDayWithoutNullPointer();

                    if (stats.getDaysWithoutNullPointer() == 7) {
                        Notifications.Bus.notify(new Notification("Achievement", getName(), getText(), NotificationType.INFORMATION));
                    }
                }
            }
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
        return stats.getDaysWithoutNullPointer() >= finish ? finish : stats.getDaysWithoutNullPointer();
    }

    @Override
    public int getFinish() {
        return finish;
    }
}
