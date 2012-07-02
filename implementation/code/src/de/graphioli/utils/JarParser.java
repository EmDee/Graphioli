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
	 * Gets a file in a game's jar as {@link InputStream}.
	 * 
	 * @param gameName
	 *            name of the game to get the file from
	 * @param fileName
	 *            the file to get
	 * @return an {@link InputStream} of the file
	 */
	public static InputStream getFileAsInputStream(String gameName, String fileName) {
		LOG.finer("JarParser.<em>getImageURL()</em> called.");

		InputStream inputStream = null;
		try {
			jarFile = new JarFile("games/" + gameName + "/" + gameName + ".jar");
			inputStream = jarFile.getInputStream(jarFile.getJarEntry(fileName));
		} catch (IOException e) {
			LOG.severe("Path does not exist: '" + inputStream + "'.");
			return null;
		}

		return inputStream;
	}

	/**
	 * Returns the property file of a given game as {@link Reader}.
	 * 
	 * @param gameName
	 *            name of the game to get the property file from
	 * @return the property file as {@link Reader}
	 */
	public static Reader getPropertyFile(String gameName) {
		LOG.finer("JarParser.<em>getPropertyFileFromJar()</em> called.");

		InputStream inputStream = getFileAsInputStream(gameName, "properties.json");
		Reader fileReader = new InputStreamReader(inputStream);

		return fileReader;
	}
}
