package de.graphioli.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.graphioli.gameexplorer.GameDefinition;
import de.graphioli.gameexplorer.GameExplorer;
import de.graphioli.model.GameBoard;
import de.graphioli.model.Player;
import de.graphioli.utils.GraphioliLogger;

/**
 * This is the framework's central class, connecting the actual game with the
 * model and the view. Any communication between model, view and controller will
 * be managed by the GameManager.
 * 
 * @author Graphioli
 */
public class GameManager {

	/**
	 * Logging instance
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
		GameManager gameManager = new GameManager();

		// Open GameExplorer to select a game
		// This will cause GameExplorer to call startGame()
		gameManager.openGameExplorer();

		LOG.fine("<em>main</em> method finished");

	}

	/**
	 * Creates a new instance of {@link GameManager}.
	 */
	public GameManager() {
		LOG.info("GameManager instantiated.");
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
	 * Kills the currently running game.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise TODO: Implement
	 */
	private boolean killGame() {
		return false;
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

		LOG.info("<em>startGame()</em> called.");

		// Create PlayerManager instance
		this.playerManager = new PlayerManager(players);

		// Create GameBoard
		this.gameBoard = new GameBoard(gameDefinition.isDirectedGraph(),
				gameDefinition.getHorizontalGridPointCount(), gameDefinition.getVerticalGridPointCount());

		// Create ViewManager instance
		this.viewManager = new ViewManager(this);

		this.currentGameDefinition = gameDefinition;

		// Facultative: Create MenuItems here

		LOG.fine("Try starting game '" + gameDefinition.getClassName() + "'.");

		// Instantiate game
		try {

			String fullyQualifiedClassName = this.gamePackagePath + gameDefinition.getClassName();
			String canonicalPath;
			try {
				canonicalPath = new File(".").getCanonicalPath();
			} catch (IOException e1) {
				canonicalPath = ".";
			}

			// Create jarBall file pointer to specific game class
			// TODO Get path (currently /src/games/) from environment (eg.
			// production and development)
			File jarBall = new File(canonicalPath + "/src/games/" + gameDefinition.getClassName() + "/");
			URL[] urls = new URL[1];

			// Format jarBall file pointer to proper URL
			try {
				urls[0] = jarBall.toURI().toURL();
			} catch (MalformedURLException e) {
				// Log exception
				LOG.severe("MalformedURLException: " + e.getMessage());
				return false;
			}

			URLClassLoader loader = new URLClassLoader(urls);
			Class<?> gameClass = loader.loadClass(fullyQualifiedClassName);

			this.game = (Game) gameClass.newInstance();

		} catch (InstantiationException e) {
			// Log exception
			LOG.severe("InstantiationException: " + e.getMessage());
			return false;
		} catch (IllegalAccessException e) {
			// Log exception
			LOG.severe("IllegalAccessException: " + e.getMessage());
			return false;
		} catch (ClassNotFoundException e) {
			// Log exception
			LOG.severe("ClassNotFoundException: " + e.getMessage());
			return false;
		}

		game.registerController(this);

		// Start Game

		LOG.finer("Calling <em>onGameInit()</em>.");

		if (game.onGameInit()) {
			LOG.fine("<em>onGameInit()</em> returned <em>true</em>.");

			LOG.finer("Calling <em>onGameStart()</em>.");

			if (game.onGameStart()) {
				LOG.info("<em>onGameStart()</em> returned <em>true</em>. Game started.");
			} else {
				LOG.warning("<em>onGameStart()</em> returned <em>false</em>.");
			}

		} else {
			LOG.warning("<em>onGameInit()</em> returned <em>false</em>.");
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
		return false;
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
		return false;
	}

	/**
	 * Finishes the game and displays a default pop-up for single player games.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise TODO Implement
	 */
	public boolean finishGame() {
		this.killGame();
		return false;
	}

	/**
	 * Finishes the game and displays the winning {@link Player} in a pop-up.
	 * 
	 * @param winner
	 *            The winning player
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise TODO Implement
	 */
	public boolean finishGame(Player winner) {
		return false;
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
		return this.openHelpFile(this.currentGameDefinition);
	}

}
