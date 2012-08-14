package de.graphioli.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class GameBoardTest {
	
	private GameBoard directedBoard;
	private GameBoard undirectedBoard;

	@Before
	public void setUp() throws Exception {
		directedBoard = new GameBoard(true, 5, 5);
		undirectedBoard = new GameBoard(false, 5, 5);
	}

	@Test
	public void testAddVisualEdgeDirected() {
		VisualVertex vtexOne = new SimpleVisualVertex(new GridPoint(1, 1));
		VisualVertex vtexTwo = new SimpleVisualVertex(new GridPoint(2, 2));
		
		VisualEdge edge = new SimpleVisualEdge(vtexOne, vtexTwo);
		VisualEdge opEdge = new SimpleVisualEdge(vtexTwo, vtexOne);
		
		assertFalse(directedBoard.addVisualEdge(edge));		
		directedBoard.addVisualVertex(vtexOne);
		assertFalse(directedBoard.addVisualEdge(edge));
		directedBoard.addVisualVertex(vtexTwo);
		assertTrue(directedBoard.addVisualEdge(edge));
		assertFalse(directedBoard.addVisualEdge(edge));
		
		assertEquals(vtexOne.getOutgoingEdges().get(0), edge);
		assertEquals(vtexOne.getIncomingEdges().size(), 0);
		assertEquals(vtexTwo.getIncomingEdges().get(0), edge);
		assertEquals(vtexTwo.getOutgoingEdges().size(), 0);	
		
		assertTrue(directedBoard.addVisualEdge(opEdge));
		assertFalse(directedBoard.addVisualEdge(opEdge));
		
		assertTrue(edge.hasOpposingEdge());
		assertFalse(edge.isOpposingEdge());	
		assertTrue(opEdge.isOpposingEdge());
		assertFalse(opEdge.hasOpposingEdge());
		
		assertEquals(vtexOne.getIncomingEdges().get(0), opEdge);
		assertEquals(vtexTwo.getOutgoingEdges().get(0), opEdge);	
		
		directedBoard.flush();
	}

	@Test
	public void testAddVisualEdgeUndirected() {
		VisualVertex vtexOne = new SimpleVisualVertex(new GridPoint(1, 1));
		VisualVertex vtexTwo = new SimpleVisualVertex(new GridPoint(2, 2));
		
		VisualEdge edge = new SimpleVisualEdge(vtexOne, vtexTwo);
		VisualEdge opEdge = new SimpleVisualEdge(vtexTwo, vtexOne);
		
		assertFalse(undirectedBoard.addVisualEdge(edge));		
		undirectedBoard.addVisualVertex(vtexOne);
		assertFalse(undirectedBoard.addVisualEdge(edge));
		undirectedBoard.addVisualVertex(vtexTwo);
		assertTrue(undirectedBoard.addVisualEdge(edge));
		assertFalse(undirectedBoard.addVisualEdge(edge));
		
		assertFalse(edge.isOpposingEdge());
		assertTrue(edge.hasOpposingEdge());
		
		assertEquals(vtexOne.getOutgoingEdges().get(0), edge);
		assertEquals(vtexOne.getIncomingEdges().size(), 1);
		assertEquals(vtexTwo.getIncomingEdges().get(0), edge);
		assertEquals(vtexTwo.getOutgoingEdges().size(), 1);	
		
		assertFalse(undirectedBoard.addVisualEdge(opEdge));
		opEdge = undirectedBoard.getVisualEdge(vtexTwo, vtexOne);

		assertEquals(edge, opEdge);
		
		assertEquals(vtexOne.getIncomingEdges().size(), 1);
		assertEquals(vtexTwo.getOutgoingEdges().size(), 1);	
		
		undirectedBoard.flush();
	}
	
	@Test
	public void testAddVisualVertex() {
		VisualVertex vtexOne = new SimpleVisualVertex(new GridPoint(2, 6));
		assertFalse(directedBoard.addVisualVertex(vtexOne));
		assertFalse(undirectedBoard.addVisualVertex(vtexOne));
		assertEquals(directedBoard.getGraph().getVertices().size(), 0);
		assertEquals(undirectedBoard.getGraph().getVertices().size(), 0);
		
		vtexOne = new SimpleVisualVertex(new GridPoint(3, 0));
		assertTrue(directedBoard.addVisualVertex(vtexOne));
		assertFalse(directedBoard.addVisualVertex(vtexOne));
		assertTrue(undirectedBoard.addVisualVertex(vtexOne));
		assertFalse(undirectedBoard.addVisualVertex(vtexOne));
		
		assertEquals(directedBoard.getGraph().getVertices().get(0), vtexOne);
		assertEquals(directedBoard.getGrid().getVisualVertexAtGridPoint(vtexOne.getGridPoint()), vtexOne);
		assertEquals(undirectedBoard.getGraph().getVertices().get(0), vtexOne);
		assertEquals(undirectedBoard.getGrid().getVisualVertexAtGridPoint(vtexOne.getGridPoint()), vtexOne);
		
		directedBoard.flush();
		undirectedBoard.flush();
	}

	@Test
	public void testFlush() {
		directedBoard.flush();
		undirectedBoard.flush();
		
		assertEquals(directedBoard.getGraph().getEdges().size(), 0);
		assertEquals(directedBoard.getGraph().getVertices().size(), 0);
		assertEquals(undirectedBoard.getGraph().getEdges().size(), 0);
		assertEquals(undirectedBoard.getGraph().getVertices().size(), 0);
		
		assertTrue(directedBoard.isDirectedGraph());
		assertFalse(undirectedBoard.isDirectedGraph());
	}

	@Test
	public void testGetVisualEdgeDirected() {
		VisualVertex vtexOne = new SimpleVisualVertex(new GridPoint(1, 1));
		VisualVertex vtexTwo = new SimpleVisualVertex(new GridPoint(2, 2));
		
		VisualEdge edge = new SimpleVisualEdge(vtexOne, vtexTwo);
		
		assertNull(directedBoard.getVisualEdge(vtexOne, vtexTwo));	
		directedBoard.addVisualVertex(vtexOne);
		assertNull(directedBoard.getVisualEdge(vtexOne, vtexTwo));
		directedBoard.addVisualVertex(vtexTwo);	
		assertNull(directedBoard.getVisualEdge(vtexOne, vtexTwo));
		
		directedBoard.addVisualEdge(edge);
		
		assertEquals(directedBoard.getVisualEdge(vtexOne, vtexTwo), edge);
		assertNull(directedBoard.getVisualEdge(vtexTwo, vtexOne));
		
		directedBoard.flush();
	}
	
	@Test
	public void testGetVisualEdgeUnirected() {
		VisualVertex vtexOne = new SimpleVisualVertex(new GridPoint(1, 1));
		VisualVertex vtexTwo = new SimpleVisualVertex(new GridPoint(2, 2));
		
		VisualEdge edge = new SimpleVisualEdge(vtexOne, vtexTwo);
		
		assertNotNull(undirectedBoard);
		assertNull(undirectedBoard.getVisualEdge(vtexOne, vtexTwo));	
		undirectedBoard.addVisualVertex(vtexOne);
		assertNull(undirectedBoard.getVisualEdge(vtexOne, vtexTwo));
		undirectedBoard.addVisualVertex(vtexTwo);	
		assertNull(undirectedBoard.getVisualEdge(vtexOne, vtexTwo));
		
		assertTrue(undirectedBoard.addVisualEdge(edge));
		
		assertEquals(undirectedBoard.getVisualEdge(vtexOne, vtexTwo), edge);
		assertNotNull(undirectedBoard.getVisualEdge(vtexTwo, vtexOne));
		
		undirectedBoard.flush();
	}

	@Test
	public void testRemoveVisualEdgeDirected() {
		VisualVertex vtexOne = new SimpleVisualVertex(new GridPoint(1, 1));
		VisualVertex vtexTwo = new SimpleVisualVertex(new GridPoint(2, 2));
		
		VisualEdge edge = new SimpleVisualEdge(vtexOne, vtexTwo);
		
		directedBoard.addVisualVertex(vtexOne);
		directedBoard.addVisualVertex(vtexTwo);	
		
		assertFalse(directedBoard.removeVisualEdge(edge));
		
		directedBoard.addVisualEdge(edge);
		
		assertTrue(directedBoard.removeVisualEdge(edge));
		assertFalse(directedBoard.removeVisualEdge(edge));
		
		assertNull(directedBoard.getVisualEdge(vtexOne, vtexTwo));
		
		assertEquals(vtexOne.getOutgoingEdges().size(), 0);
		assertEquals(vtexTwo.getIncomingEdges().size(), 0);		

		directedBoard.flush();
	}
	
	@Test
	public void testRemoveVisualEdgeUndirected() {
		VisualVertex vtexOne = new SimpleVisualVertex(new GridPoint(1, 1));
		VisualVertex vtexTwo = new SimpleVisualVertex(new GridPoint(2, 2));
		
		VisualEdge edge = new SimpleVisualEdge(vtexOne, vtexTwo);
		VisualEdge opEdge = new SimpleVisualEdge(vtexTwo, vtexOne);
		
		undirectedBoard.addVisualVertex(vtexOne);
		undirectedBoard.addVisualVertex(vtexTwo);	
		
		assertFalse(undirectedBoard.removeVisualEdge(edge));
		
		undirectedBoard.addVisualEdge(edge);
		
		assertTrue(undirectedBoard.removeVisualEdge(edge));
		assertFalse(undirectedBoard.removeVisualEdge(edge));
		
		assertEquals(vtexOne.getOutgoingEdges().size(), 0);
		assertEquals(vtexTwo.getIncomingEdges().size(), 0);	
		assertEquals(vtexOne.getIncomingEdges().size(), 0);
		assertEquals(vtexTwo.getOutgoingEdges().size(), 0);	
		
		undirectedBoard.addVisualEdge(opEdge);
		
		assertTrue(undirectedBoard.removeVisualEdge(edge));
		assertFalse(undirectedBoard.removeVisualEdge(edge));
		assertFalse(undirectedBoard.removeVisualEdge(opEdge));
		
		assertNull(undirectedBoard.getVisualEdge(vtexOne, vtexTwo));
		assertNull(undirectedBoard.getVisualEdge(vtexTwo, vtexOne));
		
		assertEquals(vtexOne.getOutgoingEdges().size(), 0);
		assertEquals(vtexTwo.getIncomingEdges().size(), 0);	
		assertEquals(vtexOne.getIncomingEdges().size(), 0);
		assertEquals(vtexTwo.getOutgoingEdges().size(), 0);	

		undirectedBoard.flush();
	}

	@Test
	public void testRemoveVisualVertexDirected() {
		VisualVertex vtexOne = new SimpleVisualVertex(new GridPoint(1, 1));
		VisualVertex vtexTwo = new SimpleVisualVertex(new GridPoint(2, 2));
		
		VisualEdge edge = new SimpleVisualEdge(vtexOne, vtexTwo);
		
		directedBoard.addVisualVertex(vtexOne);
		
		assertFalse(directedBoard.removeVisualVertex(vtexTwo));
		
		directedBoard.addVisualVertex(vtexTwo);			
		directedBoard.addVisualEdge(edge);
		
		assertTrue(directedBoard.removeVisualVertex(vtexTwo));
		assertFalse(directedBoard.removeVisualVertex(vtexTwo));
		
		assertEquals(vtexOne.getOutgoingEdges().size(), 0);
		
		directedBoard.flush();		
	}
	
	@Test
	public void testRemoveVisualVertexUndirected() {
		VisualVertex vtexOne = new SimpleVisualVertex(new GridPoint(1, 1));
		VisualVertex vtexTwo = new SimpleVisualVertex(new GridPoint(2, 2));
		
		VisualEdge edge = new SimpleVisualEdge(vtexOne, vtexTwo);
		
		undirectedBoard.addVisualVertex(vtexOne);
		
		assertFalse(undirectedBoard.removeVisualVertex(vtexTwo));
		
		undirectedBoard.addVisualVertex(vtexTwo);			
		undirectedBoard.addVisualEdge(edge);
		
		assertTrue(undirectedBoard.removeVisualVertex(vtexTwo));
		assertFalse(undirectedBoard.removeVisualVertex(vtexTwo));
		
		assertEquals(vtexOne.getOutgoingEdges().size(), 0);
		assertEquals(vtexOne.getIncomingEdges().size(), 0);
		
		undirectedBoard.flush();
	}
	
	@Test
	public void errorCatches() {
		// addVisualVertex (error: graph inconsistent)
		SimpleVisualVertex v = new SimpleVisualVertex(new GridPoint(1, 1));
		undirectedBoard.getGraph().addVertex(v);
		assertFalse(undirectedBoard.addVisualVertex(v));
		
		// addVisualVertices (error: graph inconsistent)
		ArrayList<VisualVertex> listVertex = new ArrayList<VisualVertex>();
		listVertex.add(v);
		assertFalse(undirectedBoard.addVisualVertices(listVertex));
		
		// removeVisualEdge (undirected)
		undirectedBoard.flush();
		undirectedBoard.addVisualVertex(v);
		SimpleVisualVertex b = new SimpleVisualVertex(new GridPoint(2, 2));
		undirectedBoard.addVisualVertex(b);
		SimpleVisualEdge e = new SimpleVisualEdge(v, b);
		undirectedBoard.addVisualEdge(e);
		undirectedBoard.getGraph().removeEdge(undirectedBoard.getGraph().getEdge(b, v));
		assertFalse(undirectedBoard.removeVisualEdge(e));
		
		// removeVisualVertex
		undirectedBoard.getGraph().removeVertex(v);
		assertFalse(undirectedBoard.removeVisualVertex(v));
	}

}
