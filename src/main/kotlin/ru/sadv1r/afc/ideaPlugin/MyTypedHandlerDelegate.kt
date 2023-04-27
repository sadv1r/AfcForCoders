package ru.sadv1r.afc.ideaPlugin

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import ru.sadv1r.afc.ideaPlugin.achievements.TenSymbolsTyped

class MyTypedHandlerDelegate: TypedHandlerDelegate() {

    private val stats = ServiceManager.getService(Stats::class.java)
    private val tenSymbolsTyped = TenSymbolsTyped()

    override fun charTyped(c: Char, project: Project, editor: Editor, file: PsiFile): Result {
        stats.state
        stats.symbolTyped()

        checkAchievementReached(tenSymbolsTyped);
       // checkAchievementReached(thousandSymbolsTyped);

        return Result.CONTINUE;
    }

    private fun checkAchievementReached(achievable: Achievable) {
        if (stats.symbolsTyped == achievable.finish) {
            Notifications.Bus.notify(Notification("Achievement", achievable.name, achievable.text, NotificationType.INFORMATION))
            //get запрос на сервер если не оффлайн
        }
    }

}