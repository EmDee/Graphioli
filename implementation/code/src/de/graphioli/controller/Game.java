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
	private GameManager gameManager;


	/**
	 * Associates this {@link Game} with a {@link GameManager}.
	 * 
	 * @param gameManager
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	public final boolean registerController(GameManager gameManager) {

		this.gameManager = gameManager;
		return true;

	}


	/**
	 * Returns the associated {@link GameManager}.
	 * 
	 * @return the associated {@link GameManager}
	 */
	protected final GameManager getGameManager() {
		return this.gameManager;
	}


	/**
	 * Called when a player clicks on a {@link VisualVertex}.
	 * 
	 * @param vertex The VisualVertex that was clicked
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	protected abstract boolean onVertexClick(VisualVertex vertex) {}


	/**
	 * Called when a player clicks on a {@link GridPoint} that has no
	 * {@link VisualVertex} on it.
	 * 
	 * @param gridPoint The empty GridPoint that was clicked
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	protected abstract boolean onEmptyGridPointClick(GridPoint gridPoint) {}


	/**
	 * Called after a {@link Game} has been instantiated.
	 * 
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	protected abstract boolean onGameInit() {}


	/**
	 * Called immediately before a {@link Game} gets (re)started.
	 * 
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	protected abstract boolean onGameStart() {}


	/**
	 * Called when a player releases a keyboard key.
	 * 
	 * @param keyCode The code of the key that was released
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	protected boolean onKeyRelease(int keyCode) {}


	/**
	 * Called when a player clicks on a custom {@link MenuItem}.
	 * 
	 * @param item The MenuItem that was clicked
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 * @todo Facultative
	 */
	//protected boolean onMenuItemClick(MenuItem item) {}

}