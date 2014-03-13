package Test;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GUI extends JFrame implements ActionListener {

private JButton btnStart,btnAbout, btnSettings, btnQuit;
private GridLayout layButtons;
private JPanel panButtons, panLeft, panRight, panCenter,  panTop, panBottom;

	public GUI(){
		
		btnStart = new JButton("Start");
		btnStart.setPreferredSize(new Dimension(150, 40));
		btnSettings = new JButton("Settings");
		btnSettings.setPreferredSize(new Dimension(150, 40));
		btnAbout = new JButton("About");
		btnAbout.setPreferredSize(new Dimension(150, 40));
		btnQuit  = new JButton("Quit");
		btnQuit.setPreferredSize(new Dimension(150, 40));
		
		panButtons = new JPanel();
		panLeft = new JPanel();
		panRight = new JPanel();
		panCenter = new JPanel();
		panTop = new JPanel();
		panBottom = new JPanel();
		panButtons.setLayout(new BorderLayout());
		
		layButtons = new GridLayout(3,1);
		panLeft.add(btnStart);
		panRight.add(btnQuit);
		panTop.add(btnAbout);
		panBottom.add(btnSettings);
		panCenter.setLayout(new GridLayout(1, 2));
		panCenter.add(panTop);
		panCenter.add(panBottom);
		panTop.setBackground(Color.BLUE);
		panBottom.setBackground(Color.BLUE);
		panButtons.setLayout(layButtons);
		panButtons.add(panLeft);
		panButtons.add(panCenter);
		panButtons.add(panRight);
		panButtons.setBackground(Color.BLUE);
		add(panButtons);
		
		
		
	}
	public static void main(String[] args) {
		DisplayMode displayMode = new DisplayMode(800, 600, 16,
				DisplayMode.REFRESH_RATE_UNKNOWN);
		GUI main = new GUI();
		//main.run(displayMode);
		main.runwindowed(displayMode);

	}

	public void run(DisplayMode displayMode){
		setBackground(Color.PINK);
		setForeground(Color.WHITE);
		setFont(new Font("Arial",Font.PLAIN,24));
		
		Screen screen = new Screen();
		try{
			screen.setFullScreen(displayMode, this);
			try{
				Thread.sleep(5000);
			}catch(Exception e){}
			}finally{
				screen.restoreScreen();
				
			}
		}
	
	public void runwindowed(DisplayMode displayMode){
		
		setForeground(Color.WHITE);
		setBackground(Color.BLUE);
		setFont(new Font("arial", Font.PLAIN, 24));
		
		Screen screen = new Screen();
		try{
			screen.setWindowedMode(displayMode, this);
		}catch(Exception e) {}
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void paint(Graphics g){
		if(g instanceof Graphics2D){
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		}
		g.drawString("Welcome to Operation Penguin Fish", 200, 200);
	}

	public void actionPerformed(ActionEvent e) {
		
	}

}
