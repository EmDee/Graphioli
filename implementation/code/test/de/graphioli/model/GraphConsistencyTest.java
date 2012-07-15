package de.graphioli.model;

import static org.junit.Assert.*;

import org.junit.Test;

import de.graphioli.model.Graph;
import de.graphioli.model.Vertex;
import de.graphioli.model.Edge;

public class GraphConsistencyTest {

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

		assertTrue(vtexOne.getOutgoingEdges().contains(edgeOne));
		assertFalse(vtexOne.getOutgoingEdges().contains(edgeTwo));
		assertTrue(vtexTwo.getIncomingEdges().contains(edgeOne));
		assertFalse(vtexTwo.getIncomingEdges().contains(edgeTwo));

		assertFalse(vtexTwo.getOutgoingEdges().contains(edgeOne));
		assertTrue(vtexTwo.getOutgoingEdges().contains(edgeTwo));
		assertFalse(vtexOne.getIncomingEdges().contains(edgeOne));
		assertTrue(vtexOne.getIncomingEdges().contains(edgeTwo));
	}

	@Test
	public void testRemoveEdge() {
		Graph graph = new Graph();
		Vertex[] vtecies = new Vertex[3];
		for (int i = 0; i < vtecies.length; i++) {
			vtecies[i] = new Vertex();
		}

		graph.addVertex(vtecies[0]);
		graph.addVertex(vtecies[1]);
		graph.addVertex(vtecies[2]);
		Edge edgeOne = new Edge(vtecies[0], vtecies[1]);
		Edge edgeTwo = new Edge(vtecies[1], vtecies[2]);
		graph.addEdge(edgeOne);
		graph.addEdge(edgeTwo);

		assertTrue(vtecies[0].getOutgoingEdges().contains(edgeOne));
		assertTrue(vtecies[1].getOutgoingEdges().contains(edgeTwo));
		assertTrue(vtecies[1].getIncomingEdges().contains(edgeOne));
		assertTrue(vtecies[2].getIncomingEdges().contains(edgeTwo));
		
		assertTrue(graph.removeEdge(edgeOne));
		assertFalse(graph.removeEdge(edgeOne));
		
		assertFalse(vtecies[0].getOutgoingEdges().contains(edgeOne));
		assertTrue(vtecies[1].getOutgoingEdges().contains(edgeTwo));
		assertFalse(vtecies[1].getIncomingEdges().contains(edgeOne));
		assertTrue(vtecies[2].getIncomingEdges().contains(edgeTwo));
		
		assertTrue(graph.removeEdge(edgeTwo));
		
		assertFalse(vtecies[1].getOutgoingEdges().contains(edgeTwo));
		assertFalse(vtecies[2].getIncomingEdges().contains(edgeTwo));
		
		assertEquals(graph.getEdges().size(), 0);
	}
	
	@Test
	public void testRemoveVertex() {
		Graph graph = new Graph();
		Vertex vtexOne = new Vertex();
		Vertex vtexTwo = new Vertex();
		graph.addVertex(vtexOne);
		graph.addVertex(vtexTwo);
		
		assertEquals(graph.getVertices().size(), 2);
		assertEquals(graph.getEdges().size(), 0);
		
		Edge edgeOne = new Edge(vtexOne, vtexTwo);
		Edge edgeTwo = new Edge(vtexTwo, vtexOne);
		
		graph.addEdge(edgeOne);
		graph.addEdge(edgeTwo);
		
		assertTrue(vtexOne.getOutgoingEdges().contains(edgeOne));
		assertFalse(vtexOne.getOutgoingEdges().contains(edgeTwo));
		assertTrue(vtexOne.getIncomingEdges().contains(edgeTwo));
		assertFalse(vtexOne.getIncomingEdges().contains(edgeOne));
		assertFalse(vtexTwo.getOutgoingEdges().contains(edgeOne));
		assertTrue(vtexTwo.getOutgoingEdges().contains(edgeTwo));
		assertFalse(vtexTwo.getIncomingEdges().contains(edgeTwo));
		assertTrue(vtexTwo.getIncomingEdges().contains(edgeOne));
		
		graph.removeVertex(vtexTwo);
		
		assertFalse(vtexOne.getOutgoingEdges().contains(edgeOne));
		assertFalse(vtexOne.getOutgoingEdges().contains(edgeTwo));
		assertFalse(vtexOne.getIncomingEdges().contains(edgeTwo));
		assertFalse(vtexOne.getIncomingEdges().contains(edgeOne));
		
		assertEquals(graph.getVertices().size(), 1);
		assertEquals(graph.getEdges().size(), 0);
	}
}
