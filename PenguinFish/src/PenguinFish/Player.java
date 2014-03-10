package PenguinFish;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class Player {
	private Image defaultPlayerDown, defaultPlayerUp, defaultPlayerLeft, defaultPlayerRight;
	int distanceDown;
	int distanceLeft;
	int distanceRight;
	Direction playerDirection;
	int distanceUp;
	private Image hurtPlayerDown, hurtPlayerUp, hurtPlayerLeft, hurtPlayerRight,imageDead;
	int life = 100;
	private int playerSize = 30;
	
	boolean playerUp, playerDown, playerLeft, playerRight;
	
	
	private int playerX = 200, playerY = 200;
	public Player(){
		imageDead = new ImageIcon("res/img/CharacterDead.png").getImage();
		defaultPlayerDown = new ImageIcon("res/img/CharacterDefault.png").getImage();
		defaultPlayerUp = new ImageIcon("res/img/CharacterDefaultBack.png")
				.getImage();
		defaultPlayerLeft = new ImageIcon("res/img/CharacterDefaultLeft.png")
				.getImage();
		defaultPlayerRight = new ImageIcon("res/img/CharacterDefaultRight.png")
				.getImage();
		
		hurtPlayerDown = new ImageIcon("res/img/CharacterHurt.png").getImage();
		hurtPlayerUp = new ImageIcon("res/img/CharacterHurtBack.png").getImage();
		hurtPlayerLeft = new ImageIcon("res/img/CharacterHurtLeft.png")
				.getImage();
		hurtPlayerRight = new ImageIcon("res/img/CharacterHurtRight.png")
				.getImage();
	}
	public void drawPlayer(Graphics2D g2d, JPanel panel){
	// Draw player
			if (life >= 50) {
				if (playerUp == true) {
					g2d.drawImage(defaultPlayerUp, playerX, playerY, panel);
				} else if (playerDown == true) {
					g2d.drawImage(defaultPlayerDown, playerX, playerY, panel);
				} else if (playerLeft == true) {
					g2d.drawImage(defaultPlayerLeft, playerX, playerY, panel);
				} else if (playerRight == true) {
					g2d.drawImage(defaultPlayerRight, playerX, playerY, panel);
				} else {
					g2d.drawImage(defaultPlayerDown, playerX, playerY, panel);
				}
			}
	
	
	if (life < 50 && life > 0) {
		if (playerUp == true) {
			g2d.drawImage(hurtPlayerUp, playerX, playerY, panel);
		} else if (playerDown == true) {
			g2d.drawImage(hurtPlayerDown, playerX, playerY, panel);
		} else if (playerLeft == true) {
			g2d.drawImage(hurtPlayerLeft, playerX, playerY, panel);
		} else if (playerRight == true) {
			g2d.drawImage(hurtPlayerRight, playerX, playerY, panel);
		} else {
			g2d.drawImage(hurtPlayerDown, playerX, playerY, panel);
		}
	}
	}
	public int getDistanceDown(){
		return this.distanceDown;
	}
	public int getLife(){
		return this.life;
	}
	public boolean getPlayerDown(){
		return this.playerDown;
	}
	public boolean getPlayerLeft(){
		return this.playerLeft;
	}
	public boolean getPlayerRight(){
		return this.playerRight;
	}
	public int getPlayerSize() {
		return playerSize;
	}
	public boolean getPlayerUp(){
		return this.playerUp;
	}
	public int getPlayerX(){
		return this.playerX;
	}
	public int getPlayerY(){
		return this.playerY;
	}
	public void movePlayer(JPanel panel, boolean speedHeld){
		if (playerUp == true && playerY >= 15) {
			if (speedHeld) {
				playerY += 2 * distanceDown;
			} else
				playerY += distanceDown;
		}
		if (playerDown == true && playerY <= (panel.getHeight() - playerSize)) {
			if (speedHeld) {
				playerY += 2 * distanceUp;
			} else
				playerY += distanceUp;
		}
		if (playerLeft == true && playerX >= 0) {
			if (speedHeld) {
				playerX += 2 * distanceLeft;
			} else
				playerX += distanceLeft;
		}
		if (playerRight == true && playerX <= (panel.getWidth() - playerSize)) {
			if (speedHeld) {
				playerX += 2 * distanceRight;
			} else
				playerX += distanceRight;
		}
		
	}
	
	public void deductHealth() {
		life -= 10;
	}
	public void setLife(int life){
		this.life = life;
	}
	public void setPlayerDown(boolean p){
		this.playerDown = p;
	}
	public void setPlayerLeft(boolean p){
		this.playerLeft = p;
	}
	public void setPlayerRight(boolean p){
		this.playerRight = p;
	}
	public void setPlayerSize(int playerSize) {
		this.playerSize = playerSize;
	}
	public void setPlayerUp(boolean p){
		this.playerUp = p;
	}
	public void setPlayerX(int n){
		this.playerX = n;
	}
	public void setPlayerY(int n){
		this.playerY = n;
	}
	public void setupDistances(int baseDistance) {
		this.distanceUp = baseDistance;
		this.distanceDown = -baseDistance;
		this.distanceLeft = -baseDistance;
		this.distanceRight = baseDistance;
		
	}
	public Image getPlayerDeadImage() {	
		return imageDead;
	}
	public void setDirection(){
		
		if(playerUp){
			if(playerLeft){
				this.playerDirection = Direction.NORTHWEST;
			}
			if(playerRight){
				this.playerDirection = Direction.NORTHEAST;
			}
			else this.playerDirection = Direction.NORTH;
		}
		if(playerDown){
			if(playerLeft){
				this.playerDirection = Direction.SOUTHWEST;
			}
			if(playerRight){
				this.playerDirection = Direction.SOUTHEAST;
			}
			else this.playerDirection = Direction.SOUTH;
		}
		if(playerLeft){
			if(playerUp){
				this.playerDirection = Direction.NORTHWEST;
			}
			if(playerDown){
				this.playerDirection = Direction.SOUTHWEST;
			}
			else this.playerDirection = Direction.WEST;
			
		}
		if(playerRight){
			if(playerUp){
				this.playerDirection = Direction.NORTHEAST;
			}
			if(playerDown){
				this.playerDirection = Direction.SOUTHEAST;
			}
			else this.playerDirection = Direction.EAST;
		}
		
		
		
		
		
		
	}
	public Direction getDirection() {
		return playerDirection;
	}
	
}
