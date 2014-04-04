package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import main.DataStore;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JFlashPlayer;

/**
 * The window that contains the game.
 * 
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * 
 */
public class Window implements Runnable {
	protected Camera camera;
	
	
	private boolean fullscreen;
	private JFrame frame;
	private boolean startMenuLoaded;
	private boolean startingAnimationLoaded;
	/**
	 * Sets up the window to 512*512, the standard width and height for this game.
	 */
	public Window() {
		startMenuLoaded = false;
		startingAnimationLoaded = false;
		frame = new JFrame("Penguin Fish");
		//The game initial starts not in fullscreen mode.
		fullscreen = false;
		frame.setResizable(false);
		//The width and height are now assigned to the frame.
		frame.setSize(DataStore.getInstance().panelWidth, DataStore.getInstance().panelHeight);
		//The frame is set to exit the application when the close button is pressed.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//The frame is now fully configured, so is made visible.
		frame.setVisible(true);

	}
	private void loadStartMenu() {
		//A key listener is added to detect button presses.
				frame.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent evt) {
						formKeyPressed(evt);
					}

					public void keyReleased(KeyEvent evt) {
						formKeyReleased(evt);
					}
				});
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout());
		JButton startButton = new JButton("Start Game");
		JButton helpButton = new JButton("Help");
		buttons.add(startButton);
		buttons.add(helpButton);
		startButton.setMaximumSize(new Dimension(100,20));
		frame.add(buttons,BorderLayout.CENTER);
		frame.getContentPane().add(startButton, BorderLayout.LINE_START);
	}
//	public void startGame(){
//		
//	}
	private void returnFullScreen() {
		if(fullscreen){
			int panWidth = 512;
			int panHeight = 512;
		frame.setLocation((DataStore.getInstance().panelWidth/2)-panWidth/2, (DataStore.getInstance().panelHeight/2)-panHeight/2);
		DataStore.getInstance().panelWidth = panWidth;
		DataStore.getInstance().panelHeight = panHeight;
		frame.setSize(DataStore.getInstance().panelWidth, DataStore.getInstance().panelHeight);
		fullscreen = false;
		camera.setWidth(DataStore.getInstance().panelWidth);
		camera.setHeight(DataStore.getInstance().panelHeight);
		DataStore.getInstance().world.setupDefaultBoundaries();

		}
		
	}
	private void goFullScreen(){
		
		if(!fullscreen){
		Toolkit tk = Toolkit.getDefaultToolkit();  
		DataStore.getInstance().panelWidth = ((int) tk.getScreenSize().getWidth());  
		DataStore.getInstance().panelHeight = ((int) tk.getScreenSize().getHeight());  
		frame.setSize(DataStore.getInstance().panelWidth, DataStore.getInstance().panelHeight);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);  
		camera.setWidth(DataStore.getInstance().panelWidth);
		camera.setHeight(DataStore.getInstance().panelHeight);
		DataStore.getInstance().world.setupDefaultBoundaries();
		
		
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
			switch(DataStore.getInstance().gameState){
			case PLAYING:
				camera.repaint();
				camera.processKeys();
				break;
			case STARTMENU:
				if(!startMenuLoaded){
					loadStartMenu();
					startMenuLoaded = true;
				}
				break;
			case STARTINGANIMATION:
				if(!startingAnimationLoaded){
					loadStartingAnimation();
					startingAnimationLoaded = true;
				}
				break;
			default:
				break;
			}
				
			
			
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				// Dont do anything here at the moment
			}
		}

	}
	private void loadStartingAnimation() {
//		NativeSwing.initialize();
		System.out.println("loading starting animation");
//		NativeComponentWrapper ncw = new NativeComponentWrapper(frame);
//		frame.add(ncw.createEmbeddableComponent(null));
		NativeInterface.open();
		 SwingUtilities.invokeLater(new Runnable() {
			 public void run() {
				 DataStore.getInstance().panelHeight = 750;
				 DataStore.getInstance().panelWidth = 1280;
				 frame.setSize(DataStore.getInstance().panelWidth, DataStore.getInstance().panelHeight);
				 JFlashPlayer flashPlayer = new JFlashPlayer();
					flashPlayer.load(Window.class,"res/ani/bubbles.swf");
					flashPlayer.play();
					frame.getContentPane().add(flashPlayer, BorderLayout.CENTER);
					System.out.println("Animation Loaded"); 
					
			 }
		 });
		 NativeInterface.runEventPump();
		
		
	}
}