package de.graphioli.gui;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sikuli.script.Screen;

import de.graphioli.controller.GameManager;

/**
 * This class tests the appearance of the GameExplorer.
 * 
 * @author Team Graphioli
 */
public class GameExplorerTest {
	private String screensDir = "./application/screens/";
	private Screen screen = new Screen();

	/**
	 * Set up the GameExplorer.
	 */
	@BeforeClass
	public static void beforeClass() {
		GameManager.main(null);
	}

	/**
	 * Check if the GameExplorer is correctly displayed.
	 */
	@Test
	public void testStartGameExplorer() {
		assertTrue(this.screen.exists(this.screensDir + "gameExplorer.png") != null);
	}

	/**
	 * Check if all buttons are visible.
	 */
	@Test
	public void testVisibilityOfButtons() {
		assertTrue(this.screen.exists(this.screensDir + "start_btn.png") != null);
		assertTrue(this.screen.exists(this.screensDir + "help_btn.png") != null);
		assertTrue(this.screen.exists(this.screensDir + "quit_btn.png") != null);
	}
}