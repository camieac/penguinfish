package sprites;

import java.awt.Graphics;
import java.util.Random;

import graphics.Images;
import main.DataStore;
import main.Direction;

public class Enemy extends Sprite {
	private int step;
	private int type;
	public Enemy(int x, int y, Direction d, int id) {
		super(x, y, d, id);
		direction = Direction.getRandom();
		bounces = true;
		health = 100;
		speed = 6;
		Random rand = new Random(); 
		step = rand.nextInt(80);
		
		type = rand.nextInt(2);
	}

	public void draw(double x, double y, Graphics g, int i) {
		g.drawImage(DataStore.getInstance().images.getEnemy(id), (int) x,
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