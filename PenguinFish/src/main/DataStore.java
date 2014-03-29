package main;

import graphics.Images;
import graphics.Notification;
import graphics.World;

import java.util.LinkedList;

import sprites.Bullet;
import sprites.Enemy;
import sprites.Player;
import sprites.SessileSprite;

public class DataStore {
	public Player player;
	public World world;
	public LinkedList<Bullet> bullets;
	public LinkedList<Enemy> enemies;
	public LinkedList<SessileSprite> worldSprites;
	public LinkedList<Notification> notifications;
	public int baseSpeed;
	public Images images;
	public int pace;
	public boolean cameraAttachedToPlayer;
	public double maxWidth, maxHeight;
	public int periodSinceLastFire;
	
	public LevelReader levelReader;
	public Level level;
	
	public static DataStore instance;

	
	
	private DataStore() {
		
	}
	
	public void setEverything(){
		baseSpeed = 5;
		images = new Images();
		
		enemies = new LinkedList<Enemy>();
		player = new Player(0, 0, Direction.SOUTH, 0);
		bullets = new LinkedList<Bullet>();
		player.setSpeed(baseSpeed);
		pace = 1;
		cameraAttachedToPlayer = false;
		maxWidth = images.getBackground().getWidth();
		maxHeight = images.getBackground().getHeight();
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

	public static DataStore getInstance() {
		return instance;
	}
}
