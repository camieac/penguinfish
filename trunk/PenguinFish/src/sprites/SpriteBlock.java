package sprites;

import java.awt.Rectangle;
import java.io.Serializable;

/**
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * @since 30th March 2014
 */
public class SpriteBlock implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3896530601623946746L;
	private int x, y, w, h, img;

	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param img
	 */
	public SpriteBlock(int x, int y, int w, int h, int img) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.img = img;
	}

	/**
	 * @return Image number of the sprite block.
	 */
	public int getImgNumber() {
		return img;
	}

	/**
	 * @return Rectangle to contain the sprites.
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, w, h);
	}
}