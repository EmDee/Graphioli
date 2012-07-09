package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.graphioli.model.GraphicVisualVertex;
import de.graphioli.model.GridPoint;
import de.graphioli.model.Player;

public class TwixTVertex extends GraphicVisualVertex {

	/**
	 * The BufferedImage used for the first {@link Player}
	 */
	private static transient BufferedImage playerOneTower;

	/**
	 * The BufferedImage used for the second {@link Player}
	 */
	private static transient BufferedImage playerTwoTower;

	/**
	 * The first player of this game.
	 */
	private static Player playerOne;
	
	/**
	 * The second player of this game.
	 */
	private static Player playerTwo;
	
	
	/**
	 * The {@link Player} who owns this TwixTVertex.
	 */
	private Player player;

	/**
	 * Creates a new TwixtVertex and places it on the specified GridPoint.
	 * 
	 * @param gridPoint
	 *            The {@link GridPoint} where the TwixTVertex is located
	 */
	public TwixTVertex(GridPoint gridPoint) {
		super(gridPoint);
	}

	/**
	 * Returns the Player.
	 * 
	 * @return The 'owner' of this vertex.
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Sets the image that represents this GraphicVisualVertex.
	 * 
	 * @param player
	 *            The 'owner' of this vertex
	 */
	public void setPlayer(Player player) {
		this.player = player;
		if (this.player.equals(playerOne)) {
			this.setImage(playerOneTower);
		} else if (this.player.equals(playerTwo)) {
			this.setImage(playerTwoTower);
		} else {
			System.out.println("Unknown player");
			this.setImage(null);
		}
		this.update();
	}

	/**
	 * Initializes the two BufferedImages used for the towers of both
	 * players.
	 */
	protected static void initImages(Player playerOne, BufferedImage playerOneImage, Player playerTwo, BufferedImage playerTwoImage) {
		TwixTVertex.playerOne = playerOne;
		TwixTVertex.playerTwo = playerTwo;
		TwixTVertex.playerOneTower = playerOneImage;
		TwixTVertex.playerTwoTower = playerTwoImage;
	}
	
	/**
	 * {@inheritDoc}
	 */
	/*
	 * Need to reset player, when Vertex was reloaded.
	 * (non-Javadoc)
	 * @see de.graphioli.model.GraphicVisualVertex#draw(java.awt.Graphics2D)
	 */
	@Override
	protected boolean draw(Graphics2D g2d) {
		if (this.getImage() == null && this.player != null) {
			this.setPlayer(this.player);
		}
		return super.draw(g2d);
	}

}
