package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JComponent;

import main.DataStore;
import main.Direction;
import main.State;
import sprites.Bullet;
import sprites.Enemy;
import sprites.SessileSprite;

/**
 * Represents the viewable area of the world, the camera follows the position of
 * the player. Only sprites within the viewable area are drawn. Handles Key
 * Presses.
 * 
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * 
 */
public class Camera extends JComponent {

	/**
	 * Sets the width of the camera to the double value given.
	 * 
	 * @param width
	 *            The new desired width of the camera.
	 */
	public void setWidth(double width) {
		this.width = width;
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

	private static final long serialVersionUID = -3395117504081297410L;
	boolean attached;

	double backgroundWidth, backgroundHeight;
	LinkedList<Integer> buttons;
	double camX, camY;
	Direction direction;

	boolean moveX, moveY;

	double width, height;

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
		buttons = new LinkedList<Integer>();
		camX = 0;
		camY = 0;

		width = w;
		height = h;

		attached = true;

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
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		buttons.add(e.getKeyCode());
	}

	/**
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		if (buttons.contains(e.getKeyCode()))
			buttons.remove(buttons.indexOf(e.getKeyCode()));
	}

	public void paintComponent(Graphics g) {
		// System.out.println("Painting components");

		// nc.displayPlayerText(g, "Hello", Color.black, Color.white);
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.fillRect(-5000, -5000, 10000, 10000);
		g.setColor(Color.BLACK);
		DataStore.getInstance().world.draw(g, camX, camY);
		try {
			// If attached make the cam x and y be relative to the player's
			if (attached) {
				camX = DataStore.getInstance().player.getX() - width / 2;
				camY = DataStore.getInstance().player.getY() - height / 2;
				// g.drawString("Camera x: " + camX + ", Y: " + camY, 100, 100);
			}
			for(Notification n : DataStore.getInstance().notifications){
				if(n.isVisible()){
					n.displayNotification(g);
				}
			}
			
			// Draw the enemies in the correct position in the world
			for (Enemy enemy : DataStore.getInstance().enemies) {
				if (isInFrame(enemy.getX(), enemy.getY(), enemy.getWidth(),
						enemy.getHeight())) {
					enemy.draw(enemy.getX() - camX, enemy.getY() - camY, g, 0);
				}
			}

			// Draw the background sprites in the correct position in the world
			for (LinkedList<SessileSprite> sp : DataStore.getInstance().world.sessileSprites) {
				for (SessileSprite s : sp) {
					// if the sprite is in the camera area
					if (isInFrame(s.getX(), s.getY(), s.getWidth(),
							s.getHeight())) {
						s.draw(s.getX() - camX, s.getY() - camY, g, 0);
					}
				}
			}
			// Draw Player
			DataStore.getInstance().player.drawPlayer(g,
					DataStore.getInstance().player.x - camX,
					DataStore.getInstance().player.y - camY);
			paintBar(g);
			paintText(g);
			paintHealth(g);
			if (DataStore.getInstance().player.getHealth() < 10) {
				g.setColor(Color.red);
				g.drawString("DEAD!", (int) width - 65, 10);
			}
			// Draw Bullets
			for (Bullet b : DataStore.getInstance().bullets) {
				g.drawImage(b.getImage(), (int) (b.getX() - camX),
						(int) (b.getY() - camY), null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void paintBar(Graphics g) {
		// Draw a white bar across the top of the screen.
		Color oldColor = g.getColor();
		g.setColor(Color.white);
		g.fillRect(0, 0, DataStore.getInstance().panelWidth, 20);
		g.fillRect(0, 0, 500, 20);
		g.setColor(oldColor);

	}

	/**
	 * @param g
	 *            The Graphics object to to draw to.
	 */
	protected void paintHealth(Graphics g) {
		BufferedImage fullHeart = DataStore.getInstance().images.getFullHeart();
		BufferedImage emptyHeart = DataStore.getInstance().images
				.getEmptyHeart();
		if (DataStore.getInstance().player.getHealth() == 100) {
			g.drawImage(fullHeart, (int) width - 75, 1, this);
		}
		if (DataStore.getInstance().player.getHealth() < 100
				&& DataStore.getInstance().player.getHealth() >= 90) {
			g.drawImage(emptyHeart, (int) width - 75, 1, this);
		}
		if (DataStore.getInstance().player.getHealth() >= 80) {
			g.drawImage(fullHeart, (int) width - 65, 1, this);
		}
		if (DataStore.getInstance().player.getHealth() < 80
				&& DataStore.getInstance().player.getHealth() >= 70) {
			g.drawImage(emptyHeart, (int) width - 65, 1, this);
		}
		if (DataStore.getInstance().player.getHealth() >= 60) {
			g.drawImage(fullHeart, (int) width - 55, 1, this);
		}
		if (DataStore.getInstance().player.getHealth() < 60
				&& DataStore.getInstance().player.getHealth() >= 50) {
			g.drawImage(emptyHeart, (int) width - 55, 1, this);
		}
		if (DataStore.getInstance().player.getHealth() >= 40) {
			g.drawImage(fullHeart, (int) width - 45, 1, this);
		}
		if (DataStore.getInstance().player.getHealth() < 40
				&& DataStore.getInstance().player.getHealth() >= 30) {
			g.drawImage(emptyHeart, (int) width - 45, 1, this);
		}
		if (DataStore.getInstance().player.getHealth() >= 20) {
			g.drawImage(fullHeart, (int) width - 35, 1, this);
		}
		if (DataStore.getInstance().player.getHealth() < 20
				&& DataStore.getInstance().player.getHealth() >= 10) {
			g.drawImage(emptyHeart, (int) width - 35, 1, this);
		}
	}

	protected void paintText(Graphics g) {
		g.setColor(Color.black);
		g.drawString("Avoid the red enemy!", 10, 10);
		g.drawString("Pace: " + DataStore.getInstance().pace, (int) width / 2,
				10);
		g.drawString("Life: ", (int) width - 100, 10);
		g.drawString("Time: " + DataStore.getInstance().currentLevelTime, (int) width - 300, 10);
	}

	/**
	 * Handles all key presses.+ (height/2)
	 * 
	 */
	protected void processKeys() {
		boolean moveKeyPressed = false;
		if (buttons.contains(KeyEvent.VK_UP)
				&& buttons.contains(KeyEvent.VK_LEFT)) {
			DataStore.getInstance().player.setDirection(Direction.NORTHWEST);
			moveKeyPressed = true;
		} else if (buttons.contains(KeyEvent.VK_UP)
				&& buttons.contains(KeyEvent.VK_RIGHT)) {
			DataStore.getInstance().player.setDirection(Direction.NORTHEAST);
			moveKeyPressed = true;
		} else if (buttons.contains(KeyEvent.VK_DOWN)
				&& buttons.contains(KeyEvent.VK_LEFT)) {
			DataStore.getInstance().player.setDirection(Direction.SOUTHWEST);
			moveKeyPressed = true;
		} else if (buttons.contains(KeyEvent.VK_DOWN)
				&& buttons.contains(KeyEvent.VK_RIGHT)) {
			DataStore.getInstance().player.setDirection(Direction.SOUTHEAST);
			moveKeyPressed = true;
		} else if (buttons.contains(KeyEvent.VK_UP)) {
			DataStore.getInstance().player.setDirection(Direction.NORTH);
			moveKeyPressed = true;
		} else if (buttons.contains(KeyEvent.VK_DOWN)) {
			DataStore.getInstance().player.setDirection(Direction.SOUTH);
			moveKeyPressed = true;
		} else if (buttons.contains(KeyEvent.VK_LEFT)) {
			DataStore.getInstance().player.setDirection(Direction.WEST);
			moveKeyPressed = true;
		} else if (buttons.contains(KeyEvent.VK_RIGHT)) {
			DataStore.getInstance().player.setDirection(Direction.EAST);
			moveKeyPressed = true;
		}
		// System.out.println("    Move: " + moveKeyPressed);
		if (moveKeyPressed) {
			DataStore.getInstance().player.move();
		}

		if (buttons.contains(KeyEvent.VK_F))
			DataStore.getInstance().player.addBullet();

		if (buttons.contains(KeyEvent.VK_H)) {
			DataStore.getInstance().player.displayHelpNotification();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (buttons.contains(KeyEvent.VK_L)) {

			DataStore.getInstance().world.nextLevel();
//			DataStore.getInstance().levelStartTime = System.currentTimeMillis();
//			DataStore.getInstance().currentLevelTime = 0;
			System.out.println("Level Number incremented, time set to 0");
			// This sleep is a temporary fix to stop the level incrementing more
			// than once per button press.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (buttons.contains(KeyEvent.VK_K)) {
			DataStore.getInstance().world.previousLevel();
			System.out.println("Level Number decremented");
			// This sleep is a temporary fix to stop the level incrementing more
			// than once per button press.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (buttons.contains(KeyEvent.VK_P)) {
			DataStore.getInstance().gameState = State.PAUSEMENU;
		}

	}

}

// Stuff that might be used

// protected void paintGameOver(Graphics g) {
// player.draw(g, 8);
// g.setColor(Color.black);
// g.drawString("Game Over", (width / 2) - 25, height / 2);
// }