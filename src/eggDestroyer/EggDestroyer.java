package eggDestroyer;

import javax.swing.JFrame;

public class EggDestroyer extends JFrame {

	public EggDestroyer() // creates the window frame and names it
	{
		add(new Board()); // creation
		setTitle("EggDestroyer"); // naming
		setDefaultCloseOperation(EXIT_ON_CLOSE); // exit mechanism
		setSize(Commons.WIDTH, Commons.HEIGTH); // sets width and height
		setLocationRelativeTo(null); // normal position of the window placement
		setIgnoreRepaint(true); // better performance
		setResizable(false); // you can't resize the window
		setVisible(true); // you can see the window
	}

	public static void main(String[] args) throws Exception { // main
		new EggDestroyer(); // calls the game
	}
}