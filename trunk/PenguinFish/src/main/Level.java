package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import sprites.Enemy;
import sprites.SessileSprite;

public class Level implements Serializable{
	
	public LinkedList<SessileSprite> getSessileSprites() {
		return sessileSprites;
	}
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
	public void addSessileSprite(SessileSprite s){
		sessileSprites.add(s);
	}
	public void addEnemy(Enemy e){
		enemies.add(e);
	}
	
	
	public int getLevelID() {
		return levelID;
	}
	public void setLevelID(int levelID) {
		this.levelID = levelID;
	}
	public String getName() {
		return name;
	}
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
