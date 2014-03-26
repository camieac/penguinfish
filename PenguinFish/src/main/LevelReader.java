package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

public class LevelReader {
	private BufferedReader levelReader;
	private ArrayList<Integer> curLevel = new ArrayList<Integer>();
	private String levelFile;

	public LevelReader() {
		levelFile = "res/txt/levels.txt";
		try {
			levelReader = new BufferedReader(new InputStreamReader(
					new DataInputStream(new FileInputStream(levelFile))));
		}catch(Exception e){
			System.out.println("Level file not found: " + levelFile);
		}
		prepareReader();
	}

	public void readLevel() {
		String line;

			try {
				while ((line = levelReader.readLine()) != null){
					System.out.println("Line: " + line);
					if(line.contains("startLevel:")){
						Level level = new Level();
						level.setName(levelReader.readLine());
						level.setLevelID();
						System.out.println(level.getName());
					}
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		

		

	}

	public void prepareReader() {
		String line;
		try {
			while ((line = levelReader.readLine()) != null
					&& !line.equals("startLevel:")) {
				levelReader.readLine();
			}
		} catch (Exception e) {

			System.out.println("Error reading level file");

		}
	}

	public static void main(String[] args) {
		LevelReader levelReader = new LevelReader();
		levelReader.prepareReader();
		levelReader.readLevel();
		
		System.out.println(levelReader.curLevel);
	}
}
