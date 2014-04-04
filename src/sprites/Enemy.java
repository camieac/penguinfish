package sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.DataStore;
import main.Direction;

/**
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * 
 */
public class Enemy extends Sprite {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6014554174205449235L;
	private int animationStep;
	private BufferedImage[] images;
	private int movement;
	private int step;

	/**
	 * @param x x-coordinate of the left hand side of the enemy.
	 * @param y y-coordinate of the top side of the enemy.
	 * @param id The type image of the Enemy.
	 * @param movement The type of movement of the Enemy.
	 */
	public Enemy(int x, int y, int id, int movement) {
		super(x, y, Direction.getRandom(), id);
		// Valid ID's 0,3,6,9,12
		if (id > 0 && id < 3)
			id = 0;
		if (id > 3 && id < 6)
			id = 3;
		if (id > 6 && id < 9)
			id = 6;
		if (id > 9 && id < 12)
			id = 9;
		if (id >12){
			id = 12;
		}

		this.movement = movement;
		bounces = true;
		health = 100;
		speed = 6;
		Random rand = new Random();
		step = rand.nextInt(80);

		animationStep = 0;
		images = new BufferedImage[3];
		images[0] = DataStore.getInstance().images.getEnemy(id);
		images[1] = DataStore.getInstance().images.getEnemy(id + 1);
		images[2] = DataStore.getInstance().images.getEnemy(id + 2);

	}

	// public void run() {
	// this.calcStep();
	// x += dx;
	// y += dy;
	// if (health <= 0) {
	// dead = true;
	// }
	// }
	public void calcStep() {

		dx = 0;
		dy = 0;
		if (movement == 0) {// East - South - West - North
			animationStep++;
			if (step >= 0 && step <= 20) {
				dx = speed;
			}
			if (step > 20 && step <= 40) {
				dy = speed;
			}
			if (step > 40 && step <= 60) {
				dx = -speed;
			}
			if (step > 60 && step <= 80) {
				dy = -speed;
			}
			if (step == 80)
				step = 0;
		} else if (movement == 1) { // East - Stop - West - Stop
			if (step >= 0 && step <= 20) {
				dx = speed;
				animationStep++;
			}
			if (step > 40 && step <= 60) {
				dx = -speed;
				animationStep++;
			}
			if (step == 80)
				step = 0;
		} else if (movement == 2) { // South - Stop - North - Stop
			if (step >= 0 && step <= 20) {
				dy = speed;
				animationStep++;
			}
			if (step > 40 && step <= 60) {
				dy = -speed;
				animationStep++;
			}
			if (step == 80)
				step = 0;
		}
		else if (movement == 3) { // Dragon swoosh
				dy = speed;
		}
		step++;
		animationStep = animationStep % 3;
	}

	public void draw(double x, double y, Graphics g, int i) {
		g.drawImage(images[animationStep], (int) x, (int) y, null);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("x: " + x + "\n");
		sb.append("y: " + y + "\n");
		String type;
		if (id == 0) {
			type = "Spider";
		} else if (id == 3)
			type = "Worm";
		else if (id == 6)
			type = "Fisherman";
		else if (id == 9)
			type = "Minotaur";
		else if (id == 12)
			type = "Dragon";
		else
			type = "Unknown";
		sb.append("Type: " + type + "\n");
		return sb.toString();
	}
}