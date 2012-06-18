package de.graphioli.controller;
/**
 * This class acts as an interface between the graphical user interface (GUI) and
 * the other parts of the framework. It pushes update notifications to the view and
 * receives user input from it.
 * 
 * @author Graphioli
 */
public class ViewManager {

	/**
	 * The {@link View} associated with this {@link ViewManager}.
	 */
	private View view;

	/**
	 * The {@link GameManager} associated with this {@link ViewManager}.
	 */
	private GameManager gameManager;


	/**
	 * Creates a new ViewManager associated with the given {@link GameManager}.
	 * 
	 * @param gameManager
	 */
	public ViewManager(GameManager gameManager) {}


	/**
	 * Notifies the {@link View} to display the given {@link Player} as active.
	 * 
	 * @param player
	 * @return boolean
	 */
	public boolean updatePlayerStatus(Player player) {}


	/**
	 * Callback function used by the {@link View} to notify about a click
	 * on a {@link GridPoint}.
	 * 
	 * @param gridPoint
	 * @return boolean
	 */
	public boolean onGridPointClick(GridPoint gridPoint) {}


	/**
	 * Callback function used by the {@link View} to notify about a key release.
	 * 
	 * @param keycode
	 * @return boolean
	 */
	public boolean onKeyRelease(int keycode) {}


	/**
	 * Notifies the {@link View} to display the error message.
	 * 
	 * @param message
	 * @return boolean
	 */
	public boolean displayErrorMessage(String message) {}


	/**
	 * Notifies the {@link View} to display the given message in a pop-up.
	 * 
	 * @param message
	 * @return boolean
	 */
	public boolean displayPopUp(String message) {}


	/**
	 * Notifies the {@link View} to add the given {@link MenuItem} to the menu.
	 * 
	 * @param item
	 * @return boolean
	 * @todo Facultative
	 */
	public boolean addGameMenuItem(MenuItem item) {}


	/**
	 * Callback function used by the {@link View} to notify about a click on a
	 * previously added {@link MenuItem}.
	 * 
	 * @param item
	 * @return boolean
	 * @todo Facultative
	 */
	public boolean onMenuItemClick(MenuItem item) {}


	/**
	 * Returns the associated {@link GameManager}.
	 * 
	 * @return the associated {@link GameManager}
	 */
	public boolean getGameManager() {
		return this.gameManager;
	}


	/**
	 * Notifies the {@link View} to change the size of the {@link VisualVertex}es
	 * to the given value.
	 * 
	 * @param size
	 * @return boolean
	 */
	public boolean setVisualVertexSize(int size) {}

}