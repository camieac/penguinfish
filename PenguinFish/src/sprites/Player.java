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
		checkBoundary();
		calcStep();
		
		if (health <= 0) {
			dead = true;
		}
	}

	private void checkBoundary() {
		
		if (x < 0){
			direction.disableDirection(Direction.WEST);
			direction.disableDirection(Direction.NORTHWEST);
			direction.disableDirection(Direction.SOUTHWEST);
		}else{
			direction.enableDirection(Direction.WEST);
			direction.enableDirection(Direction.NORTHWEST);
			direction.enableDirection(Direction.SOUTHWEST);
		}
		if (y < 0){
			direction.disableDirection(Direction.NORTH);
			direction.disableDirection(Direction.NORTHEAST);
			direction.disableDirection(Direction.NORTHWEST);
		}else{
			direction.enableDirection(Direction.NORTH);
			direction.enableDirection(Direction.NORTHEAST);
			direction.enableDirection(Direction.NORTHWEST);
		}
		if (x > DataStore.getInstance().images.getBackground().getWidth() - (width/2)){
			direction.disableDirection(Direction.EAST);
			direction.disableDirection(Direction.NORTHEAST);
			direction.disableDirection(Direction.SOUTHEAST);
		}else{
			direction.enableDirection(Direction.EAST);
			direction.enableDirection(Direction.NORTHEAST);
			direction.enableDirection(Direction.SOUTHEAST);
		}
		if (y > DataStore.getInstance().images.getBackground().getHeight() - (height/2)){
			direction.disableDirection(Direction.SOUTH);
			direction.disableDirection(Direction.SOUTHWEST);
			direction.disableDirection(Direction.SOUTHEAST);
		}else{
			direction.enableDirection(Direction.SOUTH);
			direction.enableDirection(Direction.SOUTHWEST);
			direction.enableDirection(Direction.SOUTHEAST);
		}
		
	}


}
