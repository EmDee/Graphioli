package game;

import java.awt.image.BufferedImage;

import de.graphioli.model.GraphicVisualVertex;
import de.graphioli.model.GridPoint;
import de.graphioli.model.Player;
import de.graphioli.utils.JarParser;

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

	/**
	 * The file name of the image used by player one
	 */
	private final String fileNameOne = "Awesome1.png";

	/**
	 * The file name of the image used by player two
	 */
	private final String fileNameTwo = "Awesome2.png";

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
			playerOneTower = this.loadBufferedImage("TwixT", this.fileNameOne);
			playerTwoTower = this.loadBufferedImage("TwixT", this.fileNameTwo);
		}
	}

	@Override
	protected void onReload() {
		this.init();
		this.setPlayer(this.player);
	}

}
