package game;

import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox.KeySelectionManager;

import de.graphioli.controller.Game;
import de.graphioli.model.GridPoint;
import de.graphioli.model.SimpleVisualEdge;
import de.graphioli.model.SimpleVisualVertex;
import de.graphioli.model.VisualEdge;
import de.graphioli.model.VisualVertex;

public class DirectedGame extends Game {
	
	private SimpleVisualVertex selectedVertex;

	@Override
	protected boolean onVertexClick(VisualVertex vertex) {
		SimpleVisualVertex cVtex = (SimpleVisualVertex) vertex;
		if (selectedVertex == null) {
			cVtex.setFillColor(Color.RED);
			selectedVertex = cVtex;
		} else {
			if (selectedVertex == cVtex) {
				selectedVertex.setFillColor(Color.ORANGE);
				selectedVertex = null;
				return true;
			}
			VisualEdge oldEdge = (VisualEdge) getGameManager().getGameBoard().getGraph().getEdge(selectedVertex, cVtex);
			if (oldEdge == null) {
				SimpleVisualEdge edge = new SimpleVisualEdge(selectedVertex, cVtex);
				edge.setStrokeColor(Color.BLUE);
				getGameManager().getGameBoard().addVisualEdge(edge);
			} else {
				getGameManager().getGameBoard().removeVisualEdge(oldEdge);
			}
			selectedVertex.setFillColor(Color.ORANGE);
			selectedVertex = null;
			
		}
		
		return true;
	}

	@Override
	protected boolean onEmptyGridPointClick(GridPoint gridPoint) {
		SimpleVisualVertex vtex = new SimpleVisualVertex(gridPoint);
		this.getGameManager().getGameBoard().addVisualVertex(vtex);
		vtex.setFillColor(Color.ORANGE);
		return true;
	}

	@Override
	protected boolean onGameInit() {
		return true;
	}

	@Override
	protected boolean onGameStart() {
		return true;
	}
	
	@Override
	protected boolean onKeyRelease(int keyCode) {
		if (keyCode == KeyEvent.VK_DELETE && selectedVertex != null) {
			getGameManager().getGameBoard().removeVisualVertex(selectedVertex);
			selectedVertex = null;
		}
		return true;
	}

}
