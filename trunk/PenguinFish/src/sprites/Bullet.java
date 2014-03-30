package sprites;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import main.DataStore;
import main.Direction;

/**
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson, Stuart Thain
 *
 */
@SuppressWarnings("serial")
public class Bullet extends Sprite {

	protected Image rotatedImage;

	/**
	 * @param x
	 * @param y
	 * @param d
	 * @param id
	 */
	public Bullet(double x, double y, Direction d, int id) {
		super(x, y, d, id);
		setDead(false);
		rotatedImage = null;
		bounces = false;
		speed = 10;
		health = 1;
		rotateBullet(degreesToRadians(direction.getAngle()));
	}

	

	/**
	 * @param dir
	 */
	public void rotateBullet(double dir) {
		if (rotatedImage == null) {
			BufferedImage bulletImage = DataStore.getInstance().images
					.getBulletImage(0);
			AffineTransform tx = AffineTransform.getRotateInstance(dir,
					bulletImage.getWidth() / 2, bulletImage.getHeight() / 2);
			AffineTransformOp op = new AffineTransformOp(tx,
					AffineTransformOp.TYPE_BILINEAR);
			rotatedImage = op.filter(bulletImage, null);
		}
	}

	/**
	 * @param degree
	 * @return
	 */
	public double degreesToRadians(int degree) {
		return Math.PI / 180 * degree;
	}



	/**
	 * @return
	 */
	public Image getImage() {
		return rotatedImage;
	}
}
