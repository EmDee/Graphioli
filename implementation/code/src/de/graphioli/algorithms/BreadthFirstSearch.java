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
	private static ArrayList<Vertex> reachableVertex;
	private static LinkedList<Vertex> queue;

	/**
	 * Private empty contructor, to ensure that no instance is being created.
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
				 * Only add new vertices to queue, if depth is 0 or higher
				 */
				if (curDepth >= 0) {
					for (Vertex curVertex : tmpVertex.getAdjacentVertices()) {
						if (curVertex.getVertexState() != VertexState.VISITED && !queue.contains(curVertex)) {
							queue.add(curVertex);
						}
					}
					curDepth -= 1;
				}
				tmpVertex.setVertexState(VertexState.VISITED);
			}
			return reachableVertex;
		}
		return null;
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