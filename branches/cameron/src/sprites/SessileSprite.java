package sprites;

import main.Boundary;
import graphics.Images;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SessileSprite extends Boundary{
	protected BufferedImage image;
	
	public SessileSprite(int absoluteX, int absoluteY, Images images, int i) {
		super(absoluteX, absoluteY);
		this.image = images.getSessileImage(i);
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
	}
	
	public void draw(Graphics g, int i) {
		g.drawImage(image, absoluteX, absoluteY, null);
	}
}
