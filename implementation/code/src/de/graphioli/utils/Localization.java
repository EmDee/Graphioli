package de.graphioli.utils;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Localization {

	public static String getLanguageString(String key) {

		//
		// Load resource bundle for Locale.UK locale. The resource
		// bundle will load the MessagesBundle_en_GB.properties file.
		//
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("lang", new Locale("en", "US"));
			return bundle.getString(key);
		} catch (MissingResourceException mre) {
			System.out.println("Can't find locale file");
		}

		return "";
	}
}
