package sprites;

import java.awt.image.BufferedImage;

import main.Direction;

public class Enemy extends Sprite {
	protected int aggroRadius;
	protected int aggroTimeRemaining;
	protected int baseAggroTime;

	public Enemy(int x, int y, Direction d, BufferedImage[] e) {
		super(x, y, d, e);
		direction = Direction.getRandom();
		bounces = true;
		health = 100;
		speed = 4;
	}
}