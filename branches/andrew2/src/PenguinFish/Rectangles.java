package PenguinFish;

import java.awt.Rectangle;

public class Rectangles {
	protected int relativeX;
	protected int relativeY;
	protected int absoluteX;
	protected int absoluteY;
	protected int width;
	protected int height;
	protected Rectangle rect;
	
	public Rectangles(int relativeX, int relativeY, int absoluteX, int absoluteY) {
		this.relativeX = relativeX;
		this.relativeY = relativeY;
		this.absoluteY = absoluteY;
		this.absoluteX = absoluteX;
	}
	
	public void setAbsoluteX(int absoluteX) {
		this.absoluteX = absoluteX;
	}

	public void setAbsoluteY(int absoluteY) {
		this.absoluteY = absoluteY;
	}
	
	public void setrelativeX(int x) {
		this.relativeX = x;
	}

	public void setrelativeY(int y) {
		this.relativeY = y;
	}

	public void setWidth(int w) {
		this.width = w;
	}

	public void setHeight(int h) {
		this.height = h;
	}
	
	public void setRect(int relativeX, int relativeY, int width, int height) {
		this.rect = new Rectangle(relativeX, relativeY, width, height);
	}
	
	public int getAbsoluteX() {
		return absoluteX;
	}

	public int getAbsoluteY() {
		return absoluteY;
	}
	
	public int getrelativeX() {
		return relativeX;
	}

	public int getrelativeY() {
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
