package ru.sadv1r.afc.ideaPlugin;


import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import ru.sadv1r.afc.ideaPlugin.achievements.TenSymbolsTyped;
import ru.sadv1r.afc.ideaPlugin.achievements.ThousandSymbolsTyped;

/**
 * Created on 12/7/15.
 *
 * @author sadv1r
 * @version 0.1
 */
public class MyTypedHandlerDelegate extends TypedHandlerDelegate {
    final Stats stats = ServiceManager.getService(Stats.class);
    final TenSymbolsTyped tenSymbolsTyped = new TenSymbolsTyped();
    final ThousandSymbolsTyped thousandSymbolsTyped = new ThousandSymbolsTyped();

    @Override
    public Result charTyped(char c, Project project, @NotNull Editor editor, @NotNull PsiFile file) {
        stats.getState();
        stats.symbolTyped();

        checkAchievementReached(tenSymbolsTyped);
        checkAchievementReached(thousandSymbolsTyped);

        return Result.CONTINUE;
    }

    private void checkAchievementReached(Achievable achievable) {
        if (stats.getSymbolsTyped() == achievable.getFinish()) {
            Notifications.Bus.notify(new Notification("Achievement", achievable.getName(), achievable.getText(), NotificationType.INFORMATION));
            //get запрос на сервер если не оффлайн
        }
    }
}
