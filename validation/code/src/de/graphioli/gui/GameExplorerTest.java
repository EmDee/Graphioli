package de.graphioli.gui;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import de.graphioli.controller.GameManager;

/**
 * This class tests the appearance of the GameExplorer.
 * 
 * @author Team Graphioli
 */
public class GameExplorerTest {
	private String screensDir = "./application/screens/";
	private static Screen screen = new Screen();

	/**
	 * Set up one instance of the GameExplorer before all tests.
	 */
	@BeforeClass
	public static void beforeClass() {
		// indication for existence of GameExplorer
		Pattern gameExplorerExistance = new Pattern("./application/screens/gameExplorerExistence.png");

		if (GameExplorerTest.screen.exists(gameExplorerExistance) == null) {
			GameManager.main(null);
		}
	}

	/**
	 * Check if the GameExplorer is correctly displayed.
	 */
	@Test
	public void testStartGameExplorer() {
		assertTrue(GameExplorerTest.screen.exists(this.screensDir + "gameExplorer.png") != null);
	}

	/**
	 * Check if all buttons are visible.
	 */
	@Test
	public void testVisibilityOfButtons() {
		assertTrue(GameExplorerTest.screen.exists(this.screensDir + "start_btn.png") != null);
		assertTrue(GameExplorerTest.screen.exists(this.screensDir + "help_btn.png") != null);
		assertTrue(GameExplorerTest.screen.exists(this.screensDir + "quit_btn.png") != null);
	}
}