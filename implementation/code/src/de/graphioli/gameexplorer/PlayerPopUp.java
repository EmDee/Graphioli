package de.graphioli.gameexplorer;

import de.graphioli.model.LocalPlayer;
import de.graphioli.model.Player;
import de.graphioli.utils.Localization;
import de.graphioli.utils.Validation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Represents a pop-up window that is used to select the the number of players
 * and their names for a {@link Game}.
 * 
 * @author Graphioli
 */
public class PlayerPopUp extends JFrame {

	/**
	 * Logging instance.
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
	 * @param geWindow
	 *            The controlling {@link GEWindow}
	 * @param minPlayer
	 *            The minimum number of players required
	 * @param maxPlayer
	 *            The maximum number of players required
	 */
	public PlayerPopUp(GEWindow geWindow, int minPlayer, int maxPlayer) {

		// Register controlling GEWindow
		this.geWindow = geWindow;

		LOG.info("PlayerPopUp instantiated.");

		// Get number of players
		int playerCount = this.askForPlayerCount(minPlayer, maxPlayer);

		// If user pressed 'Cancel', close window
		if (playerCount == 0) {
			LOG.fine("User cancelled game initialization.");
			return;
		}

		LOG.fine("Number of players chosen: " + playerCount);

		// Instantiate players
		for (int i = 0; i < playerCount; i++) {

			String playerName = "";

			if (playerCount == 1) {
				playerName = this.askForPlayerName();
			} else {
				playerName = this.askForPlayerName(i + 1);
			}

			// If user pressed 'Cancel', close window
			if (playerName == null) {
				LOG.fine("User cancelled game initialization.");
				return;
			}
		
			Player player = new LocalPlayer(playerName);
			this.players.add(player);

			LOG.fine("Player " + (i + 1) + " instantiated: '" + playerName + "'");

		}

		this.geWindow.onPlayerPopUpReturn(this.players);

	}

	/**
	 * Prompts for the number of players to initialize.
	 * 
	 * @param minPlayer
	 *            The minimum number of players required
	 * @param maxPlayer
	 *            The maximum number of players allowed
	 * @return The chosen number of players
	 */
	private int askForPlayerCount(int minPlayer, int maxPlayer) {

		if (minPlayer >= maxPlayer) {
			return maxPlayer;
		}

		String[] playerStrings = { "", Localization.getLanguageString("one_player"),
				Localization.getLanguageString("two_players"), Localization.getLanguageString("three_players"),
				Localization.getLanguageString("four_players") };
		int playerCount = 0;

		while (playerCount == 0) {

			// Ask for player number
			String inputPlayerCount = this.showInputDialog(Localization.getLanguageString("player_pop_up_count"),
					Arrays.copyOfRange(playerStrings, minPlayer, maxPlayer + 1));

			// User pressed 'Cancel'
			if (inputPlayerCount == null) {

				return 0;

				// User entered invalid number of players
			} else if (inputPlayerCount.isEmpty()) {

				return minPlayer;

				// User entered valid number of players
			} else {

				playerCount = Arrays.asList(playerStrings).indexOf(inputPlayerCount);

			}

		}

		return playerCount;

	}

	/**
	 * Prompts for a name and returns the name for player initialization.
	 * 
	 * Use this method if only one player needs to be instantiated. Use {@link PlayerPopUp#askForPlayerName(int)}
	 * if more than one player will be instantiated.
	 * 
	 * @return The chosen name
	 */
	private String askForPlayerName() {
		return this.askForPlayerName(0);
	}

	/**
	 * Prompts for a name and returns the name for player initialization.
	 * 
	 * @param playerCount
	 *            The number of the current player
	 * @return The chosen name
	 */
	private String askForPlayerName(int playerCount) {

		String playerName = "";
		String inputPlayerName;
		String localizationSuffix = (playerCount > 0) ? "_" + playerCount : "";

		while (playerName.equals("")) {

			// Ask for player name
			inputPlayerName = this.showInputDialog(
					Localization.getLanguageString("player_pop_up_input" + localizationSuffix), null);

			// User pressed 'Cancel'
			if (inputPlayerName == null) {

				// Ask user, if he really wants to cancel
				boolean choice = this.showConfirmDialog(Localization.getLanguageString("player_pop_up_choice"));

				if (choice) {
					return null;
				}

				// User entered invalid or empty player name (prompts again)
			} else if (inputPlayerName.isEmpty() || !Validation.isValidPlayerName(inputPlayerName)) {

				this.showMessageDialog(Localization.getLanguageString("player_pop_up_valid"));

				// User entered valid player name
			} else {

				playerName = inputPlayerName;

			}

		}

		return playerName;

	}

	/**
	 * Shows an input dialog. <br />
	 * If availableOptions is <code>null</code>, the user may enter any text.
	 * 
	 * @param message
	 *            The message to display
	 * @param availableOptions
	 *            Array of available options
	 * @return The user input or choice
	 */
	private String showInputDialog(String message, Object[] availableOptions) {

		Object selectedOption = "";

		// If options are provided, chose first as default
		if (availableOptions != null) {
			selectedOption = availableOptions[0];
		}

		// Ask for user input
		String input = (String) JOptionPane.showInputDialog(this, message, this.geWindow.getTitle(),
				JOptionPane.PLAIN_MESSAGE, null, availableOptions, selectedOption);

		return input;

	}

	/**
	 * Shows a confirmation dialog.
	 * 
	 * @param message
	 *            The message to display
	 * @return <code>true</code> if the user confirmed, <code>false</code>
	 *         otherwise
	 */
	private boolean showConfirmDialog(String message) {

		int confirmChoice = JOptionPane.showConfirmDialog(this, message, this.geWindow.getTitle(),
				JOptionPane.YES_NO_OPTION);

		// confirmChoice == 0: Yes
		// confirmChoice == 1: No
		return confirmChoice == 0;

	}

	/**
	 * Shows a message dialog.
	 * 
	 * @param message
	 *            The message to display
	 */
	private void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(this, message, this.geWindow.getTitle(), JOptionPane.WARNING_MESSAGE);
	}

}
