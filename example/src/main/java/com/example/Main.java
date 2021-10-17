// Licensed under the MIT license. See LICENSE file in the project root for details.

package com.example;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import io.github.kolod.FlatlafThemesComboBoxModel;

public class Main extends JFrame {
    private static final FlatlafThemesComboBoxModel themesModel = new FlatlafThemesComboBoxModel();
	private final JComboBox<String> theme = new JComboBox<>();

	/**
	 * Entry point
	 *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Create and display the form */
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }

	/**
     * Creates new form TestCheater
     */
    public Main() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
    private void initComponents() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("FlatlafThemesComboBoxModel Example");
		theme.setModel(themesModel);
		add(theme);
		pack();
		setLocationRelativeTo(null);
	}
}
