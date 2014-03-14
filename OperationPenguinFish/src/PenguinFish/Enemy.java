package PenguinFish;

import java.awt.image.BufferedImage;

public class Enemy extends Sprite {
	int aggroRadius;
	int aggroTimeRemaining;
	int baseAggroTime;

	public Enemy(int x, int y, Direction d, BufferedImage[] e) {
		super(x, y, d, e);
		direction = Direction.getRandom();
		bounces = true;
		health = 100;
		speed = 10;
	}
}