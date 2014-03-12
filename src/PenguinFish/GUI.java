package PenguinFish;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {
	int panelWidth,panelHeight;
	
	public int getPanelwidth() {
		return panelWidth;
	}

	public void setPanelwidth(int panelwidth) {
		this.panelWidth = panelwidth;
	}

	public int getPanelheight() {
		return panelHeight;
	}

	public void setPanelheight(int panelheight) {
		this.panelHeight = panelheight;
	}

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JMenuBar menuBar = new JMenuBar();
	JMenuItem paceIncreaseMenuItem,paceDecreaseMenuItem, restartMenuItem;
	JMenu paceSubMenu;
	private Game game = null; // This is the panel for the game class

	private Game getPanel() {
		if (game == null) {
			game = new Game(panelWidth,panelHeight);
		}
		return game;
	}
        
      

/**
* This is the default constructor
*/
	public GUI() {
		
		super();
		panelWidth = 1000;
		panelHeight = 1000;
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
		game.keyPressed(evt);
	}

	private void formKeyReleased(KeyEvent evt) {	// Sends the key released to the game class
		game.keyReleased(evt);
	}

	private void initialise() {
		paceSubMenu = new JMenu("Pace");
		this.setResizable(false);
		this.setBounds(new Rectangle(312, 184, panelWidth, panelHeight)); // Position on the desktop
		this.setMinimumSize(new Dimension(panelWidth, panelHeight));
		this.setMaximumSize(new Dimension(panelWidth, panelHeight));
		this.setContentPane(getJContentPane());
		this.setTitle("Penguin Fish");
		
		
		JMenu submenu = new JMenu("Edit");
		submenu.setMnemonic(KeyEvent.VK_S);
		//Pace menu item
		paceIncreaseMenuItem = new JMenuItem("Pace +");
		paceIncreaseMenuItem.addActionListener(this);
		paceIncreaseMenuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_EQUALS, ActionEvent.ALT_MASK));
		paceSubMenu.add(paceIncreaseMenuItem);
		
		paceDecreaseMenuItem = new JMenuItem("Pace -");
		paceDecreaseMenuItem.addActionListener(this);
		paceDecreaseMenuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_MINUS, ActionEvent.ALT_MASK));
		paceSubMenu.add(paceDecreaseMenuItem);
		
		
		submenu.add(paceSubMenu);
		//Restart menu item
		restartMenuItem = new JMenuItem("Restart");
		restartMenuItem.addActionListener(this);
		restartMenuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_2, ActionEvent.ALT_MASK));
		submenu.add(restartMenuItem);

		
		menuBar.add(submenu);
	}


// This method initialises jContentPane
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanel(), BorderLayout.CENTER);
			jContentPane.add(menuBar,BorderLayout.NORTH);
		}
		return jContentPane;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUI thisClass = new GUI();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == restartMenuItem){
			game.reset();
		}else if(e.getSource() == paceIncreaseMenuItem){
			game.incrementPace(1);
		}else if(e.getSource() == paceDecreaseMenuItem){
			game.incrementPace(-1);
		}
		
	}
}