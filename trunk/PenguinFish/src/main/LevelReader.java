package main;

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

import sprites.SessileSprite;

public class LevelReader {
	private FileOutputStream fos;
	private ObjectOutputStream oos;
	FileInputStream fis;
	ObjectInputStream ois;
	private BufferedReader levelReader;
	private String levelFile;
	private ArrayList<Level> tempLevels;

	public LevelReader() {
		try {
			fos = new FileOutputStream("res/temp/tempdata.ser");
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			oos = new ObjectOutputStream(fos);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			fis = new FileInputStream("res/temp/tempdata.ser");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ois = new ObjectInputStream(fis);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		levelFile = "res/txt/levels.txt";
		tempLevels = new ArrayList<Level>();
		try {
			levelReader = new BufferedReader(new FileReader(levelFile));
		} catch (Exception e) {
			System.out.println("Level file not found: " + levelFile);
		}
		prepareReader();
	}

	public void readLevel() {
		String line;
		boolean endOfLevel = false;
		try {
			Level level = new Level();
			boolean inLevel = false;
			while ((line = levelReader.readLine()) != null && !endOfLevel) {
				
				
				//System.out.println("Line: " + line);
				if (line.contains("startLevel:")) {
					inLevel = true;
					level = new Level();
					level.setName(levelReader.readLine());
					level.setLevelID(Integer.parseInt(levelReader.readLine()));
					// DataStore.getInstance().levels.add(level);
					/* sessile sprites */

					// System.out.println("Level Name set to: " +
					// level.getName());
				}
				if (line.contains("endLevel:") && inLevel) {
					inLevel = false;
					// tempLevels.add(level.getLevelID(),level);
					oos.writeObject(level);
					endOfLevel = true;
					System.out.println(level.toString());
					level = new Level();
					// System.exit(0);
				}
				if (line.contains("startSessileSprite:") && inLevel) {

					String[] data = levelReader.readLine().split(",");
					SessileSprite s = new SessileSprite(
							Integer.parseInt(data[0]),
							Integer.parseInt(data[1]),
							Integer.parseInt(data[2]));
					level.addSessileSprite(s);
					System.out.println(s.toString());

				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Level getNextLevel() {
		readLevel();
		try {
			return (Level) ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			System.err.println("No more levels :-(");
			return null;
		}
	}

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

//	public static void main(String[] args) {
//		DataStore.getInstance();
//		DataStore.getInstance().setEverything();
//		LevelReader levelReader = new LevelReader();
//		levelReader.readLevel();
//		System.out.println("\n\n\n");
//		levelReader.readLevel();
//
//		LevelReader serial = new LevelReader();
//		System.out.println(serial.getNextLevel().toString());
//		System.out.println(serial.getNextLevel().toString());
//		System.out.println(serial.getNextLevel().toString());
//		// System.out.println(levelReader.curLevel);
//	}

}
