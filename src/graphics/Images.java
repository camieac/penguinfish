package graphics;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {
	protected BufferedImage[] players, backgrounds, enemies, bullets, sessileSpriteImages;
	protected BufferedImage fullHeart, emptyHeart, background, spriteSheet;
	
	public Images(){
		players = new BufferedImage[13];
		bullets = new BufferedImage[1];
		enemies = new BufferedImage[1];	
		backgrounds = new BufferedImage[1];
		sessileSpriteImages = new BufferedImage[4];
		backgrounds[0] = getImage("res/img/back.png");
		fullHeart = getImage("res/img/Heart01.png");
		emptyHeart = getImage("res/img/Heart02.png");
		bullets[0] = getImage("res/img/FishSkeleton.png");
		enemies[0] = getImage("res/img/Enemy00.png");
		background = getImage("res/img/back.png");
		spriteSheet = Images.getImage("res/img/SpriteSheet1.png");
		sessileSpriteImages[0] = spriteSheet.getSubimage(0, 0, 64, 64);

		int numPlayerImages = 13;
		for (int i = 0; i < numPlayerImages; i++) {
			players[i] = getImage("res/img/Character" + i + ".png");
		}
	}
	
	public static BufferedImage getImage(String image) {
		BufferedImage map = null;
		try {
			map = ImageIO.read(new File(image));
			return toCompatibleImage(map);
		} catch (IOException e) {
			System.err.println("IO Exception");
			return map;
		}
	}
	
	public BufferedImage getDisplayableBackground(int currentLeft, int currentTop,int width,int height) {
		BufferedImage image = null;
		try{
		image = background.getSubimage(currentLeft, currentTop,width, height);
		}catch(RasterFormatException e){
			return background;
		}
		return image;
	}
	
	protected static BufferedImage toCompatibleImage(BufferedImage image) {
		GraphicsConfiguration gfx_config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		if (image.getColorModel().equals(gfx_config.getColorModel()))return image;
		BufferedImage new_image = gfx_config.createCompatibleImage(image.getWidth(), image.getHeight(), image.getTransparency());
		Graphics2D g2d = (Graphics2D) new_image.getGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		return new_image;
	}

	public BufferedImage getMapImage(int i) {
		return backgrounds[i];
	}

	public BufferedImage[] getPlayerImages() {
		return players;	
	}

	public BufferedImage[] getEnemyImages() {
		return enemies;
	}

	public BufferedImage getBulletImage(int i) {
		return bullets[i];
	}

	public BufferedImage getFullHeart() {
		return fullHeart;
	}

	public BufferedImage getEmptyHeart() {
		return emptyHeart;
	}
	public BufferedImage getBackground(){
		return background;
	}
	
	public BufferedImage getSessileImage(int i) {
		return sessileSpriteImages[i];
	}

	public BufferedImage getPlayer(int i) {
		return players[i];
	}

	public BufferedImage getEnemy(int i) {
		return enemies[i];
	}
	
}
