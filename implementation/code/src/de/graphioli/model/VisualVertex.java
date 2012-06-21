package de.graphioli.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class VisualVertex extends Vertex {
	private GridPoint gridPoint;
	protected BufferedImage bufferedImage;
	
	public VisualVertex(GridPoint gridPoint) {
		this.gridPoint = gridPoint;
	}
	
	protected abstract boolean draw(Graphics2D graphics);

	public final boolean update() {
		return true;
	}
	
	public final BufferedImage getBufferedImage() {
		return this.bufferedImage;
	}

	/**
	 * @return the gridPoint
	 */
	public GridPoint getGridPoint() {
		return gridPoint;
	}

	/**
	 * @param gridPoint the gridPoint to set
	 */
	public void setGridPoint(GridPoint gridPoint) {
		this.gridPoint = gridPoint;
	}
}
