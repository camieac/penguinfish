package PenguinFish;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Sprite {
	private int x;
	private int y;
	private int width;
	private int height;
	private BufferedImage image;
	Direction direction;
	
	public Sprite(int x, int y, int width, int height, Direction d, BufferedImage image){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;
		this.direction = d;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getWidth(){
	return width;	
	}
	public int getHeight(){
		return height;
	}
	public void draw(Graphics g){
		g.drawImage(image, x, y, null);
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setWidth(int w){
		this.width = w;
	}
	public void setHeight(int h){
		this.height = h;
	}
	public Direction getDirection(){
		return direction;
	}
	public void setDirection(Direction d){
		this.direction = d;
	}
}
