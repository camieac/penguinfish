package PenguinFish;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

class Map {
	protected int width, height;
	protected BufferedImage image;
	private BufferedImage[] sessileSpriteImages;
	private LinkedList<SessileSprite> sessileSprites;
	BufferedImage spriteSheet;

	public Map(BufferedImage image) {
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.image = image;
		sessileSpriteImages = new BufferedImage[4];
		sessileSprites = new LinkedList<SessileSprite>();
		spriteSheet = Images.getImage("res/img/SpriteSheet1.png");
		sessileSpriteImages[0] = spriteSheet.getSubimage(0, 0, 64, 64);
	}

	public void createSessileSprites() {
		for (int i = 0; i < sessileSpriteImages.length; i++) {
			SessileSprite tree = new SessileSprite(400, (400 * i),
					sessileSpriteImages);
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
			s.setX (absX- cameraLeft);
			s.setY(absY - cameraTop);
			if(camera.isInFrame(absX, absY, s.getWidth(), s.getHeight())){
				s.draw(g, 0);
			}
		}
	}

	public void tickSessileSprites(Player player) {
		for (SessileSprite s : sessileSprites) {
			if (player.collide(s.getRect())) {
				player.setSpeed(0);
			}
		}
	}
}
