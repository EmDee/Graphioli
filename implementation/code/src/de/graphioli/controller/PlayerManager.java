package de.graphioli.controller;

/**
 * This class is responsible for keeping information about a game's {@link Player}s.
 * 
 * @author Graphioli
 */
public class PlayerManager {

	/**
	 * Set of {@link Player}s.
	 */
	private Iterable<Player> players;

	/**
	 * Currently active {@link Player}.
	 */
	private Player activePlayer;


	/**
	 * Constructs a PlayerManager with the given set of {@link Player}s.
	 * 
	 * @param players The set of players
	 */
	public PlayerManager(Iterable<Player> players) {}


	/**
	 * Initializes registered {@link Player}s.
	 * 
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	public boolean initializePlayers() {}


	/**
	 * Returns the list of {@link Player}s managed by this instance.
	 * 
	 * @return the list of players managed by this instance
	 */
	public Iterable<Player> getPlayers() {
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
	public Player nextPlayer() {}

}