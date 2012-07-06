package de.graphioli.view;

import de.graphioli.model.Edge;
import de.graphioli.model.GameBoard;
import de.graphioli.model.Graph;
import de.graphioli.model.Vertex;
import de.graphioli.model.VisualEdge;
import de.graphioli.model.VisualVertex;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.logging.Logger;

import javax.swing.JPanel;

/**
 * This class represents the canvas where the {@link Graph} will be drawn on.
 * 
 * @author Graphioli
 */
public class GraphCanvas extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(GraphCanvas.class.getName());

	/**
	 * The stroke used for drawing the grid.
	 */
	private final Stroke gridStroke;


	/**
	 * The parent {@link GameWindow} associated with this @ GraphCanvas}.
	 */
	private GameWindow parentGameWindow;

	/**
	 * The {@link VisualGrid} this canvas uses.
	 */
	private VisualGrid visualGrid;

	/**
	 * The {@link VisualGrid} associated with this {@link GraphCanvas}. private
	 * VisualGrid visualGrid; /** Creates a {@link GraphCanvas} and registers
	 * its parent {@link GameWindow}.
	 * 
	 * @param parentGameWindow
	 *            The {@link GameWindow} that contains this {@link GraphCanvas}
	 */
	public GraphCanvas(GameWindow parentGameWindow) {
		LOG.fine("GraphCanvas instantiated");
		this.parentGameWindow = parentGameWindow;
		this.gridStroke = new BasicStroke(1);
	}

	/**
	 * Updates and redraws the {@link GraphCanvas}.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean updateCanvas() {
		this.updateUI();
		return true;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		GameBoard board = this.parentGameWindow.getViewManager().getGameManager().getGameBoard();
		Graph graph = board.getGraph();

		/*
		 * visualGrid in GameWindow after Canvas initiated but paintComponent
		 * already in canvas constructor used --> can't set next line in canvas
		 * constructor...
		 */
		this.visualGrid = this.parentGameWindow.getVisualGrid();
		int gridScale = this.visualGrid.getGridScale();

		// Drawing grid lines
		g2d.setStroke(this.gridStroke);
		this.visualGrid.draw(g2d);

		// Drawing edges of the graph from the canvas (PROTOTYPE)
		if (board.isDirectedGraph()) {
			// Draw directed
			for (Edge edge : graph.getEdges()) {
				VisualVertex originVertex = (VisualVertex) edge.getOriginVertex();
				VisualVertex targetVertex = (VisualVertex) edge.getTargetVertex();

				((VisualEdge) edge).drawDirected(g2d, (originVertex.getGridPoint().getPositionX() + 1) * gridScale,
						(originVertex.getGridPoint().getPositionY() + 1) * gridScale, (targetVertex.getGridPoint()
								.getPositionX() + 1) * gridScale, (targetVertex.getGridPoint().getPositionY() + 1)
								* gridScale);
			}
		} else {
			// Draw Undirected
			for (Edge edge : graph.getEdges()) {
				VisualVertex originVertex = (VisualVertex) edge.getOriginVertex();
				VisualVertex targetVertex = (VisualVertex) edge.getTargetVertex();

				((VisualEdge) edge).drawUndirected(g2d, (originVertex.getGridPoint().getPositionX() + 1) * gridScale,
						(originVertex.getGridPoint().getPositionY() + 1) * gridScale, (targetVertex.getGridPoint()
								.getPositionX() + 1) * gridScale, (targetVertex.getGridPoint().getPositionY() + 1)
								* gridScale);
			}
		}

		// Drawing vertices
		for (Vertex v : graph.getVertices()) {

			VisualVertex vertex = (VisualVertex) v;
			g2d.drawImage(vertex.getBufferedImage(), ((1 + vertex.getGridPoint().getPositionX()) * gridScale)
					- (VisualVertex.PIXELS_PER_SIDE / 2), ((1 + vertex.getGridPoint().getPositionY()) * gridScale)
					- (VisualVertex.PIXELS_PER_SIDE / 2), VisualVertex.PIXELS_PER_SIDE, VisualVertex.PIXELS_PER_SIDE,
					null);

		}
	}
}
