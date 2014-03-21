package sprites;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import main.Camera;
import main.Direction;

public class Player extends Sprite {
	protected int distance;
	protected boolean playerUp, playerDown, playerLeft, playerRight;
	protected boolean moveX, moveY;
	

	public Player(int x, int y, Direction d, BufferedImage[] i) {
		super(x, y, d, i);
		moveX = false;
		moveY = false;
		setHealth(100);
		speed = 5;
		
	}

	public void resetLocation(int width, int height) {
		absoluteX = width / 2 - images[0].getWidth() / 2;
		absoluteY = height / 2 - images[0].getHeight() / 2;
	}

	public void drawPlayer(Graphics g) {
		Image image = images[0];
		if (health >= 50) {
			image = images[direction.getInt()];
		}
		if (health <= 50 && health > 0) {
			image = images[direction.getInt()];
		}
		g.drawImage(image, absoluteX, absoluteY, null);
	}
	public void tick(Camera camera){
		setPlayerMoving(camera.getMoveX(), camera.getMoveY());
		runAtEdges();
	}
	
	public void runAtEdges() {
		calcVelocity();
		if(moveX){
			speed = 4;
			absoluteX += dx;
		}
		if(moveY){
			speed = 4;
			absoluteY += dy;
		}
		if (health <= 0) {
			dead = true;
		}
		//System.out.println("player moveX:" + moveX + " moveY:" + moveY);
		
	}
	
	public void setPlayerMoving(boolean xMove, boolean yMove) {
		moveX = !xMove;
		moveY = !yMove;
	}
	
}
