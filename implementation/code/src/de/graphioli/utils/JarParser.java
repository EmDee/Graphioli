package de.graphioli.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;
import java.util.logging.Logger;

/**
 * This class can access jar files and get specific files from it.
 * 
 * @author Team Graphioli
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
	 * @throws InvalidJarException
	 *             if specified JAR file does not exist
	 */
	public static InputStream getFileAsInputStream(String gameName, String fileName) throws InvalidJarException {
		LOG.finer("JarParser.<em>getFileAsInputStream()</em> called.");

		InputStream inputStream = null;
		try {
			jarFile = new JarFile("games/" + gameName + "/" + gameName + ".jar");
			inputStream = jarFile.getInputStream(jarFile.getJarEntry(fileName));
		} catch (IOException e) {
			String errorMsg = "Could not access file \"" + fileName + "\" in \"" + gameName + ".jar\" (IO Exception).";
			LOG.warning(errorMsg);
			throw new InvalidJarException(errorMsg);
		} catch (NullPointerException e) {
			String errorMsg = "Could not access file \""
					+ fileName
					+ "\" in \""
					+ gameName
					+ ".jar\" (NullPointerException). (Does file exist?)";
			LOG.fine(errorMsg);
			throw new InvalidJarException(errorMsg);
		}

		return inputStream;
	}

	/**
	 * Returns the property file of a given game as {@link Reader}.
	 * 
	 * @param gameName
	 *            name of the game to get the property file from
	 * @return the property file as {@link Reader}
	 * @throws InvalidJarException
	 *             if specified property file or parent JAR file doesn't exist
	 */
	public static Reader getPropertyFile(String gameName) throws InvalidJarException {
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
	 * @param parentClassLoader
	 *            the class loader of the {@link Game} class.
	 * @return the game's class file
	 * @throws InvalidJarException
	 *             if the specified JAR file doesn't exist
	 */
	public static Class<?> getClass(String gamePackagePath, String gameName, ClassLoader parentClassLoader)
			throws InvalidJarException {
		Class<?> classToLoad = null;
		URL jarURL = null;

		File myFile = new File("games/" + gameName + "/" + gameName + ".jar");
		try {
			jarURL = new URL("jar", "", "file:" + myFile.getAbsolutePath() + "!/");
		} catch (MalformedURLException e) {
			String errorMsg = "Could not get class file from \"" + gameName + ".jar\" (MalformedURLException).";
			LOG.severe(errorMsg);
			throw new InvalidJarException(errorMsg);
		}
		URL[] classes = new URL[] { jarURL };

		URLClassLoader classLoader = new URLClassLoader(classes, parentClassLoader);
		try {
			classToLoad = Class.forName(gamePackagePath + gameName, true, classLoader);
		} catch (ClassNotFoundException e) {
			String errorMsg = "Could not load class file from \"" + gameName + ".jar\" (MalformedURLException).";
			LOG.severe(errorMsg);
			throw new InvalidJarException(errorMsg);
		}

		return classToLoad;
	}

	/**
	 * Gets the help file within the jar, parses it to a {@link String} and
	 * convert it to a {@link URI}.
	 * 
	 * @param gameName
	 *            name of the game
	 * @return the help file as {@link URI}
	 */
	public static URI getHelpFileURI(String gameName) {
		URI helpFileURI = null;
		String helpString;

		try {
			File tmpHelpFile = File.createTempFile("tmpHelp", ".htm");
			InputStream is = getFileAsInputStream(gameName, "help.html");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			BufferedWriter bw = new BufferedWriter(new FileWriter(tmpHelpFile));

			while ((helpString = br.readLine()) != null) {
				bw.write(helpString);
				bw.newLine();
			}

			br.close();
			bw.close();

			helpFileURI = tmpHelpFile.toURI();
			tmpHelpFile.deleteOnExit();
		} catch (InvalidJarException e) {
			String errorMsg = "Could not load help file from \"" + gameName + ".jar\" (InvalidJarException).";
			LOG.severe(errorMsg);
		} catch (IOException e) {
			String errorMsg = "Could not get text from help file (IOException).";
			LOG.severe(errorMsg);
		}

		return helpFileURI;
	}
}
