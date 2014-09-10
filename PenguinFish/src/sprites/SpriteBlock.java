package sprites;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

import main.DataStore;

/**
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * @since 30th March 2014
 */
public class SpriteBlock {
	/**
	 * 
	 */

	private int x, y, w, h;
	private BufferedImage img;
	private ArrayList<SessileSprite> sprites;
	private String name;

	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param img
	 */
//	public SpriteBlock(int x, int y, int w, int h, BufferedImage img) {
//		this.x = x;
//		this.y = y;
//		this.w = w;
//		this.h = h;
//		this.img = img;
//		this.sprites = new ArrayList<SessileSprite>();
//	}

	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param img
	 */
	public SpriteBlock(int x, int y, int w, int h, String name) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.img = DataStore.getInstance().images.getSessileImage(name);
		this.sprites = new ArrayList<SessileSprite>();
		this.name = name;
	}

	/**
	 * @return Rectangle to contain the sprites.
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, w, h);
	}

	/**
	 * @param g
	 * @param camY 
	 * @param camX 
	 */
	public void draw(Graphics g, double camX, double camY) {
		Rectangle rect = getRect();

		for (int j = 0; j < rect.height / img.getHeight(); j++) {
			for (int i = 0; i < rect.width / img.getWidth(); i++) {
				sprites.add(new SessileSprite(rect.x + (img.getWidth() * i),
						rect.y + (img.getHeight() * j), name));
			}
		}
		for(SessileSprite ss : sprites){
			ss.draw(ss.getX() - camX, ss.getY() - camY, g, 0);
			
		}

	}
}