package main;

import graphics.Window;
import sound.SoundManager;

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
			//System.out.println(DataStore.getInstance().gameState + ",   " + gameStarted);
			
			if(!gameStarted && DataStore.getInstance().gameState == State.PLAYING){
				gameStarted = true;
				//old set game fields
				(new Thread(new Game())).start();
			}
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 */
	public ThreadManager() {
		DataStore.getInstance();
		DataStore.getInstance().setInitialFields();
		DataStore.getInstance().setGameFields();
		(new Thread(new Window())).start();
		System.out.println("Started Window Thread.");
//		(new Thread(new Game())).start();
//		(new Thread(new SoundManager())).start();
//		System.out.println("Started SoundManager Thread.");
	}
}
