package de.graphioli.gameexplorer;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import de.graphioli.controller.GameManager;
import de.graphioli.model.Player;

/**
 * This class represents the main window of the {@link GameExplorer}.
 * 
 * @author Graphioli
 */
public class GEWindow extends JFrame implements GEView, ListSelectionListener {

	/**
	 * Logging instance
	 */
	private static final Logger LOG = Logger.getLogger(GameManager.class.getName());

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
	 */
	public GEWindow() {

		this.setSize(500, 500);
		LOG.info("GEWindow instantiated.");

	}


	/** {@inheritDoc} */
	@Override
	public boolean registerController(GameExplorer gameExplorer) {

		LOG.info("GEWindow.<em>registerController([...])</em> called.");

		this.gameExplorer = gameExplorer;
		LOG.info("Controller registered: GameExplorer.");

		return true;

	}


	/** {@inheritDoc} */
	@Override
	public boolean generateView() {

		LOG.info("GEWindow.<em>generateView()</em> called.");

		if (!this.isGameExplorerRegistered()) {
			return false;
		}

		ArrayList<GameDefinition> gameDefinitions = this.gameExplorer.getGameDefinitions();
		this.gameDefinitionList = new GameDefinitionListModel(gameDefinitions);

		if (this.gameDefinitionList.getSize() < 1) {
			return false;
		}

		// Show window
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

		LOG.info("GEWindow.<em>valueChanged([...])</em> called.");

		// Get new selected GameDefinition
		int index = event.getFirstIndex();
		this.selectedGameDefinition = (GameDefinition) this.gameDefinitionList.getElementAt(index);

		LOG.info("New GameDefinition '" + this.selectedGameDefinition.toString() + "' selected.");

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

		LOG.info("GEWindow.<em>openPlayerPopUp()</em> called.");

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

		LOG.info("GEWindow.<em>onPlayerPopUpReturn([...])</em> called.");

		if (players == null
				|| players.isEmpty()
				|| !this.isGameDefinitionSelected()
				|| !this.isGameExplorerRegistered()) {
			return false;
		}

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
