package PenguinFish;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

class Game extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1L;
	private Background background;
	private int periodsSinceFire;
	private int baseSpeed;
	private LinkedList<Integer> buttons;
	private LinkedList<Enemy> enemies;
	private LinkedList<Bullet> bullets;
	private Player player;
	private BufferedImage[] playerImages;
	private BufferedImage[] bulletImages;
	private BufferedImage[] enemyImages;
	private Image fullHeart, emptyHeart;
	private boolean gameOver;
	private int pace;
	private int width, height; 
	private Random rand;
	private int maxEnemies;

	public Game(int panelWidth, int panelHeight) {
		background = new Background(panelWidth, panelHeight);
		playerImages = new BufferedImage[17];
		bulletImages = new BufferedImage[1];
		enemyImages = new BufferedImage[17];
		buttons = new LinkedList<Integer>();
		rand = new Random();
		width = panelWidth;
		height = panelHeight;
		periodsSinceFire = 0;
		baseSpeed = 5;
		pace = 6;
		maxEnemies = 0;
		loadImages();
		createGame();
	}

	private void createGame() {
		enemies = new LinkedList<Enemy>();
		
		for(int i =0; i< maxEnemies; i++){
			enemies.add(new Enemy(rand.nextInt(width), rand.nextInt(height), Direction.getRandom(), enemyImages));
		}
		
		player = new Player(0, 0, Direction.SOUTH, playerImages);
		player.resetLocation(width, height);
		bullets = new LinkedList<Bullet>();
		player.setSpeed(baseSpeed);
		gameOver = false;
		repaint();
	}

	private void difficultyWait() {
		try {
			switch (pace) {
			case 1:
				Thread.sleep(70);
				break;
			case 2:
				Thread.sleep(60);
				break;
			case 3:
				Thread.sleep(50);
				break;
			case 4:
				Thread.sleep(40);
				break;
			case 5:
				Thread.sleep(20);
				break;
			case 6:
				Thread.sleep(10);
			}
		} catch (InterruptedException ex) {
		}
	}

	public void keyPressed(KeyEvent e) {
		buttons.add(e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
		if (buttons.contains(e.getKeyCode()))
			buttons.remove(buttons.indexOf(e.getKeyCode()));			
	}

	private void processKeys() {
		if (buttons.contains(KeyEvent.VK_UP)
				&& buttons.contains(KeyEvent.VK_LEFT))
			player.setDirection(Direction.NW);
		else if (buttons.contains(KeyEvent.VK_UP)
				&& buttons.contains(KeyEvent.VK_RIGHT))
			player.setDirection(Direction.NE);
		else if (buttons.contains(KeyEvent.VK_DOWN)
				&& buttons.contains(KeyEvent.VK_LEFT))
			player.setDirection(Direction.SW);
		else if (buttons.contains(KeyEvent.VK_DOWN)
				&& buttons.contains(KeyEvent.VK_RIGHT))
			player.setDirection(Direction.SE);
		else if (buttons.contains(KeyEvent.VK_UP))
			player.setDirection(Direction.NORTH);
		else if (buttons.contains(KeyEvent.VK_DOWN))
			player.setDirection(Direction.SOUTH);
		else if (buttons.contains(KeyEvent.VK_LEFT))
			player.setDirection(Direction.WEST);
		else if (buttons.contains(KeyEvent.VK_RIGHT))
			player.setDirection(Direction.EAST);
		else{
			player.setSpeed(0);
			background.setSpeed(0);
			background.setMovingBackground(false);
		}
		
		if (buttons.contains(KeyEvent.VK_F)) addBullet();

	}

	public void addBullet() {
		if (periodsSinceFire >= 3) {
			Bullet b = new Bullet(player.getX()+player.getWidth()/2, player.getY()+player.getHeight()/2,
					player.getDirection(), bulletImages);
			bullets.add(b);
			b.rotateBullet(0);
			periodsSinceFire = 0;
		}
	}

	private void loadImages() {
		int numPlayerImages = 13;
		for (int i = 0; i < numPlayerImages; i++) {
			playerImages[i] = getImage("res/img/Character" + i + ".png");
		}
		fullHeart = new ImageIcon("res/img/Heart01.png").getImage();
		emptyHeart = new ImageIcon("res/img/Heart02.png").getImage();
		new ImageIcon("res/img/Background.png").getImage();
		bulletImages[0] = getImage("res/img/FishSkeleton.png");
		enemyImages[0] = getImage("res/img/Enemy00.png");
	}

	private BufferedImage getImage(String image) {
		BufferedImage map = null;
		try {
			map = ImageIO.read(new File(image));
			return toCompatibleImage(map);
		} catch (IOException e) {
			return map;
		}
	}

	private BufferedImage toCompatibleImage(BufferedImage image) {
		GraphicsConfiguration gfx_config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		if (image.getColorModel().equals(gfx_config.getColorModel()))return image;
		BufferedImage new_image = gfx_config.createCompatibleImage(image.getWidth(), image.getHeight(), image.getTransparency());
		Graphics2D g2d = (Graphics2D) new_image.getGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		return new_image;
	}

	public void paintComponent(Graphics g) {
		setOpaque(false);
		super.paintComponent(g);
		background.drawBackground(player.getDirection(), g, this);
		for (Enemy enemy : enemies) {
			enemy.draw(g, 0);
		}
		player.drawPlayer(g);
		g.setColor(Color.black);
		g.drawString("Avoid the red enemy!", 10, 10);
		g.drawString("Pace: " + pace, width / 2, 10);
		g.drawString("Life: ", width - 100, 10);
		if (player.getHealth() == 100) {
			g.drawImage(fullHeart, width - 75, 1, this);
		}
		if (player.getHealth() < 100 && player.getHealth() >= 90) {
			g.drawImage(emptyHeart, width - 75, 1, this);
		}
		if (player.getHealth() >= 80) {
			g.drawImage(fullHeart, width - 65, 1, this);
		}
		if (player.getHealth() < 80 && player.getHealth() >= 70) {
			g.drawImage(emptyHeart, width - 65, 1, this);
		}
		if (player.getHealth() >= 60) {
			g.drawImage(fullHeart, width - 55, 1, this);
		}
		if (player.getHealth() < 60 && player.getHealth() >= 50) {
			g.drawImage(emptyHeart, width - 55, 1, this);
		}
		if (player.getHealth() >= 40) {
			g.drawImage(fullHeart, width - 45, 1, this);
		}
		if (player.getHealth() < 40 && player.getHealth() >= 30) {
			g.drawImage(emptyHeart, width - 45, 1, this);
		}
		if (player.getHealth() >= 20) {
			g.drawImage(fullHeart, width - 35, 1, this);
		}
		if (player.getHealth() < 20 && player.getHealth() >= 10) {
			g.drawImage(emptyHeart, width - 35, 1, this);
		}
		if (player.getHealth() < 10) {
			g.setColor(Color.red);
			g.drawString("DEAD!", width - 65, 10);
		}
		for (Bullet b : bullets) {
			b.draw(g, 0);
		}
		if (gameOver) {
			player.draw(g, 8);
			g.setColor(Color.black);
			g.drawString("Game Over", (width / 2) - 25, height / 2);
		}
	}	

	public void run() {
		while (!gameOver) {
			periodsSinceFire++;
			processKeys();
			player.run();	
			LinkedList<Enemy> removeEnemies = new LinkedList<Enemy>();
			for (Enemy enemy : enemies) {
				enemy.run();
				enemy.collideWalls(width, height);
				if(enemy.collide(player.getRect())){
					player.damage(10);
				}
				for(Enemy e : enemies){
					if(!e.equals(enemy)){
						enemy.collide(e.getRect());
					}
				}
				if(enemy.getDead()){
					removeEnemies.add(enemy);
				}	
			}
			
			LinkedList<Bullet> removeBullets = new LinkedList<Bullet>();
			for (Bullet bullet : bullets) {
				bullet.run();
				bullet.collideWalls(width, height);
				//if(bullet.collide(player.getRect())){
				//	player.damage(10);
				//}
				for(Enemy e : enemies){
					bullet.collide(e.getRect());
					bullet.setDead(true);
					e.damage(10);					
				}
				if(bullet.getDead()){
					removeBullets.add(bullet);
				}			
			}
			bullets.removeAll(removeBullets);
			enemies.removeAll(removeEnemies);				
			difficultyWait();			
			repaint();
		}
	}

	public void incrementPace(int i) {
		pace += i;		
	}
}
