package de.graphioli.view;

import de.graphioli.model.MenuItem;
import de.graphioli.utils.GameFileDialog;
import de.graphioli.utils.Localization;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
		} else if (sourceItem.equals(this.loadItem)) {
			loadGame();
		} else if (sourceItem.equals(this.quitItem)) {
			this.parentGameWindow.closeGame();
		} else if (sourceItem.equals(this.helpItem)) {
			this.parentGameWindow.getViewManager().getGameManager().openHelpFile();
		} else if (sourceItem.equals(this.restartItem)) {
			this.parentGameWindow.getViewManager().getGameManager().restartGame();
		} else if (this.customItems.contains(sourceItem)) {
			OptionsMenuItem optItem = (OptionsMenuItem) sourceItem;
			this.parentGameWindow.getViewManager().onCustomMenuItemClick(optItem.getCustomItem());
		} else {
			LOG.warning("Unknown menu item click received: " + sourceItem.getText());
		}

	}

	/**
	 * Adds menu items to the options menu.
	 * @param menuItems the items to add.
	 */
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

	/**
	 * Displays a load game dialog.
	 */
	private void loadGame() {
		String gameName = this.parentGameWindow.getViewManager().getGameManager().getGame().getClass().getName();
		gameName = gameName.substring(gameName.indexOf('.') + 1, gameName.length());

		File fileToLoad = GameFileDialog.loadGame(gameName, this.parentGameWindow);

		if (fileToLoad != null) {
			this.parentGameWindow.getViewManager().getGameManager().loadGame(fileToLoad);
		}
	}

	/**
	 * Displays a save game dialog.
	 */
	private void saveGame() {
		String gameName = this.parentGameWindow.getViewManager().getGameManager().getGame().getClass().getName();
		gameName = gameName.substring(gameName.indexOf('.') + 1, gameName.length());

		File fileToSave = GameFileDialog.saveGame(gameName, this.parentGameWindow);

		if (fileToSave != null) {
			this.parentGameWindow.getViewManager().getGameManager().saveGame(fileToSave);
		}
	}

}
