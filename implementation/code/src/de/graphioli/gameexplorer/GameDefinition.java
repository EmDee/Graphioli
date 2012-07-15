package de.graphioli.gameexplorer;

import de.graphioli.model.GameResources;
import de.graphioli.model.MenuItem;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents the game's definition, containing crucial information
 * that is needed to start a {@link de.graphioli.controller.Game Game}.
 * 
 * @author Team Graphioli
 */
public final class GameDefinition implements Serializable {

	/**
	 * Serialization UID.
	 */
	private static final long serialVersionUID = 6466462368240633154L;

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
	 * The list of additional menu items.
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
	 * Whether the {@link de.graphioli.model.Graph Graph} of the game is directed or not.
	 */
	private boolean isDirectedGraph;

	/**
	 * Whether the game supports saving and loading of savegames or not.
	 */
	private boolean supportsSavegames;

	/**
	 * Private constructor to ensure that no instance is created of this class.
	 */
	private GameDefinition() {
	}

	/**
	 * Tries to localize a string if it begins with an {@literal @} using the
	 * given {@link GameResources}.
	 * 
	 * @param s
	 *            the string to localize.
	 * @param res
	 *            the game resources.
	 * @return the localized string if it begins with an {@literal @}, otherwise
	 *         the string itself.
	 */
	private static String localizeString(String s, GameResources res) {
		if (!s.startsWith("@") || s == null || s.length() <= 1) {
			return s;
		}
		String keyString = s.replaceFirst("@", "");
		return res.getStringResource(keyString);
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
	 * Returns the description of the game.
	 * 
	 * @return the description of the game
	 */
	public String getDescription() {
		return this.description;
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
	 * Returns the number of horizontal grid points.
	 * 
	 * @return the number of horizontal grid points
	 */
	public int getHorizontalGridPointCount() {
		return this.horizontalGridPointCount;
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
	 * Returns the list of additional menu items.
	 * 
	 * @return the list of additional menu items
	 */

	public ArrayList<MenuItem> getMenu() {
		return this.menu;
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
	 * Returns the name of the game.
	 * 
	 * @return the name of the game
	 */
	public String getName() {
		return this.name;
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
	 * Returns whether the {@link de.graphioli.model.Graph Graph} of the game is directed or not.
	 * 
	 * @return <code>true</code> if the graph of the game is directed,
	 *         <code>false</code> otherwise
	 */
	public boolean isDirectedGraph() {
		return this.isDirectedGraph;
	}

	/**
	 * Returns whether the game supports saving and loading of savegames or not.
	 * 
	 * @return <code>true</code> if the game supports savegames,
	 *         <code>false</code> otherwise
	 */
	public boolean supportsSavegames() {
		return this.supportsSavegames;
	}

	/**
	 * Localizes the strings in this game definition based on its "lang" files.
	 */
	public void localizeInstance() {
		GameResources res = new GameResources(this.className);
		this.name = localizeString(this.name, res);
		this.description = localizeString(this.description, res);

		if (this.menu != null && this.menu.size() > 0) {
			ArrayList<MenuItem> localizedMenu = new ArrayList<MenuItem>(this.menu.size());
			for (MenuItem tmpItem : this.menu) {
				localizedMenu.add(new MenuItem(tmpItem.getId(), localizeString(tmpItem.getName(), res)));
			}
			this.menu = localizedMenu;
		}
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
		result = prime * result + ((this.className == null) ? 0 : this.className.hashCode());
		result = prime * result + ((this.gamePath == null) ? 0 : this.gamePath.hashCode());
		result = prime * result + this.horizontalGridPointCount;
		result = prime * result + (this.isDirectedGraph ? 1231 : 1237);
		result = prime * result + this.maxPlayerCount;
		result = prime * result + ((this.menu == null) ? 0 : this.menu.hashCode());
		result = prime * result + this.minPlayerCount;
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
		result = prime * result + (this.supportsSavegames ? 1231 : 1237);
		result = prime * result + this.verticalGridPointCount;
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		GameDefinition other = (GameDefinition) obj;
		if (this.className == null) {
			if (other.className != null) {
				return false;
			}
		} else if (!this.className.equals(other.className)) {
			return false;
		}
		if (this.gamePath == null) {
			if (other.gamePath != null) {
				return false;
			}
		} else if (!this.gamePath.equals(other.gamePath)) {
			return false;
		}
		if (this.horizontalGridPointCount != other.horizontalGridPointCount) {
			return false;
		}
		if (this.isDirectedGraph != other.isDirectedGraph) {
			return false;
		}
		if (this.maxPlayerCount != other.maxPlayerCount) {
			return false;
		}
		if (this.menu == null) {
			if (other.menu != null) {
				return false;
			}
		} else if (!this.menu.equals(other.menu)) {
			return false;
		}
		if (this.minPlayerCount != other.minPlayerCount) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.supportsSavegames != other.supportsSavegames) {
			return false;
		}
		if (this.verticalGridPointCount != other.verticalGridPointCount) {
			return false;
		}
		return true;
	}

}
