package de.graphioli.view;

import de.graphioli.model.Grid;
import de.graphioli.model.GridPoint;
import de.graphioli.model.VisualVertex;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This class handles mouse input, serves as a connector between {@link Grid}
 * and display and handles the size of the drawn {@link VisualVertex}.
 * 
 * @author Team Graphioli
 */
public class VisualGrid implements MouseListener {

	/**
	 * The ratio of the gaps between to vertices to the size of the vertices.
	 */
	private static final float GAP_SCALE = 1.7F;

	/**
	 * The parent {@link GameWindow} associated with this {@link VisualGrid}.
	 */
	private GameWindow parentGameWindow;

	/**
	 * The {@link Grid} associated with this {@link VisualGrid}.
	 */
	private Grid grid;

	/**
	 * The size of the {@link VisualVertex}es displayed in the
	 * {@link GraphCanvas}.
	 */
	private int visualVertexSize;

	/**
	 * The distance of two {@link GridPoint}s in the {@link GraphCanvas}.
	 */
	private int gridScale;

/**
	 * Creates a {@link VisualGrid} and registers its parent {@link GameWindow}.
	 * 
	 * @param parentGameWindow
	 *            The {@link GameWindow} that contains the {@link GraphCanvas}
	 *            to this {@link VisualGrid}
	 */
	public VisualGrid(GameWindow parentGameWindow) {
		this.parentGameWindow = parentGameWindow;
		this.grid = this.parentGameWindow.getViewManager().getGameManager().getGameBoard().getGrid();
		// default gridScale based on VertexSize for now
		this.gridScale = (int) (VisualVertex.PIXELS_PER_SIDE * GAP_SCALE);
	}

	/**
	 * Calculates the size of this grid.
	 * 
	 * @return the size in pixels.
	 */
	public Dimension calculateSize() {
		int xCoord = (this.grid.getHorizontalGridPoints() + 1) * this.gridScale;
		int yCoord = (this.grid.getVerticalGridPoints() + 1) * this.gridScale;
				
		return new Dimension(Math.round(xCoord), Math.round(yCoord));
	}

	/**
	 * Draws the grid on the given Graphics2D Object.
	 * 
	 * @param g2d
	 *            the Graphics object to draw this grid on.
	 */
	public void draw(Graphics2D g2d) {
		// Drawing grid
		int xScaled;
		int yScaled;
		
		g2d.setColor(Color.BLACK);
		
		Dimension size = this.calculateSize();
		int width = size.width;
		int height = size.height;

		for (int xPoints = 0; xPoints < this.grid.getHorizontalGridPoints(); xPoints++) {
			xScaled = (xPoints + 1) * this.gridScale;
			g2d.drawLine(xScaled, 0, xScaled, height);
		}
		for (int yPoints = 0; yPoints < this.grid.getVerticalGridPoints(); yPoints++) {
			yScaled = (yPoints + 1) * this.gridScale;
			g2d.drawLine(0, yScaled, width, yScaled);
		}
	}
	
	/**
	 * Returns the {@link Grid} associated with the {@link VisualGrid}.
	 * 
	 * @return Grid The of this
	 */
	public Grid getGrid() {
		return this.grid;
	}

	/**
	 * Returns the gridScale.
	 * 
	 * @return The gridScale
	 */
	public int getGridScale() {
		return this.gridScale;
	}

	/**
	 * Returns the size of the displayed {@link VisualVertex}es.
	 * 
	 * @return size The size of the vertices
	 */
	public int getVisualVertexSize() {
		return this.visualVertexSize;
	}

	/**
	 * Invoked if the mouse button has been clicked, calls parseCoordinates(int
	 * xCoord, int yCoord) and forwards the selected {@link GridPoint} to its
	 * parent {@link GameWindow}.
	 * 
	 * @param event
	 *            The {@link MouseEvent} containing the coordinates of the mouse
	 *            click
	 */
	@Override
	public void mouseClicked(MouseEvent event) {
		GridPoint gridPointClicked = this.parseCoordinates(event.getX(), event.getY());
		if (gridPointClicked != null) {
			this.parentGameWindow.getViewManager().onGridPointClick(gridPointClicked);
		}
	}
	
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// Not needed
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// Not needed
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// Not needed
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// Not needed
	}

	/**
	 * Parses the coordinates of the mouse click to the specific
	 * {@link GridPoint}.
	 * 
	 * @param xCoord
	 *            The x coordinate of the mouse click
	 * @param yCoord
	 *            The y coordinate of the mouse click
	 * @return GridPoint The {@link GridPoint} to the responsible mouse click
	 *         coordinates
	 */
	public GridPoint parseCoordinates(int xCoord, int yCoord) {
		int xpos = Math.round((xCoord - VisualVertex.PIXELS_PER_SIDE / 2) / this.gridScale) + 1;
		int ypos = Math.round((yCoord - VisualVertex.PIXELS_PER_SIDE / 2) / this.gridScale) + 1;
		if (((xpos * this.gridScale + VisualVertex.PIXELS_PER_SIDE / 2) > xCoord)
				&& ((xpos * this.gridScale - VisualVertex.PIXELS_PER_SIDE / 2) < xCoord)
				&& ((ypos * this.gridScale + VisualVertex.PIXELS_PER_SIDE / 2) > yCoord)
				&& ((ypos * this.gridScale - VisualVertex.PIXELS_PER_SIDE / 2) < yCoord)) {
			return new GridPoint(xpos - 1, ypos - 1);
		}
		return null;
	}

	/**
	 * Sets the size of the displayed {@link VisualVertex}es up to a
	 * {@link Grid} specific maximum value.
	 * 
	 * @param size
	 *            The size of the vertices
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean setVisualVertexSize(int size) {
		this.visualVertexSize = size;
		return true;
	}

}
