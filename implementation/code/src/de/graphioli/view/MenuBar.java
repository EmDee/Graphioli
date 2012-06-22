package de.graphioli.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;

/**
 * The {@link MenuBar} contains the game menu, options menu and help menu. It is displayed at the top of the {@link GameWindow} and starts the standard menu item specific methods.
 * 
 * @author Graphioli
 */
public class MenuBar implements ActionListener {

	/**
	 * The game menu 
	 */
	private JMenu gameMenu;
	
	/**
	 * The help menu
	 */
	private JMenu helpMenu;
	
	/**
	 * The options menu
	 */
	private JMenu optionsMenu;

	/**
	 * The parent {@link GameWindow} associated with this {@ MenuBar}
	 */
	private GameWindow parentGameWindow;
	
	/**
	 * Creates the {@link MenuBar} and registers its parent {@link GameWindow}.
	 * 
	 * @param parentGameWindow The {@link GameWindow} that contains this {@link MenuBar}
	 */
	public MenuBar(GameWindow parentGameWindow) {
		
	}
	
	/**
	 * Creates the standard menus including their menu items.
	 * 
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	private boolean createMenus() {
		return true;
	}
	
	/**
	 * Invoked when a menu item is selected and performs the menu item specific action.
	 * 
	 * @param event The {@link ActionEvent} when a menu item is selected
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub

	}

}
