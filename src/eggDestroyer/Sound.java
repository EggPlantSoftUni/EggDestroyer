package eggDestroyer;
import sun.audio.*;

import java.io.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Sound extends JFrame{

	  AudioFormat audioFormat;
	  AudioInputStream audioInputStream;
	  SourceDataLine sourceDataLine;
	  boolean stopPlayback = false;

	  final JButton stopBtn = new JButton("Stop");
	  final JButton playBtn = new JButton("Play");
	  final JTextField textField =
	                       new JTextField("sound.au");
}

