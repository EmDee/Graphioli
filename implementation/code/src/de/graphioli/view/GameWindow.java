package de.graphioli.view;

import de.graphioli.controller.ViewManager;
import de.graphioli.model.MenuItem;
import de.graphioli.model.Player;
import de.graphioli.utils.Localization;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * @author Team Graphioli
 */
public class GameWindow extends JFrame implements View {

	/**
	 * Logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(GameWindow.class.getName());

	/**
	 * UID for serializing this object.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The minimum width of the window.
	 */
	private static final int MIN_WINDOW_WIDTH = 400;

	/**
	 * The minimum height of the window.
	 */
	private static final int MIN_WINDOW_HEIGHT = 450;

	/**
	 * The height of the status bar.
	 */
	private static final int STATUSBAR_HEIGHT = 25;

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
	 * The {@link VisualGrid} associated with this {@link GameWindow}.
	 */
	private VisualGrid visualGrid;
	
	/**
	 * This windows KeyDispatcher.
	 */
	
	private CustomKeyDispatcher dispatcher;


	/**
	 * Constructs a new instance of GameWindow.
	 */
	public GameWindow() {
		this.setTitle(Localization.getLanguageString("gw_title"));
		LOG.info("GameWindow instantiated.");
	}

	@Override
	public boolean addCustomMenuItems(List<MenuItem> menu) {
		this.menuBar.addOptionsItems(menu);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean askForRestart() {
		// Ask user, if he/she wants to restart
		boolean choice = this.showConfirmDialog(Localization.getLanguageString("gw_restart"));
		return choice;
	}

	/**
	 * Disposes all components of the GameWindow.
	 * 
	 * @return {@code true} when closing was successful.
	 */
	public boolean closeView() {
		this.viewManager = null;
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.removeKeyEventDispatcher(this.dispatcher);
		
		this.dispose();
		return true;
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
	 * Displays a message in a pop-up.
	 * 
	 * @param message
	 *            The message to display
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	@Override
	public boolean displayPopUp(String message) {
		JOptionPane.showMessageDialog(this, message);
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
	 * Redraws the {@link Graph}.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	@Override
	public boolean redrawGraph() {
		return this.graphCanvas.updateCanvas();
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
		LOG.info("Starting View.");
		this.viewManager = viewManager;
		
		this.generateView();
		this.addEventListeners();
		return true;
	}

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
	 * Adds CustomKeyDispatcher and window listener.
	 */
	private void addEventListeners() {
		// Initialize CustomKeyDispatcher
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		this.dispatcher = new CustomKeyDispatcher(this);
		manager.addKeyEventDispatcher(this.dispatcher);

		// Add window listener for closing attempts
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new CloseListener());
	}

	/**
	 * Generates the graph Canvas and its grid.
	 */
	private void generateCanvas() {
		this.visualGrid = new VisualGrid(this);
		this.graphCanvas = new GraphCanvas(this, this.visualGrid);
		Dimension gridSize = this.visualGrid.calculateSize();
		this.graphCanvas.addMouseListener(this.visualGrid);
		this.graphCanvas.setPreferredSize(gridSize);

		// Add container to make Canvas centered.
		JPanel canvasContainer = new JPanel();
		canvasContainer.setPreferredSize(gridSize);
		GridBagLayout gridBag = new GridBagLayout();
	    GridBagConstraints constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.CENTER;
	    gridBag.setConstraints(canvasContainer, constraints);
		canvasContainer.setLayout(gridBag);
		canvasContainer.add(this.graphCanvas);

		// Add scroll pane
		JScrollPane scrollPane = new JScrollPane(canvasContainer, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.add(scrollPane, BorderLayout.CENTER);		
	}

	/**
	 * Generates the view for the game window.
	 */
	private void generateView() {
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(MIN_WINDOW_WIDTH, MIN_WINDOW_HEIGHT));

		// Initialize and add MenuBar
		this.menuBar = new MenuBar(this);
		this.setJMenuBar(this.menuBar);

		// Initialize and add GraphCanvas and ViusalGrid
		this.generateCanvas();
			

		// Initialize and add StatusBar
		this.statusBar = new StatusBar();
		this.add(this.statusBar, BorderLayout.SOUTH);
		this.statusBar.setPreferredSize(new Dimension(this.getWidth(), STATUSBAR_HEIGHT));

		this.setMinimumSize(new Dimension(MIN_WINDOW_WIDTH, MIN_WINDOW_HEIGHT));
		this.setResizable(true);
		this.setVisible(true);
		// Center window
		this.pack();
		this.setLocationRelativeTo(null);
		
	}

	/**
	 * Shows a confirmation dialog.
	 * 
	 * @param message
	 *            The message to display
	 * @return <code>true</code> if the user confirmed, <code>false</code>
	 *         otherwise
	 */
	private boolean showConfirmDialog(String message) {

		int confirmChoice = JOptionPane.showConfirmDialog(this, message, this.getTitle(),
				JOptionPane.YES_NO_OPTION);

		// confirmChoice == 0: Yes
		// confirmChoice == 1: No
		return confirmChoice == 0;

	}

	/**
	 * Closes this Game window.
	 */
	void closeGame() {

		LOG.finer("GameWindow.<em>closeGame()</em> called.");

		// Ask user, if he/she really wants to quit
		boolean choice = this.showConfirmDialog(Localization.getLanguageString("gw_confirm_quit"));

		if (choice) {

			LOG.fine("Forwarding call to GameManager.");
			// Forward call to GameManager
			this.getViewManager().getGameManager().closeGame();

		}

	}

	/**
	 * Returns the {@link ViewManager} associated with the {@link GameWindow}.
	 * 
	 * @return The associated {@link ViewManager}
	 */
	ViewManager getViewManager() {
		return this.viewManager;
	}


	/**
	 * Forwards the key input to the {@link ViewManager}.
	 * 
	 * @param keyCode
	 *            The code of the key that was released
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	boolean onKeyRelease(int keyCode) {
		return this.viewManager.onKeyRelease(keyCode);
	}

	/**
	 * Listens for closing attempts performed by the main GameWindow.
	 * 
	 * @author Team Graphioli
	 */
	private class CloseListener extends WindowAdapter {

		/**
		 * Acts on closing attempts performed by the main GameWindow.
		 */
		@Override
		public void windowClosing(WindowEvent e) {
			GameWindow.this.closeGame();
		}

	}

}
