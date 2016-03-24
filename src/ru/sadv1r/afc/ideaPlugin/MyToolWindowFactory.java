package ru.sadv1r.afc.ideaPlugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.JBColor;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NotNull;
import ru.sadv1r.afc.ideaPlugin.achievements.HelloWorld;
import ru.sadv1r.afc.ideaPlugin.achievements.TenSymbolsTyped;
import ru.sadv1r.afc.ideaPlugin.achievements.ThousandSymbolsTyped;
import ru.sadv1r.afc.ideaPlugin.achievements.WeekWithoutNullPointerException;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sadvr on 3/24/16.
 */
public class MyToolWindowFactory implements ToolWindowFactory {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTextArea MainText;
    private JList FileList;
    private JPanel FilesTab;
    private JPanel TextTab;
    private JScrollPane TextPane;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        JPanel mainPanel = new JPanel(new MigLayout("", "[grow]", "[nogrid]"));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        mainPanel.add(getAchievementPanel(new TenSymbolsTyped()));
        mainPanel.add(Box.createVerticalStrut(7));
        mainPanel.add(getAchievementPanel(new ThousandSymbolsTyped()));
        mainPanel.add(Box.createVerticalStrut(7));
        mainPanel.add(getAchievementPanel(new HelloWorld()));
        mainPanel.add(Box.createVerticalStrut(7));
        mainPanel.add(getAchievementPanel(new WeekWithoutNullPointerException()));


        //Создаем фабрику контента
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        //Создаем контент(окно) с нашим GUI
        Content content = contentFactory.createContent(mainPanel, "", false);
        //Добавляем в IDE
        toolWindow.getContentManager().addContent(content);
    }

    private JPanel getAchievementPanel(Achievable achievement) {
        final JPanel panel = new JPanel();
        panel.setBackground(JBColor.GRAY);
        panel.setPreferredSize(new Dimension(0, 50));
        panel.setToolTipText(achievement.getText());

        if (achievement.getFinish() <= achievement.getProgress()) {
            JLabel label = new JLabel(achievement.getName() + " (Achieved)");
            label.setDisplayedMnemonic('V');
            panel.add(label, "wrap");
        } else {
            //UIManager.put("ProgressBar.background", Color.GREEN);
            //UIManager.put("ProgressBar.foreground", Color.GREEN);
            //UIManager.put("ProgressBar.selectionBackground", Color.WHITE);
            //UIManager.put("ProgressBar.selectionForeground", Color.WHITE);
            JProgressBar jProgressBar = new JProgressBar(0, achievement.getFinish());

            jProgressBar.setValue(achievement.getProgress());
            jProgressBar.setStringPainted(true);
            jProgressBar.setString(achievement.getProgress() + "/" + achievement.getFinish());
            jProgressBar.setPreferredSize(new Dimension(700, 20));

            JLabel label = new JLabel(achievement.getName());
            label.setDisplayedMnemonic('V');
            label.setLabelFor(jProgressBar);
            panel.add(label);
            panel.add(jProgressBar);
        }
        return panel;
    }
}
