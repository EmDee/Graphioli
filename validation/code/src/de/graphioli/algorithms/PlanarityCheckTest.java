package de.graphioli.algorithms;

import static org.junit.Assert.*;
import org.junit.Test;
import de.graphioli.model.*;

public class PlanarityCheckTest {
	
	private static Graph graph;
	private static SimpleVisualEdge newEdge;

	@Test
	public void testPerformAlgorithm() {
		 GridPoint p1 = new GridPoint(0,0);
		 GridPoint p2 = new GridPoint(0,1);
		 GridPoint p3 = new GridPoint(1,0);
		 GridPoint p4 = new GridPoint(1,1);
		 SimpleVisualVertex v1 = new SimpleVisualVertex(p1);
		 SimpleVisualVertex v2 = new SimpleVisualVertex(p2);
		 SimpleVisualVertex v3 = new SimpleVisualVertex(p3);
		 SimpleVisualVertex v4 = new SimpleVisualVertex(p4);
		 SimpleVisualEdge e12 = new SimpleVisualEdge(v1,v2);
		 SimpleVisualEdge e23 = new SimpleVisualEdge(v2,v3);
		 
		 graph = new Graph();
		 
		 graph.addVertex(v1);
		 graph.addVertex(v2);
		 graph.addVertex(v3);
		 graph.addVertex(v4);
		 graph.addEdge(e12);
		 graph.addEdge(e23);
		 
		 newEdge = new SimpleVisualEdge(v1, v4);
		 
		 boolean res = PlanarityCheck.performAlgorithm(graph, newEdge);
		 
		 assertFalse(res);
		 
		 newEdge = new SimpleVisualEdge(v3, v4);
		 
		 res = PlanarityCheck.performAlgorithm(graph, newEdge);
		 
		 assertTrue(res);
	}
	
	@Test
	public void testPerformAlgorithm2() {
		 GridPoint p1 = new GridPoint(0,0);
		 GridPoint p2 = new GridPoint(0,1);
		 GridPoint p3 = new GridPoint(1,0);
		 GridPoint p4 = new GridPoint(1,1);

		 SimpleVisualVertex v1 = new SimpleVisualVertex(p4);
		 SimpleVisualVertex v2 = new SimpleVisualVertex(p2);
		 SimpleVisualVertex v3 = new SimpleVisualVertex(p3);
		 SimpleVisualVertex v4 = new SimpleVisualVertex(p1);
		 SimpleVisualEdge e12 = new SimpleVisualEdge(v1,v2);
		 SimpleVisualEdge e23 = new SimpleVisualEdge(v2,v3);
		 
		 graph = new Graph();
		 
		 graph.addVertex(v1);
		 graph.addVertex(v2);
		 graph.addVertex(v3);
		 graph.addVertex(v4);
		 graph.addEdge(e12);
		 graph.addEdge(e23);
		 
		 newEdge = new SimpleVisualEdge(v1, v4);
		 
		 boolean res = PlanarityCheck.performAlgorithm(graph, newEdge);
		 
		 assertFalse(res);
		 
		 newEdge = new SimpleVisualEdge(v3, v4);
		 
		 res = PlanarityCheck.performAlgorithm(graph, newEdge);
		 
		 assertTrue(res);
	}
	
	@Test
	public void testPerformAlgorithm3() {
		 GridPoint p1 = new GridPoint(0,0);
		 GridPoint p2 = new GridPoint(0,1);
		 GridPoint p3 = new GridPoint(1,0);
		 GridPoint p4 = new GridPoint(1,1);

		 SimpleVisualVertex v1 = new SimpleVisualVertex(p1);
		 SimpleVisualVertex v2 = new SimpleVisualVertex(p3);
		 SimpleVisualVertex v3 = new SimpleVisualVertex(p2);
		 SimpleVisualVertex v4 = new SimpleVisualVertex(p4);
		 SimpleVisualEdge e12 = new SimpleVisualEdge(v1,v2);
		 SimpleVisualEdge e23 = new SimpleVisualEdge(v2,v3);
		 
		 graph = new Graph();
		 
		 graph.addVertex(v1);
		 graph.addVertex(v2);
		 graph.addVertex(v3);
		 graph.addVertex(v4);
		 graph.addEdge(e12);
		 graph.addEdge(e23);
		 
		 newEdge = new SimpleVisualEdge(v1, v4);
		 
		 boolean res = PlanarityCheck.performAlgorithm(graph, newEdge);
		 
		 assertFalse(res);
		 
		 newEdge = new SimpleVisualEdge(v3, v4);
		 
		 res = PlanarityCheck.performAlgorithm(graph, newEdge);
		 
		 assertTrue(res);
	}
	
	@Test
	public void testPerformAlgorithm4() {
		 GridPoint p1 = new GridPoint(0,0);
		 GridPoint p2 = new GridPoint(0,1);
		 GridPoint p3 = new GridPoint(1,0);
		 GridPoint p4 = new GridPoint(1,1);

		 SimpleVisualVertex v1 = new SimpleVisualVertex(p4);
		 SimpleVisualVertex v2 = new SimpleVisualVertex(p3);
		 SimpleVisualVertex v3 = new SimpleVisualVertex(p2);
		 SimpleVisualVertex v4 = new SimpleVisualVertex(p1);
		 SimpleVisualEdge e12 = new SimpleVisualEdge(v1,v2);
		 SimpleVisualEdge e23 = new SimpleVisualEdge(v2,v3);
		 
		 graph = new Graph();
		 
		 graph.addVertex(v1);
		 graph.addVertex(v2);
		 graph.addVertex(v3);
		 graph.addVertex(v4);
		 graph.addEdge(e12);
		 graph.addEdge(e23);
		 
		 newEdge = new SimpleVisualEdge(v1, v4);
		 
		 boolean res = PlanarityCheck.performAlgorithm(graph, newEdge);
		 
		 assertFalse(res);
		 
		 newEdge = new SimpleVisualEdge(v3, v4);
		 
		 res = PlanarityCheck.performAlgorithm(graph, newEdge);
		 
		 assertTrue(res);
	}
}
