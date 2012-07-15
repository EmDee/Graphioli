package game;

import de.graphioli.controller.Game;
import de.graphioli.controller.ViewManager;
import de.graphioli.model.GameBoard;
import de.graphioli.model.GridPoint;
import de.graphioli.model.MenuItem;
import de.graphioli.model.Vertex;
import de.graphioli.model.VisualVertex;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.LinkedList;

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
	 * Indicates if the graph is already fully colored.
	 */
	private boolean graphColored;

	/**
	 * If the given VisualVertex is an GraphColoringButtonVertex its color is
	 * selected otherwise it colors the given vertex if possible with the
	 * selected color and decides if the game is finished by this move.
	 * 
	 * @param vertex
	 *            The VisualVertex that was clicked
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	@Override
	protected boolean onVertexClick(VisualVertex vertex) {
		if (vertex instanceof GraphColoringButtonVertex) {
			GraphColoringButtonVertex btn = (GraphColoringButtonVertex) vertex;
			this.selectedButton.setHighlighted(false);
			this.selectedButton = btn;
			btn.setHighlighted(true);
		} else {
			GraphColoringVertex vtex = (GraphColoringVertex) vertex;

			if (this.singleplayer) {
				handleSingleplayerMove(vtex);
			} else {
				handleMultiplayerMove(vtex);
			}

		}
		return true;
	}

	/**
	 * Has no function in this game.
	 * 
	 * @param gridPoint
	 *            The empty GridPoint that was clicked
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	@Override
	protected boolean onEmptyGridPointClick(GridPoint gridPoint) {
		return true;
	}

	/**
	 * Builds up the game's board.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
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
		this.buttons = new GraphColoringButtonVertex[this.colorCount];
		for (int i = 0; i < this.colorCount; i++) {
			this.buttons[i] = new GraphColoringButtonVertex(new GridPoint(i + 1, 0));
			this.buttons[i].setColorID(i);

			this.getGameManager().getGameBoard().addVisualVertex(this.buttons[i]);
		}

		this.selectedButton = this.buttons[0];
		this.selectedButton.setHighlighted(true);
	}

	/**
	 * Decides whether the game is started in single- or multiplayer mode.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
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
		this.getGameManager().getViewManager().displayErrorMessage(this.getGameResources().getStringResource("START"));
		GraphColoringLevel level = GraphColoringLevel.getLevelInstance(this.selectedLevel + 1);

		// Adding level to board
		final GameBoard mBoard = this.getGameManager().getGameBoard();
		this.colorCount = level.getColorCount();
		this.vertices = level.getVertices();
		this.graphColored = false;

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
				for (int i = 0; i < this.colorCount; i++) {
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
		if (!this.graphColored) {
			vtex.setColorID(this.selectedButton.getColorID());
			this.graphColored = this.isGraphColored();
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
			this.getGameManager().getViewManager()
					.displayErrorMessage(this.getGameResources().getStringResource("ALREADY_COL"));
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
			this.getGameManager().getViewManager()
					.displayErrorMessage(this.getGameResources().getStringResource("INVALID"));
		}

	}

	/**
	 * Reloads and sets up the level that was loaded from a save game.
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean onGameLoad(HashMap<Integer, Object> customValues) {
		this.reloadLevel();
		Integer selection = (Integer) customValues.get(KEY_SELECTED);
		this.selectedLevel = (Integer) customValues.get(KEY_SELECTED_LEVEL);
		this.colorCount = (Integer) customValues.get(KEY_COLORCOUNT);
		this.graphColored = this.isGraphColored();
		for (int i = 0; i < this.buttons.length; i++) {
			if (this.buttons[i].getColorID() == selection) {
				this.buttons[i].setHighlighted(true);
				this.selectedButton = this.buttons[i];
				return true;
			}
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
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
		switch (item.getId()) {
			case MENU_NEXT:
				this.nextLevel();
				break;
			case MENU_PREV:
				this.prevLevel();
				break;
			default:
				break;
		}

		return true;
	}

	/**
	 * Changes the selected button on key release (keys F1 - F4) depending on
	 * the colorCount.
	 * 
	 * @param keyCode
	 *            The code of the key that was released
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	@Override
	protected boolean onKeyRelease(int keyCode) {
		if (this.colorCount > (keyCode - KeyEvent.VK_F1) && (keyCode - KeyEvent.VK_F1) >= 0) {
			this.selectedButton.setHighlighted(false);
			this.selectedButton = this.buttons[keyCode - KeyEvent.VK_F1];
			this.selectedButton.setHighlighted(true);
		}

		if (keyCode == KeyEvent.VK_SPACE) {
			if (this.isGraphColored()) {
				this.nextLevel();
			}
		}
		return true;
	}

	/**
	 * Initializes the following level.
	 */
	private void nextLevel() {
		this.getGameManager().getGameBoard().flush();
		this.getGameManager().getPlayerManager().initializePlayers();
		this.selectedLevel++;
		this.selectedLevel %= GraphColoringLevel.IMPLEMENTED_LEVEL_COUNT;
		this.generateLevel();
		this.generateButtons();
	}

	/**
	 * Initializes the previous level.
	 */
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
