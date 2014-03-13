package PenguinFish;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Sprite {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int health;
	protected BufferedImage[] images;
	Direction direction;

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
	}
}
