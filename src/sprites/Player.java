package sprites;

import main.Camera;
import main.Direction;
import graphics.Images;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Sprite {
	protected int distance;
	private Images images;
	private BufferedImage image;

	public Player(int x, int y, Direction d, Images images, int i) {
		super(x, y, d, images, i);
		this.image = images.getPlayer(i);
		setHealth(100);
		speed = 5;
		this.images = images;
		absoluteX = 100;
		absoluteY = 100;
	}

	

	public void drawPlayer(Graphics g) {
		int imageNumber = direction.getInt();
		image = images.getPlayer(imageNumber);//direction.getInt()
		g.drawImage(image, absoluteX, absoluteY, null);
		g.drawString("pos: " + absoluteX + ","+ absoluteY, absoluteX, absoluteY);
		g.drawString("step: " + dx + ","+ dy, absoluteX, absoluteY-15);
	}
	public void tick(Camera camera){
		runAtEdges();
	}
	
	public void runAtEdges() {
		calcVelocity();
		if (health <= 0) {
			dead = true;
		}
	}
	
	
}
