package de.graphioli.gameexplorer;

import de.graphioli.utils.Localization;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class represents the button panel in the main window of the
 * {@link GameExplorer}.
 * 
 * @author Graphioli
 */
public class GEButtonPanel extends JPanel implements ActionListener {

	/**
	 * UID for serializing this object.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(GEButtonPanel.class.getName());

	/**
	 * The controlling GEWindow.
	 */
	private GEWindow geWindow;

	/**
	 * Buttons for the GameExplorer.
	 */
	private JButton startButton;
	private JButton helpButton;
	private JButton quitButton;

	/**
	 * Creates a new instance of {@code GEButtonPanel}.
	 * 
	 * @param geWindow
	 *            The controlling GEWindow
	 */
	public GEButtonPanel(GEWindow geWindow) {
		this.geWindow = geWindow;

		// Button: Start
		this.startButton = new JButton(Localization.getLanguageString("gew_start"));
		this.startButton.addActionListener(this);
		this.add(this.startButton);

		// Button: Help
		this.helpButton = new JButton(Localization.getLanguageString("gew_help"));
		this.helpButton.addActionListener(this);
		this.add(this.helpButton);

		// Button: Quit
		this.quitButton = new JButton(Localization.getLanguageString("gew_quit"));
		this.quitButton.addActionListener(this);
		this.add(this.quitButton);

		LOG.finer("GEButtonPanel instantiated.");
	}

	/**
	 * Called by the {@link JButton}s when they are clicked in order to perform
	 * further actions with the previously selected {@link GameDefinition}.
	 * 
	 * @param event
	 *            The ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent event) {

		LOG.finer("GEButtonPanel.<em>actionPerformed([...])</em> called.");

		// Get clicked button
		JButton sourceButton = (JButton) event.getSource();

		LOG.fine("Button '" + sourceButton.getText() + "' clicked.");

		// Choose button action
		if (sourceButton.equals(this.startButton)) {

			this.geWindow.openPlayerPopUp();

		} else if (sourceButton.equals(this.helpButton)) {

			this.geWindow.openHelpFile();

		} else if (sourceButton.equals(this.quitButton)) {

			this.geWindow.closeGameExplorer();

		}

	}

}
