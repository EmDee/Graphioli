package de.graphioli.view;

import de.graphioli.model.Grid;
import de.graphioli.model.GridPoint;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This class handles mouse input, serves as a connector between {@link Grid} and display and handles the size of the drawn {@link VisualVertex}.
 * 
 * @author Graphioli
 */
public class VisualGrid implements MouseListener {

	/**
	 * The parent {@link GameWindow} associated with this {@link VisualGrid}
	 */
	private GameWindow parentGameWindow;
	
	/**
	 * The {@link Grid} associated with this {@link VisualGrid}
	 */
	private Grid grid;
	
	/**
	 * The size of the {@link VisualVertex}es displayed in the {@link GraphCanvas}
	 */
	private int visualVertexSize;
	
	/**
	 * Creates a {@link VisualGrid} and registers its parent {@link GameWindow}.
	 * 
	 * @param parentGameWindow The {@link GameWindow} that contains the {@link GraphCanvas} to this {@link VisualGrid}
	 */
	public VisualGrid(GameWindow parentGameWindow) {}
	
	
	/**
	 * Parses the coordinates of the mouse click to the specific {@link GridPoint}.
	 * 
	 * @param xCoord The x coordinate of the mouse click
	 * @param yCoord The y coordinate of the mouse click
	 * @return GridPoint The {@link GridPoint} to the responsible mouse click coordinates
	 */
	public GridPoint parseCoordinates(int xCoord, int yCoord) {
		return null;
	}
	
	/**
	 * Sets the size of the displayed {@link VisualVertex}es up to a {@link Grid} specific maximum value.
	 * 
	 * @param size The size of the vertices
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	public boolean setVisualVertexSize(int size) {
		return true;
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
	 * Returns the {@link Grid} associated with the {@link VisualGrid}.
	 * 
	 * @return Grid The  of this
	 */
	public Grid getGrid() {	
		return this.grid;
	}

	/**
	 * Invoked if the mouse button has been clicked, calls parseCoordinates(int xCoord, int yCoord) and forwards the selected {@link GridPoint} to its parent {@link GameWindow}.
	 * 
	 * @param event The {@link MouseEvent} containing the coordinates of the mouse click
	 */
	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
