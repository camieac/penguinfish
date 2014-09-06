package main;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class KeyHandler {
	/**
	 * 
	 */
	private LinkedList<Integer> buttons;
	public KeyHandler(){
		buttons = new LinkedList<Integer>();
	}
	
	
	
	/**
	 * Handles all key presses.
	 * 
	 */
	public void processKeys() {
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
		if (moveKeyPressed) {
			DataStore.getInstance().player.move();
		}

		if (buttons.contains(KeyEvent.VK_F))
			// Fire a bullet
			DataStore.getInstance().player.addBullet();

		if (buttons.contains(KeyEvent.VK_H)) {
			// Display help notification
			DataStore.getInstance().player.displayHelpNotification();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (buttons.contains(KeyEvent.VK_L)) {
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
		if (buttons.contains(KeyEvent.VK_K)) {
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
		if (buttons.contains(KeyEvent.VK_P)) {
			// Pause game
			DataStore.getInstance().gameState = State.PAUSEMENU;
		}
		if (buttons.contains(KeyEvent.VK_I)) {
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
		if (buttons.contains(KeyEvent.VK_O)) {
			DataStore.getInstance().player.pick();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

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

}
