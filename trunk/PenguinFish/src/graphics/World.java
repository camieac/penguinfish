package graphics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import main.DataStore;
import sprites.Enemy;
import sprites.SpriteBlock;
import sprites.Player;
import sprites.SessileSprite;

/**
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * 
 */
public class World {
	protected LinkedList<Rectangle> boundaries;
	protected int defaultBoundedAreas = 4;
	protected Rectangle[] defualtBoundaries;
	//protected BufferedImage image;
	protected LinkedList<LinkedList<SessileSprite>> sessileSprites;

	/**
	 * 
	 */
	public World() {
		sessileSprites = new LinkedList<LinkedList<SessileSprite>>();
		defualtBoundaries = new Rectangle[defaultBoundedAreas];
		boundaries = new LinkedList<Rectangle>();

		for (int i = 0; i < 4; i++) {
			createSpriteBlock(createDefaultBoundaries()[i], 0);
		}
		addLevelSprites();
		addLevelSpriteBlocks();
		addLevelEnemies();
	}
	/**
	 * 
	 */
	public void newLevel(){ //TODO: Finish this thing
		DataStore.getInstance().levelNumber++;
		if(DataStore.getInstance().levelNumber > DataStore.getInstance().images.backgrounds.length-1){
			DataStore.getInstance().levelNumber = DataStore.getInstance().images.backgrounds.length-1;
		}
		sessileSprites.clear();
		DataStore.getInstance().enemies.clear();
		DataStore.getInstance().level = DataStore.getInstance().levelReader.getNextLevel();
		addLevelSprites();
		addLevelSpriteBlocks();
		addLevelEnemies();
	}
	private void addLevelEnemies() {
		LinkedList<Enemy> lle = DataStore.getInstance().level.getEnemies();
		DataStore.getInstance().enemies.clear();
		DataStore.getInstance().enemies.addAll(lle);
	}

	private void addLevelSpriteBlocks() {
		LinkedList<SpriteBlock> ll = DataStore.getInstance().level
				.getSpriteBlocks();
		for (SpriteBlock s : ll) {
			createSpriteBlock(s.getRect(), s.getImgNumber());
		}

	}

	// For each rectangle which acts as a boundary (4 edges by default)
	// fill with sessile sprites until it is full. Integer fill
	// indicates the number of the sessile sprite to fill the area.
	/**
	 * @param rect
	 * @param spriteImage
	 */
	public void addLevelSprites() {
		sessileSprites.add(DataStore.getInstance().level.getSessileSprites());
	}

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public Rectangle createBoundaries(int x, int y, int width, int height) {
		Rectangle boundary = new Rectangle(x, y, width, height);
		boundaries.add(boundary);
		return boundary;
	}

	// Create rectangular areas which act as perimeter for the map.
	/**
	 * @return
	 */
	public Rectangle[] createDefaultBoundaries() {
		int level = DataStore.getInstance().levelNumber;
		defualtBoundaries[0] = new Rectangle(
				-((int) DataStore.getInstance().maxWidth / 2),
				-((int) DataStore.getInstance().maxHeight / 2),
				DataStore.getInstance().images.getBackground(level).getWidth()
						+ ((int) DataStore.getInstance().maxWidth),
				((int) DataStore.getInstance().maxHeight / 2));
		defualtBoundaries[1] = new Rectangle(
				-((int) DataStore.getInstance().maxWidth / 2),
				DataStore.getInstance().images.getBackground(level).getHeight(),
				DataStore.getInstance().images.getBackground(level).getWidth()
						+ ((int) DataStore.getInstance().maxWidth),
				((int) DataStore.getInstance().maxHeight / 2));
		defualtBoundaries[2] = new Rectangle(
				-((int) DataStore.getInstance().maxWidth / 2), 0,
				((int) DataStore.getInstance().maxWidth / 2),
				DataStore.getInstance().images.getBackground(level).getHeight());
		defualtBoundaries[3] = new Rectangle(DataStore.getInstance().images
				.getBackground(level).getWidth(), 0,
				((int) DataStore.getInstance().maxWidth / 2),
				DataStore.getInstance().images.getBackground(level).getHeight());
		for (int i = 0; i < defaultBoundedAreas; i++) {
			createSpriteBlock(defualtBoundaries[i], 0);
			boundaries.add(defualtBoundaries[i]);
		}
		return defualtBoundaries;
	}

	private void createSpriteBlock(Rectangle rect, int spriteImage) {
		LinkedList<SessileSprite> sessileSpriteType = new LinkedList<SessileSprite>();
		for (int j = 0; j < rect.height / 64; j++) {
			for (int i = 0; i < rect.width / 64; i++) {
				sessileSpriteType.add(new SessileSprite(rect.x + (64 * i),
						rect.y + (64 * j), spriteImage));
			}
		}
		sessileSprites.add(sessileSpriteType);

	}

	/**
	 * @param g
	 * @param camX
	 * @param camY
	 */
	public void draw(Graphics g, double camX, double camY) {
		g.drawImage(DataStore.getInstance().images.getBackground(DataStore
				.getInstance().levelNumber), -(int) camX, -(int) camY, null);

	}

	/**
	 * @return
	 */
	public LinkedList<SessileSprite> getSessileSprites() {
		LinkedList<SessileSprite> ll = new LinkedList<SessileSprite>();
		for (LinkedList<SessileSprite> l : sessileSprites) {
			ll.addAll(l);
		}
		return ll;
	}

	/**
	 * @param player
	 */
	public void tick(Player player) {
		for (Rectangle b : boundaries) {
			if (player.collide(b)) {
				// Need to sort out directions!!!
				// player.getDirection().disableDirection(player.getDirection());
			} else {
				// player.getDirection().enableDirection(player.getDirection());
				// player.getDirection().clearDisabledDirections();
			}
		}
	}
}
