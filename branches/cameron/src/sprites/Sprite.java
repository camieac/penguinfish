package sprites;

import java.awt.Rectangle;

import main.Direction;

/**
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * 
 */
public class Sprite extends SessileSprite {

	private static final long serialVersionUID = 1L;
	protected boolean bounces, dead;
	protected Direction direction, wallHit;
	protected double dx, dy, speed;
	protected int health;

	/**
	 * @param x
	 *            x-position of sprite.
	 * @param y
	 *            y-position of sprite.
	 * @param d
	 *            Initial direction of sprite.
	 * @param id
	 *            ID of player.
	 */
	public Sprite(double x, double y, Direction d, int id) {
		super((int) x, (int) y, id);
		this.direction = d;
		this.health = 0;
		this.dead = false;
	}

	/**
	 * Calculates the next movement step of the player, based on it's direction.
	 */
	public void calcStep() {
		dx = 0;
		dy = 0;
		boolean directionEnabled = direction.checkEnabled(direction);
		if (directionEnabled) {
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

	}

	/**
	 * @param rect2
	 * @return
	 */
	public boolean collide(Rectangle rect2) {

		if (this.intersects(rect2)) {
			direction = direction.getOpposite(direction, wallHit);

			return true;
		}
		return false;
	}

	/**
	 * @param xMax
	 * @param yMax
	 */
	public void collideWalls(double xMax, double yMax) {

		if (x < 0) {
			wallHit = Direction.WEST;
		} else if (x + width > xMax) {
			wallHit = Direction.EAST;
		} else if (x < 0) {
			wallHit = Direction.NORTH;
		} else if (x + height > yMax) {
			wallHit = Direction.SOUTH;
		}
		if (bounces) {
			direction = direction.getOpposite(direction, wallHit);
		} else {
			dead = true;
		}
	}

	/**
	 * @param amount
	 */
	public void damage(int amount) {
		health -= amount;
	}

	/**
	 * @return
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @return
	 */
	public double getDx() {
		return dx;
	}

	/**
	 * @return
	 */
	public double getDy() {
		return dy;
	}

	/**
	 * @return
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @return
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * @return
	 */
	public boolean isDead() {
		return dead;
	}

	/**
	 * Changes the x and y fields based on the result of the calcStep method.
	 */
	public void move() {
		x += dx;
		y += dy;
	}

	/**
	 * 
	 */
	public void run() {

		calcStep();
		x += dx;
		y += dy;
		if (health <= 0) {
			dead = true;
		}
	}

	/**
	 * @param dead
	 */
	public void setDead(boolean dead) {
		this.dead = dead;
	}

	/**
	 * @param d
	 */
	public void setDirection(Direction d) {
		direction = d;
	}

	/**
	 * @param dx
	 */
	public void setDx(int dx) {
		this.dx = dx;
	}

	/**
	 * @param dy
	 */
	public void setDy(int dy) {
		this.dy = dy;
	}

	/**
	 * @param health
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @param speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
