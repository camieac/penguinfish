package PenguinFish;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Sprite {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int health;
	protected BufferedImage[] images;
	protected Direction direction;
	protected int dx, dy, speed;
	protected Rectangle rect;
	protected boolean bounces;

	public Sprite(int x, int y, Direction d, BufferedImage[] images) {
		this.x = x;
		this.y = y;
		this.images = images;
		this.width = images[0].getWidth();
		this.height = images[0].getHeight();
		this.direction = d;
		this.health = 0;
		
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealth() {
		return health;
	}

	public void damage(int amount) {
		health -= amount;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void draw(Graphics g, int i) {
		g.drawImage(images[i], x, y, null);
	}

	public void setX(int x) {
		this.x = x;
	}
	

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int w) {
		this.width = w;
	}

	public void setHeight(int h) {
		this.height = h;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction d) {
		this.direction = d;
		this.width = images[d.getInt()].getWidth();
		this.height = images[d.getInt()].getHeight();		
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void calcVelocity() {
		switch (direction) {
		case NORTH:
			dx = 0;
			dy = -speed;
			break;
		case NE:
			dx = (int) (speed / Math.sqrt(2));
			dy = -(int) (speed / Math.sqrt(2));
			break;
		case EAST:
			dx = speed;
			dy = 0;
			break;
		case SE:
			dx = (int) (speed / Math.sqrt(2));
			dy = (int) (speed / Math.sqrt(2));
			break;
		case SOUTH:
			dx = 0;
			dy = speed;
			break;
		case SW:
			dx = -(int) (speed / Math.sqrt(2));
			dy = (int) (speed / Math.sqrt(2));
			break;
		case WEST:
			dx = -speed;
			dy = 0;
			break;
		case NW:
			dx = -(int) (speed / Math.sqrt(2));
			dy = -(int) (speed / Math.sqrt(2));
			break;

		default:
			dx = 0;
			dy = 0;

		}
	}
	
	public void run(){
		calcVelocity();
		x += dx;
		y += dy;
		if(health == 0){
			direction = Direction.DEAD;			
		}
	}

	public boolean collide(Rectangle rect2){
		rect = new Rectangle(x, y, width, height);
		if (rect.intersects(rect2)){
			dx = -dx;
			dy = -dy;
			return true;	
		}
		return false;
	}
	
	public void collideWalls(int xMax, int yMax){
		if(bounces){
			if(x < 0 || x+width > xMax || y < 0 || y+height > yMax){
				dx = -dx;
				dy = -dy;
			}
		}else{
			direction = Direction.DEAD;
		}
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y, width, height);
	}

}
