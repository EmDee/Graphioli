package de.graphioli.games;

import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Logger;

import de.graphioli.controller.Game;
import de.graphioli.controller.GameManager;
import de.graphioli.model.GameBoard;
import de.graphioli.model.GridPoint;
import de.graphioli.model.SimpleVisualVertex;
import de.graphioli.model.VisualVertex;

/**
 * Test game class.
 * 
 * @author Graphioli
 */
public class VisualVertexTestGame extends Game {
	/**
	 * Logging instance
	 */
	private static final Logger LOG = Logger.getLogger(VisualVertexTestGame.class.getName());

	/**
	 * Constructs a new instance of {@link VisualVertexTestGame}.
	 */
	public VisualVertexTestGame() {
		LOG.fine("GameTest class instantiated.");
	}

	@Override
	protected boolean onVertexClick(VisualVertex vertex) {
		return false;
	}

	@Override
	protected boolean onEmptyGridPointClick(GridPoint gridPoint) {
		return false;
	}

	@Override
	protected boolean onGameInit() {

		return true;
	}

	@Override
	protected boolean onGameStart() {
		/*
		 * Set up graph
		 */

		GridPoint gridPointA = new GridPoint(2, 2);
		GridPoint gridPointB = new GridPoint(4, 4);
		SimpleVisualVertex simpleVisualVertexA = new SimpleVisualVertex(gridPointA);
		SimpleVisualVertex simpleVisualVertexB = new SimpleVisualVertex(gridPointB);
		
		simpleVisualVertexA.setStrokeWeight(2);
		simpleVisualVertexB.setStrokeWeight(1);
		simpleVisualVertexA.setFillColor(Color.GREEN);

		ArrayList<VisualVertex> simpleVisualVertecies = new ArrayList<VisualVertex>();
		simpleVisualVertecies.add(simpleVisualVertexA);
		simpleVisualVertecies.add(simpleVisualVertexB);

		this.getGameManager().getGameBoard().addVisualVertices(simpleVisualVertecies);

		return true;
	}
}
