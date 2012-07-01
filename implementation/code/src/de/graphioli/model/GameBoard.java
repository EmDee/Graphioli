package de.graphioli.model;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * This class represents the game board of a {@link Game}. The GameBoard
 * combines the logical {@link Graph} with the {@link Grid} and has access to
 * both of them.
 * 
 * @author Graphioli
 */
public class GameBoard {

	/**
	 * Logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(GameBoard.class.getName());

	/**
	 * Whether the {@link Graph} of this GameBoard is directed or not.
	 */
	private boolean isDirectedGraph;

	/**
	 * The {@link Graph} of this GameBoard.
	 */
	private Graph graph;

	/**
	 * The {@link Grid} of this GameBoard.
	 */
	private Grid grid;

	/**
	 * Creates a new {@link GameBoard} with the specified option whether the
	 * {@link Graph} of the GameBoard is directed or not.
	 * 
	 * @param isDirectedGraph
	 *            Whether the graph of the game is directed or not
	 * @param horizontalGridPoints
	 *            the width of this {@code GameBoard}.
	 * @param verticalGridPoints
	 *            the height of this {@code GameBoard}.
	 */
	public GameBoard(boolean isDirectedGraph, int horizontalGridPoints, int verticalGridPoints) {

		// Initialize GameBoard
		this.isDirectedGraph = isDirectedGraph;
		this.graph = new Graph();
		this.grid = new Grid(horizontalGridPoints, verticalGridPoints);

	}

	/**
	 * Adds the given {@link VisualVertex} to the {@link Graph} and the
	 * {@link Grid}.
	 * 
	 * @param visualVertex
	 *            the VisualVertex to be added
	 * @return {@code true} if the adding was successful
	 */
	public boolean addVisualVertex(VisualVertex visualVertex) {
		if (this.grid.addVisualVertexToGrid(visualVertex)) {
			if (this.graph.addVertex(visualVertex)) {
				LOG.finer("Added VisualVertex at position " + visualVertex.getGridPoint() + ".");
				return true;
			}
			this.grid.removeVisualVertexAtGridPoint(visualVertex.getGridPoint());
		}
		return false;
	}

	/**
	 * Adds the given VisualVertices to the {@link Graph} and the {@link Grid}.
	 * 
	 * @param visualVertices
	 *            an ArrayList of the VisualVertices to be added
	 * @return {@code true} if the adding was successful
	 */
	public boolean addVisualVertices(ArrayList<VisualVertex> visualVertices) {
		for (VisualVertex vertex : visualVertices) {
			if (!this.addVisualVertex(vertex)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Creates the {@link VisualEdge} with the given vertices and adds it to the
	 * {@link Graph} and the {@link Grid}.
	 * 
	 * @param vertexA
	 *            one of the the vertices for the VisualEdge
	 * @param vertexB
	 *            the other vertex for the VisualEdge
	 * @return the created {@link VisualEdge}
	 */
	public VisualEdge addVisualEdge(VisualVertex vertexA, VisualVertex vertexB) {
		SimpleVisualEdge edgeToAdd = new SimpleVisualEdge(vertexA, vertexB);
		if (this.graph.addEdge(edgeToAdd)) {
			if (!this.isDirectedGraph) {
				SimpleVisualEdge twinEdgeToAdd = new SimpleVisualEdge(vertexB, vertexA);
				twinEdgeToAdd.setVisible(false);
				if (this.graph.addEdge(twinEdgeToAdd)) {
					return edgeToAdd;
				} else {
					this.graph.removeEdge(edgeToAdd);
					return null;
				}
			}
			return edgeToAdd;
		}
		return null;
	}

	/**
	 * Removes the {@link VisualVertex} from the the {@link Graph} and the
	 * {@link Grid}.
	 * 
	 * @param visualVertex
	 *            the {@link VisualVertex} to be removed
	 * @return {@code true} if the removing was successful
	 */
	public boolean removeVisualVertex(VisualVertex visualVertex) {
		if (this.grid.removeVisualVertexAtGridPoint(visualVertex.getGridPoint())) {
			if (this.graph.removeVertex(visualVertex)) {
				LOG.finer("Removed VisualVertex from position " + visualVertex.getGridPoint() + ".");
				return true;
			}
			// Removing failed, add to grid again.
			this.grid.addVisualVertexToGrid(visualVertex);
		}
		return false;
	}

	/**
	 * Removes the given {@link VisualEdge} from the the {@link Graph} and the
	 * {@link Grid}.
	 * 
	 * @param visualEdge
	 *            the {@link VisualEdge} to be removed
	 * @return {@code true} if the removing was successful
	 */
	public boolean removeVisualEdge(VisualEdge visualEdge) {
		if (this.graph.removeEdge(visualEdge)) {
			if (!this.isDirectedGraph) {
				Edge twinEdge = this.graph.getEdge(visualEdge.getTargetVertex(), visualEdge.getOriginVertex());
				if (this.graph.removeEdge(twinEdge)) {
					return true;
				} else {
					this.graph.addEdge(visualEdge);
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Returns the {@link VisualEdge} with vertexA as origin and vertexB as
	 * target vertex In an undirected graph, it returns visible one of the twin
	 * edges.
	 * 
	 * @param vertexA
	 *            the origin vertex of the edge to get
	 * @param vertexB
	 *            the target vertex of the edge to get
	 * @return the edge from vertexA to vertexB (resp. in an undirected graph,
	 *         the visible one of the twin edges
	 */
	public VisualEdge getVisualEdge(VisualVertex vertexA, VisualVertex vertexB) {
		VisualEdge edge = (VisualEdge) this.graph.getEdge(vertexA, vertexB);
		if (edge.isVisible()) {
			return edge;
		} else {
			return (VisualEdge) this.graph.getEdge(vertexB, vertexA);
		}
	}

	/**
	 * Returns whether the graph of this GameBoard is directed or not.
	 * 
	 * @return <code>true</code> if the graph of this GameBoard is directed,
	 *         <code>false</code> otherwise
	 */
	public boolean isDirectedGraph() {
		return this.isDirectedGraph;
	}

	/**
	 * Returns the {@link Graph} of this GameBoard.
	 * 
	 * @return the Graph of this GameBoard
	 */
	public Graph getGraph() {
		return this.graph;
	}

	/**
	 * Returns the {@link Grid} of this GameBoard.
	 * 
	 * @return the Grid of this GameBoard
	 */
	public Grid getGrid() {
		return this.grid;
	}

	/**
	 * Resets this GameBoard to an empty state.
	 */
	public void flush() {
		this.graph = new Graph();
		int horizontalGridPoints = this.grid.getHorizontalGridPoints();
		int verticalGridPoints = this.grid.getVerticalGridPoints();
		this.grid = new Grid(horizontalGridPoints, verticalGridPoints);
		LOG.fine("GameBoard flushed.");
	}

}