package de.graphioli.view;

import de.graphioli.utils.Localization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			this.parentGameWindow.saveFileDialog();
		}

		if (sourceItem.equals(this.loadItem)) {
			this.parentGameWindow.openFileDialog();
		}

		if (sourceItem.equals(this.quitItem)) {
			this.parentGameWindow.getViewManager().getGameManager().closeGame();
		}

		if (sourceItem.equals(this.helpItem)) {
			this.parentGameWindow.getViewManager().getGameManager().openHelpFile();
		}

	}

}
