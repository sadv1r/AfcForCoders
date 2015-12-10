package ru.sadv1r.afc.ideaPlugin;


import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import ru.sadv1r.afc.ideaPlugin.achievements.TypedSymbols;

/**
 * Created on 12/7/15.
 *
 * @author sadv1r
 * @version 0.1
 */
public class MyTypedHandlerDelegate extends TypedHandlerDelegate {

    @Override
    public Result charTyped(char c, Project project, Editor editor, PsiFile file) {
        int typedSymbols = TypedSymbols.newSymbol();
        if (typedSymbols != 0) {
            Notifications.Bus.notify(new Notification("Achievement", "Achievement!", "You typed " + typedSymbols + " symbols", NotificationType.INFORMATION));
        }

        return Result.CONTINUE;
    }
}
