package PenguinFish;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Background {
	BufferedImage background;
	int displayableWidth, displayableHeight;
	boolean movingBackground = false;
	Direction direction;
	
	int currentTop;
	int currentLeft;

	public Background(int w, int h) {
		currentTop = -1600;
		currentLeft = -2750;
		displayableWidth = w;
		displayableHeight = h;
		try {
			background = ImageIO.read(new File("res/img/Background.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	public BufferedImage getDisplayableBackground(int leftScreenEdge,int topScreenEdge) {
		
		return background.getSubimage(leftScreenEdge, topScreenEdge,
				displayableWidth, displayableHeight);
	}

	public void drawBackground(Direction d, int bd) {
		int changeX,changeY;
		switch(d){
		case NORTH: 
			changeX = 0;
			changeY = -5;
			break;
		case NORTHEAST: 
			changeX = 0;
			changeY = -5;
			break;
		case NORTH: 
			changeX = 0;
			changeY = -5;
			break;
		case NORTH: 
			changeX = 0;
			changeY = -5;
			break;
		case NORTH: 
			changeX = 0;
			changeY = -5;
			break;
		case NORTH: 
			changeX = 0;
			changeY = -5;
			break;
		case NORTH: 
			changeX = 0;
			changeY = -5;
			break;
			
		}
		
	}

}
