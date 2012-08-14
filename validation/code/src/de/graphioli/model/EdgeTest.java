package de.graphioli.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class EdgeTest {

	@Test
	public void equalsErrorCatches() {
		Vertex v1 = new Vertex();
		Vertex v2 = new Vertex();
		Edge e1 = null;
		Edge e2 = new Edge(v1, v2);
		
		assertFalse(e2.equals(e1));
		//TODO ???? Nulls nicht abgefangen
		//assertFalse(e1.equals(e2));
		e1 = new Edge(null, null);
		assertFalse(e1.equals(e2));
		e1 = new Edge(v1, null);
		assertFalse(e1.equals(e2));
	}
	
	@Test
	public void getterAndSetter() {
		Vertex v1 = new Vertex();
		Vertex v2 = new Vertex();
		Edge e = new Edge(v1,v2);
		int edgeWeight = 2;
		e.setWeight(edgeWeight);
		assertEquals(edgeWeight, e.getWeight());
		assertEquals(v1, e.getOriginVertex());
		assertEquals(v2, e.getTargetVertex());
	}

}
