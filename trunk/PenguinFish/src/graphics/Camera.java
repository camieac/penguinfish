package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JComponent;

import main.DataStore;
import main.Direction;
import sprites.Bullet;
import sprites.Enemy;
import sprites.SessileSprite;

public class Camera extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3395117504081297410L;
	double width, height;

	boolean moveX, moveY;
	Direction direction;
	LinkedList<Integer> buttons;
	double camX, camY;

	double backgroundWidth, backgroundHeight;

	boolean attached;

	public Camera(int x, int y, int w, int h) {
		buttons = new LinkedList<Integer>();
		camX = 0;
		camY = 0;

		width = w;
		height = h;

		attached = true;

	}

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

	public int getWidth() {
		return (int) width;
	}

	public void detach() {
		attached = false;
	}

	public int getHeight() {
		return (int) height;
	}

	public void attach() {
		attached = true;

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.fillRect(-5000, -5000, 10000, 10000);
		g.setColor(Color.BLACK);
		DataStore.getInstance().background.draw(g, camX, camY);
		try {
			// If attached make the cam x and y be relative to the player's
			if (attached) {
				camX = DataStore.getInstance().player.getX() - width / 2;
				camY = DataStore.getInstance().player.getY() - height / 2;
				// g.drawString("Camera x: " + camX + ", Y: " + camY, 100, 100);
			}

			// Draw the enemies in the correct position in the world
			for (Enemy enemy : DataStore.getInstance().enemies) {
				if (isInFrame(enemy.getX(), enemy.getY(), enemy.getWidth(),
						enemy.getHeight())) {
					enemy.draw(enemy.getX() - camX, enemy.getY() - camY, g, 0);
				}
			}

			// Draw the background sprites in the correct position in the world
			for (SessileSprite s : DataStore.getInstance().background.sessileSprites) {
				// if the sprite is in the camera area
				if (isInFrame(s.getX(), s.getY(), s.getWidth(), s.getHeight())) {
					s.draw(s.getX() - camX, s.getY() - camY, g, 0);
				}
			}
			// Draw Player
			DataStore.getInstance().player.drawPlayer(g,
					DataStore.getInstance().player.x - camX,
					DataStore.getInstance().player.y - camY);
			paintText(g);
			paintHealth(g);
			if (DataStore.getInstance().player.getHealth() < 10) {
				g.setColor(Color.red);
				g.drawString("DEAD!", (int) width - 65, 10);
			}
			// Draw Bullets
			for (Bullet b : DataStore.getInstance().bullets) {
				g.drawImage(b.getImage(), (int) b.getX(), (int) b.getY(), null);
			}
		} catch (Exception e) {
			// erm...
		}

	}

	protected void paintText(Graphics g) {
		g.setColor(Color.black);
		g.drawString("Avoid the red enemy!", 10, 10);
		g.drawString("Pace: " + DataStore.getInstance().pace, (int) width / 2,
				10);
		g.drawString("Life: ", (int) width - 100, 10);
	}

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

	public void keyPressed(KeyEvent e) {
		buttons.add(e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
		if (buttons.contains(e.getKeyCode()))
			buttons.remove(buttons.indexOf(e.getKeyCode()));
	}

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
			addBullet();

		// if(player.getDirection().checkDisabled(player.getDirection())){
		// player.setDirection(player.getDirection().getNormalOpposite(player.getDirection()));
		// player.setSpeed(0);
		// }

	}

	public void addBullet() {
		if (DataStore.getInstance().periodSinceLastFire >= 3) {
			// TODO: bullets seem to get deleted as soon as they spawn,
			// so they are probably colliding with the player instantly
			// and getting added to the remove array.
			Bullet b = new Bullet(DataStore.getInstance().player.getX() - camX,
					DataStore.getInstance().player.getY() - camY,
					DataStore.getInstance().player.getDirection(), 0);

			DataStore.getInstance().bullets.add(b);
			// TODO: Why are bullets rotated to 0? Just set the direction in the
			// constructor since they don't bounce.

			DataStore.getInstance().periodSinceLastFire = 0;
		}
	}

}

// Stuff that might be used

// protected void paintGameOver(Graphics g) {
// player.draw(g, 8);
// g.setColor(Color.black);
// g.drawString("Game Over", (width / 2) - 25, height / 2);
// }