package de.graphioli.utils;

import java.util.ArrayList;

import de.graphioli.model.GridPoint;
import de.graphioli.model.Player;

/**
 * This class provides static methods for checking the validity of given values.
 * 
 * @author Team Graphioli
 */
public final class Validation {

	private Validation() {
	}

	/**
	 * Checks if the specified {@link GridPoint} is a valid GridPoint for a
	 * specific Grid.
	 * 
	 * @param gridPoint
	 *            The GridPoint to check for validity
	 * @param horizontalGridPoints
	 *            The number of horizontal grid points
	 * @param verticalGridPoints
	 *            The number of vertical grid points
	 * @return <code>true</code> if the given gridPoint is valid,
	 *         <code>false</code> otherwise
	 */
	public static boolean isValidGridPoint(GridPoint gridPoint, int horizontalGridPoints, int verticalGridPoints) {

		// Check if specified GridPoint is in bounds of Grid
		return !(gridPoint == null
				|| gridPoint.getPositionX() < 0
				|| gridPoint.getPositionX() >= horizontalGridPoints
				|| gridPoint.getPositionY() < 0 || gridPoint.getPositionY() >= verticalGridPoints);

	}

	/**
	 * Checks if the specified string is a valid and unique {@link Player} name.
	 * 
	 * @param name
	 *            The name to check for validity
	 * @param createdPlayers the players already created (can be {{@code null}).
	 * @return <code>true</code> if the given name is valid, <code>false</code>
	 *         otherwise
	 */
	public static boolean isValidPlayerName(String name, ArrayList<Player> createdPlayers) {
		if (name == null || name.trim().isEmpty()) {
			return false;
		}
		
		if (createdPlayers == null) {
			return true;
		}
		
		for (int i = 0; i < createdPlayers.size(); i++) {
			if (createdPlayers.get(i) != null && createdPlayers.get(i).getName().equals(name)) {
				return false;
			}
		}
		
		return true;		
	}
}
