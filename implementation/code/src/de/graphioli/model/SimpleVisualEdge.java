package de.graphioli.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 * This class cepresents a simple, undirected edge with a stroke color and weight.
 * 
 * @author Team Graphioli
 * 
 */
public class SimpleVisualEdge extends VisualEdge {

	private transient Stroke edgeStroke;

	private int strokeWeight;
	private Color strokeColor;

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
		this.edgeStroke = new BasicStroke(this.strokeWeight + 2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(Graphics2D g2d, int originX, int originY, int targetX, int targetY) {
		if (this.isVisible()) {
			g2d.setColor(this.strokeColor);
			g2d.setStroke(this.edgeStroke);
			g2d.drawLine(originX, originY, targetX, targetY);
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
		this.edgeStroke = new BasicStroke(this.strokeWeight + 2);
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
		this.edgeStroke = new BasicStroke(this.strokeWeight + 2);	
	}


}
