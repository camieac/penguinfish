package PenguinFish;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SessileSprite extends Rectangles{
	protected BufferedImage[] images;
	
	public SessileSprite(int relX, int relY, int absX, int absY, BufferedImage[] images){
		super(relX,relY, absX, absY);
		this.images = images;
	}
	
	public void draw(Graphics g, int i) {
		g.drawImage(images[i], relativeX, relativeY, null);
	}
}
