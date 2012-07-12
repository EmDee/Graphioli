package de.graphioli.model;

import static org.junit.Assert.*;

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
		assertNotNull(opEdge);
		
		assertTrue(opEdge.isOpposingEdge());
		assertFalse(opEdge.hasOpposingEdge());
		
		assertEquals(vtexOne.getIncomingEdges().get(0), opEdge);
		assertEquals(vtexTwo.getOutgoingEdges().get(0), opEdge);	
		
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
	public void testGetVisualEdge() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveVisualEdge() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveVisualVertex() {
		fail("Not yet implemented");
	}

}
