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
	private Camera camera;
	private int periodsSinceFire;
	private int baseSpeed;
	private LinkedList<Integer> buttons;
	private LinkedList<Enemy> enemies;
	private LinkedList<Bullet> bullets;
	private LinkedList<SessileSprite> sessileSprites;
	private Player player;
	private BufferedImage[] playerImages;
	private BufferedImage[] bulletImages;
	private BufferedImage[] enemyImages;
	private BufferedImage[] sessileSpriteImages;
	private Image fullHeart, emptyHeart;
	private boolean gameOver;
	private int pace;
	private int width, height; 
	private Random rand;
	private int maxEnemies;

	public Game(int panelWidth, int panelHeight) {
		sessileSprites = new LinkedList<SessileSprite>();
		playerImages = new BufferedImage[17];
		bulletImages = new BufferedImage[1];
		enemyImages = new BufferedImage[17];
		sessileSpriteImages = new BufferedImage[4];
		buttons = new LinkedList<Integer>();
		rand = new Random();
		width = panelWidth;
		height = panelHeight;
		periodsSinceFire = 0;
		baseSpeed = 5;
		pace = 5;
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
		camera = new Camera(width, height, player);
		for(int i = 0; i < sessileSpriteImages.length; i++){
		SessileSprite tree = new SessileSprite(400,(400*i), sessileSpriteImages);
		tree.setAbsoluteX(400);
		tree.setAbsoluteY(64*i);
		sessileSprites.add(tree);
		}
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
			player.setDirection(Direction.NORTHWEST);
		else if (buttons.contains(KeyEvent.VK_UP)
				&& buttons.contains(KeyEvent.VK_RIGHT))
			player.setDirection(Direction.NORTHEAST);
		else if (buttons.contains(KeyEvent.VK_DOWN)
				&& buttons.contains(KeyEvent.VK_LEFT))
			player.setDirection(Direction.SOUTHWEST);
		else if (buttons.contains(KeyEvent.VK_DOWN)
				&& buttons.contains(KeyEvent.VK_RIGHT))
			player.setDirection(Direction.SOUTHEAST);
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
			camera.setSpeed(0);
			camera.setMovingCamera(false);
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
		BufferedImage spriteSheet = getImage("res/img/SpriteSheet1.png");
		fullHeart = new ImageIcon("res/img/Heart01.png").getImage();
		emptyHeart = new ImageIcon("res/img/Heart02.png").getImage();
		new ImageIcon("res/img/camera.png").getImage();
		bulletImages[0] = getImage("res/img/FishSkeleton.png");
		enemyImages[0] = getImage("res/img/Enemy00.png");
		sessileSpriteImages[0] = spriteSheet.getSubimage(0,0,64,64);
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
		camera.drawCamera(player.getDirection(), g, this);
		for (Enemy enemy : enemies) {
			enemy.draw(g, 0);
		}
		for(SessileSprite s: sessileSprites){
			System.out.println(s.getX() + ", " + s.getY());
			s.setX(s.getAbsoluteX()-camera.getCurrentLeft());
			s.setY(s.getAbsoluteY()-camera.getCurrentTop());
			s.draw(g, 0);
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
			player.setPlayerMoving(camera.getMoveX(),camera.getMoveY());
			player.run2();	
			LinkedList<Enemy> removeEnemies = new LinkedList<Enemy>();
			for (Enemy enemy : enemies) {
				enemy.run();
				enemy.collideWalls(width, height, camera);
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
			for (SessileSprite s: sessileSprites){
				if (player.collide(s.getRect())){
					player.setSpeed(0);
				}
			}
			LinkedList<Bullet> removeBullets = new LinkedList<Bullet>();
			for (Bullet bullet : bullets) {
				bullet.run();
				bullet.collideWalls(width, height, camera);
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
