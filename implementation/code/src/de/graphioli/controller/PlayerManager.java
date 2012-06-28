package de.graphioli.controller;

import de.graphioli.model.Player;
import java.util.ArrayList;


/**
 * This class is responsible for keeping information about a game's
 * {@link Player}s.
 * 
 * @author Graphioli
 */
public class PlayerManager {

	/**
	 * The associated {@link GameManager}.
	 */
	private GameManager gameManager;

	/**
	 * Set of {@link Player}s.
	 */
	private ArrayList<Player> players;

	/**
	 * Currently active {@link Player}.
	 */
	private Player activePlayer;

	/**
	 * Constructs a PlayerManager with the given set of {@link Player}s.
	 * 
	 * @param players
	 *            The set of players
	 * @param gameManager
	 *            The controlling {@link GameManager}
	 */
	public PlayerManager(ArrayList<Player> players, GameManager gameManager) {

		this.players = players;
		this.gameManager = gameManager;
		// Set first player to active
		this.activePlayer = players.get(0);

	}

	/**
	 * Initializes registered {@link Player}s.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean initializePlayers() {
		this.gameManager.getViewManager().updatePlayerStatus(this.activePlayer);
		return true;
	}

	/**
	 * Returns the list of {@link Player}s managed by this instance.
	 * 
	 * @return the list of players managed by this instance
	 */
	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	/**
	 * Returns the {@link Player} who is currently active.
	 * 
	 * @return the player who is currently active
	 */
	public Player getActivePlayer() {
		return this.activePlayer;
	}

	/**
	 * Sets the next {@link Player} in the list to active.
	 * 
	 * @return the player that was set to active
	 */
	public Player nextPlayer() {

		int activeIndex = this.players.indexOf(this.activePlayer);

		// Set next active player (first one, if currently active player is last
		// entry in the list)
		if (activeIndex == this.players.size() - 1) {
			this.activePlayer = this.players.get(0);
		} else {
			this.activePlayer = this.players.get(activeIndex + 1);
		}

		this.gameManager.getViewManager().updatePlayerStatus(this.activePlayer);

		// Return the newly set active player
		return this.activePlayer;

	}

}
