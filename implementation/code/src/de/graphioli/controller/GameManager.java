package de.graphioli.controller;
import java.io.File;
import java.util.ArrayList;
import de.graphioli.gameexplorer.GameDefinition;
import de.graphioli.gameexplorer.GameExplorer;
import de.graphioli.model.GameBoard;

/**
 * This is the framewor's central class, connecting the actual game with the model and the view.
 * Any communication between model, view and controller will be managed by the GameManager.
 * 
 * @author Graphioli
 */
public class GameManager {

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
	 * Initializes the GameManager to start the whole application.
	 * 
	 * @param args Provided command-line arguments
	 */
	public static void main(String[] args) {

		// Create controller
		GameManager gameManager = new GameManager();

		// Open GameExplorer to select a game
		// This will cause GameExplorer to call startGame()
		gameManager.openGameExplorer();

	}


	/**
	 * Creates a new instance of {@link GameManager}.
	 */
	public GameManager() {}


	/**
	 * Starts the {@link GameExplorer}.
	 * 
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	private boolean openGameExplorer() {

		// Create new instance of GameExplorer
		new GameExplorer(this);
		return true;

	}


	/**
	 * Kills the currently running game.
	 * 
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 * @todo Implement
	 */
	private boolean killGame() {
		return false;
	}


	/**
	 * Starts the game specified by the {@link GameDefinition}.
	 * 
	 * @param gameDefinition The GameDefinition of the game to start
	 * @param players The list of players for this game
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	public boolean startGame(GameDefinition gameDefinition, ArrayList<Player> players) {

		// Create PlayerManager instance
		this.playerManager = new PlayerManager(players);

		// Create GameBoard
		this.gameBoard = new GameBoard(gameDefinition.isDirectedGraph(),
				gameDefinition.getHorizontalGridPointCount(),
				gameDefinition.getVerticalGridPointCount());

		// Create ViewManager instance
		this.viewManager = new ViewManager(this);

		// Facultative: Create MenuItems here

		// Instantiate game
		this.game = (Game) Class.forName(gameDefinition.getFullyQualifiedClassName()).newInstance();
		game.registerController(this);
		game.onGameInit();

	}


	/**
	 * Creates a {@link GameCapsule} from the savegame file and loads the
	 * information to start the game in the saved state.
	 * 
	 * @param savegame The savegame file
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 * @todo Implement
	 */
	public boolean loadGame(File savegame) {
		return false;
	}


	/**
	 * Creates a {@link GameCapsule} and serializes it into a savegame file
	 * to save the current state of the game.
	 * 
	 * @param savegame The savegame file
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 * @todo Implement
	 */
	public boolean saveGame(File savegame) {
		return false;
	}


	/**
	 * Finishes the game and displays a default pop-up for single player games.
	 * 
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 * @todo Implement
	 */
	public boolean finishGame() {
		this.killGame();
		return false;
	}


	/**
	 * Finishes the game and displays the winning {@link Player} in a pop-up.
	 * 
	 * @param winner The winning player
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 * @todo Implement
	 */
	public boolean finishGame(Player winner) {
		return false;
	}


	/**
	 * Restarts the game and resets the {@link GameBoard} and {@link Player}s.
	 * 
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 * @todo Implement
	 */
	public boolean restartGame() {
		return false;
	}


	/**
	 * Closes the game with its {@link GameWindow} and returns the focus to
	 * the {@link GameExplorer}.
	 * 
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 * @todo Implement
	 */
	public boolean closeGame() {
		return false;
	}


	/**
	 * Returns the {@link PlayerManager} associated with this {@link GameManager}.
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

}