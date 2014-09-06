package graphics;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.text.BreakIterator;
import java.util.Locale;
import java.util.StringTokenizer;

import main.DataStore;

/**
 * A small information box that appears at points in the game to give useful
 * information.
 * 
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * @since September 2014
 * 
 */
public class Notification {
	/**
	 * The time that the notification is displayed for, in milliseconds.
	 */
	long durationOfAppearance;

	/**
	 * The height of the font in the notification bar.
	 */
	int fontHeight;

	/**
	 * An array of strings, each string is a line in the notification text box.
	 */
	String[] lines;

	/**
	 * The gap between the edge of the notification box and the start of the text. It applies horizontally and vertically.
	 */
	int margin;

	/**
	 * The maximum permissible height of the notification box.
	 */
	int maxCaptionHeight;
	/**
	 * The maximum permissible width of the notification box.
	 */
	int maxCaptionWidth;
	/**
	 * The maximum number of lines that can be displayed in the notification box.
	 */
	int maxNumberOfLines;
	/**
	 * A string containing all the text to be displayed in the notification box.
	 */
	String text;
	/**
	 * The text colour.
	 */
	Color textColour, backColour;
	/**
	 * The width and height of the box that the text fills, within the notification box.
	 */
	int textWidth, textHeight;
	/**
	 * The time relative to the start of the level that the notification should begin appearing at. In milliseconds.
	 */
	long timeOfAppearance;

	/**
	 * Whether or not the notification is currently visible.
	 */
	private boolean visible;
	/**
	 * The offset values relative to the player's x and y coordinates.
	 */
	int xOffset, yOffset;
	/**
	 * The position of the top left corner of the notification box.
	 */
	int xPosition, yPosition;

	/**
	 * @param text
	 *            The text to be displayed in the notification box.
	 * @param textColor
	 *            The colour of the text in the notification box, also used for
	 *            the box outline.
	 * @param backColor
	 *            The colour of the background of the box.
	 */
	public Notification(String text, Color textColor, Color backColor) {
		this.text = text;
		maxCaptionWidth = 250;
		maxCaptionHeight = 250;
		margin = 10;

		textWidth = 0;
		textHeight = 0;

		this.textColour = textColor;
		this.backColour = backColor;

		xOffset = 40;
		yOffset = 10;
		fontHeight = 0;
		lines = wrapStringToArray(text);
		maxNumberOfLines = 5;
		visible = false;

		xPosition = 500;
		yPosition = 500;

	}

	/**
	 * @param g The {@link #java.awt.Graphics} object that the camera draws to.
	 */
	public void displayNotification(Graphics g) {
		setFontHeight(g);

		Rectangle captionRect = stringToCaptionRectangle(g, text);

		Color oldColour = g.getColor();
		g.setColor(backColour);
		g.fillRoundRect(xPosition, yPosition, captionRect.width,
				captionRect.height, 10, 10);
		g.setColor(textColour);
		g.drawRoundRect(xPosition, yPosition, captionRect.width,
				captionRect.height, 10, 10);

		// lines.
		int i = 0;
		for (String s : lines) {
			g.drawString(s, xPosition + margin, (yPosition + (fontHeight * i))
					+ margin + 10);
			i++;
		}
		g.setColor(oldColour);

	}

	/**
	 * @param g
	 *            The {@link #java.awt.Graphics} object that the camera draws to.
	 * @param x
	 *            x-position of the player, or relative object
	 * @param y
	 *            y-position of the player, or relative object.
	 */
	public void displayPlayerText(Graphics g, int x, int y) {
		x = x + xOffset;
		y = y + yOffset;

		setFontHeight(g);

		Rectangle captionRect = stringToCaptionRectangle(g, text);

		Color oldColour = g.getColor();
		g.setColor(backColour);
		g.fillRoundRect(x, y, captionRect.width, captionRect.height, 10, 10);
		g.setColor(textColour);
		g.drawRoundRect(x, y, captionRect.width, captionRect.height, 10, 10);

		// lines.
		int i = 0;
		for (String s : lines) {
			g.drawString(s, x + margin, (y + (fontHeight * i)) + margin + 10);
			i++;
		}
		g.setColor(oldColour);

	}

	/**
	 * @return The duration that the notification bar appears for. In milliseconds.
	 */
	public long getDurationOfAppearance() {
		return durationOfAppearance;
	}

	/**
	 * @return The time that the notification starts to appear at, relative to the start of the level.
	 */
	public long getTimeOfAppearance() {
		return timeOfAppearance;
	}

	/**
	 * @return Whether the notification should be visible or not.
	 */
	public boolean isVisible() {

		return visible;
	}

	/**
	 * @param durationOfAppearance The time period that the notification appears for, in milliseconds.
	 */
	public void setDurationOfAppearance(long durationOfAppearance) {
		this.durationOfAppearance = durationOfAppearance;
	}

	/**
	 * @param g The {@link #java.awt.Graphics} object that the camera draws to.
	 */
	private void setFontHeight(Graphics g) {
		fontHeight = g.getFontMetrics().getHeight();
	}

	/**
	 * @param xPosition The x position of the notification. Most left point.
	 * @param yPosition The y position of the notification. Upper point.
	 */
	public void setPosition(int xPosition, int yPosition) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;

	}

	/**
	 * @param timeOfAppearance The time that the appearance will start to appear at, relative to the start of the level.
	 */
	public void setTimeOfAppearance(long timeOfAppearance) {
		this.timeOfAppearance = timeOfAppearance;
	}

	/** 
	 * Set whether or not the notification should be visible.
	 * @param b The visibility represented by at true or false value.
	 */
	public void setVisible(boolean b) {
		visible = b;

	}

	/**
	 * Convert a string of text into a rectangle the same size as the string of text if it were to be displayed on screen in a notification area.s
	 * @param g The {@link #java.awt.Graphics} object that the camera draws to.
	 * @param text The te be used.
	 * @return A {@link java.awt.Rectangle} that is the same width and height as the text if it were to be displayed on screen.
	 */
	private Rectangle stringToCaptionRectangle(Graphics g, String text) {
		textWidth = stringToLength(g, text);
		if (textWidth > maxCaptionWidth)
			textWidth = maxCaptionWidth;

		textHeight = (lines.length) * fontHeight;

		return new Rectangle(textWidth + margin, textHeight + margin);

	}

	/**
	 * Converts a string into a length that is equal to the length in pixels of the string if it were to be printed in a notification box.
	 * @param g The {@link #java.awt.Graphics} object that the camera draws to.
	 * @param text The text to be converted to a length.
	 * @return The length in pixels of the text if it were to be displayed on screen in a notification area.
	 */
	private int stringToLength(Graphics g, String text) {
		FontMetrics fm = g.getFontMetrics();
		int length = 0;
		for (Character c : text.toCharArray()) {
			length += fm.charWidth(c);
		}
		return length;
	}

	/**
	 * Every tick, this method is triggered by the game class. Checks if the
	 * notification should be displayed.
	 */
	public void tick() {
		if (timeToDisplay() && !timeToDisappear()) {
			// Display notification
			visible = true;
		} else {
			// Hide notication
			visible = false;
		}
	}

	private boolean timeToDisappear() {
		return DataStore.getInstance().currentLevelTime - timeOfAppearance >= durationOfAppearance;
	}

	private boolean timeToDisplay() {
		return DataStore.getInstance().currentLevelTime >= timeOfAppearance;
	}

	/**
	 * @param text
	 *            The text to wrap into an array of lines.
	 * @return An array of strings, each element of the array represents a line.
	 */
	public String[] wrapStringToArray(String text) {
		BreakIterator breakIterator = BreakIterator.getSentenceInstance(Locale
				.getDefault());
		if (text.length() == 0) {
			return new String[] { text };
		}
		String[] workingSet;
		StringTokenizer tokens = new StringTokenizer(text, "\n"); // NOI18N
		int len = tokens.countTokens();
		workingSet = new String[len];

		for (int i = 0; i < len; i++) {
			workingSet[i] = tokens.nextToken();
		}

		if (text.length() <= maxNumberOfLines) {
			return workingSet;
		}

		widthcheck: {
			boolean ok = true;

			for (int i = 0; i < workingSet.length; i++) {
				ok = ok && (workingSet[i].length() < maxNumberOfLines);

				if (!ok) {
					break widthcheck;
				}
			}

			return workingSet;
		}

		java.util.ArrayList<String> lines = new java.util.ArrayList<String>();

		int lineStart = 0;

		for (int i = 0; i < workingSet.length; i++) {
			if (workingSet[i].length() < maxNumberOfLines) {
				lines.add(workingSet[i]);
			} else {
				breakIterator.setText(workingSet[i]);

				int nextStart = breakIterator.next();
				int prevStart = 0;

				do {
					while (((nextStart - lineStart) < maxNumberOfLines)
							&& (nextStart != BreakIterator.DONE)) {
						prevStart = nextStart;
						nextStart = breakIterator.next();
					}

					if (nextStart == BreakIterator.DONE) {
						nextStart = prevStart = workingSet[i].length();
					}

					if (prevStart == 0) {
						prevStart = nextStart;
					}

					lines.add(workingSet[i].substring(lineStart, prevStart));

					lineStart = prevStart;
					prevStart = 0;
				} while (lineStart < workingSet[i].length());

				lineStart = 0;
			}
		}

		String[] s = new String[lines.size()];

		return lines.toArray(s);
	}
}
