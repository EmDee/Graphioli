package de.graphioli.model;

import java.awt.Graphics2D;

/**
 * This class represents a {@link VisualVertex} with a custom icon.
 * 
 * @author Graphioli
 */
public class GraphicVisualVertex extends VisualVertex {

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

	@Override
	protected boolean draw(Graphics2D graphics) {
		// TODO Auto-generated method stub
		return false;
	}

}
