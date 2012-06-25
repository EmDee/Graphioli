package de.graphioli.gameexplorer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JFrame;

import de.graphioli.controller.GameManager;
import de.graphioli.model.LocalPlayer;
import de.graphioli.model.Player;

/**
 * Represents a pop-up window that is used to select the the number of players and their names for a {@link Game}.
 * 
 * @author Graphioli
 */
public class PlayerPopUp extends JFrame implements ActionListener {

	/**
	 * Logging instance
	 */
	private static final Logger LOG = Logger.getLogger(PlayerPopUp.class.getName());

	/**
	 * UID for serializing this object.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The controlling {@link GEWindow}.
	 */
	private GEWindow geWindow;

	/**
	 * The list of created players.
	 */
	private ArrayList<Player> players = new ArrayList<Player>();


	/**
	 * Creates a PlayerPopUp.
	 * 
	 * @param geWindow The controlling {@link GEWindow}
	 * @param minPlayer The minimum number of players required
	 * @param maxPlayer The maximum number of players required
	 */
	public PlayerPopUp(GEWindow geWindow, int minPlayer, int maxPlayer) {

		this.geWindow = geWindow;

		LOG.info("PlayerPopUp instantiated.");

		// Instantiate mock-up players
		Player player1 = new LocalPlayer("Player 1");
		Player player2 = new LocalPlayer("Player 2");

		this.players.add(player1);
		this.players.add(player2);

		this.geWindow.onPlayerPopUpReturn(this.players);

	}


	/**
	 * Callback method for the {@link JButtons}, that creates the {@link Player}s based on
	 * the input and calls {@link GEWindow#onPlayerPopUpReturn(ArrayList)}.
	 * 
	 * @param event The ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
	}

}
