package ru.sadv1r.afc.ideaPlugin;

/**
 * Created on 12/9/15.
 *
 * @author sadv1r
 * @version 0.1
 */

import com.intellij.ui.JBColor;
import net.miginfocom.swing.MigLayout;
import ru.sadv1r.afc.ideaPlugin.achievements.HelloWorld;
import ru.sadv1r.afc.ideaPlugin.achievements.TenSymbolsTyped;
import ru.sadv1r.afc.ideaPlugin.achievements.ThousandSymbolsTyped;
import ru.sadv1r.afc.ideaPlugin.achievements.WeekWithoutNullPointerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PluginSettingsForm {
    private final JPanel root;
    private final JCheckBox playOffline = new JCheckBox();
    private final JTextField token = new JTextField();
    private JLabel tokenLabel = new JLabel("Token");
    private JButton browse = new JButton("Test");

    public PluginSettingsForm() {

        JPanel projectSettings = new JPanel(new MigLayout("", "[grow]", "[nogrid]"));
        projectSettings.setBorder(BorderFactory.createTitledBorder("Authorisation Settings"));
        {
            playOffline.setText("Play Offline");
            playOffline.setMnemonic('U');
            playOffline.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    enableOrDisableProjectSettings();
                }
            });
            //playOffline.setSelected(true);
            projectSettings.add(playOffline, "wrap");
        }
        {
            tokenLabel.setDisplayedMnemonic('C');
            tokenLabel.setLabelFor(token);


            browse.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Тестик нажал");
                }
            });

            projectSettings.add(tokenLabel, "wrap");
            projectSettings.add(token, "growx");
            projectSettings.add(browse, "wrap");
        }


        JPanel ideSettings = new JPanel(new MigLayout("", "[grow]", "[nogrid]"));
        ideSettings.setBorder(BorderFactory.createTitledBorder("Achievements"));
        ideSettings.setLayout(new BoxLayout(ideSettings, BoxLayout.PAGE_AXIS));
        {
            ideSettings.add(getAchievementPanel(new TenSymbolsTyped()));
            ideSettings.add(Box.createVerticalStrut(7));
            ideSettings.add(getAchievementPanel(new ThousandSymbolsTyped()));
            ideSettings.add(Box.createVerticalStrut(7));
            ideSettings.add(getAchievementPanel(new HelloWorld()));
            ideSettings.add(Box.createVerticalStrut(7));
            ideSettings.add(getAchievementPanel(new WeekWithoutNullPointerException()));



        }



//        {
//            JLabel label = new JLabel("SBT launcher JAR file (sbt-launch.jar). Leave blank to use the bundled launcher.");
//            label.setDisplayedMnemonic('L');
//            label.setLabelFor(applicationSbtLauncherJarPath);
//            label.setToolTipText("When using the bundled launcher, ./project/build.properties should contain the desired SBT version, e.g. sbt.version=0.12.0");
//
//            JButton browse = new JButton("...");
//            browse.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    browseForSbtLauncherJar(SbtSettingsForm.this.applicationSbtLauncherJarPath);
//                }
//            });
//
//            ideSettings.add(label, "wrap");
//            ideSettings.add(applicationSbtLauncherJarPath, "growx");
//            ideSettings.add(browse, "wrap");
//        }
//        {
//            JLabel label = new JLabel("VM parameters");
//            label.setDisplayedMnemonic('V');
//            label.setLabelFor(applicationVmParameters);
//            ideSettings.add(label, "wrap");
//            ideSettings.add(applicationVmParameters, "wrap, growx");
//        }

//        JPanel ideSettings = new JPanel(new MigLayout("", "[grow]", "[nogrid]"));
//        ideSettings.setBorder(BorderFactory.createTitledBorder("Other Settings"));
//        {
//            JLabel label = new JLabel("SBT launcher JAR file (sbt-launch.jar). Leave blank to use the bundled launcher.");
//            label.setDisplayedMnemonic('L');
//            label.setLabelFor(applicationSbtLauncherJarPath);
//            label.setToolTipText("When using the bundled launcher, ./project/build.properties should contain the desired SBT version, e.g. sbt.version=0.12.0");
//
//            JButton browse = new JButton("...");
//            browse.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    browseForSbtLauncherJar(SbtSettingsForm.this.applicationSbtLauncherJarPath);
//                }
//            });
//
//            ideSettings.add(label, "wrap");
//            ideSettings.add(applicationSbtLauncherJarPath, "growx");
//            ideSettings.add(browse, "wrap");
//        }
//        {
//            JLabel label = new JLabel("VM parameters");
//            label.setDisplayedMnemonic('V');
//            label.setLabelFor(applicationVmParameters);
//            ideSettings.add(label, "wrap");
//            ideSettings.add(applicationVmParameters, "wrap, growx");
//        }

        root = new JPanel(new MigLayout("wrap 1", "[grow]"));
        root.add(projectSettings, "grow");
        root.add(ideSettings, "grow");
        enableOrDisableProjectSettings();
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

    /**
     * Метод переносит данные из формы в память
     *
     * @param pluginSettings объект настроек плагина
     */
    public void copyTo(PluginSettings pluginSettings) {
        if (token.getText().length() == 0) {
            pluginSettings.setToken("");
        } else {
            System.out.println("Сохраняю настройки");
            pluginSettings.setToken(token.getText());
        }

        pluginSettings.setPlayOffline(playOffline.isSelected());
        enableOrDisableProjectSettings();
    }

    /**
     * Метод переносит данные из памяти в форму
     *
     * @param pluginSettings объект настроек плагина
     */
    public void copyFrom(PluginSettings pluginSettings) {
        if (pluginSettings.getToken().length() == 0) {
            token.setText("");
        } else {
            token.setText(pluginSettings.getToken());
        }

        playOffline.setSelected(pluginSettings.isPlayOffline());
    }

    public boolean isModified(PluginSettings pluginSettings) {
        return !(this.playOffline.isSelected() == pluginSettings.isPlayOffline() &&
                this.token.getText().equals(pluginSettings.getToken()));
    }



    private void enableOrDisableProjectSettings() {
        boolean enable = !playOffline.isSelected();
        tokenLabel.setEnabled(enable);
        token.setEnabled(enable);
        browse.setEnabled(enable);
    }

    public JComponent createComponent() {
        return root;
    }

    public static void main(String[] args) {
        PluginSettingsForm form = new PluginSettingsForm();

        JFrame frame = new JFrame("Test: AfcSettingsForm");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(form.createComponent());
        frame.setSize(600, 600);
        frame.setLocation(500, 300);
        frame.setVisible(true);
    }
}