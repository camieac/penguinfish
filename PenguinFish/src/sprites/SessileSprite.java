package sprites;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

import main.DataStore;

/**
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * 
 */
public class SessileSprite extends Rectangle implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int id;

	/**
	 * @param x
	 * @param y
	 * @param id
	 */
	public SessileSprite(int x, int y, int id) {
		super(x, y, DataStore.getInstance().images.getSessileImage(id)
				.getWidth(), DataStore.getInstance().images.getSessileImage(id)
				.getHeight());
		this.id = id;
	}

	/**
	 * @param x
	 * @param y
	 * @param g
	 * @param i
	 */
	public void draw(double x, double y, Graphics g, int i) {
		g.drawImage(DataStore.getInstance().images.getSessileImage(id),
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
		if (id == 0) {
			type = "Tree";
		} else if (id == 1)
			type = "Bush";
		else if (id == 2)
			type = "Hole";
		else
			type = "Unknown";
		sb.append("Type: " + type + "\n");
		return sb.toString();
	}

}
