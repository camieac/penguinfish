package graphics;

//import java.awt.BorderLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.DataStore;
import main.State;

/**
 * The window that contains the game.
 * 
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * 
 */
@SuppressWarnings("serial")
public class Window extends JFrame implements Runnable, ActionListener,
		KeyListener {
	// JPanel buttons;

	protected Camera camera;
	protected boolean fullscreen;
	// protected JButton helpButton;
	protected boolean playingScreenLoaded;
	// protected JButton startButton;
	protected boolean startingAnimationLoaded;
	protected boolean startMenuLoaded;

	protected JPanel panelViewer;
	/* The frame can only display one card at a time. */
	protected JPanel startingAnimationCard;
	protected JPanel startMenuCard;
	protected JPanel gamePlayCard;
	protected JPanel helpMenuCard;

	JButton startButton;
	JButton helpButton;
	JButton backToStartButton;
	ImageIcon logo;

	protected Toolkit tk;
	private boolean helpMenuLoaded;

	protected final static String HELPTEXT = "Welcome to PenguinFish Help Page\n"
			+ "Unfortunately no help is available at this present time. Please try again next week.";

	/**
	 * Sets up the window to 512*512, the standard width and height for this
	 * game.
	 */
	public Window() {
		super();
		panelViewer = new JPanel(new CardLayout());

		// Setup cross-card button
		backToStartButton = new JButton("Back");
		backToStartButton.addActionListener(this);
		// Setup cards here
		setupStartingAnimationCard();
		setupStartMenuCard();
		setupGamePlayCard();
		setupHelpMenuCard();

		// Give each card a unique identifier.
		startingAnimationCard.setName("Starting Animation");
		startMenuCard.setName("Start Menu");
		gamePlayCard.setName("Game Play");
		helpMenuCard.setName("Help Menu");

		// add the cards to the CardLayout Frame
		panelViewer.add(startingAnimationCard, startingAnimationCard.getName());
		panelViewer.add(startMenuCard, startMenuCard.getName());
		panelViewer.add(gamePlayCard, gamePlayCard.getName());
		panelViewer.add(helpMenuCard, helpMenuCard.getName());

		tk = Toolkit.getDefaultToolkit();

		startMenuLoaded = false;
		startingAnimationLoaded = false;
		playingScreenLoaded = false;
		helpMenuLoaded = false;
		fullscreen = false;

		this.setResizable(true);
		this.setSize(DataStore.getInstance().panelWidth,
				DataStore.getInstance().panelHeight);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panelViewer);

	}

	private void setupHelpMenuCard() {
		helpMenuCard = new JPanel();
		helpMenuCard.add(new JTextArea(HELPTEXT));
		helpMenuCard.add(backToStartButton);
		// helpMenuCard.addKeyListener(this);

	}

	private void setupStartingAnimationCard() {
		startingAnimationCard = new JPanel();
		JLabel lbl = new JLabel("Starting animation goes here");

		startingAnimationCard.add(lbl);
	}

	private void setupStartMenuCard() {
		startMenuCard = new JPanel(new GridLayout(2, 1));
		JPanel buttons = new JPanel();
		startButton = new JButton("Start Game");
		helpButton = new JButton("Help");

		// Logo setup
		logo = new ImageIcon(DataStore.getInstance().images.getTitleImage(1));
		JLabel lblLogo = new JLabel(logo);
		buttons.setLayout(new GridLayout(2, 2));
		buttons.add(startButton);
		buttons.add(helpButton);

		startMenuCard.add(lblLogo);
		startMenuCard.add(buttons);

		startButton.addActionListener(this);
		helpButton.addActionListener(this);
		this.setVisible(true);
	}

	private void setupGamePlayCard() {
		camera = new Camera(0, 0, DataStore.getInstance().panelWidth,
				DataStore.getInstance().panelHeight);
		// camera = new Camera(0, 0, 512, 512);
		gamePlayCard = new JPanel();
		gamePlayCard.setLayout(new BorderLayout());
		gamePlayCard.add(camera, BorderLayout.CENTER);
		gamePlayCard.add(backToStartButton);
		// panelViewer.addKeyListener(this);
		// camera.addKeyListener(this);
		// camera.setFocusable(true);
		setFocusable(true);
		addKeyListener(this);
		gamePlayCard.addKeyListener(this);

		gamePlayCard.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			DataStore.getInstance().gameState = State.PLAYING;
		} else if (e.getSource() == helpButton) {
			DataStore.getInstance().gameState = State.HELPMENU;
		} else if (e.getSource() == backToStartButton) {
			DataStore.getInstance().gameState = State.STARTMENU;
			System.out.println("Return to Start Menu");
		}
	}

	protected void formKeyPressed(KeyEvent evt) {
		System.out.println("Key pressed");
		camera.keyPressed(evt);
	}

	protected void formKeyReleased(KeyEvent evt) {
		camera.keyReleased(evt);
		if (evt.getKeyCode() == KeyEvent.VK_G) {
			if (!fullscreen) {
				// goFullScreen();
				setFullscreen(true);
			} else {
				// returnFullScreen();
				setFullscreen(false);
			}

		}
	}

	/**
	 * @param fullscreen True if full screen, false if not.
	 */
	public void setFullscreen(boolean fullscreen) {
		if (fullscreen == this.fullscreen)
			return;

		boolean visible = isVisible();
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();

		if (fullscreen) {
			this.fullscreen = true;
			setVisible(false);
			dispose();
			setUndecorated(true);
			setResizable(false);
			gd.setFullScreenWindow(this);
			DataStore.getInstance().panelHeight = gd.getFullScreenWindow()
					.getHeight();
			DataStore.getInstance().panelWidth = gd.getFullScreenWindow()
					.getWidth();
			camera.setHeight(DataStore.getInstance().panelHeight);
			camera.setWidth(DataStore.getInstance().panelWidth);
		} else {
			this.fullscreen = false;
			gd.setFullScreenWindow(null);
			setVisible(false);
			dispose();
			setUndecorated(false);
			setResizable(true);
			setSize(1280, 750);
			DataStore.getInstance().panelHeight = 750;
			DataStore.getInstance().panelWidth = 1280;
			camera.setHeight(DataStore.getInstance().panelHeight);
			camera.setWidth(DataStore.getInstance().panelWidth);
		}

		setVisible(visible);
	}

	/**
	 * @param cardName
	 *            The name of the card to change to, the names of the cards are
	 *            set up in the constructor.
	 */
	public void changeCard(String cardName) {

		CardLayout cl = (CardLayout) (panelViewer.getLayout());
		cl.show(panelViewer, cardName);
	}

	@Override
	public void run() {
		while (true) {
			switch (DataStore.getInstance().gameState) {
			case PLAYING:
				if (!playingScreenLoaded) {
					startMenuLoaded = false;
					helpMenuLoaded = false;
					this.setTitle("Operation Penguin Fish: "
							+ gamePlayCard.getName());
					changeCard(gamePlayCard.getName());

					playingScreenLoaded = true;
				}
				camera.repaint();
				camera.processKeys();
				repaint();
				// System.out.println("processing");
				break;
			case STARTMENU:
				if (!startMenuLoaded) {
					helpMenuLoaded = false;
					this.setTitle("Operation Penguin Fish: "
							+ startMenuCard.getName());
					changeCard(startMenuCard.getName());

					startMenuLoaded = true;
				}

				break;
			case STARTINGANIMATION:
				if (!startingAnimationLoaded) {
					startMenuLoaded = false;
					helpMenuLoaded = false;
					this.setTitle("Operation Penguin Fish: "
							+ startingAnimationCard.getName());
					changeCard(startingAnimationCard.getName());
					startingAnimationLoaded = true;
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DataStore.getInstance().gameState = State.STARTMENU;
					// System.out.println("pause over, swihcing to start menu");
				}
				break;
			case HELPMENU:
				if (!helpMenuLoaded) {
					startMenuLoaded = false;
					changeCard(helpMenuCard.getName());
					// System.out.println("Card changed to helpmenu");
					helpMenuLoaded = true;
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

	@Override
	public void keyPressed(KeyEvent evt) {
		System.out.println("KEY PRESSED");
		formKeyPressed(evt);

	}

	@Override
	public void keyReleased(KeyEvent evt) {
		formKeyReleased(evt);

	}

	@Override
	public void keyTyped(KeyEvent evt) {

	}
}