package de.graphioli.controller;

import de.graphioli.gameexplorer.GameDefinition;
import de.graphioli.gameexplorer.GameExplorer;
import de.graphioli.model.GameBoard;
import de.graphioli.model.GameCapsule;
import de.graphioli.model.Player;
import de.graphioli.utils.GraphioliLogger;
import de.graphioli.utils.JarParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is the framework's central class, connecting the actual game with the
 * model and the view. Any communication between model, view and controller will
 * be managed by the GameManager.
 * 
 * @author Graphioli
 */
public class GameManager {

	/**
	 * Logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(GameManager.class.getName());

	/**
	 * The {@link Game} associated with this {@link GameManager}.
	 */
	private Game game;

	/**
	 * The {@link ViewManager} associated with this {@link GameManager}.
	 */
	private ViewManager viewManager;

	/**
	 * The {@link GameBoard} associated with this {@link GameManager}.
	 */
	private GameBoard gameBoard;

	/**
	 * The {@link PlayerManager} associated with this {@link GameManager}.
	 */
	private PlayerManager playerManager;

	/**
	 * Path to the package that holds all implemented {@link Game} sub-classes.
	 */
	private String gamePackagePath = "game.";

	/**
	 * GameDefinition for the current played game.
	 */
	private GameDefinition currentGameDefinition;
	
	/**
	 * {@code true} when the game is about to be finished.
	 * Volatile because accessed from the game call thread.
	 */
	private volatile boolean finishFlag;

	/**
	 * Creates a new instance of {@link GameManager}.
	 */
	public GameManager() {
		LOG.info("GameManager instantiated.");
	}

	/**
	 * Initializes the GameManager to start the whole application.
	 * 
	 * @param args
	 *            Provided command-line arguments
	 */
	public static void main(String[] args) {

		// Start Logging
		if (!initLogger()) {
			System.out.print("STOP: Unable to create log file.");
			return;
		}

		// Create controller
		final GameManager gameManager = new GameManager();

		// Open GameExplorer to select a game
		// This will cause GameExplorer to call startGame()
		gameManager.openGameExplorer();

		LOG.fine("<em>main</em> method finished");

	}

	/**
	 * Starts the {@link GameExplorer}.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	private boolean openGameExplorer() {

		// Create new instance of GameExplorer
		new GameExplorer(this);

		LOG.fine("<em>GameExplorer</em> call finished.");

		return true;

	}

	/**
	 * Starts the game specified by the {@link GameDefinition}.
	 * 
	 * @param gameDefinition
	 *            The GameDefinition of the game to start
	 * @param players
	 *            The list of players for this game
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean startGame(GameDefinition gameDefinition, ArrayList<Player> players) {

		LOG.info("Try starting game '" + gameDefinition.getClassName() + "'.");

		this.initializeFramework(gameDefinition, players);

		// get game class from Jar
		Class<?> classToLoad = JarParser.getClass(this.gamePackagePath, gameDefinition.getClassName());

		// Instantiate game
		try {
			this.game = (Game) classToLoad.newInstance();
		} catch (InstantiationException e) {
			LOG.severe("InstantiationException: " + e.getMessage());
			return false;
		} catch (IllegalAccessException e) {
			LOG.severe("IllegalAccessException: " + e.getMessage());
			return false;
		}

		this.game.registerController(this);

		if (!runGame()) {
			this.viewManager.displayPopUp("Game initialization failed. Closing.");
			this.closeGame();
		}

		return true;
	}

	/**
	 * Creates the components of the framework.
	 * 
	 * @param gameDefinition
	 *            The GameDefinition of the game to start
	 * @param players
	 *            The list of players for this game
	 */
	private void initializeFramework(GameDefinition gameDefinition, ArrayList<Player> players) {
		// Create GameBoard
		this.gameBoard = new GameBoard(gameDefinition.isDirectedGraph(), gameDefinition.getHorizontalGridPointCount(),
				gameDefinition.getVerticalGridPointCount());

		// Create ViewManager instance
		this.viewManager = new ViewManager(this);

		// Create PlayerManager instance
		this.playerManager = new PlayerManager(players, this);

		this.playerManager.initializePlayers();

		this.currentGameDefinition = gameDefinition;

		// Facultative: Create MenuItems here
	}

	/**
	 * Does the first calls to the game.
	 * 
	 * @return {@code true} when calls succeeded.
	 */
	private boolean runGame() {
		this.finishFlag = false;
		LOG.finer("Calling <em>onGameInit()</em>.");

		try {
			if (this.game.callOnGameInit()) {
				LOG.fine("<em>onGameInit()</em> returned <em>true</em>.");
				this.viewManager.displayErrorMessage("Running...");
				LOG.finer("Calling <em>onGameStart()</em>.");

				if (this.game.callOnGameStart()) {
					LOG.info("<em>onGameStart()</em> returned <em>true</em>. Game started.");
				} else {
					LOG.warning("<em>onGameStart()</em> returned <em>false</em>.");
					return false;
				}

			} else {
				LOG.warning("<em>onGameInit()</em> returned <em>false</em>.");
				return false;
			}
		} catch (TimeoutException toe) {
			this.viewManager.displayPopUp("Game timed out. Closing.");
			this.closeGame();
		}
		return true;
	}

	/**
	 * Creates a {@link GameCapsule} from the savegame file and loads the
	 * information to start the game in the saved state.
	 * 
	 * @param savegame
	 *            The savegame file
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise TODO: Implement
	 */
	public boolean loadGame(File savegame) {
		try {
			FileInputStream fis = new FileInputStream(savegame);
			ObjectInputStream in = new ObjectInputStream(fis);
			GameCapsule capsule = (GameCapsule) in.readObject();
			in.close();
			// this.gameBoard = capsule.getBoard();
			// this.playerManager = new PlayerManager(capsule.getPlayers(),
			// this);
			// this.playerManager.setActivePlayer(capsule.getActivePlayer());

			// TODO what to do with the capsule (onGameStart)
			LOG.info("Loaded GameCapsule from File: " + savegame.getName());
			LOG.info("Just for testing: Name of the active player: " + capsule.getActivePlayer().getName());
		} catch (FileNotFoundException e) {
			LOG.severe("FileNotFoundException: " + e.getMessage());
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			LOG.severe("IOException: " + e.getMessage());
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			LOG.severe("ClassNotFoundException: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Creates a {@link GameCapsule} and serializes it into a savegame file to
	 * save the current state of the game.
	 * 
	 * @param savegame
	 *            The savegame file
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise TODO Implement
	 */
	public boolean saveGame(File savegame) {
		GameCapsule capsule = new GameCapsule(this.gameBoard, this.playerManager.getPlayers(),
				this.playerManager.getActivePlayer());
		try {
			FileOutputStream fos = new FileOutputStream(savegame);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(capsule);
			out.flush();
			out.close();
			LOG.info("Saved game to file: " + savegame.getName());
		} catch (FileNotFoundException e) {
			LOG.severe("FileNotFoundException: " + e.getMessage());
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			LOG.severe("IOException: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Notifies this {@link GameManager}, that the game is supposed to be finished.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise.
	 */
	public boolean finishGame() {
		this.finishFlag = true;
		return true;
	}
	
	
	/**
	 * Checks whether the finishFlag is set and if so closes the game with a winner Pop-up.
	 */
	public void checkFinished() {
		if (this.finishFlag) {
			if (this.playerManager.getWinningPlayer() == null) {
				this.viewManager.displayPopUp("Draw.");
			} else {
				this.viewManager.displayPopUp(this.playerManager.getWinningPlayer().getName() + " wins.");
			}
			
			//TODO: Restart prompt
			this.closeGame();
		}
	}

	/**
	 * Restarts the game and resets the {@link GameBoard} and {@link Player}s.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise TODO Implement
	 */
	public boolean restartGame() {
		return false;
	}

	/**
	 * Closes the game with its {@link GameWindow} and returns the focus to the
	 * {@link GameExplorer}.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise TODO Implement
	 */
	public boolean closeGame() {
		LOG.info("Closing game.");
		this.viewManager.closeView();
		this.openGameExplorer();
		return true;
	}

	/**
	 * Returns the {@link PlayerManager} associated with this
	 * {@link GameManager}.
	 * 
	 * @return PlayerManager The PlayerManager
	 */
	public PlayerManager getPlayerManager() {
		return this.playerManager;
	}

	/**
	 * Returns the {@link ViewManager} associated with this {@link GameManager}.
	 * 
	 * @return ViewManager The ViewManager
	 */
	public ViewManager getViewManager() {
		return this.viewManager;
	}

	/**
	 * Returns the {@link GameBoard} associated with this {@link GameManager}.
	 * 
	 * @return GameBoard The GameBoard
	 */
	public GameBoard getGameBoard() {
		return this.gameBoard;
	}

	/**
	 * Returns the {@link Game} associated with this {@link GameManager}.
	 * 
	 * @return The Game
	 */
	public Game getGame() {
		return this.game;
	}

	/**
	 * Starts the logging for the framework.
	 * 
	 * @return {@code false} when logging could not be started
	 */
	private static boolean initLogger() {

		try {
			GraphioliLogger.startLog(Level.FINEST);
		} catch (IOException e) {
			return false;
		}

		return true;

	}

	/**
	 * Opens the help file with the link in the given {@link GameDefinition}.
	 * 
	 * @param gameDefinition
	 *            given {@link GameDefinition}
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean openHelpFile(GameDefinition gameDefinition) {
		LOG.info("GameManager.<em>openHelpFile([...])</em> called.");

		// TODO: getDesktop().browse only works for Java 6
		// TODO: currently it doesn't load local help files; find correct path

		String url = gameDefinition.getHelpFile().toString();
		try {
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		} catch (IOException e) {
			LOG.severe("IOException: " + e.getMessage());
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * Opens the help file with the link of the current {@link GameDefinition}.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean openHelpFile() {
		LOG.info("GameManager.<em>openHelpFile()</em> within game called.");
		return this.openHelpFile(this.currentGameDefinition);
	}

	/**
	 * Exits the whole program.
	 */
	public void exit() {

		LOG.finer("GameManager.<em>exit()</em> called.");

		System.exit(0);

	}

}
