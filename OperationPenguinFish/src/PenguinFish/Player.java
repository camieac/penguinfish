package PenguinFish;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

class Player extends Sprite {
	int distance;
	boolean playerUp, playerDown, playerLeft, playerRight;
	boolean moveX, moveY;

	public Player(int x, int y, Direction d, BufferedImage[] i) {
		super(x, y, d, i);
		moveX = false;
		moveY = false;
		setHealth(100);
		speed = 5;
	}

	public void resetLocation(int width, int height) {
		relativeX = width / 2 - images[0].getWidth() / 2;
		relativeY = height / 2 - images[0].getHeight() / 2;
	}

	public void drawPlayer(Graphics g) {
		Image image = images[0];
		if (health >= 50) {
			image = images[direction.getInt()];
		}
		if (health <= 50 && health > 0) {
			image = images[direction.getInt()];
		}
		g.drawImage(image, relativeX, relativeY, null);
	}
	
	public void run2() {
		calcVelocity();
		if(moveX){
			speed = 5;
			relativeX += dx;
		}
		if(moveY){
			speed = 5;
			relativeY += dy;
		}
		if (health <= 0) {
			dead = true;
		}
		System.out.println("player moveX:" + moveX + " moveY:" + moveY);
		
	}
	
	public void setPlayerMoving(boolean xMove, boolean yMove) {
		moveX = !xMove;
		moveY = !yMove;
	}
}
