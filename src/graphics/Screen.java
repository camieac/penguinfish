package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import main.DataStore;

/**
 * @author Cameron A. Craig
 * @since 31st March 2014
 *
 */
public class Screen extends JPanel {
	private Color backgroundColor;
	private Color foregroundColor;
	ArrayList<Button> buttons;
	private int width;
	private int height;
	BufferedImage titleImage = DataStore.getInstance().images.getTitleImage(0);
	
	


	/**
	 * @param width
	 * @param height
	 */
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		buttons = new ArrayList<Button>();
		backgroundColor = Color.white;
		setForegroundColor(Color.black);
	}

	
	
	/**
	 * @return The current background colour.
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @param backgroundColor The desired background colour.
	 */
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	
	

	/**
	 * @return The current width of the Screen.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width The desired width of the screen.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return The current height of the screen.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height The desired width of the screen.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Adds a button to the screen.
	 * @param button
	 */
	public void addButton(Button button) {
		buttons.add(button);
		
	}
	/**
	 * Draws the title image and buttons
	 * @param g The Graphics object to draw on.
	 */
	public void draw(Graphics g){
		//Draw Title image
		int imageLength = titleImage.getWidth();
		g.drawImage(titleImage, (width-imageLength)/2, 0, width, height, null);
		//Draw Buttons
		for(Button b:buttons){
			b.draw(g);
		}
		
	}

	public Color getForegroundColor() {
		return foregroundColor;
	}

	public void setForegroundColor(Color foregroundColor) {
		this.foregroundColor = foregroundColor;
	}
}
