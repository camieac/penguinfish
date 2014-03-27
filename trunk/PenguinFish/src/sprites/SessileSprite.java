package sprites;

import java.awt.Graphics;
import java.awt.Rectangle;

import main.DataStore;

public class SessileSprite extends Rectangle {

	private static final long serialVersionUID = 1L;
	protected int id;

	public SessileSprite(int x, int y, int id) {
		super(x, y, DataStore.getInstance().images.getSessileImage(id)
				.getWidth(), DataStore.getInstance().images.getSessileImage(id)
				.getHeight());
		this.id = id;
	}

	public void draw(double x, double y, Graphics g, int i) {
		g.drawImage(DataStore.getInstance().images.getSessileImage(id),
				(int) x, (int) y, null);
	}
	public void setX(int x) {
		this.x = x;
		
	}
	public void setY(int y) {
		this.y = y;
		
	}
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("x: " + x + "\n");
		sb.append("y: " + y + "\n");
		String type;
		if(id == 0){
			type = "Tree";
		}else type = "Unknown";
		sb.append("Type: "+ type + "\n");
		return sb.toString();
	}

}
