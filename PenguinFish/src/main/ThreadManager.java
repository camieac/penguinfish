package main;

import sound.SoundManager;
import graphics.Window;

public class ThreadManager {
	public ThreadManager() {
		DataStore.getInstance();
		DataStore.getInstance().setEverything();
		(new Thread(new Window())).start();
		(new Thread(new Game())).start();
		(new Thread(new SoundManager())).start();
	}

	public static void main(String[] args) {
		new ThreadManager();
	}
}
