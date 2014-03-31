package graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

/**
 * @author Cameron A. Craig
 *
 */
@SuppressWarnings("serial")
public class Button{
	int width;
	int height;
	int x;
	int y;
	Color backgroundColour;
	Color foreGroundColour;
	int curveRadius;
	String text;
	int margin;
	boolean mouseHover;
	
	/**
	 * 
	 */
	public Button(){
		
	}
	
	/**
	 * @param g
	 */
	public void draw(Graphics g){
		Color oldColour = g.getColor();
		g.setColor(foreGroundColour);
		g.drawString(text,x+margin,y+margin);
		g.drawRoundRect(x, y, width+ (margin*2), height+(margin*2), curveRadius, curveRadius);
		g.setColor(backgroundColour);
		g.fillRoundRect(x, y, width+ (margin*2), height+ (margin*2), curveRadius, curveRadius);
		g.setColor(oldColour);
	}
	/**
	 * 
	 */
	public void mouseHover(){
		mouseHover = true;
	}
	/**
	 * 
	 */
	public void noMouseHover(){
		mouseHover = false;
	}
	
}
