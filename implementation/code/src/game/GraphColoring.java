package game;

import java.awt.Color;
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
	private static final int CLRID_RED = 0;
	private static final int CLRID_GREEN = 1;
	private static final int CLRID_BLUE = 2;
	private static final int CLRID_BLANK = -1;

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
	 * Quick access to the game board.
	 */
	private GameBoard mBoard;

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

		this.singleplayer = this.getGameManager().getPlayerManager().getPlayers().size() == 1;

		this.mBoard = this.getGameManager().getGameBoard();
		this.buttons = new GraphColoringButtonVertex[COLOR_COUNT];
		for (int i = 0; i < COLOR_COUNT; i++) {
			this.buttons[i] = new GraphColoringButtonVertex(new GridPoint(i + 1, 0));
			this.buttons[i].setColorID(i);
			this.mBoard.addVisualVertex(this.buttons[i]);
		}

		this.selectedButton = this.buttons[0];
		this.buttons[0].setHighlighted(true);

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean onGameStart() {
		this.generateLevel();
		return true;
	}

	/**
	 * Builds a level for the game.
	 */
	private void generateLevel() {
		this.vertices = new GraphColoringVertex[4];
		GridPoint tmpPoint;
		for (int i = 0; i < 4; i++) {
			tmpPoint = new GridPoint((i / 2) * 3 + 2, (i % 2) * 3 + 2);
			this.vertices[i] = new GraphColoringVertex(tmpPoint);
			this.mBoard.addVisualVertex(this.vertices[i]);
		}

		
		for (int i = 0; i < 4; i++) {
			this.mBoard.addVisualEdge(this.vertices[i], this.vertices[(i + 1) % 4]);
		}
		this.mBoard.addVisualEdge(this.vertices[0], this.vertices[2]);

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
				this.getGameManager().finishGame();
			} else {
				this.getGameManager().getPlayerManager().nextPlayer();
			}
		} else {
			this.getGameManager().getViewManager().displayErrorMessage("Invalid move.");
		}

	}

	/**
	 * This class represents a vertex for the GraphColoring game.
	 * 
	 * @author Team Graphioli
	 */
	private class GraphColoringVertex extends SimpleVisualVertex {

		/**
		 * The color ID of this vertex.
		 */
		private int colorID;

		/**
		 * Constructs a GraphColoringVertex with the given GridPoint.
		 * 
		 * @param gridPoint
		 *            the GridPoint this vertex is located on.
		 */
		public GraphColoringVertex(GridPoint gridPoint) {
			super(gridPoint);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		protected void init() {
			super.init();
			setColorID(CLRID_BLANK);
		}

		/**
		 * Returns the color ID.
		 * 
		 * @return the color ID:
		 */
		public int getColorID() {
			return this.colorID;
		}

		/**
		 * Sets the color ID of this vertex and the color accordingly.
		 * 
		 * @param id
		 *            the new color ID.
		 */
		public void setColorID(int id) {
			this.colorID = id;

			switch (this.colorID) {
				case CLRID_RED:
					setFillColor(Color.RED);
					break;
				case CLRID_GREEN:
					setFillColor(Color.GREEN);
					break;
				case CLRID_BLUE:
					setFillColor(Color.BLUE);
					break;
				default:
					setFillColor(Color.WHITE);
			}
		}
	}

	/**
	 * This class represents a special vertex for the GraphColoring game used as
	 * a button.
	 * 
	 * @author Team Graphioli
	 */
	private class GraphColoringButtonVertex extends GraphColoringVertex {

		/**
		 * Constructs a GraphColoringButtonVertex with the given GridPoint.
		 * 
		 * @param gridPoint
		 *            the GridPoint this vertex is located on.
		 */
		public GraphColoringButtonVertex(GridPoint gridPoint) {
			super(gridPoint);
			// TODO Auto-generated constructor stub
		}

		/**
		 * (Un-)highlights this button vertex.
		 * 
		 * @param highlighted
		 *            the new {@code highlighted} value.
		 */
		public void setHighlighted(boolean highlighted) {
			setStrokeColor(highlighted ? Color.YELLOW : Color.BLACK);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		protected void init() {
			super.init();
			this.setHighlighted(false);
			setStrokeWeight(2);
		}

	}

}
