import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import de.graphioli.controller.GameManager;

public class SikuliDemonstration {

	private String screensDir = "./application/screens/";
	private String graphColoringScreenPath = "./application/screens/GraphColoring/";
	private String directedEdgeScreenPath = "./application/screens/DirectedEdge/";
	private String twixtScreenPath = "./application/screens/TwixT/";
	private String playerOne = "Bob";
	private String playerTwo = "Alice";
	private String playerName = "Team Graphioli";

	private static Screen screen = new Screen();

	// buttons
	private Pattern okBtn = new Pattern(this.screensDir + "ok_btn.png");
	private Pattern startBtn = new Pattern(this.screensDir + "start_btn.png");
	private Pattern newGameBtn = new Pattern(this.screensDir + "newGame.png");
	private Pattern noBtn = new Pattern(this.screensDir + "no_btn.png");
	private Pattern yesBtn = new Pattern(this.screensDir + "yes_btn.png");
	private Pattern quitBtn = new Pattern(this.screensDir + "quit_btn.png");

	// messages
	private Pattern duplicateName = new Pattern(this.screensDir + "duplicateName.png");
	private Pattern invalidMove = new Pattern(this.graphColoringScreenPath + "gcInvalidMove.png");
	private Pattern alreadyColored = new Pattern(this.graphColoringScreenPath + "gcAlreadyColored.png");

	// menu items
	private Pattern quitMenuItem = new Pattern(this.screensDir + "quitMenuItem.png");
	private Pattern gameMenuItem = new Pattern(this.screensDir + "gameMenuItem.png");

	// dialogs
	private Pattern aliceWins = new Pattern(this.graphColoringScreenPath + "gcAliceWins.png");
	private Pattern twoPlayers = new Pattern(this.screensDir + "twoPlayers.png");
	private Pattern playerInput = new Pattern(this.screensDir + "player_input.png");
	private Pattern firstPlayerInput = new Pattern(this.screensDir + "playerInputFirst.png");
	private Pattern secondPlayerInput = new Pattern(this.screensDir + "playerInputSecond.png");
	private Pattern playerSelection = new Pattern(this.screensDir + "playerSelection.png");

	// vertices
	private Pattern topLeftCorner = new Pattern(this.graphColoringScreenPath + "gcTopLeftCorner.png");
	private Pattern topRightCorner = new Pattern(this.graphColoringScreenPath + "gcTopRightCorner.png");
	private Pattern bottomLeftCorner = new Pattern(this.graphColoringScreenPath + "gcBottomLeftCorner.png");
	private Pattern bottomRightCorner = new Pattern(this.graphColoringScreenPath + "gcBottomRightCorner.png");
	private Pattern topRightCornerRed = new Pattern(this.graphColoringScreenPath + "gcTopRightCornerRed.png");

	// graph coloring buttons
	private Pattern greenSelected = new Pattern(this.graphColoringScreenPath + "gcGreenSelected.png");
	private Pattern redSelected = new Pattern(this.graphColoringScreenPath + "gcRedSelected.png");
	private Pattern blueSelected = new Pattern(this.graphColoringScreenPath + "gcBlueSelected.png");
	private Pattern greenButton = new Pattern(this.graphColoringScreenPath + "gcGreenBtn.png");
	private Pattern blueButton = new Pattern(this.graphColoringScreenPath + "gcBlueBtn.png");

	// miscellaneous
	private Pattern graphColoringText = new Pattern(this.screensDir + "graphColoringSelect.png");
	private Pattern directedGameText = new Pattern(this.screensDir + "directedGame.png");
	private Pattern graphColoringSelected = new Pattern(this.graphColoringScreenPath + "gcSelectionSelected.png");
	private Pattern vertexWithArrow = new Pattern(this.directedEdgeScreenPath + "deVertexArrow.png");
	private Pattern vertex = new Pattern(this.directedEdgeScreenPath + "deVertex.png");
	private Pattern spot = new Pattern(this.screensDir + "spot.png");

	// graph canvas
	private Pattern firstPlayerTower = new Pattern(this.twixtScreenPath + "twixtFirstPlayerTower.png");
	private Pattern secondPlayerTower = new Pattern(this.twixtScreenPath + "twixtSecondPlayerTower.png");

	// assertions
	private Pattern twixtSelection = new Pattern(this.twixtScreenPath + "twixtSelection.png");
	private Pattern menuBar = new Pattern(this.twixtScreenPath + "twixtMenuBar.png");
	private Pattern bobWins = new Pattern(this.twixtScreenPath + "twixtBobWins.png");

	/**
	 * Set up one instance of the GameExplorer before all tests.
	 */
	@BeforeClass
	public static void beforeClass() {
		// indication for existence of GameExplorer
		Pattern gameExplorerExistance = new Pattern("./application/screens/gameExplorerExistence.png");

		if (SikuliDemonstration.screen.exists(gameExplorerExistance) == null) {
			GameManager.main(null);
		}
	}

	//
	// DirectEdge tests
	//
	/**
	 * Test removal of a vertex.
	 */
	@Test
	public void testRemoveVertex() {
		try {
			// set up
			SikuliDemonstration.screen.click(this.startBtn, 5);
			SikuliDemonstration.screen.wait(this.playerInput, 5);
			SikuliDemonstration.screen.type(null, playerName, 0);
			SikuliDemonstration.screen.click(this.okBtn, 5);

			@SuppressWarnings("deprecation")
			Region region = new Region(585, 535, 0, 0);
			region.setW(70);
			region.setH(70);
			region.highlight(1);
			if (region.exists(this.spot) != null) {
				region.click(this.spot);
				region.click(this.vertex);
				SikuliDemonstration.screen.type(null, Key.DELETE, 0);
				assertFalse(SikuliDemonstration.screen.exists(this.vertex) != null);
			}
		} catch (FindFailed e) {
			fail("Test failed due to: " + e.getMessage());
		}

		// tear down
		try {
			SikuliDemonstration.screen.click(this.gameMenuItem);
			SikuliDemonstration.screen.click(this.quitMenuItem);
			SikuliDemonstration.screen.click(this.yesBtn);
		} catch (FindFailed e) {
			fail("Couldn't tear down previous test due to: " + e.getMessage());
		}
	}

	/**
	 * Test the automatic removal of one edge, if a vertex with an adjacent
	 * vertex is removed.
	 */
	@Test
	public void testRemoveSingleEdge() {
		try {
			// set up
			SikuliDemonstration.screen.click(this.startBtn, 5);
			SikuliDemonstration.screen.wait(this.playerInput, 5);
			SikuliDemonstration.screen.type(null, playerName, 0);
			SikuliDemonstration.screen.click(this.okBtn, 5);

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

		// tear down
		if (SikuliDemonstration.screen.exists(this.gameMenuItem) != null) {
			try {
				SikuliDemonstration.screen.click(this.gameMenuItem);
				SikuliDemonstration.screen.click(this.quitMenuItem);
				SikuliDemonstration.screen.click(this.yesBtn);
			} catch (FindFailed e) {
				fail("Tear down failed due to: " + e.getMessage());
			}
		}
		if (SikuliDemonstration.screen.exists(this.graphColoringSelected) != null) {
			try {
				SikuliDemonstration.screen.click(this.directedGameText);
			} catch (FindFailed e) {
				fail("Couldn't click on directedGameText, due to: " + e.getMessage());
			}
		}
	}

	//
	// GraphColoring tests
	//
	/**
	 * Test a two player game, where the second player wins.
	 */
	@Test
	public void testTwoPlayerGame() {
		try {
			if (SikuliDemonstration.screen.exists(this.graphColoringSelected) == null) {
				try {
					SikuliDemonstration.screen.click(this.graphColoringText, 0);
					SikuliDemonstration.screen.click(this.startBtn);
					SikuliDemonstration.screen.click(this.newGameBtn);
				} catch (FindFailed e) {
					fail("Set up failed due to: " + e.getMessage());
				}
			} else {
				try {
					SikuliDemonstration.screen.click(this.startBtn);
					SikuliDemonstration.screen.click(this.newGameBtn);
				} catch (FindFailed e) {
					fail("Set up failed due to: " + e.getMessage());
				}
			}

			// select two players
			SikuliDemonstration.screen.click(this.playerSelection);
			SikuliDemonstration.screen.click(this.twoPlayers);
			SikuliDemonstration.screen.click(this.okBtn);

			// enter names for players
			SikuliDemonstration.screen.wait(this.firstPlayerInput);
			SikuliDemonstration.screen.type(null, this.playerOne, 0);
			SikuliDemonstration.screen.click(this.okBtn);
			SikuliDemonstration.screen.wait(this.secondPlayerInput);
			SikuliDemonstration.screen.type(null, this.playerOne, 0);
			SikuliDemonstration.screen.click(this.okBtn);

			assertTrue(SikuliDemonstration.screen.exists(this.duplicateName.similar((float) 0.2)) != null);
			SikuliDemonstration.screen.type(Key.ENTER);
			// SikuliDemonstration.screen.click(this.okBtn);

			SikuliDemonstration.screen.type(null, this.playerTwo, 0);
			SikuliDemonstration.screen.click(this.okBtn);

			// red button should be selected by default
			assertTrue(SikuliDemonstration.screen.exists(this.redSelected) != null);

			SikuliDemonstration.screen.click(this.topRightCorner);

			// already colored
			SikuliDemonstration.screen.click(this.topRightCornerRed);
			assertTrue(SikuliDemonstration.screen.exists(this.alreadyColored) != null);

			// invalid move
			SikuliDemonstration.screen.click(this.topLeftCorner);
			assertTrue(SikuliDemonstration.screen.exists(this.invalidMove) != null);

			// select green color
			SikuliDemonstration.screen.click(this.greenButton);
			assertTrue(SikuliDemonstration.screen.exists(this.greenSelected) != null);
			SikuliDemonstration.screen.click(this.topLeftCorner);

			// select blue color
			SikuliDemonstration.screen.click(this.blueButton);
			assertTrue(SikuliDemonstration.screen.exists(this.blueSelected) != null);
			SikuliDemonstration.screen.click(this.bottomLeftCorner);
			SikuliDemonstration.screen.click(this.bottomRightCorner);

			assertTrue(SikuliDemonstration.screen.exists(this.aliceWins) != null);
			SikuliDemonstration.screen.type(Key.ENTER);
			SikuliDemonstration.screen.click(this.noBtn);
		} catch (FindFailed e) {
			fail("Test didn't succeed, due to: " + e.getMessage());
		}
	}

	//
	// TwixT tests
	//
	/**
	 * Test a winning game.
	 */
	@Test
	public void testWinningGame() {
		// regions
		@SuppressWarnings("deprecation")
		Region region1 = new Region(565, 425, 0, 0);
		@SuppressWarnings("deprecation")
		Region region2 = new Region(605, 425, 0, 0);
		@SuppressWarnings("deprecation")
		Region region3 = new Region(520, 510, 0, 0);
		@SuppressWarnings("deprecation")
		Region region4 = new Region(565, 340, 0, 0);
		@SuppressWarnings("deprecation")
		Region region5 = new Region(605, 340, 0, 0);
		@SuppressWarnings("deprecation")
		Region region6 = new Region(650, 340, 0, 0);
		@SuppressWarnings("deprecation")
		Region region7 = new Region(565, 255, 0, 0);

		ArrayList<Region> regions = new ArrayList<Region>();
		regions.add(region1);
		regions.add(region2);
		regions.add(region3);
		regions.add(region4);
		regions.add(region5);
		regions.add(region6);
		regions.add(region7);

		for (Region region : regions) {
			region.setH(70);
			region.setW(70);
		}

		// set up
		if (SikuliDemonstration.screen.exists(this.twixtSelection) != null) {
			try {
				SikuliDemonstration.screen.click(this.twixtSelection);
			} catch (FindFailed e) {
				fail("Set up failed due to: " + e.getMessage());
			}
		}

		try {
			SikuliDemonstration.screen.click(this.startBtn);
			SikuliDemonstration.screen.click(this.newGameBtn);
			SikuliDemonstration.screen.wait(this.firstPlayerInput);
			SikuliDemonstration.screen.type(null, this.playerOne, 0);
			SikuliDemonstration.screen.click(this.okBtn);
			SikuliDemonstration.screen.wait(this.secondPlayerInput);
			SikuliDemonstration.screen.type(null, this.playerTwo, 0);
			SikuliDemonstration.screen.click(this.okBtn);
		} catch (FindFailed e) {
			fail("Set up failed due to: " + e.getMessage());
		}

		try {
			region1.highlight(1);
			if (region1.exists(this.spot) != null) {
				region1.click(this.spot);
			}

			region2.highlight(1);
			if (region2.exists(this.spot) != null) {
				region2.click(this.spot);
			}

			region3.highlight(1);
			region3.click(this.firstPlayerTower);
			region1.click(this.firstPlayerTower);

			region4.highlight(1);
			region4.click(this.spot);

			region5.highlight(1);
			region5.click(this.spot);

			region6.highlight(1);
			region6.click(this.spot);

			region1.click(this.firstPlayerTower);
			region5.click(this.firstPlayerTower);

			region2.click(this.secondPlayerTower);
			region6.click(this.secondPlayerTower);

			region5.click(this.firstPlayerTower);
			region7.highlight(1);
			region7.click(this.firstPlayerTower);

			assertTrue(SikuliDemonstration.screen.exists(this.bobWins) != null);

			SikuliDemonstration.screen.type(Key.ENTER);
			SikuliDemonstration.screen.type(Key.ENTER);
		} catch (FindFailed e) {
			fail("Test failed due to: " + e.getMessage());
		}

		if (SikuliDemonstration.screen.exists(this.menuBar) != null) {
			// quit first
			try {
				SikuliDemonstration.screen.click(this.gameMenuItem);
				SikuliDemonstration.screen.click(this.quitMenuItem);
				SikuliDemonstration.screen.click(this.yesBtn);
			} catch (FindFailed e) {
				fail("Couldn't tear down previous test due to: " + e.getMessage());
			}
		} else {
			// nothing do tear down.
			System.out.println("nothing to tear down.");
		}
	}
}