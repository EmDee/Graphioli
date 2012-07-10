package game;

import de.graphioli.model.GraphicVisualVertex;
import de.graphioli.model.GridPoint;
import de.graphioli.model.Player;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RescaleOp;
import java.awt.image.WritableRaster;

/**
 * This class represents a Vertex for the {@link TwixT} game.
 * @author Team Graphioli
 *
 */

public class TwixTVertex extends GraphicVisualVertex {
	
	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 8090530645635389205L;

	/**
	 * The factor by which the brightness of a graphic is changed, when "highlighted".
	 */
	private static final float HIGHLIGHTING_FACTOR = 0.5f;

	/**
	 * The BufferedImage used for the first {@link Player}.
	 */
	private static transient BufferedImage playerOneTower;

	/**
	 * The BufferedImage used for the second {@link Player}.
	 */
	private static transient BufferedImage playerTwoTower;

	/**
	 * The highlighted BufferedImage used for the first {@link Player}.
	 */
	private static transient BufferedImage playerOneHLTower;

	/**
	 * The highlighted BufferedImage used for the second {@link Player}.
	 */
	private static transient BufferedImage playerTwoHLTower;


	/**
	 * The first player of this game.
	 */
	private static Player playerOne;

	/**
	 * The second player of this game.
	 */
	private static Player playerTwo;
	
	/**
	 * {@code true} when this Vertex is highlighted.
	 */
	private boolean highlighted;

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
			this.setImage(this.highlighted ? playerOneHLTower : playerOneTower);
		} else if (this.player.equals(playerTwo)) {
			this.setImage(this.highlighted ? playerTwoHLTower : playerTwoTower);
		} else {
			// System.out.println("Unknown player");
			this.setImage(null);
		}
		this.update();
	}

	/**
	 * Initializes the two BufferedImages used for the towers of both players.
	 * 
	 * @param playerOne the first player of the game
	 * @param playerOneImage the graphic belonging to the first player
	 * @param playerTwo the second player of the game
	 * @param playerTwoImage the graphic belonging to the second player
	 */
	protected static void initImages(Player playerOne, BufferedImage playerOneImage, Player playerTwo,
			BufferedImage playerTwoImage) {
		TwixTVertex.playerOne = playerOne;
		TwixTVertex.playerTwo = playerTwo;
		TwixTVertex.playerOneTower = playerOneImage;
		TwixTVertex.playerTwoTower = playerTwoImage;

		// Creating "highlighted" copies
		ColorModel cm = playerOneImage.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = playerOneImage.copyData(null);
		TwixTVertex.playerOneHLTower = new BufferedImage(cm, raster, isAlphaPremultiplied, null);

		cm = playerTwoImage.getColorModel();
		isAlphaPremultiplied = cm.isAlphaPremultiplied();
		raster = playerTwoImage.copyData(null);
		TwixTVertex.playerTwoHLTower = new BufferedImage(cm, raster, isAlphaPremultiplied, null);

		RescaleOp resc = new RescaleOp(HIGHLIGHTING_FACTOR, 0, null);
		resc.filter(TwixTVertex.playerOneHLTower, TwixTVertex.playerOneHLTower);
		resc.filter(TwixTVertex.playerTwoHLTower, TwixTVertex.playerTwoHLTower);

	}

	/**
	 * {@inheritDoc}
	 */
	/*
	 * Need to reset player, when Vertex was reloaded. (non-Javadoc)
	 * @see de.graphioli.model.GraphicVisualVertex#draw(java.awt.Graphics2D)
	 */
	@Override
	protected boolean draw(Graphics2D g2d) {
		if (this.getImage() == null && this.player != null) {
			this.highlighted = false;
			if (this.player.equals(playerOne)) {
				this.setImage(playerOneTower);
			} else if (this.player.equals(playerTwo)) {
				this.setImage(playerTwoTower);
			}
		}
		return super.draw(g2d);
	}
	
	/**
	 * Sets the highlighted attribute of this vertex.
	 * @param highlighted the highlighted attribute of this vertex.
	 */
	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
		this.setPlayer(this.player);
	}
	
	/**
	 * Returns {@code true} if this vertex is highlighted.
	 * @return {@code true} if this vertex is highlighted.
	 */
	public boolean isHighlighted() {
		return this.highlighted;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void init() {
		super.init();
		this.highlighted = false;
	}

}
