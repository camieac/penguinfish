package main;

import graphics.Notification;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import sprites.Enemy;
import sprites.Item;
import sprites.ItemType;
import sprites.SessileSprite;
import sprites.SpriteBlock;

/**
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * 
 */
public class Level {

	/**
	 * This is here for no reason, someone will delete it eventually.
	 */
	//private static final long serialVersionUID = 6846891938893773222L;
	/**
	 * The description of the level.
	 */
	private String description;
	/**
	 * The enemies that are in the level.
	 */
	private LinkedList<Enemy> enemies;
	/**
	 * The unique ID of the level.
	 */
	private int levelID;
	/**
	 * The name of the level.
	 */
	private String name;
	/**
	 * The sessile sprites that appear in the level.
	 */
	private LinkedList<SessileSprite> sessileSprites;
	/**
	 * The blocks of sprites that are used in the level.
	 */
	private ArrayList<SpriteBlock> spriteBlocks;
	/**
	 * The notifications that are used in the level to instruct the user.
	 */
	private ArrayList<Notification> notifications;
	/**
	 * A list of items to be populated into the level.
	 */
	private ArrayList<Item> items;

	Level() {
		enemies = new LinkedList<Enemy>();
		sessileSprites = new LinkedList<SessileSprite>();
		spriteBlocks = new ArrayList<SpriteBlock>();
		levelID = 0;
		name = "No name specified";
		description = "No description specified.";
		notifications = new ArrayList<Notification>();
		items = new ArrayList<Item>();
	}

	/**
	 * @param e
	 */
	public void addEnemy(Enemy e) {
		enemies.add(e);
	}

	/**
	 * @param x The starting x position of the enemy.
	 * @param y The starting y position of the enemy.
	 * @param id The image key of the enemy, in order to identify what image goes with the enemy.
	 * @param movement The movement key, identifies what type of movement the enemy will make.
	 */
	public void addEnemy(int x, int y, int id, int movement) {
		enemies.add(new Enemy(x, y, id, movement));

	}
	/**
	 * @param s
	 */
	public void addSessileSprite(SessileSprite s) {
		sessileSprites.add(s);
	}
	/**
	 * @param x The leftmost x position of the start of the sprite block.
	 * @param y The upper most y position of the start of the sprite block.
	 * @param w The width of the sprite block
	 * @param h The height of the sprite block.
	 * @param img The sessile sprite image key that is used to identify what image to display.
	 */
	public void addSpriteBlock(int x, int y, int w, int h, int img) {
		spriteBlocks.add(new SpriteBlock(x, y, w, h, img));

	}
	public String getDescription() {
		return description;
	}
	/**
	 * @return All the enemies in the level in a LinkedList.
	 */
	public LinkedList<Enemy> getEnemies() {
		return enemies;
	}
	/**
	 * @return The ID of the level.
	 */
	public int getLevelID() {
		return levelID;
	}

	/**
	 * @return The name of the level.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The sessile sprites that are part of the level.
	 */
	public LinkedList<SessileSprite> getSessileSprites() {
		return sessileSprites;
	}

	/**
	 * @return The blocks of sessile sprites that are part of the level
	 */
	public ArrayList<SpriteBlock> getSpriteBlocks() {
		return spriteBlocks;
	}

	/**
	 * Sets the description of the level.
	 * @param description The description of the level
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * This imports a LinkedList od enemies into the level.
	 * @param enemies The enemies that are part of the level.
	 */
	public void setEnemies(LinkedList<Enemy> enemies) {
		this.enemies = enemies;
	}

	/**
	 * @param levelID
	 */
	public void setLevelID(int levelID) {
		this.levelID = levelID;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param sessileSprites
	 */
	public void setSessileSprites(LinkedList<SessileSprite> sessileSprites) {
		this.sessileSprites = sessileSprites;
	}

	/**
	 * @param spriteBlocks
	 */
	public void setSpriteBlocks(ArrayList<SpriteBlock> spriteBlocks) {
		this.spriteBlocks = spriteBlocks;
	}
	
	/**
	 * Sets the notifications for the level.
	 * @param notifications The notifications that are used in the level.
	 */
	public void setNotifications(ArrayList<Notification> notifications){
		this.notifications = notifications;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Name: " + name + "\n");
		sb.append("ID: " + levelID + "\n");
		sb.append("# of sessileSprites: " + sessileSprites.size() + "\n");
		sb.append("# of spriteblocks: " + spriteBlocks.size() + "\n");
		sb.append("# of enemies: " + enemies.size() + "\n");
		sb.append("# of notifi.: " + notifications.size() + "\n");
		return sb.toString();
	}

	/**
	 * Adds a notification to the existing notifications.
	 * @param text The text to be displayed in the notification.
	 * @param textColour The text colour.
	 * @param backColour The background colour.
	 * @param displayTime The time in milliseconds that the notification should display at.
	 * @param displayDuration The length of time in milliseconds that the notification should display for.
	 */
	public void addNotification(String text, Color textColour, Color backColour, long displayTime, long displayDuration, int xPosition, int yPosition) {
		Notification n = new Notification(text,textColour,backColour);
		n.setTimeOfAppearance(displayTime);
		n.setDurationOfAppearance(displayDuration);
		n.setPosition(xPosition,yPosition);
		notifications.add(n);
		
	}

	/**
	 * @return An arraylist of notifications for this level.
	 */
	public ArrayList<Notification> getNotifications() {
		return notifications;
	}
	/**
	 * @param name
	 * @param description
	 * @param image
	 * @param itemType
	 * @param value
	 * @param xCoordinate
	 * @param yCoordinate
	 */
	public void addItem(String name, String description, BufferedImage image, ItemType itemType, int value,int xCoordinate,int yCoordinate){
		Item item = new Item(name,description,image,itemType,value,xCoordinate,yCoordinate);
		items.add(item);
	}

	/**
	 * @return All the items in the level.
	 */
	public ArrayList<Item> getItems() {
		return items;
	}
}
