package PenguinFish;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Background {
	BufferedImage background;
	int displayableWidth, displayableHeight;
	boolean movingBackground = true;
	Direction direction;
	
	int currentTop;
	int currentLeft;

	public Background(int w, int h) {
		currentTop = 0;
		currentLeft = 0;
		displayableWidth = w;
		displayableHeight = h;
		try {
			background = ImageIO.read(new File("res/img/Background.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	public BufferedImage getDisplayableBackground() {
		BufferedImage image = null;
		try{
			System.out.println("Trying to render from " + currentLeft + " , " + currentTop);
		image = background.getSubimage(currentLeft, currentTop,
				displayableWidth, displayableHeight);
		System.out.println("                     RENDERED");
		movingBackground = true;
		}catch(RasterFormatException e){
			movingBackground = false;
			return background;
		}
		return image;
	}

	public void drawBackground(Direction d, int bd,Graphics2D g2d,JPanel panel) {
		double sqrt2bd = Math.sqrt(2)*bd;
		double changeX = 0,changeY = 0;
		switch(d){
		case NORTH: 
			changeX = 0;
			changeY = -bd;
			break;
		case NORTHEAST: 
			changeX = sqrt2bd;
			changeY = -sqrt2bd;
			break;
		case EAST: 
			changeX = bd;
			changeY = 0;
			break;
		case SOUTHEAST: 
			changeX = sqrt2bd;
			changeY = sqrt2bd;
			break;
		case SOUTH: 
			changeX = 0;
			changeY = bd;
			break;
		case SOUTHWEST: 
			changeX = -sqrt2bd;
			changeY = sqrt2bd;
			break;
		case WEST: 
			changeX = -bd;
			changeY = 0;
			break;
		case NORTHWEST: 
			changeX = -sqrt2bd;
			changeY = -sqrt2bd;
			break;
		case NONE:
			changeX = 0;
			changeY = 0;
		}
		

	currentTop += changeY;
	currentLeft += changeX;
	if(currentTop < 0) currentTop = 0;
	if(currentLeft < 0) currentLeft = 0;
	System.out.println("Current Top: " + currentTop + ", Current Left: " + currentLeft);
	BufferedImage image = getDisplayableBackground();
	if(image != null){
	g2d.drawImage(image,0,0,panel);
	}
	}
}
