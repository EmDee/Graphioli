package de.graphioli.model;

import java.awt.Image;

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
	 * @return the image of this {@code VisualEdge}
	 */
	public Image getBufferedImage() {
		// TODO Auto-generated method stub
		return null;
	}

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

}
