package PenguinFish;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SessileSprite extends Boundary{
	protected int width;
	protected int height;
	protected BufferedImage[] images;
	
	public SessileSprite(int absoluteX, int absoluteY, BufferedImage[] images) {
		super(absoluteX, absoluteY);
		this.images = images;
		this.width = images[0].getWidth();
		this.height = images[0].getHeight();
	}
	
	public void draw(Graphics g, int i) {
		g.drawImage(images[i], absoluteX,absoluteY, null);
	}
}
