package sprites;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import main.DataStore;

/**
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * 
 */
public class SessileSprite extends Rectangle implements Serializable {

	private static final long serialVersionUID = 1L;
//	protected int id;
	protected String name;

	/**
	 * @param x
	 * @param y
	 * @param id
	 */
//	public SessileSprite(int x, int y, int id) {
//		super(x, y, DataStore.getInstance().images.getSessileImage(id)
//				.getWidth(), DataStore.getInstance().images.getSessileImage(id)
//				.getHeight());
//		this.id = id;
//	}
	public SessileSprite(int x, int y, String name) {
		super(x, y, DataStore.getInstance().images.getSessileImage(name)
				.getWidth(), DataStore.getInstance().images.getSessileImage(name)
				.getHeight());
		this.name = name;
	}
	/**
	 * @param x
	 * @param y
	 * @param img
	 */
//	public SessileSprite(int x, int y, BufferedImage img) {
//		super(x, y, img
//				.getWidth(), img
//				.getHeight());
//	//	this.id = 0;
//		this.name = "Unknown";
//	}

	/**
	 * @param x
	 * @param y
	 * @param g
	 * @param i
	 */
	public void draw(double x, double y, Graphics g, int i) {
		g.drawImage(DataStore.getInstance().images.getSessileImage(name),
				(int) x, (int) y, null);
	}

	/**
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;

	}

	/**
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;

	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("x: " + x + "\n");
		sb.append("y: " + y + "\n");
		String type;
		type = name;
		sb.append("Type: " + type + "\n");
		return sb.toString();
	}

}
