package de.graphioli.controller;

import de.graphioli.model.GridPoint;
import de.graphioli.model.Player;
import de.graphioli.view.GameWindow;
import de.graphioli.view.View;

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
		this.view = new GameWindow(this);
	}

	/**
	 * Notifies the {@link View} to display the given {@link Player} as active.
	 * 
	 * @param player
	 *            The active player
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean updatePlayerStatus(Player player) {
		return this.view.updatePlayerStatus(player);
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
	 * Notifies the {@link View} to display the given message in a pop-up.
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
	 * Notifies the {@link View} to add the given {@link MenuItem} to the menu.
	 * 
	 * @param item
	 *            The MenuItem to add
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	// TODO: Facultative
	/*
	 * public boolean addGameMenuItem(MenuItem item) { return
	 * this.view.addCustomMenuItem(item); }
	 */

	/**
	 * Callback function used by the {@link View} to notify about a click on a
	 * previously added {@link MenuItem}.
	 * 
	 * @param item
	 *            The MenuItem that was clicked
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	// TODO: Facultative
	// public boolean onMenuItemClick(MenuItem item) {}

	/**
	 * Returns the associated {@link GameManager}.
	 * 
	 * @return the associated {@link GameManager}
	 */
	public GameManager getGameManager() {
		return this.gameManager;
	}

	/**
	 * Notifies the {@link View} to change the size of the {@link VisualVertex}
	 * es to the given value.
	 * 
	 * @param size
	 *            The new size of the VisualVertex
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean setVisualVertexSize(int size) {
		return this.view.setVisualVertexSize(size);
	}
	

	/**
	 * Notifies the view to update itself. 
	 */
	public void updateView() {
		this.view.redrawGraph();
	}


	/**
	 * Closes the View and all its components.
	 * 
	 * @return {@code true} if the disposal was successful
	 */
	public boolean closeView() {
		this.view.closeView();
		return true;
	}
}
