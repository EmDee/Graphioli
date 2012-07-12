package de.graphioli.utils;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 * This class provides save and load dialogs for savegames.
 * 
 * @author Team Graphioli
 */
public final class GameFileDialog {

	/**
	 * The file extension for savegames.
	 */
	private static final String SAVE_FILE_EXT = ".save";

	/**
	 * Private constructor to ensure that no instance is created from this
	 * class.
	 */
	private GameFileDialog() {
	}

	/**
	 * Displays a load game dialog.
	 * 
	 * @param gameName
	 *            The name of the game to load
	 * @param parentWindow
	 *            The parent window for the dialog
	 * @return The game file to load
	 */
	public static File loadGame(String gameName, JFrame parentWindow) {

		JFileChooser fc = new JFileChooser(new File("games/" + gameName + "/"));
		SaveGameFilter filter = new SaveGameFilter();
		fc.setFileFilter(filter);

		int returnVal = fc.showOpenDialog(parentWindow);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		}

		return null;
	}

	/**
	 * Displays a save game dialog.
	 * 
	 * @param gameName
	 *            The name of the game to save
	 * @param parentWindow
	 *            The parent window for the dialog
	 * @return The file to save the game
	 */
	public static File saveGame(String gameName, JFrame parentWindow) {

		JFileChooser fc = new JFileChooser(new File("games/" + gameName + "/"));
		SaveGameFilter filter = new SaveGameFilter();
		fc.setFileFilter(filter);

		File fileToSave = null;
		boolean fileChosen = false;

		while (!fileChosen) {
			int returnVal = fc.showSaveDialog(parentWindow);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				fileToSave = fc.getSelectedFile();
				String filePath = fileToSave.getAbsolutePath();

				if (!filePath.endsWith(SAVE_FILE_EXT)) {

					int seperatorIndex = filePath.lastIndexOf('.');

					if (seperatorIndex == -1) {
						filePath = filePath + SAVE_FILE_EXT;
					} else {
						filePath = filePath.substring(0, seperatorIndex) + SAVE_FILE_EXT;
					}
				}

				fileToSave = new File(filePath);

				if (fileToSave.exists()) {
					int confirmChoice = JOptionPane.showConfirmDialog(parentWindow,
							Localization.getLanguageString("file_dialog_confirm_overwrite"), parentWindow.getTitle(),
							JOptionPane.YES_NO_OPTION);

					// confirmChoice == 0: Yes
					// confirmChoice == 1: No
					fileChosen = confirmChoice == 0;
				} else {
					fileChosen = true;
				}
			} else {
				return null;
			}
		}

		return fileToSave;
	}

	/**
	 * Specifies a filter for file selection.
	 * 
	 * @author Team Graphioli
	 */
	private static class SaveGameFilter extends FileFilter {
		public boolean accept(File file) {
			String filename = file.getName();
			return filename.endsWith(SAVE_FILE_EXT) || file.isDirectory();
		}

		public String getDescription() {
			return "*" + SAVE_FILE_EXT;
		}
	}

}
