package sprites;

import graphics.Images;
import main.Direction;

public class Enemy extends Sprite {

	public Enemy(int x, int y, Direction d, Images images, int i) {
		super(x, y, d, images, i);
		direction = Direction.getRandom();
		bounces = true;
		health = 100;
		speed = 4;
		this.image = images.getEnemy(i);
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
	}
}