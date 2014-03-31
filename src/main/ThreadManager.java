package main;

import sound.SoundManager;
import graphics.Window;

/**
 * Starts the Window, Game and SoundManager threads. Sets up the DataStore
 * class, to communicate between Threads.
 * 
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 *         @since 25th March 2014
 * 
 */
public class ThreadManager {
	/**
	 * @param args
	 *            No arguments are used.
	 */
	public static void main(String[] args) {
		new ThreadManager();
	}

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
}
