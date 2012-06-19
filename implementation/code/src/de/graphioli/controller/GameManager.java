package de.graphioli.controller;
import java.io.File;
import de.graphioli.gameexplorer.GameDefinition;

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
	public static void main(String[] args) {}


	/**
	 * Starts the {@link GameExplorer}.
	 * 
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	private boolean openGameExplorer() {}


	/**
	 * Kills the currently running game.
	 * 
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	private boolean killGame() {}


	/**
	 * Starts the game specified by the {@link GameDefinition}.
	 * 
	 * @param gameDefinition The GameDefinition of the game to start
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	public boolean startGame(GameDefinition gameDefinition) {}


	/**
	 * Creates a {@link GameCapsule} from the savegame file and loads the
	 * information to start the game in the saved state.
	 * 
	 * @param savegame The savegame file
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	public boolean loadGame(File savegame) {}


	/**
	 * Creates a {@link GameCapsule} and serializes it into a savegame file
	 * to save the current state of the game.
	 * 
	 * @param savegame The savegame file
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	public boolean saveGame(File savegame) {}


	/**
	 * Finishes the game and displays a default pop-up for single player games.
	 * 
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	public boolean finishGame() {}


	/**
	 * Finishes the game and displays the winning {@link Player} in a pop-up.
	 * 
	 * @param winner The winning player
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	public boolean finishGame(Player winner) {}


	/**
	 * Restarts the game and resets the {@link GameBoard} and {@link Player}s.
	 * 
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	public boolean restartGame() {}


	/**
	 * Closes the game with its {@link GameWindow} and returns the focus to
	 * the {@link GameExplorer}.
	 * 
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	public boolean closeGame() {}


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