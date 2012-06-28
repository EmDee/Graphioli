package de.graphioli.algorithms;

import java.util.ArrayList;

import de.graphioli.model.Graph;
import de.graphioli.model.Vertex;

/**
 * This class can check if a path between two given vertices exists.
 * 
 * @author Graphioli
 */
public final class FindPath {

	/**
	 * Private empty constructor, to ensure that no instance is being created.
	 */
	private FindPath() {
	}

	/**
	 * Checks if a path exists in a specified graph between two given vertices.
	 * This method uses the {@link BreadthFirstSearch} algorithm.
	 * 
	 * @param graph
	 *            specified graph
	 * @param vertexA
	 *            starting {@link Vertex}
	 * @param vertexB
	 *            {@link Vertex} to check for
	 * @return <code>true</code> if a path exists between the two given
	 *         vertices, <code>false</code> otherwise.
	 */
	public static boolean performAlgorithm(Graph graph, Vertex vertexA, Vertex vertexB) {
		ArrayList<Vertex> reachableVertices = BreadthFirstSearch.performAlgorithm(graph, vertexA, 1000);
		if (reachableVertices.contains(vertexB)) {
			return true;
		}
		return false;
	}
}
