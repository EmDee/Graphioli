package de.graphioli.model;

import java.io.Serializable;

/**
 * This class represents a options menu item in a {@code Graphioli} game.
 * 
 * @author Team Graphioli
 *
 */
public class MenuItem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7225779580275935635L;

	/**
	 * The menu item's ID.
	 */
	private final int id;
	
	/**
	 * The menu item's name.
	 */
	private final String name;
	
	/**
	 * Creates a new menu item with the given name and ID.
	 * 
	 * @param id the ID.
	 * @param name the name.
	 */
	public MenuItem(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * Returns the ID.
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
