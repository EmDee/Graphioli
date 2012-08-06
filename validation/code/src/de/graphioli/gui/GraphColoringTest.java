/**
 * 
 */
package de.graphioli.gui;

import static org.junit.Assert.*;

import java.awt.AWTException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.sikuli.script.SikuliScript;

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

	private Screen screen = new Screen();
	private SikuliScript sikuliSkript;

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
	private Pattern graphColoringGame = new Pattern(this.screensDir + "graphColoringGame.png");
	private Pattern helpPage = new Pattern(this.graphColoringScreenPath + "gcHelp.png");
	private Pattern loadedGame = new Pattern(this.graphColoringScreenPath + "gcLoadedGame.png");
	private Pattern saveGame = new Pattern(this.screensDir + "saveGame.png");
	private Pattern overrideSavegame = new Pattern(this.screensDir + "overrideSavegame.png");

	public GraphColoringTest() {
		try {
			this.sikuliSkript = new SikuliScript();
		} catch (AWTException e) {
			fail("Couldn't initialize SikuliScript");
		}
	}

	/**
	 * Create a new instance of the GameExplorer befor eevery test.
	 */
	@Before
	public void setUp() {
		GameManager.main(null);
	}

	/**
	 * Test if the GraphColoring game is in the menu selection list and
	 * validates if the screenshot and description field are being loaded
	 * correctly.
	 */
	@Test
	public void testGraphColoringExistence() {
		try {
			this.screen.click(this.graphColoringText, 0);
		} catch (FindFailed e) {
			System.out.println("Couldn't find GraphColoring text in menu list");
		}
		assertTrue(this.screen.exists(this.graphColoringGame.similar((float) 0.4)) != null);
	}

	/**
	 * Test if the pop up opens for choosing between a new game or to load a
	 * game.
	 */
	@Test
	public void testNewOrLoadSelection() {
		try {
			this.screen.click(this.graphColoringText, 0);
			this.screen.click(this.startBtn);
		} catch (FindFailed e) {
			System.out.println("Couldn't find GraphColoring text in menu list");
		}
		assertTrue(this.screen.exists(this.newOrLoadGame) != null);

		// cancel
		try {
			this.screen.click(this.cancelBtn);
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
			this.screen.click(this.graphColoringText, 0);
			this.screen.click(this.screensDir + "start_btn.png");
			this.screen.click(this.screensDir + "newGame.png");
			this.screen.click(this.okBtn);
			this.screen.wait(this.screensDir + "player_input.png");
			this.screen.type(null, this.playerOne, 0);
			this.screen.click(this.okBtn);
			assertTrue(this.screen.exists(this.graphCanvas.similar((float) 0.6)) != null);
		} catch (FindFailed e) {
			System.out.println("Mop!");
		}

	}

	/**
	 * Test a one player game.s
	 */
	@Test
	public void testOnePlayerGame() {
		try {
			this.screen.click(this.graphColoringText, 0);
			this.screen.click(this.startBtn);
			this.screen.click(this.newGameBtn);
			this.screen.click(this.okBtn);
			this.screen.wait(this.playerInput, 2);
			this.screen.type(null, this.playerOne, 0);
			this.screen.click(this.okBtn);

			// TODO: F1-F3 keys for changing colors
			
			// red button should be selected by default
			assertTrue(this.screen.exists(this.redSelected) != null);

			this.screen.click(this.topLeftCorner);

			// error, because same adjacent color
			this.screen.click(this.topRightCorner);
			assertTrue(this.screen.exists(this.invalidColor) != null);

			// select green color
			this.screen.click(this.greenButton);
			assertTrue(this.screen.exists(this.greenSelected) != null);
			this.screen.click(this.topRightCornerRed);

			// select blue color
			this.screen.click(this.blueButton);
			assertTrue(this.screen.exists(this.blueSelected) != null);
			this.screen.click(this.bottomLeftCorner);
			this.screen.click(this.bottomRightCorner);

			assertTrue(this.screen.exists(this.graphColored) != null);
			
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
			this.screen.click(this.graphColoringText, 0);
			this.screen.click(this.startBtn);
			this.screen.click(this.screensDir + "newGame.png");

			// select two players
			this.screen.click(this.playerSelection);
			this.screen.click(this.twoPlayers);
			this.screen.click(this.okBtn);

			// enter names for players
			this.screen.wait(this.firstPlayerInput);
			this.screen.type(null, this.playerOne, 0);
			this.screen.click(this.okBtn);
			this.screen.wait(this.secondPlayerInput);
			this.screen.type(null, this.playerOne, 0);
			this.screen.click(this.okBtn);

			assertTrue(this.screen.exists(this.duplicateName.similar((float) 0.2)) != null);
			this.screen.type(Key.ENTER);
			// this.screen.click(this.okBtn);

			this.screen.type(null, this.playerTwo, 0);
			this.screen.click(this.okBtn);

			// red button should be selected by default
			assertTrue(this.screen.exists(this.redSelected) != null);

			this.screen.click(this.topRightCorner);

			// already colored
			this.screen.click(this.topRightCornerRed);
			assertTrue(this.screen.exists(this.alreadyColored) != null);

			// invalid move
			this.screen.click(this.topLeftCorner);
			assertTrue(this.screen.exists(this.invalidMove) != null);

			// select green color
			this.screen.click(this.greenButton);
			assertTrue(this.screen.exists(this.greenSelected) != null);
			this.screen.click(this.topLeftCorner);

			// select blue color
			this.screen.click(this.blueButton);
			assertTrue(this.screen.exists(this.blueSelected) != null);
			this.screen.click(this.bottomLeftCorner);
			this.screen.click(this.bottomRightCorner);

			assertTrue(this.screen.exists(this.aliceWins) != null);
			this.screen.click(this.okBtn.similar((float) 0.4));
			this.screen.type(Key.TAB);
			this.screen.type(Key.SPACE);
			this.screen.click(this.noBtn.similar((float) 0.4));
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
			this.screen.click(this.graphColoringText, 0);
			this.screen.click(this.screensDir + "start_btn.png");
			this.screen.click(this.screensDir + "newGame.png");
			this.screen.click(this.okBtn);
			this.screen.wait(this.screensDir + "player_input.png");
			this.screen.type(null, this.playerOne, 0);
			this.screen.click(this.okBtn);

			// next level (9 times to cover all levels)
			for (int i = 0; i < 9; i++) {
				this.screen.click(this.optionsMenuItem);
				this.screen.click(this.nextLevelItem);
			}

			assertTrue(this.screen.exists(this.secondLevel) != null);

			// prev level
			this.screen.click(this.optionsMenuItem);
			this.screen.click(this.prevLevelItem);

			assertTrue(this.screen.exists(this.graphCanvas) != null);
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
			this.screen.click(this.graphColoringText, 0);
			this.screen.click(this.screensDir + "start_btn.png");
			this.screen.click(this.screensDir + "newGame.png");
			this.screen.click(this.okBtn);
			this.screen.wait(this.screensDir + "player_input.png");
			this.screen.type(null, this.playerOne, 0);
			this.screen.click(this.okBtn);

			// quit
			this.screen.click(this.gameMenuItem);
			this.screen.click(this.quitMenuItem);

			assertTrue(this.screen.exists(this.quitDialog) != null);

			this.screen.type(Key.ENTER);
		} catch (FindFailed e) {
			fail("Test didn't succeed, due to: " + e.getMessage());
		}
	}

	/**
	 * Tests the functionality of saving and loading a game.
	 */
	@Test
	public void saveAndLoadGame() {
		try {
			this.screen.click(this.graphColoringText, 0);
			this.screen.click(this.startBtn);
			this.screen.click(this.newGameBtn);
			this.screen.click(this.okBtn);
			this.screen.wait(this.playerInput, 2);
			this.screen.type(null, this.playerOne, 0);
			this.screen.click(this.okBtn);

			this.screen.click(this.topLeftCorner);
			this.screen.click(this.gameMenuItem);
			this.screen.click(this.saveMenuItem);
			this.screen.wait(this.saveDialog, 2);

			assertTrue(this.screen.exists(this.saveDialog) != null);

			this.screen.type(null, "Test", 0);
			this.screen.click(this.saveBtn);

			if (this.screen.exists(this.overrideSavegame) != null) {
				assertTrue(this.screen.exists(this.overrideSavegame) != null);
				this.screen.click(this.yesBtn);
			} else {
				// cause override dialog
				this.screen.click(this.topLeftCorner);
				this.screen.click(this.gameMenuItem);
				this.screen.click(this.saveMenuItem);
				this.screen.wait(this.saveDialog, 2);

				this.screen.type(null, "Test", 0);
				this.screen.click(this.saveBtn);
			}

			// restart game
			this.screen.click(this.gameMenuItem);
			this.screen.click(this.restartMenuItem);

			// load game
			this.screen.click(this.gameMenuItem);
			this.screen.click(this.loadMenuItem);
			this.screen.click(this.saveGame);
			this.screen.click(this.openBtn);
			assertTrue(this.screen.exists(this.loadedGame) != null);
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
			this.screen.click(this.graphColoringText, 0);
			this.screen.click(this.helpBtn);
			assertTrue(this.screen.exists(this.helpPage) != null);
			this.screen.wait(this.helpPage, 5);
			this.screen.type(Key.TAB, KeyModifier.CMD);
		} catch (FindFailed e) {
			fail("Test didn't succeed, due to: " + e.getMessage());
		}
	}
}
