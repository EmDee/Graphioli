package de.graphioli.view;

import de.graphioli.utils.Localization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;

/**
 * The {@link MenuBar} contains the game menu, options menu and help menu. It is
 * displayed at the top of the {@link GameWindow} and starts the standard menu
 * item specific methods.
 * 
 * @author Graphioli
 */
public class MenuBar extends JMenuBar implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The game menu.
	 */
	private JMenu gameMenu;

	/**
	 * The help menu.
	 */
	private JMenu helpMenu;

	/**
	 * The options menu.
	 */
	private JMenu optionsMenu;

	/**
	 * The parent {@link GameWindow} associated with this @ MenuBar}.
	 */
	private GameWindow parentGameWindow;

	private JMenuItem saveItem;
	private JMenuItem loadItem;
	private JMenuItem quitItem;
	private JMenuItem helpItem;

	/**
	 * Creates the {@link MenuBar} and registers its parent {@link GameWindow}.
	 * 
	 * @param parentGameWindow
	 *            The {@link GameWindow} that contains this {@link MenuBar}
	 */
	public MenuBar(GameWindow parentGameWindow) {
		this.parentGameWindow = parentGameWindow;
		this.createMenus();
		// TODO create menus...
	}

	/**
	 * Creates the standard menus including their menu items.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	private boolean createMenus() {
		// this.saveItem = Localization.getLanguageString("menu_item_save");
		// this.loadItem = Localization.getLanguageString("menu_item_load");
		// this.quitItem = Localization.getLanguageString("menu_item_quit");
		// this.helpItem = Localization.getLanguageString("menu_item_help");

		this.gameMenu = new JMenu(Localization.getLanguageString("menu_game"));
		this.optionsMenu = new JMenu(Localization.getLanguageString("menu_options"));
		this.helpMenu = new JMenu(Localization.getLanguageString("menu_help"));

		this.saveItem = new JMenuItem(Localization.getLanguageString("menu_item_save"));
		this.loadItem = new JMenuItem(Localization.getLanguageString("menu_item_load"));
		this.quitItem = new JMenuItem(Localization.getLanguageString("menu_item_quit"));
		this.helpItem = new JMenuItem(Localization.getLanguageString("menu_item_help"));

		this.saveItem.addActionListener(this);
		this.loadItem.addActionListener(this);
		this.quitItem.addActionListener(this);
		this.helpItem.addActionListener(this);

		this.gameMenu.add(this.saveItem);
		this.gameMenu.add(this.loadItem);
		this.gameMenu.add(this.quitItem);
		this.helpMenu.add(this.helpItem);

		this.add(this.gameMenu);
		this.add(this.optionsMenu);
		this.add(this.helpMenu);

		return true;
	}

	/**
	 * Invoked when a menu item is selected and performs the menu item specific
	 * action.
	 * 
	 * @param event
	 *            The {@link ActionEvent} when a menu item is selected
	 */
	@Override
	public void actionPerformed(ActionEvent event) {

		JMenuItem sourceItem = (JMenuItem) event.getSource();

		if (sourceItem.equals(this.saveItem)) {
			String gameName = this.parentGameWindow.getViewManager().getGameManager().getGame().getClass().getName();
			gameName = gameName.substring(gameName.indexOf('.') + 1, gameName.length());
			JFileChooser fc = new JFileChooser(new File("games/" + gameName + "/"));
			SaveGameFilter filter = new SaveGameFilter();
			fc.setFileFilter(filter);
			int returnVal = fc.showSaveDialog(this.parentGameWindow);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fc.getSelectedFile();
				String filePath = fileToSave.getAbsolutePath();
//				if (!filePath.endsWith(".save")) {
//					fileToSave.delete();
//					int seperatorIndex = filePath.indexOf('.');
//					if (seperatorIndex == -1) {
//						filePath = filePath + ".save";
//					} else {
//						filePath = filePath.substring(0, seperatorIndex) + ".save";
//						fileToSave = new File(filePath);
//						try {
//							fileToSave.createNewFile();
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
//				}
				this.parentGameWindow.getViewManager().getGameManager().saveGame(fileToSave);
			}
		}

		if (sourceItem.equals(this.loadItem)) {
			JFileChooser fc = new JFileChooser();
			SaveGameFilter filter = new SaveGameFilter();
			fc.setFileFilter(filter);
			int returnVal = fc.showOpenDialog(this.parentGameWindow);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File fileToLoad = fc.getSelectedFile();
				this.parentGameWindow.getViewManager().getGameManager().loadGame(fileToLoad);
			}
		}

		if (sourceItem.equals(this.quitItem)) {
			this.parentGameWindow.closeGame();
		}

		if (sourceItem.equals(this.helpItem)) {
			this.parentGameWindow.getViewManager().getGameManager().openHelpFile();
		}

	}
	
	private class SaveGameFilter extends FileFilter {
		
		public boolean accept(File file) {
			String filename = file.getName();
	        return filename.endsWith(".save");
		}
		
		public String getDescription() {
			return "*.save";
		}
	}

}
