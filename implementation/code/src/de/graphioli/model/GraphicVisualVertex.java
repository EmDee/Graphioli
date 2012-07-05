package de.graphioli.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class represents a {@link VisualVertex} with a custom icon.
 * 
 * @author Graphioli
 */
public class GraphicVisualVertex extends VisualVertex {

	/**
	 * The image of the GraphicVisualVertex
	 */
	private transient BufferedImage image;

	/**
	 * The path to and file name of the image to load
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
		// TODO Auto-generated constructor stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean draw(Graphics2D g2d) {
		if (image != null) {
			g2d.drawImage(image, 0, 0, VisualVertex.PIXELS_PER_SIDE, VisualVertex.PIXELS_PER_SIDE, null);
		} else {
			g2d.drawString("X", VisualVertex.PIXELS_PER_SIDE, VisualVertex.PIXELS_PER_SIDE);
			return false;
		}
		return true;
	}

	/**
	 * Loads a new BufferedImage from a given file
	 * 
	 * @param fileName
	 *            The path to the file that is loaded in the BufferedImage
	 * @return The new image that was loaded
	 */
	protected final BufferedImage loadBufferedImage(String fileName) {
		BufferedImage newImage = null;
		try {
			newImage = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		if (!this.fileName.isEmpty()) {
			try {
				this.image = ImageIO.read(new File(this.fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

