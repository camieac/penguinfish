package PenguinFish;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

class Player extends Sprite {
	int distance;
	boolean playerUp, playerDown, playerLeft, playerRight;
	boolean movePlayer;

	public Player(int x, int y, Direction d, BufferedImage[] i) {
		super(x, y, d, i);
		movePlayer = false;
		setHealth(100);
		speed = 5;
	}

	public void resetLocation(int width, int height) {
		x = width / 2 - images[0].getWidth() / 2;
		y = height / 2 - images[0].getHeight() / 2;
	}

	public void drawPlayer(Graphics g) {
		Image image = images[0];
		if (health >= 50) {
			image = images[direction.getInt()];
		}
		if (health <= 50 && health > 0) {
			image = images[direction.getInt()];
		}
		g.drawImage(image, x, y, null);
	}

	public void setPlayerMoving(int baseDistance) {
		if (movePlayer)
			this.distance = baseDistance;
		else
			this.distance = 0;
	}
}
