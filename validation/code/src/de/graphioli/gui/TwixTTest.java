package de.graphioli.gui;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import de.graphioli.controller.GameManager;

public class TwixTTest {
	private String screensDir = "./application/screens/";
	private String twixtScreenPath = "./application/screens/TwixT/";
	private String playerOne = "Bob";
	private String playerTwo = "Alice";
	
	private static Screen screen = new Screen();
	
	// buttons
	private Pattern startBtn = new Pattern(this.screensDir + "start_btn.png");
	private Pattern newGameBtn = new Pattern(this.screensDir + "newGame.png");
	private Pattern okBtn = new Pattern(this.screensDir + "ok_btn.png");
	private Pattern yesBtn = new Pattern(this.screensDir + "yes_btn.png");
	
	// menu items
	private Pattern gameMenuItem = new Pattern(this.screensDir + "gameMenuItem.png");
	private Pattern quitMenuItem = new Pattern(this.screensDir + "quitMenuItem.png");
	
	// dialogs
	private Pattern firstPlayerInput = new Pattern(this.screensDir + "playerInputFirst.png");
	private Pattern secondPlayerInput = new Pattern(this.screensDir + "playerInputSecond.png");
	
	// assertions
	private Pattern twixtStart = new Pattern(this.twixtScreenPath + "twixtStart.png");
	private Pattern twixtSelection = new Pattern(this.twixtScreenPath + "twixtSelection.png");
	private Pattern twixtSelectionOld = new Pattern(this.twixtScreenPath + "twixtSelectionOld.png");

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
	}
	
	@Before
	public void setUp() {
		try {
			TwixTTest.screen.click(this.twixtSelectionOld);
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
	
	@After
	public void tearDown() {
		// quit first
		try {
			TwixTTest.screen.click(this.gameMenuItem);
			TwixTTest.screen.click(this.quitMenuItem);
			TwixTTest.screen.click(this.yesBtn);
		} catch (FindFailed e) {
			fail("Couldn't tear down previous test due to: " + e.getMessage());
		}
	}
	
	@Test
	public void testTwixtExistence() {
		//assertTrue(TwixTTest.screen.exists(this.twixtStart) != null);
	}
}
