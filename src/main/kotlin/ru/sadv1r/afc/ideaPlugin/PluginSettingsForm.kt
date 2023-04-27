package ru.sadv1r.afc.ideaPlugin

import com.intellij.ui.JBColor
import net.miginfocom.swing.MigLayout
import ru.sadv1r.afc.ideaPlugin.achievements.HelloWorld
import ru.sadv1r.afc.ideaPlugin.achievements.WeekWithoutNullPointer
import java.awt.Dimension
import javax.swing.*

class PluginSettingsForm {
    private val root: JPanel = JPanel(MigLayout("wrap 1", "[grow]"))
    private val playOffline: JCheckBox = JCheckBox()
    private val token: JTextField = JTextField()
    private val tokenLabel: JLabel = JLabel("Token")
    private val browse: JButton = JButton("Test")

    constructor() {
        val projectSettings = JPanel(MigLayout("", "[grow]", "[nogrid]"))
        projectSettings.border = BorderFactory.createTitledBorder("Authorization Settings")
        playOffline.text = "Play Offline"
        playOffline.setMnemonic('U')
        playOffline.addActionListener { enableOrDisableProjectSettings() }
        projectSettings.add(playOffline, "wrap")

        tokenLabel.setDisplayedMnemonic('C')
        tokenLabel.labelFor = token

        browse.addActionListener { println("Тестик нажал") }

        projectSettings.add(tokenLabel, "wrap")
        projectSettings.add(token, "growx")
        projectSettings.add(browse, "wrap")


        val ideSettings = JPanel(MigLayout("", "[grow]", "[nogrid]"))
        ideSettings.border = BorderFactory.createTitledBorder("Achievements")
        ideSettings.layout = BoxLayout(ideSettings, BoxLayout.PAGE_AXIS)

//        ideSettings.add(getAchievementPanel(TenSymbolsTyped()))
//        ideSettings.add(Box.createVerticalStrut(7))
//        ideSettings.add(getAchievementPanel(ThousandSymbolsTyped()))
//        ideSettings.add(Box.createVerticalStrut(7))
//        ideSettings.add(getAchievementPanel(LakhSymbolsTyped()))
//        ideSettings.add(Box.createVerticalStrut(7))
//        ideSettings.add(getAchievementPanel(HalfMillionSymbolsTyped()))
//        ideSettings.add(Box.createVerticalStrut(7))
//        ideSettings.add(getAchievementPanel(MillionSymbolsTyped()))
        ideSettings.add(Box.createVerticalStrut(7))
        ideSettings.add(getAchievementPanel(HelloWorld()))
        ideSettings.add(Box.createVerticalStrut(7))
        ideSettings.add(getAchievementPanel(WeekWithoutNullPointer()))

        root.add(projectSettings, "grow")
        root.add(ideSettings, "grow")
        enableOrDisableProjectSettings()
    }

    fun copyFrom(pluginSettings: PluginSettings) {
        token.text = pluginSettings.getToken().orElse("")
        playOffline.isSelected = pluginSettings.isPlayOffline()
        enableOrDisableProjectSettings()
    }

    private fun getAchievementPanel(achievement: Achievable): JPanel {
        val panel = JPanel()
        panel.background = JBColor.GRAY
        panel.preferredSize = Dimension(0, 50)
        panel.toolTipText = achievement.text

        if (achievement.finish <= (achievement.progress ?: 0)) {
            val label = JLabel(achievement.name + " (Achieved)")
            label.setDisplayedMnemonic('V')
            panel.add(label, "wrap")
        } else {
            //UIManager.put("ProgressBar.background", Color.GREEN)
            //UIManager.put("ProgressBar.foreground", Color.GREEN)
            //UIManager.put("ProgressBar.selectionBackground", Color.WHITE)
            //UIManager.put("ProgressBar.selectionForeground", Color.WHITE)
            val jProgressBar = JProgressBar(0, achievement.finish)

            jProgressBar.value = achievement.progress ?: 0
            jProgressBar.isStringPainted = true
            jProgressBar.string = "${achievement.progress}/${achievement.finish}"
            jProgressBar.preferredSize = Dimension(700, 20)

            val label = JLabel(achievement.name)
            label.setDisplayedMnemonic('V')
            label.labelFor = jProgressBar
            panel.add(label)
            panel.add(jProgressBar)
        }
        return panel
    }


    fun copyTo(pluginSettings: PluginSettings) {
        if (token.text.isNotEmpty()) {
            println("Сохраняю настройки")
            pluginSettings.storeToken(token.text)
        }

        pluginSettings.setPlayOffline(playOffline.isSelected)
        enableOrDisableProjectSettings()
    }


    private fun enableOrDisableProjectSettings() {
        val enable = !playOffline.isSelected
        tokenLabel.isEnabled = enable
        token.isEnabled = enable
        browse.isEnabled = enable
    }

    fun isModified(pluginSettings: PluginSettings): Boolean {
        return !(this.playOffline.isSelected == pluginSettings.isPlayOffline() &&
                this.token.text.equals(pluginSettings.getToken().orElse("")))
    }


    fun createComponent(): JComponent {
        return root
    }

    fun main(args: Array<String>) {
        val form = PluginSettingsForm()
        val frame = JFrame("Test: AfcSettingsForm")
        frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        frame.contentPane = form.createComponent();
        frame.setSize(600, 600)
        frame.setLocation(500, 300)
        frame.isVisible = true
    }
}