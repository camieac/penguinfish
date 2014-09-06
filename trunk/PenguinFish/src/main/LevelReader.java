package main;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
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
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import sprites.Item;
import sprites.ItemType;
import sprites.SessileSprite;

/**
 * Handles all level file reading. Provides level objects on demand.
 * 
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * @since September 2014
 * 
 */
public class LevelReader {

	private String levelFileLocation;
	private BufferedReader levelReader;
	private LinkedList<Level> levels;
	private boolean finishedReading;

	/**
	 * @param levelFileLocation The location of the level file. Development file location : "res/txt/levels.txt"
	 * 
	 */
	public LevelReader(String levelFileLocation) {

		this.levelFileLocation = levelFileLocation;
		levels = new LinkedList<Level>();
		try {
			levelReader = new BufferedReader(new FileReader(this.levelFileLocation));
		} catch (Exception e) {
			System.out.println("Level file not found: " + this.levelFileLocation);
		}
		prepareReader();
		finishedReading = false;
		while(!finishedReading){
			System.out.println("Reading a level...");
			readLevel();
		}
		System.out.println("Done reading levels");
		try {
			levelReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

//	/**
//	 * @return The next level of the game.
//	 */
//	public Level getNextLevel() {
//		return tempLevels.getLast();
//	}
	
//	/**
//	 * Returns the previous level by removing the current level from the linked list, then getting the last level from the linked list.
//	 * 
//	 * @return The previous level.
//	 */
//	public Level getPreviousLevel(){
//		
//	}

	/**
	 * Remove the latest level in the levels linked list.
	 */
//	private void removeLevel() {
//		if(!tempLevels.isEmpty()){
//			tempLevels.remove();
//		}else{
//			System.err.println("No levels to remove, this shoudn't happen, meaning the code is broken somewhere.");
//		}
//		
//	}

//	/**
//	 * 
//	 */
//	public void loadLevel() {
//		DataStore.getInstance().levelNumber++;
//
//	}

	/**
	 * Prepare the BufferedReader by finding the start of the level data in the level file.
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
			boolean inItems = false;
			while (!endOfLevel) {//(line = levelReader.readLine()) != null && 
				line = levelReader.readLine();//this or above comments
				if (line.contains("<level>")) {
					inLevel = true;
					level = new Level();
					//

				} else if (line.contains("</level>") && inLevel) {
					inLevel = false;
					levels.add(level);
					endOfLevel = true;
					System.out.println("The following level has been added to the list:\n" + level.toString());
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
				} else if (line.contains("<items>") && inLevel && !inItems){
					inItems = true;
				} else if (line.contains("</items>") && inLevel && inItems){
					inItems = false;
				}
				else if (inSessileSprites) {
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
	
					
					Color textColour = null;
					Color backColour = null;
					try {
					    Field fieldText = Class.forName("java.awt.Color").getField(data[1].trim());
					    Field fieldBack = Class.forName("java.awt.Color").getField(data[2].trim());
					    textColour = (Color)fieldText.get(null);
					    backColour = (Color)fieldBack.get(null);
			
					} catch (NullPointerException e) {
						System.err.println("Colours set to default, cannot read input colours.");
					    textColour = Color.BLACK;
					    backColour = Color.WHITE;
					}catch(Exception e){
						System.err.println("Something went wrong while reading colours from level input file. Default colours used.");
						textColour = Color.BLACK;
					    backColour = Color.WHITE;
					}

					long displayTime = Long.parseLong(data[3].trim());
					long displayDuration = Long.parseLong(data[4].trim());
					int xPosition = Integer.parseInt(data[5].trim());
					int yPosition = Integer.parseInt(data[6].trim());
					//TODO: Add x position, y postion and display time.

					level.addNotification(text, textColour, backColour,displayTime,displayDuration,xPosition,yPosition);
				} else if(inItems){
					String[] data = line.split(",");
					String name = data[0].trim();
					String description = data[1].trim();
					BufferedImage image = DataStore.getInstance().images.getItemImage(data[2]);
					ItemType itemType = ItemType.getItemType(data[3].trim());
					int value = Integer.parseInt(data[4].trim());
					int xCoordinate = Integer.parseInt(data[5].trim());
					int yCoordinate = Integer.parseInt(data[6].trim());
					
					level.addItem(name,description,image,itemType,value,xCoordinate,yCoordinate);
					
				}

			}
			

		} catch (IOException e) {
			//e.printStackTrace();
			System.err.println("IO Exception, must be end of level file. Finished reading");
		//	finishedReading = true;
		}catch(NullPointerException e){
			System.err.println("End of level file. Finished reading.");
			finishedReading = true;
		}

	}

	/**
	 * Get a level from the levelReader's list of levels.
	 * @param levelNumber The level number to return.
	 * @return The level specified.
	 */
	public Level getLevel(int levelNumber) {
		return levels.get(levelNumber);
	}


}
