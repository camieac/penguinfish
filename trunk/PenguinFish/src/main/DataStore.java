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
 * Stores variables what are required to be accessed globally.
 * 
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * 
 */

public class DataStore {
	/**
	 * The instance of Datastore that is used.
	 */
	public static DataStore instance;
	static {
		instance = new DataStore();
	}

	/**
	 * @return The instance of Datastore.
	 */
	public static DataStore getInstance() {
		return instance;
	}

	/**
	 * The speed of the player. All other speeds are based on this speed.
	 */
	public int baseSpeed;
	/**
	 * LinkedList containing the bullets that are currently in the game. Bullets
	 * are created when fired by the player and destroyed when they leave the
	 * edge of the camera.
	 */
	public LinkedList<Bullet> bullets;
	/**
	 * True if the camera is following the player, false if not.
	 */
	public boolean cameraAttachedToPlayer;
	/**
	 * A list of enemies in the game. They are created when the level loads and
	 * destroyed when killed.
	 */
	public LinkedList<Enemy> enemies;
	/**
	 * A collection of images that are used in the game.
	 */
	public Images images;
	/**
	 * The current level that the player is playing.
	 */
	public Level level;
	/**
	 * The current level number that the player is on.
	 */
	public int levelNumber;
	/**
	 * Reads in levels one at a time, on request.
	 */
	public LevelReader levelReader;
	/**
	 * TODO:Not required?
	 */

	public double maxWidth;
	/**
	 * TODO:Not required?
	 */
	public double maxHeight;

	/**
	 * 
	 */
	public LinkedList<Notification> notifications;
	/**
	 * The current pace of the game. It determined how fast everything moves.
	 * TODO:Not required? Because of baseSpeed?
	 */
	public int pace;

	/**
	 * 
	 */
	public int periodSinceLastFire;

	/**
	 * The player that the user controls.
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
	 * The widtrh of the panel that contains the game.
	 */
	public int panelWidth;
	/**
	 * Thje height of the panel that contains the game.
	 */
	public int panelHeight;
	/**
	 * True if the game has started, false if not. TODO:Not required? Because of
	 * gameState?
	 */
	public boolean gameStarted;
	/**
	 * Stores the current state of the game. @see main.State
	 */
	public State gameState;

	private DataStore() {

	}

	/**
	 * 
	 */
	// public void setEverything() { TODO:Not required? Replaced by
	// setInitialFields and setGameFields
	//
	//
	//
	//
	//
	//
	//
	// System.out.println("Everything has been setup in DataStore.");
	//
	//
	//
	//
	// }
	/**
	 * When the game is first opened, not all of DataStore's fields are
	 * required, so this method initialises the required fields to run the
	 * stating animation and display the start menu.
	 */
	public void setInitialFields() {
		// these should be in setGameFields

		panelWidth = 1280;
		panelHeight = 750;

		images = new Images();
		gameState = State.STARTINGANIMATION;
		gameStarted = false;
	}

	/**
	 * Sets up all the fields used by the game class.
	 */
	public void setGameFields() {
		levelReader = new LevelReader();
		level = levelReader.getNextLevel();

		maxWidth = images.getBackground(levelNumber).getWidth();
		maxHeight = images.getBackground(levelNumber).getHeight();
		levelNumber = 0;
		baseSpeed = 5;
		periodSinceLastFire = 0;
		enemies = new LinkedList<Enemy>();
		player = new Player(200, 200, Direction.SOUTH, 0);
		bullets = new LinkedList<Bullet>();
		player.setSpeed(baseSpeed);
		pace = 1;
		cameraAttachedToPlayer = false;
		world = new World();

		notifications = new LinkedList<Notification>();
	}
}