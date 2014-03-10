package PenguinFish;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.text.StyleContext.SmallAttributeSet;

class Bullet {
	private int speed;
	private Image image;
	private int xPosition,yPosition;
	private double rotation;
	private final double PI = Math.PI;
	private Direction direction;
	public Bullet(int x,int y,Direction direction){
		this.xPosition = x;
		this.yPosition = y;
		this.direction = direction;
		this.speed = 10;
		image = new ImageIcon("res/img/FishSkeleton.png").getImage();
		rotation = 0;
		
	}
	
	public void drawBullet(Graphics2D g2d, JPanel panel){
		switch(direction){
		case NORTH:
			yPosition -= speed;
			break;
		case NORTHEAST:
			//System.out.println("Broken");
			xPosition += (int) speed/Math.sqrt(2);
			yPosition -= (int) speed/Math.sqrt(2);
			break;
		case EAST:
			xPosition += speed;
			break;
		case SOUTHEAST:
			xPosition  += (int) speed/Math.sqrt(2);
			yPosition  += (int) speed/Math.sqrt(2);
			break;
		case SOUTH: 
			yPosition += speed;
			break;
		case SOUTHWEST:
			xPosition  -= (int) speed/Math.sqrt(2);
			yPosition  += (int) speed/Math.sqrt(2);
			break;
		case WEST:
			xPosition -= speed;
			break;
		case NORTHWEST:
			xPosition  -= (int) speed/Math.sqrt(2);
			yPosition  -= (int) speed/Math.sqrt(2);
			break;
		default:
		System.out.println("ERROR");
			break;
		}
		g2d.drawImage(image, xPosition, yPosition, panel);
	}
	public void rotateBullet(Graphics2D g2d){
		g2d.rotate(rotation, xPosition + 15, yPosition + 15);
	}
	
	public void setPosition(int x, int y){
		this.xPosition = x;
		this.yPosition = y;
	}
	public void setRotation(int degree){
		rotation = PI/180*degree;
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
