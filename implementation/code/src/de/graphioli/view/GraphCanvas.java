package de.graphioli.view;
import javax.swing.JPanel;

public class GraphCanvas extends JPanel {

	/**
	 * The parent {@link GameWindow} associated with this {@ GraphCanvas}
	 */
	private GameWindow parentGameWindow;
	
	/**
	 * The {@link VisualGrid} associated with this {@link GraphCanvas}
	 */
	
	/**
	 * Creates a {@link GraphCanvas} and registers its parent {@link GameWindow}.
	 * 
	 * @param parentGameWindow The {@link GameWindow} that contains this {@link GraphCanvas}
	 */
	public GraphCanvas(GameWindow parentGameWindow) {
		
	}
	
	/**
	 * Updates and redraws the {@link GraphCanvas}.
	 * 
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	public boolean updateCanvas() {
		return true;
		
	}
}
