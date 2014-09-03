package main;

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

	Item(String name, String description, BufferedImage image, int value) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.value = value;
	}
}

enum ItemType{
	WEAPON(),
	CONSUMABLE(),
	CLOTHING();
	
}
