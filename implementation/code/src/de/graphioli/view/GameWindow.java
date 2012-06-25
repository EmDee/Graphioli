package de.graphioli.view;

import de.graphioli.controller.ViewManager;
import java.io.File;
import javax.swing.JFrame;

import de.graphioli.model.Player;

/**
 * 
 * 
 * @author Graphioli
 */
public class GameWindow extends JFrame implements View {

	/**
	 * UID for serializing this object
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The controlling {@link ViewManager} of this class.
	 */
	private ViewManager viewManager;

	/**
	 * The {@link GraphCanvas} associated with the this {@link GameWindow}.
	 */
	private GraphCanvas graphCanvas;

	/**
	 * The {@link MenuBar} associated with the this {@link GameWindow}.
	 */
	private MenuBar menuBar;

	/**
	 * The {@link StatusBar} associated with the this {@link GameWindow}.
	 */
	private StatusBar statusBar;

	/**
	 * Constructs a {@link GameWindow} including {@link GraphCanvas},
	 * {@link StatusBar} and {@link MenuBar}.
	 */
	public GameWindow(ViewManager viewManager) {
		this.registerController(viewManager);
		this.menuBar = new MenuBar(this);
		this.graphCanvas = new GraphCanvas(this);
		this.statusBar = new StatusBar();
		this.add(this.menuBar);
		this.add(this.graphCanvas);
		this.add(this.statusBar);
		this.setSize(500, 500);
		this.statusBar.setSize(500, 100);
		this.setResizable(false);
		this.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Registers a {@link ViewManager} as the controller for the user interface.
	 * 
	 * @param viewManager
	 *            The controlling ViewManager
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	@Override
	public boolean registerController(ViewManager viewManager) {
		this.viewManager = viewManager;
		return true;
	}

	/**
	 * Displays a message in a pop-up.
	 * 
	 * @param message
	 *            The message to display
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	@Override
	public boolean displayPopUp(String message) {
		return true;
	}

	/**
	 * Adds a custom {@link MenuItem} to the menu.
	 * 
	 * @param item
	 *            The MenuItem to add
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 * @todo Facultative
	 */
	// public boolean addCustomMenuItem(MenuItem item);

	/**
	 * Updates which {@link Player} is displayed as active.
	 * 
	 * @param player
	 *            The new active player
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	@Override
	public boolean updatePlayerStatus(Player player) {
		if (this.statusBar.updatePlayerStatus(player)) {
			return true;
		}
		return false;
	}

	/**
	 * Displays an error message.
	 * 
	 * @param message
	 *            The message to display
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	@Override
	public boolean displayErrorMessage(String message) {
		if (this.statusBar.displayErrorMessage(message)) {
			return true;
		}
		return false;
	}

	/**
	 * Redraws the {@link Graph}.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	@Override
	public boolean redrawGraph() {
		return true;
	}

	/**
	 * Sets the size of the {@link VisualVertex}es displayed.
	 * 
	 * @param size
	 *            The size of the vertices
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	@Override
	public boolean setVisualVertexSize(int size) {
		return true;
	}

	/**
	 * Returns the {@link ViewManager} associated with the {@link GameWindow}.
	 * 
	 * @return viewManager The associated {@link ViewManager}
	 */
	public ViewManager getViewManager() {
		return viewManager;
	}

	/**
	 * Forwards the key input to the {@link ViewManager}.
	 * 
	 * @param keyCode
	 *            The code of the key that was released
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean onKeyRelease(int keyCode) {
		return true;
	}

	/**
	 * Opens a load file dialog.
	 * 
	 * @return File The file of saved game to load
	 */
	public File openFileDialog() {
		return null;

	}

	/**
	 * Opens a save file dialog.
	 * 
	 * @return File The file the game should be saved
	 */
	public File saveFileDialog() {
		return null;

	}
}
