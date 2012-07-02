package de.graphioli.gameexplorer;

import de.graphioli.utils.Localization;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * Displays information about the currently selected game.
 * 
 * @author Graphioli
 */
public class GEGameInformation extends JPanel {

	/**
	 * Logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(GEGameInformation.class.getName());

	/**
	 * The width of the screenshot label in pixels.
	 */
	private static final int SCREENSHOT_LABEL_WIDTH = 380;

	/**
	 * The height of the screenshot label in pixels.
	 */
	private static final int SCREENSHOT_LABEL_HEIGHT = 250;

	/**
	 * The width of the description title in pixels.
	 */
	private static final int DESCRIPTION_TITLE_WIDTH = 380;

	/**
	 * The height of the description title in pixels.
	 */
	private static final int DESCRIPTION_TITLE_HEIGHT = 30;

	/**
	 * The width of the description label in pixels.
	 */
	private static final int DESCRIPTION_LABEL_WIDTH = 380;

	/**
	 * The height of the description label in pixels.
	 */
	private static final int DESCRIPTION_LABEL_HEIGHT = 140;

	/**
	 * UID for serializing this object.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Component for displaying the screenshot of the currently selected game.
	 */
	private JLabel screenshotLabel;

	/**
	 * Component for displaying the description of the currently selected game.
	 */
	private JTextArea descriptionLabel;

	/**
	 * Constructs a new instance of GEGameInformation.
	 */
	public GEGameInformation() {

		// Generate screenshot label
		this.generateScreenshotLabel();

		// Generate description title label
		this.generateDescriptionTitleLabel();

		// Generate description label
		this.generateDescriptionLabel();

		LOG.finer("GEGameInformation instantiated.");

	}

	/**
	 * Updates the display of the description about the currently selected game.
	 * 
	 * @param description
	 *            The new description to display
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean setDescription(String description) {

		LOG.finer("GEGameInformation.<em>setDescription([...])</em> called.");

		this.descriptionLabel.setText(description);

		LOG.fine("Display of description updated.");
		return false;

	}

	/**
	 * Updates the display of the screenshot of the currently selected game.
	 * 
	 * @param screenshot
	 *            The new screenshot to display
	 * @return <code>true</code> if the action was performed successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean setScreenshot(BufferedImage screenshot) {

		LOG.finer("GEGameInformation.<em>setScreenshot([...])</em> called.");

		if (screenshot != null) {
			this.screenshotLabel.setIcon(new ImageIcon(screenshot));
		} else {
			// Remove image
			this.screenshotLabel.setIcon(null);
		}

		this.screenshotLabel.revalidate();

		LOG.fine("Display of screenshot updated.");
		return false;

	}

	/**
	 * Generates the screenshot label.
	 */
	private void generateScreenshotLabel() {

		this.screenshotLabel = new JLabel();

		// Style screenshot label
		this.screenshotLabel.setPreferredSize(new Dimension(SCREENSHOT_LABEL_WIDTH, SCREENSHOT_LABEL_HEIGHT));
		this.screenshotLabel.setBackground(Color.WHITE);
		this.screenshotLabel.setOpaque(true);
		this.screenshotLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.screenshotLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// Add default text
		this.screenshotLabel.setText(Localization.getLanguageString("ge_screenshotlabel"));

		// Add screenshot label to parent panel
		this.add(this.screenshotLabel);

	}

	/**
	 * Generates the description title label.
	 */
	private void generateDescriptionTitleLabel() {

		JLabel descriptionTitleLabel = new JLabel();

		// Style description title label
		Font boldFont = new Font(descriptionTitleLabel.getFont().getName(), Font.BOLD, descriptionTitleLabel.getFont()
				.getSize());
		descriptionTitleLabel.setFont(boldFont);
		descriptionTitleLabel.setPreferredSize(new Dimension(DESCRIPTION_TITLE_WIDTH, DESCRIPTION_TITLE_HEIGHT));

		// Add title text
		descriptionTitleLabel.setText(Localization.getLanguageString("ge_description"));

		// Add description title label to parent panel
		this.add(descriptionTitleLabel);

	}

	/**
	 * Generates the description label.
	 */
	private void generateDescriptionLabel() {

		this.descriptionLabel = new JTextArea();

		// Style description label
		this.descriptionLabel.setPreferredSize(new Dimension(DESCRIPTION_LABEL_WIDTH, DESCRIPTION_LABEL_HEIGHT));
		this.descriptionLabel.setWrapStyleWord(true);
		this.descriptionLabel.setLineWrap(true);
		this.descriptionLabel.setEditable(false);
		this.descriptionLabel.setOpaque(false);

		// Add default text
		this.descriptionLabel.setText(Localization.getLanguageString("ge_description_error"));

		// Add description label to parent panel
		this.add(this.descriptionLabel);

	}

}
