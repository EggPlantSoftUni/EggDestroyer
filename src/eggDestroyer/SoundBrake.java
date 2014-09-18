package eggDestroyer;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class SoundBrake {

	public SoundBrake() throws Exception {
	    	
	    	String str = "eggBreak.wav";
	        File sound = new File(str);
	        Clip clip = AudioSystem.getClip();
	        AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
	        clip.open(ais);
	        clip.loop(0);
	    }
}
