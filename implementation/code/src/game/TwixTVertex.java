package game;

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
	 * The {@link Player} who owns this TwixTVertex.
	 */
	private Player player;
	
	private final String fileNameOne = "games/TwixT/Awesome1.png";

	private final String fileNameTwo = "games/TwixT/Awesome2.png";
	
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
		if (this.player == TwixT.playerOne) {
			this.setImage(playerTwoTower);
		} else {
			this.setImage(playerOneTower);
		}
		this.update();
	}

	/**
	 * Initializes the two BufferedImage for used for the towers of both
	 * players.
	 */
	@Override
	protected void init() {
		if (playerOneTower == null || playerTwoTower == null) {
			playerOneTower = this.loadBufferedImage(this.fileNameOne);
			playerTwoTower = this.loadBufferedImage(this.fileNameTwo);			
		}
	}
	
	@Override
	protected void onReload() {
		this.init();
		this.setPlayer(this.player);
	}

}
