package de.graphioli.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;

import de.graphioli.model.Graph;
import de.graphioli.model.Vertex;
import de.graphioli.model.VertexState;

/**
 * This class performs the breadth-first-search algorithm.
 * 
 * @author Graphioli
 * 
 */
public final class BreadthFirstSearch {
	private static ArrayList<Vertex> reachableVertex = new ArrayList<Vertex>();
	private static LinkedList<Vertex> queue = new LinkedList<Vertex>();

	/**
	 * Private empty constructor, to ensure that no instance is being created.
	 */
	private BreadthFirstSearch() {
	}

	/**
	 * The method performs the BFS algorithm. It needs a {@link Graph}, a start
	 * {@link Vertex} and a depth. The algorithms run as long as the depth
	 * hasn't reached 0 and as long as the queue isn't empty.
	 * 
	 * @param graph
	 *            the graph to perform BFS on
	 * @param vertex
	 *            the starting {@link Vertex}
	 * @param depth
	 *            the depth to perform BFS
	 * @return list of all vertices that can be reached within the given depth
	 */
	public static ArrayList<Vertex> performAlgorithm(Graph graph, Vertex vertex, int depth) {
		int curDepth = depth;
		if (graph != null && vertex != null && curDepth > -1) {
			resetStatesOfVertices(graph);
			reachableVertex = new ArrayList<Vertex>();
			queue = new LinkedList<Vertex>();

			Vertex tmpVertex = graph.getVertices().get(graph.getVertices().indexOf(vertex));

			queue.add(tmpVertex);
			while (curDepth >= 0 || !queue.isEmpty()) {
				/*
				 * If depth is below 0, but the queue still has previous added
				 * vertices to add to reachableVertices list
				 */
				if (!queue.isEmpty()) {
					tmpVertex = queue.pop();
					tmpVertex.setVertexState(VertexState.ACTIVE);
					if (!tmpVertex.equals(vertex) && !reachableVertex.contains(tmpVertex)) {
						reachableVertex.add(tmpVertex);
					}
				}

				/*
				 * Only add new vertices to queue, if depth is higher or equal
				 * to 0 and if there are still unvisited vertices left
				 */
				if (curDepth >= 0 && isUnvisitedVertexLeft(graph)) {
					for (Vertex curVertex : tmpVertex.getAdjacentVertices()) {
						if (curVertex.getVertexState() != VertexState.VISITED && !queue.contains(curVertex)) {
							queue.add(curVertex);
						}
					}
					curDepth -= 1;
				} else {
					/*
					 * either depth is -1 or there are no more unvisited
					 * vertices; reset depth and queue
					 */
					curDepth = -1;
					queue = new LinkedList<Vertex>();
				}
				tmpVertex.setVertexState(VertexState.VISITED);
			}
			return reachableVertex;
		}
		return null;
	}

	/**
	 * Checks if there are any unvisited vertices left
	 * 
	 * @param graph
	 *            given graph to check for unvisited vertices
	 * @return <code>true</code> if there are unvisited vertices left,
	 *         <code>false</code> otherwise
	 */
	private static boolean isUnvisitedVertexLeft(Graph graph) {
		for (Vertex vertex : graph.getVertices()) {
			if (vertex.getVertexState() == VertexState.UNVISITED) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Resets the state of all vertices in a given graph to 'UNVISITED'.
	 * 
	 * @param graph
	 *            given graph with vertices to reset
	 */
	private static void resetStatesOfVertices(Graph graph) {
		for (Vertex vertex : graph.getVertices()) {
			vertex.setVertexState(VertexState.UNVISITED);
		}
	}
}