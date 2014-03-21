package gui;

import javax.swing.*;

import main.Game;

import java.awt.*;
import java.awt.event.*;

public class GUI {
	int panelWidth, panelHeight;
	protected Game game;

	public GUI() {
		panelWidth = 500;
		panelHeight = 500;
		JFrame frame = new JFrame("Penguin Fish");	
		frame.setResizable(false);		
		game = new Game(panelWidth, panelHeight);
		frame.getContentPane().add(game, BorderLayout.CENTER);
		frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				formKeyPressed(evt);
			}
			public void keyReleased(KeyEvent evt) {
				formKeyReleased(evt);
			}
		});
		frame.pack();
		frame.setSize(panelWidth,panelHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);	
		
	}

	protected void formKeyPressed(KeyEvent evt) {
		game.keyPressed(evt);
	}

	protected void formKeyReleased(KeyEvent evt) {
		game.keyReleased(evt);
	}

	public static void main(String[] args) {
		GUI g = new GUI();
		g.game.run();
	}
}