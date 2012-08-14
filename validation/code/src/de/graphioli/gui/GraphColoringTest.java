/**
 * 
 */
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
import org.sikuli.script.Screen;

import de.graphioli.controller.GameManager;

// TODO: F1, F2, F3, Space Key integration

/**
 * This class tests all functionality of the GraphColoring game.
 * 
 * @author Team Graphioli
 */
public class GraphColoringTest {
	private String screensDir = "./application/screens/";
	private String graphColoringScreenPath = "./application/screens/GraphColoring/";
	private String playerOne = "Bob";
	private String playerTwo = "Alice";

	private static Screen screen = new Screen();

	// buttons
	private Pattern okBtn = new Pattern(this.screensDir + "ok_btn.png");
	private Pattern startBtn = new Pattern(this.screensDir + "start_btn.png");
	private Pattern cancelBtn = new Pattern(this.screensDir + "cancel_btn.png");
	private Pattern helpBtn = new Pattern(this.screensDir + "help_btn.png");
	private Pattern newGameBtn = new Pattern(this.screensDir + "newGame.png");
	private Pattern noBtn = new Pattern(this.screensDir + "no_btn.png");
	private Pattern saveBtn = new Pattern(this.screensDir + "save_btn.png");
	private Pattern openBtn = new Pattern(this.screensDir + "open_btn.png");
	private Pattern yesBtn = new Pattern(this.screensDir + "yes_btn.png");

	// messages
	private Pattern duplicateName = new Pattern(this.screensDir + "duplicateName.png");
	private Pattern invalidMove = new Pattern(this.graphColoringScreenPath + "gcInvalidMove.png");
	private Pattern invalidColor = new Pattern(this.graphColoringScreenPath + "gcInvalidColor.png");
	private Pattern alreadyColored = new Pattern(this.graphColoringScreenPath + "gcAlreadyColored.png");
	private Pattern graphCanvas = new Pattern(this.graphColoringScreenPath + "gcGraphCanvas.png");
	private Pattern graphColored = new Pattern(this.graphColoringScreenPath + "gcGraphColored.png");

	// menu items
	private Pattern quitMenuItem = new Pattern(this.screensDir + "quitMenuItem.png");
	private Pattern gameMenuItem = new Pattern(this.screensDir + "gameMenuItem.png");
	private Pattern nextLevelItem = new Pattern(this.graphColoringScreenPath + "gcNextLevelItem.png");
	private Pattern prevLevelItem = new Pattern(this.graphColoringScreenPath + "gcPrevLevelItem.png");
	private Pattern optionsMenuItem = new Pattern(this.screensDir + "optionsMenuItem.png");
	private Pattern saveMenuItem = new Pattern(this.screensDir + "saveMenuItem.png");
	private Pattern loadMenuItem = new Pattern(this.screensDir + "loadMenuItem.png");
	private Pattern restartMenuItem = new Pattern(this.screensDir + "restartMenuItem.png");

	// dialogs
	private Pattern newOrLoadGame = new Pattern(this.screensDir + "newOrLoadGame.png");
	private Pattern quitDialog = new Pattern(this.screensDir + "quitConfirmationDialog.png");
	private Pattern aliceWins = new Pattern(this.graphColoringScreenPath + "gcAliceWins.png");
	private Pattern twoPlayers = new Pattern(this.screensDir + "twoPlayers.png");
	private Pattern playerInput = new Pattern(this.screensDir + "player_input.png");
	private Pattern firstPlayerInput = new Pattern(this.screensDir + "playerInputFirst.png");
	private Pattern secondPlayerInput = new Pattern(this.screensDir + "playerInputSecond.png");
	private Pattern playerSelection = new Pattern(this.screensDir + "playerSelection.png");
	private Pattern saveDialog = new Pattern(this.screensDir + "saveDialog.png");

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
	private Pattern secondLevel = new Pattern(this.graphColoringScreenPath + "gcSecondLevel.png");
	private Pattern greenButton = new Pattern(this.graphColoringScreenPath + "gcGreenBtn.png");
	private Pattern blueButton = new Pattern(this.graphColoringScreenPath + "gcBlueBtn.png");

	// miscellaneous
	private Pattern graphColoringText = new Pattern(this.screensDir + "graphColoringSelect.png");
	private Pattern directedGameText = new Pattern(this.screensDir + "directedGame.png");
	private Pattern graphColoringSelected = new Pattern(this.graphColoringScreenPath + "gcSelectionSelected.png");
	private Pattern graphColoringGame = new Pattern(this.screensDir + "graphColoringGame.png");
	private Pattern helpPage = new Pattern(this.graphColoringScreenPath + "gcHelp.png");
	private Pattern loadedGame = new Pattern(this.graphColoringScreenPath + "gcLoadedGame.png");
	private Pattern saveGame = new Pattern(this.screensDir + "saveGame.png");
	private Pattern overrideSavegame = new Pattern(this.screensDir + "overrideSavegame.png");

	/**
	 * Set up one instance of the GameExplorer before all tests.
	 */
	@BeforeClass
	public static void beforeClass() {
		// indication for existence of GameExplorer
		Pattern gameExplorerExistance = new Pattern("./application/screens/gameExplorerExistence.png");

		if (GraphColoringTest.screen.exists(gameExplorerExistance) == null) {
			GameManager.main(null);
		}
	}

	/**
	 * General set up before every test.
	 */
	@Before
	public void setUp() {
		if (GraphColoringTest.screen.exists(this.graphColoringSelected) == null) {
			try {
				GraphColoringTest.screen.click(this.graphColoringText, 0);
				GraphColoringTest.screen.click(this.startBtn);
				GraphColoringTest.screen.click(this.newGameBtn);
			} catch (FindFailed e) {
				fail("Set up failed due to: " + e.getMessage());
			}
		} else {
			try {
				GraphColoringTest.screen.click(this.startBtn);
				GraphColoringTest.screen.click(this.newGameBtn);
			} catch (FindFailed e) {
				fail("Set up failed due to: " + e.getMessage());
			}
		}
	}

	/**
	 * If the game window is existent, then close it to return to GameExplorer.
	 */
	@After
	public void tearDown() {
		if (GraphColoringTest.screen.exists(this.gameMenuItem) != null) {
			try {
				GraphColoringTest.screen.click(this.gameMenuItem);
				GraphColoringTest.screen.click(this.quitMenuItem);
				GraphColoringTest.screen.click(this.yesBtn);
			} catch (FindFailed e) {
				fail("Tear down failed due to: " + e.getMessage());
			}
		}
		if (GraphColoringTest.screen.exists(this.graphColoringSelected) != null) {
			try {
				GraphColoringTest.screen.click(this.directedGameText);
			} catch (FindFailed e) {
				fail("Couldn't click on directedGameText, due to: " + e.getMessage());
			}
		}
	}

	/**
	 * Test if the GraphColoring game is in the menu selection list and
	 * validates if the screenshot and description field are being loaded
	 * correctly.
	 */
	@Test
	public void testGraphColoringExistence() {
		try {
			GraphColoringTest.screen.click(this.cancelBtn);
			GraphColoringTest.screen.click(this.graphColoringText, 0);
		} catch (FindFailed e) {
			System.out.println("Couldn't find GraphColoring text in menu list");
		}
		assertTrue(GraphColoringTest.screen.exists(this.graphColoringGame.similar((float) 0.4)) != null);
	}

	/**
	 * Test if the pop up opens for choosing between a new game or to load a
	 * game.
	 */
	@Test
	public void testNewOrLoadSelection() {
		try {
			GraphColoringTest.screen.click(this.cancelBtn);
			GraphColoringTest.screen.click(this.graphColoringText, 0);
			GraphColoringTest.screen.click(this.startBtn);
		} catch (FindFailed e) {
			System.out.println("Couldn't find GraphColoring text in menu list");
		}
		assertTrue(GraphColoringTest.screen.exists(this.newOrLoadGame) != null);

		// cancel
		try {
			GraphColoringTest.screen.click(this.cancelBtn);
		} catch (FindFailed e) {
			fail("Couldn't find cancel button");
		}
	}

	/**
	 * Starting a game should open up the game window with the first level.
	 */
	@Test
	public void testGameWindow() {
		try {
			GraphColoringTest.screen.click(this.okBtn);
			GraphColoringTest.screen.wait(this.playerInput);
			GraphColoringTest.screen.type(null, this.playerOne, 0);
			GraphColoringTest.screen.click(this.okBtn);
			assertTrue(GraphColoringTest.screen.exists(this.graphCanvas.similar((float) 0.6)) != null);
		} catch (FindFailed e) {
			fail("Test failed due to: " + e.getMessage());
		}

	}

	/**
	 * Test a one player game.s
	 */
	@Test
	public void testOnePlayerGame() {
		try {
			GraphColoringTest.screen.click(this.okBtn);
			GraphColoringTest.screen.wait(this.playerInput, 2);
			GraphColoringTest.screen.type(null, this.playerOne, 0);
			GraphColoringTest.screen.click(this.okBtn);

			// TODO: F1-F3 keys for changing colors

			// red button should be selected by default
			assertTrue(GraphColoringTest.screen.exists(this.redSelected) != null);

			GraphColoringTest.screen.click(this.topLeftCorner);

			// error, because same adjacent color
			GraphColoringTest.screen.click(this.topRightCorner);
			assertTrue(GraphColoringTest.screen.exists(this.invalidColor) != null);

			// select green color
			GraphColoringTest.screen.click(this.greenButton);
			assertTrue(GraphColoringTest.screen.exists(this.greenSelected) != null);
			GraphColoringTest.screen.click(this.topRightCornerRed);

			// select blue color
			GraphColoringTest.screen.click(this.blueButton);
			assertTrue(GraphColoringTest.screen.exists(this.blueSelected) != null);
			GraphColoringTest.screen.click(this.bottomLeftCorner);
			GraphColoringTest.screen.click(this.bottomRightCorner);

			assertTrue(GraphColoringTest.screen.exists(this.graphColored) != null);

			// TODO: Space for next level
		} catch (FindFailed e) {
			fail("Test didn't succeed, due to: " + e.getMessage());
		}
	}

	/**
	 * Test a two player game, where the second player wins.
	 */
	@Test
	public void testTwoPlayerGame() {
		try {
			// select two players
			GraphColoringTest.screen.click(this.playerSelection);
			GraphColoringTest.screen.click(this.twoPlayers);
			GraphColoringTest.screen.click(this.okBtn);

			// enter names for players
			GraphColoringTest.screen.wait(this.firstPlayerInput);
			GraphColoringTest.screen.type(null, this.playerOne, 0);
			GraphColoringTest.screen.click(this.okBtn);
			GraphColoringTest.screen.wait(this.secondPlayerInput);
			GraphColoringTest.screen.type(null, this.playerOne, 0);
			GraphColoringTest.screen.click(this.okBtn);

			assertTrue(GraphColoringTest.screen.exists(this.duplicateName.similar((float) 0.2)) != null);
			GraphColoringTest.screen.type(Key.ENTER);
			// GraphColoringTest.screen.click(this.okBtn);

			GraphColoringTest.screen.type(null, this.playerTwo, 0);
			GraphColoringTest.screen.click(this.okBtn);

			// red button should be selected by default
			assertTrue(GraphColoringTest.screen.exists(this.redSelected) != null);

			GraphColoringTest.screen.click(this.topRightCorner);

			// already colored
			GraphColoringTest.screen.click(this.topRightCornerRed);
			assertTrue(GraphColoringTest.screen.exists(this.alreadyColored) != null);

			// invalid move
			GraphColoringTest.screen.click(this.topLeftCorner);
			assertTrue(GraphColoringTest.screen.exists(this.invalidMove) != null);

			// select green color
			GraphColoringTest.screen.click(this.greenButton);
			assertTrue(GraphColoringTest.screen.exists(this.greenSelected) != null);
			GraphColoringTest.screen.click(this.topLeftCorner);

			// select blue color
			GraphColoringTest.screen.click(this.blueButton);
			assertTrue(GraphColoringTest.screen.exists(this.blueSelected) != null);
			GraphColoringTest.screen.click(this.bottomLeftCorner);
			GraphColoringTest.screen.click(this.bottomRightCorner);

			assertTrue(GraphColoringTest.screen.exists(this.aliceWins) != null);
			GraphColoringTest.screen.click(this.okBtn);
			GraphColoringTest.screen.type(Key.TAB);
			GraphColoringTest.screen.type(Key.SPACE);
			GraphColoringTest.screen.click(this.noBtn);
		} catch (FindFailed e) {
			fail("Test didn't succeed, due to: " + e.getMessage());
		}
	}
	
	/**
	 * Test restart.
	 */
	@Test
	public void testRestartGameAfterWon() {
		try {
			// select two players
			GraphColoringTest.screen.click(this.playerSelection);
			GraphColoringTest.screen.click(this.twoPlayers);
			GraphColoringTest.screen.click(this.okBtn);

			// enter names for players
			GraphColoringTest.screen.wait(this.firstPlayerInput);
			GraphColoringTest.screen.type(null, this.playerOne, 0);
			GraphColoringTest.screen.click(this.okBtn);
			GraphColoringTest.screen.wait(this.secondPlayerInput);
			GraphColoringTest.screen.type(null, this.playerTwo, 0);
			GraphColoringTest.screen.click(this.okBtn);

			GraphColoringTest.screen.click(this.topRightCorner);

			// select green color
			GraphColoringTest.screen.type(Key.F2);
			GraphColoringTest.screen.click(this.topLeftCorner);

			// select blue color
			GraphColoringTest.screen.type(Key.F3);
			GraphColoringTest.screen.click(this.bottomLeftCorner);
			
			// select red and blue again
			GraphColoringTest.screen.type(Key.F1);
			GraphColoringTest.screen.type(Key.F3);
			GraphColoringTest.screen.click(this.bottomRightCorner);

			GraphColoringTest.screen.click(this.okBtn);
			GraphColoringTest.screen.click(this.yesBtn);
			assertTrue(GraphColoringTest.screen.exists(this.graphCanvas) != null);
		} catch (FindFailed e) {
			fail("Test didn't succeed, due to: " + e.getMessage());
		}
	}

	/**
	 * Test the functionality to load the next and previous level.
	 */
	@Test
	public void testNextAndPrevLevel() {
		try {
			GraphColoringTest.screen.click(this.okBtn);
			GraphColoringTest.screen.wait(this.playerInput, 5);
			GraphColoringTest.screen.type(null, this.playerOne, 0);
			GraphColoringTest.screen.click(this.okBtn);

			// next level (10 times to cover all levels)
			for (int i = 0; i < 10; i++) {
				GraphColoringTest.screen.click(this.optionsMenuItem);
				GraphColoringTest.screen.click(this.nextLevelItem);
			}

			assertTrue(GraphColoringTest.screen.exists(this.secondLevel) != null);

			// prev level
			GraphColoringTest.screen.click(this.optionsMenuItem);
			GraphColoringTest.screen.click(this.prevLevelItem);

			assertTrue(GraphColoringTest.screen.exists(this.graphCanvas) != null);
		} catch (FindFailed e) {
			fail("Test didn't succeed, due to: " + e.getMessage());
		}
	}

	/**
	 * Test the quitting of a game.
	 */
	@Test
	public void testQuitGame() {
		try {
			GraphColoringTest.screen.click(this.okBtn);
			GraphColoringTest.screen.wait(this.playerInput);
			GraphColoringTest.screen.type(null, this.playerOne, 0);
			GraphColoringTest.screen.click(this.okBtn);

			// quit
			GraphColoringTest.screen.click(this.gameMenuItem);
			GraphColoringTest.screen.click(this.quitMenuItem);

			assertTrue(GraphColoringTest.screen.exists(this.quitDialog) != null);

			GraphColoringTest.screen.type(Key.ENTER);
		} catch (FindFailed e) {
			fail("Test didn't succeed, due to: " + e.getMessage());
		}
	}

	/**
	 * Tests the functionality of saving and loading a game.
	 */
	@Test
	public void testSaveAndLoadGame() {
		try {
			GraphColoringTest.screen.click(this.okBtn);
			GraphColoringTest.screen.wait(this.playerInput, 2);
			GraphColoringTest.screen.type(null, this.playerOne, 0);
			GraphColoringTest.screen.click(this.okBtn);

			GraphColoringTest.screen.click(this.topLeftCorner);
			GraphColoringTest.screen.click(this.gameMenuItem);
			GraphColoringTest.screen.click(this.saveMenuItem);
			GraphColoringTest.screen.wait(this.saveDialog, 2);

			assertTrue(GraphColoringTest.screen.exists(this.saveDialog) != null);

			GraphColoringTest.screen.type(null, "Test", 0);
			GraphColoringTest.screen.click(this.saveBtn);

			if (GraphColoringTest.screen.exists(this.overrideSavegame) != null) {
				assertTrue(GraphColoringTest.screen.exists(this.overrideSavegame) != null);
				GraphColoringTest.screen.click(this.yesBtn);
			} else {
				// cause override dialog
				GraphColoringTest.screen.click(this.gameMenuItem);
				GraphColoringTest.screen.click(this.saveMenuItem);
				GraphColoringTest.screen.wait(this.saveDialog, 2);

				GraphColoringTest.screen.type(null, "Test", 0);
				GraphColoringTest.screen.click(this.saveBtn);
				GraphColoringTest.screen.click(this.yesBtn);
			}

			// restart game
			GraphColoringTest.screen.click(this.gameMenuItem);
			GraphColoringTest.screen.click(this.restartMenuItem);

			// load game
			GraphColoringTest.screen.click(this.gameMenuItem);
			GraphColoringTest.screen.click(this.loadMenuItem);
			GraphColoringTest.screen.click(this.saveGame);
			GraphColoringTest.screen.click(this.openBtn);
			assertTrue(GraphColoringTest.screen.exists(this.loadedGame) != null);
		} catch (FindFailed e) {
			fail("Test didn't succeed, due to: " + e.getMessage());
		}
	}

	/**
	 * Test the functionality of the help button and partially compare the help
	 * page.
	 */
	@Test
	public void testHelpPage() {
		try {
			GraphColoringTest.screen.click(this.cancelBtn);
			GraphColoringTest.screen.click(this.graphColoringText, 0);
			GraphColoringTest.screen.click(this.helpBtn);
			assertTrue(GraphColoringTest.screen.exists(this.helpPage) != null);
			GraphColoringTest.screen.wait(this.helpPage, 5);
			GraphColoringTest.screen.type(Key.TAB, KeyModifier.CMD);
		} catch (FindFailed e) {
			fail("Test didn't succeed, due to: " + e.getMessage());
		}
	}
}