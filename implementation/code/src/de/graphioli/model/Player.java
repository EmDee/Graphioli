package de.graphioli.model;

import java.util.UUID;
import de.graphioli.utils.UIDManager;
import de.graphioli.utils.Validation;

/**
 * This class represents a player.
 * 
 * @author Graphioli
 */
public abstract class Player {

	/**
	 * The unique ID of this {@link Player}
	 */
	private UUID uid;

	/**
	 * The player's name
	 */
	private String name;

	/**
	 * Creates a new {@link Player} with the specified name.
	 * 
	 * @param name
	 *            The player's name
	 * @throws IllegalArgumentException
	 *             if param <code>name</code> is invalid
	 * @see Validator#isvalidPlayerName
	 */
	public Player(String name) {

		if (Validation.isValidPlayerName(name)) {
			this.name = name;
		} else {
			// TODO: Find consistent form for exceptions.
			throw new IllegalArgumentException("Param 'name' is invalid.");
		}

		this.uid = UIDManager.generateUniqueID();

	}

	/**
	 * Returns the unique ID of this {@link Player}.
	 * 
	 * @return the unique ID of this player.
	 */
	public UUID getUID() {
		return this.uid;
	}

	/**
	 * Returns the player's name.
	 * 
	 * @return the player's name
	 */
	public String getName() {
		return this.name;
	}

}
