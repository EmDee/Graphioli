package de.graphioli.algorithms;

import static org.junit.Assert.*;

import org.junit.Test;

import de.graphioli.model.Edge;
import de.graphioli.model.Graph;
import de.graphioli.model.Vertex;

public class FindPathTest {

	@Test
	public void testPerformAlgorithm() {
		/*
		 * Setting up graph with two vertices and one edge from vertex1 to vertex2
		 */
		Graph graph = new Graph();
		Vertex vertex1 = new Vertex();
		Vertex vertex2 = new Vertex();
		Vertex vertex3 = new Vertex();

		Edge edge1 = new Edge(vertex1, vertex2);
		Edge edge2 = new Edge(vertex2, vertex3);
		
		assertFalse(FindPath.performAlgorithm(graph, vertex1, vertex3));
		graph.addVertex(vertex1);
		graph.addVertex(vertex2);
		graph.addVertex(vertex3);
		graph.addEdge(edge1);
		graph.addEdge(edge2);
		
		assertTrue(FindPath.performAlgorithm(graph, vertex1, vertex3));
	}

}
