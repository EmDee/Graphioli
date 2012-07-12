package de.graphioli.model;

import de.graphioli.utils.UIDManager;
import de.graphioli.utils.Validation;

import java.io.Serializable;
import java.util.UUID;

/**
 * This class represents a player.
 * 
 * @author Team Graphioli
 */
public abstract class Player implements Serializable {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -2106686641317916584L;

	/**
	 * The unique ID of this {@link Player}.
	 */
	private UUID uid;

	/**
	 * The player's name.
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
	 * Returns the player's name.
	 * 
	 * @return the player's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the unique ID of this {@link Player}.
	 * 
	 * @return the unique ID of this player.
	 */
	public UUID getUID() {
		return this.uid;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.uid == null) ? 0 : this.uid.hashCode());
		return result;
	}


	/**
	 * Compares this player to the given object.
	 * 
	 * @param obj the object to compare this one to
	 * @return {@code true} if the object is a Player and its UID equals this player's UID.
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
		Player other = (Player) obj;
		if (this.uid == null) {
			if (other.uid != null) {
				return false;
			}
		} else if (!this.uid.equals(other.uid)) {
			return false;
		}
		return true;
	}




}
