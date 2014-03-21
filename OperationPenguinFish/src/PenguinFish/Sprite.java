package PenguinFish;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Sprite extends SessileSprite{
	protected int health;
	protected Direction direction;
	protected Direction directionHit;
	protected int dx, dy, speed;
	protected boolean bounces;
	protected boolean dead;

	public Sprite(int x, int y, Direction d, BufferedImage[] images) {
		super(x,y, images);
		this.direction = d;
		this.health = 0;
		this.dead = false;
	}

	public void calcVelocity() {
		
		if(!direction.checkDisabled(direction)){
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
		}else{
			speed = 0;
			dx = 0;
			dy = 0;
		}
	}

	public boolean collide(Rectangle rect2) {
		rect = new Rectangle(relativeX, relativeY, width, height);
		if (rect.intersects(rect2)) {
			direction = direction.getOpposite(direction, directionHit);
			dy = 0;
			dx = 0;
			speed = 0;
			System.out.println("                        STOPPPPP");
			return true;
		}
		return false;
	}

	public void collideWalls(int xMax, int yMax, Camera camera) {
			
			if (relativeX < 0){
				directionHit = Direction.WEST;				
			}
			else if (relativeX + width > xMax){
				directionHit = Direction.EAST;	
			}
			else if (relativeY < 0){
				directionHit = Direction.NORTH;	
			}
			else if (relativeY + height > yMax){
				directionHit = Direction.SOUTH;	
			}
				if (bounces) {					
					direction = direction.getOpposite(direction,directionHit);
				} else {
					dead = true;
				}
			}

	public void run() {
		calcVelocity();
		relativeX += dx;
		relativeY += dy;
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
	public int getSpeed() {
		return this.speed;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public void setDirection(Direction d) {
		this.direction = d;
		this.width = images[d.getInt()].getWidth();
		this.height = images[d.getInt()].getHeight();
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}

	public int getHealth() {
		return health;
	}

	public boolean getDead() {
		return dead;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void collisionA(Rectangle rectA, Rectangle rectB){
		if(rectA.intersects(rectB)){
			//A bounces off B 
		}
	}
	
	public void collisionB(Rectangle rectA, Rectangle rectB){
		if(rectA.intersects(rectB)){
			//A is destroyed
		}
	}
	
//	public void collisionC(Rectangle rectB){
//		if(this.rect.intersects(rectB)){
//			//A stops moving (this is collision walls)
//			Direction playerDHit = direction;
//			
//		}
//	}
	
	public void collisionD(Rectangle rectA, Rectangle rectB){
		if(rectA.intersects(rectB)){
			//A slows down
		}
	}
}
