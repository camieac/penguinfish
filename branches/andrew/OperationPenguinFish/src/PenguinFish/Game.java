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

class Game extends JPanel {

	private static final long serialVersionUID = 1L;
	private Camera camera;
	private int periodsSinceFire;
	private int baseSpeed;
	private LinkedList<Integer> buttons;
	private LinkedList<Enemy> enemies;
	private LinkedList<Bullet> bullets;
	private Images images;

	private Destroyer destroyer;
	
	private Player player;
	private Background background;

	private boolean gameOver;
	private int pace;
	private int width, height;
	private Random rand;
	private int maxEnemies;

	public Game(int panelWidth, int panelHeight) {
		images = new Images();
		destroyer = new Destroyer();
		buttons = new LinkedList<Integer>();
		rand = new Random();
		width = panelWidth;
		height = panelHeight;
		periodsSinceFire = 0;
		baseSpeed = 5;
		pace = 5;
		maxEnemies = 10;
		createGame();

	}

	private void createGame() {
		background = new Background(images.getMapImage(0));
		background.createSessileSprites();
		enemies = new LinkedList<Enemy>();
		createEnemies();
		player = new Player(0, 0, Direction.SOUTH, images.getPlayerImages());

		player.resetLocation(width, height);
		bullets = new LinkedList<Bullet>();
		player.setSpeed(baseSpeed);
		gameOver = false;
		camera = new Camera(width, height,background,images);

		repaint();
	}

	private void createEnemies() {
		for (int i = 0; i < maxEnemies; i++) {
			enemies.add(new Enemy(rand.nextInt(width), rand.nextInt(height),
					Direction.getRandom(), images.getEnemyImages()));
		}
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
		else {
			player.setSpeed(0);
			camera.setSpeed(0);
			camera.setMovingCamera(false);
		}

		if (buttons.contains(KeyEvent.VK_F))
			addBullet();
		
		if(player.getDirection().checkDisabled(player.getDirection())){
			//player.setDirection(player.getDirection().getNormalOpposite(player.getDirection()));
			player.setSpeed(0);
		}

	}

	public void addBullet() {
		if (periodsSinceFire >= 3) {
			Bullet b = new Bullet(player.getAbsoluteX() + player.getWidth(),
					player.getAbsoluteY() + player.getHeight(),
					player.getDirection(), images.getBulletImages());
			bullets.add(b);
			b.rotateBullet(0);
			periodsSinceFire = 0;
		}
	}

	public void paintComponent(Graphics g) {
		setOpaque(false);
		super.paintComponent(g);
		camera.drawCamera(player.getDirection(), g, this);
		for (Enemy enemy : enemies) {
			enemy.draw(g, 0);
		}
		background.paintSessileSprites(g,camera);
		player.drawPlayer(g);
		paintText(g);
		paintHealth(g);
		if (player.getHealth() < 10) {
			g.setColor(Color.red);
			g.drawString("DEAD!", width - 65, 10);
		}
		for (Bullet b : bullets) {
			b.draw(g, 0);
		}
		if (gameOver) {
			paintGameOver(g);
		}
	}

	private void paintGameOver(Graphics g) {
		player.draw(g, 8);
		g.setColor(Color.black);
		g.drawString("Game Over", (width / 2) - 25, height / 2);
	}

	private void paintText(Graphics g) {
		g.setColor(Color.black);
		g.drawString("Avoid the red enemy!", 10, 10);
		g.drawString("Pace: " + pace, width / 2, 10);
		g.drawString("Life: ", width - 100, 10);
	}

	private void paintHealth(Graphics g) {
		BufferedImage fullHeart = images.getFullHeart();
		BufferedImage emptyHeart = images.getEmptyHeart();
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
	}

	public void run() {
		while (!gameOver) {
			periodsSinceFire++;
			processKeys();
			player.tick(camera);
			
			detectEnemyCollisions();
			detectBulletCollisions();
			destroyer.destroyBullets(bullets);
			destroyer.destroyEnemies(enemies);
			//System.out.println(bullets);
			background.tick(player);
			difficultyWait();
			repaint();
		}
	}
	private void tickAll(){
		
	}

	private void detectEnemyCollisions() {
		for (Enemy enemy : enemies) {
			enemy.run();
			enemy.collideWalls(width, height, camera);
			if (enemy.collide(player.createRect())) {
				player.damage(10);
			}
			for (Enemy e : enemies) {
				if (!e.equals(enemy)) {
					if(enemy.collide(e.createRect())){
						enemy.setDead(true);
					}
						
				}
			}
			if (enemy.getDead()) {
				destroyer.mark(enemy);
			}
		}
	}

	private void detectBulletCollisions() {
		for (Bullet bullet : bullets) {
			bullet.run();
			bullet.collideWalls(width, height, camera);
			// if(bullet.collide(player.getRect())){
			// player.damage(10);
			// }
			for (Enemy e : enemies) {
				if(bullet.collide(e.createRect())){
					bullet.setDead(true);
					e.damage(10);
				}
				
				
				
			}
			
			System.out.println(bullet.getDead());
			if (bullet.getDead()) {
				destroyer.mark(bullet);
				System.out.println("Bullet marked");
			}
		}
	}

	public void incrementPace(int i) {
		pace += i;
	}
}
