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
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson, Stuart Thain
 *
 */

public class DataStore {
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
	public LinkedList<Bullet> bullets;
	/**
	 * 
	 */
	public LinkedList<Enemy> enemies;
	/**
	 * 
	 */
	public LinkedList<SessileSprite> worldSprites;
	/**
	 * 
	 */
	public LinkedList<Notification> notifications;
	/**
	 * 
	 */
	public int baseSpeed;
	/**
	 * 
	 */
	public Images images;
	/**
	 * 
	 */
	public int pace;
	/**
	 * 
	 */
	public boolean cameraAttachedToPlayer;
	/**
	 * 
	 */
	public double maxWidth, maxHeight;
	/**
	 * 
	 */
	public int periodSinceLastFire;
	
	/**
	 * 
	 */
	public LevelReader levelReader;
	/**
	 * 
	 */
	public Level level;
	
	/**
	 * 
	 */
	public static DataStore instance;

	public int levelNumber;
	
	private DataStore() {
		
	}
	
	/**
	 * 
	 */
	public void setEverything(){
		levelNumber = 0;
		baseSpeed = 5;
		images = new Images();
		
		enemies = new LinkedList<Enemy>();
		player = new Player(0, 0, Direction.SOUTH, 0);
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
		
		
		
		for(int i =0 ; i < 4; i++){
			world.createSessileSprites(world.createDefaultBoundaries()[i], 0);
			}
		world.createSessileSprites(world.createBoundaries(200, 200, 256, 128), 1);
		world.createSessileSprites(world.createBoundaries(400, 400, 256, 256), 2);
	}

	static {
		instance = new DataStore();
	}

	/**
	 * @return
	 */
	public static DataStore getInstance() {
		return instance;
	}
}
