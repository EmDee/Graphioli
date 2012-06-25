package de.graphioli.games;

import java.util.logging.Logger;

import de.graphioli.controller.Game;
import de.graphioli.controller.GameManager;
import de.graphioli.model.GridPoint;
import de.graphioli.model.VisualVertex;

/**
 * Test game class.
 * 
 * @author Graphioli
 */
public class GameTest extends Game {

	/**
	 * Logging instance
	 */
	private static final Logger LOG = Logger.getLogger(GameManager.class.getName());

	/**
	 * Constructs a new instance of {@link GameTest}.
	 */
	public GameTest() {
		LOG.info("GameTest class instantiated.");
	}

	@Override
	protected boolean onVertexClick(VisualVertex vertex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean onEmptyGridPointClick(GridPoint gridPoint) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean onGameInit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean onGameStart() {
		// TODO Auto-generated method stub
		return false;
	}

}
