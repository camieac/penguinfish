package PenguinFish;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

public class Enemy {
	private boolean hit;
	private int distanceUp, distanceDown, distanceLeft, distanceRight;
	private Image image;
	private int enemySize;
	private int enemyX, enemyY;
	int aggroRadius;
	int aggroTimeRemaining;
	int baseAggroTime = 100;
	Direction directionX, directionY;
	Random rand = new Random();

	//Constructor
	public Enemy(int enemyX, int enemyY, Image enemy) {
		this.enemyX = enemyX;
		this.enemyY = enemyY;
		this.enemySize = 10;
		this.image = enemy;

		directionX = Direction.getRandomX();
		directionY = Direction.getRandomY();

		aggroRadius = 30;

		hit = false;

	}

	//draw enemy on gui
	public void drawEnemy(Game game, Graphics2D g2d) {
		g2d.drawImage(image, enemyX, enemyY, game);
	}

	//Getters start
	public int getDistanceDown() {
		return distanceDown;
	}

	public int getDistanceLeft() {
		return distanceLeft;
	}

	public int getDistanceRight() {
		return distanceRight;
	}

	public int getDistanceUp() {
		return distanceUp;
	}

	public Image getEnemy() {
		return image;
	}

	public int getEnemySize() {
		return enemySize;
	}

	public int getEnemyX() {
		return enemyX;
	}

	public int getEnemyY() {
		return enemyY;
	}
	//Getters end

	//Setters start
	public void setDistanceDown(int distanceDown) {
		this.distanceDown = distanceDown;
	}

	public void setDistanceLeft(int distanceLeft) {
		this.distanceLeft = distanceLeft;
	}

	public void setDistanceRight(int distanceRight) {
		this.distanceRight = distanceRight;
	}

	public void setDistanceUp(int distanceUp) {
		this.distanceUp = distanceUp;
	}

	public void setEnemy(Image enemy) {
		this.image = enemy;
	}

	public void setEnemySize(int enemySize) {
		this.enemySize = enemySize;
	}

	public void setEnemyX(int enemyX) {
		this.enemyX = enemyX;
	}

	public void setEnemyY(int enemyY) {
		this.enemyY = enemyY;
	}
	//Setters end

	//
	public void setupDistances(int baseDistance) {
		this.distanceUp = baseDistance * (rand.nextInt(20)) / 10;
		this.distanceDown = -baseDistance * (rand.nextInt(20)) / 10;
		this.distanceLeft = -baseDistance * (rand.nextInt(20)) / 10;
		this.distanceRight = baseDistance * (rand.nextInt(20)) / 10;
	}

	public void moveRight() {
		this.enemyX += distanceRight;

	}

	public void moveLeft() {
		this.enemyX += distanceLeft;

	}

	public void moveDown() {
		this.enemyY += distanceUp;

	}

	public void moveUp() {
		this.enemyY += distanceDown;

	}

	public void aggression(int playerX, int playerY, int playerSize,
			int aggroRadius, Game game) {
		System.out.println("Aggro Engaged");
		// Move enemy to the right
		if (directionX == Direction.EAST) {
			if (enemyX >= (playerX + playerSize + aggroRadius)) {
				directionX = Direction.WEST;
			}
		} // Move enemy to the left
		else {
			if (enemyX <= (playerX - (playerSize + aggroRadius))) {
				directionX = Direction.EAST;
			}
		}

		// Move enemy down
		if (directionY == Direction.SOUTH) {
			if (enemyY >= (playerY + playerSize + aggroRadius)) {
				directionY = Direction.NORTH;
			}
		} else {
			// Move enemy up
			if (enemyY <= (playerY - (playerSize + aggroRadius))) {
				directionY = Direction.SOUTH;
			}
		}

	}

	public void hitPlayer(Collision collision, Player player, Game game) {

		// Enemy Hits player on right
		if (enemyX >= (player.getPlayerX() + player.getPlayerSize() - 5)
				&& enemyX <= (player.getPlayerX() + player.getPlayerSize())
				&& enemyY >= (player.getPlayerY() - enemySize)
				&& enemyY <= (player.getPlayerY() + player.getPlayerSize())) {
			directionX = Direction.EAST;
			hit = true;
		}

		// Enemy hits player on the left
		if (enemyX >= (player.getPlayerX() - enemySize)
				&& enemyX <= (player.getPlayerX() - enemySize + 5)
				&& enemyY >= (player.getPlayerY() - enemySize)
				&& enemyY <= (player.getPlayerY() + player.getPlayerSize())) {
			directionX = Direction.WEST;
			hit = true;
		}

		// Enemy hits player on the bottom
		if (enemyY >= (player.getPlayerY() + player.getPlayerSize() - 5)
				&& enemyY <= (player.getPlayerY() + player.getPlayerSize())
				&& enemyX >= (player.getPlayerX() - enemySize)
				&& enemyX <= (player.getPlayerX() + player.getPlayerSize())) {
			directionY = Direction.SOUTH;
			hit = true;
		}

		// Enemy hits player on the top
		if (enemyY >= (player.getPlayerY() - enemySize)
				&& enemyY <= (player.getPlayerY() - enemySize + 5)
				&& enemyX >= (player.getPlayerX() - enemySize)
				&& enemyX <= (player.getPlayerX() + player.getPlayerSize())) {
			directionY = Direction.NORTH;
			hit = true;
		}
		if (hit) {
			System.out.println("Player hit enemy");
			aggroTimeRemaining = baseAggroTime;
			player.deductHealth();
			hit = false;
		}

	}

	//ememy movement
	public void moveEnemy(Player player, Game game) {
		if (aggroTimeRemaining > 0) {
				aggression(player.getPlayerX(), player.getPlayerY(), player.getPlayerSize(), aggroRadius, game);
			aggroTimeRemaining--;
		}
		// Move enemy to right
		if (directionX == Direction.EAST) {
			moveRight();
		} 
		// Move enemy to the left
		if (directionX == Direction.WEST) {
			moveLeft();
		}
		// Move enemy down
		if (directionY == Direction.SOUTH) {
			moveDown();
		}
		// Move enemy up
		if (directionY == Direction.NORTH) {
			
			moveUp();
		}
	}

	//enemy hits a wall
	public void hitWall(Collision collision, Player player, Game game) {
		boolean hitOccurred = false;
		Direction[] newDirections = collision.collisionWalls(enemyX, enemyY,
				enemySize, enemySize, directionX, directionY);
		directionX = newDirections[0];
		directionY = newDirections[1];

	}

	//two enemies collide
	public void enemyHitsEnemy(Collision collision) {

		// enemy.getMovement2()[0] = leftRight[i];
		// enemy.getMovement2()[1] = topBottom[i];
		collision.collisionObjects(enemyX, enemyY, enemySize, enemySize,
				enemyX, enemyY, enemySize, enemySize, directionX, directionY);
		// leftRight[i] = enemy.getMovement2()[0];
		// topBottom[i] = enemy.getMovement2()[1];

	}
	// if (Math.abs(enemyX - player.getPlayerX()) >= 80
	// && Math.abs(enemyY - player.getPlayerY()) >= 80) {
	// //System.out.println("Wall hit");
	// hitOccurred = true;
	// }
	// if (hitOccurred) {
	// //engageAggro(player.getPlayerX(), player.getPlayerY(),
	// // player.getPlayerSize(), aggroRadius, game);
	// hitOccurred = false;
	// }
}