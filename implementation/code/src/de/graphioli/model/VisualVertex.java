package de.graphioli.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

/**
 * This class represents a {@link Vertex} with its graphical representation.
 * 
 * @author Graphioli
 */
public abstract class VisualVertex extends Vertex {

	private static final Logger LOG = Logger.getLogger(VisualVertex.class.getName());

	private GridPoint gridPoint;
	protected BufferedImage bufferedImage;

	/**
	 * The length of a side in pixels.
	 */
	public static final int PIXELS_PER_SIDE = 25;

	/**
	 * The default color value for a {@link VisualVertex}.
	 */
	private static final int DEFAULT_COLOR = 255;

	/**
	 * Constructs a {@code VisualVertex} with the given {@link GridPoint},
	 * initializes and updates it.
	 * 
	 * @param gridPoint
	 *            the {@link GridPoint} for the new vertex.
	 */
	public VisualVertex(GridPoint gridPoint) {
		this.gridPoint = gridPoint;
		this.bufferedImage = new BufferedImage(PIXELS_PER_SIDE, PIXELS_PER_SIDE, BufferedImage.TYPE_4BYTE_ABGR);
		init();
		update();
	}

	/**
	 * Initialize this {@code VisualVertex}.
	 */
	protected void init() {
	}

	/**
	 * Draws the visualization of this {@code VisualVertex} onto the given
	 * {@link Graphics2D} instance.
	 * 
	 * @param graphics
	 *            the graphic to draw on.
	 * @return {@code true} when drawing was successful.
	 */
	protected abstract boolean draw(Graphics2D graphics);

	/**
	 * Recreates the buffered image of this {@code VisualVertex}. Has to be
	 * called to change the visualization.
	 * 
	 * @return {@code true} when updating was successful.
	 */
	protected final boolean update() {

		LOG.finer("Updating VisualVertex at position " + getGridPoint() + ".");

		clearBufferedImage();
		Graphics2D g2d = this.bufferedImage.createGraphics();

		// Use anti-aliasing
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		return this.draw(g2d);
	}

	/**
	 * Returns the graphic representation of this {@code VisualVertex}.
	 * 
	 * @return the graphic representation.
	 */
	public final BufferedImage getBufferedImage() {

		return this.bufferedImage;
	}

	/**
	 * Returns the {@link GridPoint} of this {@code VisualVertex}.
	 * 
	 * @return the grid point.
	 */
	public GridPoint getGridPoint() {
		return this.gridPoint;
	}

	/**
	 * @param gridPoint
	 *            the gridPoint to set
	 */
	public void setGridPoint(GridPoint gridPoint) {
		// TODO: Take out this method?
		LOG.warning("Resetting the position of a VisualVertex from "
				+ this.gridPoint
				+ " to "
				+ gridPoint
				+ ". This might lead to inconsistencies!");
		this.gridPoint = gridPoint;
	}

	/**
	 * Resets the buffered image of this {@code VisualVertex} to a completely
	 * transparent state.
	 */
	private void clearBufferedImage() {
		Graphics2D g2d = this.bufferedImage.createGraphics();
		g2d.setBackground(new Color(VisualVertex.DEFAULT_COLOR, VisualVertex.DEFAULT_COLOR, VisualVertex.DEFAULT_COLOR,
				0));
		g2d.clearRect(0, 0, PIXELS_PER_SIDE, PIXELS_PER_SIDE);
	}
}
