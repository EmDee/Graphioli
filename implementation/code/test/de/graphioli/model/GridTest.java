package de.graphioli.model;

import static org.junit.Assert.*;

import de.graphioli.model.GridPoint;
import org.junit.Test;

public class GridTest {

	@Test
	public void testAddVisualVertexToGrid() {
		Grid grid = new Grid(5, 3);

		VisualVertex tmpTex = new SimpleVisualVertex(new GridPoint(2, 1));

		assertTrue(grid.addVisualVertexToGrid(tmpTex));
		assertFalse(grid.addVisualVertexToGrid(tmpTex));

		tmpTex = new SimpleVisualVertex(new GridPoint(5, 3));

		assertFalse(grid.addVisualVertexToGrid(tmpTex));
	}

	@Test
	public void testGetVisualVertexAtGridPoint() {
		Grid grid = new Grid(3, 4);

		VisualVertex tmpTex = new SimpleVisualVertex(new GridPoint(2, 1));

		grid.addVisualVertexToGrid(tmpTex);
		assertEquals(grid.getVisualVertexAtGridPoint(new GridPoint(2, 1)), tmpTex);
		assertNull(grid.getVisualVertexAtGridPoint(new GridPoint(1, 1)));
		assertNull(grid.getVisualVertexAtGridPoint(new GridPoint(6, 1)));

	}

	@Test
	public void testRemoveVisualVertexAtGridPoint() {
		Grid grid = new Grid(2, 2);
		VisualVertex vtexOne = new SimpleVisualVertex(new GridPoint(0, 0));
		VisualVertex vtexTwo = new SimpleVisualVertex(new GridPoint(1,1));
		
		assertFalse(grid.removeVisualVertexAtGridPoint(new GridPoint(1,0)));
		assertFalse(grid.removeVisualVertexAtGridPoint(new GridPoint(2, 2)));
		
		grid.addVisualVertexToGrid(vtexOne);
		grid.addVisualVertexToGrid(vtexTwo);
		
		assertTrue(grid.removeVisualVertexAtGridPoint(vtexOne.getGridPoint()));
		assertNull(grid.getVisualVertexAtGridPoint(vtexOne.getGridPoint()));
		assertEquals(grid.getVisualVertexAtGridPoint(vtexTwo.getGridPoint()), vtexTwo);
		
	}

}
