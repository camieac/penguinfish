package sprites;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import main.DataStore;
import main.Direction;

@SuppressWarnings("serial")
public class Bullet extends Sprite {

	protected Image rotatedImage;

	public Bullet(double x, double y, Direction d, int id) {
		super(x, y, d, id);
		setDead(false);
		rotatedImage = null;
		bounces = false;
		speed = 10;
		health = 1;

	}

	public void draw(Graphics g, int i) {
		g.drawImage(rotatedImage, x, y, null);
	}

	public void rotateBullet(int i) {
		double dir = setRotation(direction.getAngle());
		if (rotatedImage == null) {
			BufferedImage bulletImage = DataStore.getInstance().images
					.getBulletImage(i);
			AffineTransform tx = AffineTransform.getRotateInstance(dir,
					bulletImage.getWidth() / 2, bulletImage.getHeight() / 2);
			AffineTransformOp op = new AffineTransformOp(tx,
					AffineTransformOp.TYPE_BILINEAR);
			rotatedImage = op.filter(bulletImage, null);
		}
	}

	public double setRotation(int degree) {
		return Math.PI / 180 * degree;
	}
}
