package PenguinFish;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
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
		// Draw player
		Image image = images[0];
		if (health >= 50) {
			image = images[direction.getInt()];
		}

		if (health <= 50 && health > 0) {
			image = images[direction.getInt() + 9];
		}
		g2d.drawImage(image, x, y, panel);
	}

	public void movePlayer(JPanel panel) {

		if (playerUp == true && y >= 15) {
			y -= distance;
		}
		if (playerDown == true && y <= (panel.getHeight() - height)) {
			y += distance;
		}
		if (playerLeft == true && x >= 0) {
			x -= distance;
		}
		if (playerRight == true && x <= (panel.getWidth() - height)) {
			x += distance;
		}

	}

	public void setupDistances(int baseDistance) {
		if (movePlayer)
			this.distance = baseDistance;
		else
			this.distance = 0;
	}

}
