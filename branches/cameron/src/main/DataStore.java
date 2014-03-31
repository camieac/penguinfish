package main;

import graphics.Images;
import graphics.Notification;
import graphics.World;

import java.util.LinkedList;

import sprites.Bullet;
import sprites.Enemy;
import sprites.Player;
import sprites.SessileSprite;

/**
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * 
 */

public class DataStore {
	/**
	 * 
	 */
	public static DataStore instance;
	static {
		instance = new DataStore();
	}
	/**
	 * @return
	 */
	public static DataStore getInstance() {
		return instance;
	}
	/**
	 * 
	 */
	public int baseSpeed;
	/**
	 * 
	 */
	public LinkedList<Bullet> bullets;
	/**
	 * 
	 */
	public boolean cameraAttachedToPlayer;
	/**
	 * 
	 */
	public LinkedList<Enemy> enemies;
	/**
	 * 
	 */
	public Images images;
	/**
	 * 
	 */
	public Level level;
	public int levelNumber;
	/**
	 * 
	 */
	public LevelReader levelReader;
	/**
	 * 
	 */
	public double maxWidth, maxHeight;

	/**
	 * 
	 */
	public LinkedList<Notification> notifications;
	/**
	 * 
	 */
	public int pace;

	/**
	 * 
	 */
	public int periodSinceLastFire;

	/**
	 * 
	 */
	public Player player;

	/**
	 * 
	 */
	public World world;

	/**
	 * 
	 */
	public LinkedList<SessileSprite> worldSprites;

	
	/**
	 * 
	 */
	public int panelWidth;
	public int panelHeight;
	private DataStore() {

	}

	/**
	 * 
	 */
	public void setEverything() {
		panelHeight = 512;
		panelWidth = 512;
		levelNumber = 0;
		baseSpeed = 5;
		images = new Images();
		enemies = new LinkedList<Enemy>();
		player = new Player(200, 200, Direction.SOUTH, 0);
		bullets = new LinkedList<Bullet>();
		player.setSpeed(baseSpeed);
		pace = 1;
		cameraAttachedToPlayer = false;
		maxWidth = images.getBackground(levelNumber).getWidth();
		maxHeight = images.getBackground(levelNumber).getHeight();
		periodSinceLastFire = 0;
		levelReader = new LevelReader();
		level = levelReader.getNextLevel();
		world = new World();
		notifications = new LinkedList<Notification>();
		

	}
}
