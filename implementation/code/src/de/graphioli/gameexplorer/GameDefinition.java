package de.graphioli.gameexplorer;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import de.graphioli.model.MenuItem;

/**
 * This class represents the game’s definition, containing crucial information
 * that is needed to start a {@link Game}.
 * 
 * @author Graphioli
 */
public final class GameDefinition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9032856528106026142L;

	/**
	 * The name of the game.
	 */
	private String name;

	/**
	 * The minimum number of players allowed.
	 */
	private int minPlayerCount;

	/**
	 * The maximum number of players allowed.
	 */
	private int maxPlayerCount;

	/**
	 * The path to the game class file.
	 */
	private String gamePath;

	/**
	 * The description of the game.
	 */
	private String description;

	/**
	 * The name of the game class.
	 */
	private String className;

	/**
	 * The URI to the help file of the game.
	 */
	private URI helpFile;

	/**
	 * The list of additional menu items
	 */
	private ArrayList<MenuItem> menu;

	/**
	 * The number of horizontal grid points.
	 */
	private int horizontalGridPointCount;

	/**
	 * The number of vertical grid points.
	 */
	private int verticalGridPointCount;

	/**
	 * Whether the {@link Graph} of the game is directed or not.
	 */
	private boolean isDirectedGraph;

	/**
	 * Private constructor to ensure that no instance is created of this class.
	 */
	private GameDefinition() {
	};

	/**
	 * Returns the name of the game.
	 * 
	 * @return the name of the game
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the minimum number of players allowed.
	 * 
	 * @return the minimum number of players allowed
	 */
	public int getMinPlayerCount() {
		return this.minPlayerCount;
	}

	/**
	 * Returns the maximum number of players allowed.
	 * 
	 * @return the maximum number of players allowed
	 */
	public int getMaxPlayerCount() {
		return this.maxPlayerCount;
	}

	/**
	 * Returns the path to the game class file.
	 * 
	 * @return the path to the game class file
	 */
	public String getGamePath() {
		return this.gamePath;
	}

	/**
	 * Returns the description of the game.
	 * 
	 * @return the description of the game
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Returns the name of the game class.
	 * 
	 * @return the name of the game class
	 */
	public String getClassName() {
		return this.className;
	}

	/**
	 * Returns the URI to the help file of the game.
	 * 
	 * @return the URI to the help file of the game
	 */
	public URI getHelpFile() {
		return this.helpFile;
	}

	/**
	 * Returns the list of additional menu items.
	 * 
	 * @return the list of additional menu items
	 */

	public ArrayList<MenuItem> getMenu() {
		return this.menu;
	}

	/**
	 * Returns the number of horizontal grid points.
	 * 
	 * @return the number of horizontal grid points
	 */
	public int getHorizontalGridPointCount() {
		return this.horizontalGridPointCount;
	}

	/**
	 * Returns the number of vertical grid points.
	 * 
	 * @return the number of vertical grid points
	 */
	public int getVerticalGridPointCount() {
		return this.verticalGridPointCount;
	}

	/**
	 * Returns whether the {@link Graph} of the game is directed or not.
	 * 
	 * @return <code>true</code> if the graph of the game is directed,
	 *         <code>false</code> otherwise
	 */
	public boolean isDirectedGraph() {
		return this.isDirectedGraph;
	}

	/**
	 * Returns a string representation of this instance of
	 * {@link GameDefinition}.
	 * 
	 * @return a string representation of this instance of
	 *         {@link GameDefinition}.
	 */
	@Override
	public String toString() {
		return this.getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((className == null) ? 0 : className.hashCode());
		result = prime * result + horizontalGridPointCount;
		result = prime * result + (isDirectedGraph ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + verticalGridPointCount;
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameDefinition other = (GameDefinition) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (horizontalGridPointCount != other.horizontalGridPointCount)
			return false;
		if (isDirectedGraph != other.isDirectedGraph)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (verticalGridPointCount != other.verticalGridPointCount)
			return false;
		return true;
	}
	

}