package de.graphioli.model;

import de.graphioli.gameexplorer.GameDefinition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This serializable class represents a GameCapsule that holds all the important
 * data about the game state. A savegame will be saved in such a
 * {@code GameCapsule}, which later on can be loaded to resume the game.
 * 
 * @author Graphioli
 */
public class GameCapsule implements Serializable {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -563123642396872207L;

	/**
	 * The game board in the state to be saved.
	 */
	private final GameBoard board;

	/**
	 * The players of the game to be saved.
	 */
	private final ArrayList<Player> players;

	/**
	 * The currently active player.
	 */
	private final Player activePlayer;

	/**
	 * The game definition of the game to be saved.
	 */
	private final GameDefinition gameDef;

	/**
	 * A hash map for custom values.
	 */
	private final HashMap<Integer, Object> hashMap;

	/**
	 * Creates a new GameCapsule with the given parameters.
	 * 
	 * @param board
	 *            the game board in the state to be saved
	 * @param players
	 *            the players of the game to be saved
	 * @param activePlayer
	 *            the currently active player
	 * @param definition
	 *            the game definition of the currently active game.
	 */
	public GameCapsule(GameBoard board, ArrayList<Player> players, Player activePlayer, GameDefinition definition) {
		this.board = board;
		this.players = players;
		this.activePlayer = activePlayer;
		this.gameDef = definition;
		this.hashMap = new HashMap<Integer, Object>();
	}

	/**
	 * Returns the saved active {@link Player}.
	 * 
	 * @return the saved active {@link Player}
	 */
	public Player getActivePlayer() {
		return this.activePlayer;
	}

	/**
	 * Returns the saved {@link GameBoard}.
	 * 
	 * @return the saved {@link GameBoard}
	 */
	public GameBoard getBoard() {
		return this.board;
	}

	/**
	 * Returns the saved active {@link GameDefinition}.
	 * 
	 * @return the saved active {@link GameDefinition}
	 */
	public GameDefinition getGameDefinition() {
		return this.gameDef;
	}

	/**
	 * Returns the saved active {@link GameDefinition}.
	 * 
	 * @return the saved active {@link GameDefinition}
	 */
	public HashMap<Integer, Object> getHashMap() {
		return this.hashMap;
	}

	/**
	 * Returns the saved {@link Player}s.
	 * 
	 * @return an ArrayList of the saved {@link Player}s
	 */
	public ArrayList<Player> getPlayers() {
		return this.players;
	}
}
