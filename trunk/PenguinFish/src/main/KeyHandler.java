package main;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 * Handles all key presses for the game.
 * 
 * @author Cameron A. Craig
 * @since September 2014
 * 
 */
public class KeyHandler {
	/**
	 * A {@link #java.util.LinkedList} of all the buttons that are currently pressed down.
	 */
	private LinkedList<Integer> buttonsCurrentlyPressed;

	/**
	 * Set up {@link #buttonsCurrentlyPressed} as a new {@link #java.util.LinkedList}
	 */
	public KeyHandler() {
		buttonsCurrentlyPressed = new LinkedList<Integer>();
	}

	/**
	 * @param e
	 *            A {@link #java.awt.event.KeyEvent} representing a pressed key.
	 */
	public void keyPressed(KeyEvent e) {
		buttonsCurrentlyPressed.add(e.getKeyCode());
	}

	/**
	 * @param e
	 *            A {@link #java.awt.event.KeyEvent} representing a released key.
	 */
	public void keyReleased(KeyEvent e) {
		if (buttonsCurrentlyPressed.contains(e.getKeyCode()))
			buttonsCurrentlyPressed.remove(buttonsCurrentlyPressed.indexOf(e
					.getKeyCode()));
	}

	/**
	 * Handles all key presses. Checks the list of pressed keys and calls the
	 * necessary method(s) to execute the key's set command.
	 * 
	 */
	public void processKeys() {
		boolean moveKeyPressed = false;
		if (buttonsCurrentlyPressed.contains(KeyEvent.VK_UP)
				&& buttonsCurrentlyPressed.contains(KeyEvent.VK_LEFT)) {
			DataStore.getInstance().player.setDirection(Direction.NORTHWEST);
			moveKeyPressed = true;
		} else if (buttonsCurrentlyPressed.contains(KeyEvent.VK_UP)
				&& buttonsCurrentlyPressed.contains(KeyEvent.VK_RIGHT)) {
			DataStore.getInstance().player.setDirection(Direction.NORTHEAST);
			moveKeyPressed = true;
		} else if (buttonsCurrentlyPressed.contains(KeyEvent.VK_DOWN)
				&& buttonsCurrentlyPressed.contains(KeyEvent.VK_LEFT)) {
			DataStore.getInstance().player.setDirection(Direction.SOUTHWEST);
			moveKeyPressed = true;
		} else if (buttonsCurrentlyPressed.contains(KeyEvent.VK_DOWN)
				&& buttonsCurrentlyPressed.contains(KeyEvent.VK_RIGHT)) {
			DataStore.getInstance().player.setDirection(Direction.SOUTHEAST);
			moveKeyPressed = true;
		} else if (buttonsCurrentlyPressed.contains(KeyEvent.VK_UP)) {
			DataStore.getInstance().player.setDirection(Direction.NORTH);
			moveKeyPressed = true;
		} else if (buttonsCurrentlyPressed.contains(KeyEvent.VK_DOWN)) {
			DataStore.getInstance().player.setDirection(Direction.SOUTH);
			moveKeyPressed = true;
		} else if (buttonsCurrentlyPressed.contains(KeyEvent.VK_LEFT)) {
			DataStore.getInstance().player.setDirection(Direction.WEST);
			moveKeyPressed = true;
		} else if (buttonsCurrentlyPressed.contains(KeyEvent.VK_RIGHT)) {
			DataStore.getInstance().player.setDirection(Direction.EAST);
			moveKeyPressed = true;
		}
		if (moveKeyPressed) {
			DataStore.getInstance().player.move();
		}

		if (buttonsCurrentlyPressed.contains(KeyEvent.VK_F))
			// Fire a bullet
			DataStore.getInstance().player.fireBullet();

		if (buttonsCurrentlyPressed.contains(KeyEvent.VK_H)) {
			// Display help notification
			DataStore.getInstance().player.displayHelpNotification();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (buttonsCurrentlyPressed.contains(KeyEvent.VK_L)) {
			// Go forward a level
			DataStore.getInstance().world.nextLevel();
			// This sleep is a temporary fix to stop the level incrementing more
			// than once per button press.
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (buttonsCurrentlyPressed.contains(KeyEvent.VK_K)) {
			// Go back a level
			DataStore.getInstance().world.previousLevel();
			System.out.println("Level Number decremented");
			// This sleep is a temporary fix to stop the level incrementing more
			// than once per button press.
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (buttonsCurrentlyPressed.contains(KeyEvent.VK_P)) {
			// Pause game
			DataStore.getInstance().gameState = State.PAUSEMENU;
		}
		if (buttonsCurrentlyPressed.contains(KeyEvent.VK_I)) {
			// Toggle inventory display
			DataStore.getInstance().player.getInventory().setVisible(
					!DataStore.getInstance().player.getInventory().isVisible());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (buttonsCurrentlyPressed.contains(KeyEvent.VK_O)) {
			//Pick up an item
			DataStore.getInstance().player.pick();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
