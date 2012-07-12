package de.graphioli.model;

import de.graphioli.utils.InvalidJarException;
import de.graphioli.utils.JarParser;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.logging.Logger;

import javax.imageio.ImageIO;



/**
 * This class provides the game with access to the resources in its Jar-File.
 * 
 * @author Team Graphioli
 *
 */
public class GameResources {

	/**
	 * Logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(GameResources.class.getName());

	/**
	 * The name of the game this resources belong to.
	 */
	private final String gameName;

	/**
	 * The internal resource bundle for string resources.
	 */
	private final PropertyResourceBundle resBundle;

	/**
	 * Creates the {@link GameResources} for the gaem of the given name.
	 * 
	 * @param gameName the game's name.
	 */
	public GameResources(String gameName) {
		LOG.fine("Creating GameResources.");
		this.gameName = gameName;
		this.resBundle = this.loadResourceBundle();
	}

	/**
	 * Used to locate the resource bundle for the strings based on the systems default locale.
	 * 
	 * @return the internal resource bundle for string resources.
	 */
	private PropertyResourceBundle loadResourceBundle() {
		Locale defLoc = Locale.getDefault();
		InputStream fileIn;
		try {
			fileIn = JarParser.getFileAsInputStream(this.gameName, "lang_"
					+ defLoc.getLanguage()
					+ "_"
					+ defLoc.getCountry()
					+ ".properties");
		} catch (InvalidJarException e1) {
			LOG.finer("Could not load "
					+ "lang_"
					+ defLoc.getLanguage()
					+ "_"
					+ defLoc.getCountry()
					+ ".properties. Trying to load default.");
			try {
				fileIn = JarParser.getFileAsInputStream(this.gameName, "lang.properties");
			} catch (InvalidJarException e) {
				LOG.fine("No game language file found. Using fallback.");
				return null;
			}
		}

		PropertyResourceBundle returnBundle = null;

		try {
			returnBundle = new PropertyResourceBundle(fileIn);
		} catch (IOException e) {
			LOG.warning("Could not access game language file (IO Exception)!");
			returnBundle = null;
		}

		LOG.fine("Game language file loaded.");
		return returnBundle;
	}

	/**
	 * Returns a string resource from this game's language file, selected by the systems default language.
	 * If the resource for the given key cannot be found the fallback string is returned.
	 * 
	 * @param key the string resource's key.
	 * @param fallback the fallback string.
	 * @return the localized string or the fallback string.
	 */
	public String getStringResource(String key, String fallback) {
		if (this.resBundle == null || key == null) {
			return fallback;
		}

		String stringRes;

		try {
			stringRes = this.resBundle.getString(key);
		} catch (MissingResourceException mre) {
			stringRes = null;
			LOG.warning("String resource " + key + " not found.");
		} catch (ClassCastException cce) {
			stringRes = null;
			LOG.warning("Resource " + key + "is not a string.");
		}
		
		if (stringRes == null) {
			return fallback;
		} else {
			LOG.finer("Got language string for " + key + ".");
			return stringRes;
		}
	}

	/**
	 * Returns a string resource from this game's language file, selected by the systems default language.
	 * If the resource for the given key cannot be found the string "RES: {@literal <key>}" is returned.
	 * 
	 * @param key the string resource's key.
	 * @return the localized string.
	 */
	public String getStringResource(String key) {
		return this.getStringResource(key, "RES: " + key);
	}

	/**
	 * Returns the name of the game this resources belong to.
	 * @return the name of the game this resources belong to.
	 */
	public String getGameName() {
		return this.gameName;
	}

	/**
	 * Returns the image of the given file name in the Jar's "img" folder.
	 * 
	 * @param filename the image's filename;
	 * @return the image.
	 */
	public BufferedImage getImageRessource(String filename) {
		InputStream fis;
		try {
			fis = JarParser.getFileAsInputStream(this.gameName, "img/" + filename);
		} catch (InvalidJarException e1) {
			LOG.warning("ImageResource  img/" + filename + " not found.");
			return null;
		}
		BufferedImage img = null;
		try {
			img = ImageIO.read(fis);
		} catch (IOException e) {
			LOG.warning("Could not create ImageResource from " + filename + ".");
			return null;
		}
		
		return img;
	}

}
