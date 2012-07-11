package de.graphioli.controller;

import de.graphioli.gameexplorer.GameDefinition;
import de.graphioli.gameexplorer.GameExplorer;
import de.graphioli.model.Edge;
import de.graphioli.model.GameBoard;
import de.graphioli.model.GameCapsule;
import de.graphioli.model.GameResources;
import de.graphioli.model.Player;
import de.graphioli.model.Vertex;
import de.graphioli.model.VisualEdge;
import de.graphioli.model.VisualVertex;
import de.graphioli.utils.GraphioliLogger;
import de.graphioli.utils.InvalidJarException;
import de.graphioli.utils.JarParser;
import de.graphioli.utils.Localization;

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
public final class GameManager {

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
	 * GameDefinition for the current played game.
	 */
	private GameDefinition currentGameDefinition;

	/**
	 * {@code true} when the game is about to be finished. Volatile because
	 * accessed from the game call thread.
	 */
	private volatile boolean finishFlag;

	/**
	 * Creates a new instance of {@link GameManager}.
	 */
	private GameManager() {
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
	 * Closes the game with its {@link GameWindow} and returns the focus to the
	 * {@link GameExplorer}.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean closeGame() {
		LOG.info("Closing game.");
		this.viewManager.closeView();
		this.openGameExplorer();
		return true;
	}

	/**
	 * Exits the whole program.
	 */
	public void exit() {

		LOG.finer("GameManager.<em>exit()</em> called.");

		System.exit(0);

	}

	/**
	 * Notifies this {@link GameManager}, that the game is supposed to be
	 * finished.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise.
	 */
	public boolean finishGame() {
		this.finishFlag = true;
		return true;
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
	 * Returns the {@link GameBoard} associated with this {@link GameManager}.
	 * 
	 * @return GameBoard The GameBoard
	 */
	public GameBoard getGameBoard() {
		return this.gameBoard;
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
	 * Creates a {@link GameCapsule} from the savegame file and loads the
	 * information to start the game in the saved state.
	 * 
	 * @param savegame
	 *            The savegame file
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise TODO: Implement
	 */
	public boolean loadGame(File savegame) {

		LOG.info("Loading savegame: " + savegame.getPath());

		// Unserializing capsule
		GameCapsule capsule;
		try {
			FileInputStream fis = new FileInputStream(savegame);
			ObjectInputStream in = new ObjectInputStream(fis);
			capsule = (GameCapsule) in.readObject();
			in.close();
			LOG.info("Loaded GameCapsule from File: " + savegame.getName());

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

		if (!capsule.getGameDefinition().equals(this.currentGameDefinition)) {
			this.viewManager.displayPopUp(Localization.getLanguageString("savegeme_not_compatible"));
			return true;
		}

		// Recreating game data
		this.gameBoard = capsule.getBoard();
		this.playerManager = new PlayerManager(capsule.getPlayers(), this);
		this.playerManager.setActivePlayer(capsule.getActivePlayer());

		for (Vertex vtex : capsule.getBoard().getGraph().getVertices()) {
			((VisualVertex) vtex).reload();
		}

		for (Edge egd : capsule.getBoard().getGraph().getEdges()) {
			((VisualEdge) egd).reload();
		}

		// Calling game
		try {
			this.game.callOnGameLoad(capsule.getHashMap());
			this.game.callOnGameStart();
		} catch (TimeoutException e) {
			this.viewManager.displayPopUp(Localization.getLanguageString("timeout_err"));
			this.closeGame();
		}

		this.viewManager.updatePlayerStatus(this.playerManager.getActivePlayer());
		this.viewManager.displayErrorMessage(Localization.getLanguageString("mess_load"));
		this.viewManager.updateView();

		return true;
	}

	/**
	 * Logs an event in a {@link Game}.
	 * 
	 * @param logMessage
	 *            The event's message to log
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean logGameAction(String logMessage) {
		String message;
		if (logMessage.isEmpty()) {
			message = "<em>Empty message</em>";
		} else {
			message = logMessage;
		}
		LOG.info("Current game '" + this.currentGameDefinition.getName() + "' states: " + message);
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
	 * Restarts the game and resets the {@link GameBoard} and {@link Player}s.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean restartGame() {
		this.getGameBoard().flush();
		this.playerManager.initializePlayers();
		this.runGame();
		this.viewManager.updateView();
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
				this.playerManager.getActivePlayer(), this.currentGameDefinition);

		// Calling game
		boolean success = false;
		try {
			success = this.game.callOnGameSave(capsule.getHashMap());
		} catch (TimeoutException e) {
			this.viewManager.displayPopUp(Localization.getLanguageString("timeout_err"));
			this.closeGame();
		}

		if (!success) {
			this.viewManager.displayPopUp(Localization.getLanguageString("saving_unsupported"));
			return false;
		}

		// Serializing GameCapsule
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
		// Class<?> classToLoad = JarParser.getClass(this.gamePackagePath,
		// gameDefinition.getClassName());
		// TODO: Remove this method for production
		
		Class<?> classToLoad;
		try {
			classToLoad = JarParser.getClass("game.", gameDefinition.getClassName());
		} catch (InvalidJarException ije) {
			LOG.severe("Jar of \"" + gameDefinition.getClassName() + "\" corrupted : " + ije.getMessage());
			this.viewManager.displayPopUp(Localization.getLanguageString("jar_err"));
			return false;
		}

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

		this.game.registerController(this, new GameResources(gameDefinition.getClassName()));

		if (!runGame()) {
			this.viewManager.displayPopUp(Localization.getLanguageString("init_err"));
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

		if (gameDefinition.getMenu() != null) {
			this.viewManager.addCustomMenuItems(gameDefinition.getMenu());
		} else {
			LOG.fine("No custom menu items found");
		}

		// Create PlayerManager instance
		this.playerManager = new PlayerManager(players, this);

		this.playerManager.initializePlayers();

		this.currentGameDefinition = gameDefinition;
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
				this.viewManager.displayErrorMessage(Localization.getLanguageString("mess_running"));
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
			this.viewManager.displayPopUp(Localization.getLanguageString("timeout_err"));
			this.closeGame();
		}

		this.viewManager.updateView();
		return true;
	}

	/**
	 * Checks whether the finishFlag is set and if so closes the game with a
	 * winner Pop-up.
	 */
	void checkFinished() {
		if (this.finishFlag) {
			if (this.playerManager.getWinningPlayer() == null) {
				this.viewManager.displayPopUp(Localization.getLanguageString("draw_mess"));
			} else {
				this.viewManager.displayPopUp(this.playerManager.getWinningPlayer().getName()
						+ " "
						+ Localization.getLanguageString("win_mess"));
			}

			// Prompt if game should be restarted or closed
			if (this.viewManager.askForRestart()) {
				this.restartGame();
			} else {
				this.closeGame();
			}
		}
	}

	/**
	 * Starts the {@link GameExplorer}.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	boolean openGameExplorer() {

		// Create new instance of GameExplorer
		new GameExplorer(this);

		LOG.fine("<em>GameExplorer</em> call finished.");

		return true;

	}

}
