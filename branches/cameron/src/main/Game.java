package main;

import graphics.Images;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JPanel;

import sprites.Bullet;
import sprites.Enemy;
import sprites.Player;

public class Game extends JPanel {

	protected static final long serialVersionUID = 1L;
	protected LinkedList<Integer> buttons;
	protected LinkedList<Enemy> enemies;
	protected LinkedList<Bullet> bullets;
	protected Images images;
	protected Destroyer destroyer;
	protected Player player;
	protected Camera camera;
	protected Background background;
	protected boolean gameOver;
	protected int width, height,pace, numberOfEnemies, baseSpeed,periodsSinceFire;
	protected Random rand;

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
		numberOfEnemies = 0;
		createGame();
	}

	protected void createGame() {
		background = new Background(images);
		background.createSessileSprites();
		enemies = new LinkedList<Enemy>();
		createEnemies();
		player = new Player(0, 0, Direction.SOUTH, images, 0);
		bullets = new LinkedList<Bullet>();
		player.setSpeed(baseSpeed);
		gameOver = false;
		camera = new Camera(width, height,background,images);

		repaint();
	}

	protected void createEnemies() {
		for (int i = 0; i < numberOfEnemies; i++) {
			enemies.add(new Enemy(rand.nextInt(width), rand.nextInt(height),Direction.getRandom(), images, 0));
		}
	}

	protected void difficultyWait() {
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

	protected void processKeys() {
		boolean moveKeyPressed = false;
		if (buttons.contains(KeyEvent.VK_UP) && buttons.contains(KeyEvent.VK_LEFT)){
			player.setDirection(Direction.NORTHWEST);
			moveKeyPressed = true;
		}else if (buttons.contains(KeyEvent.VK_UP) && buttons.contains(KeyEvent.VK_RIGHT)){
			player.setDirection(Direction.NORTHEAST);
			moveKeyPressed = true;
		}else if (buttons.contains(KeyEvent.VK_DOWN) && buttons.contains(KeyEvent.VK_LEFT)){
			player.setDirection(Direction.SOUTHWEST);
			moveKeyPressed = true;
		}else if (buttons.contains(KeyEvent.VK_DOWN) && buttons.contains(KeyEvent.VK_RIGHT)){
			player.setDirection(Direction.SOUTHEAST);
			moveKeyPressed = true;
		}else if (buttons.contains(KeyEvent.VK_UP)){
			player.setDirection(Direction.NORTH);
			moveKeyPressed = true;
		}else if (buttons.contains(KeyEvent.VK_DOWN)){
			player.setDirection(Direction.SOUTH);
			moveKeyPressed = true;
		}else if (buttons.contains(KeyEvent.VK_LEFT)){
			player.setDirection(Direction.WEST);
			moveKeyPressed = true;
		}else if (buttons.contains(KeyEvent.VK_RIGHT)){
			player.setDirection(Direction.EAST);
			moveKeyPressed = true;
		}
			//System.out.println("    Move: " + moveKeyPressed);
		if(moveKeyPressed){
			player.move();
		}

		if (buttons.contains(KeyEvent.VK_F))
			addBullet();
		
//		if(player.getDirection().checkDisabled(player.getDirection())){
//			player.setDirection(player.getDirection().getNormalOpposite(player.getDirection()));
//			player.setSpeed(0);
//		}

	}

	public void addBullet() {
		if (periodsSinceFire >= 3) {
			Bullet b = new Bullet(player.getAbsoluteX() + player.getWidth(), player.getAbsoluteY() + player.getHeight(), player.getDirection(), images, 0);
			bullets.add(b);
			b.rotateBullet(0);
			periodsSinceFire = 0;
		}
	}

	public void paintComponent(Graphics g) {
		setOpaque(false);
		super.paintComponent(g);
		camera.drawCamera(player,background, g, this);
		for (Enemy enemy : enemies) {
			enemy.draw(g, 0);
		}
		background.paintSessileSprites(g,camera);
		background.draw(g);
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

	protected void paintGameOver(Graphics g) {
		player.draw(g, 8);
		g.setColor(Color.black);
		g.drawString("Game Over", (width / 2) - 25, height / 2);
	}

	protected void paintText(Graphics g) {
		g.setColor(Color.black);
		g.drawString("Avoid the red enemy!", 10, 10);
		g.drawString("Pace: " + pace, width / 2, 10);
		g.drawString("Life: ", width - 100, 10);
	}

	protected void paintHealth(Graphics g) {
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
		 tickAll();
		}
	}
	protected void tickAll(){
		periodsSinceFire++;
		processKeys();
		player.tick(camera);
		detectEnemyCollisions();
		detectBulletCollisions();
		destroyer.destroyBullets(bullets);
		destroyer.destroyEnemies(enemies);
		background.tick(player);
		difficultyWait();
		repaint();
		cameraControl();
	}

	private void cameraControl() {
		
		if(westBoundaryBreach()||eastBoundaryBreach()||northBoundaryBreach()||southBoundaryBreach()){
			System.out.println("Detached");
			camera.detach();
		}else{
			camera.attach();
			setupBackgroundFrame();
//			background.draw();
			System.out.println("Attached");
			
		}
		
	}

	private void setupBackgroundFrame() {
		background.setImage(images.getDisplayableBackground(camera.getCurrentLeft(), camera.getCurrentTop(),camera.getWidth(),camera.getHeight()));
	}

	private boolean westBoundaryBreach() {
		int westBoundary = camera.getWidth()/2;
		return player.getAbsoluteX() < westBoundary;
	}
	private boolean eastBoundaryBreach() {
		int eastBoundary = background.getWidth() - camera.getWidth()/2;
		return player.getAbsoluteX() > eastBoundary;
	}
	private boolean northBoundaryBreach() {
		int northBoundary = camera.getHeight()/2;
		return player.getAbsoluteY() < northBoundary;
	}
	private boolean southBoundaryBreach() {
		int southBoundary = camera.getHeight()/2;
		return player.getAbsoluteY() > southBoundary;
	}
	

	protected void detectEnemyCollisions() {
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
 
	protected void detectBulletCollisions() {
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
			
			//System.out.println(bullet.getDead());
			if (bullet.getDead()) {
				destroyer.mark(bullet);
				//System.out.println("Bullet marked");
			}
		}
	}

	public void incrementPace(int i) {
		pace += i;
	}
}
