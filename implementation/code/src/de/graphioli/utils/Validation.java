package de.graphioli.utils;
import java.io.File;

import de.graphioli.model.GridPoint;

/**
 * This class provides static methods for checking the validity of given values.
 * 
 * @author Graphioli
 */
public class Validation {

	public Validation() {}


	/**
	 * Checks if the specified file is valid.
	 * 
	 * @param file The file to check for validity
	 * @return <code>true</code> if the given file is valid, <code>false</code> otherwise
	 */
	public static boolean isValidFile(File file) {
		return true;
	}


	/**
	 * Checks if the specified string is a valid {@link Player} name.
	 * 
	 * @param name The name to check for validity
	 * @return <code>true</code> if the given name is valid, <code>false</code> otherwise
	 */
	public static boolean isValidPlayerName(String name) {
		return !name.isEmpty();
	}


	/**
	 * Checks if the specified {@link GridPoint} is a valid GridPoint for a specific Grid.
	 * 
	 * @param gridPoint The GridPoint to check for validity
	 * @param horizontalGridPoints The number of horizontal grid points
	 * @param verticalGridPoints The number of vertical grid points
	 * @return <code>true</code> if the given gridPoint is valid, <code>false</code> otherwise
	 */
	public static boolean isValidGridPoint(GridPoint gridPoint, int horizontalGridPoints, int verticalGridPoints) {

		// Check if specified GridPoint is in bounds of Grid
		if (gridPoint == null ||
				gridPoint.getPositionX() < 0 ||
				gridPoint.getPositionX() >= horizontalGridPoints ||
				gridPoint.getPositionY() < 0 ||
				gridPoint.getPositionY() >= verticalGridPoints) {
			return false;
		}

		return true;

	}
}