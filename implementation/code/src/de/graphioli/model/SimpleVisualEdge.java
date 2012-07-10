package de.graphioli.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 * This class represents a simple, undirected edge with a stroke color and
 * weight.
 * 
 * @author Team Graphioli
 */
public class SimpleVisualEdge extends VisualEdge {
	
	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -1341477739023648491L;

	/**
	 * The ratio between the vertex size and the edge width.
	 */
	private static final int WIDTH_SCALE_RATIO = 25;
	
	/**
	 * The width of this edge.
	 */
	private static final int WIDTH_SCALE = Math.max(VisualVertex.PIXELS_PER_SIDE / WIDTH_SCALE_RATIO, 1);
	
	/**
	 * The length of the directed edge's arrow head lines.
	 */
	private static final int ARROW_HEAD_LENGTH = 10;

	/**
	 * The slope of the directed edge's arrow head.
	 */
	private static final double ARROW_HEAD_SLOPE = 1.25;

	/**
	 * The stroke used for this edge.
	 */
	private transient Stroke edgeStroke;

	/**
	 * This edge's stroke weight.
	 */
	private int strokeWeight;
	
	/**
	 * This edge's stroke color.
	 */
	private Color strokeColor;

	

	/**
	 * Creates a black, undirected edge between the given vertices.
	 * 
	 * @param vertexA
	 *            the origin VisualVertex.
	 * @param vertexB
	 *            the target VisualVertex.
	 */
	public SimpleVisualEdge(VisualVertex vertexA, VisualVertex vertexB) {
		super(vertexA, vertexB);
		this.strokeColor = Color.BLACK;
		this.strokeWeight = 1;
		this.edgeStroke = new BasicStroke((this.strokeWeight + 2) * WIDTH_SCALE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawDirected(Graphics2D g2d, int originX, int originY, int targetX, int targetY) {
		this.drawUndirected(g2d, originX, originY, targetX, targetY);
		this.drawArrowHead(g2d, originX, originY, targetX, targetY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawUndirected(Graphics2D g2d, int originX, int originY, int targetX, int targetY) {
		g2d.setColor(this.strokeColor);
		g2d.setStroke(this.edgeStroke);
		g2d.drawLine(originX, originY, targetX, targetY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VisualEdge generateOpposedEdge() {
		SimpleVisualEdge newEdge = new SimpleVisualEdge(this.getTargetVertex(), this.getOriginVertex());
		newEdge.setStrokeColor(this.strokeColor);
		newEdge.setStrokeWeight(this.strokeWeight);
		return newEdge;
	}

	/**
	 * Returns the stroke color.
	 * 
	 * @return the strokeColor
	 */
	public Color getStrokeColor() {
		return this.strokeColor;
	}

	/**
	 * Returns the stroke weight.
	 * 
	 * @return the stroke weight
	 */
	public int getStrokeWeight() {
		return this.strokeWeight;
	}

	/**
	 * Sets the stroke color.
	 * 
	 * @param strokeColor
	 *            the strokeColor to set
	 */
	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}

	/**
	 * Sets the stroke weight.
	 * 
	 * @param strokeWeight
	 *            the stroke weight to set
	 */
	public void setStrokeWeight(int strokeWeight) {
		this.strokeWeight = strokeWeight;
		this.edgeStroke = new BasicStroke((this.strokeWeight + 2) * WIDTH_SCALE);
	}

	private void drawArrowHead(Graphics2D g2d, int originX, int originY, int targetX, int targetY) {
		

		int distanceX = targetX - originX;
		int distanceY = targetY - originY;
		double angleArrowOne;
		double anglelArrowTwo;
		double slopeAngle;
		slopeAngle = Math.atan2(distanceY, distanceX);
		angleArrowOne = slopeAngle +  Math.PI * ARROW_HEAD_SLOPE;
		anglelArrowTwo = slopeAngle - Math.PI * ARROW_HEAD_SLOPE;

		// for both arrows
		double arrowLength = Math.sqrt(distanceX * distanceX + distanceY * distanceY)
				- VisualVertex.PIXELS_PER_SIDE
				/ 2;

		int originArrowX = (int) Math.round(Math.cos(slopeAngle) * arrowLength + originX);
		int originArrowY = (int) Math.round(Math.sin(slopeAngle) * arrowLength + originY);

		// for arrow one
		int targetArrowXOne = (int) Math.round(Math.cos(angleArrowOne) * ARROW_HEAD_LENGTH);
		targetArrowXOne += originArrowX;
		int targetArrowYOne = (int) Math.round(Math.sin(angleArrowOne) * ARROW_HEAD_LENGTH);
		targetArrowYOne += originArrowY;
		// for arrow two
		int targetArrowXTwo = (int) Math.round(Math.cos(anglelArrowTwo) * ARROW_HEAD_LENGTH);
		targetArrowXTwo += originArrowX;
		int targetArrowYTwo = (int) Math.round(Math.sin(anglelArrowTwo) * ARROW_HEAD_LENGTH);
		targetArrowYTwo += originArrowY;

		g2d.drawLine(originArrowX, originArrowY, targetArrowXOne, targetArrowYOne);
		g2d.drawLine(originArrowX, originArrowY, targetArrowXTwo, targetArrowYTwo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onReload() {
		this.edgeStroke = new BasicStroke((this.strokeWeight + 2) * WIDTH_SCALE);
	}

}
