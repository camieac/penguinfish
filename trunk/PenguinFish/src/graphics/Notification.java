package graphics;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.text.BreakIterator;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * A small information box that appears at points in the game to give useful
 * information.
 * 
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * 
 */
public class Notification {
	int fontHeight;
	String[] lines;
	int margin;
	int maxCaptionHeight;
	int maxCaptionWidth;

	int maxLength;
	int numberOfRows;
	String text;

	Color textColour, backColour;
	int textWidth, textHeight;

	int xOffset, yOffset;

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

		numberOfRows = 0;
		fontHeight = 0;
		lines = wrapStringToArray(text);
		maxLength = 5;

	}

	/**
	 * @param g
	 *            The graphics object to draw on.
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

	private void setFontHeight(Graphics g) {
		fontHeight = g.getFontMetrics().getHeight();
	}

	private Rectangle stringToCaptionRectangle(Graphics g, String text) {
		textWidth = stringToLength(g, text);
		if (textWidth > maxCaptionWidth)
			textWidth = maxCaptionWidth;

		textHeight = (lines.length) * fontHeight;

		return new Rectangle(textWidth + margin, textHeight + margin);

	}

	private int stringToLength(Graphics g, String text) {
		FontMetrics fm = g.getFontMetrics();
		int length = 0;
		for (Character c : text.toCharArray()) {
			length += fm.charWidth(c);
		}
		return length;
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

		if (text.length() <= maxLength) {
			return workingSet;
		}

		widthcheck: {
			boolean ok = true;

			for (int i = 0; i < workingSet.length; i++) {
				ok = ok && (workingSet[i].length() < maxLength);

				if (!ok) {
					break widthcheck;
				}
			}

			return workingSet;
		}

		java.util.ArrayList<String> lines = new java.util.ArrayList<String>();

		int lineStart = 0;

		for (int i = 0; i < workingSet.length; i++) {
			if (workingSet[i].length() < maxLength) {
				lines.add(workingSet[i]);
			} else {
				breakIterator.setText(workingSet[i]);

				int nextStart = breakIterator.next();
				int prevStart = 0;

				do {
					while (((nextStart - lineStart) < maxLength)
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
