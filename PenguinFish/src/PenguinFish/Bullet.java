package PenguinFish;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

class Bullet {
	private int speed;
	private BufferedImage image;
	private Image rotatedImage;
	private int xPosition,yPosition;
	private final double PI = Math.PI;
	private Direction direction;
	public Bullet(int x,int y,Direction direction){
		rotatedImage = null;
		this.xPosition = x;
		this.yPosition = y;
		this.direction = direction;
		this.speed = 10;
		try{
		image = ImageIO.read(new File("res/img/Bullet.png"));
		}catch(IOException e){
			System.err.println(e.toString());
		}
	}
	private int directionToRotation(Direction d){
		
		int angle = 0;
		switch(d){
		case NORTH: angle = 0;break;
		case NORTHEAST: angle = 45;break;
		case EAST: angle = 90;break;
		case SOUTHEAST: angle = 135;break;
		case SOUTH: angle = 180;break;
		case SOUTHWEST: angle = 225;break;
		case WEST: angle = 270;break;
		case NORTHWEST: angle = 315;break;
		}
		return angle;
	}
	public void drawBullet(Graphics2D g2d, JPanel panel){
		switch(direction){
		case NORTH: 
			yPosition -= speed;
			
			break;
		case NORTHEAST:
			xPosition += (int)speed/Math.sqrt(2);
			yPosition -= (int)speed/Math.sqrt(2);
			break;
		case EAST: 
			xPosition += speed;
			break;
		case SOUTHEAST: 
			xPosition  += (int)speed/Math.sqrt(2);
			yPosition  += (int)speed/Math.sqrt(2);
			break;
		case SOUTH: 
			yPosition += speed;
			break;
		case SOUTHWEST: 
			xPosition  -= (int)speed/Math.sqrt(2);
			yPosition  += (int)speed/Math.sqrt(2);
			break;
		case WEST: 
			xPosition -= speed;
			break;
		case NORTHWEST:
			xPosition  -= (int)speed/Math.sqrt(2);
			yPosition  -= (int)speed/Math.sqrt(2);
			break;		
		}		
	}
	public void rotateBullet(Graphics2D g2d){
		double dir = setRotation(directionToRotation(direction));
		if(rotatedImage == null){
		AffineTransform tx = AffineTransform.getRotateInstance(dir, image.getWidth()/2, image.getHeight()/2);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		rotatedImage = op.filter(image, null);
		}
		g2d.drawImage(rotatedImage, xPosition, yPosition, null);
	}
	
	public void setPosition(int x, int y){
		this.xPosition = x;
		this.yPosition = y;
	}
	public double setRotation(int degree){
		return PI/180*degree;
	}
	public int getX(){
		return xPosition;
	}
	public int getY(){
		return yPosition;
	}
	public int getWidth(){
		return image.getWidth(null);
	}
	public int getHeight(){
		return image.getHeight(null);
	}
	
}
