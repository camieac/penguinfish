package PenguinFish;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class Player {
	private Image defaultPlayerDown, defaultPlayerUp, defaultPlayerLeft, defaultPlayerRight,
	defaultPlayerNW, defaultPlayerNE, defaultPlayerSW, defaultPlayerSE;
	int distance;
	Direction playerDirection;
	private Image hurtPlayerDown, hurtPlayerUp, hurtPlayerLeft, hurtPlayerRight,imageDead;
	int life = 100;
	private int playerSize = 30;
	private int playerX, playerY;
	boolean playerUp, playerDown, playerLeft, playerRight;
	boolean movePlayer;
	
	public Player(int w, int h){
		movePlayer = false;
		playerX = (w/2) - (playerSize/2);
		playerY = (h/2) - (playerSize/2);
		playerDirection = Direction.SOUTH;
		imageDead = new ImageIcon("res/img/CharacterDead.png").getImage();
		defaultPlayerDown = new ImageIcon("res/img/CharacterDefault.png").getImage();
		defaultPlayerUp = new ImageIcon("res/img/CharacterDefaultBack.png").getImage();
		defaultPlayerLeft = new ImageIcon("res/img/CharacterDefaultLeft.png").getImage();
		defaultPlayerRight = new ImageIcon("res/img/CharacterDefaultRight.png").getImage();
		defaultPlayerNW = new ImageIcon("res/img/CharacterDefaultNW.png").getImage();
		defaultPlayerNE = new ImageIcon("res/img/CharacterDefaultNE.png").getImage();
		defaultPlayerSW = new ImageIcon("res/img/CharacterDefaultSW.png").getImage();
		defaultPlayerSE = new ImageIcon("res/img/CharacterDefaultSE.png").getImage();
		hurtPlayerDown = new ImageIcon("res/img/CharacterHurt.png").getImage();
		hurtPlayerUp = new ImageIcon("res/img/CharacterHurtBack.png").getImage();
		hurtPlayerLeft = new ImageIcon("res/img/CharacterHurtLeft.png").getImage();
		hurtPlayerRight = new ImageIcon("res/img/CharacterHurtRight.png").getImage();
	}
	public void drawPlayer(Graphics2D g2d, JPanel panel){
		// Draw player
		Image image = defaultPlayerDown;
		if (life >=50){
			switch(playerDirection){
			case NORTH:image = defaultPlayerUp;
				break;
			case NORTHEAST:image = defaultPlayerNE;
				break;
			case EAST:image = defaultPlayerRight;
				break;
			case SOUTHEAST:image = defaultPlayerSE;
				break;
			case SOUTH:image = defaultPlayerDown;
				break;
			case SOUTHWEST:image = defaultPlayerSW;
				break;
			case WEST:image = defaultPlayerLeft;
				break;
			case NORTHWEST:image = defaultPlayerNW;
				break;
	}
		}
	if (life <= 50 && life > 0){
			switch(playerDirection){
			case NORTH:image = hurtPlayerUp;		
				break;
			case NORTHEAST:image = defaultPlayerNE;
				break;
			case EAST:image = hurtPlayerRight;
				break;
			case SOUTHEAST:image = defaultPlayerSE;
				break;
			case SOUTH:image = hurtPlayerDown;
				break;
			case SOUTHWEST:image =defaultPlayerSW;
				break;
			case WEST:image = hurtPlayerLeft;
				break;
			case NORTHWEST:image = defaultPlayerNW;
				break;
			}
	}
	g2d.drawImage(image, playerX, playerY, panel);
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
	public int getX(){
		return this.playerX;
	}
	public int getY(){
		return this.playerY;
	}
	public void movePlayer(JPanel panel, boolean speedHeld){
		if(movePlayer){
		if (playerUp == true && playerY >= 15) {
			if (speedHeld) {
				playerY -= 2 * distance;
			} else
				playerY -= distance;
		}
		if (playerDown == true && playerY <= (panel.getHeight() - playerSize)) {
			if (speedHeld) {
				playerY += 2 * distance;
			} else
				playerY += distance;
		}
		if (playerLeft == true && playerX >= 0) {
			if (speedHeld) {
				playerX -= 2 * distance;
			} else
				playerX -= distance;
		}
		if (playerRight == true && playerX <= (panel.getWidth() - playerSize)) {
			if (speedHeld) {
				playerX += 2 * distance;
			} else
				playerX += distance;
		}
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
	public void setX(int n){
		this.playerX = n;
	}
	public void setY(int n){
		this.playerY = n;
	}
	public void setupDistances(int baseDistance) {
		//if(movePlayer)
		this.distance = baseDistance;
		//else this.distance = 0;
		
	}
	public Image getPlayerDeadImage() {	
		return imageDead;
	}
	public void setDirection(Direction d){
		playerDirection = d;
	}
	public void findDirection(){
		
		if(playerUp){
			if(playerLeft){
				playerDirection = Direction.NORTHWEST;
			}
			else if(playerRight){
				playerDirection = Direction.NORTHEAST;
			}
			else playerDirection = Direction.NORTH;
		}
		else if(playerDown){
			if(playerLeft){
				playerDirection = Direction.SOUTHWEST;
			}
			else if(playerRight){
				playerDirection = Direction.SOUTHEAST;
			}
			else playerDirection = Direction.SOUTH;
		}
		else if(playerLeft){
			if(playerUp){
				playerDirection = Direction.NORTHWEST;
			}
			else if(playerDown){
				playerDirection = Direction.SOUTHWEST;
			}
			else playerDirection = Direction.WEST;
			
		}
		else if(playerRight){
			if(playerUp){
				playerDirection = Direction.NORTHEAST;
			}
			else if(playerDown){
				playerDirection = Direction.SOUTHEAST;
			}
			else playerDirection = Direction.EAST;
		}else{
			playerDirection = Direction.NONE;
		}
		
	}
	public Direction getDirection() {
		return playerDirection;
	}
	public void setMovePlayer(boolean m){
		movePlayer = m;
	}
	
}
