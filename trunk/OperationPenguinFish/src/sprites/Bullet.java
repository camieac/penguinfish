package sprites;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import main.Direction;

public class Bullet extends Sprite {

	protected Image rotatedImage;

	public Bullet(int x, int y, Direction d, BufferedImage[] i) {
		super(x, y, d, i);
		setDead(false);
		rotatedImage = null;
		bounces = false;
		speed = 10;
		health = 1;
	}
	
	public void draw(Graphics g, int i) {
		g.drawImage(rotatedImage, absoluteX, absoluteY, null);
	}

	public void rotateBullet(int i) {
		double dir = setRotation(direction.getAngle());
		if (rotatedImage == null) {
			AffineTransform tx = AffineTransform.getRotateInstance(dir,images[i].getWidth() / 2, images[i].getHeight() / 2);
			AffineTransformOp op = new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
			rotatedImage = op.filter(images[i], null);
		}		
	}

	public double setRotation(int degree) {
		return Math.PI / 180 * degree;
	}
}
