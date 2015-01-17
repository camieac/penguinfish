package main;

import java.util.LinkedList;
import java.util.Random;
import sprites.Bullet;
import sprites.Enemy;

/**
 * Handles collisions and creates enemies at the start of a game. Looks at the
 * current state of the game and ticks everything as long as the game is being
 * played (state is PLAYING).
 * 
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * @since 
 * 
 */
public class Game implements Runnable {

	
	

	/**
	 * 
	 */
	public Game() {
		
	}


	

	protected void detectBulletCollisions() {
		LinkedList<Bullet> dead = new LinkedList<Bullet>();
		for (Bullet bullet : DataStore.getInstance().bullets) {
			bullet.run();

			// Check if the bullet has reached the edge of the camera, mark as
			// dead if it has.
			bullet.collideWalls(DataStore.getInstance().panelWidth,
					DataStore.getInstance().panelHeight);

			// if(bullet.collide(player.getRect())){
			// player.damage(10);
			// }

			for (Enemy e : DataStore.getInstance().level.getEnemies()) {
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
		for (Enemy enemy : DataStore.getInstance().level.getEnemies()) {
			enemy.run();
			enemy.collideWalls(DataStore.getInstance().maxWidth,
					DataStore.getInstance().maxHeight);
			if (enemy.collide(DataStore.getInstance().player)) {
				DataStore.getInstance().player.damage(10);
			}
			for (Enemy e : DataStore.getInstance().level.getEnemies()) {
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
		DataStore.getInstance().level.getEnemies().removeAll(dead);
	}

	/**
	 * Creates a delay that is used in the ticking cycle to slow down ticking.
	 */
	
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
		while (true) {
			if (DataStore.getInstance().gameState == State.PLAYING){
				difficultyWait();
				tickAll();
			}
				
		}
	}

	protected void tickAll() {
		DataStore.getInstance().periodSinceLastFire++;
		DataStore.getInstance().player.tick();
		detectEnemyCollisions();
		detectBulletCollisions();
		DataStore.getInstance().level.tick(DataStore.getInstance().player);
		long currentTimeMillis = System.currentTimeMillis();
		DataStore.getInstance().currentLevelTime = Math.abs((DataStore.getInstance().levelStartTime - currentTimeMillis)/1000);
		
	}
}
