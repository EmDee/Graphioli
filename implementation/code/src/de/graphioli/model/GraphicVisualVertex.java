package de.graphioli.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;




/**
 * This class represents a {@link VisualVertex} with a custom icon.
 * 
 * @author Graphioli
 */
public class GraphicVisualVertex extends VisualVertex {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 8176293229543732820L;

	/**
	 * Logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(GraphicVisualVertex.class.getName());

	/**
	 * The image of the GraphicVisualVertex.
	 */
	private transient BufferedImage image;
	/**
	 * Creates an instance of this {@link GraphicVisualVertex} with a given
	 * {@link GridPoint}.
	 * 
	 * @param gridPoint
	 *            given {@link GridPoint}, on which this vertex is located on.
	 */
	public GraphicVisualVertex(GridPoint gridPoint) {
		super(gridPoint);
	}

	/**
	 * Returns the image that represents this GraphicVisualVertex.
	 * 
	 * @return The image of this vertex
	 */
	public BufferedImage getImage() {
		return this.image;
	}


	/**
	 * Sets the image that represents this GraphicVisualVertex.
	 * 
	 * @param image
	 *            The new BufferedImage
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean setImage(BufferedImage image) {
		this.image = image;
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean draw(Graphics2D g2d) {
		if (this.image != null) {
			g2d.drawImage(this.image, 0, 0, VisualVertex.PIXELS_PER_SIDE, VisualVertex.PIXELS_PER_SIDE, null);
		} else {
			LOG.fine("No image of the GraphicVisualVertex is set. Drawing red 'X' as place holder.");
			g2d.setColor(Color.RED);
			g2d.drawLine(0, 0, PIXELS_PER_SIDE, PIXELS_PER_SIDE);
			g2d.drawLine(PIXELS_PER_SIDE, 0, 0, PIXELS_PER_SIDE);
			return false;
		}
		return true;
	}

	/**
	 * Dummy implementation. Graphics are not serialized, so you need to take care of it by yourself.
	 */
	@Override
	protected void onReload() {
		// Cannot reload the graphic.
		return;
	}

}
