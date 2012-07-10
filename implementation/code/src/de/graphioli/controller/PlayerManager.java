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
	 * The player that is displayed as winner when the game is finished. (
	 * {@code null) for draw)

	 */
	private Player winner;

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
	 * Returns the list of {@link Player}s managed by this instance.
	 * 
	 * @return the list of players managed by this instance
	 */
	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	/**
	 * Returns the player that is displayed as winner when the game is finished.
	 * 
	 * @return the player that is displayed as winner when the game is finished.
	 */
	public Player getWinningPlayer() {
		return this.winner;
	}

	/**
	 * Initializes registered {@link Player}s.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean initializePlayers() {
		// Set first player to active
		this.activePlayer = this.players.get(0);
		this.winner = null;
		this.gameManager.getViewManager().updatePlayerStatus(this.activePlayer);
		return true;
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

	/**
	 * Sets the active {@link Player} to the given {@link Player} if is in the
	 * list of players of this game.
	 * 
	 * @param player
	 *            the new active player
	 * @return {@code true} if the given Player is in the players list
	 */
	public boolean setActivePlayer(Player player) {
		if (this.players.contains(player)) {
			this.activePlayer = player;
			return true;
		}
		return false;
	}

	/**
	 * Defines the player currently active as the player currently winning.
	 */
	public void setActivePlayerAsWinning() {
		this.winner = this.activePlayer;
	}

	/**
	 * Sets the player that is displayed as winner when the game is finished.
	 * 
	 * @param winningPlayer
	 *            the winning player to set.
	 */
	public void setWinningPlayer(Player winningPlayer) {
		this.winner = winningPlayer;
	}

}
