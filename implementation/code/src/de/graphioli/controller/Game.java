package de.graphioli.controller;

/**
 * This is the class that every game has to inherit from, when using the Graphioli framework. It defines 
 * the callback functions that the game developer needs in order to implement the game's logic.
 * 
 * @author Graphioli
 */
public abstract class Game {

	/**
	 * The {@link GameManager} associated with this {@link Game}.
	 */
	GameManager gameManager;


	/**
	 * Associates this {@link Game} with a {@link GameManager}.
	 * 
	 * @param gameManager
	 * @return
	 */
	public final boolean registerController(GameManager gameManager) {

		this.gameManager = gameManager;
		return true;

	}


	/**
	 * Returns the associated {@link GameManager}.
	 * 
	 * @return
	 */
	protected final GameManager getGameManager() {
		return this.gameManager;
	}


	/**
	 * Called when a player clicks on a {@link VisualVertex}.
	 * 
	 * @param vertex
	 * @return
	 */
	protected abstract boolean onVertexClick(VisualVertex vertex) {}


	/**
	 * Called when a player clicks on a {@link GridPoint} that has no
	 * {@link VisualVertex} on it.
	 * 
	 * @param gridPoint
	 * @return
	 */
	protected abstract boolean onEmptyGridPointClick(GridPoint gridPoint) {}


	/**
	 * Called after a {@link Game} has been instantiated.
	 * 
	 * @return
	 */
	protected abstract boolean onGameInit() {}


	/**
	 * Called immediately before a {@link Game} gets (re)started.
	 * 
	 * @return
	 */
	protected abstract boolean onGameStart() {}


	/**
	 * Called when a player releases a keyboard key.
	 * 
	 * @param keyCode
	 * @return
	 */
	protected boolean onKeyRelease(int keyCode) {}


	/**
	 * Called when a player clicks on a custom {@link MenuItem}.
	 * 
	 * @param item
	 * @return
	 * @todo Facultative
	 */
	protected boolean onMenuItemClick(MenuItem item) {}

}