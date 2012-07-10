package de.graphioli.controller;

import de.graphioli.model.GameResources;
import de.graphioli.model.GridPoint;
import de.graphioli.model.MenuItem;
import de.graphioli.model.VisualVertex;

import java.util.HashMap;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

/**
 * This is the class that every game has to inherit from, when using the
 * Graphioli framework. It defines the callback functions that the game
 * developer needs in order to implement the game's logic.
 * 
 * @author Graphioli
 */
public abstract class Game {

	/**
	 * Logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(Game.class.getName());

	private static final long CALL_TIMEOUT = 2000;
	
	/**
	 * {@true} when there is a call to the game running.
	 */
	volatile boolean callFinished;
	
	/**
	 * The result of the last call to the game.
	 */
	volatile boolean callResult;

	/**
	 * The {@link GameManager} associated with this {@link Game}.
	 */
	private GameManager gameManager;
	
	/**
	 * The {@link GameResources} associated with this {@link Game}.
	 */
	private GameResources resources;

	
	/**
	 * Creates a new instance of Game.
	 */
	public Game() {
		LOG.info("Game Object instantiated.");
	}

	@SuppressWarnings("deprecation")
	private boolean executeAndWaitForCall(Thread callThread) {
		this.callFinished = false;
		this.callResult = false;
		callThread.start();

		try {
			callThread.join(CALL_TIMEOUT);
		} catch (InterruptedException e) {
			LOG.severe("Call interrupted: " + e.getMessage());
			e.printStackTrace();
			return true;
		}

		if (!this.callFinished) {
			callThread.stop(); // Deprecated, but in this case there is no
								// alternative.
			this.callFinished = true;
			LOG.warning("Call timed out.");
			return false;
		}

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
	 * Returns the associated {@link GameResources}.
	 * 
	 * @return the associated {@link GameResources}
	 */
	protected final GameResources getGameResources() {
		return this.resources;
	}

	/**
	 * Called when a player clicks on a {@link GridPoint} that has no
	 * {@link VisualVertex} on it.
	 * 
	 * @param gridPoint
	 *            The empty GridPoint that was clicked
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	protected abstract boolean onEmptyGridPointClick(GridPoint gridPoint);

	/**
	 * Called after a {@link Game} has been instantiated.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	protected abstract boolean onGameInit();

	/**
	 * Called after a savegame was loaded.
	 * 
	 * @param customValues
	 *            the stored custom values.
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	protected boolean onGameLoad(HashMap<Integer, Object> customValues) {
		return false;
	}

	/**
	 * Called before a savegame is stored.
	 * 
	 * @param customValues
	 *            the custom values to be stored.
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	protected boolean onGameSave(HashMap<Integer, Object> customValues) {
		return false;
	}

	/**
	 * Called immediately before a {@link Game} gets (re)started.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	protected abstract boolean onGameStart();

	/**
	 * Called when a player releases a keyboard key.
	 * 
	 * @param keyCode
	 *            The code of the key that was released
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	protected boolean onKeyRelease(int keyCode) {
		return false;
	}

	/**
	 * Called when a player clicks on a custom {@link MenuItem}.
	 * 
	 * @param item
	 *            The MenuItem that was clicked
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	protected boolean onMenuItemClick(MenuItem item) {
		return true;
	}

	/**
	 * Called when a player clicks on a {@link VisualVertex}.
	 * 
	 * @param vertex
	 *            The VisualVertex that was clicked
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	protected abstract boolean onVertexClick(VisualVertex vertex);

	/**
	 * This method executes a {@code onEmptyGridPointClick} call.
	 * 
	 * @param gridPoint
	 *            The {@link GridPoint} clicked on.
	 * @return the result of the {@link Game#onEmptyGridPointClick(GridPoint)}
	 *         method.
	 * @throws TimeoutException
	 *             when the call does not return in time.
	 */
	final boolean callOnEmptyGridPointClick(final GridPoint gridPoint) throws TimeoutException {

		Thread callThread = new Thread(new Runnable() {
			public void run() {
				Game.this.callResult = Game.this.onEmptyGridPointClick(gridPoint);
				Game.this.callFinished = true;

			}

		});

		LOG.finer("Executing onEmptyGridPointClick");
		if (!this.executeAndWaitForCall(callThread)) {
			throw new TimeoutException("onEmptyGridPointClick timed out.");
		}
		LOG.finer("onEmptyGridPointClick returned in time.");
		return this.callResult;
	}

	/**
	 * This method executes a {@code onGameInit} call.
	 * 
	 * NOTE: The game will be closed when this method returns {@code false}.
	 * 
	 * @throws TimeoutException
	 *             when the call does not return in time.
	 *             
	 * @return {@code true} when the initialization was successful.
	 */
	final boolean callOnGameInit() throws TimeoutException {

		Thread callThread = new Thread(new Runnable() {
			public void run() {
				Game.this.callResult = Game.this.onGameInit();
				Game.this.callFinished = true;
			}

		});

		LOG.finer("Executing onGameInit");
		if (!this.executeAndWaitForCall(callThread)) {
			throw new TimeoutException("onGameInit timed out.");
		}
		LOG.finer("onGameInit returned in time.");
		return this.callResult;
	}

	/**
	 * This method executes a {@code onGameLoad} call.
	 * 
	 * @param customValues
	 *            the custom values.
	 * @return the result of the {@link Game#onKeyRelease(int)} method.
	 * @throws TimeoutException
	 *             when the call does not return in time.
	 */
	final boolean callOnGameLoad(final HashMap<Integer, Object> customValues) throws TimeoutException {

		Thread callThread = new Thread(new Runnable() {
			public void run() {
				Game.this.callResult = Game.this.onGameLoad(customValues);
				Game.this.callFinished = true;
			}

		});

		LOG.finer("Executing onGameLoad");
		if (!this.executeAndWaitForCall(callThread)) {
			throw new TimeoutException("onGameLoad timed out.");
		}
		LOG.finer("onGameLoad returned in time.");
		return this.callResult;
	}

	/**
	 * This method executes a {@code onGameLoad} call.
	 * 
	 * @param customValues
	 *            the custom values.
	 * @return the result of the {@link Game#onKeyRelease(int)} method.
	 * @throws TimeoutException
	 *             when the call does not return in time.
	 */
	final boolean callOnGameSave(final HashMap<Integer, Object> customValues) throws TimeoutException {

		Thread callThread = new Thread(new Runnable() {
			public void run() {
				Game.this.callResult = Game.this.onGameSave(customValues);
				Game.this.callFinished = true;
			}

		});

		LOG.finer("Executing onGameSave");
		if (!this.executeAndWaitForCall(callThread)) {
			throw new TimeoutException("onGameSave timed out.");
		}
		LOG.finer("onGameSave returned in time.");
		return this.callResult;
	}

	/**
	 * This method executes a {@code onGameStart} call.
	 * 
	 * @return the result of the {@link Game#onGameStart()} method.
	 * @throws TimeoutException
	 *             when the call does not return in time.
	 */
	final boolean callOnGameStart() throws TimeoutException {

		Thread callThread = new Thread(new Runnable() {
			public void run() {
				Game.this.callResult = Game.this.onGameStart();
				Game.this.callFinished = true;
			}

		});

		LOG.finer("Executing onGameStart");
		if (!this.executeAndWaitForCall(callThread)) {
			throw new TimeoutException("onGameStart timed out.");
		}
		LOG.finer("onGameStart returned in time.");
		return this.callResult;
	}

	/**
	 * This method executes a {@code onKeyRelease} call.
	 * 
	 * @param keycode
	 *            The code of the key that was released.
	 * @return the result of the {@link Game#onKeyRelease(int)} method.
	 * @throws TimeoutException
	 *             when the call does not return in time.
	 */
	final boolean callOnKeyRelease(final int keycode) throws TimeoutException {

		Thread callThread = new Thread(new Runnable() {
			public void run() {
				Game.this.callResult = Game.this.onKeyRelease(keycode);
				Game.this.callFinished = true;
			}

		});

		LOG.finer("Executing onKeyRelease");
		if (!this.executeAndWaitForCall(callThread)) {
			throw new TimeoutException("onKeyRelease timed out.");
		}
		LOG.finer("onKeyRelease returned in time.");
		return this.callResult;
	}

	/**
	 * This method executes a {@code onMenuItemClick} call.
	 * 
	 * @param menuItem
	 *            The MenuItem that was clicked
	 * @return the result of the {@link Game#onMenuItemClick(MenuItem)} method.
	 * @throws TimeoutException
	 *             when the call does not return in time.
	 */
	final boolean callOnMenuItemClick(final MenuItem menuItem) throws TimeoutException {

		Thread callThread = new Thread(new Runnable() {
			public void run() {
				Game.this.callResult = Game.this.onMenuItemClick(menuItem);
				Game.this.callFinished = true;
			}

		});

		LOG.finer("Executing onMenuItemClick");
		if (!this.executeAndWaitForCall(callThread)) {
			throw new TimeoutException("onMenuItemClick timed out.");
		}
		LOG.finer("onMenuItemClick returned in time.");
		return this.callResult;
	}

	/**
	 * This method executes a {@code onVertexClick} call.
	 * 
	 * @param vertex
	 *            the {@code VisualVertex} clicked on.
	 * @return the result of the {@link Game#onVertexClick(VisualVertex)}
	 *         method.
	 * @throws TimeoutException
	 *             when the call does not return in time.
	 */
	final boolean callOnVertexClick(final VisualVertex vertex) throws TimeoutException {

		Thread callThread = new Thread(new Runnable() {
			public void run() {
				Game.this.callResult = Game.this.onVertexClick(vertex);
				Game.this.callFinished = true;
			}

		});

		LOG.finer("Executing onVertexClick");
		if (!this.executeAndWaitForCall(callThread)) {
			throw new TimeoutException("onVertexClick timed out.");
		}
		LOG.finer("onVertexClick returned in time.");
		return this.callResult;
	}

	/**
	 * Associates this {@link Game} with a {@link GameManager} and its
	 * {@link GameResources} .
	 * 
	 * @param gameManager
	 *            The controlling <code>GameManager</code>
	 * @param resources
	 *            The resources of this game.
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	final boolean registerController(GameManager gameManager, GameResources resources) {
		this.gameManager = gameManager;
		this.resources = resources;
		return true;

	}

}