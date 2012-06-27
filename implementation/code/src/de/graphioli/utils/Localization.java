package de.graphioli.utils;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 
 * This class is responsible for loading the correct localization file.
 * 
 * @author Graphioli
 * 
 */
public final class Localization {
	// TODO: load localization from game definition
	/**
	 * Private constructor to ensure that no instance of this class is created.
	 */
	private Localization() {
	}

	/**
	 * Gets the respective string to a given key value.
	 * 
	 * @param key
	 *            given key
	 * @return the string that corresponds to the key
	 */
	public static String getLanguageString(String key) {

		//
		// Load resource bundle for Locale.UK locale. The resource
		// bundle will load the MessagesBundle_en_GB.properties file.
		//
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("lang", new Locale("de", "DE"));
			return bundle.getString(key);
		} catch (MissingResourceException mre) {
			System.out.println("Can't find locale file");
		}

		return "";
	}
}
