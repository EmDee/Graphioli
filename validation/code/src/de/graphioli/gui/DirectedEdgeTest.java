package de.graphioli.gui;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import de.graphioli.controller.GameManager;

/**
 * This class tests all functionality of the DirectedEdge game.
 * 
 * @author Team Graphioli
 */
public class DirectedEdgeTest {
	private String screensDir = "./application/screens/";
	private String directedEdgeScreenPath = "./application/screens/DirectedEdge/";
	private Screen screen = new Screen();
	private String playerName = "Team Graphioli";

	private Pattern cancelBtn = new Pattern(this.screensDir + "cancel_btn.png");
	private Pattern startBtn = new Pattern(this.screensDir + "start_btn.png");

	private Pattern playerInput = new Pattern(this.screensDir + "player_input.png");

	private Pattern vertex = new Pattern(this.screensDir + "vertex.png");
	private Pattern vertexSelected = new Pattern(this.screensDir + "vertex_selected.png");
	private Pattern spot = new Pattern(this.screensDir + "spot.png");

	private Pattern oneEdge = new Pattern(this.directedEdgeScreenPath + "deOneEdge.png");
	private Pattern twoEdge = new Pattern(this.directedEdgeScreenPath + "deTwoEdge.png");

	private Pattern afterRemoval = new Pattern(this.directedEdgeScreenPath + "deAfterRemoval.png");

	/**
	 * Create a new instance of the GameExplorer befor eevery test.
	 */
	@Before
	public void setUp() {
		GameManager.main(null);
	}

	/**
	 * Test the pop up for name input.
	 */
	@Test
	public void onePlayerPopUpTest() {
		try {
			this.screen.click(this.startBtn);
			assertTrue(this.screen.exists(this.playerInput) != null);

			// cancel
			try {
				this.screen.click(this.cancelBtn);
			} catch (FindFailed e) {
				fail("Couldn't find cancel button");
			}
		} catch (FindFailed e) {
			fail("Test failed due to: " + e.getMessage());
		}
	}

	/**
	 * Starts a DirectedEdge game.
	 */
	@Test
	public void testStartDirectedEdgeGame() {
		try {
			this.screen.click(this.startBtn);
			this.screen.wait(this.playerInput);
			this.screen.type(null, playerName, 0);
			// this.screen.click(this.screensDir + "ok_btn", 0);
			this.screen.type(Key.ENTER);
			assertTrue(this.screen.exists(this.screensDir + "directedEdgeGame.png") != null);
		} catch (FindFailed e) {
			fail("Test failed due to: " + e.getMessage());
		}
	}

	/**
	 * Adds a vertex to the graph canvas.
	 */
	@Test
	public void testAddVertex() {
		try {
			this.screen.click(this.startBtn);
			this.screen.wait(this.playerInput);
			this.screen.type(null, playerName, 0);
			// this.screen.click(this.screensDir + "ok_btn", 0);
			this.screen.type(Key.ENTER);
			Pattern pattern = new Pattern(this.spot);
			this.screen.click(pattern.similar(1));
			assertTrue(this.screen.exists(this.vertex) != null);
		} catch (FindFailed e) {
			fail("Test failed due to: " + e.getMessage());
		}
	}

	/**
	 * The yellow should turn red when selected.
	 */
	@Test
	public void testSelectVertex() {
		try {
			this.screen.click(this.startBtn);
			this.screen.wait(this.playerInput);
			this.screen.type(null, playerName, 0);
			this.screen.type(Key.ENTER);
			if (this.screen.exists(this.spot) != null) {
				this.screen.click(this.spot);
				this.screen.click(this.vertex);
				assertTrue(this.screen.exists(this.vertexSelected) != null);
			}
		} catch (FindFailed e) {
			fail("Test failed due to: " + e.getMessage());
		}
	}

	/**
	 * Test removal of a vertex.
	 */
	@Test
	public void testRemoveVertex() {
		// TODO: Delete function doesn't work yet
		try {
			this.screen.click(this.startBtn);
			this.screen.wait(this.playerInput);
			this.screen.type(null, playerName, 0);
			this.screen.type(Key.ENTER);
			Region region = new Region(585, 535, 0, 0);
			region.setW(70);
			region.setH(70);
			region.highlight(1);
			if (region.exists(this.spot) != null) {
				region.click(this.spot);
				region.click(this.vertex);
				this.screen.wait((double) 1);
				// this.screen.type(Key.DELETE);
				// this.screen.type(Key.DELETE, KeyModifier.META);
				this.screen.type(null, "\ue006", 0);
				this.screen.wait((double) 1);
				assertFalse(this.screen.exists(this.vertex) != null);
			}
		} catch (FindFailed e) {
			fail("Test failed due to: " + e.getMessage());
		}
	}

	/**
	 * Test the edge creation, by selecting two vertices.
	 */
	@Test
	public void testOneEdge() {
		try {
			this.screen.click(this.startBtn);
			this.screen.wait(this.playerInput);
			this.screen.type(null, playerName, 0);
			this.screen.type(Key.ENTER);
			Region region1 = new Region(585, 535, 0, 0);
			region1.setW(70);
			region1.setH(70);
			region1.highlight(1);
			if (region1.exists(this.spot) != null) {
				region1.click(this.spot);
			}

			Region region2 = new Region(460, 410, 0, 0);
			region2.setW(70);
			region2.setH(70);
			region2.highlight(1);
			if (region2.exists(this.spot) != null) {
				region2.click(this.spot);
			}

			region2.click(this.vertex);
			region1.click(this.vertex);
			assertTrue(this.screen.exists(this.oneEdge) != null);
		} catch (FindFailed e) {
			fail("Test failed due to: " + e.getMessage());
		}
	}

	/**
	 * Test the creation of two edges, by selecting two vertices twice.
	 */
	@Test
	public void testTwoEdge() {
		try {
			this.screen.click(this.startBtn);
			this.screen.wait(this.playerInput);
			this.screen.type(null, playerName, 0);
			this.screen.type(Key.ENTER);
			Region region1 = new Region(585, 535, 0, 0);
			region1.setW(70);
			region1.setH(70);
			region1.highlight(1);
			if (region1.exists(this.spot) != null) {
				region1.click(this.spot);
			}

			Region region2 = new Region(460, 410, 0, 0);
			region2.setW(70);
			region2.setH(70);
			region2.highlight(1);
			if (region2.exists(this.spot) != null) {
				region2.click(this.spot);
			}

			region2.click(this.vertex);
			region1.click(this.vertex);
			region1.click(this.vertex);
			region2.click(this.vertex);
			assertTrue(this.screen.exists(this.twoEdge) != null);
		} catch (FindFailed e) {
			fail("Test failed due to: " + e.getMessage());
		}
	}

	/**
	 * Test removal of edges, by selecting a vertex that has an edge to another
	 * vertex.
	 */
	@Test
	public void testRemoveOfEdges() {
		try {
			this.screen.click(this.startBtn);
			this.screen.wait(this.playerInput);
			this.screen.type(null, playerName, 0);
			this.screen.type(Key.ENTER);
			Region region1 = new Region(585, 535, 0, 0);
			region1.setW(70);
			region1.setH(70);
			region1.highlight(1);
			if (region1.exists(this.spot) != null) {
				region1.click(this.spot);
			}

			Region region2 = new Region(460, 410, 0, 0);
			region2.setW(70);
			region2.setH(70);
			region2.highlight(1);
			if (region2.exists(this.spot) != null) {
				region2.click(this.spot);
			}

			region2.click(this.vertex);
			this.screen.click(this.vertex);
			region1.click(this.vertex);
			this.screen.click(this.vertex);

			region1.click(this.vertex);
			// TODO: Fix delete key
			this.screen.type(Key.DELETE);
			assertTrue(this.screen.exists(this.afterRemoval) != null);
		} catch (FindFailed e) {
			fail("Test failed due to: " + e.getMessage());
		}
	}
}
