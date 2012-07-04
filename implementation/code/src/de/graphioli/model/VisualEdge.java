package de.graphioli.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * This class represents an {@link Edge} with its visual representation.
 * 
 * @author Graphioli
 */
public abstract class VisualEdge extends Edge {

	/**
	 * Information if the VisualEdge is visible or not (for undirected graphs).
	 */
	private boolean visible;

	/**
	 * Creates a new VisualEdge between the given vertices.
	 * 
	 * @param vertexA
	 *            the origin vertex
	 * @param vertexB
	 *            the target vertex
	 */
	public VisualEdge(Vertex vertexA, Vertex vertexB) {
		super(vertexA, vertexB);
		this.visible = true;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Recreates the fields, that are not serialized.
	 */
	public void reload() {
		this.onReload();
	}
	
	/**
	 * Implement this method to recreates the fields, that are not serialized.
	 */
	abstract protected void onReload();

	/**
	 * Returns whether or not this VisualEdge is visible.
	 * 
	 * @return {@code true} if the VisualEdge is visible
	 */
	public boolean isVisible() {
		return this.visible;
	}

	/**
	 * Sets the VisualEdge (in)visible.
	 * 
	 * @param visible
	 *            {@code true} should be set visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	/**
	 * Draws this edge onto the given {@code Graphics2D} object.
	 * 
	 * @param g2d the {@code Graphics2D} object to draw on.
	 * @param originX the x coordinate of the origin vertex.
	 * @param originY the y coordinate of the origin vertex.
	 * @param targetX the x coordinate of the target vertex.
	 * @param targetY the y coordinate of the target vertex.
	 */
	public abstract void draw(Graphics2D g2d, int originX, int originY, int targetX, int targetY);

}
