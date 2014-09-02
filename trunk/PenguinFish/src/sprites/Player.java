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
			//Top Edge - everything with north in it.
//			System.out.println("TOP EDGE BREACH");
//			direction.disableDirection(Direction.NORTHWEST);
//			direction.disableDirection(Direction.NORTH);
//			direction.disableDirection(Direction.NORTHEAST);
			direction.setDirections(true, true, false, false, false, false, false, true);
			break;
		case NORTHEAST:
//			System.out.println("TOP RIGHT EDGE BREACH");
//			direction.disableDirection(Direction.NORTH);
//			direction.disableDirection(Direction.NORTHEAST);
//			direction.disableDirection(Direction.EAST);
			direction.setDirections(true, true, true, false, false, false, false, false);
			break;
		case EAST:
			 // Right edge - everything with east in it.
//			System.out.println("RIGHT EDGE BREACH");
//			direction.disableDirection(Direction.NORTHEAST);
//			direction.disableDirection(Direction.EAST);
//			direction.disableDirection(Direction.SOUTHEAST);
			direction.setDirections(false, true, true, true, false, false, false, false);
			break;
		case SOUTHEAST:
//			System.out.println("BOTTOM RIGHT EDGE BREACH");
//			direction.disableDirection(Direction.EAST);
//			direction.disableDirection(Direction.SOUTHEAST);
//			direction.disableDirection(Direction.SOUTH);
			direction.setDirections(false, false, true, true, true, false, false, false);
			break;
		case SOUTH:
			//Bottom Edge - everything with south in it.
//			System.out.println("BOTTOM EDGE BREACH");
//			direction.disableDirection(Direction.SOUTHEAST);
//			direction.disableDirection(Direction.SOUTH);
//			direction.disableDirection(Direction.SOUTHWEST);
			direction.setDirections(false, false, false, true, true, true, false, false);
			break;
		case SOUTHWEST:
//			System.out.println("BOTTOM LEFT EDGE BREACH");
//			direction.disableDirection(Direction.SOUTH);
//			direction.disableDirection(Direction.SOUTHWEST);
//			direction.disableDirection(Direction.WEST);
			direction.setDirections(false, false, false, false, true, true, true, false);
			break;
		case WEST:
			 //Left Edge - everything with west in it
//			System.out.println("LEFT EDGE BREACH");
//			direction.disableDirection(Direction.SOUTHWEST);
//			direction.disableDirection(Direction.WEST);
//			direction.disableDirection(Direction.NORTHWEST);
			direction.setDirections(false, false, false, false, false, true, true, true);
			break;
		case NORTHWEST:
//			System.out.println("TOP LEFT EDGE BREACH");
//			direction.disableDirection(Direction.WEST);
//			direction.disableDirection(Direction.NORTHWEST);
//			direction.disableDirection(Direction.NORTH);
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
		if(!DataStore.getInstance().notifications.isEmpty()){
			DataStore.getInstance().notifications.getLast().displayPlayerText(g,
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
	public void addBullet() {//TODO: Make the bullet spawn in a different place based on the position of the player.
		if (DataStore.getInstance().periodSinceLastFire >= 3) {
			//System.out.println("Add bullet");
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
			Bullet b = new Bullet(x,y,playerDirection,0);

			DataStore.getInstance().bullets.add(b);
			
			
			DataStore.getInstance().periodSinceLastFire = 0;
		}
	}

}
