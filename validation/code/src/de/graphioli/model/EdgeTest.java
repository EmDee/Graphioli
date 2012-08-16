package de.graphioli.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class EdgeTest {

	@Test
	public void testEqualsErrorCatches() {
		Vertex v1 = new Vertex();
		Vertex v2 = new Vertex();
		Edge e1 = null;
		Edge e2 = new Edge(v1, v2);
		
		assertFalse(e2.equals(e1));
		e1 = new Edge(null, null);
		assertFalse(e1.equals(e2));
		e1 = new Edge(v1, null);
		assertFalse(e1.equals(e2));
	}
	
	@Test
	public void testGetterAndSetter() {
		Vertex v1 = new Vertex();
		Vertex v2 = new Vertex();
		Edge e = new Edge(v1,v2);
		int edgeWeight = 2;
		e.setWeight(edgeWeight);
		assertEquals(edgeWeight, e.getWeight());
		assertEquals(v1, e.getOriginVertex());
		assertEquals(v2, e.getTargetVertex());
	}
	
	@Test
	public void testHashCode() {
		Vertex v1 = new Vertex();
		Edge e = new Edge(v1, new Vertex());
		
		// VertexHashCode
		int expectedResult = 31 + v1.getUID().hashCode();
		assertEquals(v1.hashCode(), expectedResult);
		
		// EdgeHashCode
		expectedResult = 31 + e.getOriginVertex().hashCode();
		expectedResult = 31 * expectedResult + e.getTargetVertex().hashCode();
		
		assertEquals(e.hashCode(), expectedResult);		
	}

}
