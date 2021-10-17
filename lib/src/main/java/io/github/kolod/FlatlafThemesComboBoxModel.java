// Licensed under the MIT license. See LICENSE file in the project root for details.

package io.github.kolod;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.io.Reader;
import java.io.InputStreamReader;
import java.nio.file.FileSystemNotFoundException;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import static javax.swing.UIManager.setLookAndFeel;

public class FlatlafThemesComboBoxModel extends AbstractListModel<String> implements ComboBoxModel<String> {

    private class ThemeInfo {
        String name;
        String className;
        String resource;

        ThemeInfo() {
        }
    }

    private static final String THEMES_PACKAGE = "/com/formdev/flatlaf/intellijthemes/themes/";
    private static final Logger logger = LogManager.getLogger();
    private List<ThemeInfo> themes = new ArrayList<>();
    private Integer selected = null;

    /**
     * Constructor.
     */
    public FlatlafThemesComboBoxModel() {
        try {
            Type listType = new TypeToken<ArrayList<ThemeInfo>>() {
            }.getType();
            Reader reader = new InputStreamReader(FlatlafThemesComboBoxModel.class.getResourceAsStream("themes.json"));
            themes = new Gson().fromJson(reader, listType);
            selected = themes.size() > 0 ? 0 : null;
            logger.info(String.format("%d bundled themes loaded.", themes.size()));
        } catch (FileSystemNotFoundException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    private void apply() throws UnsupportedLookAndFeelException {
        ThemeInfo theme = themes.get(selected);
        if (theme != null) {
            if (theme.className != null) {
                FlatAnimatedLafChange.showSnapshot();
                try {
                    setLookAndFeel(theme.className);
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
                    logger.error("Failed to create '" + theme.className + "'.", ex);
                }
            } else {
                FlatAnimatedLafChange.showSnapshot();
                IntelliJTheme.setup(getClass().getResourceAsStream(THEMES_PACKAGE + theme.resource));
            }

            // update all components
            FlatLaf.updateUI();
            FlatAnimatedLafChange.hideSnapshotWithAnimation();
            logger.info(String.format("Set theme: %s.", theme.name));
        }
    }

    @Override
    public int getSize() {
        return themes.size();
    }

    @Override
    public String getElementAt(int index) {
        ThemeInfo theme = themes.get(index);
        return theme != null ? theme.name : "";
    }

    @Override
    public void setSelectedItem(Object anItem) {
        try {
            if (anItem instanceof Integer) {
                selected = (Integer) anItem;
                apply();
            } else if (anItem instanceof String) {
                for (Integer index = 0; index < themes.size(); index++) {
                    if (themes.get(index).name.equals((String) anItem)) {
                        selected = index;
                        apply();
                        break;
                    }
                }
            }
        } catch (UnsupportedLookAndFeelException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    public Object getSelectedItem() {
        return selected == null ? null : themes.get(selected).name;
    }
}
