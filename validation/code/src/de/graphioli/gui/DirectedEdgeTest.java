package de.graphioli.gui;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
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

	private Pattern startBtn = new Pattern(this.screensDir + "start_btn.png");
	private Pattern yesBtn = new Pattern(this.screensDir + "yes_btn.png");
	private Pattern okBtn = new Pattern(this.screensDir + "ok_btn.png");

	private Pattern playerInput = new Pattern(this.screensDir + "player_input.png");

	private Pattern vertex = new Pattern(this.directedEdgeScreenPath + "deVertex.png");
	private Pattern pinkVertex = new Pattern(this.directedEdgeScreenPath + "dePinkVertex.png");
	private Pattern vertexSelected = new Pattern(this.directedEdgeScreenPath + "deVertexSelected.png");
	private Pattern spot = new Pattern(this.screensDir + "spot.png");
	private Pattern emptyGraphCanvas = new Pattern(this.screensDir + "emptyGraphCanvas.png");

	private Pattern oneEdge = new Pattern(this.directedEdgeScreenPath + "deOneEdge.png");
	private Pattern twoEdge = new Pattern(this.directedEdgeScreenPath + "deTwoEdge.png");
	private Pattern vertexWithArrow = new Pattern(this.directedEdgeScreenPath + "deVertexArrow.png");

	private Pattern pinkMenuItem = new Pattern(this.directedEdgeScreenPath + "dePink.png");
	private Pattern optionsMenuItem = new Pattern(this.screensDir + "optionsMenuItem.png");
	private Pattern flushMenuItem = new Pattern(this.directedEdgeScreenPath + "deFlush.png");
	private Pattern gameMenuItem = new Pattern(this.screensDir + "gameMenuItem.png");
	private Pattern quitMenuItem = new Pattern(this.screensDir + "quitMenuItem.png");
	private Pattern helpMenuItem = new Pattern(this.screensDir + "helpMenuItem.png");
	private Pattern openHelpFileMenuItem = new Pattern(this.screensDir + "openHelpFileMenuItem.png");
	
	private Pattern helpPage = new Pattern(this.directedEdgeScreenPath + "deHelpPage.png");

	/**
	 * Set up one instance of the GameExplorer before all tests.
	 */
	@BeforeClass
	public static void beforeClass() {
		//GameManager.main(null);
	}

	/**
	 * General set up before every test.
	 */
	@Before
	public void setUp() {
		try {
			this.screen.click(this.startBtn, 5);
			this.screen.wait(this.playerInput, 5);
			this.screen.type(null, playerName, 0);
			this.screen.click(this.okBtn, 5);
		} catch (FindFailed e) {
			fail("Set up failed due to: " + e.getMessage());
		}
	}

	/**
	 * If the game window is existent, then close it to return to GameExplorer.
	 */
	@After
	public void tearDown() {
		// quit first
		try {
			this.screen.click(this.gameMenuItem);
			this.screen.click(this.quitMenuItem);
			this.screen.click(this.yesBtn);
		} catch (FindFailed e) {
			fail("Couldn't tear down previous test due to: " + e.getMessage());
		}
	}

	/**
	 * Test the player pop up.
	 */
	@Test
	public void testOnePlayerPopUp() {
		try {
			// quit first
			this.screen.click(this.gameMenuItem);
			this.screen.click(this.quitMenuItem);
			this.screen.click(this.yesBtn);

			this.screen.click(this.startBtn);
			assertTrue(this.screen.exists(this.playerInput) != null);
			this.screen.type(null, playerName, 0);
			this.screen.type(Key.ENTER);
		} catch (FindFailed e) {
			fail("Test failed due to: " + e.getMessage());
		}
	}

	/**
	 * Starts a DirectedEdge game.
	 */
	@Test
	public void testStartDirectedEdgeGame() {
		assertTrue(this.screen.exists(this.screensDir + "directedEdgeGame.png") != null);
	}

	/**
	 * Adds a vertex to the graph canvas.
	 */
	@Test
	public void testAddVertex() {
		try {
			this.screen.click(this.spot);
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
		try {
			@SuppressWarnings("deprecation")
			Region region = new Region(585, 535, 0, 0);
			region.setW(70);
			region.setH(70);
			region.highlight(1);
			if (region.exists(this.spot) != null) {
				region.click(this.spot);
				region.click(this.vertex);
				this.screen.type(null, Key.DELETE, 0);
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
			@SuppressWarnings("deprecation")
			Region region1 = new Region(585, 535, 0, 0);
			region1.setW(70);
			region1.setH(70);
			region1.highlight(1);
			if (region1.exists(this.spot) != null) {
				region1.click(this.spot);
			}

			@SuppressWarnings("deprecation")
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
	 * Test the automatic removal of one edge, if a vertex with an adjacent
	 * vertex is removed.
	 */
	@Test
	public void testRemoveSingleEdge() {
		try {
			@SuppressWarnings("deprecation")
			Region region1 = new Region(585, 535, 0, 0);
			region1.setW(70);
			region1.setH(70);
			region1.highlight(1);
			if (region1.exists(this.spot) != null) {
				region1.click(this.spot);
			}

			@SuppressWarnings("deprecation")
			Region region2 = new Region(460, 410, 0, 0);
			region2.setW(70);
			region2.setH(70);
			region2.highlight(1);
			if (region2.exists(this.spot) != null) {
				region2.click(this.spot);
			}

			region2.click(this.vertex);
			region1.click(this.vertex);
			assertTrue(region1.exists(this.vertexWithArrow) != null);

			region2.click(this.vertex);
			region1.click(this.vertex);
			assertFalse(region1.exists(this.vertexWithArrow.similar(1)) != null);
		} catch (FindFailed e) {
			fail("Test failed due to: " + e.getMessage());
		}
	}

	/**
	 * Test the reset of a already selected vertex.
	 */
	@Test
	public void testSelectVertexTwice() {
		try {
			@SuppressWarnings("deprecation")
			Region region1 = new Region(585, 535, 0, 0);
			region1.setW(70);
			region1.setH(70);
			region1.highlight(1);
			if (region1.exists(this.spot) != null) {
				region1.click(this.spot);
			}

			region1.click(this.vertex);
			assertTrue(region1.exists(this.vertexSelected) != null);
			region1.click(this.vertexSelected);
			assertTrue(region1.exists(this.vertex) != null);
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
			@SuppressWarnings("deprecation")
			Region region1 = new Region(585, 535, 0, 0);
			region1.setW(70);
			region1.setH(70);
			region1.highlight(1);
			if (region1.exists(this.spot) != null) {
				region1.click(this.spot);
			}
			@SuppressWarnings("deprecation")
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
			@SuppressWarnings("deprecation")
			Region region1 = new Region(585, 535, 0, 0);
			region1.setW(70);
			region1.setH(70);
			region1.highlight(1);
			if (region1.exists(this.spot) != null) {
				region1.click(this.spot);
			}
			@SuppressWarnings("deprecation")
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

			region1.click(this.vertex);
			this.screen.type(null, Key.DELETE, 0);
			assertFalse(region1.exists(this.vertex) != null);
		} catch (FindFailed e) {
			fail("Test failed due to: " + e.getMessage());
		}
	}

	/**
	 * Test custom menu item; pink vertex
	 */
	@Test
	public void testPinkVertex() {
		try {
			this.screen.click(this.optionsMenuItem);
			this.screen.click(this.pinkMenuItem);
			if (this.screen.exists(this.spot) != null) {
				this.screen.click(this.spot);
				assertTrue(this.screen.exists(this.pinkVertex) != null);
			}
		} catch (FindFailed e) {
			fail("Test failed due to: " + e.getMessage());
		}
	}

	/**
	 * Test custom menu item; flush
	 */
	@Test
	public void testFlush() {
		try {
			if (this.screen.exists(this.spot) != null) {
				for (int i = 0; i < 80; i++) {
					this.screen.click(this.spot);
				}
			}
			assertFalse(this.screen.exists(this.emptyGraphCanvas.similar((float) 0.7)) != null);
			this.screen.click(this.optionsMenuItem);
			this.screen.click(this.flushMenuItem);
			assertTrue(this.screen.exists(this.emptyGraphCanvas) != null);
		} catch (FindFailed e) {
			fail("Test failed due to: " + e.getMessage());
		}
	}

	/**
	 * Test the help menu button inside the game window.
	 */
	@Test
	public void testHelpInGameWindow() {
		try {
			this.screen.click(this.helpMenuItem);
			this.screen.click(this.openHelpFileMenuItem);
			assertTrue(this.screen.exists(this.helpPage) != null);
			this.screen.wait(this.helpPage, 3);
			this.screen.type(Key.TAB, KeyModifier.CMD);
		} catch (FindFailed e) {
			fail("Test didn't succeed, due to: " + e.getMessage());
		}
	}
}
