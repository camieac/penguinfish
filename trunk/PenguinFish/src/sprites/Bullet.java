package sprites;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import main.DataStore;
import main.Direction;

/**
 * Fired from the player. It's image is rotated to the direction fired, then the
 * bullet continues moving until it collides with something, when it collides,
 * it is destroyed.
 * 
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * 
 */
@SuppressWarnings("serial")
public class Bullet extends Sprite {

	protected Image rotatedImage;

	/**
	 * @param x
	 *            The desired x position of the bullet.
	 * @param y
	 *            The desired y position of the bullet.
	 * @param d
	 *            The desired direction of the bullet.
	 * @param id
	 */
	public Bullet(double x, double y, Direction d, String name) {
		super(x, y, d, name);
		setDead(false);
		rotatedImage = null;
		bounces = false;
		speed = 10;
		health = 1;
		rotateBullet(degreesToRadians(direction.getAngle()));
	}

	/**
	 * @param degree
	 * @return The value of the angle to be rotated to in radians.
	 */
	public double degreesToRadians(int degree) {
		return Math.PI / 180 * degree;
	}

	/**
	 * @return The image of the bullet.
	 */
	public Image getImage() {
		return rotatedImage;
	}

	/**
	 * @param dir
	 *            The direction to rotate the bullet to.
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
	
	public void calcStep(){
		switch (direction) {
		case NORTH:
			dx = 0;
			dy = -speed;
			break;
		case NORTHEAST:
			dx = (speed / Math.sqrt(2));
			dy = -(speed / Math.sqrt(2));
			break;
		case EAST:
			dx = speed;
			dy = 0;
			break;
		case SOUTHEAST:
			dx = (speed / Math.sqrt(2));
			dy = (speed / Math.sqrt(2));
			break;
		case SOUTH:
			dx = 0;
			dy = speed;
			break;
		case SOUTHWEST:
			dx = -(speed / Math.sqrt(2));
			dy = (speed / Math.sqrt(2));
			break;
		case WEST:
			dx = -speed;
			dy = 0;
			break;
		case NORTHWEST:
			dx = -(speed / Math.sqrt(2));
			dy = -(speed / Math.sqrt(2));
			break;
		default:
			dx = 0;
			dy = 0;
		}

	
	}
	
	public String toString(){
		return "Type: Bullet\nx: " + x + "\ny: " + y +"\n" ;
	}
}
