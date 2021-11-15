// Licensed under the MIT license. See LICENSE file in the project root for details.
package io.github.kolod

import javax.swing.*

class Example : JFrame() {
	private val themesModel = FlatlafThemesComboBoxModel()
	private val theme = JComboBox<String>(themesModel)

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	private fun initComponents() {
		defaultCloseOperation = EXIT_ON_CLOSE
		title = "FlatlafThemesComboBoxModel Example"
		theme.model = themesModel
		add(theme)
		pack()
		setLocationRelativeTo(null)
	}

	/**
	 * Creates new form
	 */
	init {
		initComponents()
	}

	companion object {
		/**
		 * @param args the command line arguments
		 */
		@JvmStatic
		fun main(args: Array<String>) {
			SwingUtilities.invokeLater { Example().isVisible = true }
		}
	}
}
