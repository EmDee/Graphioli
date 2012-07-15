package game;

import de.graphioli.model.GridPoint;

import java.awt.Color;

/**
 * This class represents a special vertex for the GraphColoring game used as
 * a button.
 * 
 * @author Team Graphioli
 */
public class GraphColoringButtonVertex extends GraphColoringVertex {

	/**
	 * Serialization UID.
	 */
	private static final long serialVersionUID = -7148091699613322445L;

	/**
	 * Constructs a GraphColoringButtonVertex with the given GridPoint.
	 * 
	 * @param gridPoint
	 *            the GridPoint this vertex is located on.
	 */
	public GraphColoringButtonVertex(GridPoint gridPoint) {
		super(gridPoint);
	}

	/**
	 * (Un-)highlights this button vertex.
	 * 
	 * @param highlighted
	 *            the new {@code highlighted} value.
	 */
	public void setHighlighted(boolean highlighted) {
		setStrokeColor(highlighted ? Color.YELLOW : Color.BLACK);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void init() {
		super.init();
		this.setHighlighted(false);
		setStrokeWeight(2);
	}

}
