package de.graphioli.model;

/**
 * This class represents the grid, on which the {@link Graph} will be located.
 * 
 * @author Graphioli
 * @todo Implement ValidParameters checkings
 */
public class GridPoint {

	/**
	 * The x coordinate of the GridPoint
	 */
	private int positionX;

	/**
	 * The y coordinate of the GridPoint
	 */
	private int positionY;


	/**
	 * Creates a new GridPoint with the specified positions positionX and positionY.
	 * 
	 * @param positionX The x coordinate of the GridPoint
	 * @param positionY The y coordinate of the GridPoint
	 */
	GridPoint(int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}


	/**
	 * Returns the x coordinate of the {@link GridPoint}.
	 * @return the x coordinate of the GridPoint
	 */
	public int getPositionX() {
		return this.positionX;
	}


	/**
	 * Returns the y coordinate of the {@link GridPoint}.
	 * @return the y coordinate of the GridPoint
	 */
	public int getPositionY() {
		return this.positionY;
	}
	
	/**
	 * Returns a string representation of this grid point in form of "(PositionX, PositionY)". 
	 */
	@Override
	public String toString() {
		return "(" + positionX + ", " + positionY + ")";
	}

}
