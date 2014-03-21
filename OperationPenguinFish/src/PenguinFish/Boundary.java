package PenguinFish;

import java.awt.Rectangle;

public class Boundary {
	protected int relativeX;
	protected int relativeY;
	protected int absoluteX;
	protected int absoluteY;
	protected int width;
	protected int height;
	protected Rectangle rect;
	
	public Boundary(int relX, int relY, int absX, int absY) {
		this.relativeX = relX;
		this.relativeY = relY;
		this.absoluteX = absX;
		this.absoluteY = absY;
		this.rect = createRect();
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
	
	public Rectangle createRect() {
		return new Rectangle(relativeX, relativeY, width, height);
	}
}
