package de.graphioli.gameexplorer;

import de.graphioli.model.Player;
import de.graphioli.utils.InvalidJarException;
import de.graphioli.utils.JarParser;
import de.graphioli.utils.Localization;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * This class represents the main window of the {@link GameExplorer}.
 * 
 * @author Team Graphioli
 */
public class GEWindow extends JFrame implements GEView {

	/**
	 * Logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(GEWindow.class.getName());

	/**
	 * The initial width of this window.
	 */
	private static final int INIT_WINDOW_WIDTH = 800;

	/**
	 * The initial height of this window.
	 */
	private static final int INIT_WINDOW_HEIGHT = 500;

	/**
	 * Margin between the bottom of the list pane and the bottom of the window.
	 */
	private static final int LIST_PANE_BOTTOM_MARGIN = 70;

	/**
	 * UID for serializing this object.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The {@link GameExplorer} controlling this GEView implementation.
	 */
	private GameExplorer gameExplorer;

	/**
	 * The currently selected {@link GameDefinition}.
	 */
	private GameDefinition selectedGameDefinition;

	/**
	 * The data model providing the list of available {@link GameDefinition}s.
	 */
	private GameDefinitionListModel gameDefinitionList;

	/**
	 * Panel containing information about the currently selected game.
	 */
	private GEGameInformation visibleGameInformationPanel;

	/**
	 * The action and event listener.
	 */
	private GEWindowActions geWindowActions;


	/**
	 * Constructs a new instance of GEWindow.
	 */
	public GEWindow() {

		this.setTitle(Localization.getLanguageString("ge_title"));

		LOG.info("GEWindow instantiated.");

	}

	/**
	 * Closes this GameExplorer window.
	 */
	public void closeGameExplorer() {

		LOG.finer("GEWindow.<em>closeGameExplorer()</em> called.");

		this.dispose();
		this.gameExplorer.close();

	}

	/** {@inheritDoc} */
	@Override
	public boolean generateView() {

		LOG.finer("GEWindow.<em>generateView()</em> called.");

		if (!this.isGameExplorerRegistered()) {
			LOG.severe("Cannot generate view: No GameExplorer registered. Please call "
					+ "<em>registerController([...])</em> first.");
			return false;
		}

		ArrayList<GameDefinition> gameDefinitions = this.gameExplorer.getGameDefinitions();
		this.gameDefinitionList = new GameDefinitionListModel(gameDefinitions);

		if (this.gameDefinitionList.getSize() < 1) {
			LOG.severe("Cannot generate view: Empty list of GameDefinitions.");
			return false;
		}

		// Generate visual window
		this.generateWindow();

		return true;

	}

	/**
	 * Returns a BufferedImage containing the screenshot of the currently select
	 * game.
	 * 
	 * @return a BufferedImage containing the screenshot of the currently select
	 *         game
	 */
	public BufferedImage getCurrentScreenshot() {

		LOG.finer("GEWindow.<em>getCurrentScreenshot()</em> called.");

		BufferedImage screenshot;
		InputStream screenshotInputStream;
		try {
			screenshotInputStream = JarParser.getFileAsInputStream(this.selectedGameDefinition.getClassName(),
					"screenshot.jpg");
		} catch (InvalidJarException e1) {
			LOG.severe("JAR file doesn't provide valid creenshot for " + this.selectedGameDefinition.getClassName());
			return null;
		}

		// Try creating buffered image from path
		try {
			screenshot = ImageIO.read(screenshotInputStream);
		} catch (IllegalArgumentException e) {
			LOG.severe("File does not exist: Screenshot for " + this.selectedGameDefinition.getClassName());
			return null;
		} catch (IOException e) {
			LOG.severe("Could not read file: Screenshot for " + this.selectedGameDefinition.getClassName());
			return null;
		}

		return screenshot;

	}

	/**
	 * Returns the class name of the currently select game.
	 * 
	 * @return the class name of the currently select game
	 */
	public String getCurrentClassName() {

		LOG.finer("GEWindow.<em>getCurrentClassName()</em> called.");
		return this.selectedGameDefinition.getClassName();

	}

	/**
	 * Called by the {@link PlayerPopUp} when it has finished and triggers the
	 * start of the {@link de.graphioli.controller.Game Game}. For this method to perform its task
	 * successfully, a {@link GameDefinition} must be selected from the list of
	 * available definitions.
	 * 
	 * @param players
	 *            The created players
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean onPlayerPopUpReturn(ArrayList<Player> players) {

		LOG.finer("GEWindow.<em>onPlayerPopUpReturn([...])</em> called.");

		if (players == null
				|| players.isEmpty()
				|| !this.isGameDefinitionSelected()
				|| !this.isGameExplorerRegistered()) {
			LOG.severe("Cannot start game: No GameExplorer registered or no valid players committed.");
			return false;
		}

		// Close window
		this.setVisible(false);

		// Forward call to GameExplorer
		return this.gameExplorer.selectGame(this.selectedGameDefinition, players);

	}

	/**
	 * Called by the {@link PlayerPopUp} when it has finished and triggers the
	 * start of the {@link de.graphioli.controller.Game Game}. For this method to perform its task
	 * successfully, a {@link GameDefinition} must be selected from the list of
	 * available definitions.
	 * 
	 * @param savegame
	 *            The specified savegame to load
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean onPlayerPopUpReturn(File savegame) {

		LOG.finer("GEWindow.<em>onPlayerPopUpReturn([...])</em> called.");

		if (!savegame.exists()) {
			LOG.severe("Cannot start game: Specified savegame doesn't exist.");
			return false;
		}

		// Close window
		this.setVisible(false);

		// Forward call to GameExplorer
		return this.gameExplorer.selectGame(this.selectedGameDefinition, savegame);

	}

	/**
	 * Calls {@link GameExplorer#openHelpFile(GameDefinition gameDef)} with the
	 * selected {@link GameDefinition}. For this method to perform its task
	 * successfully, a {@link GameDefinition} must be selected from the list of
	 * available definitions.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean openHelpFile() {

		LOG.fine("GEWindow.<em>openHelpFile()</em> called.");

		if (!isGameDefinitionSelected() || !this.isGameExplorerRegistered()) {
			LOG.severe("Cannot open help file: No GameExplorer registered or no GameDefinition selected.");
			return false;
		}

		// Forward call to GameExplorer with the currently selected
		// GameDefinition
		return this.gameExplorer.openHelpFile(this.selectedGameDefinition);

	}

	/**
	 * Creates and shows a {@link PlayerPopUp} for {@link Player} selection.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean openPlayerPopUp() {

		LOG.fine("GEWindow.<em>openPlayerPopUp()</em> called.");

		// Create new Player pop-up (will call onPlayerPopUpReturn)
		new PlayerPopUp(this,
				this.selectedGameDefinition.getMinPlayerCount(),
				this.selectedGameDefinition.getMaxPlayerCount(),
				this.selectedGameDefinition.supportsSavegames());

		return true;

	}

	/** {@inheritDoc} */
	@Override
	public boolean registerController(GameExplorer gameExplorer) {

		LOG.finer("GEWindow.<em>registerController([...])</em> called.");

		this.gameExplorer = gameExplorer;
		LOG.info("Controller registered: GameExplorer.");

		return true;

	}

	/**
	 * Performs the required steps after a GameDefinition has been selected from
	 * the list.
	 * 
	 * @param selectedGameDefinition
	 *            The newly selected GameDefinition
	 */
	public void selectGameDefinition(GameDefinition selectedGameDefinition) {

		this.selectedGameDefinition = selectedGameDefinition;
		this.updateGameInformation();

	}

	/**
	 * Generates the buttons that allow to start a game, show the help file or
	 * quit the GameExplorer.
	 */
	private void generateButtonPanel() {

		JPanel visibleButtonPanel = new GEButtonPanel(this);

		// Add button panel to window
		this.add(visibleButtonPanel, BorderLayout.PAGE_END);

	}

	/**
	 * Generates the panels that show information about the currently selected
	 * game.
	 */
	private void generateGameInformationPanel() {

		this.visibleGameInformationPanel = new GEGameInformation(this);

		// Add information
		this.updateGameInformation();

		// Add information panel to window
		this.add(this.visibleGameInformationPanel, BorderLayout.CENTER);

	}

	/**
	 * Generates the list pane that shows the available GameDefinitions.
	 */
	private void generateListPane() {

		JList visibleGameDefinitionList = new JList(this.gameDefinitionList);
		visibleGameDefinitionList.addListSelectionListener(this.geWindowActions);
		visibleGameDefinitionList.addKeyListener(this.geWindowActions);
		visibleGameDefinitionList.addMouseListener(this.geWindowActions);

		// Set list selection mode to 'single selection'
		visibleGameDefinitionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Make first index selected
		visibleGameDefinitionList.setSelectedIndex(0);

		JScrollPane visibleGameDefinitionListPane = new JScrollPane(visibleGameDefinitionList);

		// Style list pane
		visibleGameDefinitionListPane.setPreferredSize(new Dimension(INIT_WINDOW_WIDTH / 2, INIT_WINDOW_HEIGHT
				- LIST_PANE_BOTTOM_MARGIN));
		visibleGameDefinitionListPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JPanel visibleGameDefinitionListPaneBox = new JPanel();
		visibleGameDefinitionListPaneBox.add(visibleGameDefinitionListPane);

		// Add list pane to window
		this.add(visibleGameDefinitionListPaneBox, BorderLayout.LINE_START);

	}

	/**
	 * Generates the visible window consisting of several swing components.
	 */
	private void generateWindow() {

		// Create window action and event listener
		this.geWindowActions = new GEWindowActions(this);

		// Add window listener for closing attempts
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this.geWindowActions);

		// Style window
		this.setSize(INIT_WINDOW_WIDTH, INIT_WINDOW_HEIGHT);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		// Center window
		this.setLocationRelativeTo(null);

		this.generateListPane();
		this.generateButtonPanel();
		this.generateGameInformationPanel();

		// Show window
		this.setVisible(true);

	}

	/**
	 * Returns <code>true</code> if a {@link GameDefinition} is currently
	 * selected.
	 * 
	 * @return <code>true</code> if a {@link GameDefinition} is currently
	 *         selected, <code>false</code> otherwise
	 */
	private boolean isGameDefinitionSelected() {
		return this.selectedGameDefinition != null;
	}

	/**
	 * Returns <code>true</code> if a controlling {@link GameExplorer} is
	 * registered.
	 * 
	 * @return <code>true</code> if a controlling {@link GameExplorer} is
	 *         registered, <code>false</code> otherwise
	 */
	private boolean isGameExplorerRegistered() {
		return this.gameExplorer != null;
	}

	/**
	 * Updates the information displayed in the game information panel.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	private boolean updateGameInformation() {

		if (this.visibleGameInformationPanel == null
				|| this.selectedGameDefinition == null) {
			return false;
		}

		// Set description
		this.visibleGameInformationPanel.setDescription(this.selectedGameDefinition.getDescription());

		// Create buffered image for screenshot
		this.visibleGameInformationPanel.setScreenshot(this.selectedGameDefinition.getClassName());

		return true;

	}

}
