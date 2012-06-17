/**
 * This class is responsible for keeping information about a game’s {@link Player}s.
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
	 * @param players
	 */
	public PlayerManager(Iterable<Player> players) {}


	/**
	 * Initializes registered {@link Player}s.
	 * 
	 * @return boolean
	 */
	public boolean initializePlayers() {}


	/**
	 * Returns the list of {@link Player}s managed by this instance.
	 * 
	 * @return Iterable<Player>
	 */
	public Iterable<Player> getPlayers() {}


	/**
	 * Returns the {@link Player}, who is currently active.
	 * 
	 * @return Player
	 */
	public Player getActivePlayer() {}


	/**
	 * Sets the next {@link Player} in the list to active.
	 * 
	 * @return Player
	 */
	public Player nextPlayer() {}

}