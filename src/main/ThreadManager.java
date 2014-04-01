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
		boolean gameStarted = false;
		while(true){
			if(!gameStarted && DataStore.getInstance().gameState == State.PLAYING){
				System.out.println("Started game thread.");
				(new Thread(new Game())).start();
			}
		}
	}

	/**
	 * 
	 */
	public ThreadManager() {
		DataStore.getInstance();
		DataStore.getInstance().setInitialFields();
		(new Thread(new Window())).start();
		System.out.println("Started Window Thread.");
//		(new Thread(new Game())).start();
		(new Thread(new SoundManager())).start();
		System.out.println("Started SoundManager Thread.");
	}
}
