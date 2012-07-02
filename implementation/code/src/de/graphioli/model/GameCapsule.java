package de.graphioli.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This serializable class represents a GameCapsule that holds all the important
 * data about the game state. A savegame will be saved in such a
 * {@code GameCapsule}, which later on can be loaded to resume the game.
 * 
 * @author Graphioli
 */
public class GameCapsule implements Serializable {

	/**
	 * The game board in the state to be saved
	 */
	private GameBoard board;

	/**
	 * The players of the game to be saved
	 */
	private ArrayList<Player> players;

	/**
	 * The currently active player
	 */
	private Player activePlayer;

	/**
	 * Creates a new GameCapsule with the given parameters.
	 * 
	 * @param board
	 *            the game board in the state to be saved
	 * @param players
	 *            the players of the game to be saved
	 * @param activePlayer
	 *            the currently active player
	 */
	public GameCapsule(GameBoard board, ArrayList<Player> players, Player activePlayer) {
		this.board = board;
		this.players = players;
		this.activePlayer = activePlayer;
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
	 * Returns the saved {@link Player}s.
	 * 
	 * @return an ArrayList of the saved {@link Player}s
	 */
	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	/**
	 * Returns the saved active {@link Player}.
	 * 
	 * @return the saved acrive {@link Player}
	 */
	public Player getActivePlayer() {
		return this.activePlayer;
	}
}
