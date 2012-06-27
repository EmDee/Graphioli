package de.graphioli.gameexplorer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.graphioli.model.LocalPlayer;
import de.graphioli.model.Player;

/**
 * Represents a pop-up window that is used to select the the number of players and their names for a {@link Game}.
 * 
 * @author Graphioli
 */
public class PlayerPopUp extends JFrame implements ActionListener {

	/**
	 * Logging instance
	 */
	private static final Logger LOG = Logger.getLogger(PlayerPopUp.class.getName());

	/**
	 * UID for serializing this object.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The controlling {@link GEWindow}.
	 */
	private GEWindow geWindow;

	/**
	 * The list of created players.
	 */
	private ArrayList<Player> players = new ArrayList<Player>();


	/**
	 * Creates a PlayerPopUp.
	 * 
	 * @param geWindow The controlling {@link GEWindow}
	 * @param minPlayer The minimum number of players required
	 * @param maxPlayer The maximum number of players required
	 */
	public PlayerPopUp(GEWindow geWindow, int minPlayer, int maxPlayer) {

		// Register controlling GEWindow
		this.geWindow = geWindow;

		LOG.info("PlayerPopUp instantiated.");

		// Get number of players
		int playerCount = this.askForPlayerCount(minPlayer, maxPlayer);

		// Instantiate players
		for (int i = 0; i < playerCount; i++) {

			String playerName = this.askForPlayerName(i + 1);
			Player player = new LocalPlayer(playerName);
			this.players.add(player);

		}

		this.geWindow.onPlayerPopUpReturn(this.players);

	}


	/**
	 * Callback method for the {@link JButtons}, that creates the {@link Player}s based on
	 * the input and calls {@link GEWindow#onPlayerPopUpReturn(ArrayList)}.
	 * 
	 * @param event The ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
	}

	private int askForPlayerCount(int minPlayer, int maxPlayer) {

		if (minPlayer >= maxPlayer) {
			return maxPlayer;
		}

		String[] playerStrings = {"", "One player", "Two players", "Three players", "Four players"};

		String playerCount = (String) JOptionPane.showInputDialog(this,
		                    "Please choose the number of players:",
		                    "Game Explorer",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    Arrays.copyOfRange(playerStrings, minPlayer, maxPlayer + 1),
		                    playerStrings[minPlayer]);

		if (playerCount == null || playerCount.isEmpty()) {
		    return minPlayer;
		}

		return Arrays.asList(playerStrings).indexOf(playerCount);

	}

	private String askForPlayerName(int playerCount) {

		String[] ordinaryText = {"", "first", "second", "third", "fourth"};

		String playerName = (String) JOptionPane.showInputDialog(this,
                "Please enter a name for the " + ordinaryText[playerCount] + " player:",
                "Game Explorer",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");

		if (playerName == null || playerName.isEmpty()) {
			return "";
		}

		return playerName;

	}

}
