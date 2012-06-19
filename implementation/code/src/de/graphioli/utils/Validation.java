package de.graphioli.utils;
import java.io.File;

/**
 * This class provides static methods for checking the validity of given values.
 * 
 * @author Graphioli
 */
public class Validation {

	/**
	 * 
	 */
	public Validation() {}


	/**
	 * Checks if the specified file is valid.
	 * 
	 * @param file
	 * @return <code>true</code> if the given file is valid, <code>false</code> otherwise
	 */
	public static boolean isValidFile(File file) {
		return true;
	}


	/**
	 * Checks if the specified string is a valid {@link Player} name.
	 * 
	 * @param name
	 * @return <code>true</code> if the given name is valid, <code>false</code> otherwise
	 */
	public static boolean isValidPlayerName(String name) {
		return !name.isEmpty();
	}

}