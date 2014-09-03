package main;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import sprites.SessileSprite;

/**
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * 
 */
public class LevelReader {

	private String levelFile;
	private BufferedReader levelReader;
	private LinkedList<Level> tempLevels;

	/**
	 * 
	 */
	public LevelReader() {

		levelFile = "res/txt/levels.txt";
		tempLevels = new LinkedList<Level>();
		try {
			levelReader = new BufferedReader(new FileReader(levelFile));
		} catch (Exception e) {
			System.out.println("Level file not found: " + levelFile);
		}
		prepareReader();
		
	
	}

	/**
	 * @return The next level of the game.
	 */
	public Level getNextLevel() {
		readLevel();
		return tempLevels.getLast();
	}
	
	/**
	 * Returns the previous level by removing the current level from the linked list, then getting the last level from the linked list.
	 * 
	 * @return The previous level.
	 */
	public Level getPreviousLevel(){
		removeLevel();
		try{
		return tempLevels.getLast();
		}catch(NoSuchElementException e){
			System.err.println("Who cares whats's causing this error, good luck solving it in the future! #YOLO");
		}
		return new Level();
		
	}

	/**
	 * Remove the latest level in the levels linked list.
	 */
	private void removeLevel() {
		if(!tempLevels.isEmpty()){
			tempLevels.remove();
		}else{
			System.err.println("No levels to remove, this shoudn't happen, meaning the code is broken somewhere.");
		}
		
	}

	/**
	 * 
	 */
	public void loadLevel() {
		DataStore.getInstance().levelNumber++;

	}

	/**
	 * 
	 */
	public void prepareReader() {
		String line;
		try {
			while ((line = levelReader.readLine()) != null
					&& !line.equals("begin:")) {

			}
			System.out.println("Reader set up succesfully");
		} catch (Exception e) {

			System.out.println("Error reading level file");

		}
	}

	/**
	 * 
	 */
	public void readLevel() {
		String line;
		boolean endOfLevel = false;
		try {
			Level level = new Level();
			boolean inLevel = false;
			boolean inSessileSprites = false;
			boolean inName = false;
			boolean inID = false;
			boolean inSpriteBlock = false;
			boolean inEnemies = false;
			boolean inNotifications = false;
			while ((line = levelReader.readLine()) != null && !endOfLevel) {

				if (line.contains("<level>")) {
					inLevel = true;
					level = new Level();
					//

				} else if (line.contains("</level>") && inLevel) {
					inLevel = false;
					tempLevels.add(level);
					endOfLevel = true;
					System.out.println(level.toString());
					level = new Level();
				} else if (line.contains("<name>") && !inName) {
					inName = true;
					level.setName(levelReader.readLine().trim());
				} else if (line.contains("</name>") && inName) {
					inName = false;
				} else if (line.contains("<id>") && !inID) {
					inID = true;
					level.setLevelID(Integer.parseInt(levelReader.readLine()
							.trim()));
				} else if (line.contains("</id>") && inID) {
					inID = false;
				} else if (line.contains("<SessileSprites>") && inLevel
						&& !inSessileSprites) {
					inSessileSprites = true;
				} else if (line.contains("</SessileSprites>") && inLevel
						&& inSessileSprites) {
					inSessileSprites = false;
				} else if (line.contains("<spriteblock>") && inLevel
						&& !inSpriteBlock) {
					inSpriteBlock = true;
				} else if (line.contains("</spriteblock>") && inLevel
						&& inSpriteBlock) {
					inSpriteBlock = false;
				} else if (line.contains("<enemies>") && inLevel && !inEnemies) {
					inEnemies = true;
				} else if (line.contains("</enemies>") && inLevel && inEnemies) {
					inEnemies = false;
				} else if (line.contains("<notifications>") && inLevel && !inNotifications){
					inNotifications = true;
				} else if (line.contains("</notifications>") && inLevel && inNotifications){
					inNotifications = false;
				} else if (inSessileSprites) {

					String[] data = line.split(",");
					int x = Integer.parseInt(data[0].trim());
					int y = Integer.parseInt(data[1].trim());
					int type = Integer.parseInt(data[2].trim());
					SessileSprite s = new SessileSprite(x, y, type);
					level.addSessileSprite(s);
					System.out.println(s.toString());

				} else if (inSpriteBlock) {
					// We need rectangle x,y,w,h and spriteImage. So 5
					// datapoints
					String[] data = line.split(",");
					int x = Integer.parseInt(data[0].trim());
					int y = Integer.parseInt(data[1].trim());
					int w = Integer.parseInt(data[2].trim());
					int h = Integer.parseInt(data[3].trim());
					int img = Integer.parseInt(data[4].trim());
					level.addSpriteBlock(x, y, w, h, img);
				} else if (inEnemies) {
					String[] data = line.split(",");
					int x = Integer.parseInt(data[0].trim());
					int y = Integer.parseInt(data[1].trim());
					int id = Integer.parseInt(data[2].trim());
					int movement = Integer.parseInt(data[3].trim());

					level.addEnemy(x, y, id, movement);
				} else if (inNotifications){
					String[] data = line.split(",");
					String text = data[0].trim();
					Color textColour = Color.getColor(data[1].trim());
					Color backColour = Color.getColor(data[2].trim());
					//TODO: Add x position, y postion and display time.

					level.addNotification(text, textColour, backColour);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
