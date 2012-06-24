package de.graphioli.algorithms;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import de.graphioli.model.Edge;
import de.graphioli.model.Graph;
import de.graphioli.model.Vertex;

public class BreadthFirstSearchTest {
	private static Graph graph;
	private static ArrayList<Vertex> expectedListOfVertices = new ArrayList<Vertex>();
	ArrayList<Vertex> actualListOfVertices = new ArrayList<Vertex>();
	
	@Test
	public void testTwoVerticesOneEdgeDepthFive() {
		/*
		 * Setting up graph with two vertices and one edge from vertex1 to vertex2
		 */
		graph = new Graph();
		Vertex vertex1 = new Vertex();
		Vertex vertex2 = new Vertex();

		Edge edge1 = new Edge(vertex1, vertex2);
		
		graph.addVertex(vertex1);
		graph.addVertex(vertex2);
		graph.addEdge(edge1);
		
		expectedListOfVertices.add(vertex2);
		
		/*
		 * depth 5 should return a list with vertex2.
		 * 
		 */
		actualListOfVertices = BreadthFirstSearch.performAlgorithm(graph, vertex1, 5);
		assertEquals(expectedListOfVertices, actualListOfVertices);
	}
	
	@Test
	public void testFiveVerticesFiveEdgesDepthTwo() {
		actualListOfVertices = new ArrayList<Vertex>();
		expectedListOfVertices = new ArrayList<Vertex>();
		
		/*
		 * Setting up graph with 5 vertices. 
		 * edge: (1,2), (2,3), (2,4), (3,4), (4,5)
		 * 
		 */
		graph = new Graph();
		Vertex vertex1 = new Vertex();
		Vertex vertex2 = new Vertex();
		Vertex vertex3 = new Vertex();
		Vertex vertex4 = new Vertex();
		Vertex vertex5 = new Vertex();

		Edge edge1 = new Edge(vertex1, vertex2);
		Edge edge2 = new Edge(vertex2, vertex3);
		Edge edge3 = new Edge(vertex2, vertex4);
		Edge edge4 = new Edge(vertex3, vertex4);
		Edge edge5 = new Edge(vertex4, vertex5);
		
		graph.addVertex(vertex1);
		graph.addVertex(vertex2);
		graph.addVertex(vertex3);
		graph.addVertex(vertex4);
		graph.addVertex(vertex5);
		graph.addEdge(edge1);
		graph.addEdge(edge2);
		graph.addEdge(edge3);
		graph.addEdge(edge4);
		graph.addEdge(edge5);
		
		/*
		 * depth 2 should return a list with the vertex 2, 3, 4
		 */
		expectedListOfVertices.add(vertex2);
		expectedListOfVertices.add(vertex3);
		expectedListOfVertices.add(vertex4);

		actualListOfVertices = BreadthFirstSearch.performAlgorithm(graph, vertex1, 2);
		assertEquals(expectedListOfVertices, actualListOfVertices);
	}
	
	@Test
	public void testFiveVerticesFiveEdgesDepthThree() {
		actualListOfVertices = new ArrayList<Vertex>();
		expectedListOfVertices = new ArrayList<Vertex>();
		
		/*
		 * Setting up graph with 5 vertices. 
		 * edge: (1,2), (2,3), (2,4), (3,4), (4,5)
		 * 
		 */
		graph = new Graph();
		Vertex vertex1 = new Vertex();
		Vertex vertex2 = new Vertex();
		Vertex vertex3 = new Vertex();
		Vertex vertex4 = new Vertex();
		Vertex vertex5 = new Vertex();

		Edge edge1 = new Edge(vertex1, vertex2);
		Edge edge2 = new Edge(vertex2, vertex3);
		Edge edge3 = new Edge(vertex2, vertex4);
		Edge edge4 = new Edge(vertex3, vertex4);
		Edge edge5 = new Edge(vertex4, vertex5);
		
		graph.addVertex(vertex1);
		graph.addVertex(vertex2);
		graph.addVertex(vertex3);
		graph.addVertex(vertex4);
		graph.addVertex(vertex5);
		graph.addEdge(edge1);
		graph.addEdge(edge2);
		graph.addEdge(edge3);
		graph.addEdge(edge4);
		graph.addEdge(edge5);
		
		/*
		 * depth 3 should return a list with the vertex 2, 3, 4, 5
		 */
		expectedListOfVertices.add(vertex2);
		expectedListOfVertices.add(vertex3);
		expectedListOfVertices.add(vertex4);
		expectedListOfVertices.add(vertex5);

		actualListOfVertices = BreadthFirstSearch.performAlgorithm(graph, vertex1, 4);
		assertEquals(expectedListOfVertices, actualListOfVertices);
	}
}
