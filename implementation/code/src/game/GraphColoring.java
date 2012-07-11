package game;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Logger;

import de.graphioli.controller.Game;
import de.graphioli.controller.GameManager;
import de.graphioli.controller.ViewManager;
import de.graphioli.model.GameBoard;
import de.graphioli.model.GridPoint;
import de.graphioli.model.MenuItem;
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
	public static final int CLRID_YELLOW = 3;
	public static final int CLRID_BLANK = -1;

	/*
	 * Keys
	 */
	private static final int KEY_SELECTED = 0;
	private static final int KEY_COLORCOUNT = 1;
	private static final int KEY_SELECTED_LEVEL = 2;
	
	/*
	 * Menu IDs
	 */
	private static final int MENU_NEXT = 1;
	private static final int MENU_PREV = 2;

	/**
	 * Number of colors used.
	 */
	private int colorCount;

	/**
	 * The selected level.
	 */
	private int selectedLevel = 0;

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

		this.generateLevel();
		this.generateButtons();
		
		return true;
	}
	
	/**
	 * Creates and adds the button vertices.
	 */
	private void generateButtons() {
		this.buttons = new GraphColoringButtonVertex[colorCount];
		for (int i = 0; i < colorCount; i++) {
			this.buttons[i] = new GraphColoringButtonVertex(new GridPoint(i + 1, 0));
			this.buttons[i].setColorID(i);

			this.getGameManager().getGameBoard().addVisualVertex(this.buttons[i]);
		}

		this.selectedButton = this.buttons[0];
		this.selectedButton.setHighlighted(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean onGameStart() {
		this.singleplayer = this.getGameManager().getPlayerManager().getPlayers().size() == 1;

		return true;
	}

	/**
	 * Builds a level for the game.
	 */
	private void generateLevel() {
		GraphColoringLevel level = GraphColoringLevel.getLevelInstance(this.selectedLevel + 1);
				
		// Adding level to board
		final GameBoard mBoard = this.getGameManager().getGameBoard();
		this.colorCount = level.getColorCount();
		this.vertices = level.getVertices();
		
		for (int i = 0; i < this.vertices.length; i++) {
			mBoard.addVisualVertex(this.vertices[i]);
		}

		for (int i = 0; i < level.getEdges().length; i++) {
			mBoard.addVisualEdge(level.getEdges()[i]);
		}

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
				vm.displayErrorMessage(this.getGameResources().getStringResource("NOT_COLORED"));
				return false;
			}
			for (Vertex adjTex : tmpTex.getAdjacentVertices()) {
				if (((GraphColoringVertex) adjTex).getColorID() == tmpTex.getColorID()) {
					vm.displayErrorMessage(this.getGameResources().getStringResource("COL_INVALID"));
					return false;
				}
			}
		}
		vm.displayErrorMessage(this.getGameResources().getStringResource("COLORED"));
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
				for (int i = 0; i < colorCount; i++) {
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
			this.nextLevel();
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
			this.getGameManager().getViewManager().displayErrorMessage(this.getGameResources().getStringResource("ALREADY_COL"));
			return;
		}

		if (this.isColoringValid(vtex, this.selectedButton.getColorID())) {
			vtex.setColorID(this.selectedButton.getColorID());
			if (this.isGraphColored() || !this.isColoringPossible()) {
				this.getGameManager().getPlayerManager().setActivePlayerAsWinning();
				this.getGameManager().finishGame();
			} else {
				this.getGameManager().getPlayerManager().nextPlayer();
			}
		} else {
			this.getGameManager().getViewManager().displayErrorMessage(this.getGameResources().getStringResource("INVALID"));
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

	@Override
	protected boolean onGameLoad(HashMap<Integer, Object> customValues) {
		this.reloadLevel();
		Integer selection = (Integer) customValues.get(KEY_SELECTED);
		this.selectedLevel = (Integer) customValues.get(KEY_SELECTED_LEVEL);
		this.colorCount = (Integer) customValues.get(KEY_COLORCOUNT);
		for (int i = 0; i < this.buttons.length; i++) {
			if (this.buttons[i].getColorID() == selection) {
				this.buttons[i].setHighlighted(true);
				this.selectedButton = this.buttons[i];
				return true;
			}
		}
		return false;
	}

	@Override
	protected boolean onGameSave(HashMap<Integer, Object> customValues) {
		customValues.put(KEY_SELECTED, this.selectedButton.getColorID());
		customValues.put(KEY_COLORCOUNT, this.colorCount);
		customValues.put(KEY_SELECTED_LEVEL, this.selectedLevel);
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean onMenuItemClick(MenuItem item) {
		switch(item.getId()) {
			case MENU_NEXT:
				this.nextLevel();
				break;
			case MENU_PREV:
				this.prevLevel();
				break;
		}
		
		return true;
	}
	
	private void nextLevel() {
		this.getGameManager().getGameBoard().flush();
		this.getGameManager().getPlayerManager().initializePlayers();
		this.selectedLevel++;
		this.selectedLevel %= GraphColoringLevel.IMPLEMENTED_LEVEL_COUNT;
		this.generateLevel();
		this.generateButtons();
	}
	
	private void prevLevel() {
		this.getGameManager().getGameBoard().flush();
		this.getGameManager().getPlayerManager().initializePlayers();
		this.selectedLevel--;
		this.selectedLevel += GraphColoringLevel.IMPLEMENTED_LEVEL_COUNT;
		this.selectedLevel %= GraphColoringLevel.IMPLEMENTED_LEVEL_COUNT;
		this.generateLevel();
		this.generateButtons();
	}
}
