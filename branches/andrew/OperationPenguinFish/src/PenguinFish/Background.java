package PenguinFish;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Background{
	BufferedImage background;
	int displayableWidth, displayableHeight;
	boolean movingBackground = true;
	boolean moveX, moveY;
	Direction direction;
	int speed;
	int currentTop;
	int currentLeft;
	Player player;

	public Background(int w, int h, Player player) {
		this.player = player;
		currentTop = 0;
		currentLeft = 0;
		displayableWidth = w;
		displayableHeight = h;
		speed = 0;
		try {
			background = ImageIO.read(new File("res/img/back.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	public BufferedImage getDisplayableBackground() {
		BufferedImage image = null;
		try{
		image = background.getSubimage(currentLeft, currentTop,
				displayableWidth, displayableHeight);
		movingBackground = true;
		}catch(RasterFormatException e){
			movingBackground = false;
			return background;
		}
		return image;
	}

	public int getSpeed(){
		return speed;
	}
	
	public void drawBackground(Direction d,Graphics g,JPanel panel) {
		double sqrt2speed = speed/Math.sqrt(2);
		double changeX = 0,changeY = 0;
		if(movingBackground){
			speed = 5;
		}
		switch(d){
		case NORTH: 
			changeX = 0;
			changeY = -speed;
			break;
		case NORTHEAST: 
			changeX = sqrt2speed;
			changeY = -sqrt2speed;
			break;
		case EAST: 
			changeX = speed;
			changeY = 0;
			break;
		case SOUTHEAST: 
			changeX = sqrt2speed;
			changeY = sqrt2speed;
			break;
		case SOUTH: 
			changeX = 0;
			changeY = speed;
			break;
		case SOUTHWEST: 
			changeX = -sqrt2speed;
			changeY = sqrt2speed;
			break;
		case WEST: 
			changeX = -speed;
			changeY = 0;
			break;
		case NORTHWEST: 
			changeX = -sqrt2speed;
			changeY = -sqrt2speed;
			break;
		}
	currentTop += changeY;
	currentLeft += changeX;
	if(currentTop <= 0) {
		currentTop = 0; 
		moveY = false;
		if(currentLeft <= 0){ 
			currentLeft = 0;
			moveX = false;
		}
		else if (currentLeft >= background.getWidth()-displayableWidth){
			currentLeft = background.getWidth()-displayableWidth;
			moveX = false;
		}
		else moveX = true;
	}
	else if(currentLeft <= 0){
		currentLeft = 0;
		moveX = false;
		if(currentTop <= 0){
			currentTop = 0; 
			moveY = false;
		}
		else if (currentTop >= background.getHeight()-displayableHeight){
			currentTop = background.getHeight()-displayableHeight;
			moveY = false;
		}
		else moveY = true;
	}
	else if (currentTop >= background.getHeight()-displayableHeight){
		currentTop = background.getHeight()-displayableHeight;
		moveY = false;
		if(currentLeft <= 0){
			currentLeft = 0;
			moveX = false;
		}
		else if (currentLeft >= background.getWidth()-displayableWidth){
			currentLeft = background.getWidth()-displayableWidth;
			moveX = false;
		}
		else moveX = true;
	}
	else if (currentLeft >= background.getWidth()-displayableWidth){
		currentLeft = background.getWidth()-displayableWidth;
		moveX = false;
		if (currentTop <= 0 || currentTop >= background.getHeight()-displayableHeight){
			currentTop = background.getHeight()-displayableHeight;
			moveY = false;
		}
		else moveY = true;
	}
	else {
		moveY = true;
		moveX = true;
	}
	 if(player.getY() < displayableHeight/2-player.getHeight()/2 || player.getY() > displayableHeight/2-player.getHeight()/2){
		 moveY = false;
	 }
	 if(player.getX() < displayableWidth/2-player.getWidth()/2 || player.getX() > displayableWidth/2-player.getWidth()/2){
		 moveX = false;
	 }
	
	System.out.println("MoveX: " + moveX + ", " + "MoveY: " + moveY);
	BufferedImage image = getDisplayableBackground();
	if(image != null){
	g.drawImage(image,0,0,panel);
	}
	}

	public boolean getMoveX(){
		return moveX;
	}
	
	public boolean getMoveY(){
		return moveY;
	}
	
	public int getCurrentLeft() {
		return currentLeft;
	}

	public int getCurrentTop() {
		return currentTop;
	}

	public void setMovingBackground(boolean b) {
		movingBackground = b;
	}
}
