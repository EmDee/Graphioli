package de.graphioli.model;

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


	public boolean addVisualVertex(VisualVertex visualVertex) {
		return false;}
	public boolean addVisualVertices(Iterable<VisualVertex> visualVertices) {
		return false;}
	public VisualEdge addVisualEdge(VisualVertex vertexA, VisualVertex vertexB) {
		return null;}
	public boolean removeVisualVertex(VisualVertex visualVertex) {
		return false;}
	public boolean removeVisualEdge(VisualEdge visualEdge) {
		return false;}
	public VisualEdge getVisualEdge(VisualVertex vertexA, VisualVertex vertexB) {
		return null;}


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