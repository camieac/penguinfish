package sound;

/**
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson, Stuart Thain
 *
 */
public class Sound {
	String file;
	boolean loop;

	/**
	 * @param file
	 * @param loop
	 */
	public Sound(String file, boolean loop) {
		this.file = file;
		this.loop = loop;
	}
}
