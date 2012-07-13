package de.graphioli.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

/**
 * This class represents a {@link Vertex} with its graphical representation.
 * 
 * @author Team Graphioli
 */
public abstract class VisualVertex extends Vertex {

	
	/**
	 * The length of a side in pixels.
	 */
	public static final int PIXELS_PER_SIDE = 25;
	
	
	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -2537708216840445130L;

	
	/**
	 * Log instance.
	 */
	private static final Logger LOG = Logger.getLogger(VisualVertex.class.getName());
	
	/**
	 * The image cache of this visual vertex.
	 */
	protected transient BufferedImage bufferedImage;
	
	/**
	 * The grid point this vertex is placed on.
	 */
	private GridPoint gridPoint;

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
	 * Recreates the fields, that are not serialized.
	 */
	public void reload() {
		this.bufferedImage = new BufferedImage(PIXELS_PER_SIDE, PIXELS_PER_SIDE, BufferedImage.TYPE_4BYTE_ABGR);
		this.onReload();
		this.update();
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
	 * Recreates the buffered image of this {@code VisualVertex}. Has to be
	 * called to change the visualization.
	 * 
	 * @return {@code true} when updating was successful.
	 */
	public final boolean update() {

		LOG.finer("Updating VisualVertex at position " + this.getGridPoint() + ".");
		
		clearBufferedImage();
		
		
		Graphics2D g2d = this.bufferedImage.createGraphics();

		// Use anti-aliasing
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		return this.draw(g2d);
	}

	/**
	 * Resets the buffered image of this {@code VisualVertex} to a completely
	 * transparent state.
	 */
	private void clearBufferedImage() {
		Graphics2D g2d = this.bufferedImage.createGraphics();
		g2d.setBackground(new Color(255, 255, 255, 0));
		g2d.clearRect(0, 0, PIXELS_PER_SIDE, PIXELS_PER_SIDE);
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
	 * Initialize this {@code VisualVertex}.
	 * Call {@code super()} when overriding!
	 */
	protected void init() {
		this.bufferedImage = new BufferedImage(PIXELS_PER_SIDE, PIXELS_PER_SIDE, BufferedImage.TYPE_4BYTE_ABGR);
	}

	/**
	 * Implement this method to recreates the fields, that are not serialized.
	 */
	protected abstract void onReload();
}
