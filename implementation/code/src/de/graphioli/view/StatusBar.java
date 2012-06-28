package de.graphioli.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import de.graphioli.model.Player;
import de.graphioli.model.VisualVertex;

/**
 * The StatusBar displays which Player is currently active and a short status or
 * error message.
 * 
 * @author Graphioli
 */
public class StatusBar extends JPanel {

	/**
	 * Logging instance
	 */
	private final static Logger LOG = Logger.getLogger(StatusBar.class.getName());

	/**
	 * The error message that is currently displayed
	 */
	private String errorMessage;

	/**
	 * The {@link Player} that is currently displayed as active
	 */
	private Player playerStatus;

	private JTextField statusLabel;
	private JTextField playerLabel;

	/**
	 * Constructs a {@link StatusBar} responsible for displaying the
	 * playerStatus and errorMessages
	 */
	public StatusBar() {
		LOG.fine("StatusBar created.");
		this.setLayout(new BorderLayout());
		this.setBorder(new BevelBorder(BevelBorder.LOWERED));
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		statusLabel = new JTextField("status");
		statusLabel.setEditable(false);
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(statusLabel, BorderLayout.WEST);
		playerLabel = new JTextField("player");
		playerLabel.setEditable(false);
		playerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(playerLabel, BorderLayout.EAST);
	}

	/**
	 * Updates the currently active {@link Player} displayed.
	 * 
	 * @param player
	 *            The {@link Player} to display in the {@link StatusBar}
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean updatePlayerStatus(Player player) {
		this.playerStatus = player;
		playerLabel.setText(player.getName());
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
	public boolean displayErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		statusLabel.setText(errorMessage);
		return true;
	}

}
