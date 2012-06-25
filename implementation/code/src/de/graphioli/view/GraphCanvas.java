package de.graphioli.view;

import de.graphioli.model.Edge;
import de.graphioli.model.Graph;
import de.graphioli.model.Vertex;
import de.graphioli.model.VisualEdge;
import de.graphioli.model.VisualVertex;

import java.awt.Graphics;
import java.awt.Graphics2D;

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
		this.parentGameWindow = parentGameWindow;
		visualGrid = new VisualGrid(parentGameWindow);
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

		Graph graph = parentGameWindow.getViewManager().getGameManager().getGameBoard().getGraph();
		int horizontalGridScale = (int) (this.getHeight() / (visualGrid.getGrid().getVerticalGridPoints() + 1));
		int verticalGridScale = (int) (this.getWidth() / (visualGrid.getGrid().getHorizontalGridPoints() + 1));

		for (Vertex v : graph.getVertices()) {
			/*
			 * on grid
			 */
			VisualVertex vertex = (VisualVertex) v;
			g2d.drawImage(
					vertex.getBufferedImage(),
					(vertex.getGridPoint().getPositionX() * horizontalGridScale)
							- (visualGrid.getVisualVertexSize() / 2),
					(vertex.getGridPoint().getPositionY() * verticalGridScale)
							- (visualGrid.getVisualVertexSize() / 2), visualGrid.getVisualVertexSize(),
					visualGrid.getVisualVertexSize(), null);

		}

		for (Edge edge : graph.getEdges()) {
			/*
			 * Size of the VisualEdge according to the grid
			 */
			int xsize = 0;
			int ysize = 0;

			/*
			 * Positioning of the VisualEdge on grid
			 */
			int xpos = 0;
			int ypos = 0;

			VisualVertex originVertex = (VisualVertex) edge.getOriginVertex();
			VisualVertex targetVertex = (VisualVertex) edge.getTargetVertex();

			if ((originVertex.getGridPoint().getPositionX() > targetVertex.getGridPoint().getPositionX())) {
				xsize = (originVertex.getGridPoint().getPositionX() - targetVertex.getGridPoint()
						.getPositionX()) * horizontalGridScale;
				xpos = (targetVertex.getGridPoint().getPositionX() * horizontalGridScale);
			} else {
				xsize = (targetVertex.getGridPoint().getPositionX() - originVertex.getGridPoint()
						.getPositionX()) * horizontalGridScale;
				xpos = (originVertex.getGridPoint().getPositionX() * horizontalGridScale);
			}
			if ((originVertex.getGridPoint().getPositionY() > targetVertex.getGridPoint().getPositionY())) {
				ysize = (originVertex.getGridPoint().getPositionY() - targetVertex.getGridPoint()
						.getPositionY()) * verticalGridScale;
				xpos = (targetVertex.getGridPoint().getPositionY() * verticalGridScale);
			} else {
				ysize = (targetVertex.getGridPoint().getPositionY() - originVertex.getGridPoint()
						.getPositionY()) * verticalGridScale;
				xpos = (originVertex.getGridPoint().getPositionY() * verticalGridScale);
			}

			g2d.drawImage(((VisualEdge) edge).getBufferedImage(), xpos, ypos, xsize, ysize, null);

		}
	}
}
