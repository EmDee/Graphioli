package de.graphioli.gameexplorer;

import java.net.URI;
import java.util.ArrayList;
import de.graphioli.controller.GameManager;
import de.graphioli.model.LocalPlayer;
import de.graphioli.model.Player;

/**
 * This has mock-up functionalities!
 * 
 * The GameExplorer lists the available games and enables the user to select and start one of it.
 * 
 * @author Graphioli
 * @todo Implement real GameExplorer
 */
public class GameExplorer {

	/**
	 * The {@link GameManager} controlling this GameExplorer.
	 */
	private GameManager gameManager;

	/**
	 * The list of {@link GameDefinition}s in this GameExplorer.
	 */
	private ArrayList<GameDefinition> gameDefinitions = new ArrayList<GameDefinition>();

	/**
	 * The implementation of the {@link View} interface that this GameExplorer uses.
	 */
	//private GEView view;


	/**
	 * Creates a new {@link GameExplorer}.
	 * 
	 * @param gameManager The controlling {@link GameManager} for this GameExplorer.
	 */
	public GameExplorer(GameManager gameManager) {

		this.gameManager = gameManager;

		// Instantiate mock-up GameDefinition
		GameDefinition gameDefinition = new GameDefinition("Test Game", 1, 2, "dummy/path/to/game.class", "Fake description for test game",
				"GameTest", "dummy/path/to/screenshot.jpg", "dummy/path/to/localization/file.txt", URI.create("http://supergame.io/help.html"), 8, 8, true);
		this.gameDefinitions.add(gameDefinition);

		// Instantiate mock-up players
		Player player1 = new LocalPlayer("Player 1");
		Player player2 = new LocalPlayer("Player 2");
		ArrayList<Player> players = new ArrayList<Player>();

		players.add(player1);
		players.add(player2);

		// Notify controller. Note: This is usually done in the startGame() method.
		this.gameManager.startGame(this.gameDefinitions.get(0), players);

	}


	/**
	 * Parses a {@link GameDefinition} from a specified JSONObject.
	 * 
	 * @param jsonData
	 * @return the parsed GameDefinition
	 */
	//private GameDefinition createGameDefinitionFromJSON(JSONObject jsonData) {}


	/**
	 * Scans the game folder for games and returns the {@link GameDefinition}s of the games in it.
	 * 
	 * @return {@link GameDefinition}s of the games in the parsed folder.
	 */
	//private ArrayList<GameDefinition> scanGameFolder() {}


	/**
	 * Calls the {@link GameManager} to start the game of the given {@link GameDefinition} and
	 * with the given {@link Player}s.
	 * 
	 * @param gameDefinition The GameDefinition of the selected game.
	 * @param players The list of players.
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	//public boolean selectGame(GameDefinition gameDefinition, ArrayList<Player> players) {}


	/**
	 * Opens the help file of the given {@link GameDefinition}.
	 * 
	 * @param gameDefinition The selected GameDefinition
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	public boolean openHelpFile(GameDefinition gameDefinition) {
		return true;
	}


	/**
	 * Returns the list of {@link GameDefinition}s of this {@link GameExplorer}.
	 * 
	 * @return the list of {@link GameDefinition}s of this {@link GameExplorer}
	 */
	public ArrayList<GameDefinition> getGameDefinitions() {
		return this.gameDefinitions;
	}


	/**
	 * Returns the {@link GameDefinition} at the specific index in the list of GameDefinitions.
	 * 
	 * @param index The index of the GameDefinition in the list
	 * @return the {@link GameDefinition} at the specific index
	 */
	public GameDefinition getGameDefinitionAtIndex(int index) {
		return this.gameDefinitions.get(index);
	}

}
