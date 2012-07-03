package de.graphioli.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 * This class represents a simple {@link VisualVertex} with predefined
 * attributes.
 * 
 * @author Graphioli
 */
public class SimpleVisualVertex extends VisualVertex {

	private Color fillColor;
	private Color strokeColor;
	private int strokeWeight;

	private transient Stroke stroke;

	/**
	 * Creates an instance of this {@code SimpleVisualVertex} with a given
	 * {@link GridPoint}.
	 * 
	 * @param gridPoint
	 *            {@link GridPoint}, on which this vertex is located on.
	 */
	public SimpleVisualVertex(GridPoint gridPoint) {
		super(gridPoint);
	}

	/**
	 * Sets the fill color of this {@code SimpleVisualVertex} to {@code WHITE},
	 * the stroke color to {@code BLACK} and the stroke weight to {@code 1}.
	 */
	@Override
	protected void init() {
		this.fillColor = Color.WHITE;
		this.strokeColor = Color.BLACK;
		this.strokeWeight = 1;
		this.stroke = new BasicStroke(this.strokeWeight);
	}

	/**
	 * Draws this {@code SimpleVisualVertex} according to its fill color, stroke
	 * color and stroke weight.
	 * 
	 * @param graphics
	 *            given graphics to draw
	 * @return <code>true</code> when the drawing is doen
	 */
	@Override
	protected boolean draw(Graphics2D graphics) {
		graphics.setStroke(this.stroke);
		graphics.setColor(this.fillColor);
		graphics.fillOval(0, 0, PIXELS_PER_SIDE - 1, PIXELS_PER_SIDE - 1);

		// Draw Stroke
		graphics.setColor(this.strokeColor);
		graphics.drawOval(0 + (this.strokeWeight - 1) / 2, 0 + (this.strokeWeight - 1) / 2, (PIXELS_PER_SIDE - 1)
				- (this.strokeWeight - 1), (PIXELS_PER_SIDE - 1) - (this.strokeWeight - 1));

		return true;
	}

	/**
	 * @return the fillColor
	 */
	public Color getFillColor() {
		return this.fillColor;
	}

	/**
	 * @param fillColor
	 *            the fillColor to set
	 */
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
		this.update();
	}

	/**
	 * @return the strokeColor
	 */
	public Color getStrokeColor() {
		return this.strokeColor;
	}

	/**
	 * @param strokeColor
	 *            the strokeColor to set
	 */
	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
		this.update();
	}

	/**
	 * @return the strokeWeight
	 */
	public int getStrokeWeight() {
		return (this.strokeWeight + 1) / 2;
	}

	/**
	 * @param strokeWeight
	 *            the strokeWeight to set
	 */
	public void setStrokeWeight(int strokeWeight) {
		// Stroke Weight has to be an uneven number internally to prevent edges
		// being cut off.
		this.strokeWeight = 2 * strokeWeight - 1;
		this.stroke = new BasicStroke(this.strokeWeight);
		this.update();
	}

}
