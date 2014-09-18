package breakout;

import java.io.*;
import java.net.URL;

import javax.sound.sampled.*;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Sound {

    public Sound() throws Exception {
    	
    	String str = "Sound.wav";
        File sound = new File(str);
		URL music = new URL("http://www.soundjay.com/button/beep-07.wav");
        Clip clip = AudioSystem.getClip();
        // getAudioInputStream() also accepts a File or InputStream
        AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}