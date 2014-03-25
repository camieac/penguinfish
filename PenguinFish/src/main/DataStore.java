package main;

import java.util.LinkedList;

import graphics.World;
import graphics.Images;
import sprites.Bullet;
import sprites.Enemy;
import sprites.Player;
import sprites.SessileSprite;

public class DataStore {
	public Player player;
	public World background;
	public LinkedList<Bullet> bullets;
	public LinkedList<Enemy> enemies;
	public LinkedList<SessileSprite> worldSprites;
	public int baseSpeed;
	public Images images;
	public int pace;
	public boolean cameraAttachedToPlayer;
	public double maxWidth, maxHeight;
	public int periodSinceLastFire;

	public static DataStore instance;

	private DataStore() {
		
	}
	
	public void setEverything(){
		baseSpeed = 5;
		images = new Images();
		background = new World();
		background.createSessileSprites();
		enemies = new LinkedList<Enemy>();
		player = new Player(0, 0, Direction.SOUTH, 0);
		bullets = new LinkedList<Bullet>();
		player.setSpeed(baseSpeed);
		pace = 1;
		cameraAttachedToPlayer = false;
		maxWidth = images.getBackground().getWidth();
		maxHeight = images.getBackground().getHeight();
		periodSinceLastFire = 0;
	}

	static {
		instance = new DataStore();
	}

	public static DataStore getInstance() {
		return instance;
	}
}
