package main;

import graphics.Images;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import sprites.Player;
import sprites.SessileSprite;

class Background {
	protected int width, height;
	protected BufferedImage image;
	protected LinkedList<SessileSprite> sessileSprites;
	protected Rectangle boundaries[];
	private Images images;
	
	public Background(Images images) {
		this.image = images.getBackground();
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.images = images;
		sessileSprites = new LinkedList<SessileSprite>();
		boundaries = new Rectangle[3];
	}

	public void createSessileSprites() {
		for (int i = 0; i < 4; i++) {
			SessileSprite tree = new SessileSprite(400, (400 * i),images, 0);
			tree.setAbsoluteX(400);
			tree.setAbsoluteY(64 * i);
			sessileSprites.add(tree);
		}
	}

	public void paintSessileSprites(Graphics g,Camera camera) {
		int cameraLeft = camera.getCurrentLeft();
		int cameraTop = camera.getCurrentTop();
		for (SessileSprite s : sessileSprites) {
		int absX = s.getAbsoluteX();
		int absY = s.getAbsoluteY();
			s.setAbsoluteX (absX- cameraLeft);
			s.setAbsoluteY(absY - cameraTop);
			if(camera.isInFrame(absX, absY, s.getWidth(), s.getHeight())){
				s.draw(g, 0);
			}
		}
	}

	public void tickSessileSprites(Player player) {
		for (SessileSprite s : sessileSprites) {
			Direction d = player.getDirection();
			if (player.collide(s.createRect())) {
				System.out.println(d);
				player.getDirection().disableDirection(d);
				
				System.out.println("Player collision with SessileSprite");
			}else{
				player.getDirection().enableDirection(d);
				player.getDirection().clearDisabledDirections();
			}
		}
	}

	public void tick(Player player) {
		tickSessileSprites(player);
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	public void setImage(BufferedImage image){
		this.image = image;
	}

	public void draw(Graphics g) {
		g.drawImage(image,0,0,null);
		
	}
}
