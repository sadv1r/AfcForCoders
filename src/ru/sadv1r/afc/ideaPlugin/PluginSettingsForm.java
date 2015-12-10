package ru.sadv1r.afc.ideaPlugin;

/**
 * Created on 12/9/15.
 *
 * @author sadv1r
 * @version 0.1
 */

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PluginSettingsForm {
    private final JPanel root;
    private final JCheckBox playOffline = new JCheckBox();
    private final JTextField token = new JTextField();
    private JLabel TokenLabel = new JLabel("Token");

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
            projectSettings.add(playOffline, "wrap");
        }
        {
            TokenLabel.setDisplayedMnemonic('C');
            TokenLabel.setLabelFor(token);

            JButton browse = new JButton("Test");
            browse.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Тестик нажал");
                }
            });

            projectSettings.add(TokenLabel, "wrap");
            projectSettings.add(token, "growx");
            projectSettings.add(browse, "wrap");
        }

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
//        root.add(ideSettings, "grow");
        enableOrDisableProjectSettings();
    }

    /**
     * Метод переносит данные из формы в память
     *
     * @param pluginSettings
     */
    public void copyTo(PluginSettings pluginSettings) {
        if (token.getText().length() == 0) {
            pluginSettings.setToken("");
        } else {
            System.out.println("пихаю в файл:" + token.getText());
            pluginSettings.setToken(token.getText());
        }

        pluginSettings.setPlayOffline(playOffline.isSelected());
        enableOrDisableProjectSettings();
    }

    /**
     * Метод переносит данные из памяти в форму
     *
     * @param pluginSettings
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
        PluginSettings currentSettings = new PluginSettings();
        copyTo(currentSettings);
        return !currentSettings.equals(pluginSettings);
    }


    private void enableOrDisableProjectSettings() {
        boolean enable = !playOffline.isSelected();
        TokenLabel.setEnabled(enable);
        token.setEnabled(enable);
    }

    public JComponent createComponent() {
        return root;
    }

    public static void main(String[] args) {
        PluginSettingsForm form = new PluginSettingsForm();

        JFrame frame = new JFrame("Test: SbtSettingsForm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(form.createComponent());
        frame.setSize(600, 600);
        frame.setLocation(500, 300);
        frame.setVisible(true);
    }
}