package main;

import graphics.Images;
import graphics.Notification;
import graphics.World;

import java.awt.Color;
import java.util.ArrayList;
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
	 * A help notification. Generated when the player presses H.
	 */
	public Notification helpNotification;
	/**
	 * The time in the game in milliseconds. Used to trigger time-based events. The time is reset at the start of each level. Set to 0 for safety.
	 */
	public long currentLevelTime = 0;
	
	/**
	 * The current system time that was recorded at the start of the level. Set to 0 for safety.
	 */
	public long levelStartTime = 0;
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
	 * A linked list of all the notifications for the current game.
	 */
	public ArrayList<Notification> notifications;
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
	 * When the game is first opened, not all of DataStore's fields are
	 * required, so this method initialises the required fields to run the
	 * stating animation and display the start menu.
	 */
	public void setInitialFields() {
		/*The default window size is set here*/
		panelWidth = 1280;
		panelHeight = 750;

		images = new Images();
		
		/*The initial game state is the starting animation.*/
		gameState = State.STARTINGANIMATION;
		gameStarted = false;
	}

	/**
	 * Sets up all the fields used by the game class.
	 */
	public void setGameFields() {
		levelReader = new LevelReader("res/txt/levels.txt");
		level = levelReader.getNextLevel();

		maxWidth = images.getBackground(levelNumber).getWidth();
		maxHeight = images.getBackground(levelNumber).getHeight();
		levelNumber = 0;
		baseSpeed = 5;
		periodSinceLastFire = 0;
		enemies = new LinkedList<Enemy>();
		notifications = new ArrayList<Notification>();
		helpNotification = new Notification("No Help Available", Color.BLACK, Color.WHITE);
		player = new Player(200, 200, Direction.SOUTH, 0);
		bullets = new LinkedList<Bullet>();
		player.setSpeed(baseSpeed);
		pace = 1;
		cameraAttachedToPlayer = false;
		world = new World();

		
	}
}