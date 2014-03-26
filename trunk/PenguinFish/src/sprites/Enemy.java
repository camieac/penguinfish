package sprites;

import java.awt.Graphics;

import graphics.Images;
import main.DataStore;
import main.Direction;

public class Enemy extends Sprite {
private int step;
	public Enemy(int x, int y, Direction d, int id) {
		super(x, y, d, id);
		direction = Direction.getRandom();
		bounces = true;
		health = 100;
		speed = 4;
		step = 0;
	}

	public void draw(double x, double y, Graphics g, int i) {
		g.drawImage(DataStore.getInstance().images.getEnemy(id), (int) x,
				(int) y, null);
	}
//	public void run() {
//		this.calcStep();
//		x += dx;
//		y += dy;
//		if (health <= 0) {
//			dead = true;
//		}
//	}
	public void calcStep(){
		System.out.println("Step: " + step);
		dx = 0;
		dy = 0;
		if(step >= 0 && step <=20){
			dx = speed;
		}if(step > 20 && step <= 40){
			dy = speed;
		}if(step > 40 && step <= 60){
			dx = -speed;
		}if(step > 60 && step <= 80){
			dy = - speed;
		}if(step == 80) step = 0;
		step++;
	}
}