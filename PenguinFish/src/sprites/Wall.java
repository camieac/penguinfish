package sprites;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Cameron A. Craig
 *
 */
public class Wall extends SessileSprite {
	
	private Color colour;
/**
 * @param x
 * @param y
 * @param name
 * @param width
 * @param height
 */
public Wall(int x, int y, String name, int width, int height,Color colour){
	super(x, y, name);
	this.setRect(x, y, width, height);
	this.colour = colour;
}
/**
 * @param g
 * @param camX 
 * @param camY 
 */
public void draw(Graphics g,double camX,double camY){
	Color oldColour = g.getColor();
	g.setColor(colour);
	//g.drawRect((int)(x - camX),(int)( y - camY), width, height);
	g.fillRect((int)(x - camX),(int)( y - camY), width, height);
	g.setColor(oldColour);
}
}
