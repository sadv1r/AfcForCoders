package ru.sadv1r.afc.ideaPlugin;

import com.intellij.execution.filters.ConsoleFilterProvider;
import com.intellij.execution.filters.Filter;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import ru.sadv1r.afc.ideaPlugin.achievements.HelloWorld;
import ru.sadv1r.afc.ideaPlugin.achievements.WeekWithoutNullPointerException;

/**
 * Created by sadv1r on 12/11/15.
 */
public class MyConsoleFilter implements ConsoleFilterProvider {

    @NotNull
    @Override
    public Filter[] getDefaultFilters(@NotNull Project project) {
        HelloWorld helloWorld = new HelloWorld();
        WeekWithoutNullPointerException weekWithoutNullPointerException = new WeekWithoutNullPointerException();

        return new Filter[] {helloWorld, weekWithoutNullPointerException};
    }

}
