package eggDestroyer;

import java.io.*;
import java.net.URL;

import javax.sound.sampled.*;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

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