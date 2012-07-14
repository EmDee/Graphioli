/**
 * 
 */
package de.graphioli.view;
import de.graphioli.model.MenuItem;

import javax.swing.JMenuItem;



/**
 * This class implements the {@link MenuItem} as a Swing {@link JMenuItem}.
 * 
 * @author Team Graphioli
 *
 */
public class OptionsMenuItem extends JMenuItem {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2319566418319082586L;

	/**
	 * The menu item represented by this instance.
	 */
	private final MenuItem customItem;

	
	
	/**
	 * Creates a OptionsMenuItem from the given MenuItem.
	 * 
	 * @param item
	 *            the menu item.
	 */
	public OptionsMenuItem(MenuItem item) {
		super(item.getName());
		this.customItem = item;
	}

	/**
	 * Returns the menu item.
	 *
	 * @return the menu item
	 */
	MenuItem getCustomItem() {
		return this.customItem;
	}	
	
	
	
}
