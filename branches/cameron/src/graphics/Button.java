package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * @author Cameron A. Craig
 *
 */
@SuppressWarnings("serial")
public class Button extends JButton implements ActionListener{
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
	 * @param height 
	 * @param width 
	 * @param y 
	 * @param x 
	 * 
	 */
	public Button(int x, int y, int width, int height){
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		text = "";
		margin = 10;
		mouseHover = false;
		curveRadius = 10;
		backgroundColour = Color.white;
		foreGroundColour = Color.black;
		setText(text);
		setBackground(backgroundColour);
		setForeground(foreGroundColour);
		setBounds(x, y, width, height);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("ACTION");
		
	}
	
}
