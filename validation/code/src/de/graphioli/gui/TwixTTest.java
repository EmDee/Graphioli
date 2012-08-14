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

import java.util.ArrayList;
import de.graphioli.controller.GameManager;

public class TwixTTest {
	private String screensDir = "./application/screens/";
	private String twixtScreenPath = "./application/screens/TwixT/";
	private String playerOne = "Bob";
	private String playerTwo = "Alice";
	public String blub;

	private static Screen screen = new Screen();

	// buttons
	private Pattern startBtn = new Pattern(this.screensDir + "start_btn.png");
	private Pattern newGameBtn = new Pattern(this.screensDir + "newGame.png");
	private Pattern okBtn = new Pattern(this.screensDir + "ok_btn.png");
	private Pattern yesBtn = new Pattern(this.screensDir + "yes_btn.png");
	private Pattern helpBtn = new Pattern(this.screensDir + "help_btn.png");
	private Pattern saveBtn = new Pattern(this.screensDir + "save_btn.png");
	private Pattern openBtn = new Pattern(this.screensDir + "open_btn.png");
	private Pattern loadFromGameExplorerBtn = new Pattern(this.screensDir + "loadSavegame_btn.png");

	// menu items
	private Pattern gameMenuItem = new Pattern(this.screensDir + "gameMenuItem.png");
	private Pattern quitMenuItem = new Pattern(this.screensDir + "quitMenuItem.png");
	private Pattern saveMenuItem = new Pattern(this.screensDir + "saveMenuItem.png");
	private Pattern restartMenuItem = new Pattern(this.screensDir + "restartMenuItem.png");

	// dialogs
	private Pattern firstPlayerInput = new Pattern(this.screensDir + "playerInputFirst.png");
	private Pattern secondPlayerInput = new Pattern(this.screensDir + "playerInputSecond.png");
	private Pattern saveDialog = new Pattern(this.screensDir + "saveDialog.png");
	private Pattern overrideSavegame = new Pattern(this.screensDir + "overrideSavegame.png");

	// graph canvas
	private Pattern spot = new Pattern(this.screensDir + "spot.png");
	private Pattern firstPlayerTower = new Pattern(this.twixtScreenPath + "twixtFirstPlayerTower.png");
	private Pattern secondPlayerTower = new Pattern(this.twixtScreenPath + "twixtSecondPlayerTower.png");
	private Pattern singleRedEdge = new Pattern(this.twixtScreenPath + "twixtSingleRedEdge.png");

	// assertions
	private Pattern start = new Pattern(this.twixtScreenPath + "twixtStart.png");
	private Pattern twixtSelection = new Pattern(this.twixtScreenPath + "twixtSelection.png");
	private Pattern description = new Pattern(this.twixtScreenPath + "twixtDescription.png");
	private Pattern screenshot = new Pattern(this.twixtScreenPath + "twixtScreenshot.png");
	private Pattern menuBar = new Pattern(this.twixtScreenPath + "twixtMenuBar.png");
	private Pattern bobWins = new Pattern(this.twixtScreenPath + "twixtBobWins.png");
	private Pattern helpPage = new Pattern(this.twixtScreenPath + "twixtHelpPage.png");
	private Pattern loadedGame = new Pattern(this.twixtScreenPath + "twixtLoadedGame.png");
	private Pattern saveGame = new Pattern(this.screensDir + "saveGame.png");

	// error messages
	private Pattern invalidWall = new Pattern(this.twixtScreenPath + "twixtInvalidWall.png");
	private Pattern enemyTower = new Pattern(this.twixtScreenPath + "twixtEnemyTower.png");
	private Pattern intersectingWalls = new Pattern(this.twixtScreenPath + "twixtIntersectingWalls.png");
	private Pattern towerDemand = new Pattern(this.twixtScreenPath + "twixtTowerDemand.png");

	// regions
	@SuppressWarnings("deprecation")
	static Region region1 = new Region(565, 425, 0, 0);
	@SuppressWarnings("deprecation")
	static Region region2 = new Region(605, 425, 0, 0);
	@SuppressWarnings("deprecation")
	static Region region3 = new Region(520, 510, 0, 0);
	@SuppressWarnings("deprecation")
	static Region region4 = new Region(565, 340, 0, 0);
	@SuppressWarnings("deprecation")
	static Region region5 = new Region(605, 340, 0, 0);
	@SuppressWarnings("deprecation")
	static Region region6 = new Region(650, 340, 0, 0);
	@SuppressWarnings("deprecation")
	static Region region7 = new Region(565, 255, 0, 0);

	/**
	 * Set up one instance of the GameExplorer before all tests.
	 */
	@BeforeClass
	public static void beforeClass() {
		// indication for existence of GameExplorer
		Pattern gameExplorerExistance = new Pattern("./application/screens/gameExplorerExistence.png");

		if (TwixTTest.screen.exists(gameExplorerExistance) == null) {
			GameManager.main(null);
		}

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
	}

	/**
	 * General set up before every test.
	 */
	@Before
	public void setUp() {
		if (TwixTTest.screen.exists(this.twixtSelection) != null) {
			try {
				TwixTTest.screen.click(this.twixtSelection);
			} catch (FindFailed e) {
				fail("Set up failed due to: " + e.getMessage());
			}
		}

		try {
			TwixTTest.screen.click(this.startBtn);
			TwixTTest.screen.click(this.newGameBtn);
			TwixTTest.screen.wait(this.firstPlayerInput);
			TwixTTest.screen.type(null, this.playerOne, 0);
			TwixTTest.screen.click(this.okBtn);
			TwixTTest.screen.wait(this.secondPlayerInput);
			TwixTTest.screen.type(null, this.playerTwo, 0);
			TwixTTest.screen.click(this.okBtn);
		} catch (FindFailed e) {
			fail("Set up failed due to: " + e.getMessage());
		}
	}

	/**
	 * If the game window is existent, then close it to return to GameExplorer.
	 */
	@After
	public void tearDown() {
		if (TwixTTest.screen.exists(this.menuBar) != null) {
			// quit first
			try {
				TwixTTest.screen.click(this.gameMenuItem);
				TwixTTest.screen.click(this.quitMenuItem);
				TwixTTest.screen.click(this.yesBtn);
			} catch (FindFailed e) {
				fail("Couldn't tear down previous test due to: " + e.getMessage());
			}
		} else {
			// nothing do tear down.
			System.out.println("nothing to tear down.");
		}
	}

	/**
	 * Test if the TwixT screenshot in the GameExplorer is correctly displayed.
	 */
	@Test
	public void testTwixtScreenshot() {
		this.tearDown();

		try {
			TwixTTest.screen.click(this.twixtSelection);
		} catch (FindFailed e) {
			fail("Set up failed due to: " + e.getMessage());
		}

		assertTrue(TwixTTest.screen.exists(this.screenshot) != null);
	}

	/**
	 * Test if the TwixT description in the GameExplorer is displayed correctly.
	 */
	@Test
	public void testTwixtDescription() {
		this.tearDown();

		try {
			TwixTTest.screen.click(this.twixtSelection);
		} catch (FindFailed e) {
			fail("Set up failed due to: " + e.getMessage());
		}

		assertTrue(TwixTTest.screen.exists(this.description) != null);
	}

	/**
	 * Check if the first appearance of the TwixT game is correct.
	 */
	@Test
	public void testTwixtExistence() {
		assertTrue(TwixTTest.screen.exists(this.start) != null);
	}

	/**
	 * Set a tower for each player.
	 */
	@Test
	public void testSetTowers() {
		try {
			@SuppressWarnings("deprecation")
			Region region1 = new Region(565, 425, 0, 0);
			region1.setW(70);
			region1.setH(70);
			region1.highlight(1);

			if (region1.exists(this.spot) != null) {
				region1.click(this.spot);
				assertTrue(region1.exists(this.firstPlayerTower) != null);
			}

			@SuppressWarnings("deprecation")
			Region region2 = new Region(605, 425, 0, 0);
			region2.setW(70);
			region2.setH(70);
			region2.highlight(1);

			if (region2.exists(this.spot) != null) {
				region2.click(this.spot);
				assertTrue(region2.exists(this.secondPlayerTower) != null);
			}
		} catch (FindFailed e) {
			fail("Test failed due to: " + e.getMessage());
		}
	}

	/**
	 * Test that only towers with a "knight's move" away can be connected.
	 */
	@Test
	public void testConnectTowers() {
		try {
			region1.highlight(1);
			if (region1.exists(this.spot) != null) {
				region1.click(this.spot);
			}

			region2.highlight(1);
			if (region2.exists(this.spot) != null) {
				region2.click(this.spot);
			}

			@SuppressWarnings("deprecation")
			Region regionGroup = new Region(530, 425, 0, 0);
			regionGroup.setW(140);
			regionGroup.setH(140);
			regionGroup.highlight(1);

			// there shouldn't be an edge
			assertFalse(regionGroup.exists(this.singleRedEdge.similar((float) 0.8)) != null);
			region3.highlight(1);
			region3.click(this.firstPlayerTower);
			region1.click(this.firstPlayerTower);

			// edge visible
			assertTrue(regionGroup.exists(this.singleRedEdge.similar((float) 0.8)) != null);
		} catch (FindFailed e) {
			fail("Test failed due to: " + e.getMessage());
		}
	}

	/**
	 * Test various status messages.
	 */
	@Test
	public void testStatusMessages() {
		try {
			region1.highlight(1);
			if (region1.exists(this.spot) != null) {
				region1.click(this.spot);
			}

			// 'place a tower or wall'
			assertTrue(TwixTTest.screen.exists(this.towerDemand) != null);

			region2.highlight(1);
			if (region2.exists(this.spot) != null) {
				region2.click(this.spot);
			}

			// click enemy's tower, resulting in an error
			region2.click(this.secondPlayerTower);
			assertTrue(TwixTTest.screen.exists(this.enemyTower) != null);

			region3.highlight(1);
			region3.click(this.firstPlayerTower);
			region1.click(this.firstPlayerTower);

			// cannot place wall
			region2.click(this.secondPlayerTower);
			TwixTTest.screen.click(this.secondPlayerTower);
			assertTrue(TwixTTest.screen.exists(this.invalidWall) != null);

			region4.highlight(1);
			region4.click(this.spot);

			region5.highlight(1);
			region5.click(this.spot);

			region6.highlight(1);
			region6.click(this.spot);

			region1.click(this.firstPlayerTower);
			region5.click(this.firstPlayerTower);

			// intersecting edges
			region4.click(this.secondPlayerTower);
			region2.click(this.secondPlayerTower);

			assertTrue(TwixTTest.screen.exists(this.intersectingWalls) != null);
		} catch (FindFailed e) {
			fail("Test failed due to: " + e.getMessage());
		}
	}

	/**
	 * Test a winning game.
	 */
	@Test
	public void testWinningGame() {
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

			assertTrue(TwixTTest.screen.exists(this.bobWins) != null);

			TwixTTest.screen.type(Key.ENTER);
			TwixTTest.screen.type(Key.TAB);
			TwixTTest.screen.type(Key.SPACE);
		} catch (FindFailed e) {
			fail("Test failed due to: " + e.getMessage());
		}
	}

	/**
	 * Test that only towers with a "knight's move" away can be connected.
	 */
	@Test
	public void testSaveAndLoad() {
		try {
			region1.highlight(1);
			if (region1.exists(this.spot) != null) {
				region1.click(this.spot);
			}

			region2.highlight(1);
			if (region2.exists(this.spot) != null) {
				region2.click(this.spot);
			}

			@SuppressWarnings("deprecation")
			Region regionGroup = new Region(530, 425, 0, 0);
			regionGroup.setW(140);
			regionGroup.setH(140);
			regionGroup.highlight(1);

			region3.highlight(1);
			region3.click(this.firstPlayerTower);
			region1.click(this.firstPlayerTower);

			// save game
			TwixTTest.screen.click(this.gameMenuItem);
			TwixTTest.screen.click(this.saveMenuItem);
			TwixTTest.screen.wait(this.saveDialog, 2);

			assertTrue(TwixTTest.screen.exists(this.saveDialog) != null);

			TwixTTest.screen.type(null, "Test", 0);
			TwixTTest.screen.click(this.saveBtn);

			if (TwixTTest.screen.exists(this.overrideSavegame) != null) {
				TwixTTest.screen.click(this.yesBtn);
			}

			// restart game
			TwixTTest.screen.click(this.gameMenuItem);
			TwixTTest.screen.click(this.restartMenuItem);

			// load game
			TwixTTest.screen.click(this.gameMenuItem);
			TwixTTest.screen.click(this.quitMenuItem);
			TwixTTest.screen.click(this.yesBtn);

			// load from GameExplorer
			TwixTTest.screen.click(this.twixtSelection);
			TwixTTest.screen.click(this.startBtn);
			TwixTTest.screen.click(this.loadFromGameExplorerBtn);
			TwixTTest.screen.click(this.saveGame);
			TwixTTest.screen.click(this.openBtn);
			assertTrue(TwixTTest.screen.exists(this.loadedGame) != null);
		} catch (FindFailed e) {
			fail("Test failed due to: " + e.getMessage());
		}
	}

	/**
	 * Test the functionality of the help button and partially compare the help
	 * page.
	 */
	@Test
	public void testHelpPage() {
		try {
			this.tearDown();
			TwixTTest.screen.click(this.twixtSelection);
			TwixTTest.screen.click(this.helpBtn);
			assertTrue(TwixTTest.screen.exists(this.helpPage) != null);
			TwixTTest.screen.wait(this.helpPage, 5);
			TwixTTest.screen.type(Key.TAB, KeyModifier.CMD);
		} catch (FindFailed e) {
			fail("Test didn't succeed, due to: " + e.getMessage());
		}
	}
}
