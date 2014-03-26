package main;

import java.io.Serializable;
import java.util.ArrayList;

import sprites.Enemy;
import sprites.SessileSprite;

public class Level implements Serializable{
	
	private ArrayList<Enemy> enemies;
	private ArrayList<SessileSprite> sessileSprites;
	private int levelID;
	private String name;
	Level(){
		
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
		return sb.toString();
	}
}
