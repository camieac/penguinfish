package sprites;

import java.awt.Graphics;
import java.awt.Rectangle;
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
	private ItemType itemType;
	
	/**
	 * 
	 */
	private boolean picked;
	/**
	 * @param name
	 * @param description
	 * @param image
	 * @param itemType
	 * @param value
	 * @param xPosition 
	 * @param yPosition 
	 */
	public Item(String name, String description, BufferedImage image, ItemType itemType, int value,int xPosition,int yPosition) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.itemType = itemType;
		this.value = value;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.picked = false;
	}



	/**
	 * @param g
	 * @param camY 
	 * @param camX 
	 */
	public void draw(Graphics g, double camX, double camY) {
		if(picked){
			g.drawImage(this.image,xPosition,yPosition, null);
		}else{
			g.drawImage(this.image,(int)(xPosition - camX),(int)(yPosition - camY), null);
		}
		
		
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



	/**
	 * @return Has the item been picked?
	 */
	public boolean isPicked() {
		return picked;
	}
	/**
	 * @param picked
	 */
	public void setPicked(boolean picked){
		this.picked = picked;
		DataStore.getInstance().player.getInventory().addItem(this);
	}



	/**
	 * @return The rectangle object that surrounds the item.
	 */
	public Rectangle getBounds() {
		Rectangle boundingRectangle = new Rectangle(xPosition,yPosition,64,64);
		return boundingRectangle;
	}
}

