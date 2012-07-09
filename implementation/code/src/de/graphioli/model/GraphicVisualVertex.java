package de.graphioli.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import de.graphioli.utils.JarParser;

/**
 * This class represents a {@link VisualVertex} with a custom icon.
 * 
 * @author Graphioli
 */
public class GraphicVisualVertex extends VisualVertex {

	/**
	 * Logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(GraphicVisualVertex.class.getName());

	/**
	 * The image of the GraphicVisualVertex
	 */
	private transient BufferedImage image;

	/**
	 * The name of the game (used for reloading the image)
	 */
	private String gameName;
	
	/**
	 * The file name of the image to load (used for reloading the images)
	 */
	private String fileName;

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
	 * {@inheritDoc}
	 */
	@Override
	protected boolean draw(Graphics2D g2d) {
		if (image != null) {
			g2d.drawImage(image, 0, 0, VisualVertex.PIXELS_PER_SIDE, VisualVertex.PIXELS_PER_SIDE, null);
		} else {
			LOG.warning("No image of the GraphicVisualVertex is set. Drawing red 'X' as place holder.");
			g2d.setColor(Color.RED);
			g2d.drawLine(0, 0, PIXELS_PER_SIDE, PIXELS_PER_SIDE);
			g2d.drawLine(PIXELS_PER_SIDE, 0, 0, PIXELS_PER_SIDE);
			return false;
		}
		return true;
	}

	/**
	 * Loads a new BufferedImage from a given file
	 * 
	 * @param gameName
	 *            The name of the game used to generate the path to the file
	 * @param fileName
	 *            The file name of the image
	 * @return The new image that was loaded
	 */
	protected final BufferedImage loadBufferedImage(String gameName, String fileName) {
		BufferedImage newImage = null;
		InputStream imageInputStream = JarParser.getFileAsInputStream(gameName, fileName);

		// Try creating buffered image from path
		try {
			newImage = ImageIO.read(imageInputStream);
		} catch (IllegalArgumentException e) {
			LOG.severe("Illegal Argument: '" + imageInputStream + "'.");
			return null;
		} catch (IOException e) {
			LOG.severe("Path does not exist: '" + imageInputStream + "'.");
			return null;
		}
		this.gameName = gameName;
		this.fileName = fileName;
		return newImage;
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
	 * Reloads the image to the BufferedImage.
	 */
	@Override
	protected void onReload() {
		if (!this.fileName.isEmpty() && !this.gameName.isEmpty()) {
			InputStream imageInputStream = JarParser.getFileAsInputStream(gameName, fileName);

			// Try creating buffered image from path
			try {
				this.image = ImageIO.read(imageInputStream);
			} catch (IllegalArgumentException e) {
				LOG.severe("Illegal Argument: '" + imageInputStream + "'.");
			} catch (IOException e) {
				LOG.severe("Path does not exist: '" + imageInputStream + "'.");
			}
		}
	}

}
