package sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.DataStore;

/**
 * @author Cameron A. Craig
 * @since 17th April 2014
 * 
 */
public class Item {
	
	/**
	 * 
	 */
	String name;
	/**
	 * 
	 */
	String description;
	/**
	 * 
	 */
	BufferedImage image;
	/**
	 * 
	 */
	int value;
	/**
	 * 
	 */
	private int xPosition,yPosition;
	/**
	 * 
	 */
	private int width,height;
	/**
	 * 
	 */
	private int xCoordinate,yCoordinate;
	/**
	 * 
	 */
	private ItemType itemType;
	/**
	 * @param name
	 * @param description
	 * @param image
	 * @param itemType
	 * @param value
	 * @param xCoordinate
	 * @param yCoordinate
	 */
	public Item(String name, String description, BufferedImage image, ItemType itemType, int value,int xCoordinate,int yCoordinate) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.itemType = itemType;
		this.value = value;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}



	/**
	 * @param g
	 */
	public void draw(Graphics g) {
		g.drawImage(this.image,xPosition,yPosition, null);
		
	}

	/**
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y) {
		xPosition = x;
		yPosition = y;
	}

	/**
	 * 
	 */
	public void print() {
		StringBuffer sb = new StringBuffer("Item: " + this.name);
		sb.append("\nDescription: " + this.description);
		sb.append("\nPosition: (" + xPosition + "," + yPosition + ")\n");
		System.out.println(sb.toString());
		
	}
}

