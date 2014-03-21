package sprites;

import graphics.Images;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import main.Direction;

public class Bullet extends Sprite {

	protected Image rotatedImage;
	private Images images;
	public Bullet(int x, int y, Direction d, Images images, int i) {
		super(x, y, d, images, i);
		setDead(false);
		rotatedImage = null;
		bounces = false;
		speed = 10;
		health = 1;
		this.images = images;
	}
	
	public void draw(Graphics g, int i) {
		g.drawImage(rotatedImage, absoluteX, absoluteY, null);
	}

	public void rotateBullet(int i) {
		double dir = setRotation(direction.getAngle());
		if (rotatedImage == null) {
			BufferedImage bulletImage = images.getBulletImage(i);
			AffineTransform tx = AffineTransform.getRotateInstance(dir,bulletImage.getWidth() / 2, bulletImage.getHeight() / 2);
			AffineTransformOp op = new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
			rotatedImage = op.filter(bulletImage, null);
		}		
	}

	public double setRotation(int degree) {
		return Math.PI / 180 * degree;
	}
}
