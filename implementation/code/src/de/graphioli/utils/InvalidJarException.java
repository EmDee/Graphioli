package de.graphioli.utils;

/**
 * This exception gets thrown, when a Jar file does not match the format for a
 * {@link de.graphioli.controller.Game Game}.
 * 
 * @author Team Graphioli
 */
public class InvalidJarException extends Exception {

	/**
	 * Serialization version UID.
	 */
	private static final long serialVersionUID = -736757078681283478L;

	/**
	 * Creates a new InvalidJarException.
	 */
	public InvalidJarException() {
	}

	/**
	 * Creates a new InvalidJarException with a message.
	 * 
	 * @param msg
	 *            the message.
	 */
	public InvalidJarException(String msg) {
		super(msg);
	}

}
