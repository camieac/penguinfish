package PenguinFish;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class Bullet {
	private double currentRadians;
	private int speed;
	private Image image;
	private int xPosition,yPosition;
	private double rotation;
	private final double PI = Math.PI;
	private Direction direction;
	public Bullet(int x,int y,Direction direction){
		currentRadians = PI;
		this.xPosition = x;
		this.yPosition = y;
		this.direction = direction;
		this.speed = 10;
		image = new ImageIcon("res/img/Bullet.png").getImage();
		rotation = setRotation(directionToRotation(direction));
		
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
			
		default:
			break;
			
		}
		rotation = setRotation(directionToRotation(direction));
		//long before = System.currentTimeMillis();
		g2d.rotate(rotation, xPosition, yPosition);
		//long after = System.currentTimeMillis();
		//System.out.println("Rotate time: " + (after - before) + "ms");
		g2d.drawImage(image, xPosition, yPosition, panel);
		//rotation = 360
		g2d.dispose();
		
	}
	public void rotateBullet(Graphics2D g2d){
//		currentRadians = (currentRadians + rotation)%2*PI;
//		System.out.println("Current Radians" + currentRadians);
//		double t = setRotation(directionToRotation(direction));
//		System.out.println("Rotation: " + rotation);
//		//System.out.println("Direction: " + t);
//		while (rotation != currentRadians){
//			g2d.rotate(PI/12, xPosition+10, yPosition+10);
//			//g2d.r
//			System.out.println("Rotate");
//		}



		
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
