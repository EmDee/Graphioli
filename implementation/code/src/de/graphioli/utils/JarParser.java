package de.graphioli.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
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
		} catch (NullPointerException e) {
			LOG.severe("File does not exist at: '" + inputStream + "'.");
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

	/**
	 * Returns the game's class file within the jar.
	 * 
	 * @param gamePackagePath
	 *            the fully qualified class name
	 * @param gameName
	 *            name of the game
	 * @return the game's class file
	 */
	public static Class<?> getClass(String gamePackagePath, String gameName) {
		Class<?> classToLoad = null;
		URL jarURL = null;

		File myFile = new File("games/" + gameName + "/" + gameName + ".jar");
		try {
			jarURL = new URL("jar", "", "file:" + myFile.getAbsolutePath() + "!/");
		} catch (MalformedURLException e) {
			LOG.severe("MalformedURLException: " + e.getMessage());
			return null;
		}
		URL[] classes = new URL[] { jarURL };

		URLClassLoader classLoader = new URLClassLoader(classes);
		try {
			classToLoad = Class.forName(gamePackagePath + gameName, true, classLoader);
		} catch (ClassNotFoundException e) {
			LOG.severe("MalformedURLException: " + e.getMessage());
			return null;
		}

		return classToLoad;
	}

	// TODO: Remove this method for production
	public static Class<?> getClassFromBin(String gameName) {
		File file = new File("bin/games");
		Class<?> cls = null;

		try {
			URL url = file.toURI().toURL();
			URL[] urls = new URL[] { url };

			ClassLoader cl = new URLClassLoader(urls);
			cls = cl.loadClass("game." + gameName);

		} catch (MalformedURLException e) {
		} catch (ClassNotFoundException e) {
		}
		return cls;
	}
}
