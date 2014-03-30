package sprites;

import main.DataStore;
import main.Direction;

import graphics.Notification;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson, Stuart Thain
 *
 */
@SuppressWarnings("serial")
public class Player extends Sprite {
	protected int distance;

	/**
	 * @param x x-position of the player.
	 * @param y y-position of the player.
	 * @param d Direction of the player.
	 * @param i
	 */
	public Player(int x, int y, Direction d, int i) {
		super(x, y, d, i);

		health = 100;
		speed = 5;

		x = 200;
		y = 200;
	}

	/**
	 * @param g
	 * @param xcam
	 * @param ycam
	 */
	public void drawPlayer(Graphics g, double xcam, double ycam) {
		int imageNumber = direction.getInt();
		g.drawImage(DataStore.getInstance().images.getPlayer(imageNumber), (int)xcam,
				(int)ycam, null);
		g.drawString("pos: " + x + "," + y, (int)xcam,(int) ycam);
		g.drawString("step: " + dx + "," + dy, (int)xcam,(int) ycam - 15);
		DataStore.getInstance().notifications.getLast().displayPlayerText(g, (int) xcam, (int) ycam);
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
		if (x > DataStore.getInstance().images.getBackground(DataStore.getInstance().levelNumber).getWidth() - (width/2)){
			direction.disableDirection(Direction.EAST);
			direction.disableDirection(Direction.NORTHEAST);
			direction.disableDirection(Direction.SOUTHEAST);
		}else{
			direction.enableDirection(Direction.EAST);
			direction.enableDirection(Direction.NORTHEAST);
			direction.enableDirection(Direction.SOUTHEAST);
		}
		if (y > DataStore.getInstance().images.getBackground(DataStore.getInstance().levelNumber).getHeight() - (height/2)){
			direction.disableDirection(Direction.SOUTH);
			direction.disableDirection(Direction.SOUTHWEST);
			direction.disableDirection(Direction.SOUTHEAST);
		}else{
			direction.enableDirection(Direction.SOUTH);
			direction.enableDirection(Direction.SOUTHWEST);
			direction.enableDirection(Direction.SOUTHEAST);
		}
		
	}

	/**
	 * 
	 */
	public void displayHelpNotification() {
		boolean overSessileSprite = false;
f1:		for(SessileSprite s : DataStore.getInstance().world.getSessileSprites()){
			if(collide(s.getBounds())){
				Notification n = new Notification("This is a sessile sprite\n" + s.toString(), Color.black, Color.white);
				DataStore.getInstance().notifications.add(n);
				overSessileSprite =  true;
				break f1;
				
			}
		}
			boolean overEnemy = false;
			f2:		for(Enemy e : DataStore.getInstance().enemies){
						if(collide(e.getBounds())){
							Notification n = new Notification("This is an enemy\n" + e.toString(), Color.black, Color.white);
							DataStore.getInstance().notifications.add(n);
							System.out.println("Help Notification for Enemy");
							overEnemy =  true;
							break f2;
							
						}
			
		
			
		}
		if(!overSessileSprite && !overEnemy){
			DataStore.getInstance().notifications.clear();
		}
	
		
	}


}
