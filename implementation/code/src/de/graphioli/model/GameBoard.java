package de.graphioli.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * This class represents the game board of a {@link Game}. The GameBoard
 * combines the logical {@link Graph} with the {@link Grid} and has access to
 * both of them.
 * 
 * @author Team Graphioli
 */
public class GameBoard implements Serializable {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -2685640996584191994L;

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
	 * Adds the given {@link VisualEdge} to the board. If the used graph is
	 * undirected the opposing edge is also added. If there already is an edge
	 * present connecting the two vertices, {@code false} is returned.
	 * 
	 * @param vEdge
	 *            the visual edge to add
	 * @return {@code true} when successful.
	 */

	public boolean addVisualEdge(VisualEdge vEdge) {
		if (this.graph.addEdge(vEdge)) {
			if (!this.isDirectedGraph) {
				// Undirected
				VisualEdge twinEdgeToAdd = vEdge.generateOpposedEdge();
				twinEdgeToAdd.setHasOpposingEdge(false);
				twinEdgeToAdd.setIsOpposingEdge(true);
				vEdge.setHasOpposingEdge(true);
				vEdge.setIsOpposingEdge(false);
				if (!this.graph.addEdge(twinEdgeToAdd)) {
					LOG.severe("Adding opposing edge failed. Graph inconsistent.");
					return false;
				}
			} else {
				VisualEdge opEdge = this.getVisualEdge(vEdge.getTargetVertex(), vEdge.getOriginVertex());
				if (opEdge == null) {
					vEdge.setHasOpposingEdge(false);
					vEdge.setIsOpposingEdge(false);
				} else {
					vEdge.setIsOpposingEdge(true);
					opEdge.setHasOpposingEdge(true);
				}

			}
			return true;
		} else {
			return false;
		}
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
	 * Resets this GameBoard to an empty state.
	 */
	public void flush() {
		this.graph = new Graph();
		int horizontalGridPoints = this.grid.getHorizontalGridPoints();
		int verticalGridPoints = this.grid.getVerticalGridPoints();
		this.grid = new Grid(horizontalGridPoints, verticalGridPoints);
		LOG.fine("GameBoard flushed.");
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
	 * Returns the {@link VisualEdge} with vertexA as origin and vertexB as
	 * target vertex In an undirected graph, it returns the one which is not
	 * flagged eas opposing.
	 * 
	 * @param vertexA
	 *            the origin vertex of the edge to get
	 * @param vertexB
	 *            the target vertex of the edge to get
	 * @return the edge from vertexA to vertexB (resp. in an undirected graph,
	 *         the not-opposing one.
	 */
	public VisualEdge getVisualEdge(VisualVertex vertexA, VisualVertex vertexB) {
		VisualEdge edge = (VisualEdge) this.graph.getEdge(vertexA, vertexB);
		
		if (edge == null) {
			return null;
		}
		
		if (this.isDirectedGraph || !edge.isOpposingEdge()) {
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
				// Undirected
				Edge twinEdge = this.graph.getEdge(visualEdge.getTargetVertex(), visualEdge.getOriginVertex());
				if (this.graph.removeEdge(twinEdge)) {
					return true;
				} else {
					this.graph.addEdge(visualEdge);
					return false;
				}
			} else {
				if (visualEdge.hasOpposingEdge()) {
					VisualEdge twinEdge = (VisualEdge) this.graph.getEdge(visualEdge.getTargetVertex(),
							visualEdge.getOriginVertex());
					twinEdge.setIsOpposingEdge(false);
				}
				if (visualEdge.isOpposingEdge()) {
					VisualEdge twinEdge = (VisualEdge) this.graph.getEdge(visualEdge.getTargetVertex(),
							visualEdge.getOriginVertex());
					twinEdge.setHasOpposingEdge(false);
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Removes the {@link VisualVertex} from the {@link Graph} and the
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

}
