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
	Direction direction;
	int speed;
	int currentTop;
	int currentLeft;

	public Background(int w, int h) {
		currentTop = 0;
		currentLeft = 0;
		displayableWidth = w;
		displayableHeight = h;
		speed = 0;
		try {
			background = ImageIO.read(new File("res/img/Background.png"));
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
		case NE: 
			changeX = sqrt2speed;
			changeY = -sqrt2speed;
			break;
		case EAST: 
			changeX = speed;
			changeY = 0;
			break;
		case SE: 
			changeX = sqrt2speed;
			changeY = sqrt2speed;
			break;
		case SOUTH: 
			changeX = 0;
			changeY = speed;
			break;
		case SW: 
			changeX = -sqrt2speed;
			changeY = sqrt2speed;
			break;
		case WEST: 
			changeX = -speed;
			changeY = 0;
			break;
		case NW: 
			changeX = -sqrt2speed;
			changeY = -sqrt2speed;
			break;
		}
		
	currentTop += changeY;
	currentLeft += changeX;
	if(currentTop < 0) currentTop = 0;
	if(currentLeft < 0) currentLeft = 0;
	if (currentTop > background.getHeight()-displayableHeight) currentTop = background.getHeight()-displayableHeight;
	if (currentLeft > background.getWidth()-displayableWidth) currentLeft = background.getWidth()-displayableWidth;
	BufferedImage image = getDisplayableBackground();
	if(image != null){
	g.drawImage(image,0,0,panel);
	}
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
