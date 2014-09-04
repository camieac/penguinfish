package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * @author Cameron A. Craig
 * @since 17th April 2014
 * 
 */
public enum Item {
	/**
	 * A sword, can be used in combat.
	 */
	SWORD("Sword", "This is a sword",
			DataStore.getInstance().images.getSword(), 100),
	/**
	 * A dagger, can be used in combat.
	 */
	DAGGER("Dagger","This is a dagger",
			DataStore.getInstance().images.getDagger(), 50);
	String name;
	String description;
	BufferedImage image;
	int value;
	private int xPosition,yPosition;
	private int width,height;
	Item(String name, String description, BufferedImage image, int value) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.value = value;
	}

	public void draw(Graphics g) {
		g.drawImage(this.image,xPosition,yPosition, null);
		
	}

	public void setPosition(int x, int y) {
		xPosition = x;
		yPosition = y;
	}

	/**
	 * 
	 */
	public void print() {
		StringBuffer sb = new StringBuffer("Item: " + this.name());
		sb.append("\nDescription: " + this.description);
		sb.append("\nPosition: (" + xPosition + "," + yPosition + ")\n");
		System.out.println(sb.toString());
		
	}
}

enum ItemType{
	WEAPON(),
	CONSUMABLE(),
	CLOTHING();
	
}
