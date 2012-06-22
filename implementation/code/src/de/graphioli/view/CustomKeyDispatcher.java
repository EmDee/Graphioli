package de.graphioli.view;
import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

/**
 * This class handles the keyboard input.
 * 
 * @author Graphioli
 */
public class CustomKeyDispatcher implements KeyEventDispatcher {

	/**
	 * The associated {@link GameWindow} that uses this {@link CustomKeyDispatcher}
	 */
	private GameWindow gameWindow;
	
	/**
	 * Creates a {@link CustomKeyDispatcher} and registers its parent {@link GameWindow}.
	 * 
	 * @param gameWindow The {@link GameWindow} using this {@link CustomKeyDispatcher}
	 */
	public CustomKeyDispatcher(GameWindow gameWindow) {
		
	}
	
	/**
	 * Dispatches the {@link KeyEvent} and calls onKeyRelease(int keyCode) in the {@link GameWindow}.
	 * 
	 * @param event The {@link KeyEvent} containing the code of the key that was released
	 */
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		return true;
	}

}
