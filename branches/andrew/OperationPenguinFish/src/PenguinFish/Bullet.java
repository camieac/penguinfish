package PenguinFish;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

class Bullet extends Sprite {

	private Image rotatedImage;

	public Bullet(int x, int y, Direction d, BufferedImage[] i) {
		super(x, y, d, i);
		rotatedImage = null;
		bounces = false;
	}

	@Override
	public void draw(Graphics g, int i) {
		g.drawImage(images[i], x, y, null);
	}

	public void rotateBullet(Graphics g2d, int i) {
		double dir = setRotation(direction.getAngle());
		if (rotatedImage == null) {
			AffineTransform tx = AffineTransform.getRotateInstance(dir,
					images[i].getWidth() / 2, images[i].getHeight() / 2);
			AffineTransformOp op = new AffineTransformOp(tx,
					AffineTransformOp.TYPE_BILINEAR);
			rotatedImage = op.filter(images[i], null);
		}
		g2d.drawImage(rotatedImage, x, y, null);
	}

	public double setRotation(int degree) {
		return Math.PI / 180 * degree;
	}
}
