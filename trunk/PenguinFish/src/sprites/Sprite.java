package sprites;

import graphics.Camera;
import graphics.Images;

import java.awt.Rectangle;
import java.util.Random;

import main.Direction;

public class Sprite extends SessileSprite {
	protected Direction direction, wallHit;
	protected double dx, dy, speed;
	protected int health;
	protected boolean bounces, dead;

	public Sprite(double x, double y, Direction d, int i) {
		super((int) x, (int) y, i);
		this.direction = d;
		this.health = 0;
		this.dead = false;
	}

	public void calcStep() {
		dx = 0;
		dy = 0;
		boolean directionEnabled = !direction.checkDisabled(direction);
		if (directionEnabled) {
			switch (direction) {
			case NORTH:
				dx = 0;
				dy = -speed;
				break;
			case NORTHEAST:
				dx = (int) (speed / Math.sqrt(2));
				dy = -(int) (speed / Math.sqrt(2));
				break;
			case EAST:
				dx = speed;
				dy = 0;
				break;
			case SOUTHEAST:
				dx = (int) (speed / Math.sqrt(2));
				dy = (int) (speed / Math.sqrt(2));
				break;
			case SOUTH:
				dx = 0;
				dy = speed;
				break;
			case SOUTHWEST:
				dx = -(int) (speed / Math.sqrt(2));
				dy = (int) (speed / Math.sqrt(2));
				break;
			case WEST:
				dx = -speed;
				dy = 0;
				break;
			case NORTHWEST:
				dx = -(int) (speed / Math.sqrt(2));
				dy = -(int) (speed / Math.sqrt(2));
				break;
			default:
				dx = 0;
				dy = 0;
			}

		}
		

	}

	public void move() {
		x += dx;
		y += dy;
	}

	public boolean collide(Rectangle rect2) {

		if (this.intersects(rect2)) {
			direction = direction.getOpposite(direction, wallHit);

			return true;
		}
		return false;
	}

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

	public void run() {
		
		calcStep();
		x += dx;
		y += dy;
		if (health <= 0) {
			dead = true;
		}
	}

	public void damage(int amount) {
		health -= amount;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public void setDirection(Direction d) {
		direction = d;
	}

	public double getSpeed() {
		return speed;
	}

	public double getDx() {
		return dx;
	}

	public double getDy() {
		return dy;
	}

	public int getHealth() {
		return health;
	}

	public boolean isDead() {
		return dead;
	}

	public Direction getDirection() {
		return direction;
	}

}
