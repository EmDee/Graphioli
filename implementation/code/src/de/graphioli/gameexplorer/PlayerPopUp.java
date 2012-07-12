package de.graphioli.gameexplorer;

import de.graphioli.model.LocalPlayer;
import de.graphioli.model.Player;
import de.graphioli.utils.GameFileDialog;
import de.graphioli.utils.Localization;
import de.graphioli.utils.Validation;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Represents a pop-up window that is used to select the the number of players
 * and their names for a {@link Game}.
 * 
 * @author Team Graphioli
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
	 * @param supportsSavegames
	 * 			  Whether the game supports loading of savegames or not
	 */
	public PlayerPopUp(GEWindow geWindow, int minPlayer, int maxPlayer, boolean supportsSavegames) {

		// Register controlling GEWindow
		this.geWindow = geWindow;

		LOG.info("PlayerPopUp instantiated.");

		// If game supports savegames, ask for it
		File savegame = null;
		if (supportsSavegames) {
			int askForSavegame = this.askForSavegame();

			if (askForSavegame == 2) {
				savegame = GameFileDialog.loadGame(geWindow.getCurrentClassName(), geWindow);

				if (savegame == null) {
					LOG.fine("User cancelled savegame selection.");
					return;
				}
			} else if(askForSavegame == 0) {
				LOG.fine("User cancelled at savegame decision.");
				return;
			}
		}

		if (savegame != null) {

			LOG.fine("Loading existing savegame.");
			this.geWindow.onPlayerPopUpReturn(savegame);

		} else {

			LOG.fine("Initializing new game.");

			// Get number of players
			int playerCount = this.askForPlayerCount(minPlayer, maxPlayer);

			// If user pressed 'Cancel', close window
			if (playerCount == 0) {
				LOG.fine("User cancelled game initialization.");
				return;
			}

			LOG.fine("Number of players chosen: " + playerCount);

			// Instantiate players
			if (this.instantiatePlayers(playerCount)) {
				this.geWindow.onPlayerPopUpReturn(this.players);
			}

		}
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
	 * Prompts for a name and returns the name for player initialization. Use
	 * this method if only one player needs to be instantiated. Use
	 * {@link PlayerPopUp#askForPlayerName(int)} if more than one player will be
	 * instantiated.
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

			// User pressed 'Cancel', but this is the first (or only) dialog
			if (inputPlayerName == null && playerCount < 2) {
				return null;

				// User pressed 'Cancel', but has made other choices before
			} else if (inputPlayerName == null) {

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
	 * Asks the user whether to load savegames or to instantiate a new game.
	 * 
	 * @return <code>0</code> if the user cancelled,
	 *         <code>1</code> if the user wants to start a new game,
	 * 		   <code>2</code> if the user wants to load an existing savegame
	 */
	private int askForSavegame() {
		String optionCancel = Localization.getLanguageString("player_pop_up_cancel");
		String optionNewGame = Localization.getLanguageString("player_pop_up_new_game");
		String optionLoadGame = Localization.getLanguageString("player_pop_up_load_savegame");
		String[] options = {optionCancel, optionNewGame, optionLoadGame};

		String choice = (String) this.showDecisionDialog(Localization.getLanguageString("player_pop_up_ask_load_savegame"), options, 1);

		if (choice == optionNewGame) {
			return 1;
		} else if(choice == optionLoadGame) {
			return 2;
		}

		return 0;
	}

	/**
	 * Displays the Pop-Ups for entering the player names.
	 * 
	 * @param playerCount
	 *            the number of players to instantiate.
	 */
	private boolean instantiatePlayers(int playerCount) {
		for (int i = 0; i < playerCount; i++) {
			boolean isNewName;
			String playerName;
			do {
				isNewName = true;
				playerName = "";
				
				if (playerCount == 1) {
					playerName = this.askForPlayerName();
				} else {
					playerName = this.askForPlayerName(i + 1);
				}

				// If user pressed 'Cancel', close window
				if (playerName == null) {
					LOG.fine("User cancelled game initialization.");
					return false;
				}

				for (int j = 0; j < i; j++) {
					if (this.players.get(j).getName().equals(playerName)) {
						isNewName = false;
						this.showMessageDialog(Localization.getLanguageString("player_pop_up_uniqueName"));
						break;
					}
				}

			// If name is already taken, get user to choose a different one
			} while (!isNewName);

			Player player = new LocalPlayer(playerName);
			this.players.add(player);

			LOG.fine("Player " + (i + 1) + " instantiated: '" + playerName + "'");

		}
		return true;
	}

	/**
	 * Shows a dialog where the user has to decide between different options.
	 * 
	 * @param message The message to display with the decision dialog
	 * @param availableOptions Array of available options
	 * @param defaultOption The position in the array that is the default option
	 * @return The choice the user made
	 */
	private Object showDecisionDialog(String message, Object[] availableOptions, int defaultOption) {

		int decisionChoice = JOptionPane.showOptionDialog(this,
                message,
                this.geWindow.getTitle(),
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                availableOptions,
                availableOptions[defaultOption]);

		if (decisionChoice == JOptionPane.CLOSED_OPTION) {
			return null;
		}

		return availableOptions[decisionChoice];

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
	 * Shows a message dialog.
	 * 
	 * @param message
	 *            The message to display
	 */
	private void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(this, message, this.geWindow.getTitle(), JOptionPane.WARNING_MESSAGE);
	}

}
