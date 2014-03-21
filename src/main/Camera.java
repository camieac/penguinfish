package main;

import graphics.Images;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import sprites.Player;

public class Camera{
	
	protected int cameraWidth, cameraHeight;
	protected boolean movingCamera= true;
	protected boolean moveX, moveY;
	protected Direction direction;
	protected int speed;
	protected int currentTop;
	protected int currentLeft;
	protected int backgroundWidth, backgroundHeight;
	protected Images images;
	protected boolean attached;

	public Camera(int w, int h, Background background,Images images) {
		currentTop = 0;
		currentLeft = 0;
		cameraWidth = w;
		cameraHeight = h;
		speed = 0;
		backgroundHeight = background.getHeight();
		backgroundWidth = background.getWidth();
		this.images = images;
		attached = true;
		
	}

	public void setSpeed(int speed){
		this.speed = speed;
	}

	public int getSpeed(){
		return speed;
	}
	
	public void drawCamera(Player player,Background background,Graphics g,JPanel panel) {
		direction = player.getDirection();
		
		if(attached){
			int playerAbsCentreX = player.getAbsoluteX() + player.getWidth()/2;
			currentLeft = playerAbsCentreX - cameraWidth/2;
			int playerAbsCentreY = player.getAbsoluteY() + player.getHeight()/2;
			currentTop = playerAbsCentreY - cameraHeight/2;
		}
//		double sqrt2speed = speed/Math.sqrt(2);
//		double changeX = 0,changeY = 0;
//		if(movingCamera){
//			speed = 5;
//		}
//		switch(d){
//		case NORTH: 
//			changeX = 0;
//			changeY = -speed;
//			break;
//		case NORTHEAST: 
//			changeX = sqrt2speed;
//			changeY = -sqrt2speed;
//			break;
//		case EAST: 
//			changeX = speed;
//			changeY = 0;
//			break;
//		case SOUTHEAST: 
//			changeX = sqrt2speed;
//			changeY = sqrt2speed;
//			break;
//		case SOUTH: 
//			changeX = 0;
//			changeY = speed;
//			break;
//		case SOUTHWEST: 
//			changeX = -sqrt2speed;
//			changeY = sqrt2speed;
//			break;
//		case WEST: 
//			changeX = -speed;
//			changeY = 0;
//			break;
//		case NORTHWEST: 
//			changeX = -sqrt2speed;
//			changeY = -sqrt2speed;
//			break;
//		}
//	currentTop += changeY;
//	currentLeft += changeX;
//	
//	
//	if(currentTop <= 0) {
//		currentTop = 0; 
//		moveY = false;
//		if(currentLeft <= 0){ 
//			currentLeft = 0;
//			moveX = false;
//		}
//		else if (currentLeft >= backgroundWidth-cameraWidth){
//			currentLeft = backgroundWidth-cameraWidth;
//			moveX = false;
//		}
//		else moveX = true;
//	}
//	else if(currentLeft <= 0){
//		currentLeft = 0;
//		moveX = false;
//		if(currentTop <= 0){
//			currentTop = 0; 
//			moveY = false;
//		}
//		else if (currentTop >= backgroundHeight-cameraHeight){
//			currentTop = backgroundHeight-cameraHeight;
//			moveY = false;
//		}
//		else moveY = true;
//	}
//	else if (currentTop >= backgroundHeight-cameraHeight){
//		currentTop = backgroundHeight-cameraHeight;
//		moveY = false;
//		if(currentLeft <= 0){
//			currentLeft = 0;
//			moveX = false;
//		}
//		else if (currentLeft >= backgroundWidth-cameraWidth){
//			currentLeft = backgroundWidth-cameraWidth;
//			moveX = false;
//		}
//		else moveX = true;
//	}
//	else if (currentLeft >= backgroundWidth-cameraWidth){
//		currentLeft = backgroundWidth-cameraWidth;
//		moveX = false;
//		if (currentTop <= 0 || currentTop >= backgroundHeight-cameraHeight){
//			currentTop = backgroundHeight-cameraHeight;
//			moveY = false;
//		}
//		else moveY = true;
//	}
//	else {
//		moveY = true;
//		moveX = true;
//	}
//	 if(player.getY() < cameraHeight/2-player.getHeight()/2 || player.getY() > cameraHeight/2-player.getHeight()/2){
//		 moveY = false;
//	 }
//	 if(player.getX() < cameraWidth/2-player.getWidth()/2 || player.getX() > cameraWidth/2-player.getWidth()/2){
//		 moveX = false;
//	 }
	
	//System.out.println("MoveX: " + moveX + ", " + "MoveY: " + moveY);
	
	
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

	public void setMovingCamera(boolean b) {
		movingCamera = b;
	}
	public boolean isInFrame(int x, int y, int w, int h){
		if(x + w < currentLeft) return false;
		else if(x > currentLeft + cameraWidth) return false;
		else if(y + h < currentTop) return false;
		else if(y > currentTop + cameraHeight) return false;
		else return true;
	}

	public int getWidth() {
		return this.cameraWidth;
	}

	public void detach() {
		attached = false;
		
	}

	public int getHeight() {
		return this.cameraHeight;
	}

	public void attach() {
		attached = true;
		
	}

	
}
