package de.graphioli.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is used to create a HTML formatted log file for the Graphioli
 * framework.
 * 
 * @author Team Graphioli
 */
public final class GraphioliLogger {

	/**
	 * Path to logging file.
	 */
	private static final String FILENAME = "log.html";

	private GraphioliLogger() {
	}

	/**
	 * Creates the log file and registers this logger.
	 * 
	 * @param logLevel
	 *            The initial logging level
	 * @throws IOException
	 *             when unable to write to the log file
	 */
	public static void startLog(Level logLevel) throws IOException {

		Logger log = Logger.getLogger("de.graphioli");
		log.setLevel(logLevel);

		FileHandler logFile = new FileHandler(FILENAME);
		Formatter formatter = new GraphioliLogFormatter();
		logFile.setFormatter(formatter);
		log.addHandler(logFile);

	}

}
