package graphics;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.DataStore;

/**
 * Imports all images to BufferedImages. Handles individual image files and
 * spritesheets.
 * 
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * 
 */
public class Images {
	/**
	 * @param image
	 *            The location of the image to get.
	 * @return BufferedImage from the location given.
	 */
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
	protected static BufferedImage toCompatibleImage(BufferedImage image) {
		GraphicsConfiguration gfx_config = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		if (image.getColorModel().equals(gfx_config.getColorModel()))
			return image;
		BufferedImage new_image = gfx_config.createCompatibleImage(
				image.getWidth(), image.getHeight(), image.getTransparency());
		Graphics2D g2d = (Graphics2D) new_image.getGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		return new_image;
	}

	protected BufferedImage fullHeart, emptyHeart, sessileSpriteSheet,
			playerSpriteSheet, enemiesSpriteSheet;

	protected BufferedImage[] players, backgrounds, enemies, bullets,
			sessileSpriteImages,titleImages;

	/**
	 * 
	 */
	public Images() {
		titleImages = new BufferedImage[2];
		players = new BufferedImage[16];
		bullets = new BufferedImage[1];
		enemies = new BufferedImage[16];
		backgrounds = new BufferedImage[2];
		sessileSpriteImages = new BufferedImage[5];
		backgrounds[0] = getImage("res/img/back.png");
		backgrounds[1] = getImage("res/img/map2.png");
		fullHeart = getImage("res/img/Heart01.png");
		emptyHeart = getImage("res/img/Heart02.png");
		bullets[0] = getImage("res/img/FishSkeleton.png");
		sessileSpriteSheet = getImage("res/img/sessileSprites.png");
		playerSpriteSheet = getImage("res/img/player.png");
		enemiesSpriteSheet = getImage("res/img/enemies.png");
		titleImages[0] = getImage("res/img/title.png");
		titleImages[1] = getImage("res/img/cooltext.png");
		for (int i = 0; i < 4; i++) {
			sessileSpriteImages[i] = sessileSpriteSheet.getSubimage(64 * i, 0,
					64, 64);
		}
		sessileSpriteImages[4] = sessileSpriteSheet.getSubimage(0, 70, 45, 69);
		int numPlayerImages = 16;
		int rows = 2;
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < numPlayerImages / rows; i++) {
				if (j == 0)
					players[i] = playerSpriteSheet.getSubimage(i * 30, j * 42,
							30, 42);
				if (j == 1)
					players[i + 8] = playerSpriteSheet.getSubimage(i * 30,
							j * 42, 30, 42);
			}
		}
		int numEnemyImages = 3;
		// Setup spider images (0,1,2)
		for (int i = 0; i < numEnemyImages; i++) {
			enemies[i] = enemiesSpriteSheet.getSubimage(i * 128, 0, 128, 64);
		}
		// Setup worm images (3,4,5)
		for (int i = 3; i < numEnemyImages + 3; i++) {
			enemies[i] = enemiesSpriteSheet.getSubimage(128 * (i - 3), 64, 128,
					82);
		}
		// Setup Fisherman images (6,7,8)
		for (int i = 6; i < numEnemyImages + 6; i++) {
			enemies[i] = enemiesSpriteSheet.getSubimage(128 * (i - 6), 192,
					128, 128);
		}
		// Setup Minotaurs
		enemies[9] = enemiesSpriteSheet.getSubimage(12, 331, 96, 145);
		enemies[10] = enemiesSpriteSheet.getSubimage(133, 331, 127, 145);
		enemies[11] = enemiesSpriteSheet.getSubimage(246, 326, 127, 145);
		
		//Setup Dragons
		enemies[12] = enemiesSpriteSheet.getSubimage(136, 564, 860, 580);
		enemies[13] = enemiesSpriteSheet.getSubimage(1260, 573, 861, 564);
		enemies[14] = enemiesSpriteSheet.getSubimage(2376, 561, 860, 580);
		

	}

	/**
	 * @param i
	 *            Index of the backgrounds array
	 * @return BufferedImage background at index i.
	 */
	public BufferedImage getBackground(int i) {
		return backgrounds[i];
	}
	/**
	 * @return The background image for the current level.
	 */
	public BufferedImage getCurrentBackground(){
		return backgrounds[DataStore.getInstance().levelNumber];
	}

	/**
	 * @param i
	 *            Index of the bullets array.
	 * @return Image of a bullet at index i.
	 */
	public BufferedImage getBulletImage(int i) {
		return bullets[i];
	}

	/**
	 * @param camX
	 * @param camY
	 * @param width
	 * @param height
	 * @return
	 */
	public BufferedImage getDisplayableBackground(double camX, double camY,
			double width, double height) {
		BufferedImage image = null;
		try {
			image = backgrounds[0].getSubimage((int) camX, (int) camY,
					(int) width, (int) height);
		} catch (RasterFormatException e) {
			return backgrounds[0];
		}
		return image;
	}

	/**
	 * @return BufferedImage of an empty heart
	 */
	public BufferedImage getEmptyHeart() {
		return emptyHeart;
	}

	/**
	 * @param i
	 *            Index of the array of enemies.
	 * @return BufferedImage of enemy at index i.
	 */
	public BufferedImage getEnemy(int i) {
		return enemies[i];
	}

	/**
	 * @return Array of images of enemies.
	 */
	public BufferedImage[] getEnemyImages() {
		return enemies;
	}

	/**
	 * @return BufferedImage of a full heart.
	 */
	public BufferedImage getFullHeart() {
		return fullHeart;
	}

	/**
	 * @param i
	 *            The index of the world map array.
	 * @return The image of the world background.
	 */
	public BufferedImage getMapImage(int i) {
		return backgrounds[i];
	}

	/**
	 * @param i
	 *            Index of the player images array.
	 * @return BufferedImage of the player at index i.
	 */
	public BufferedImage getPlayer(int i) {
		return players[i];
	}

	/**
	 * @return Array of images of players.
	 */
	public BufferedImage[] getPlayerImages() {
		return players;
	}
	
	/**
	 * @param i The index of the title image to get.
	 * @return The BufferedImage of the title image.
	 */
	public BufferedImage getTitleImage(int i){
		return titleImages[i];
	}

	/**
	 * @param i
	 *            Index of the sessile sprite array.
	 * @return BufferedImage of a sessile sprite at index i.
	 */
	public BufferedImage getSessileImage(int i) {
		if (i > sessileSpriteImages.length) {
			return sessileSpriteImages[sessileSpriteImages.length - 1];
		}
		return sessileSpriteImages[i];
	}

}