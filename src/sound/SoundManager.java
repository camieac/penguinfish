package sound;

import java.util.ArrayList;

public class SoundManager implements Runnable {

	SoundPlayer player;
	boolean playing;
	ArrayList<Sound> tracks;

	/**
	 * 
	 */
	public SoundManager() {
		tracks = new ArrayList<Sound>();
		player = null;

		addToQueue(new Sound("res/Sound/PenguinFish D01 End.wav", false));
		addToQueue(new Sound("res/Sound/PenguinFish D01Repeat.wav", true));
		// addToQueue(new Sound("res/Sound/PenguinFish D01 Intro.wav", false));

	}

	/**
	 * @param s
	 */
	public void addToQueue(Sound s) {
		tracks.add(s);
	}

	/**
	 * 
	 */
	private void playNextTrack() {
		Sound nextTrack = tracks.get(tracks.size() - 1);
		System.out.println("Now playing " + nextTrack.file);
		SoundPlayer sp;
		(new Thread(sp = new SoundPlayer(nextTrack.file))).start();
		player = sp;

		if (!nextTrack.loop) {
			tracks.remove(nextTrack);
		}

	}

	/**
	 * 
	 */
	public void playQueue() {
		if (player != null) {
			if (tracks.size() != 0) {
				if (player.finished) {
					player = null;

					playNextTrack();

				}
			}

		} else if (tracks.size() != 0) {
			playNextTrack();
		}

	}

	/**
	 * @param s
	 */
	public void removeFromQueue(Sound s) {
		if (tracks.contains(s)) {
			tracks.remove(s);
		}
	}

	@Override
	public void run() {
		while (true) {
			playQueue();
		}

	}

}
