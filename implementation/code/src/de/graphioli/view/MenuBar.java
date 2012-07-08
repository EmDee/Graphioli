package de.graphioli.view;

import de.graphioli.controller.Game;
import de.graphioli.model.MenuItem;
import de.graphioli.utils.Localization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
	 * Logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(MenuBar.class.getName());

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
	 * The custom menu items.
	 */
	private List<OptionsMenuItem> customItems;

	/**
	 * The parent {@link GameWindow} associated with this @ MenuBar}.
	 */
	private GameWindow parentGameWindow;

	private JMenuItem saveItem;
	private JMenuItem loadItem;
	private JMenuItem quitItem;
	private JMenuItem helpItem;
	private JMenuItem restartItem;

	/**
	 * Creates the {@link MenuBar} and registers its parent {@link GameWindow}.
	 * 
	 * @param parentGameWindow
	 *            The {@link GameWindow} that contains this {@link MenuBar}
	 */
	public MenuBar(GameWindow parentGameWindow) {
		this.parentGameWindow = parentGameWindow;
		this.createMenus();
	}

	/**
	 * Creates the standard menus including their menu items.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	private boolean createMenus() {

		this.gameMenu = new JMenu(Localization.getLanguageString("menu_game"));
		this.optionsMenu = new JMenu(Localization.getLanguageString("menu_options"));
		this.helpMenu = new JMenu(Localization.getLanguageString("menu_help"));

		this.saveItem = new JMenuItem(Localization.getLanguageString("menu_item_save"));
		this.loadItem = new JMenuItem(Localization.getLanguageString("menu_item_load"));
		this.quitItem = new JMenuItem(Localization.getLanguageString("menu_item_quit"));
		this.helpItem = new JMenuItem(Localization.getLanguageString("menu_item_help"));
		this.restartItem = new JMenuItem(Localization.getLanguageString("menu_item_restart"));

		this.saveItem.addActionListener(this);
		this.loadItem.addActionListener(this);
		this.quitItem.addActionListener(this);
		this.helpItem.addActionListener(this);
		this.restartItem.addActionListener(this);

		this.gameMenu.add(this.restartItem);
		this.gameMenu.addSeparator();
		this.gameMenu.add(this.saveItem);
		this.gameMenu.add(this.loadItem);
		this.gameMenu.addSeparator();
		this.gameMenu.add(this.quitItem);
		this.helpMenu.add(this.helpItem);

		this.add(this.gameMenu);
		this.add(this.optionsMenu);
		this.add(this.helpMenu);

		this.optionsMenu.setEnabled(false);
		this.customItems = null;

		return true;
	}

	public void addOptionsItems(List<MenuItem> menuItems) {
		if (menuItems.size() == 0) {
			return;
		}

		this.customItems = new ArrayList<OptionsMenuItem>(menuItems.size());

		OptionsMenuItem tmpCustItem;
		for (MenuItem tmpMenItem : menuItems) {
			tmpCustItem = new OptionsMenuItem(tmpMenItem);
			tmpCustItem.addActionListener(this);
			this.customItems.add(tmpCustItem);
			this.optionsMenu.add(tmpCustItem);
		}

		this.optionsMenu.setEnabled(true);
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
			saveGame();
		}

		else if (sourceItem.equals(this.loadItem)) {
			loadGame();
		}

		else if (sourceItem.equals(this.quitItem)) {
			this.parentGameWindow.closeGame();
		}

		else if (sourceItem.equals(this.helpItem)) {
			this.parentGameWindow.getViewManager().getGameManager().openHelpFile();
		}

		else if (sourceItem.equals(this.restartItem)) {
			this.parentGameWindow.getViewManager().getGameManager().restartGame();
		}

		else if (this.customItems.contains(sourceItem)) {
			OptionsMenuItem optItem = (OptionsMenuItem) sourceItem;
			this.parentGameWindow.getViewManager().onCustomMenuItemClick(optItem.getCustomItem());
		} else {
			LOG.warning("Unknown menu item click received: " + sourceItem.getText());
		}

	}

	private void saveGame() {
		String gameName = this.parentGameWindow.getViewManager().getGameManager().getGame().getClass().getName();
		gameName = gameName.substring(gameName.indexOf('.') + 1, gameName.length());
		JFileChooser fc = new JFileChooser(new File("games/" + gameName + "/"));
		SaveGameFilter filter = new SaveGameFilter();
		fc.setFileFilter(filter);
		int returnVal = fc.showSaveDialog(this.parentGameWindow);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fc.getSelectedFile();
			String filePath = fileToSave.getAbsolutePath();
			if (!filePath.endsWith(".save")) {
				int seperatorIndex = filePath.lastIndexOf('.');
				if (seperatorIndex == -1) {
					filePath = filePath + ".save";
				} else {
					filePath = filePath.substring(0, seperatorIndex) + ".save";
				}
				fileToSave = new File(filePath);
			}
			this.parentGameWindow.getViewManager().getGameManager().saveGame(fileToSave);
		}
	}

	private void loadGame() {
		String gameName = this.parentGameWindow.getViewManager().getGameManager().getGame().getClass().getName();
		gameName = gameName.substring(gameName.indexOf('.') + 1, gameName.length());
		JFileChooser fc = new JFileChooser(new File("games/" + gameName + "/"));
		SaveGameFilter filter = new SaveGameFilter();
		fc.setFileFilter(filter);
		int returnVal = fc.showOpenDialog(this.parentGameWindow);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File fileToLoad = fc.getSelectedFile();
			this.parentGameWindow.getViewManager().getGameManager().loadGame(fileToLoad);
		}
	}

	private class SaveGameFilter extends FileFilter {

		public boolean accept(File file) {
			String filename = file.getName();
			return filename.endsWith(".save") || file.isDirectory();
		}

		public String getDescription() {
			return "*.save";
		}
	}

}
