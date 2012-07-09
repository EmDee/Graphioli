package de.graphioli.view;

import java.util.List;

import de.graphioli.controller.ViewManager;
import de.graphioli.model.MenuItem;
import de.graphioli.model.Player;

/**
 * The View interface defines the methods a graphical user interface (GUI) for
 * the Graphioli framework has to implement.
 * 
 * @author Graphioli
 */
public interface View {

	/**
	 * Registers a {@link ViewManager} as the controller for the user interface.
	 * 
	 * @param viewManager
	 *            The controlling ViewManager
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	boolean registerController(ViewManager viewManager);

	/**
	 * Displays a message in a pop-up.
	 * 
	 * @param message
	 *            The message to display
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	boolean displayPopUp(String message);

	/**
	 * Adds a custom {@link MenuItem} to the menu.
	 * 
	 * @param item
	 *            The MenuItem to add
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 * @todo Facultative
	 */
	// public boolean addCustomMenuItem(MenuItem item);

	/**
	 * Updates which {@link Player} is displayed as active.
	 * 
	 * @param player
	 *            The new active player
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	boolean updatePlayerStatus(Player player);

	/**
	 * Displays an error message.
	 * 
	 * @param message
	 *            The message to display
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	boolean displayErrorMessage(String message);

	/**
	 * Redraws the {@link Graph}.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	boolean redrawGraph();

	/**
	 * Sets the size of the {@link VisualVertex}es displayed.
	 * 
	 * @param size
	 *            The size of the vertices
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	boolean setVisualVertexSize(int size);

	/**
	 * Disposes all components of the View.
	 * 
	 * @return {@code true} if the disposal was successful
	 */
	boolean closeView();
	
	/**
	 * Adds a list of menu items to the options menu.
	 * 
	 * @return {@code true} if the adding was successful
	 */
	boolean addMenuItems(List<MenuItem> menu);

	/**
	 * Asks the player if the game should be restarted.
	 * 
	 * @return {@code true} if player wants to restart, {@code false} otherwise
	 */
	boolean askForRestart();

}
