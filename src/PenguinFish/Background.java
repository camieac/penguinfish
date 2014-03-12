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
	boolean moveX = true;
	boolean moveY = true;
	Direction atEdge;
	Direction direction;
	
	int currentTop;
	int currentLeft;

	public Background(int w, int h) {
		atEdge = Direction.NORTHWEST;
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
			//System.out.println("Trying to render from " + currentLeft + " , " + currentTop);
		image = background.getSubimage(currentLeft, currentTop,
				displayableWidth, displayableHeight);
		//System.out.println("                     RENDERED");
		//movingBackground = true;
		}catch(RasterFormatException e){
			//movingBackground = false;
			return background;
		}
		return image;
	}

	public void drawBackground(Direction d, int bd,Graphics2D g2d,JPanel panel,Player player,boolean speedHeld) {
		double sqrt2bd = Math.sqrt(2)*bd;
		double changeX = 0,changeY = 0;
		if(movingBackground){
			Movement movement = new Movement(d,bd);
			changeX = movement.getX();
			changeY = movement.getY();
//		switch(d){
//		case NORTH: 
//			if(moveY){
//			changeX = 0;
//			changeY = -bd;
//			}
//			break;
//		case NORTHEAST: 
//			if(moveX && moveY){
//			changeX = sqrt2bd;
//			changeY = -sqrt2bd;
//			}
//			break;
//		case EAST: 
//			if(moveX){
//			changeX = bd;
//			changeY = 0;
//			}
//			break;
//		case SOUTHEAST: 
//			if(moveX && moveY){
//			changeX = sqrt2bd;
//			changeY = sqrt2bd;
//			}
//			break;
//		case SOUTH: 
//			if(moveY){
//			changeX = 0;
//			changeY = bd;
//			}
//			break;
//		case SOUTHWEST: 
//			if(moveX && moveY){
//			changeX = -sqrt2bd;
//			changeY = sqrt2bd;
//			}
//			break;
//		case WEST: 
//			if(moveX){
//			changeX = -bd;
//			changeY = 0;
//			}
//			break;
//		case NORTHWEST: 
//			if(moveX && moveY){
//			changeX = -sqrt2bd;
//			changeY = -sqrt2bd;
//			}
//			break;
//		case NONE:
//			changeX = 0;
//			changeY = 0;
//		}
		}
		if(speedHeld){
			changeX *= 2;
			changeY *= 2;
		}
		

	currentTop += changeY;
	currentLeft += changeX;
	boolean move = true;
	
	if(currentTop < 0){
		currentTop = 0;
		move = false;
		moveY = false;
		moveX = true;
		atEdge = Direction.NORTH;
		
	}
	if(currentTop > background.getHeight() - displayableHeight){
		currentTop = background.getHeight() - displayableHeight;
		move = false;
		atEdge = Direction.SOUTH;
		moveY = false;
		moveX = true;
	}
	if(currentLeft < 0){
		currentLeft = 0;
		move = false;
		if(atEdge == Direction.NORTH){
			atEdge = Direction.NORTHWEST;
			moveY = false;
			moveX = false;	
		}else if (atEdge == Direction.SOUTH){
			atEdge = Direction.SOUTHWEST;
			moveY = false;
			moveX = false;
		}else{
			atEdge = Direction.WEST;
			moveY = true;
			moveX = false;
		}
		
	}
	if(currentLeft > background.getWidth() - displayableWidth){
		currentLeft = background.getWidth() - displayableWidth;
		move = false;
		if(atEdge == Direction.SOUTH){
			atEdge = Direction.SOUTHEAST;
			moveY = false;
			moveX = false;
		}else if(atEdge == Direction.NORTH){
			atEdge = Direction.NORTHEAST;
			moveY = false;
			moveX = false;
		}else{
			atEdge = Direction.EAST;
			moveY = true;
			moveX = false;
		}
	}
	
	if(atEdge == Direction.WEST && player.getX() < displayableWidth/2){
		movingBackground = false;
		//move = false;
		
	}
	if(atEdge == Direction.NORTH && player.getY() < displayableHeight/2){
		movingBackground = false;
		//move = false;
	}
	if(move){
		moveX = true;
		moveY = true;
	}
	if(moveX && moveY){
		atEdge = Direction.NONE;
		movingBackground = true;
		player.setMovePlayer(false);
	}else{
//		player.findDirection();
//		player.movePlayer(panel, speedHeld);
//		player.drawPlayer(g2d, panel);
		player.setMovePlayer(true);
		movingBackground = false;
	}
	//if()
	System.out.println("Moving background: " + movingBackground);
	System.out.println("MoveX?: " + moveX + ", MoveY?: " + moveY);
	System.out.println("EDGE: " + atEdge.toString());
	System.out.println("Current Top: " + currentTop + ", Current Left: " + currentLeft);
	BufferedImage image = getDisplayableBackground();
	if(image != null){
	g2d.drawImage(image,0,0,panel);
	}
	}



	public int getCurrentTop() {
		return currentTop;
	}



	public int getCurrentLeft() {
		return currentLeft;
	}
}
