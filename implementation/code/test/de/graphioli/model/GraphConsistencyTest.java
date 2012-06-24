package de.graphioli.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class GraphConsistencyTest {

	//TODO: More tests.
	
	@Test
	public void testAddingVertices() {
		Graph graph = new Graph();
		
		Vertex vtexOne = new Vertex();
		assertTrue(graph.addVertex(vtexOne));
		assertFalse(graph.addVertex(vtexOne));
		
		Vertex vtexTwo = new Vertex();
		
		assertTrue(graph.addVertex(vtexTwo));
		assertFalse(graph.addVertex(vtexTwo));
		assertFalse(graph.addVertex(vtexOne));	
	}
	
	@Test
	public void testAddingEdges() {
		Graph graph = new Graph();
		Vertex vtexOne = new Vertex();
		Vertex vtexTwo = new Vertex();	
		Edge edgeOne = new Edge(vtexOne, vtexTwo);	
		assertFalse(graph.addEdge(edgeOne));
		graph.addVertex(vtexOne);
		assertFalse(graph.addEdge(edgeOne));
		graph.addVertex(vtexTwo);
		assertTrue(graph.addEdge(edgeOne));
		assertFalse(graph.addEdge(edgeOne));
		
		Edge edgeTwo = new Edge(vtexOne, vtexOne);	
		assertFalse(graph.addEdge(edgeTwo));
		edgeTwo = new Edge(vtexTwo, vtexOne);
		assertTrue(graph.addEdge(edgeTwo));
		
		assertFalse(graph.addEdge(edgeOne));		
	}


}
