package de.graphioli.view;

import de.graphioli.model.Player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

/**
 * The StatusBar displays which Player is currently active and a short status or
 * error message.
 * 
 * @author Team Graphioli
 */
public class StatusBar extends JPanel {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(StatusBar.class.getName());

	private JTextField statusLabel;
	private JTextField playerLabel;

	/**
	 * Constructs a {@link StatusBar} responsible for displaying the
	 * playerStatus and errorMessages.
	 */
	public StatusBar() {

		// Style status bar
		this.setLayout(new BorderLayout());
		this.setBorder(new BevelBorder(BevelBorder.LOWERED));
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));

		// Create status label
		this.statusLabel = new JTextField("status");

		// Style status label
		this.statusLabel.setEditable(false);
		this.statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		this.statusLabel.setOpaque(false);
		this.statusLabel.setBorder(null);

		// Add status label to status bar
		add(this.statusLabel, BorderLayout.WEST);

		// Create player label
		this.playerLabel = new JTextField("player");

		// Style player label
		this.playerLabel.setEditable(false);
		this.playerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		this.playerLabel.setOpaque(false);
		this.playerLabel.setBorder(null);

		// Add player label to status bar
		add(this.playerLabel, BorderLayout.EAST);

		LOG.fine("StatusBar created.");

	}

	/**
	 * Updates the currently active {@link Player} displayed.
	 * 
	 * @param player
	 *            The {@link Player} to display in the {@link StatusBar}
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	boolean updatePlayerStatus(Player player) {
		this.playerLabel.setText(player.getName());
		return true;
	}

	/**
	 * Displays a status or error message.
	 * 
	 * @param errorMessage
	 *            The error message to display in the {@link StatusBar}
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	boolean displayErrorMessage(String errorMessage) {
		this.statusLabel.setText(errorMessage);
		return true;
	}

}
