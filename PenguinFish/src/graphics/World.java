package graphics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import main.DataStore;
import sprites.Player;
import sprites.SessileSprite;

public class World {
	protected Rectangle [] defualtBoundaries;
	protected BufferedImage image;
	protected LinkedList<LinkedList<SessileSprite>> sessileSprites;
	protected int defaultBoundedAreas = 4;
	protected LinkedList<Rectangle> boundaries;
	

	public World() {
		sessileSprites = new LinkedList<LinkedList<SessileSprite>>();
		defualtBoundaries = new Rectangle [defaultBoundedAreas];
		boundaries = new LinkedList<Rectangle>();
	}

	//Create rectangular areas which act as perimeter for the map.
	public Rectangle[] createDefaultBoundaries(){
		defualtBoundaries[0] = new Rectangle(-((int)DataStore.getInstance().maxWidth/2), -((int)DataStore.getInstance().maxHeight/2), DataStore.getInstance().images.getBackground().getWidth()+((int)DataStore.getInstance().maxWidth), ((int)DataStore.getInstance().maxHeight/2));
		defualtBoundaries[1] = new Rectangle(-((int)DataStore.getInstance().maxWidth/2), DataStore.getInstance().images.getBackground().getHeight(), DataStore.getInstance().images.getBackground().getWidth()+((int)DataStore.getInstance().maxWidth), ((int)DataStore.getInstance().maxHeight/2));
		defualtBoundaries[2] = new Rectangle(-((int)DataStore.getInstance().maxWidth/2), 0, ((int)DataStore.getInstance().maxWidth/2), DataStore.getInstance().images.getBackground().getHeight());
		defualtBoundaries[3] = new Rectangle(DataStore.getInstance().images.getBackground().getWidth(), 0, ((int)DataStore.getInstance().maxWidth/2), DataStore.getInstance().images.getBackground().getHeight());
			for (int i = 0; i < defaultBoundedAreas; i++){
				createSessileSprites(defualtBoundaries[i], 0);
				boundaries.add(defualtBoundaries[i]);
			}
		return defualtBoundaries;
		}
	
	public Rectangle createBoundaries(int x, int y, int width, int height){
		Rectangle boundary = new Rectangle(x, y, width, height);
		boundaries.add(boundary);
		return boundary;
	}
	
	//For each rectangle which acts as a boundary (4 edges by default)
	//fill with sessile sprites until it is full.  Integer fill 
	//indicates the number of the sessile sprite to fill the area.
	public void createSessileSprites(Rectangle rect, int spriteImage) {
			LinkedList<SessileSprite> sessileSpriteType = new LinkedList<SessileSprite>();
			for (int j = 0; j < rect.height / 64; j++){
				for (int i = 0; i < rect.width / 64; i++) {
					sessileSpriteType.add(new SessileSprite(rect.x + (64 * i), rect.y + (64 * j), spriteImage));
				}
			}
			sessileSprites.add(sessileSpriteType);
			sessileSprites.add(DataStore.getInstance().level.getSessileSprites());
	}

	public void tick(Player player) {
		for (Rectangle b: boundaries) {
			if (player.collide(b)) {
				//Need to sort out directions!!!
				//player.getDirection().disableDirection(player.getDirection());
			} else {
				//player.getDirection().enableDirection(player.getDirection());
				//player.getDirection().clearDisabledDirections();
			}
		}
	}

	public void draw(Graphics g, double camX, double camY) {
		g.drawImage(DataStore.getInstance().images.getBackground(), -(int)camX,-(int) camY, null);

	}

	public LinkedList<SessileSprite> getSessileSprites() {
		LinkedList<SessileSprite> ll = new LinkedList<SessileSprite>();
		for(LinkedList<SessileSprite> l : sessileSprites){
			ll.addAll(l);
		}
		return ll;
	}
}
