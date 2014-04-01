package sprites;

import main.DataStore;
import main.Direction;

import graphics.Notification;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * 
 */
@SuppressWarnings("serial")
public class Player extends Sprite {
	protected int distance;

	/**
	 * @param x
	 *            x-position of the player.
	 * @param y
	 *            y-position of the player.
	 * @param d
	 *            Direction of the player.
	 * @param i
	 */
	public Player(int x, int y, Direction d, int i) {
		super(x, y, d, i);

		health = 100;
		speed = 5;

	}

	private void checkBoundary() {
		boolean leftEdgeBreach = x <= 0;
		boolean topEdgeBreach = y <= 0;
		boolean rightEdgeBreach = x >= DataStore.getInstance().images.getCurrentBackground().getWidth()
				- (width / 2);
		boolean bottomEdgeBreach = y >= DataStore.getInstance().images.getCurrentBackground().getHeight()
				- (height / 2);
		Direction edgeDirection = null;
		if(topEdgeBreach) edgeDirection = Direction.NORTH;
		if(rightEdgeBreach) edgeDirection = Direction.EAST;
		if(bottomEdgeBreach) edgeDirection = Direction.SOUTH;
		if(leftEdgeBreach) edgeDirection = Direction.WEST;
		if(topEdgeBreach && rightEdgeBreach) edgeDirection = Direction.NORTHEAST;
		if(topEdgeBreach && leftEdgeBreach) edgeDirection = Direction.NORTHWEST;
		if(bottomEdgeBreach && leftEdgeBreach) edgeDirection = Direction.SOUTHWEST;
		if(bottomEdgeBreach && rightEdgeBreach) edgeDirection = Direction.SOUTHEAST;

		if(edgeDirection != null){
		switch(edgeDirection){			
		case NORTH:
			direction.setDirections(true, true, false, false, false, false, false, true);
			break;
		case NORTHEAST:
			direction.setDirections(true, true, true, false, false, false, false, false);
			break;
		case EAST:
			direction.setDirections(false, true, true, true, false, false, false, false);
			break;
		case SOUTHEAST:
			direction.setDirections(false, false, true, true, true, false, false, false);
			break;
		case SOUTH:
			direction.setDirections(false, false, false, true, true, true, false, false);
			break;
		case SOUTHWEST:
			direction.setDirections(false, false, false, false, true, true, true, false);
			break;
		case WEST:
			direction.setDirections(false, false, false, false, false, true, true, true);
			break;
		case NORTHWEST:
			direction.setDirections(true, false, false, false, false, false, true, true);
			break;
		default:
			break;
		}
		}
	
		else{
			direction.enableDirection(Direction.NORTH);
			direction.enableDirection(Direction.NORTHEAST);
			direction.enableDirection(Direction.EAST);
			direction.enableDirection(Direction.SOUTHEAST);
			direction.enableDirection(Direction.SOUTH);
			direction.enableDirection(Direction.SOUTHWEST);
			direction.enableDirection(Direction.WEST);
			direction.enableDirection(Direction.NORTHWEST);
		}
	}

	/**
	 * 
	 */
	public void displayHelpNotification() {
		boolean overSessileSprite = false;
		f1: for (SessileSprite s : DataStore.getInstance().world
				.getSessileSprites()) {
			if (collide(s.getBounds())) {
				Notification n = new Notification("This is a sessile sprite\n"
						+ s.toString(), Color.black, Color.white);
				DataStore.getInstance().notifications.add(n);
				overSessileSprite = true;
				break f1;

			}
		}
		boolean overEnemy = false;
		f2: for (Enemy e : DataStore.getInstance().enemies) {
			if (collide(e.getBounds())) {
				Notification n = new Notification("This is an enemy\n"
						+ e.toString(), Color.black, Color.white);
				DataStore.getInstance().notifications.add(n);
				System.out.println("Help Notification for Enemy");
				overEnemy = true;
				break f2;

			}

		}
		if (!overSessileSprite && !overEnemy) {
			DataStore.getInstance().notifications.clear();
		}

	}

	/**
	 * @param g
	 * @param xcam
	 * @param ycam
	 */
	public void drawPlayer(Graphics g, double xcam, double ycam) {
		int imageNumber = direction.getInt();
		g.drawImage(DataStore.getInstance().images.getPlayer(imageNumber),
				(int) xcam, (int) ycam, null);
		g.drawString("pos: " + x + "," + y, (int) xcam, (int) ycam);
		g.drawString("step: " + dx + "," + dy, (int) xcam, (int) ycam - 15);
		DataStore.getInstance().notifications.getLast().displayPlayerText(g,
				(int) xcam, (int) ycam);
	}

	/**
	 * 
	 */
	public void tick() {
		checkBoundary();
		calcStep();

		if (health <= 0) {
			dead = true;
		}
	}

}
