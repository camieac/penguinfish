package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import settings.Settings;

public class ThreadManager {
Input input;

public ThreadManager(){
	//input = new Input(Settings.getInstance().getAttribute("windowHeight"));
	
}
	public static void main(String[] args) {
		new ThreadManager();
		String title = Settings.getTitle();
		System.out.println("TITle: " + title);
		// start container
		try {
			AppGameContainer container = new AppGameContainer(new Game("Infiniguin"));
			container.setTitle("Infiniguin");
			container.setDisplayMode(800, 600, false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		//Start Input thread
		

	}

}
