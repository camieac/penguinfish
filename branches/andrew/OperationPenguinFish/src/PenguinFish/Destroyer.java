package PenguinFish;

import java.util.LinkedList;

public class Destroyer {

	public LinkedList<Enemy> getRemoveEnemies() {
		return removeEnemies;
	}

	public void setRemoveEnemies(LinkedList<Enemy> removeEnemies) {
		this.removeEnemies = removeEnemies;
	}

	public LinkedList<Bullet> getRemoveBullets() {
		return removeBullets;
	}

	public void setRemoveBullets(LinkedList<Bullet> removeBullets) {
		this.removeBullets = removeBullets;
	}

	LinkedList<Enemy> removeEnemies = new LinkedList<Enemy>();
	LinkedList<Bullet> removeBullets = new LinkedList<Bullet>();

	public void mark(Sprite sprite) {
		if (sprite instanceof Enemy) {
			if (!removeEnemies.contains(sprite)) {
				removeEnemies.add((Enemy) sprite);
			}
		} else if (sprite instanceof Bullet) {
			if (!removeBullets.contains(sprite)) {
				removeBullets.add((Bullet) sprite);
			}
		} else {
			System.err.println("Cannot destroy unknown type");
		}

	}

	public void destroyBullets(LinkedList<Bullet> bullets) {
		if(!removeBullets.isEmpty()){
			bullets.removeAll(removeBullets);
			removeBullets.clear();
		System.out.println("Bullet destroyed");
		}
		
	}

	public void destroyEnemies(LinkedList<Enemy> enemies) {
		enemies.removeAll(removeEnemies);
	}
}
