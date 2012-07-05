package game;

import java.awt.Color;

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

public class TwixT extends Game {
	private PlayerManager playerManager;
	private GameBoard board;
	private int gridSize;

	public static Player playerOne;
	public static Player playerTwo;

	private Color playerOneColor = Color.GREEN;
	private Color playerTwoColor = Color.MAGENTA;

	private TwixTVertex[] startVerticesOne;
	private TwixTVertex[] endVerticesOne;
	private TwixTVertex[] startVerticesTwo;
	private TwixTVertex[] endVerticesTwo;

	private TwixTVertex originVertex;

	@Override
	protected boolean onVertexClick(VisualVertex vertex) {
		TwixTVertex vex = (TwixTVertex) vertex;
		if (vex.getPlayer() == this.playerManager.getActivePlayer()) {
			if (this.originVertex != null) {
				if (this.originVertex == vex) {
					this.getGameManager().getViewManager().displayErrorMessage("Place your tower or wall");
					this.originVertex = null;
					return true;
				}
				if (this.checkEdgeBuild(vex)) {
					SimpleVisualEdge edge = new SimpleVisualEdge(this.originVertex, vex);
					Graph graph = this.board.getGraph();
					if (PlanarityCheck.performAlgorithm(graph, edge)) {
						edge = (SimpleVisualEdge) this.board.addVisualEdge(originVertex, vex);
						if (playerOne == this.playerManager.getActivePlayer()) {
							edge.setStrokeColor(this.playerOneColor);
							// Checks if Player One has a path connecting his two sides
							for (TwixTVertex vertexA : startVerticesOne) {
								for (TwixTVertex vertexB : endVerticesOne) {
									if (FindPath.performAlgorithm(graph, vertexA, vertexB)) {
										this.playerManager.setActivePlayerAsWinning();
										this.getGameManager().finishGame();
										return true;
									}
								}
							}
						} else {
							edge.setStrokeColor(this.playerTwoColor);
							// Checks if Player Two has a path connecting his two sides
							for (TwixTVertex vertexA : startVerticesTwo) {
								for (TwixTVertex vertexB : endVerticesTwo) {
									if (FindPath.performAlgorithm(graph, vertexA, vertexB)) {
										this.playerManager.setActivePlayerAsWinning();
										this.getGameManager().finishGame();
										return true;
									}
								}
							}
						}
						this.getGameManager().getViewManager().displayErrorMessage("Place your tower or wall");
						this.originVertex = null;
						this.playerManager.nextPlayer();
						return true;
					} else {
						this.originVertex = null;
						this.getGameManager().getViewManager().displayErrorMessage("Walls can't intersect");
					}
				} else {
					this.originVertex =  null;
					this.getGameManager().getViewManager().displayErrorMessage("You can't place a wall here");
				}
			} else {
				this.originVertex = vex;
				this.getGameManager().getViewManager().displayErrorMessage("Select a second tower to place a wall");
			}
		} else {
			this.originVertex = null;
			this.getGameManager().getViewManager().displayErrorMessage("Can't select the tower of your enemy");
		}
		return true;
	}

	@Override
	protected boolean onEmptyGridPointClick(GridPoint gridPoint) {
		if (gridPoint.getPositionX() > 0 && gridPoint.getPositionX() < this.gridSize && gridPoint.getPositionY() > 0
				&& gridPoint.getPositionY() < this.gridSize) {
			TwixTVertex addVertex = new TwixTVertex(gridPoint);
			addVertex.setPlayer(this.playerManager.getActivePlayer());
			this.board.addVisualVertex(addVertex);
			this.playerManager.nextPlayer();
			this.getGameManager().getViewManager().displayErrorMessage("Place your tower or wall");
			this.originVertex = null;
			return true;
		}
		return false;
	}

	@Override
	protected boolean onGameInit() {
		this.playerManager = this.getGameManager().getPlayerManager();
		playerOne = this.playerManager.getPlayers().get(0);
		playerTwo = this.playerManager.getPlayers().get(1);
		this.board = this.getGameManager().getGameBoard();
		// angenommen horizontal grid points = vertical
		this.gridSize = this.board.getGrid().getHorizontalGridPoints();
		this.startVerticesOne = new TwixTVertex[this.gridSize];
		this.endVerticesOne = new TwixTVertex[this.gridSize];
		this.startVerticesTwo = new TwixTVertex[this.gridSize];
		this.endVerticesTwo = new TwixTVertex[this.gridSize];
		
		// Create board
		GridPoint tmpGridPoint;
		TwixTVertex tmpVertex;
		for (int i = 1; i < this.gridSize - 1; i++) {
			tmpGridPoint = new GridPoint(i, 0);
			tmpVertex = new TwixTVertex(tmpGridPoint);
			tmpVertex.setPlayer(playerOne);
			this.startVerticesOne[i - 1] = tmpVertex;
			this.board.addVisualVertex(tmpVertex);

			tmpGridPoint = new GridPoint(i, this.gridSize - 1);
			tmpVertex = new TwixTVertex(tmpGridPoint);
			tmpVertex.setPlayer(playerOne);
			this.endVerticesOne[i - 1] = tmpVertex;
			this.board.addVisualVertex(tmpVertex);

			tmpGridPoint = new GridPoint(0, i);
			tmpVertex = new TwixTVertex(tmpGridPoint);
			tmpVertex.setPlayer(playerTwo);
			this.startVerticesTwo[i - 1] = tmpVertex;
			this.board.addVisualVertex(tmpVertex);

			tmpGridPoint = new GridPoint(this.gridSize - 1, i);
			tmpVertex = new TwixTVertex(tmpGridPoint);
			tmpVertex.setPlayer(playerTwo);
			this.endVerticesTwo[i - 1] = tmpVertex;
			this.board.addVisualVertex(tmpVertex);
		}
		
		return true;
	}

	@Override
	protected boolean onGameStart() {
		this.playerManager = this.getGameManager().getPlayerManager();
		playerOne = this.playerManager.getPlayers().get(0);
		playerTwo = this.playerManager.getPlayers().get(1);
		this.board = this.getGameManager().getGameBoard();
		
		// angenommen horizontal grid points = vertical
		this.gridSize = this.board.getGrid().getHorizontalGridPoints();
		
		// Recreating Arrays
		this.startVerticesOne = new TwixTVertex[this.gridSize];
		this.endVerticesOne = new TwixTVertex[this.gridSize];
		this.startVerticesTwo = new TwixTVertex[this.gridSize];
		this.endVerticesTwo = new TwixTVertex[this.gridSize];
		
		GridPoint tmpGridPoint;
		TwixTVertex tmpVertex;
		for (int i = 1; i < this.gridSize - 1; i++) {
			tmpGridPoint = new GridPoint(i, 0);
			tmpVertex = (TwixTVertex) this.board.getGrid().getVisualVertexAtGridPoint(tmpGridPoint);
			this.startVerticesOne[i - 1] = tmpVertex;

			tmpGridPoint = new GridPoint(i, this.gridSize - 1);
			tmpVertex = (TwixTVertex) this.board.getGrid().getVisualVertexAtGridPoint(tmpGridPoint);
			this.endVerticesOne[i - 1] = tmpVertex;

			tmpGridPoint = new GridPoint(0, i);
			tmpVertex = (TwixTVertex) this.board.getGrid().getVisualVertexAtGridPoint(tmpGridPoint);
			this.startVerticesTwo[i - 1] = tmpVertex;

			tmpGridPoint = new GridPoint(this.gridSize - 1, i);
			tmpVertex = (TwixTVertex) this.board.getGrid().getVisualVertexAtGridPoint(tmpGridPoint);
			this.endVerticesTwo[i - 1] = tmpVertex;
		}
		// Refresh vertices... -.-"
		for (Vertex vtex : board.getGraph().getVertices()) {
			((TwixTVertex) vtex).reload();
		}
		return true;
	}

	/**
	 * Checks if the target Vertex has the right distance to the originVertex
	 * 
	 * @param targetVertex
	 * @return
	 */
	private boolean checkEdgeBuild(TwixTVertex targetVertex) {
		int x1 = this.originVertex.getGridPoint().getPositionX();
		int y1 = this.originVertex.getGridPoint().getPositionY();
		int x2 = targetVertex.getGridPoint().getPositionX();
		int y2 = targetVertex.getGridPoint().getPositionY();

		return ((Math.abs(x1 - x2) == 2 && Math.abs(y1 - y2) == 1) || (Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 2));
	}

}
