package de.graphioli.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 * This class represents a simple, undirected edge with a stroke color and weight.
 * 
 * @author Team Graphioli
 * 
 */
public class SimpleVisualEdge extends VisualEdge {

	private transient Stroke edgeStroke;

	private int strokeWeight;
	private Color strokeColor;
	
	private static final int WIDTH_SCALE = Math.max(VisualVertex.PIXELS_PER_SIDE / 25, 1); 

	/**
	 * Creates a black, undirected edge between the given vertices.
	 * 
	 * @param vertexA
	 *            the origin vertex.
	 * @param vertexB
	 *            the target vertex.
	 */
	public SimpleVisualEdge(Vertex vertexA, Vertex vertexB) {
		super(vertexA, vertexB);
		this.strokeColor = Color.BLACK;
		this.strokeWeight = 1;
		this.edgeStroke = new BasicStroke((this.strokeWeight + 2) * WIDTH_SCALE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawUndirected(Graphics2D g2d, int originX, int originY, int targetX, int targetY) {
		if (this.isVisible()) {
			g2d.setColor(this.strokeColor);
			g2d.setStroke(this.edgeStroke);
			g2d.drawLine(originX, originY, targetX, targetY);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawDirected(Graphics2D g2d, int originX, int originY, int targetX, int targetY) {
		if (this.isVisible()) {
			this.drawUndirected(g2d, originX, originY, targetX, targetY);
			
			int arrowEndLength = 10;
			
			int distanceX = targetX - originX;
			int distanceY = targetY - originY;
			double winkelArrowOne;
			double winkelArrowTwo;
			double steigungswinkel;
			steigungswinkel = Math.atan2(distanceY, distanceX);
			winkelArrowOne = steigungswinkel + 5 * Math.PI / 4;
			winkelArrowTwo = steigungswinkel - 5 * Math.PI / 4;

			// for both arrows 
			double arrowLength = Math.sqrt(distanceX * distanceX + distanceY * distanceY) - VisualVertex.PIXELS_PER_SIDE / 2;

			int originArrowX = (int) Math.round(((Math.cos(steigungswinkel) * arrowLength + originX)));
			int originArrowY = (int) Math.round(((Math.sin(steigungswinkel) * arrowLength + originY)));
			
			// for arrow one
			int targetArrowXOne = (int) Math.round((Math.cos(winkelArrowOne) * arrowEndLength));
			targetArrowXOne += originArrowX;
			int targetArrowYOne = (int) Math.round((Math.sin(winkelArrowOne) * arrowEndLength));
			targetArrowYOne += originArrowY;
			// for arrow two
			int targetArrowXTwo = (int) Math.round((Math.cos(winkelArrowTwo) * arrowEndLength));
			targetArrowXTwo += originArrowX;
			int targetArrowYTwo = (int) Math.round((Math.sin(winkelArrowTwo) * arrowEndLength));
			targetArrowYTwo += originArrowY;
			
			g2d.drawLine(originArrowX, originArrowY, targetArrowXOne, targetArrowYOne);
			g2d.drawLine(originArrowX, originArrowY, targetArrowXTwo, targetArrowYTwo);
		}
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
	 * Sets the stroke weight.
	 * 
	 * @param strokeWeight
	 *            the stroke weight to set
	 */
	public void setStrokeWeight(int strokeWeight) {
		this.strokeWeight = strokeWeight;
		this.edgeStroke = new BasicStroke((this.strokeWeight + 2) * WIDTH_SCALE);
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
	 * Sets the stroke color.
	 * 
	 * @param strokeColor
	 *            the strokeColor to set
	 */
	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onReload() {
		this.edgeStroke = new BasicStroke((this.strokeWeight + 2) * WIDTH_SCALE);	
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


}
