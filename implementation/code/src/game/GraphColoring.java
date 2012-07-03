package game;

import java.awt.Color;
import java.util.LinkedList;
import java.util.logging.Logger;

import de.graphioli.controller.Game;
import de.graphioli.controller.GameManager;
import de.graphioli.controller.ViewManager;
import de.graphioli.model.GameBoard;
import de.graphioli.model.GridPoint;
import de.graphioli.model.SimpleVisualEdge;
import de.graphioli.model.SimpleVisualVertex;
import de.graphioli.model.Vertex;
import de.graphioli.model.VisualEdge;
import de.graphioli.model.VisualVertex;

/**
 * This is a imlementation of the GraphColoring game (single- and multiplayer).
 * 
 * @author Team Graphioli
 */
public class GraphColoring extends Game {

	/*
	 * IDs for the colors.
	 */
	public static final int CLRID_RED = 0;
	public static final int CLRID_GREEN = 1;
	public static final int CLRID_BLUE = 2;
	public static final int CLRID_BLANK = -1;

	/**
	 * Number of colors used.
	 */
	private static final int COLOR_COUNT = 3;

	/**
	 * List of vertices used in the game.
	 */
	private GraphColoringVertex[] vertices;

	/**
	 * List of the buttons.
	 */
	private GraphColoringButtonVertex[] buttons;

	/**
	 * The button currently selected.
	 */
	private GraphColoringButtonVertex selectedButton;

	/**
	 * Whether this is a singleplayer instance or not.
	 */
	private boolean singleplayer;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean onVertexClick(VisualVertex vertex) {
		if (vertex instanceof GraphColoringButtonVertex) {
			GraphColoringButtonVertex btn = (GraphColoringButtonVertex) vertex;
			selectedButton.setHighlighted(false);
			selectedButton = btn;
			btn.setHighlighted(true);
		} else {
			GraphColoringVertex vtex = (GraphColoringVertex) vertex;

			if (singleplayer) {
				handleSingleplayerMove(vtex);
			} else {
				handleMultiplayerMove(vtex);
			}

		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean onEmptyGridPointClick(GridPoint gridPoint) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean onGameInit() {

		this.buttons = new GraphColoringButtonVertex[COLOR_COUNT];
		for (int i = 0; i < COLOR_COUNT; i++) {
			this.buttons[i] = new GraphColoringButtonVertex(new GridPoint(i + 1, 0));
			this.buttons[i].setColorID(i);

			this.getGameManager().getGameBoard().addVisualVertex(this.buttons[i]);
		}

		this.generateLevel();

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean onGameStart() {
		this.singleplayer = this.getGameManager().getPlayerManager().getPlayers().size() == 1;

		if (this.buttons == null || this.vertices == null) {
			this.reloadLevel();
		}
		this.selectedButton = this.buttons[0];
		this.selectedButton.setHighlighted(true);

		return true;
	}

	/**
	 * Builds a level for the game.
	 */
	private void generateLevel() {
		final GameBoard mBoard = this.getGameManager().getGameBoard();
		this.vertices = new GraphColoringVertex[4];
		GridPoint tmpPoint;
		for (int i = 0; i < 4; i++) {
			tmpPoint = new GridPoint((i / 2) * 3 + 2, (i % 2) * 3 + 2);
			this.vertices[i] = new GraphColoringVertex(tmpPoint);
			mBoard.addVisualVertex(this.vertices[i]);
		}

		for (int i = 0; i < 4; i++) {
			mBoard.addVisualEdge(this.vertices[i], this.vertices[(i + 1) % 4]);
		}
		mBoard.addVisualEdge(this.vertices[0], this.vertices[2]);

	}

	/**
	 * Checks if every vertex of the game has a color and no adjacent vertices
	 * have the same color.
	 * 
	 * @return {@true} if graph is completely and correctly colored.
	 */
	private boolean isGraphColored() {
		ViewManager vm = this.getGameManager().getViewManager();
		for (GraphColoringVertex tmpTex : this.vertices) {
			if (tmpTex.getColorID() == CLRID_BLANK) {
				vm.displayErrorMessage("Graph not completely colored.");
				return false;
			}
			for (Vertex adjTex : tmpTex.getAdjacentVertices()) {
				if (((GraphColoringVertex) adjTex).getColorID() == tmpTex.getColorID()) {
					vm.displayErrorMessage("Coloring invalid.");
					return false;
				}
			}
		}
		vm.displayErrorMessage("Graph colored.");
		return true;
	}

	/**
	 * Checks if the given vertex may be colored with the given color.
	 * 
	 * @param vtex
	 *            the vertex to color.
	 * @param colorID
	 *            the ID of the color.
	 * @return {@code true} if the given vertex may be colored with the given
	 *         color.
	 */
	private boolean isColoringValid(GraphColoringVertex vtex, int colorID) {
		int currentID;
		for (Vertex adjTex : vtex.getAdjacentVertices()) {
			currentID = ((GraphColoringVertex) adjTex).getColorID();
			if (currentID != CLRID_BLANK && currentID == colorID) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if there is a remaining valid move.
	 * 
	 * @return {@true} if there is a remaining valid move.
	 */
	private boolean isColoringPossible() {
		for (GraphColoringVertex vtex : this.vertices) {
			if (vtex.getColorID() == CLRID_BLANK) {
				for (int i = 0; i < COLOR_COUNT; i++) {
					if (this.isColoringValid(vtex, i)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Handles a move in a singleplayer instance.
	 * 
	 * @param vtex
	 *            the vertex clicked on.
	 */
	private void handleSingleplayerMove(GraphColoringVertex vtex) {
		vtex.setColorID(this.selectedButton.getColorID());
		if (this.isGraphColored()) {
			this.getGameManager().getPlayerManager().setActivePlayerAsWinning();
			this.getGameManager().finishGame();
		}
	}

	/**
	 * Handles a move in a multiplayer instance.
	 * 
	 * @param vtex
	 *            the vertex clicked on.
	 */
	private void handleMultiplayerMove(GraphColoringVertex vtex) {
		if (vtex.getColorID() != CLRID_BLANK) {
			this.getGameManager().getViewManager().displayErrorMessage("Already colored.");
			return;
		}

		if (this.isColoringValid(vtex, this.selectedButton.getColorID())) {
			vtex.setColorID(this.selectedButton.getColorID());
			if (this.isGraphColored() || !this.isColoringPossible()) {
				this.getGameManager().getViewManager().displayErrorMessage("Game over.");
				this.getGameManager().getPlayerManager().setActivePlayerAsWinning();
				this.getGameManager().finishGame();
			} else {
				this.getGameManager().getPlayerManager().nextPlayer();
			}
		} else {
			this.getGameManager().getViewManager().displayErrorMessage("Invalid move.");
		}

	}

	private void reloadLevel() {
		LinkedList<GraphColoringVertex> tmpVtices = new LinkedList<GraphColoringVertex>();
		LinkedList<GraphColoringButtonVertex> tmpBtns = new LinkedList<GraphColoringButtonVertex>();

		for (Vertex vtex : this.getGameManager().getGameBoard().getGraph().getVertices()) {
			if (vtex instanceof GraphColoringVertex) {
				if (vtex instanceof GraphColoringButtonVertex) {
					GraphColoringButtonVertex gcbtnv = (GraphColoringButtonVertex) vtex;
					gcbtnv.setHighlighted(false);
					tmpBtns.add(gcbtnv);
				} else {
					GraphColoringVertex gcv = (GraphColoringVertex) vtex;
					gcv.update();
					tmpVtices.add(gcv);
				}
			}
		}

		this.buttons = new GraphColoringButtonVertex[tmpBtns.size()];
		tmpBtns.toArray(this.buttons);
		this.vertices = new GraphColoringVertex[tmpVtices.size()];
		tmpVtices.toArray(this.vertices);
	}
}
