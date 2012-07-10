package de.graphioli.controller;

import de.graphioli.model.GridPoint;
import de.graphioli.model.MenuItem;
import de.graphioli.model.Player;
import de.graphioli.view.GameWindow;
import de.graphioli.view.View;

import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

/**
 * This class acts as an interface between the graphical user interface (GUI)
 * and the other parts of the framework. It pushes update notifications to the
 * view and receives user input from it.
 * 
 * @author Graphioli
 */
public class ViewManager {

	/**
	 * Logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(ViewManager.class.getName());

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
	 *            The controlling <code>GameManager</code>
	 */
	public ViewManager(GameManager gameManager) {
		LOG.fine("ViewManager initiated");
		this.gameManager = gameManager;

		// Instantiate view
		this.view = new GameWindow();
		this.view.registerController(this);
	}

	/**
	 * Notifies the {@link View} to display the error message.
	 * 
	 * @param message
	 *            The message to display
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean displayErrorMessage(String message) {
		return this.view.displayErrorMessage(message);
	}

	/**
	 * Notifies the {@link View} to display the given message in a pop-up. NOTE:
	 * Don't use this inside game logic. It will lead to an timeout.
	 * 
	 * @param message
	 *            The message to display
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean displayPopUp(String message) {
		return this.view.displayPopUp(message);
	}

	/**
	 * Returns the associated {@link GameManager}.
	 * 
	 * @return the associated {@link GameManager}
	 */
	public GameManager getGameManager() {
		return this.gameManager;
	}

	/**
	 * Callback function used by the {@link View} to notify about a custom menu
	 * item click.
	 * 
	 * @param menuItem
	 *            The menu item that was clicked
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean onCustomMenuItemClick(MenuItem menuItem) {
		try {
			if (this.gameManager.getGame().callOnMenuItemClick(menuItem)) {
				this.updateView();
				this.gameManager.checkFinished();
				return true;
			}
		} catch (TimeoutException e) {
			this.displayPopUp("Game timed out. Closing.");
			this.gameManager.closeGame();
		}
		return false;
	}

	/**
	 * Callback function used by the {@link View} to notify about a click on a
	 * {@link GridPoint}.
	 * 
	 * @param gridPoint
	 *            The GridPoint that was clicked
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean onGridPointClick(GridPoint gridPoint) {
		try {
			if (this.gameManager.getGameBoard().getGrid().getVisualVertexAtGridPoint(gridPoint) == null) {
				LOG.fine("OnEmptyGridPointClick " + gridPoint);
				if (this.gameManager.getGame().callOnEmptyGridPointClick(gridPoint)) {
					this.updateView();
				}

			} else {
				LOG.fine("OnVertexClick (at GridPoint "
						+ this.gameManager.getGameBoard().getGrid().getVisualVertexAtGridPoint(gridPoint)
								.getGridPoint()
						+ ")");
				if (this.gameManager.getGame().callOnVertexClick(
						this.gameManager.getGameBoard().getGrid().getVisualVertexAtGridPoint(gridPoint))) {

					this.updateView();
				}
			}
		} catch (TimeoutException toe) {
			this.displayPopUp("Game timed out. Closing.");
			this.gameManager.closeGame();
		}

		this.gameManager.checkFinished();
		return true;
	}

	/**
	 * Callback function used by the {@link View} to notify about a key release.
	 * 
	 * @param keyCode
	 *            The code of the key that was released
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean onKeyRelease(int keyCode) {
		try {
			if (this.gameManager.getGame().callOnKeyRelease(keyCode)) {
				this.updateView();
				this.gameManager.checkFinished();
				return true;
			}
		} catch (TimeoutException e) {
			this.displayPopUp("Game timed out. Closing.");
			this.gameManager.closeGame();
		}
		return false;
	}

	/**
	 * Notifies the view to add the given menu items.
	 * 
	 * @param items the menu items to add.
	 * @return {@code true} on success.
	 */
	boolean addCustomMenuItems(List<MenuItem> items) {
		return this.view.addMenuItems(items);
	}

	/**
	 * Informs the View to ask the player if the game should be restarted.
	 * 
	 * @return {@code true} if player wants to restart, {@code false} otherwise
	 */
	boolean askForRestart() {
		return this.view.askForRestart();
	}

	/**
	 * Closes the View and all its components.
	 * 
	 * @return {@code true} if the disposal was successful
	 */
	boolean closeView() {
		this.view.closeView();
		return true;
	}

	/**
	 * Notifies the {@link View} to display the given {@link Player} as active.
	 * 
	 * @param player
	 *            The active player
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	boolean updatePlayerStatus(Player player) {
		return this.view.updatePlayerStatus(player);
	}

	/**
	 * Notifies the view to update itself.
	 */
	void updateView() {
		this.view.redrawGraph();
	}

}
