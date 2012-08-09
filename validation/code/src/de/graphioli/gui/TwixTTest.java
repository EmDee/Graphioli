package de.graphioli.gui;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
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
	
	private Screen screen = new Screen();
	
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

	@Before
	public void setUp() {
		GameManager.main(null);
		try {
			this.screen.click(this.twixtSelectionOld);
			this.screen.click(this.startBtn);
			this.screen.click(this.newGameBtn);
			this.screen.wait(this.firstPlayerInput);
			this.screen.type(null, this.playerOne, 0);
			this.screen.click(this.okBtn);
			this.screen.wait(this.secondPlayerInput);
			this.screen.type(null, this.playerTwo, 0);
			this.screen.click(this.okBtn);
		} catch (FindFailed e) {
			fail("Set up failed due to: " + e.getMessage());
		}
	}
	
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
	
	@Test
	public void testTwixtExistence() {
		assertTrue(this.screen.exists(this.twixtStart) != null);
	}
}
