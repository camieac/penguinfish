package PenguinFish;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SessileSprite extends Boundary{
	protected int width;
	protected int height;
	protected BufferedImage[] images;
	
	public SessileSprite(int x, int y, int X, int Y, BufferedImage[] images) {
		super(x,y,X, Y);
		this.images = images;
		this.width = images[0].getWidth();
		this.height = images[0].getHeight();
	}
	
	public void draw(Graphics g, int i) {
		g.drawImage(images[i], relativeX, relativeY, null);
	}
}
