package de.graphioli.gameexplorer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This class handles the action and event listeners for the GEWindow.
 * 
 * @author Graphioli
 */
public class GEWindowActions extends WindowAdapter implements ListSelectionListener, KeyListener, MouseListener {

	/**
	 * Logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(GEWindow.class.getName());

	/**
	 * The controlling GEWindow.
	 */
	private GEWindow geWindow;

	/**
	 * Constructs a new GEWindowActions action listener.
	 * 
	 * @param geWindow The controlling GEWindow
	 */
	public GEWindowActions(GEWindow geWindow) {
		this.geWindow = geWindow;
	}

	/** {@inheritDoc} */
	@Override
	public void keyPressed(KeyEvent e) {

		LOG.finer("GEWindowActions.<em>keyPressed([...])</em> called.");

		// If key is 'Enter', start game
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.geWindow.openPlayerPopUp();
		}

	}

	/** {@inheritDoc} */
	@Override
	public void keyReleased(KeyEvent e) {
		// Not needed
	}

	/** {@inheritDoc} */
	@Override
	public void keyTyped(KeyEvent e) {
		// Not needed
	}

	/** {@inheritDoc} */
	@Override
	public void mouseClicked(MouseEvent e) {

		LOG.finer("GEWindowActions.<em>mouseClicked([...])</em> called.");

		// If double clicked on a list item
		if (e.getClickCount() >= 2) {
			this.geWindow.openPlayerPopUp();
		}

	}

	/** {@inheritDoc} */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// Not needed
	}

	/** {@inheritDoc} */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// Not needed
	}

	/** {@inheritDoc} */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// Not needed
	}

	/** {@inheritDoc} */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// Not needed
	}

	/**
	 * Called by the {@link JList} when its selection has changed to update the
	 * remaining graphical elements of this {@link GEWindow}.
	 * 
	 * @param event
	 *            The ListSelectionEvent
	 */
	@Override
	public void valueChanged(ListSelectionEvent event) {

		LOG.finer("GEWindowActions.<em>valueChanged([...])</em> called.");

		// Get new selected GameDefinition
		@SuppressWarnings("unchecked") // We only have one list in the GameExplorer, so this cast should be safe.
		JList sourceList = (JList) event.getSource();
		GameDefinition selectedGameDefinition = (GameDefinition) sourceList.getSelectedValue();

		// Notify relevant dependencies about the selection
		this.geWindow.selectGameDefinition(selectedGameDefinition);

		LOG.fine("New GameDefinition '" + selectedGameDefinition.toString() + "' selected.");

	}

	/**
	 * Acts on closing attempts performed by the main GEWindow.
	 * 
	 * @param e
	 * 			The WindowEvent
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		this.geWindow.closeGameExplorer();
	}

}
