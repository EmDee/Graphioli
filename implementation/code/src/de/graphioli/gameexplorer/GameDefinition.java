package de.graphioli.gameexplorer;
import java.net.URI;
//import java.util.ArrayList;

public class GameDefinition {

	/**
	 * The name of the game
	 */
	private String name;

	/**
	 * The minimum number of players allowed
	 */
	private int minPlayerCount;

	/**
	 * The maximum number of players allowed
	 */
	private int maxPlayerCount;

	/**
	 * The path to the game class file
	 */
	private String gamePath;

	/**
	 * The description of the game
	 */
	private String description;

	/**
	 * The name of the game class
	 */
	private String fullyQualifiedClassName;

	/**
	 * The path to the screenshot of the game
	 */
	private String screenshotPath;

	/**
	 * The path to the localization file of the game
	 */
	private String localizationFilePath;

	/**
	 * The URI to the help file of the game
	 */
	private URI helpFile;

	/**
	 * The list of additional menu items
	 */
	//private ArrayList<MenuItem> menu;

	/**
	 * The number of horizontal grid points
	 */
	private int horizontalGridPointCount;

	/**
	 * The number of vertical grid points
	 */
	private int verticalGridPointCount;

	/**
	 * Whether the graph of the game is directed or not
	 */
	private boolean directedGraph;


	/**
	 * Creates a new instance of {@link GameDefinition}.
	 * 
	 * @param name The name of the game
	 * @param minPlayerCount The minimum number of players allowed
	 * @param maxPlayerCount The maximum number of players allowed
	 * @param gamePath The path to the game class file
	 * @param description The description of the game
	 * @param fullyQualifiedClassName The name of the game class
	 * @param screenshotPath The path to the screenshot of the game
	 * @param localizationFilePath The path to the localization file of the game
	 * @param helpFile The URI to the help file of the game
	 * @param menu The list of additional menu items
	 * @param horizontalGridPointCount The number of horizontal grid points
	 * @param verticalGridPointCount The number of vertical grid points
	 * @param directedGraph Whether the graph of the game is directed or not
	 */
	public GameDefinition(String name,
			int minPlayerCount,
			int maxPlayerCount,
			String gamePath,
			String description,
			String fullyQualifiedClassName,
			String screenshotPath,
			String localizationFilePath,
			URI helpFile,
			//ArrayList<MenuItem> menu,
			int horizontalGridPointCount,
			int verticalGridPointCount,
			boolean directedGraph) {

		this.name = name;
		this.minPlayerCount = minPlayerCount;
		this.maxPlayerCount = maxPlayerCount;
		this.gamePath = gamePath;
		this.description = description;
		this.fullyQualifiedClassName = fullyQualifiedClassName;
		this.screenshotPath = screenshotPath;
		this.localizationFilePath = localizationFilePath;
		this.helpFile = helpFile;
		//this.menu = menu;
		this.horizontalGridPointCount = horizontalGridPointCount;
		this.verticalGridPointCount = verticalGridPointCount;
		this.directedGraph = directedGraph;

	}


	/**
	 * Returns the name of the game.
	 * @return the name of the game
	 */
	public String getName() {
		return this.name;
	}


	/**
	 * Returns the minimum number of players allowed.
	 * @return the minimum number of players allowed
	 */
	public int getMinPlayerCount() {
		return this.minPlayerCount;
	}


	/**
	 * Returns the maximum number of players allowed.
	 * @return the maximum number of players allowed
	 */
	public int getMaxPlayerCount() {
		return this.maxPlayerCount;
	}


	/**
	 * Returns the path to the game class file.
	 * @return the path to the game class file
	 */
	public String getGamePath() {
		return this.gamePath;
	}


	/**
	 * Returns the description of the game.
	 * @return the description of the game
	 */
	public String getDescription() {
		return this.description;
	}


	/**
	 * Returns the name of the game class.
	 * @return the name of the game class
	 */
	public String getFullyQualifiedClassName() {
		return this.fullyQualifiedClassName;
	}


	/**
	 * Returns the path to the screenshot of the game.
	 * @return the path to the screenshot of the game
	 */
	public String getScreenshotPath() {
		return this.screenshotPath;
	}


	/**
	 * Returns the path to the localization file of the game.
	 * @return the path to the localization file of the game
	 */
	public String getLocalizationFilePath() {
		return this.localizationFilePath;
	}


	/**
	 * Returns the URI to the help file of the game.
	 * @return the URI to the help file of the game
	 */
	public URI getHelpFile() {
		return this.helpFile;
	}


	/**
	 * Returns the list of additional menu items.
	 * @return the list of additional menu items
	 */
	/*public ArrayList<MenuItem> getMenu() {
		return this.menu;
	}*/


	/**
	 * Returns the number of horizontal grid points.
	 * @return the number of horizontal grid points
	 */
	public int getHorizontalGridPointCount() {
		return this.horizontalGridPointCount;
	}


	/**
	 * Returns the number of vertical grid points.
	 * @return the number of vertical grid points
	 */
	public int getVerticalGridPointCount() {
		return this.verticalGridPointCount;
	}


	/**
	 * Returns whether the graph of the game is directed or not.
	 * @return <code>true</code> if the graph of the game is directed, <code>false</code> otherwise
	 */
	public boolean isDirectedGraph() {
		return this.directedGraph;
	}

}