package de.graphioli.utils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * This class is used by the {@link GraphioliLogger} to generate HTML formatted log messages.
 * 
 * @author Team Graphioli
 *
 */

public class GraphioliLogFormatter extends Formatter {

	/** {@inheritDoc} */
	@Override
	public String format(LogRecord rec) {

		StringBuffer buf = new StringBuffer(750);

		int level = rec.getLevel().intValue();

		if (level == Level.SEVERE.intValue()) {
			buf.append("<tr BGCOLOR=\"#FF0033\"><td>SEVERE");
		} else if (level == Level.WARNING.intValue()) {
			buf.append("<tr BGCOLOR=\"#FFCC00\"><td>WARNING");
		} else if (level == Level.INFO.intValue()) {
			buf.append("<tr BGCOLOR=\"#66FF66\"><td>INFO");
		} else if (level == Level.CONFIG.intValue()) {
			buf.append("<tr BGCOLOR=\"#00CCFF\"><td>CONFIG");
		} else if (level == Level.FINE.intValue()) {
			buf.append("<tr><td>FINE");
		} else if (level == Level.FINER.intValue()) {
			buf.append("<tr><td>FINER");
		} else if (level == Level.FINEST.intValue()) {
			buf.append("<tr><td>FINEST");
		} else {
			buf.append("<tr BGCOLOR=\"#D0D0D0\"><td>???");
		}

		buf.append("</td>\n");
		buf.append("<td>" + generateTimeStamp() + "</td>\n");
		buf.append("<td>" + rec.getLoggerName() + "</td>\n");
		buf.append("<td>" + formatMessage(rec) + "</td>\n");
		buf.append("</tr>\n\n");

		return buf.toString();
	}

	/** {@inheritDoc} */
	@Override
	public String getHead(Handler h) {
		String headString = "<!DOCTYPE html><html><body><h2>Graphioli Log</h2><p>\n"
				+ "Logging started on "
				+ generateTimeStamp()
				+ ".<br>\n"
				+ "<table border=\"1\"><tr><th>Level</th><th>Time</th><th>Class</th><th>Message</th></tr>";
		return headString;
	}

	/** {@inheritDoc} */
	@Override
	public String getTail(Handler h) {
		String tailString = "</table> </p><p>\n" + "Logging finished on "
				+ generateTimeStamp() + ".\n" + "</p></body></html>";
		return tailString;
	}

	/**
	 * Generates a formatted date string of the time this method was called.
	 * 
	 * @return Formatted date string
	 */
	private static String generateTimeStamp() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd. MMM yyyy HH:mm:ss");
		return df.format(cal.getTime());
	}
};
