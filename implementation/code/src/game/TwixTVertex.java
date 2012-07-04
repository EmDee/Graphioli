package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import de.graphioli.model.GraphicVisualVertex;
import de.graphioli.model.GridPoint;
import de.graphioli.model.Player;
import de.graphioli.model.VisualVertex;


public class TwixTVertex extends GraphicVisualVertex {

	private BufferedImage playerOneTower;
	private BufferedImage playerTwoTower;	
	
	private Player player;

	public TwixTVertex(GridPoint gridPoint) {
		super(gridPoint);
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
		this.update();
	}
	
	@Override
	protected void init() {
		this.playerOneTower = super.loadBufferedImage("games/TwixT/Awesome1.png");
		this.playerTwoTower = super.loadBufferedImage("games/TwixT/Awesome2.png");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean draw(Graphics2D g2d) {
		if (this.player == TwixT.PLAYER_ONE) {
			if (this.playerOneTower != null) {
				g2d.drawImage(this.playerOneTower, 0, 0, VisualVertex.PIXELS_PER_SIDE, VisualVertex.PIXELS_PER_SIDE, null);
			} else {
				g2d.drawString("X", VisualVertex.PIXELS_PER_SIDE, VisualVertex.PIXELS_PER_SIDE);
				return false;
			}
		} else {
			if (this.playerTwoTower != null) {
				g2d.drawImage(this.playerTwoTower, 0, 0, VisualVertex.PIXELS_PER_SIDE, VisualVertex.PIXELS_PER_SIDE, null);
			} else {
				g2d.drawString("O", VisualVertex.PIXELS_PER_SIDE, VisualVertex.PIXELS_PER_SIDE);
				return false;
			}
		}
		return true;
	}
}
