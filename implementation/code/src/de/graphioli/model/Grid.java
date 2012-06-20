package de.graphioli.model;

import de.graphioli.utils.Validation;

/**
 * This class represents the grid, on which the {@link Graph} will be located.
 * 
 * @author Graphioli
 * @todo Implement ArrayIndexOutOfBounds checkings
 */
public class Grid {

	/**
	 * The number of horizontal grid points
	 */
	private int horizontalGridPoints;

	/**
	 * The number of vertical grid points
	 */
	private int verticalGridPoints;

	/**
	 * Array-based representation of the grid
	 */
	private VisualVertex[][] grid;


	/**
	 * Creates a new {@link Grid} with the specified parameters.
	 * 
	 * @param horizontalGridPoints The number of horizontal grid points
	 * @param verticalGridPoints The number of vertical grid points
	 */
	public Grid(int horizontalGridPoints, int verticalGridPoints) {

		this.horizontalGridPoints = horizontalGridPoints;
		this.verticalGridPoints = verticalGridPoints;
		this.grid = new VisualVertex[horizontalGridPoints][verticalGridPoints];

	}


	/**
	 * Adds the specified {@link VisualVertex} to the Grid.
	 * 
	 * @param visualVertex The VisualVertex to add
	 * @return <code>true</code> if the VisualVertex was added successfully to this Grid, <code>false</code> otherwise
	 */
	public boolean addVisualVertexToGrid(GridPoint gridPoint, VisualVertex visualVertex) {

		// Early negative return if specified GridPoint is invalid
		if (!Validation.isValidGridPoint(gridPoint, this.horizontalGridPoints, this.verticalGridPoints)) {
			return false;
		}

		// Get VisualVertex at specified GridPoint
		VisualVertex visualVertexAtGridPoint = this.grid[gridPoint.getPositionX()][gridPoint.getPositionY()];

		// Return false if GridPoint is not empty
		if (visualVertexAtGridPoint != null) {
			return false;
		}

		// Add VisualVertex
		this.grid[gridPoint.getPositionX()][gridPoint.getPositionY()] = visualVertex;

		return true;

	}


	/**
	 * Removes the {@link VisualVertex} from the specified {@link GridPoint} on the Grid.
	 * 
	 * @param gridPoint The GridPoint from which the VisualVertex will be removed
	 * @return <code>true</code> if a VisualVertex was removed from this GridPoint on the Grid, <code>false</code> otherwise
	 */
	public boolean removeVisualVertexAtGridPoint(GridPoint gridPoint) {

		// Early negative return if specified GridPoint is invalid
		if (!Validation.isValidGridPoint(gridPoint, this.horizontalGridPoints, this.verticalGridPoints)) {
			return false;
		}

		// Get VisualVertex at specified GridPoint
		VisualVertex visualVertex = this.grid[gridPoint.getPositionX()][gridPoint.getPositionY()];

		// Return false if GridPoint is empty
		if (visualVertex == null) {
			return false;
		}

		// Remove VisualVertex
		this.grid[gridPoint.getPositionX()][gridPoint.getPositionY()] = null;

		return true;

	}


	/**
	 * Returns the {@link VisualVertex} at the specified {@link GridPoint}.
	 * 
	 * @param gridPoint the GridPoint of the VisualVertex to return
	 * @return the VisualVertex at the specified GridPoint or <code>null</code> if the GridPoint is empty
	 */
	public VisualVertex getVisualVertexAtGridPoint(GridPoint gridPoint) {

		// Early negative return if specified GridPoint is invalid
		if (!Validation.isValidGridPoint(gridPoint, this.horizontalGridPoints, this.verticalGridPoints)) {
			return null;
		}

		// Get VisualVertex at specified GridPoint
		VisualVertex visualVertex = this.grid[gridPoint.getPositionX()][gridPoint.getPositionY()];

		return visualVertex;

	}

}
