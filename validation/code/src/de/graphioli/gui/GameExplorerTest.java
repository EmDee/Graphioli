package de.graphioli.gui;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import de.graphioli.controller.GameManager;

public class GameExplorerTest {
	private String playerName = "Team Graphioli";
	private String screensDir = "./application/screens/";
	private Screen screen = new Screen();
	
	@Before
	public void setUp() {
		GameManager.main(null);
	}

	@Test
	public void testVisibilityOfButtons() {
		assertTrue(this.screen.exists(this.screensDir + "start_btn.png") != null);
		assertTrue(this.screen.exists(this.screensDir + "help_btn.png") != null);
		assertTrue(this.screen.exists(this.screensDir + "quit_btn.png") != null);
	}

	@Test
	public void testStartGameExplorer() {
		assertTrue(this.screen.exists(this.screensDir + "gameExplorer.png") != null);
	}
}
