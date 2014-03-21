package sprites;

import main.Camera;
import main.Direction;
import graphics.Images;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Sprite {
	protected int distance;
	protected boolean playerUp, playerDown, playerLeft, playerRight;
	protected boolean moveX, moveY;
	private Images images;
	private BufferedImage image;

	public Player(int x, int y, Direction d, Images images, int i) {
		super(x, y, d, images, i);this.image = images.getPlayer(i);
		moveX = false;
		moveY = false;
		setHealth(100);
		speed = 5;
		
	}

	public void resetLocation(int width, int height) {
		absoluteX = width / 2 - image.getWidth() / 2;
		absoluteY = height / 2 - image.getHeight() / 2;
	}

	public void drawPlayer(Graphics g) {
		image = images.getPlayer(0);//direction.getInt()
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
	}
	
	public void setPlayerMoving(boolean xMove, boolean yMove) {
		moveX = !xMove;
		moveY = !yMove;
	}
}
