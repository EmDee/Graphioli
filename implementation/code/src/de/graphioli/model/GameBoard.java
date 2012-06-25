package de.graphioli.model;

import java.util.ArrayList;

/**
 * This class represents the game board of a {@link Game}. The GameBoard combines the logical
 * {@link Graph} with the {@link Grid} and has access to both of them.
 * 
 * @author Graphioli
 */
public class GameBoard {

	/**
	 * Whether the {@link Graph} of this GameBoard is directed or not
	 */
	private boolean isDirectedGraph;

	/**
	 * The {@link Graph} of this GameBoard
	 */
	private Graph graph;

	/**
	 * The {@link Grid} of this GameBoard
	 */
	private Grid grid;


	/**
	 * Creates a new {@link GameBoard} with the specified option whether the {@link Graph} of
	 * the GameBoard is directed or not.
	 * 
	 * @param isDirectedGraph Whether the graph of the game is directed or not
	 */
	public GameBoard(boolean isDirectedGraph, int horizontalGridPoints, int verticalGridPoints) {

		// Initialize GameBoard
		this.isDirectedGraph = isDirectedGraph;
		this.graph = new Graph();
		this.grid = new Grid(horizontalGridPoints, verticalGridPoints);

	}

	/**
	 * 
	 * @param visualVertex
	 * @return
	 */
	public boolean addVisualVertex(VisualVertex visualVertex) {
		if (this.grid.addVisualVertexToGrid(visualVertex)) {
			if (this.graph.addVertex(visualVertex)) {
				return true;
			}
			this.grid.removeVisualVertexAtGridPoint(visualVertex.getGridPoint());
		}
		return false;
	}
	
	
	/**
	 * 
	 * @param visualVertices
	 * @return
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
	 * 
	 * @param vertexA
	 * @param vertexB
	 * @return
	 */
	public VisualEdge addVisualEdge(VisualVertex vertexA, VisualVertex vertexB) {
		SimpleVisualEdge edgeToAdd = new SimpleVisualEdge(vertexA, vertexB);
		if (this.graph.addEdge(edgeToAdd)) {
			if (!this.isDirectedGraph) {
				SimpleVisualEdge twinEdgeToAdd = new SimpleVisualEdge(vertexB, vertexA);
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
	 * 
	 * @param visualVertex
	 * @return
	 */
	public boolean removeVisualVertex(VisualVertex visualVertex) {
		if (this.grid.removeVisualVertexAtGridPoint(visualVertex.getGridPoint())) {
			if (this.graph.removeVertex(visualVertex)) {
				return true;
			}
			this.grid.addVisualVertexToGrid(visualVertex);
		}
		return false;
	}
	
	/**
	 * 
	 * @param visualEdge
	 * @return
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
	 * 
	 * @param vertexA
	 * @param vertexB
	 * @return
	 */
	public VisualEdge getVisualEdge(VisualVertex vertexA, VisualVertex vertexB) {
		return null;
	}


	/**
	 * Returns whether the graph of this GameBoard is directed or not.
	 * @return <code>true</code> if the graph of this GameBoard is directed, <code>false</code> otherwise
	 */
	public boolean isDirectedGraph() {
		return this.isDirectedGraph;
	}


	/**
	 * Returns the {@link Graph} of this GameBoard.
	 * @return the Graph of this GameBoard
	 */
	public Graph getGraph() {
		return this.graph;
	}


	/**
	 * Returns the {@link Grid} of this GameBoard.
	 * @return the Grid of this GameBoard
	 */
	public Grid getGrid() {
		return this.grid;
	}

}