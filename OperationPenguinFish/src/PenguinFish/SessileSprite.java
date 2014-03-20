package PenguinFish;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SessileSprite {
	protected int relativeX;
	protected int relativeY;
	protected int absoluteX;
	protected int absoluteY;
	protected int width;
	protected int height;
	protected BufferedImage[] images;
	protected Rectangle rect;
	
	public SessileSprite(int x, int y, BufferedImage[] images) {
		this.relativeX = x;
		this.relativeY = y;
		absoluteX = 400;
		absoluteY = 400;
		this.images = images;
		this.width = images[0].getWidth();
		this.height = images[0].getHeight();
		this.rect = getRect();
	}
	
	public int getAbsoluteX() {
		return absoluteX;
	}

	public void setAbsoluteX(int absoluteX) {
		this.absoluteX = absoluteX;
	}

	public int getAbsoluteY() {
		return absoluteY;
	}

	public void setAbsoluteY(int absoluteY) {
		this.absoluteY = absoluteY;
	}

	public void draw(Graphics g, int i) {
		g.drawImage(images[i], relativeX, relativeY, null);
	}
	
	public void setX(int x) {
		this.relativeX = x;
	}

	public void setY(int y) {
		this.relativeY = y;
	}

	public void setWidth(int w) {
		this.width = w;
	}

	public void setHeight(int h) {
		this.height = h;
	}
	
	public int getX() {
		return relativeX;
	}

	public int getY() {
		return relativeY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Rectangle getRect() {
		return new Rectangle(relativeX, relativeY, width, height);
	}
}
