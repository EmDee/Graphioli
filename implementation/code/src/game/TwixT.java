package game;

import de.graphioli.algorithms.FindPath;
import de.graphioli.algorithms.PlanarityCheck;
import de.graphioli.controller.Game;
import de.graphioli.controller.PlayerManager;
import de.graphioli.model.GameBoard;
import de.graphioli.model.Graph;
import de.graphioli.model.GridPoint;
import de.graphioli.model.Player;
import de.graphioli.model.SimpleVisualEdge;
import de.graphioli.model.Vertex;
import de.graphioli.model.VisualVertex;

import java.awt.Color;
import java.util.HashMap;

/**
 * Our implemented game containing the game’s logic of TwixT.
 * 
 * @author Team Graphioli
 */
public class TwixT extends Game {

	private static String playerOneImgFile = "Awesome1.png";
	private static String playerTwoImgFile = "Awesome2.png";

	private static Color playerOneColor = Color.MAGENTA;
	private static Color playerTwoColor = Color.GREEN;

	private PlayerManager playerManager;
	private GameBoard board;
	private int gridSize;

	private Player playerOne;
	private Player playerTwo;

	private TwixTVertex startVertexOne;
	private TwixTVertex endVertexOne;
	private TwixTVertex startVertexTwo;
	private TwixTVertex endVertexTwo;

	private TwixTVertex originVertex;

	/**
	 * Selects a tower on the {@link GameBoard} and creates an {@link Edge}
	 * between this {@link Vertex} and the one previously selected if they
	 * belong to the same {@link Player} and the new Edge would not intersect
	 * with another one. It then checks if the baselines are connected and the
	 * game is finished.
	 * 
	 * @param vertex
	 *            The VisualVertex that was clicked
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	@Override
	protected boolean onVertexClick(VisualVertex vertex) {
		TwixTVertex vex = (TwixTVertex) vertex;

		if (vex.getPlayer() != this.playerManager.getActivePlayer()) {
			// Tower not belonging to active player.
			if (this.originVertex != null) {
				this.originVertex.setHighlighted(false);
				this.originVertex = null;
			}
			this.getGameManager().getViewManager()
					.displayErrorMessage(this.getGameResources().getStringResource("enemy_tower"));
			return true;
		}

		if (this.originVertex == null) {
			// First tower selected.
			this.originVertex = vex;
			this.originVertex.setHighlighted(true);
			this.getGameManager().getViewManager()
					.displayErrorMessage(this.getGameResources().getStringResource("select_second"));
			return true;
		}

		if (this.originVertex == vex) {
			// Undo selection
			this.getGameManager().getViewManager()
					.displayErrorMessage(this.getGameResources().getStringResource("place"));
			this.originVertex.setHighlighted(false);
			this.originVertex = null;
			return true;
		}

		if (this.checkEdgeBuild(vex)) {
			SimpleVisualEdge edge = new SimpleVisualEdge(this.originVertex, vex);
			Graph graph = this.board.getGraph();
			if (PlanarityCheck.performAlgorithm(graph, edge)) {
				edge = new SimpleVisualEdge(this.originVertex, vex);
				if (!this.board.addVisualEdge(edge)) {
					this.getGameManager().getViewManager()
							.displayErrorMessage(this.getGameResources().getStringResource("already_wall"));
					this.originVertex.setHighlighted(false);
					this.originVertex = null;
					return true;
				}
				if (this.playerOne == this.playerManager.getActivePlayer()) {
					edge.setStrokeColor(TwixT.playerOneColor);
					// Checks if Player One has a path connecting his two
					// sides

					if (FindPath.performAlgorithm(graph, this.startVertexOne, this.endVertexOne)) {
						this.playerManager.setActivePlayerAsWinning();
						this.getGameManager().finishGame();
						return true;
					}
				} else {
					edge.setStrokeColor(playerTwoColor);
					// Checks if Player Two has a path connecting his two
					// sides
					if (FindPath.performAlgorithm(graph, this.startVertexTwo, this.endVertexTwo)) {
						this.playerManager.setActivePlayerAsWinning();
						this.getGameManager().finishGame();
						return true;
					}
				}
				this.getGameManager().getViewManager()
						.displayErrorMessage(this.getGameResources().getStringResource("place"));
				this.originVertex.setHighlighted(false);
				this.originVertex = null;
				this.playerManager.nextPlayer();
				return true;
			} else {
				this.originVertex.setHighlighted(false);
				this.originVertex = null;
				this.getGameManager().getViewManager()
						.displayErrorMessage(this.getGameResources().getStringResource("intersecting"));
			}
		} else {
			this.originVertex.setHighlighted(false);
			this.originVertex = null;
			this.getGameManager().getViewManager()
					.displayErrorMessage(this.getGameResources().getStringResource("invalid"));
		}

		return true;
	}

	/**
	 * Sets a new {@link TwixTVertex} belonging to the active {@link Player}
	 * onto the given {@link GridPoint}, if possible.
	 * 
	 * @param gridPoint
	 *            The empty GridPoint that was clicked
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	@Override
	protected boolean onEmptyGridPointClick(GridPoint gridPoint) {
		if (gridPoint.getPositionX() > 0
				&& gridPoint.getPositionX() < this.gridSize
				&& gridPoint.getPositionY() > 0
				&& gridPoint.getPositionY() < this.gridSize) {
			TwixTVertex addVertex = new TwixTVertex(gridPoint);
			addVertex.setPlayer(this.playerManager.getActivePlayer());
			this.board.addVisualVertex(addVertex);
			this.playerManager.nextPlayer();
			this.getGameManager().getViewManager()
					.displayErrorMessage(this.getGameResources().getStringResource("place"));
			if (this.originVertex != null) {
				this.originVertex.setHighlighted(false);
				this.originVertex = null;
			}
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean onGameInit() {

		this.initFields();

		// Create board
		GridPoint tmpGridPoint;
		SimpleVisualEdge tmpEdge;
		TwixTVertex prevVertex = null;
		TwixTVertex tmpVertex;

		for (int i = 1; i < this.gridSize - 1; i++) {
			tmpGridPoint = new GridPoint(i, 0);
			tmpVertex = new TwixTVertex(tmpGridPoint);
			tmpVertex.setPlayer(this.playerOne);
			this.board.addVisualVertex(tmpVertex);

			if (prevVertex != null) {
				tmpEdge = new SimpleVisualEdge(prevVertex, tmpVertex);
				tmpEdge.setStrokeColor(playerOneColor);
				this.board.addVisualEdge(tmpEdge);
			}
			prevVertex = tmpVertex;
		}
		this.startVertexOne = prevVertex;
		prevVertex = null;

		for (int i = 1; i < this.gridSize - 1; i++) {
			tmpGridPoint = new GridPoint(i, this.gridSize - 1);
			tmpVertex = new TwixTVertex(tmpGridPoint);
			tmpVertex.setPlayer(this.playerOne);
			this.board.addVisualVertex(tmpVertex);

			if (prevVertex != null) {
				tmpEdge = new SimpleVisualEdge(prevVertex, tmpVertex);
				tmpEdge.setStrokeColor(playerOneColor);
				this.board.addVisualEdge(tmpEdge);
			}
			prevVertex = tmpVertex;
		}
		this.endVertexOne = prevVertex;
		prevVertex = null;

		for (int i = 1; i < this.gridSize - 1; i++) {
			tmpGridPoint = new GridPoint(0, i);
			tmpVertex = new TwixTVertex(tmpGridPoint);
			tmpVertex.setPlayer(this.playerTwo);
			this.board.addVisualVertex(tmpVertex);

			if (prevVertex != null) {
				tmpEdge = new SimpleVisualEdge(prevVertex, tmpVertex);
				tmpEdge.setStrokeColor(playerTwoColor);
				this.board.addVisualEdge(tmpEdge);
			}
			prevVertex = tmpVertex;
		}
		this.startVertexTwo = prevVertex;
		prevVertex = null;

		for (int i = 1; i < this.gridSize - 1; i++) {
			tmpGridPoint = new GridPoint(this.gridSize - 1, i);
			tmpVertex = new TwixTVertex(tmpGridPoint);
			tmpVertex.setPlayer(this.playerTwo);
			this.board.addVisualVertex(tmpVertex);

			if (prevVertex != null) {
				tmpEdge = new SimpleVisualEdge(prevVertex, tmpVertex);
				tmpEdge.setStrokeColor(playerTwoColor);
				this.board.addVisualEdge(tmpEdge);
			}
			prevVertex = tmpVertex;
		}
		this.endVertexTwo = prevVertex;
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean onGameStart() {

		String turnMessage;
		if (this.getGameManager().getPlayerManager().getActivePlayer() == this.playerOne) {
			turnMessage = this.getGameResources().getStringResource("pink_turn");
		} else {
			turnMessage = this.getGameResources().getStringResource("green_turn");
		}

		this.getGameManager().getViewManager().displayErrorMessage(turnMessage);

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean onGameLoad(HashMap<Integer, Object> customValues) {
		this.initFields();

		this.startVertexOne = (TwixTVertex) this.board.getGrid().getVisualVertexAtGridPoint(
				new GridPoint(this.gridSize - 2, 0));
		this.endVertexOne = (TwixTVertex) this.board.getGrid().getVisualVertexAtGridPoint(
				new GridPoint(this.gridSize - 2, this.gridSize - 1));
		this.startVertexTwo = (TwixTVertex) this.board.getGrid().getVisualVertexAtGridPoint(
				new GridPoint(0, this.gridSize - 2));
		this.endVertexTwo = (TwixTVertex) this.board.getGrid().getVisualVertexAtGridPoint(
				new GridPoint(this.gridSize - 1, this.gridSize - 2));

		// Refresh vertices... -.-"
		for (Vertex vtex : this.board.getGraph().getVertices()) {
			((TwixTVertex) vtex).update();
		}

		return true;
	}

	private void initFields() {
		this.originVertex = null;
		this.playerManager = this.getGameManager().getPlayerManager();
		this.playerOne = this.playerManager.getPlayers().get(0);
		this.playerTwo = this.playerManager.getPlayers().get(1);

		TwixTVertex.initImages(this.playerOne, this.getGameResources().getImageRessource(playerOneImgFile),
				this.playerTwo, this.getGameResources().getImageRessource(playerTwoImgFile));

		this.board = this.getGameManager().getGameBoard();

		this.gridSize = Math.min(this.board.getGrid().getHorizontalGridPoints(), this.board.getGrid()
				.getVerticalGridPoints());

	}

	/**
	 * Checks if the target Vertex has the right distance to the originVertex.
	 * 
	 * @param targetVertex
	 *            The target vertex
	 * @return <code>true</code> if those two vertices have the right distance,
	 *         <code>false</code> otherwise
	 */
	private boolean checkEdgeBuild(TwixTVertex targetVertex) {
		int x1 = this.originVertex.getGridPoint().getPositionX();
		int y1 = this.originVertex.getGridPoint().getPositionY();
		int x2 = targetVertex.getGridPoint().getPositionX();
		int y2 = targetVertex.getGridPoint().getPositionY();

		return (Math.abs(x1 - x2) == 2 && Math.abs(y1 - y2) == 1) || (Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean onGameSave(HashMap<Integer, Object> customValues) {
		return true;
	}

}
