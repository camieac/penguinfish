package main;

import java.awt.Rectangle;

public class Boundary {
	protected int absoluteX;
	protected int absoluteY;
	protected int width;
	protected int height;
	protected Rectangle rect;
	
	public Boundary(int absoluteX, int absoluteY) {
		this.absoluteX = absoluteX;
		this.absoluteY = absoluteY;
		this.rect = createRect();
	}
	
	public void setAbsoluteX(int absoluteX) {
		this.absoluteX = absoluteX;
	}

	public void setAbsoluteY(int absoluteY) {
		this.absoluteY = absoluteY;
	}
	
	public void setWidth(int w) {
		this.width = w;
	}

	public void setHeight(int h) {
		this.height = h;
	}

	public int getAbsoluteX() {
		return absoluteX;
	}

	public int getAbsoluteY() {
		return absoluteY;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Rectangle createRect() {
		return new Rectangle(absoluteX, absoluteY, width, height);
	}
}
