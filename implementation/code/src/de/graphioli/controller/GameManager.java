package de.graphioli.controller;
import java.io.File;

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
	 * @param args
	 */
	public static void main(String[] args) {}


	/**
	 * Starts the {@link GameExplorer}.
	 * 
	 * @return boolean
	 */
	private boolean openGameExplorer() {}


	/**
	 * Kills the currently running game.
	 * 
	 * @return boolean
	 */
	private boolean killGame() {}


	/**
	 * Starts the game specified by the {@link GameDefinition}.
	 * 
	 * @param gameDefinition
	 * @return boolean
	 */
	public boolean startGame(GameDefinition gameDefinition) {}


	/**
	 * Creates a {@link GameCapsule} from the savegame file and loads the
	 * information to start the game in the saved state.
	 * 
	 * @param savegame
	 * @return boolean
	 */
	public boolean loadGame(File savegame) {}


	/**
	 * Creates a {@link GameCapsule} and serializes it into a savegame file
	 * to save the current state of the game.
	 * 
	 * @param savegame
	 * @return boolean
	 */
	public boolean saveGame(File savegame) {}


	/**
	 * Finishes the game and displays a default pop-up for single player games.
	 * 
	 * @return boolean
	 */
	public boolean finishGame() {}


	/**
	 * Finishes the game and displays the winning {@link Player} in a pop-up.
	 * 
	 * @param winner
	 * @return boolean
	 */
	public boolean finishGame(Player winner) {}


	/**
	 * Restarts the game and resets the {@link GameBoard} and {@link Player}s.
	 * 
	 * @return boolean
	 */
	public boolean restartGame() {}


	/**
	 * Closes the game with its {@link GameWindow} and returns the focus to
	 * the {@link GameExplorer}.
	 * 
	 * @return boolean
	 */
	public boolean closeGame() {}


	/**
	 * Returns the {@link PlayerManager} associated with this {@link GameManager}.
	 * 
	 * @return PlayerManager
	 */
	public PlayerManager getPlayerManager() {
		return this.playerManager;
	}


	/**
	 * Returns the {@link ViewManager} associated with this {@link GameManager}.
	 * 
	 * @return ViewManager
	 */
	public ViewManager getViewManager() {
		return this.viewManager;
	}


	/**
	 * Returns the {@link GameBoard} associated with this {@link GameManager}.
	 * 
	 * @return GameBoard
	 */
	public GameBoard getGameBoard() {
		return this.gameBoard;
	}

}