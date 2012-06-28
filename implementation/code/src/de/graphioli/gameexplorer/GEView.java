package de.graphioli.gameexplorer;

/**
 * This interface defines the methods the {@link GameExplorer} uses to
 * communicate with its graphical user interface (GUI).
 * 
 * @author Graphioli
 */
public interface GEView {

	/**
	 * Registers the controller for the {@link GameExplorer} user interface.
	 * 
	 * @param gameExplorer
	 *            The controlling GameExplorer
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	boolean registerController(GameExplorer gameExplorer);

	/**
	 * Generates the user interface of the {@link GameExplorer}.
	 * 
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	boolean generateView();

}
