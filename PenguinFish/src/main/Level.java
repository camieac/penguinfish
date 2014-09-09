package main;

import graphics.Notification;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import sprites.Enemy;
import sprites.Item;
import sprites.ItemType;
import sprites.Player;
import sprites.SessileSprite;
import sprites.SpriteBlock;

/**
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * 
 */
public class Level {

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
	
//	protected LinkedList<Rectangle> boundaries;
//	protected int defaultBoundedAreas = 4;
//	protected Rectangle[] defaultBoundaries;
//	public LinkedList<LinkedList<SessileSprite>> sessileSpritesLL;

	Level() {
		enemies = new LinkedList<Enemy>();
		sessileSprites = new LinkedList<SessileSprite>();
		spriteBlocks = new ArrayList<SpriteBlock>();
		levelID = 0;
		name = "No name specified";
		description = "No description specified.";
		notifications = new ArrayList<Notification>();
		items = new ArrayList<Item>();
		
//		sessileSpritesLL = new LinkedList<LinkedList<SessileSprite>>();
//		defaultBoundaries = new Rectangle[defaultBoundedAreas];
//		boundaries = new LinkedList<Rectangle>();

//		setupDefaultBoundaries();
//		addLevelEnemies();
//		addLevelNotifications();
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
		sb.append("# of items: " + items.size());
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
	
//	private void addLevelNotifications() {
//		ArrayList<Notification> arr = DataStore.getInstance().level.getNotifications();
//		DataStore.getInstance().notifications.clear();
//		DataStore.getInstance().notifications.addAll(arr);
//		
//	}

	/**
	 * 
	 */
//	public void setupDefaultBoundaries() {
//		boundaries.clear();
//		sessileSprites.clear();
////		addLevelSprites();
//		addLevelSpriteBlocks();
//		
//		for (int i = 0; i < 4; i++) {
//			createSpriteBlock(createDefaultBoundaries()[i], 4);
//		}
//	}

	/**
	 * 
	 */
	public void nextLevel() { // TODO: Finish this thing
		
		DataStore.getInstance().levelStartTime = System.currentTimeMillis();//#untested
		
		DataStore.getInstance().levelNumber++;
		if (DataStore.getInstance().levelNumber > DataStore.getInstance().images.getBackgrounds().length - 1) {
			DataStore.getInstance().levelNumber = DataStore.getInstance().images.getBackgrounds().length - 1;
		}
//		sessileSprites.clear();
//		DataStore.getInstance().enemies.clear();
		DataStore.getInstance().level = DataStore.getInstance().levelReader
				.getLevel(DataStore.getInstance().levelNumber);
//		addLevelSprites();
//		addLevelSpriteBlocks();
//		addLevelEnemies();
//		addLevelNotifications();
	}
	
	/**
	 * Changes the level stored in the datastore to the previous level.
	 */
	public void previousLevel() { // TODO: Finish this thing
		DataStore.getInstance().levelStartTime = System.currentTimeMillis(); //#untested
		DataStore.getInstance().levelNumber--;

		if(DataStore.getInstance().levelNumber < 0){
			DataStore.getInstance().levelNumber = 0;
		}
//		sessileSprites.clear();
//		DataStore.getInstance().enemies.clear();
		DataStore.getInstance().level = DataStore.getInstance().levelReader
				.getLevel(DataStore.getInstance().levelNumber);
//		addLevelSprites();
//		addLevelSpriteBlocks();
//		addLevelEnemies();
//		addLevelNotifications();
	}

//	private void addLevelEnemies() {
//		LinkedList<Enemy> lle = DataStore.getInstance().level.getEnemies();
//		DataStore.getInstance().enemies.clear();
//		DataStore.getInstance().enemies.addAll(lle);
//	}

//	private void addLevelSpriteBlocks() {
//		ArrayList<SpriteBlock> ll = DataStore.getInstance().level
//				.getSpriteBlocks();
//		for (SpriteBlock s : ll) {
//			createSpriteBlock(s.getRect(), s.getImgNumber());
//		}
//
//	}

	// For each rectangle which acts as a boundary (4 edges by default)
	// fill with sessile sprites until it is full. Integer fill
	// indicates the number of the sessile sprite to fill the area.
	/**
	 * @param rect
	 * @param spriteImage
	 */
//	public void addLevelSprites() {
//		sessileSpritesLL.add(DataStore.getInstance().level.getSessileSprites());
//	}

//	/**
//	 * @param x
//	 * @param y
//	 * @param width
//	 * @param height
//	 * @return
//	 */
//	public Rectangle createBoundaries(int x, int y, int width, int height) {
//		Rectangle boundary = new Rectangle(x, y, width, height);
//		boundaries.add(boundary);
//		return boundary;
//	}

	// Create rectangular areas which act as perimeter for the map.
	/**
	 * @return
	 */
//	public Rectangle[] createDefaultBoundaries() {
//		//System.out.println("WIDTH: " + DataStore.getInstance().panelWidth + " HEIGHT: " + DataStore.getInstance().panelHeight);
//		int level = DataStore.getInstance().levelNumber;
//		defaultBoundaries[0] = new Rectangle(
//				-(DataStore.getInstance().panelWidth/ 2),
//				-(DataStore.getInstance().panelHeight / 2),
//				DataStore.getInstance().images.getBackground(level).getWidth()
//						+ (DataStore.getInstance().panelWidth),
//				(DataStore.getInstance().panelHeight/ 2));
//		defaultBoundaries[1] = new Rectangle(
//				-(DataStore.getInstance().panelWidth / 2),
//				DataStore.getInstance().images.getBackground(level).getHeight(),
//				DataStore.getInstance().images.getBackground(level).getWidth()
//						+ (DataStore.getInstance().panelWidth),
//				(DataStore.getInstance().panelHeight / 2));
//		defaultBoundaries[2] = new Rectangle(
//				-(DataStore.getInstance().panelWidth / 2), 0,
//				(DataStore.getInstance().panelWidth / 2),
//				DataStore.getInstance().images.getBackground(level).getHeight());
//		defaultBoundaries[3] = new Rectangle(DataStore.getInstance().images
//				.getBackground(level).getWidth(), 0,
//				(DataStore.getInstance().panelWidth / 2),
//				DataStore.getInstance().images.getBackground(level).getHeight());
//		for (int i = 0; i < defaultBoundedAreas; i++) {
//			// createSpriteBlock(defualtBoundaries[i], 0);
//			boundaries.add(defaultBoundaries[i]);
//		}
//		return defaultBoundaries;
//	}

//	/**
//	 * @param rect The rectangle to create the sprite block in.
//	 * @param spriteImage The image of the sprite that will be arrayed within the rectangle.
//	 */
//	public void createSpriteBlock(Rectangle rect, int spriteImage) {
//		LinkedList<SessileSprite> sessileSpriteType = new LinkedList<SessileSprite>();
//		for (int j = 0; j < rect.height
//				/ DataStore.getInstance().images.getSessileImage(spriteImage)
//						.getHeight(); j++) {
//			for (int i = 0; i < rect.width
//					/ DataStore.getInstance().images.getSessileImage(
//							spriteImage).getWidth(); i++) {
//				sessileSpriteType.add(new SessileSprite(rect.x
//						+ (DataStore.getInstance().images.getSessileImage(
//								spriteImage).getWidth() * i), rect.y
//						+ (DataStore.getInstance().images.getSessileImage(
//								spriteImage).getHeight() * j), spriteImage));
//			}
//		}
//		sessileSpritesLL.add(sessileSpriteType);
//
//	}

	/**
	 * @param g
	 * @param camX
	 * @param camY
	 */
	public void draw(Graphics g, double camX, double camY) {
		g.drawImage(DataStore.getInstance().images.getBackground(DataStore
				.getInstance().levelNumber), -(int) camX, -(int) camY, null);

	}

//	/**
//	 * @return A Linked list containing all the sessile sprites in the world.
//	 */
//	public LinkedList<SessileSprite> getSessileSpritesLL() {
//		LinkedList<SessileSprite> ll = new LinkedList<SessileSprite>();
//		for (LinkedList<SessileSprite> l : sessileSpritesLL) {
//			ll.addAll(l);
//		}
//		return ll;
//	}

	/**
	 * @param player
	 */
	public void tick(Player player) {
//		for (Rectangle b : boundaries) {
//			if (player.collide(b)) {
//				// Need to sort out directions!!!
//				// player.getDirection().disableDirection(player.getDirection());
//			} else {
//				// player.getDirection().enableDirection(player.getDirection());
//				// player.getDirection().clearDisabledDirections();
//			}
//		}
		for(Notification n : DataStore.getInstance().level.getNotifications()){
			n.tick();
		}
	}
}
