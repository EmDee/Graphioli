package de.graphioli.model;

import java.awt.Graphics2D;
import java.util.logging.Logger;

/**
 * This class represents an {@link Edge} with its visual representation.
 * 
 * @author Team Graphioli
 */
public abstract class VisualEdge extends Edge {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -5932235747243972058L;

	/**
	 * Logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(VisualEdge.class.getName());

	private boolean hasOpposingEdge;
	private boolean isOpposingEdge;

	/**
	 * Creates a new VisualEdge between the given vertices.
	 * 
	 * @param originVertex
	 *            the origin VisualVertex
	 * @param targetVertex
	 *            the target VisualVertex
	 */
	public VisualEdge(VisualVertex originVertex, VisualVertex targetVertex) {
		super(originVertex, targetVertex);
		this.hasOpposingEdge = false;
		this.isOpposingEdge = false;
	}

	/**
	 * Calculates an offset for the edge and then calls {@link VisualEdge#drawDirected(Graphics2D, int, int, int, int)
	 * with the adapted coordinates.
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
	 * @param offset the offset
	 */
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

	/**
	 * Calls {@link VisualEdge#drawUndirected(Graphics2D, int, int, int, int)}
	 * if this edge is not flagged as an opposing edge.
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
	public final void callDrawUndirected(Graphics2D g2d, int originX, int originY, int targetX, int targetY) {
		if (!this.isOpposingEdge) {
			this.drawUndirected(g2d, originX, originY, targetX, targetY);
		}
	}

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
	 * Generates an Edge from this one where target and origin vertices are
	 * swapped.
	 * 
	 * @return the opposed edge.
	 */
	public abstract VisualEdge generateOpposedEdge();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VisualVertex getOriginVertex() {
		return (VisualVertex) super.getOriginVertex();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VisualVertex getTargetVertex() {
		return (VisualVertex) super.getTargetVertex();
	}

	/**
	 * Returns {@code true} if this edge has an opposing edge.
	 * 
	 * @return {@code true} if this edge has an opposing edge.
	 */
	public boolean hasOpposingEdge() {
		return this.hasOpposingEdge;
	}

	/**
	 * Returns {@code true} if this edge is a opposing edge.
	 * 
	 * @return {@code true} if this edge is a opposing edge.
	 */
	public boolean isOpposingEdge() {
		return this.isOpposingEdge;
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
	protected abstract void onReload();

	/**
	 * Sets whether this edge has an opposing edge.
	 * 
	 * @param hasOpposingEdge
	 *            whether this edge has an opposing edge.
	 */
	void setHasOpposingEdge(boolean hasOpposingEdge) {
		this.hasOpposingEdge = hasOpposingEdge;
	}

	/**
	 * Sets whether this edge is an opposing edge.
	 * 
	 * @param isOpposingEdge
	 *            whether this edge is an opposing edge.
	 */
	void setIsOpposingEdge(boolean isOpposingEdge) {
		this.isOpposingEdge = isOpposingEdge;
	}

}
