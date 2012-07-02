package de.graphioli.controller;

import de.graphioli.model.GridPoint;
import de.graphioli.model.VisualVertex;

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

	private static final long CALL_TIMEOUT = 500;

	/**
	 * The {@link GameManager} associated with this {@link Game}.
	 */
	private GameManager gameManager;

	volatile boolean callFinished;
	volatile boolean callResult;

	/**
	 * Creates a new instance of Game.
	 */
	public Game() {
		LOG.info("Game Object instantiated.");
	}

	/**
	 * Associates this {@link Game} with a {@link GameManager}.
	 * 
	 * @param gameManager
	 *            The controlling <code>GameManager</code>
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
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
				callResult = onVertexClick(vertex);
				callFinished = true;
			}

		});

		LOG.finer("Executing onVertexClick");
		if (!this.executeAndWaitForCall(callThread)) {
			throw new TimeoutException("onVertexClick timed out.");
		}
		LOG.finer("onVertexClick returned in time.");
		return callResult;
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
	 * @param GridPoint
	 *            The {@link GridPoint} clicked on.
	 * @return the result of the {@link Game#onEmptyGridPointClick(GridPoint)} method.
	 * @throws TimeoutException
	 *             when the call does not return in time.
	 */
	final boolean callOnEmptyGridPointClick(final GridPoint gridPoint) throws TimeoutException {

		Thread callThread = new Thread(new Runnable() {
			public void run() {
				callResult = onEmptyGridPointClick(gridPoint);
				callFinished = true;
			}

		});

		LOG.finer("Executing onEmptyGridPointClick");
		if (!this.executeAndWaitForCall(callThread)) {
			throw new TimeoutException("onEmptyGridPointClick timed out.");
		}
		LOG.finer("onEmptyGridPointClick returned in time.");
		return callResult;
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
	 * This method executes a {@code onGameInit} call.
	 * 
	 * @throws TimeoutException
	 *             when the call does not return in time.
	 */
	final boolean callOnGameInit() throws TimeoutException {

		Thread callThread = new Thread(new Runnable() {
			public void run() {
				callResult = onGameInit();
				callFinished = true;
			}

		});

		LOG.finer("Executing onGameInit");
		if (!this.executeAndWaitForCall(callThread)) {
			throw new TimeoutException("onGameInit timed out.");
		}
		LOG.finer("onGameInit returned in time.");
		return callResult;
	}
	
	/**
	 * Called after a {@link Game} has been instantiated.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	protected abstract boolean onGameInit();

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
				callResult = onGameStart();
				callFinished = true;
			}

		});

		LOG.finer("Executing onGameStart");
		if (!this.executeAndWaitForCall(callThread)) {
			throw new TimeoutException("onGameStart timed out.");
		}
		LOG.finer("onGameStart returned in time.");
		return callResult;
	}
	
	/**
	 * Called immediately before a {@link Game} gets (re)started.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	protected abstract boolean onGameStart();

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
				callResult = onKeyRelease(keycode);
				callFinished = true;
			}

		});

		LOG.finer("Executing onKeyRelease");
		if (!this.executeAndWaitForCall(callThread)) {
			throw new TimeoutException("onKeyRelease timed out.");
		}
		LOG.finer("onKeyRelease returned in time.");
		return callResult;
	}

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
	 * @todo Facultative
	 */
	// protected boolean onMenuItemClick(MenuItem item) {}

	@SuppressWarnings("deprecation")
	private boolean executeAndWaitForCall(Thread callThread) {
		callFinished = false;
		callResult = false;
		callThread.start();

		try {
			callThread.join(CALL_TIMEOUT);
		} catch (InterruptedException e) {
			LOG.severe("Call interrupted: " + e.getMessage());
			e.printStackTrace();
			return true;
		}

		if (!callFinished) {
			callThread.stop(); // Deprecated, but in this case there is no
								// alternative.
			callFinished = true;
			LOG.warning("Call timed out.");
			return false;
		}

		return true;
	}

}