package main;

import sound.SoundManager;
import graphics.Window;

/**
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson, Stuart Thain
 *
 */
public class ThreadManager {
	/**
	 * 
	 */
	public ThreadManager() {
		DataStore.getInstance();
		DataStore.getInstance().setEverything();
		(new Thread(new Window())).start();
		(new Thread(new Game())).start();
		(new Thread(new SoundManager())).start();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ThreadManager();
	}
}
