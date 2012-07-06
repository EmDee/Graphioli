package de.graphioli.model;

import java.awt.Graphics2D;

/**
 * This class represents an {@link Edge} with its visual representation.
 * 
 * @author Graphioli
 */
public abstract class VisualEdge extends Edge {

	private boolean hasOpposingEdge;
	private boolean isOpposingEdge;

	/**
	 * Creates a new VisualEdge between the given vertices.
	 * 
	 * @param VisualVertexA
	 *            the origin VisualVertex
	 * @param VisualVertexB
	 *            the target VisualVertex
	 */
	public VisualEdge(VisualVertex VisualVertexA, VisualVertex VisualVertexB) {
		super(VisualVertexA, VisualVertexB);
		this.hasOpposingEdge = false;
		this.isOpposingEdge = false;
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
	 * Draws this edge as undirected edge onto the given {@code Graphics2D}
	 * object.
	 * 
	 * @param g2d
	 *            the {@code Graphics2D} object to draw on.
	 * @param originX
	 *            the x coordinate of the origin VisualVertex.
	 * @param originY
	 *            the y coordinate of the origin VisualVertex.
	 * @param targetX
	 *            the x coordinate of the target VisualVertex.
	 * @param targetY
	 *            the y coordinate of the target VisualVertex.
	 */
	public abstract void drawUndirected(Graphics2D g2d, int originX, int originY, int targetX, int targetY);

	/**
	 * Draws this edge as directed edge onto the given {@code Graphics2D}
	 * object.
	 * 
	 * @param g2d
	 *            the {@code Graphics2D} object to draw on.
	 * @param originX
	 *            the x coordinate of the origin VisualVertex.
	 * @param originY
	 *            the y coordinate of the origin VisualVertex.
	 * @param targetX
	 *            the x coordinate of the target VisualVertex.
	 * @param targetY
	 *            the y coordinate of the target VisualVertex.
	 */
	public abstract void drawDirected(Graphics2D g2d, int originX, int originY, int targetX, int targetY);

	/**
	 * Generates an Edge from this one where target and origin vertices are
	 * swapped.
	 * 
	 * @return the opposed edge.
	 */
	public abstract VisualEdge generateOpposedEdge();

	public boolean hasOpposingEdge() {
		return hasOpposingEdge;
	}

	void setHasOpposingEdge(boolean hasOpposingEdge) {
		this.hasOpposingEdge = hasOpposingEdge;
	}

	public boolean isOpposingEdge() {
		return isOpposingEdge;
	}

	void setIsOpposingEdge(boolean isOpposingEdge) {
		this.isOpposingEdge = isOpposingEdge;
	}

	@Override
	public VisualVertex getOriginVertex() {
		return (VisualVertex) super.getOriginVertex();
	}

	@Override
	public VisualVertex getTargetVertex() {
		return (VisualVertex) super.getTargetVertex();
	}

}
