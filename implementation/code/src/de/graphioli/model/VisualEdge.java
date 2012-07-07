package de.graphioli.model;

import java.awt.Graphics2D;
import java.util.logging.Logger;

/**
 * This class represents an {@link Edge} with its visual representation.
 * 
 * @author Graphioli
 */
public abstract class VisualEdge extends Edge {

	/**
	 * Logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(VisualEdge.class.getName());

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

	public final void callDrawDirected(Graphics2D g2d, int originX, int originY, int targetX, int targetY, int offset) {

		double xOffset = 0;
		double yOffset = 0;
		final int vecX = targetX - originX;
		final int vecY = targetY - originY;

		if (this.isOpposingEdge || this.hasOpposingEdge) {
			// Calculating the orthogonal offset

			if (offset == 0 || (vecX == 0 && vecY == 0)) {
				LOG.warning("Incorrect call of drawDirectedWithOffset");
				return;
			}

			xOffset = Math.sqrt(offset * offset * vecY * vecY / (vecX * vecX + vecY * vecY));
			yOffset = Math.sqrt(offset * offset - xOffset * xOffset);

			xOffset *= Math.signum(vecY);
			yOffset *= Math.signum(vecX) * -1;

		}

		this.drawDirected(g2d, originX + (int) xOffset, originY + (int) yOffset, targetX + (int) xOffset, targetY
				+ (int) yOffset);

	}

	public final void callDrawUndirected(Graphics2D g2d, int originX, int originY, int targetX, int targetY) {
		if (!this.isOpposingEdge) {
			this.drawUndirected(g2d, originX, originY, targetX, targetY);
		}
	}

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
