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
	 * @param players
	 * @param activePlayer
	 */
	public GameCapsule(GameBoard board, ArrayList<Player> players, Player activePlayer) {
		this.board = board;
		this.players = players;
		this.activePlayer = activePlayer;
	}
	
	/**
	 * 
	 * @return
	 */
	public GameBoard getBoard() {
		return this.board;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Player> getPlayers() {
		return this.players;
	}
	
	/**
	 * 
	 * @return
	 */
	public Player getActivePlayer() {
		return this.activePlayer;
	}
}
