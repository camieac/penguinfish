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
	Movement movement;
	
	int currentTop;
	int currentLeft;

	public Background(int w, int h) {
		atEdge = Direction.NORTHWEST;
		currentTop = 0;
		currentLeft = 0;
		displayableWidth = w;
		displayableHeight = h;
		movement = new Movement(0,0);
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
			//Movement movement = new Movement(d,bd);
			movement.setDistance(bd);
			movement.setDirection(d);
			changeX = movement.getX();
			changeY = movement.getY();

		}
		if(speedHeld){
			changeX *= 2;
			changeY *= 2;
		}
		

	currentTop += changeY;
	currentLeft += changeX;
	boolean move = true;
	/* Make sure the camera never extends beyond the top edge*/
	if(currentTop < 0){
		currentTop = 0;
		move = false;
		movement.removeYMovement();
		atEdge = Direction.NORTH;
		
	}
	/* Make sure the camera never extends beyond the bottom edge*/
	if(currentTop > background.getHeight() - displayableHeight){
		currentTop = background.getHeight() - displayableHeight;
		move = false;
		atEdge = Direction.SOUTH;
		movement.removeYMovement();
	}
	/* Make sure the camera never extends beyond the left edge*/
	if(currentLeft < 0){
		currentLeft = 0;
		move = false;
		if(atEdge == Direction.NORTH){
			atEdge = Direction.NORTHWEST;
			movement.setZero();
		}else if (atEdge == Direction.SOUTH){
			atEdge = Direction.SOUTHWEST;
			movement.setZero();
		}else{
			atEdge = Direction.WEST;
			movement.removeXMovement();
		}
		
	}
	/*If the camera is at the left edge of the background image, prevent the camera from looking beyond the edge of the background image.
	 * If the camera is at the south edge as well as the west edge, the current edge of the camera is set to SOUTHEAST...*/
	if(currentLeft > background.getWidth() - displayableWidth){
		currentLeft = background.getWidth() - displayableWidth;
		move = false;
		if(atEdge == Direction.SOUTH){
			atEdge = Direction.SOUTHEAST;
			//Camera cannot move as it is at the edge
			movement.setZero();
			
		}else if(atEdge == Direction.NORTH){
			atEdge = Direction.NORTHEAST;
			movement.setZero();
		}else{
			atEdge = Direction.EAST;
			movement.removeXMovement();
		}
	}
	
//	if(atEdge == Direction.WEST && player.getX() < displayableWidth/2){
//		movingBackground = false;
//		//move = false;
//		
//	}
//	if(atEdge == Direction.NORTH && player.getY() < displayableHeight/2){
//		movingBackground = false;
//		//move = false;
//	}
//	if(move){
//		moveX = true;
//		moveY = true;
//	}
//	if(moveX && moveY){
//		atEdge = Direction.NONE;
//		movingBackground = true;
//		player.setMovePlayer(false);
//	}else{
////		player.findDirection();
////		player.movePlayer(panel, speedHeld);
////		player.drawPlayer(g2d, panel);
//		player.setMovePlayer(true);
//		movingBackground = false;
//	}
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
