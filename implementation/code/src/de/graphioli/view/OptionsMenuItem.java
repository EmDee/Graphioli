/**
 * 
 */
package de.graphioli.view;

import javax.swing.JMenuItem;

import de.graphioli.model.MenuItem;

/**
 * This class implements the {@link MenuItem} as a Swing {@link JMenuItem}
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
	 * Creates a OptionsMenuItem from the given MenuItem.
	 * @param caption
	 */
	public OptionsMenuItem(MenuItem item) {
		super(item.getName());
		this.customItem = item;
	}

	
	
	/**
	 * The menu item represented by this instance.
	 */
	private final MenuItem customItem;

	/**
	 * Returns the menu item.
	 *
	 * @return the menu item
	 */
	MenuItem getCustomItem() {
		return customItem;
	}	
	
	
	
}
