package graphics;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.text.BreakIterator;
import java.util.Locale;
import java.util.StringTokenizer;

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

// private static String trimString(String s) {
// int idx = 0;
// char c;
// final int slen = s.length();
//
// if (slen == 0) {
// return s;
// }
//
// do {
// c = s.charAt(idx++);
// } while (((c == '\n') || (c == '\r')) && (idx < slen));
//
// s = s.substring(--idx);
// idx = s.length() - 1;
//
// if (idx < 0) {
// return s;
// }
//
// do {
// c = s.charAt(idx--);
// } while (((c == '\n') || (c == '\r')) && (idx >= 0));
//
// return s.substring(0, idx + 2);
// }
// }

// static public void main(String[] args) {
//
// // characterExamples();
// // System.out.println();
// // wordExamples();
// // System.out.println();
// // sentenceExamples();
// // System.out.println();
// lineExamples("Nearly all programs with user interfaces manipulate text. In an international market the text your programs display must conform to the rules of languages from around the world. The Java programming language provides a number of classes that help you handle text in a locale-independent manner.");
// }
// }
//private String[] stringToLineArray(String target) {
	// String[] lines = new String[50];
	// // lines = text.split("(?<=\\G.{25})");
	//
	// BreakIterator boundary = BreakIterator.getLineInstance(Locale
	// .getDefault());
	// boundary.setText(target);
	// int start = boundary.first();
	// int end = boundary.next();
	// int lineLength = 0;
	// int i = 0;
	// StringBuffer sb = new StringBuffer();
	// while (end != BreakIterator.DONE) {
	// String word = target.substring(start, end);
	// lineLength = lineLength + word.length();
	// if (lineLength >= maxLength) {
	// // System.out.println();
	// i++;
	// sb = new StringBuffer();
	// lineLength = word.length();
	// }
	// System.out.print(word);
	// sb.append("" + word);
	// lines[i] = sb.toString();
	// start = end;
	// end = boundary.next();
	// }
	//
	// return lines;
	// }

	// static ArrayList<String> formatLines(String target, int maxLength,
	// Locale currentLocale) {
	// ArrayList<String> linesArray = new ArrayList<String>();
	// linesArray.add(0, "");
	// // String[] lines = new String[100];
	// // lines[0] = "";
	// // StringBuffer sb = new StringBuffer();
	// BreakIterator boundary = BreakIterator.getLineInstance(currentLocale);
	// boundary.setText(target);
	// int start = boundary.first();
	// int end = boundary.next();
	// int lineLength = 0;
	// int i = 0;
	// while (end != BreakIterator.DONE) {
	// String word = target.substring(start, end);
	// lineLength = lineLength + word.length();
	// if (lineLength >= maxLength) {
	// System.out.println();
	// i++;
	// lineLength = word.length();
	// }
	// System.out.print(word);
	// // String old = lines[i];
	// String o = linesArray.get(i);
	// // lines[i] = old + word;
	// linesArray.add(i, o + word);
	// o = null;
	// start = end;
	// end = boundary.next();
	// }
	// System.out.println(linesArray.get(0));
	// System.out.println(linesArray.get(1));
	// System.out.println(linesArray.get(2));
	// System.out.println(linesArray.get(3));
	// System.out.println(linesArray.get(4));
	// System.out.println(linesArray.get(5));
	// return linesArray;
	// }

	// static void lineExamples(String text) {
	//
	// Locale currentLocale = new Locale("en", "US");
	// BreakIterator lineIterator = BreakIterator
	// .getLineInstance(currentLocale);
	//
	// formatLines(text, 30, currentLocale);
	//
	// }
