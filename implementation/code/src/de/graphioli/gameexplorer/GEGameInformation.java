package de.graphioli.gameexplorer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
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
	 * Logging instance
	 */
	private static final Logger LOG = Logger.getLogger(GEWindow.class.getName());

	/**
	 * UID for serializing this object.
	 */
	private static final long serialVersionUID = 1L;

	private JLabel screenshotLabel;
	private JTextArea descriptionLabel;


	/**
	 * Constructs a new instance of GEGameInformation.
	 */
	public GEGameInformation() {

		// Style panel
		//this.setLayout();

		// Create screenshot label
		this.screenshotLabel = new JLabel();
		this.screenshotLabel.setPreferredSize(new Dimension(380, 250));
		this.screenshotLabel.setBackground(Color.WHITE);
		this.screenshotLabel.setOpaque(true);
		this.screenshotLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.screenshotLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.screenshotLabel.setText("No screenshot provided.");

		// Create description title panel
		JLabel descriptionTitleLabel = new JLabel();
		descriptionTitleLabel.setText("Description");
		Font boldFont = new Font(descriptionTitleLabel.getFont().getName(),
				Font.BOLD,
				descriptionTitleLabel.getFont().getSize());  
		descriptionTitleLabel.setFont(boldFont);
		descriptionTitleLabel.setPreferredSize(new Dimension(380, 30));

		// Create description panel
		this.descriptionLabel = new JTextArea();
		this.descriptionLabel.setText("No description provided.");
		this.descriptionLabel.setPreferredSize(new Dimension(380, 140));
		this.descriptionLabel.setWrapStyleWord(true);
		this.descriptionLabel.setLineWrap(true);
		this.descriptionLabel.setEditable(false);
		this.descriptionLabel.setOpaque(false);

		// Add components to information panel
		this.add(this.screenshotLabel);
		this.add(descriptionTitleLabel);
		this.add(this.descriptionLabel);

	}


	/**
	 * Updates the display of the description about the currently selected game.
	 * 
	 * @param description The new description to display
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
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
	 * @param screenshot The new screenshot to display
	 * @return <code>true</code> if the action was performed successfully, <code>false</code> otherwise
	 */
	public boolean setScreenshot(BufferedImage screenshot) {

		LOG.finer("GEGameInformation.<em>setScreenshot([...])</em> called.");

		// TODO implement

		LOG.fine("Display of screenshot updated.");
		return false;

	}

}
