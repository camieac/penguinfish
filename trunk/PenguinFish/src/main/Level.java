package main;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import sprites.Enemy;
import sprites.SessileSprite;
import sprites.SpriteBlock;

/**
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * 
 */
public class Level {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6846891938893773222L;

	private String description;

	private LinkedList<Enemy> enemies;

	private int levelID;

	private String name;

	private LinkedList<SessileSprite> sessileSprites;

	private LinkedList<SpriteBlock> spriteBlocks;

	Level() {
		enemies = new LinkedList<Enemy>();
		sessileSprites = new LinkedList<SessileSprite>();
		spriteBlocks = new LinkedList<SpriteBlock>();
		levelID = 0;
		name = "No name specified";
		description = "No description specified.";
	}

	/**
	 * @param e
	 */
	public void addEnemy(Enemy e) {
		enemies.add(e);
	}

	/**
	 * @param x
	 * @param y
	 * @param id
	 * @param movement
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
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param img
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
	 * @return
	 */
	public int getLevelID() {
		return levelID;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public LinkedList<SessileSprite> getSessileSprites() {
		return sessileSprites;
	}

	/**
	 * @return
	 */
	public LinkedList<SpriteBlock> getSpriteBlocks() {
		return spriteBlocks;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param enemies
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
	public void setSpriteBlocks(LinkedList<SpriteBlock> spriteBlocks) {
		this.spriteBlocks = spriteBlocks;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Name: " + name + "\n");
		sb.append("ID: " + levelID + "\n");
		sb.append("# of sessileSprites: " + sessileSprites.size() + "\n");
		sb.append("# of spriteblocks: " + spriteBlocks.size() + "\n");
		sb.append("# of enemies: " + enemies.size() + "\n");
		return sb.toString();
	}
}
