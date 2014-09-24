package eggDestroyer;

import java.io.*;

import javax.sound.sampled.*;

public class Sound {

	public Sound() throws Exception {

		String str = "Sound.wav";
		File sound = new File(str);
		Clip clip = AudioSystem.getClip();
		AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
		clip.open(ais);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
}