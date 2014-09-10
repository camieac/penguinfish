package sprites;

import main.DataStore;
import main.Direction;
import main.Inventory;
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
	private Inventory inventory;

	/**
	 * @param x
	 *            x-position of the player.
	 * @param y
	 *            y-position of the player.
	 * @param d
	 *            Direction of the player.
	 * @param i
	 */
	public Player(int x, int y, Direction d, String name) {
		super(x, y, d, name);
		health = 100;
		speed = 5;
		inventory = new Inventory();


	}

	/**
	 * Checks for boundary breaches and disables all player directions that would cause the player to further breach that boundary.
	 */
	private void checkBoundary() {
		boolean leftEdgeBreach = x <= 0;
		boolean topEdgeBreach = y <= 0;
		boolean rightEdgeBreach = x >= DataStore.getInstance().images.getCurrentBackground().getWidth()
				- (width / 2);
		boolean bottomEdgeBreach = y >= DataStore.getInstance().images.getCurrentBackground().getHeight()
				- (height / 2);
		Direction edgeDirection = null;
		//Cannot be going north and south at the same time, hence the else if. Same applies to east and west.
		if(topEdgeBreach) edgeDirection = Direction.NORTH;
		if(bottomEdgeBreach) edgeDirection = Direction.SOUTH;
		if(rightEdgeBreach) edgeDirection = Direction.EAST;
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
		//if(edgeDirection != null) System.out.println("Direction: " + edgeDirection.toString());
	}

	/**
	 * 
	 */
	public void displayHelpNotification() {
		
		if(DataStore.getInstance().helpNotification.isVisible()){
			DataStore.getInstance().helpNotification.setVisible(false);
		}else{
			
		boolean overSessileSprite = false;
		f1: for (SessileSprite s : DataStore.getInstance().level
				.getSessileSprites()) {
			if (collide(s.getBounds())) {
				Notification n = new Notification("This is a sessile sprite\n"
						+ s.toString(), Color.black, Color.white);
				n.setVisible(true);
				DataStore.getInstance().helpNotification = n;
				overSessileSprite = true;
				break f1;

			}
		}
		boolean overEnemy = false;
		f2: for (Enemy e : DataStore.getInstance().level.getEnemies()) {
			if (collide(e.getBounds())) {
				Notification n = new Notification("This is an enemy\n"
						+ e.toString(), Color.black, Color.white);
				n.setVisible(true);
				DataStore.getInstance().helpNotification = n;
				System.out.println("Help Notification for Enemy");
				overEnemy = true;
				break f2;

			}

		}
		if (!overSessileSprite && !overEnemy) {
			DataStore.getInstance().helpNotification = new Notification("No Help Available", Color.RED, Color.WHITE);
			DataStore.getInstance().helpNotification.setVisible(true);
		}
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
		if(DataStore.getInstance().helpNotification.isVisible()){
			DataStore.getInstance().helpNotification.displayPlayerText(g,
					(int) xcam, (int) ycam);
		}

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

	/**
	 * Adds a bullet to the LinkedList of bullets, stored in the DataStore.
	 * 
	 */
	public void fireBullet() {
		if (DataStore.getInstance().periodSinceLastFire >= 3) {
			double x = 0;
			double y = 0;
			Direction playerDirection = DataStore.getInstance().player.getDirection();
			double playerXcenter = DataStore.getInstance().player.getX()+10;//+ (width/2);
			double playerYcenter = DataStore.getInstance().player.getY()+10;//+ (height/2);


			int offsetY = height/4;
			int offsetX = width/4;
			switch(playerDirection){
			case NORTH:
				x = playerXcenter;
				y = playerYcenter - offsetY;
				break;
			case NORTHEAST:
				x = playerXcenter + offsetX;
				y = playerYcenter - offsetY;
				break;
			case EAST:
				x = playerXcenter + offsetX;
				y = playerYcenter;
				break;
			case SOUTHEAST:
				x = playerXcenter + offsetX;
				y = playerYcenter + offsetY;
				break;
			case SOUTH:
				x = playerXcenter;
				y = playerYcenter + offsetY;
				break;
			case SOUTHWEST:
				x = playerXcenter - offsetX;
				y = playerYcenter + offsetY;
				break;
			case WEST:
				x = playerXcenter - offsetX;
				y = playerYcenter;
				break;
			case NORTHWEST:
				x = playerXcenter - offsetX;
				y = playerYcenter - offsetY;
				break;

			default:
				break;

			}
			Bullet b = new Bullet(x,y,playerDirection,"bullet");

			DataStore.getInstance().bullets.add(b);


			DataStore.getInstance().periodSinceLastFire = 0;
		}
	}

	/**
	 * @return
	 */
	public Inventory getInventory() {
		return inventory;
		
	}

	/**
	 * 
	 */
	public void pick() {
		
		f1: for (Item i : DataStore.getInstance().level.getItems()){
			if (collide(i.getBounds())) {
				//Item over = i;
				i.setPicked(true);
				
				break f1;

			}
		}
		
	}

}
