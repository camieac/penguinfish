package sprites;

import main.DataStore;
import main.Direction;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class Player extends Sprite {
	protected int distance;

	public Player(int x, int y, Direction d, int i) {
		super(x, y, d, i);

		health = 100;
		speed = 5;

		x = 200;
		y = 200;
	}

	public void drawPlayer(Graphics g, double xcam, double ycam) {
		int imageNumber = direction.getInt();
		g.drawImage(DataStore.getInstance().images.getPlayer(imageNumber), (int)xcam,
				(int)ycam, null);
		g.drawString("pos: " + x + "," + y, (int)xcam,(int) ycam);
		g.drawString("step: " + dx + "," + dy, (int)xcam,(int) ycam - 15);
	}

	public void tick() {
		calcVelocity();
		if (health <= 0) {
			dead = true;
		}
	}

}
