package de.graphioli.view;

import de.graphioli.model.Edge;
import de.graphioli.model.Graph;
import de.graphioli.model.Vertex;
import de.graphioli.model.VisualEdge;
import de.graphioli.model.VisualVertex;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Logger;

import javax.swing.JPanel;

/**
 * This class represents the canvas where the {@link Graph} will be drawn on.
 * 
 * @author Graphioli
 * 
 */
public class GraphCanvas extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Logging instance
	 */
	private final static Logger LOG = Logger.getLogger(GraphCanvas.class
			.getName());

	/**
	 * The parent {@link GameWindow} associated with this @ GraphCanvas}
	 */
	private GameWindow parentGameWindow;

	/**
	 * The {@link VisualGrid} associated with this {@link GraphCanvas}
	 */
	private VisualGrid visualGrid;

	/**
	 * Creates a {@link GraphCanvas} and registers its parent {@link GameWindow}
	 * 
	 * @param parentGameWindow
	 *            The {@link GameWindow} that contains this {@link GraphCanvas}
	 */
	public GraphCanvas(GameWindow parentGameWindow) {
		LOG.fine("GraphCanvas instantiated");
		this.parentGameWindow = parentGameWindow;
		this.setSize(500, 300);
		this.visualGrid = new VisualGrid(this, this.parentGameWindow);
		this.addMouseListener(visualGrid);
	}

	/**
	 * Updates and redraws the {@link GraphCanvas}.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean updateCanvas() {
		paintComponent(null);
		return true;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		Graph graph = this.parentGameWindow.getViewManager().getGameManager().getGameBoard().getGraph();

		int horizontalGridScale = this.visualGrid.getHorizontalGridScale();
		int verticalGridScale = this.visualGrid.getVerticalGridScale();
		
		for (Vertex v : graph.getVertices()) {
			/*
			 * on grid
			 */
			VisualVertex vertex = (VisualVertex) v;
			g2d.drawImage(
					vertex.getBufferedImage(),
					((1 + vertex.getGridPoint().getPositionX()) * horizontalGridScale)
							- (this.visualGrid.getVisualVertexSize() / 2),
					((1 + vertex.getGridPoint().getPositionY()) * verticalGridScale)
							- (this.visualGrid.getVisualVertexSize() / 2), 
							VisualVertex.PIXELS_PER_SIDE,
							VisualVertex.PIXELS_PER_SIDE, null);

		}
		/*
		// Drawing edges of the graph
		for(Edge edge: graph.getEdges()) {
			
			// Size of the VisualEdge
			int xsize = 0;
			int ysize = 0;

			
			// Positioning of the VisualEdge
			int xpos = 0;
			int ypos = 0;

			VisualVertex originVertex = (VisualVertex) edge.getOriginVertex();
			VisualVertex targetVertex = (VisualVertex) edge.getTargetVertex();
			
			if((originVertex.getGridPoint().getPositionX() > targetVertex.getGridPoint().getPositionX())) {
				xsize = (originVertex.getGridPoint().getPositionX() - targetVertex.getGridPoint().getPositionX()) * horizontalGridScale;
				xpos = ((1 + targetVertex.getGridPoint().getPositionX()) * horizontalGridScale);
			} else {
				xsize = (targetVertex.getGridPoint().getPositionX() - originVertex.getGridPoint().getPositionX()) * horizontalGridScale;
				xpos = ((1 + originVertex.getGridPoint().getPositionX()) * horizontalGridScale);
			}
			if((originVertex.getGridPoint().getPositionY() > targetVertex.getGridPoint().getPositionY())) {
				ysize = (originVertex.getGridPoint().getPositionY() - targetVertex.getGridPoint().getPositionY()) * verticalGridScale;
				xpos = ((1 + targetVertex.getGridPoint().getPositionY()) * verticalGridScale);
			} else {
				ysize = (targetVertex.getGridPoint().getPositionY() - originVertex.getGridPoint().getPositionY()) * verticalGridScale;
				xpos = ((1 + originVertex.getGridPoint().getPositionY()) * verticalGridScale);
			}
			
			// Draw VisualEdge
			g2d.drawImage(((VisualEdge) edge).getBufferedImage(), xpos, ypos, xsize, ysize, null); 

		} */
	}
}
