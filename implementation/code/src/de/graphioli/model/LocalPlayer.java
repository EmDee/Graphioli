package de.graphioli.model;

/**
 * This class represents a local {@link Player}.
 * 
 * @author Graphioli
 */
public class LocalPlayer extends Player {

	/**
	 * Creates a new {@link LocalPlayer} with the specified name.
	 * 
	 * @param name
	 *            The player's name
	 * @throws IllegalArgumentException
	 *             if param <code>name</code> is invalid
	 * @see Validator#isvalidPlayerName
	 */
	public LocalPlayer(String name) {
		super(name);
	}

}
