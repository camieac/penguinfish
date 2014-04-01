package main;

import java.util.LinkedList;
import java.util.Random;
import sprites.Bullet;
import sprites.Enemy;

/**
 * Handles collisions and creates enemies at the start of a game.
 * 
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * 
 */
public class Game implements Runnable {

	
	// protected int numberOfEnemies;
	protected Random rand;

	public Game() {
		rand = new Random();
		// numberOfEnemies = 300;
		
	}

	private void cameraControl() {
		DataStore.getInstance().cameraAttachedToPlayer = true;

	}

	protected void startGame() {
		DataStore.getInstance().gameStarted = true;
	}

	protected void detectBulletCollisions() {
		LinkedList<Bullet> dead = new LinkedList<Bullet>();
		for (Bullet bullet : DataStore.getInstance().bullets) {
			bullet.run();
			bullet.collideWalls(DataStore.getInstance().maxWidth,
					DataStore.getInstance().maxHeight);
			// if(bullet.collide(player.getRect())){
			// player.damage(10);
			// }
			for (Enemy e : DataStore.getInstance().enemies) {
				if (bullet.collide(e)) {
					bullet.setDead(true);
					e.damage(10);
				}

			}
			if (bullet.isDead()) {
				dead.add(bullet);
			}

		}
		// System.out.println("Bullets: " + DataStore.getInstance().bullets);
		// System.out.println("Dead Bullets: " + dead);
		DataStore.getInstance().bullets.removeAll(dead);
	}

	protected void detectEnemyCollisions() {
		LinkedList<Enemy> dead = new LinkedList<Enemy>();
		for (Enemy enemy : DataStore.getInstance().enemies) {
			enemy.run();
			enemy.collideWalls(DataStore.getInstance().maxWidth,
					DataStore.getInstance().maxHeight);
			if (enemy.collide(DataStore.getInstance().player)) {
				DataStore.getInstance().player.damage(10);
			}
			for (Enemy e : DataStore.getInstance().enemies) {
				if (!e.equals(enemy)) {
					if (enemy.collide(e)) {
						enemy.setDead(true);
					}

				}
			}
			if (enemy.isDead()) {
				dead.add(enemy);
			}

		}
		DataStore.getInstance().enemies.removeAll(dead);
	}

	

	protected void difficultyWait() {
		try {
			Thread.sleep(70 - (DataStore.getInstance().pace * 10));
		} catch (InterruptedException e) {
			// Thread got interrupted
			e.printStackTrace();
		}
	}

	/**
	 * @param i
	 */
	public void incrementPace(int i) {
		DataStore.getInstance().pace += i;
	}

	public void run() {
		while (DataStore.getInstance().gameState == State.PLAYING) {
			tickAll();
		}
	}

	protected void tickAll() {
		cameraControl();
		DataStore.getInstance().periodSinceLastFire++;

		DataStore.getInstance().player.tick();
		detectEnemyCollisions();
		detectBulletCollisions();
		DataStore.getInstance().world.tick(DataStore.getInstance().player);
		difficultyWait();

	}
}
