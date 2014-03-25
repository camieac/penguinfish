package sprites;

import java.awt.Graphics;

import graphics.Images;
import main.DataStore;
import main.Direction;

public class Enemy extends Sprite {

	public Enemy(int x, int y, Direction d, int id) {
		super(x, y, d, id);
		direction = Direction.getRandom();
		bounces = true;
		health = 100;
		speed = 4;
	}

	public void draw(double x, double y, Graphics g, int i) {
		g.drawImage(DataStore.getInstance().images.getEnemy(id), (int) x,
				(int) y, null);
	}
}