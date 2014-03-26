package main;

import java.util.ArrayList;

public class Level {
	private ArrayList<ArrayList<Integer>> initialEnemyCoordinates;
	private ArrayList<Integer> initialPlayerCoordinates;
	private int levelID;
	private String name;
	Level(){
		
	}
	public ArrayList<ArrayList<Integer>> getInitialEnemyCoordinates() {
		return initialEnemyCoordinates;
	}
	public void setInitialEnemyCoordinates(
			ArrayList<ArrayList<Integer>> initialEnemyCoordinates) {
		this.initialEnemyCoordinates = initialEnemyCoordinates;
	}
	public ArrayList<Integer> getInitialPlayerCoordinates() {
		return initialPlayerCoordinates;
	}
	public void setInitialPlayerCoordinates(
			ArrayList<Integer> initialPlayerCoordinates) {
		this.initialPlayerCoordinates = initialPlayerCoordinates;
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
}
