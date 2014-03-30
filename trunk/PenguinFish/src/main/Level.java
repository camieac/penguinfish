package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import sprites.Enemy;
import sprites.SessileSprite;

/**
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson, Stuart Thain
 *
 */
public class Level implements Serializable{
	
	/**
	 * @return
	 */
	public LinkedList<SessileSprite> getSessileSprites() {
		return sessileSprites;
	}
	/**
	 * @param sessileSprites
	 */
	public void setSessileSprites(LinkedList<SessileSprite> sessileSprites) {
		this.sessileSprites = sessileSprites;
	}
	private LinkedList<Enemy> enemies;
	private LinkedList<SessileSprite> sessileSprites;
	private int levelID;
	private String name;
	Level(){
		enemies = new LinkedList<Enemy>();
		sessileSprites = new LinkedList<SessileSprite>();
		levelID = 0;
		name = "No Name Specified";
	}
	/**
	 * @param s
	 */
	public void addSessileSprite(SessileSprite s){
		sessileSprites.add(s);
	}
	/**
	 * @param e
	 */
	public void addEnemy(Enemy e){
		enemies.add(e);
	}
	
	
	/**
	 * @return
	 */
	public int getLevelID() {
		return levelID;
	}
	/**
	 * @param levelID
	 */
	public void setLevelID(int levelID) {
		this.levelID = levelID;
	}
	/**
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("Name: " + name + "\n");
		sb.append("ID: " + levelID + "\n");
		sb.append("# of sessileSprites: " + sessileSprites.size() + "\n");
		return sb.toString();
	}
}
