package PenguinFish;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.*;

class Game extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	// X and Y positions for thea enemy and the player
	private int baseDistance;
	private Collision collision;
	private Enemy[] enemies;
	private LinkedList<Bullet> bullets;
	private Image fullHeart, emptyHeart;

	private Graphics2D g2d;
	private boolean game, gameOver;

	private int heartX;

	private int heartY;
	private boolean newHeart;
	private int numberOfEnemies;

	private int pace;

	private Player player;
	private Random rand;

	private boolean speedHeld;
	private Thread thread;

	private int width, height; // Size of the window

	public Game(int panelWidth, int panelHeight) {
		numberOfEnemies = 1;
		width = panelWidth;
		height = panelHeight;
		rand = new Random();
		collision = new Collision(panelWidth, panelHeight);
		baseDistance = 5;

		loadImages();
		createGame();
	}

	private void createGame() {
		pace = 2;
		enemies = new Enemy[numberOfEnemies];
		player = new Player();
		bullets = new LinkedList<Bullet>();
		//bullet = new Bullet(player.getPlayerX(),player.getPlayerY());
		
		for(int i = 0; i< numberOfEnemies;i++){
		enemies[i] = new Enemy(rand.nextInt(width), rand.nextInt(height), new ImageIcon("res/img/Enemy01.png").getImage());
		enemies[i].setupDistances(baseDistance);
		}
		
		player.setupDistances(baseDistance);
		thread = new Thread(this);
		thread.start();
		game = true;
		gameOver = false;
		System.out.println("Ba: " + pace);
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
			}
		} catch (InterruptedException ex) {
		}
	}
	

	public int getBaseDistance() {
		return baseDistance;
	}

	public void incrementPace(int inc){
		if(pace !=5 && inc >0 || pace != 1 && inc <0) pace += inc;
	}

	

	public void keyPressed(KeyEvent evt) {
		if (player.getLife() > 0) {
			switch (evt.getKeyCode()) {

			// Start moving player

		
			case KeyEvent.VK_UP:
				player.setPlayerUp(true);
				break;
			case KeyEvent.VK_DOWN:
				player.setPlayerDown(true);
				break;
			case KeyEvent.VK_LEFT:
				player.setPlayerLeft(true);
				break;
			case KeyEvent.VK_RIGHT:
				player.setPlayerRight(true);
				break;
			case KeyEvent.VK_SPACE:
				addBullet();
				break;
			case KeyEvent.VK_S:
				speedHeld = true;
				break;
			}
		}
	}
	
	public void addBullet(){
		bullets.add(new Bullet(player.getPlayerX(),player.getPlayerY(),player.getDirection()));
	}

	// public void timeForNewHeart(){
	// int randHeart = generator.nextInt(60000);
	// long newTime = System.currentTimeMillis();
	// if (newTime - startTime > randHeart){
	// newHeart = true;
	// }
	// }

	// Receive the key released
	public void keyReleased(KeyEvent evt) {

		if (player.getLife() > 0) {
			switch (evt.getKeyCode()) {
			// Stop moving player
			case KeyEvent.VK_UP:
				player.setPlayerUp(false);
				break;
			case KeyEvent.VK_DOWN:
				player.setPlayerDown(false);
				break;
			case KeyEvent.VK_LEFT:
				player.setPlayerLeft(false);
				break;
			case KeyEvent.VK_RIGHT:
				player.setPlayerRight(false);
				break;
			
			case KeyEvent.VK_S:
				speedHeld = false;
				break;
			}
		}
	}

	private void loadImages() {

		

		fullHeart = new ImageIcon("res/img/Heart01.png").getImage();
		emptyHeart = new ImageIcon("res/img/Heart02.png").getImage();

	}

	// Interface
	@Override
	public void paintComponent(Graphics gc) {
		setOpaque(false);
		super.paintComponent(gc);

		g2d = (Graphics2D) gc;
		if (newHeart) {

			g2d.drawImage(fullHeart, heartX, heartY, this);
		}
		for(Enemy enemy : enemies){
		enemy.drawEnemy(this, g2d);
		}
		player.drawPlayer(this.g2d, this);

		// Draw instructions
		gc.setColor(Color.black);
		gc.drawString("Avoid the red enemy!", 10, 10);

		//Draw difficulty
		gc.drawString("Pace: " + pace, width / 2, 10);
		// Draw player's life
		gc.drawString("Life: ", width - 100, 10);
		if (player.getLife() == 100) {
			g2d.drawImage(fullHeart, width - 75, 1, this);
		}
		if (player.getLife() < 100 && player.getLife() >= 90) {
			g2d.drawImage(emptyHeart, width - 75, 1, this);
		}
		if (player.getLife() >= 80) {
			g2d.drawImage(fullHeart, width - 65, 1, this);
		}
		if (player.getLife() < 80 && player.getLife() >= 70) {
			g2d.drawImage(emptyHeart, width - 65, 1, this);
		}
		if (player.getLife() >= 60) {
			g2d.drawImage(fullHeart, width - 55, 1, this);
		}
		if (player.getLife() < 60 && player.getLife() >= 50) {
			g2d.drawImage(emptyHeart, width - 55, 1, this);
		}
		if (player.getLife() >= 40) {
			g2d.drawImage(fullHeart, width - 45, 1, this);
		}
		if (player.getLife() < 40 && player.getLife() >= 30) {
			g2d.drawImage(emptyHeart, width - 45, 1, this);
		}
		if (player.getLife() >= 20) {
			g2d.drawImage(fullHeart, width - 35, 1, this);
		}
		if (player.getLife() < 20 && player.getLife() >= 10) {
			g2d.drawImage(emptyHeart, width - 35, 1, this);
		}
		if (player.getLife() < 10) {
			gc.setColor(Color.red);
			gc.drawString("DEAD!", width - 65, 10);
		}
		
		Bullet tempBullet;
		for(int i = 0; i < bullets.size();i++){
			tempBullet = bullets.get(i);
			//tempBullet.setRotation(45);
			tempBullet.drawBullet(g2d, this);
			if(collision.collisionWallsAmmo(tempBullet.getX(), tempBullet.getY(), tempBullet.getWidth(), tempBullet.getHeight())){
				bullets.remove(i);
			}
		}
		System.out.println("# of bullets" + bullets.size());
		
		
		// Draw "Game Over" screen when life = 0
		if (gameOver) {
			g2d.drawImage(player.getPlayerDeadImage(), player.getPlayerX(), player.getPlayerY(),
					this);
			gc.setColor(Color.black);
			gc.drawString("Game Over", (width / 2) - 25, height / 2);
		}
	}

	public void reset() {
		loadImages();
		createGame();
		//repaint();
		
	}

	public void run() {
		while (game) {
			for(Enemy enemy : enemies){
			enemy.moveEnemy(player,this);
			enemy.hitWall(collision, player, this);
			enemy.hitPlayer(collision, player, this);
			enemy.enemyHitsEnemy(collision);
			}
			// repaint();

			difficultyWait();
			player.setDirection();
			player.movePlayer(this, speedHeld);

			repaint();

			if (player.getLife() <= 0) {
				game = false;
				gameOver = true;
			}

//			if (player.getPlayerX() + player.getPlayerSize() >= heartX
//					&& player.getPlayerX() <= heartX + enemies.getEnemySize()
//					&& player.getPlayerY() + player.getPlayerSize() >= heartY
//					&& player.getPlayerY() <= heartY + enemies.getEnemySize()) {
//				player.setLife(player.getLife() + 10);
//				newHeart = false;
//			}

			

		

		}
	}
	
	public void setBaseDistance(int baseDistance) {
		this.baseDistance = baseDistance;
	}

}
