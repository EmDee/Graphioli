package de.graphioli.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.jar.JarFile;
import java.util.logging.Logger;

/**
 * This class can access jar files and get specific files from it.
 * 
 * @author Graphioli
 */
public final class JarParser {

	private static JarFile jarFile;

	/**
	 * Logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(JarParser.class.getName());

	/**
	 * Private constructor to ensure that no instance is created from this
	 * class.
	 */
	private JarParser() {
	}

	/**
	 * Gets the property file within the jar and returns it as a {@link Reader}.
	 * 
	 * @param path
	 *            path to root folder of the game folder, where the property
	 *            file is
	 * @return the property file as {@link Reader}
	 */
	public static Reader getPropertyFileFromJar(String path) {
		LOG.finer("JarParser.<em>getPropertyFileFromJar()</em> called.");

		InputStream inputStream = null;
		Reader fileReader = null;

		try {
			jarFile = new JarFile("games/" + path + "/" + path + ".jar");
			inputStream = jarFile.getInputStream(jarFile.getJarEntry("properties.json"));
			fileReader = new InputStreamReader(inputStream);
		} catch (IOException e) {
			LOG.severe("No InputStream generated");
		}

		return fileReader;
	}

	/**
	 * Gets an {@link InputStream} of the screenshot of a specific game.
	 * 
	 * @param path
	 *            path to root folder of the game folder, where the screenshot
	 *            is
	 * @return {@link InputStream} of the screenshot
	 */
	public static InputStream getImageURL(String path) {
		LOG.finer("JarParser.<em>getImageURL()</em> called.");

		InputStream imageURL = null;
		try {
			jarFile = new JarFile("games/" + path + "/" + path + ".jar");
			imageURL = jarFile.getInputStream(jarFile.getJarEntry("screenshot.jpg"));
		} catch (IOException e) {
			LOG.severe("Path does not exist: '" + imageURL + "'.");
			return null;
		}

		return imageURL;
	}
}
