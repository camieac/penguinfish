package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.activation.DataSource;

import sprites.Item;
import sprites.ItemType;

/**
 * Represents the inventory of the player. Contains a list of pickable items
 * that are currently in the player's inventory.
 * 
 * @author Cameron A. Craig
 * @since 17th April 2014
 * 
 */
public class Inventory {
	private int width, height;
	private int xPosition, yPosition;
	private LinkedList<Item> items;
	private int currentNumberOfItems;
	private boolean visible;

	/**
	 * 
	 */
	public Inventory() {
		width = DataStore.getInstance().panelWidth;
		height = 150;
		xPosition = 0;
		yPosition = DataStore.getInstance().panelHeight - height;
		currentNumberOfItems = 0;
		visible = true;

		items = new LinkedList<Item>();
		addItem(new Item("Sword", "This is a sword", DataStore.getInstance().images.getSword(), ItemType.WEAPON, 100, 400, 400));
		
		printItems();
	}

	/**
	 * @param g
	 */
	public void displayInventory(Graphics g) {
		if (visible) {

			Color oldColor = g.getColor();
			Color inventoryColor = Color.getHSBColor(118, 37, 100);
			Font oldFont = g.getFontMetrics().getFont();
			g.setColor(inventoryColor);
			g.drawRect(xPosition, yPosition, width, height);
			g.fillRect(xPosition, yPosition, width, height);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Helvetica", 10, 10));
			g.drawString("Inventory", xPosition + 10, yPosition + 10);

			g.setColor(oldColor);
			g.setFont(oldFont);

			for (Item i : items) {
				i.draw(g);
			}
		}
	}

	/**
	 * @param item
	 */
	public void addItem(Item item) {
		int xYOffset = 20;
		int xSeperation = 64;
		item.setPosition(xPosition + xYOffset
				+ (currentNumberOfItems * xSeperation), yPosition + xYOffset);
		items.add(item);
		currentNumberOfItems++;

	}

	/**
	 * 
	 */
	public void printItems() {
		for (Item i : items) {
			i.print();
		}
	}

	/**
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * @return 
	 * 
	 */
	public boolean isVisible() {
		return visible;
		
	}
}
