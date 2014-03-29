package graphics;

import javax.swing.*;

import main.DataStore;
import main.Game;

import java.awt.*;
import java.awt.event.*;

public class Window implements Runnable {
	int panelWidth, panelHeight;
	protected Camera camera;

	public Window() {
		panelWidth = 512*3;
		panelHeight = 512*2;
		JFrame frame = new JFrame("Penguin Fish");
		frame.setResizable(false);
		camera = new Camera(0, 0, panelWidth, panelHeight);
		frame.getContentPane().add(camera, BorderLayout.CENTER);
		frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				formKeyPressed(evt);
			}

			public void keyReleased(KeyEvent evt) {
				formKeyReleased(evt);
			}
		});
		frame.pack();
		frame.setSize(panelWidth, panelHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	protected void formKeyPressed(KeyEvent evt) {
		camera.keyPressed(evt);
	}

	protected void formKeyReleased(KeyEvent evt) {
		camera.keyReleased(evt);
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