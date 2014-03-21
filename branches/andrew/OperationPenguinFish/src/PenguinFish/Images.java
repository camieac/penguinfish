package PenguinFish;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class Images {
	private BufferedImage[] players;
	private BufferedImage[] bullets;
	private BufferedImage[] enemies;
	private BufferedImage background;
	private BufferedImage[] maps;
	private BufferedImage fullHeart, emptyHeart;
	
	public Images(){
		players = new BufferedImage[17];
		bullets = new BufferedImage[1];
		enemies = new BufferedImage[17];
		
		maps = new BufferedImage[1];
		
		

		maps[0] = getImage("res/img/back.png");
		fullHeart = getImage("res/img/Heart01.png");
		emptyHeart = getImage("res/img/Heart02.png");
		bullets[0] = getImage("res/img/FishSkeleton.png");
		enemies[0] = getImage("res/img/Enemy00.png");
		background = getImage("res/img/back.png");

		int numPlayerImages = 13;
		for (int i = 0; i < numPlayerImages; i++) {
			players[i] = getImage("res/img/Character" + i + ".png");
		}

	}
	
	static BufferedImage getImage(String image) {
		BufferedImage map = null;
		try {
			map = ImageIO.read(new File(image));
			return toCompatibleImage(map);
		} catch (IOException e) {
			return map;
		}
	}
	
	public BufferedImage getDisplayableBackground(int currentLeft, int currentTop) {
		BufferedImage image = null;
		try{
		image = background.getSubimage(currentLeft, currentTop,background.getWidth(), background.getHeight());
		}catch(RasterFormatException e){
			return background;
		}
		return image;
	}
	private static BufferedImage toCompatibleImage(BufferedImage image) {
		GraphicsConfiguration gfx_config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		if (image.getColorModel().equals(gfx_config.getColorModel()))return image;
		BufferedImage new_image = gfx_config.createCompatibleImage(image.getWidth(), image.getHeight(), image.getTransparency());
		Graphics2D g2d = (Graphics2D) new_image.getGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		return new_image;
	}

	public BufferedImage getMapImage(int i) {
		return maps[i];
		
	}

	public BufferedImage[] getPlayerImages() {
		return players;
		
	}

	public BufferedImage[] getEnemyImages() {
		return enemies;
	}

	public BufferedImage[] getBulletImages() {
		return bullets;
	}

	public BufferedImage getFullHeart() {
		return fullHeart;
	}

	public BufferedImage getEmptyHeart() {
		return emptyHeart;
	}
	
}
