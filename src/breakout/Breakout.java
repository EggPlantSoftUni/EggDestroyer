package breakout;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.swing.JFrame;

public class Breakout extends JFrame {
	
    public Breakout() //creates the window frame and names it
    {
        add(new Board()); //creation
        setTitle("Breakout"); //naming
        setDefaultCloseOperation(EXIT_ON_CLOSE); //exit mechanism
        setSize(Commons.WIDTH, Commons.HEIGTH); //sets width and height
        setLocationRelativeTo(null); //normal position of the window placement
        setIgnoreRepaint(true); //better performance ( we this it disable the native desktop repaint)
        setResizable(false); //you can't reside the window
        setVisible(true); //you can see the window
    }

    public static void main(String[] args) throws Exception { //main
        new Breakout(); //calls the game
        /*String str = "ding.wav";
        File sound = new File(str);
		URL music = new URL("http://www.soundjay.com/button/beep-07.wav");
        Clip clip = AudioSystem.getClip();
        // getAudioInputStream() also accepts a File or InputStream
        AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);*/
    }
}