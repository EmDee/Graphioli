package de.graphioli.gameexplorer;

import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;

/**
 * Represents a data model that provides the {@link JList} with its content.
 * 
 * @author Team Graphioli
 */
public class GameDefinitionListModel extends AbstractListModel {

	/**
	 * UID for serializing this object.
	 */
	private static final long serialVersionUID = 3043301665361133479L;


	/**
	 * Logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(GameDefinitionListModel.class.getName());


	/**
	 * The list of {@link GameDefinition}s in this ListModel.
	 */
	private ArrayList<GameDefinition> gameDefinitions;

	/**
	 * Creates a new GameDefinitionListModel with the given
	 * {@link GameDefinition}s.
	 * 
	 * @param gameDefinitions
	 *            The list of {@link GameDefinition}s in this ListModel
	 */
	public GameDefinitionListModel(ArrayList<GameDefinition> gameDefinitions) {

		this.gameDefinitions = gameDefinitions;
		LOG.fine("Created new GameDefinitionListModel with " + this.getSize() + " items.");

	}

	/**
	 * Returns the name of the {@link GameDefinition} at the given index so the
	 * {@link JList} can display it.
	 * 
	 * @param index
	 *            The index position
	 * @return GameDefinition The GameDefintion at the specified index position
	 */
	@Override
	public GameDefinition getElementAt(int index) {
		return this.gameDefinitions.get(index);
	}

	/** {@inheritDoc} */
	@Override
	public int getSize() {
		return this.gameDefinitions.size();
	}

}
