package de.graphioli.gameexplorer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import de.graphioli.controller.GameManager;
import de.graphioli.model.Player;

/**
 * This class represents the main window of the {@link GameExplorer}.
 * 
 * @author Graphioli
 */
public class GEWindow extends JFrame implements GEView, ActionListener, ListSelectionListener {

	/**
	 * Logging instance
	 */
	private static final Logger LOG = Logger.getLogger(GEWindow.class.getName());

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
	 * The data model providing the list of available {@link GameDefintion}s.
	 */
	private GameDefinitionListModel gameDefinitionList;

	/**
	 * The initial width of this window.
	 */
	private int windowWidth = 800;

	/**
	 * The initial height of this window.
	 */
	private int windowHeight = 500;


	/**
	 */
	public GEWindow() {

		super("Game Explorer");
		LOG.info("GEWindow instantiated.");

	}


	/** {@inheritDoc} */
	@Override
	public boolean registerController(GameExplorer gameExplorer) {

		LOG.finer("GEWindow.<em>registerController([...])</em> called.");

		this.gameExplorer = gameExplorer;
		LOG.info("Controller registered: GameExplorer.");

		return true;

	}


	/** {@inheritDoc} */
	@Override
	public boolean generateView() {

		LOG.finer("GEWindow.<em>generateView()</em> called.");

		if (!this.isGameExplorerRegistered()) {
			return false;
		}

		ArrayList<GameDefinition> gameDefinitions = this.gameExplorer.getGameDefinitions();
		this.gameDefinitionList = new GameDefinitionListModel(gameDefinitions);

		if (this.gameDefinitionList.getSize() < 1) {
			return false;
		}

		// Show window
		this.setSize(this.windowWidth, this.windowHeight);
		this.setLayout(new BorderLayout());

		// Add scrollable list of GameDefinitions
		JList visibleGameDefinitionList = new JList(this.gameDefinitionList);
		visibleGameDefinitionList.addListSelectionListener(this);
		JScrollPane visibleGameDefinitionListPane = new JScrollPane(visibleGameDefinitionList);
		visibleGameDefinitionListPane.setBackground(Color.BLACK);
		this.add(visibleGameDefinitionListPane, BorderLayout.LINE_START);


		/*
		 * Button Panel
		 */
		JPanel visibleButtonPanel = new JPanel();
		
		// Button: Start
		JButton startButton = new JButton("Start");
		startButton.addActionListener(this);
		visibleButtonPanel.add(startButton);

		// Button: Help
		JButton helpButton = new JButton("Help");
		helpButton.addActionListener(this);
		visibleButtonPanel.add(helpButton);

		// Button: Quit
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(this);
		visibleButtonPanel.add(quitButton);

		this.add(visibleButtonPanel, BorderLayout.PAGE_END);


		/*
		 * Show window
		 */
		this.setVisible(true);

		return true;

	}


	/**
	 * Called by the {@link JList} when its selection has changed to update the
	 * remaining graphical elements of this {@link GEWindow}.
	 * 
	 * @param event The ListSelectionEvent
	 */
	@Override
	public void valueChanged(ListSelectionEvent event) {

		LOG.finer("GEWindow.<em>valueChanged([...])</em> called.");

		// Get new selected GameDefinition
		JList sourceList = (JList) event.getSource();
		this.selectedGameDefinition = (GameDefinition) sourceList.getSelectedValue();

		LOG.fine("New GameDefinition '" + this.selectedGameDefinition.toString() + "' selected.");

	}


	/**
	 * Called by the {@link JButton}s when they are clicked in order to perform further actions
	 * with the previously selected {@link GameDefinition}.
	 * 
	 * @param event The ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent event) {

		LOG.finer("GEWindow.<em>actionPerformed([...])</em> called.");

		// Get clicked button
		JButton sourceButton = (JButton) event.getSource();

		LOG.fine("Button '" + sourceButton.getText() + "' clicked.");

		if (sourceButton.getText().equals("Start")) {

			if (this.isGameDefinitionSelected()) {
				this.openPlayerPopUp();
			} else {
				// TODO fallback dialog
				System.out.println("Please select a game from the list.");
			}

		} else if (sourceButton.getText().equals("Help")) {

			if (this.isGameDefinitionSelected()) {
				this.openHelpFile();
			} else {
				// TODO fallback dialog
				System.out.println("Please select a game from the list.");
			}

		} else if (sourceButton.getText().equals("Quit")) {

			// TODO implement GameExplorer#quit()
			//this.quitGameExplorer();
			System.exit(0);

		}

	}


	/**
	 * Calls {@link GameExplorer#openHelpFile(GameDefinition gameDef)} with the selected {@link GameDefinition}.
	 * 
	 * For this method to perform its task successfully, a {@link GameDefinition} must be selected from
	 * the list of available definitions.
	 * 
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	public boolean openHelpFile() {

		LOG.info("GEWindow.<em>openHelpFile()</em> called.");

		if (!isGameDefinitionSelected()
				|| !this.isGameExplorerRegistered()) {
			return false;
		}

		// Forward call to GameExplorer with the currently selected GameDefinition
		return this.gameExplorer.openHelpFile(this.selectedGameDefinition);

	}


	/**
	 * Creates and shows a {@link PlayerPopUp} for {@link Player} selection.
	 * 
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	public boolean openPlayerPopUp() {

		LOG.fine("GEWindow.<em>openPlayerPopUp()</em> called.");

		// Create new Player pop-up (will call onPlayerPopUpReturn)
		new PlayerPopUp(this,
				this.selectedGameDefinition.getMinPlayerCount(),
				this.selectedGameDefinition.getMaxPlayerCount());

		return true;

	}


	/**
	 * Called by the {@link PlayerPopUp} when it has finished and triggers the start of the {@link Game}.
	 * 
	 * For this method to perform its task successfully, a {@link GameDefinition} must be selected from
	 * the list of available definitions.
	 * 
	 * @param players The created players
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	public boolean onPlayerPopUpReturn(ArrayList<Player> players) {

		LOG.finer("GEWindow.<em>onPlayerPopUpReturn([...])</em> called.");

		if (players == null
				|| players.isEmpty()
				|| !this.isGameDefinitionSelected()
				|| !this.isGameExplorerRegistered()) {
			return false;
		}

		// Close window
		this.setVisible(false);

		// Forward call to GameExplorer
		return this.gameExplorer.selectGame(this.selectedGameDefinition, players);

	}


	/**
	 * Returns <code>true</code> if a {@link GameDefinition} is currently selected.
	 * @return <code>true</code> if a {@link GameDefinition} is currently selected, <code>false</code> otherwise
	 */
	private boolean isGameDefinitionSelected() {
		return this.selectedGameDefinition != null;
	}


	/**
	 * Returns <code>true</code> if a controlling {@link GameExplorer} is registered.
	 * @return <code>true</code> if a controlling {@link GameExplorer} is registered, <code>false</code> otherwise
	 */
	private boolean isGameExplorerRegistered() {
		return this.gameExplorer != null;
	}

}
