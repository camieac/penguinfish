package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JComponent;

import main.DataStore;
import sprites.Bullet;
import sprites.Enemy;
import sprites.Item;
import sprites.SessileSprite;
import sprites.SpriteBlock;
import sprites.Wall;

/**
 * Represents the viewable area of the world, the camera follows the position of
 * the player. Only sprites within the viewable area are drawn.
 * 
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * @since September 2014
 * 
 */
public class Camera extends JComponent {

	private static final long serialVersionUID = -3395117504081297410L;

	/**
	 * Whether or not the camera is attached to the player. For the current
	 * implementation, the camera is always attached to the player.
	 */
	private boolean attached;

	/**
	 * The X and Y position of the camera.
	 */
	private double camX, camY;
	/**
	 * The width and height of the camera.
	 */
	private double width, height;

	/**
	 * @param x
	 *            x-position of the left edge of the camera.
	 * @param y
	 *            y-position of the top edge of the camera.
	 * @param w
	 *            Width of the camera.
	 * @param h
	 *            Height of the camera.
	 */
	public Camera(int x, int y, int w, int h) {
		camX = 0;
		camY = 0;
		width = w;
		height = h;
		attached = true;
	}

	/**
	 * Draws all the bullets that have been fired by the player.
	 * @param g
	 *            The {@link #java.awt.Graphics} object that the camera draws to.
	 */
	private void drawBullets(Graphics g) {
		for (Bullet b : DataStore.getInstance().bullets) {
			g.drawImage(b.getImage(), (int) (b.getX() - camX),
					(int) (b.getY() - camY), null);
		}
	}

	/**
	 * Draws the enemies in their correct location on the screen.
	 * @param g
	 *            The {@link #java.awt.Graphics} object that the camera draws to.
	 */
	private void drawEnemies(Graphics g) {
		for (Enemy enemy : DataStore.getInstance().level.getEnemies()) {
			if (isInFrame(enemy.getX(), enemy.getY(), enemy.getWidth(),
					enemy.getHeight())) {
				enemy.draw(enemy.getX() - camX, enemy.getY() - camY, g, 0);
			}
		}
	}

	/**
	 * Draws the inventory at the bottom of the screen.
	 * 
	 * @param g
	 *            The {@link #java.awt.Graphics} object that the camera draws to.
	 */
	private void drawInventory(Graphics g) {
		DataStore.getInstance().player.getInventory().displayInventory(g);

	}

	/**
	 * Draws all the items.
	 * @param g
	 *            The {@link #java.awt.Graphics} object that the camera draws to.
	 */
	private void drawItems(Graphics g) {
		for (Item item : DataStore.getInstance().level.getItems()) {
			item.draw(g, camX, camY);
		}
	}

	/**
	 * @param g
	 *            The {@link #java.awt.Graphics} object that the camera draws to.
	 */
	private void drawNotifications(Graphics g) {
		for (Notification n : DataStore.getInstance().level.getNotifications()) {
			if (n.isVisible()) {
				n.displayNotification(g);
			}
		}
	}

	/**
	 * @param g
	 *            The {@link #java.awt.Graphics} object that the camera draws to.
	 */
	private void drawSessileSprites(Graphics g) {
		//for (LinkedList<SessileSprite> sp : DataStore.getInstance().level.sessileSprites) {
			for (SessileSprite s : DataStore.getInstance().level.getSessileSprites()) {
				// if the sprite is in the camera area
				if (isInFrame(s.getX(), s.getY(), s.getWidth(), s.getHeight())) {
					s.draw(s.getX() - camX, s.getY() - camY, g, 0);
				}
			//}
		}
	}

	public int getHeight() {
		return (int) height;
	}

	public int getWidth() {
		return (int) width;
	}

	/**
	 * @param x
	 *            x-position of the left edge of the object being checked.
	 * @param y
	 *            y-position of the top edge of the object being checked.
	 * @param w
	 *            Width of the object being checked.
	 * @param h
	 *            Height of the object being checked.
	 * @return Whether a sprite is viewable or not.
	 */
	public boolean isInFrame(double x, double y, double w, double h) {
		if (x + w < camX)
			return false;
		else if (x > camX + width)
			return false;
		else if (y + h < camY)
			return false;
		else if (y > camY + height)
			return false;
		else
			return true;
	}

	/**
	 * Draws a white bar across the top of the screen.
	 * 
	 * @param g
	 *            The {@link #java.awt.Graphics} object that the camera draws to.
	 */
	private void paintBar(Graphics g) {
		// Draw a white bar across the top of the screen.
		Color oldColor = g.getColor();
		g.setColor(Color.white);
		g.fillRect(0, 0, DataStore.getInstance().panelWidth, 20);
		g.fillRect(0, 0, 500, 20);
		g.setColor(oldColor);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.BLUE);
		g.fillRect(-5000, -5000, 10000, 10000);
		g.setColor(Color.BLACK);
		drawOutsideBoundary(g);
		
		try {
			// If attached make the cam x and y be relative to the player
			if (attached) {
				camX = DataStore.getInstance().player.getX() - width / 2;
				camY = DataStore.getInstance().player.getY() - height / 2;
				// g.drawString("Camera x: " + camX + ", Y: " + camY, 100, 100);
			}

			// Draw the enemies in the correct position in the world.
			drawEnemies(g);
			//Draw Sprite Blocks
			drawSpriteBlocks(g);
			// Draw the background sprites in the correct position in the world.
			drawSessileSprites(g);
			// Draw items.
			drawItems(g);
			//Draw walls
			drawWalls(g);
			// Draw Player.
			DataStore.getInstance().player.drawPlayer(g,
					DataStore.getInstance().player.x - camX,
					DataStore.getInstance().player.y - camY);
			// Paint the white bar across the top of the screen.
			paintBar(g);
			// Paint the text to be displayed on the white bar.
			paintText(g);
			// Paint the health hearts on top of the white bar.
			paintHealth(g);
			// Draw Bullets
			drawBullets(g);
			/* Draw the inventory above everything */
			drawInventory(g);
			/* Notifications appear above inventory */
			drawNotifications(g);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void drawWalls(Graphics g) {
		for(Wall w : DataStore.getInstance().level.getWalls()){
			w.draw(g,camX,camY);
		}
		
	}

	private void drawSpriteBlocks(Graphics g) {
		for(SpriteBlock sb : DataStore.getInstance().level.getSpriteBlocks()){
			sb.draw(g,camX,camY);
		}
		
	}

	private void drawOutsideBoundary(Graphics g) {
		DataStore.getInstance().level.draw(g, camX, camY);
		
	}

	/**
	 * @param g
	 *            The {@link #java.awt.Graphics} object that the camera draws to.
	 */
	protected void paintHealth(Graphics g) {
		BufferedImage fullHeart = DataStore.getInstance().images.getFullHeart();
		BufferedImage emptyHeart = DataStore.getInstance().images
				.getEmptyHeart();
		if (DataStore.getInstance().player.getHealth() == 100) {
			g.drawImage(fullHeart, (int) width - 75, 1, this);
		} else if (DataStore.getInstance().player.getHealth() < 100
				&& DataStore.getInstance().player.getHealth() >= 90) {
			g.drawImage(emptyHeart, (int) width - 75, 1, this);
		} else if (DataStore.getInstance().player.getHealth() >= 80) {
			g.drawImage(fullHeart, (int) width - 65, 1, this);
		} else if (DataStore.getInstance().player.getHealth() < 80
				&& DataStore.getInstance().player.getHealth() >= 70) {
			g.drawImage(emptyHeart, (int) width - 65, 1, this);
		} else if (DataStore.getInstance().player.getHealth() >= 60) {
			g.drawImage(fullHeart, (int) width - 55, 1, this);
		} else if (DataStore.getInstance().player.getHealth() < 60
				&& DataStore.getInstance().player.getHealth() >= 50) {
			g.drawImage(emptyHeart, (int) width - 55, 1, this);
		} else if (DataStore.getInstance().player.getHealth() >= 40) {
			g.drawImage(fullHeart, (int) width - 45, 1, this);
		} else if (DataStore.getInstance().player.getHealth() < 40
				&& DataStore.getInstance().player.getHealth() >= 30) {
			g.drawImage(emptyHeart, (int) width - 45, 1, this);
		} else if (DataStore.getInstance().player.getHealth() >= 20) {
			g.drawImage(fullHeart, (int) width - 35, 1, this);
		} else if (DataStore.getInstance().player.getHealth() < 20
				&& DataStore.getInstance().player.getHealth() >= 10) {
			g.drawImage(emptyHeart, (int) width - 35, 1, this);
		} else if (DataStore.getInstance().player.getHealth() < 10) {
			g.setColor(Color.red);
			g.drawString("DEAD!", (int) width - 65, 10);
		}
	}

	/**
	 * @param g
	 *            The {@link #java.awt.Graphics} object that the camera draws to.
	 */
	protected void paintText(Graphics g) {
		g.setColor(Color.black);
		g.drawString("Avoid the red enemy!", 10, 10);
		g.drawString("Pace: " + DataStore.getInstance().pace, (int) width / 2,
				10);
		g.drawString("Life: ", (int) width - 100, 10);
		g.drawString("Time: " + DataStore.getInstance().currentLevelTime,
				(int) width - 300, 10);
	}

	/**
	 * Sets the height of the camera to the double value given.
	 * 
	 * @param height
	 *            The new desired height of the camera
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * Sets the width of the camera to the double value given.
	 * 
	 * @param width
	 *            The new desired width of the camera.
	 */
	public void setWidth(double width) {
		this.width = width;
	}

}