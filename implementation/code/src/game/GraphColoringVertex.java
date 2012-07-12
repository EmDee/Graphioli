package game;


import de.graphioli.model.GridPoint;
import de.graphioli.model.SimpleVisualVertex;
import java.awt.Color;



/**
 * This class represents a vertex for the GraphColoring game.
 * 
 * @author Team Graphioli
 */
public class GraphColoringVertex extends SimpleVisualVertex {

	/**
	 * The color ID of this vertex.
	 */
	private int colorID;

	/**
	 * Constructs a GraphColoringVertex with the given GridPoint.
	 * 
	 * @param gridPoint
	 *            the GridPoint this vertex is located on.
	 */
	public GraphColoringVertex(GridPoint gridPoint) {
		super(gridPoint);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void init() {
		super.init();
		setColorID(GraphColoring.CLRID_BLANK);
	}

	/**
	 * Returns the color ID.
	 * 
	 * @return the color ID:
	 */
	public int getColorID() {
		return this.colorID;
	}

	/**
	 * Sets the color ID of this vertex and the color accordingly.
	 * 
	 * @param id
	 *            the new color ID.
	 */
	public void setColorID(int id) {
		this.colorID = id;
		switch (this.colorID) {
			case GraphColoring.CLRID_RED:
				setFillColor(Color.RED);
				break;
			case GraphColoring.CLRID_GREEN:
				setFillColor(Color.GREEN);
				break;
			case GraphColoring.CLRID_BLUE:
				setFillColor(Color.BLUE);
				break;
			case GraphColoring.CLRID_YELLOW:
				setFillColor(Color.YELLOW);
				break;
			default:
				setFillColor(Color.WHITE);
		}
	}
}
