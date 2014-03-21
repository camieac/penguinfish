package PenguinFish;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

class Map {
	protected int width, height;
	protected BufferedImage image;
	private BufferedImage[] sessileSpriteImages;
	private LinkedList<SessileSprite> sessileSprites;
	BufferedImage spriteSheet;
	protected Rectangle boundaries[];

	public Map(BufferedImage image) {
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.image = image;
		sessileSpriteImages = new BufferedImage[4];
		sessileSprites = new LinkedList<SessileSprite>();
		spriteSheet = Images.getImage("res/img/SpriteSheet1.png");
		sessileSpriteImages[0] = spriteSheet.getSubimage(0, 0, 64, 64);
		boundaries = new Rectangle[3];
	}
//	public void createBoundaries(Rectangle camera){
//		boundaries[0] = new Rectangle(0,0,0,camera.height);	//left
//		boundaries[1] = new Rectangle(0,0,camera.width,0);	//top
//		boundaries[2] = new Rectangle(0,camera.height);	//right
//		boundaries[3] = new Rectangle(camera.width,0);	//down	
//	}

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
			Direction d = player.getDirection();
			if (player.collide(s.getRect())) {
				System.out.println(d);
				player.getDirection().disableDirection(d);
				player.setSpeed(0);
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
}
