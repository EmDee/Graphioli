package de.graphioli.gameexplorer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import de.graphioli.controller.GameManager;
import de.graphioli.model.Player;

/**
 * This has mock-up functionalities!
 * 
 * The GameExplorer lists the available games and enables the user to select and
 * start one of it.
 * 
 * @author Graphioli
 */
public class GameExplorer {

	/**
	 * Logging instance
	 */
	private static final Logger LOG = Logger.getLogger(GameExplorer.class.getName());

	/**
	 * The {@link GameManager} controlling this GameExplorer.
	 */
	private GameManager gameManager;

	/**
	 * The list of {@link GameDefinition}s in this GameExplorer.
	 */
	private ArrayList<GameDefinition> gameDefinitions = new ArrayList<GameDefinition>();

	/**
	 * The implementation of the {@link GEView} interface that this GameExplorer
	 * uses.
	 */
	private GEView view;

	/**
	 * Creates a new {@link GameExplorer}.
	 * 
	 * @param gameManager
	 *            The controlling {@link GameManager} for this GameExplorer.
	 */
	public GameExplorer(GameManager gameManager) {

		this.gameManager = gameManager;

		// scan game folder and create GameDefinitions
		this.scanGameFolderAndCreateGameDefinitions();

		// Initialize GEWindow (implementation of GEView)
		this.view = new GEWindow();
		view.registerController(this);
		view.generateView();

	}

	/**
	 * Creates {@link GameDefinition} from a given path to the json file.
	 * 
	 * @param jsonPath
	 *            path to the json file
	 * @return the {@link GameDefinition}
	 */
	private GameDefinition createGameDefinitionFromJSON(String jsonPath) {
		File json = new File(jsonPath);
		GameDefinition gameDefinition = null;
		try {
			gameDefinition = new Gson().fromJson(new FileReader(json), GameDefinition.class);
		} catch (JsonSyntaxException e) {
			LOG.severe("JsonSyntaxException: " + e.getMessage());
			e.printStackTrace();
		} catch (JsonIOException e) {
			LOG.severe("JsonIOException: " + e.getMessage());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			LOG.severe("FileNotFoundException: " + e.getMessage());
			e.printStackTrace();
		}
		return gameDefinition;
	}

	/**
	 * Scans the game folder for games, creates {@link GameDefinition}s and adds
	 * these to the gameDefinition's list.
	 * 
	 * @return {@link GameDefinition}s of the games in the parsed folder.
	 */
	private void scanGameFolderAndCreateGameDefinitions() {

		File gamesDirectory = new File("src/games/");
		System.out.println("gamesDirectory: " + gamesDirectory);
		String pathToPropertyFile = new String();
		GameDefinition gameDefinition;

		// TODO: Different path for different environments
		for (File tmpGame : gamesDirectory.listFiles()) {
			if (tmpGame.isDirectory()) {
				System.out.println("tmpGame: " + tmpGame.getName());
				pathToPropertyFile = (tmpGame.getName() + "/properties.json");
				gameDefinition = this.createGameDefinitionFromJSON("src/games/" + pathToPropertyFile);
				if (gameDefinition != null) {
					this.gameDefinitions.add(gameDefinition);
				}
			}
		}
	}

	/**
	 * Calls the {@link GameManager} to start the game of the given
	 * {@link GameDefinition} and with the given {@link Player}s.
	 * 
	 * @param gameDefinition
	 *            The GameDefinition of the selected game.
	 * @param players
	 *            The list of players.
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean selectGame(GameDefinition gameDefinition, ArrayList<Player> players) {

		LOG.finer("GameExplorer.<em>selectGame([...])</em> called.");

		// Forward call to GameManager with the selected GameDefinition and
		// Players
		return this.gameManager.startGame(gameDefinition, players);

	}

	/**
	 * Opens the help file of the given {@link GameDefinition}.
	 * 
	 * @param gameDefinition
	 *            The selected GameDefinition
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean openHelpFile(GameDefinition gameDefinition) {
		// TODO implementation

		LOG.info("GameExplorer.<em>openHelpFile([...])</em> called.");

		System.out.println("URI to help file: " + gameDefinition.getHelpFile().toString());
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
	 * Returns the {@link GameDefinition} at the specific index in the list of
	 * GameDefinitions.
	 * 
	 * @param index
	 *            The index of the GameDefinition in the list
	 * @return the {@link GameDefinition} at the specific index
	 */
	public GameDefinition getGameDefinitionAtIndex(int index) {
		return this.gameDefinitions.get(index);
	}

}
