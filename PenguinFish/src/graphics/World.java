package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import main.DataStore;
import sprites.Player;
import sprites.SessileSprite;

public class World {
	
	protected BufferedImage image;
	protected LinkedList<SessileSprite> sessileSprites;
	
	

	public World() {
		sessileSprites = new LinkedList<SessileSprite>();
	}

	public void createSessileSprites() {
		for (int i = 0; i < 100; i++) {
			sessileSprites.add(new SessileSprite(100, (64 * i), 0));
		}
		sessileSprites = DataStore.getInstance().level.getSessileSprites();
	}

	public void tick(Player player) {
		for (SessileSprite s : sessileSprites) {
			if (player.collide(s)) {
				// System.out.println(d);
				player.getDirection().disableDirection(player.getDirection());
				// System.out.println("Player collision with SessileSprite");
			} else {
				player.getDirection().enableDirection(player.getDirection());
				player.getDirection().clearDisabledDirections();
			}
		}
	}

	

	//public void setImage(BufferedImage image) {
	//	this.image = image;
	//}

	public void draw(Graphics g, double camX, double camY) {
		g.drawImage(DataStore.getInstance().images.getBackground(), -(int)camX,-(int) camY, null);

	}
}
