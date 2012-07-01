package de.graphioli.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.jar.JarFile;

/**
 * This class can access jar files and get specific files from it.
 * 
 * @author Graphioli
 */
public final class JarParser {

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
	 *            path to the property file
	 * @return the property file as {@link Reader}
	 */
	public static Reader getPropertyFileFromJar(String path) {
		JarFile jarFile;
		InputStream inputStream = null;
		Reader fileReader = null;

		try {
			jarFile = new JarFile("games/" + path + "/" + path + ".jar");
			inputStream = jarFile.getInputStream(jarFile.getJarEntry("properties.json"));
			fileReader = new InputStreamReader(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileReader;
	}
}
