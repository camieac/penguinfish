package sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import graphics.Images;
import main.DataStore;
import main.Direction;

/**
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson, Stuart Thain
 *
 */
public class Enemy extends Sprite {
	private int step;
	private int type;
	private int animationStep;
	private BufferedImage[] images;
	/**
	 * @param x
	 * @param y
	 * @param d
	 * @param id
	 */
	public Enemy(int x, int y, Direction d, int id) {
		super(x, y, d, id);
		direction = Direction.getRandom();
		bounces = true;
		health = 100;
		speed = 6;
		Random rand = new Random(); 
		step = rand.nextInt(80);
		
		type = rand.nextInt(2);
		animationStep = 0;
		images = new BufferedImage[3];
		images[0] = DataStore.getInstance().images.getEnemy(id);
		images[1] = DataStore.getInstance().images.getEnemy(id+1);
		images[2] = DataStore.getInstance().images.getEnemy(id+2);
		
	}

	public void draw(double x, double y, Graphics g, int i) {
		g.drawImage(images[animationStep], (int) x,
				(int) y, null);
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
		animationStep++;
		animationStep = animationStep%3;
		dx = 0;
		dy = 0;
		if (type == 0) {//East - South - West - North
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
		}else if(type ==1){ //East - Stop - West - Stop
			if (step >= 0 && step <= 20) {
				dx = speed;
			}
			if (step > 40 && step <= 60) {
				dx = -speed;
			}
			if (step == 80)
				step = 0;
		}
		else if(type ==2){ //South - Stop - North - Stop
			if (step >= 0 && step <= 20) {
				dy = speed;
			}
			if (step > 40 && step <= 60) {
				dy = -speed;
			}
			if (step == 80)
				step = 0;
		}
		step++;
	}
}