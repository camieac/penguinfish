package graphics;

import javax.swing.*;

import main.DataStore;
import main.Game;

import java.awt.*;
import java.awt.event.*;

/**
 * The window that contains the game.
 * 
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * 
 */
public class Window implements Runnable {
	protected Camera camera;
	int panelWidth, panelHeight;
	private boolean fullscreen;
	private JFrame frame;
	/**
	 * Sets up the window to 512*512, the standard width and height for this game.
	 */
	public Window() {
		//The title of the window is set.
		frame = new JFrame("Penguin Fish");
		//The game initial starts not in fullscreen mode.
		fullscreen = false;
		//The default width and height are set to 512 pixels.
		panelWidth = 512;
		panelHeight = 512;
		//The frame is not resizable by default.
		frame.setResizable(false);
		//The camera is set up with the width and height of the window.
		camera = new Camera(0, 0, panelWidth, panelHeight);
		//The camera is added to the centre of the window.
		frame.getContentPane().add(camera, BorderLayout.CENTER);
		//A key listener is added to detect button presses.
		frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				formKeyPressed(evt);
			}

			public void keyReleased(KeyEvent evt) {
				formKeyReleased(evt);
			}
		});
		//frame.pack();
		//The width and height are now assigned to the frame.
		frame.setSize(panelWidth, panelHeight);
		//The frame is set to exit the application when the close button is pressed.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//The frame is now fully configured, so is made visible.
		frame.setVisible(true);

	}
	private void returnFullScreen() {
		if(fullscreen){
		panelWidth = 512;
		panelHeight = 512;
		frame.setSize(panelWidth, panelHeight);
		fullscreen = false;
		camera.setWidth(panelWidth);
		camera.setHeight(panelHeight);
		}
		
	}
	private void goFullScreen(){
		
		if(!fullscreen){
		Toolkit tk = Toolkit.getDefaultToolkit();  
		panelWidth = ((int) tk.getScreenSize().getWidth());  
		panelHeight = ((int) tk.getScreenSize().getHeight());  
		frame.setSize(panelWidth, panelHeight);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);  
		camera.setWidth(panelWidth);
		camera.setHeight(panelHeight);
		
		fullscreen = true;
		
		}
	}

	protected void formKeyPressed(KeyEvent evt) {
		camera.keyPressed(evt);
	}

	protected void formKeyReleased(KeyEvent evt) {
		camera.keyReleased(evt);
		if(evt.getKeyCode() == KeyEvent.VK_G){
			if(!fullscreen){
				goFullScreen();
			}else{
				returnFullScreen();
			}
			
		}
	}

	@Override
	public void run() {
		while (true) {
			camera.repaint();
			camera.processKeys();
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				// Dont do anything here at the moment
			}
		}

	}
}