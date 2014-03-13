package PenguinFish;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

class Player extends Sprite {
	int distance;
	boolean playerUp, playerDown, playerLeft, playerRight;
	boolean movePlayer;

	public Player(int x, int y, Direction d, BufferedImage[] i) {
		super(x, y, d, i);
		movePlayer = false;
		setHealth(100);
	}

	public void resetLocation(int width, int height) {
		x = width / 2 - images[0].getWidth() / 2;
		y = height / 2 - images[0].getHeight() / 2;
	}

	public void drawPlayer(Graphics2D g2d, JPanel panel) {
		Image image = images[0];
		if (health >= 50) {
			image = images[direction.getInt()];
		}
		if (health <= 50 && health > 0) {
			image = images[direction.getInt() + 9];
		}
		g2d.drawImage(image, x, y, panel);
	}

	public void setPlayerMoving(int baseDistance) {
		if (movePlayer)
			this.distance = baseDistance;
		else
			this.distance = 0;
	}
}
