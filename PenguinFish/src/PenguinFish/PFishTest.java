package PenguinFish;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PFishTest extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private PFishTestGame panel = null; // This is the panel for the game class

	private PFishTestGame getPanel() {
		if (panel == null) {
			panel = new PFishTestGame();
		}
		return panel;
	}
        
        int panelwidth = 500;
        int panelheight = 500;

/**
* This is the default constructor
*/
	public PFishTest() {
		super();
		initialise();
		this.addKeyListener(new KeyAdapter() {		// Listeners for the keyboard
			public void keyPressed(KeyEvent evt) {	// Method for the key pressed
				formKeyPressed(evt);
			}
			public void keyReleased(KeyEvent evt) {	// Method for the key released
				formKeyReleased(evt);
			}
		});
	}

	private void formKeyPressed(KeyEvent evt) {		// Sends the key pressed to the game class
		panel.keyPressed(evt);
	}

	private void formKeyReleased(KeyEvent evt) {	// Sends the key released to the game class
		panel.keyReleased(evt);
	}

	private void initialise() {
		this.setResizable(false);
		this.setBounds(new Rectangle(312, 184, panelwidth, panelheight)); // Position on the desktop
		this.setMinimumSize(new Dimension(panelwidth, panelheight));
		this.setMaximumSize(new Dimension(panelwidth, panelheight));
		this.setContentPane(getJContentPane());
		this.setTitle("Balls");
	}


// This method initialises jContentPane
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PFishTest thisClass = new PFishTest();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}
}