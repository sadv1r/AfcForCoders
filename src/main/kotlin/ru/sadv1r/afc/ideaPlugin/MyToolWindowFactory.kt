package ru.sadv1r.afc.ideaPlugin

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.JBColor
import com.intellij.ui.content.ContentFactory
import net.miginfocom.swing.MigLayout
import ru.sadv1r.afc.ideaPlugin.achievements.HelloWorld
import ru.sadv1r.afc.ideaPlugin.achievements.WeekWithoutNullPointer
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.*

class MyToolWindowFactory: ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val mainPanel =  JPanel(MigLayout("", "[grow]", "[nogrid]"));
        mainPanel.layout = BoxLayout(mainPanel, BoxLayout.PAGE_AXIS);

      //  mainPanel.add(getAchievementPanel(TenSymbolsTyped()))
        mainPanel.add(Box.createVerticalStrut(7))
      //  mainPanel.add(getAchievementPanel(ThousandSymbolsTyped()))
        mainPanel.add(Box.createVerticalStrut(7))
        mainPanel.add(getAchievementPanel(HelloWorld()))
        mainPanel.add(Box.createVerticalStrut(7))
        mainPanel.add(getAchievementPanel(WeekWithoutNullPointer()))


        //Создаем фабрику контента
        val contentFactory = ContentFactory.SERVICE.getInstance();
        //Создаем контент(окно) с нашим GUI
        val content = contentFactory.createContent(mainPanel, "", false);
        //Добавляем в IDE
        toolWindow.contentManager.addContent(content);
    }

    private fun getAchievementPanel(achievement: Achievable): JPanel {
        val panel = JPanel()
        panel.background = JBColor.GRAY
        panel.preferredSize = Dimension(0, 50)
        panel.toolTipText = achievement.text
        if (achievement.finish <= (achievement.progress ?: 0)) {
            val label = JLabel("${achievement.name} (Achieved)")
            label.setDisplayedMnemonic('V')
            panel.add(label, "wrap")
        } else {
            val jProgressBar = JProgressBar(0, achievement.finish)
            jProgressBar.value = achievement.progress ?: 0
            jProgressBar.isStringPainted = true
            jProgressBar.string = "${achievement.progress}/${achievement.finish}"
            jProgressBar.preferredSize = Dimension(700, 20)
            val label = JLabel(achievement.name)
            label.setDisplayedMnemonic('V')
            label.labelFor = jProgressBar
            panel.add(label)
            panel.add(jProgressBar, BorderLayout.NORTH)
        }
        return panel
    }
}