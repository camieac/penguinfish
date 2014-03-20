package PenguinFish;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Camera{
	BufferedImage camera;
	int cameraWidth, cameraHeight;
	boolean movingCamera= true;
	boolean moveX, moveY;
	int width, height;
	Direction direction;
	int speed;
	int currentTop;
	int currentLeft;

	public Camera(int w, int h) {
		currentTop = 0;
		currentLeft = 0;
		cameraWidth = w;
		cameraHeight = h;
		speed = 0;
		try {
			camera = ImageIO.read(new File("res/img/back.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		width = camera.getWidth();
		height = camera.getHeight();
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	public BufferedImage getDisplayableBackground() {
		BufferedImage image = null;
		try{
		image = camera.getSubimage(currentLeft, currentTop,
				cameraWidth, cameraHeight);
		movingCamera = true;
		}catch(RasterFormatException e){
			movingCamera = false;
			return camera;
		}
		return image;
	}

	public int getSpeed(){
		return speed;
	}
	
	public void drawCamera(Direction d,Graphics g,JPanel panel) {
		double sqrt2speed = speed/Math.sqrt(2);
		double changeX = 0,changeY = 0;
		if(movingCamera){
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
		else if (currentLeft >= camera.getWidth()-cameraWidth){
			currentLeft = camera.getWidth()-cameraWidth;
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
		else if (currentTop >= camera.getHeight()-cameraHeight){
			currentTop = camera.getHeight()-cameraHeight;
			moveY = false;
		}
		else moveY = true;
	}
	else if (currentTop >= camera.getHeight()-cameraHeight){
		currentTop = camera.getHeight()-cameraHeight;
		moveY = false;
		if(currentLeft <= 0){
			currentLeft = 0;
			moveX = false;
		}
		else if (currentLeft >= camera.getWidth()-cameraWidth){
			currentLeft = camera.getWidth()-cameraWidth;
			moveX = false;
		}
		else moveX = true;
	}
	else if (currentLeft >= camera.getWidth()-cameraWidth){
		currentLeft = camera.getWidth()-cameraWidth;
		moveX = false;
		if (currentTop <= 0 || currentTop >= camera.getHeight()-cameraHeight){
			currentTop = camera.getHeight()-cameraHeight;
			moveY = false;
		}
		else moveY = true;
	}
	else {
		moveY = true;
		moveX = true;
	}
//	 if(player.getY() < cameraHeight/2-player.getHeight()/2 || player.getY() > cameraHeight/2-player.getHeight()/2){
//		 moveY = false;
//	 }
//	 if(player.getX() < cameraWidth/2-player.getWidth()/2 || player.getX() > cameraWidth/2-player.getWidth()/2){
//		 moveX = false;
//	 }
	
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
}
