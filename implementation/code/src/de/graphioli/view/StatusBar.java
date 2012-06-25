package de.graphioli.view;
import javax.swing.JPanel;

import de.graphioli.model.Player;

/**
 * The StatusBar displays which Player is currently active and a short status or error message.
 * 
 * @author Graphioli
 */
public class StatusBar extends JPanel {
	
	/**
	 * The error message that is currently displayed
	 */
	private String errorMessage;
	
	/**
	 * The {@link Player} that is currently displayed as active
	 */
	private Player playerStatus;

	/**
	 * Constructs a {@link StatusBar} responsible for displaying the playerStatus and errorMessages
	 */
	public StatusBar() {
	}
	
	/**
	 * Updates the currently active {@link Player} displayed.
	 * 
	 * @param player The {@link Player} to display in the {@link StatusBar}
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	public boolean updatePlayerStatus(Player player) {
		return true;
	}
	
	/**
	 * Displays a status or error message.
	 * 
	 * @param errorMessage The error message to display in the {@link StatusBar}
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	public boolean displayErrorMessage(String errorMessage) {
		return true;
	}
	
}
