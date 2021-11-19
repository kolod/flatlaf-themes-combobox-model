// Licensed under the MIT license. See LICENSE file in the project root for details.
package io.github.kolod

import com.formdev.flatlaf.*
import com.formdev.flatlaf.extras.FlatAnimatedLafChange
import javax.swing.AbstractListModel
import javax.swing.ComboBoxModel
import javax.swing.UIManager.setLookAndFeel
import javax.swing.UnsupportedLookAndFeelException
import org.slf4j.LoggerFactory

private const val THEMES_PACKAGE = "/com/formdev/flatlaf/intellijthemes/themes/"

/**
 * ComboBox Model
 */
@Suppress("unused")
class FlatlafThemesComboBoxModel : AbstractListModel<String?>(), ComboBoxModel<String?> {

    private interface ThemeInfo {
        val name :String
        fun apply() :ThemeInfo?
    }

    private class ModuleThemeInfo(override val name :String, private val laf :Class<*>) : ThemeInfo {
        private val lafInstance :FlatLaf by lazy { laf.newInstance() as FlatLaf }
        override fun apply() = try {
            FlatAnimatedLafChange.showSnapshot()
            setLookAndFeel(lafInstance)
            FlatLaf.updateUI()
            FlatAnimatedLafChange.hideSnapshotWithAnimation()
            this
        } catch (e :UnsupportedLookAndFeelException) {
            null
        }
    }

    private class IntellijThemeInfo(override val name :String, private val resource :String) : ThemeInfo {
        override fun apply() = try {
            FlatAnimatedLafChange.showSnapshot()
            IntelliJTheme.setup(javaClass.getResourceAsStream(THEMES_PACKAGE + resource))
            FlatLaf.updateUI()
            FlatAnimatedLafChange.hideSnapshotWithAnimation()
            this
        } catch (e :UnsupportedLookAndFeelException) {
            null
        }
    }

    // region themes
    private val themes = listOf(
        ModuleThemeInfo("Flat Light",    FlatLightLaf::class.java),
        ModuleThemeInfo("Flat Dark",     FlatDarkLaf::class.java),
        ModuleThemeInfo("Flat IntelliJ", FlatIntelliJLaf::class.java),
        ModuleThemeInfo("Flat Darcula",  FlatDarkLaf::class.java),

        IntellijThemeInfo("Arc", "arc-theme.theme.json"),
        IntellijThemeInfo("Arc - Orange", "arc-theme-orange.theme.json"),
        IntellijThemeInfo("Arc Dark", "arc_theme_dark.theme.json"),
        IntellijThemeInfo("Arc Dark - Orange", "arc_theme_dark_orange.theme.json"),
        IntellijThemeInfo("Carbon", "Carbon.theme.json"),
        IntellijThemeInfo("Cobalt 2", "Cobalt_2.theme.json"),
        IntellijThemeInfo("Cyan light", "Cyan.theme.json"),
        IntellijThemeInfo("Dark purple", "DarkPurple.theme.json"),
        IntellijThemeInfo("Dracula", "Dracula.theme.json"),
        IntellijThemeInfo("Gradianto Dark Fuchsia", "Gradianto_dark_fuchsia.theme.json"),
        IntellijThemeInfo("Gradianto Deep Ocean", "Gradianto_deep_ocean.theme.json"),
        IntellijThemeInfo("Gradianto Midnight Blue", "Gradianto_midnight_blue.theme.json"),
        IntellijThemeInfo("Gradianto Nature Green", "Gradianto_Nature_Green.theme.json"),
        IntellijThemeInfo("Gray", "Gray.theme.json"),
        IntellijThemeInfo("Gruvbox Dark Medium", "gruvbox_dark_medium.theme.json"),
        IntellijThemeInfo("Gruvbox Dark Soft", "gruvbox_dark_soft.theme.json"),
        IntellijThemeInfo("Hiberbee Dark", "HiberbeeDark.theme.json"),
        IntellijThemeInfo("High contrast", "HighContrast.theme.json"),
        IntellijThemeInfo("Light Flat", "LightFlatTheme.theme.json"),
        IntellijThemeInfo("Monocai", "Monocai.theme.json"),
        IntellijThemeInfo("Nord", "nord.theme.json"),
        IntellijThemeInfo("One Dark", "one_dark.theme.json"),
        IntellijThemeInfo("Solarized Dark", "SolarizedDark.theme.json"),
        IntellijThemeInfo("Solarized Light", "SolarizedLight.theme.json"),
        IntellijThemeInfo("Vuesion", "vuesion_theme.theme.json"),
        IntellijThemeInfo("Arc Dark", "material-theme-ui-lite/Arc Dark.theme.json"),
        IntellijThemeInfo("Arc Dark Contrast", "material-theme-ui-lite/Arc Dark Contrast.theme.json"),
        IntellijThemeInfo("Atom One Dark","material-theme-ui-lite/Atom One Dark.theme.json"),
        IntellijThemeInfo("Atom One Dark Contrast", "material-theme-ui-lite/Atom One Dark Contrast.theme.json"),
        IntellijThemeInfo("Atom One Light", "material-theme-ui-lite/Atom One Light.theme.json"),
        IntellijThemeInfo("Dracula Contrast", "material-theme-ui-lite/Dracula Contrast.theme.json"),
        IntellijThemeInfo("GitHub", "material-theme-ui-lite/GitHub.theme.json"),
        IntellijThemeInfo("GitHub Dark Contrast", "material-theme-ui-lite/GitHub Dark Contrast.theme.json"),
        IntellijThemeInfo("Light Owl", "material-theme-ui-lite/Light Owl.theme.json"),
        IntellijThemeInfo("Material Darker Contrast", "material-theme-ui-lite/Material Darker Contrast.theme.json"),
        IntellijThemeInfo("Material Deep Ocean", "material-theme-ui-lite/Material Deep Ocean.theme.json"),
        IntellijThemeInfo("Material Deep Ocean Contrast", "material-theme-ui-lite/Material Deep Ocean Contrast.theme.json"),
        IntellijThemeInfo("Material Lighter","material-theme-ui-lite/Material Lighter.theme.json"),
        IntellijThemeInfo("Material Oceanic Contrast", "material-theme-ui-lite/Material Oceanic Contrast.theme.json"),
        IntellijThemeInfo("Material Palenight", "material-theme-ui-lite/Material Palenight.theme.json"),
        IntellijThemeInfo("Material Palenight Contrast", "material-theme-ui-lite/Material Palenight Contrast.theme.json"),
        IntellijThemeInfo("Monokai Pro", "material-theme-ui-lite/Monokai Pro.theme.json"),
        IntellijThemeInfo("Monokai Pro Contrast", "material-theme-ui-lite/Monokai Pro Contrast.theme.json"),
        IntellijThemeInfo("Moonlight", "material-theme-ui-lite/Moonlight.theme.json"),
        IntellijThemeInfo("Moonlight Contrast", "material-theme-ui-lite/Moonlight Contrast.theme.json"),
        IntellijThemeInfo("Night Owl", "material-theme-ui-lite/Night Owl.theme.json"),
        IntellijThemeInfo("Night Owl Contrast", "material-theme-ui-lite/Night Owl Contrast.theme.json"),
        IntellijThemeInfo("Solarized Dark", "material-theme-ui-lite/Solarized Dark.theme.json"),
        IntellijThemeInfo("Solarized Dark Contrast", "material-theme-ui-lite/Solarized Dark Contrast.theme.json"),
        IntellijThemeInfo("Solarized Light", "material-theme-ui-lite/Solarized Light.theme.json"),
    )
    //endregion

    private val logger = LoggerFactory.getLogger(FlatlafThemesComboBoxModel::class.java)
    private var selected: ThemeInfo? = null

    init {
        selected = themes[0]
    }

    /**
     * Returns the length of the list.
     * @return the length of the list
     */
    override fun getSize(): Int = themes.size

    /**
     * Returns the value at the specified index.
     * @param index the requested index
     * @return the value at `index`
     */
    override fun getElementAt(index: Int): String? = themes.getOrNull(index)?.name

    /**
     * Set the selected item. The implementation of this  method should notify
     * all registered `ListDataListener`s that the contents
     * have changed.
     *
     * @param anItem the list object to select or `null`
     * to clear the selection
     */
    override fun setSelectedItem(anItem: Any?) {
        try {
            themes.firstOrNull { it.name == anItem }?.apply {
                logger.info("Set theme: ${name}.")
                selected = apply()
            } ?: logger.error("Theme not found $anItem")
        } catch (ex: UnsupportedLookAndFeelException) {
            logger.error(ex.message, ex)
        }
    }

    /**
     * Returns the selected item
     * @return The selected item or `null` if there is no selection
     */
    override fun getSelectedItem(): Any? = selected?.name
}