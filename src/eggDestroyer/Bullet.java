package eggDestroyer;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Bullet extends Sprite {

	private int ybdir;
	private int xbdir;
	protected String bullet = "../images/fangbang.png";
	public static boolean shot = false;

	public Bullet() {

		ybdir = -2; // sets the initial motion of the bullet upward

		ImageIcon ii = new ImageIcon(this.getClass().getResource(bullet));
		image = ii.getImage(); // calls the "getImage" function to display it

		width = image.getWidth(null); // sets the width 
		heigth = image.getHeight(null); // sets the height
		shot = false;
	}

	public void keyPressed(KeyEvent e) { // sets the motion

		int key = e.getKeyCode(); // gets the key pressed

		if (key == KeyEvent.VK_SPACE) {
			shot = true;
		}
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_SPACE) {
			shot = false;
		}
	}

	public boolean isShot() {
		return shot;
	}

	public void move() // class for bullet motion
	{
		x += xbdir;
		y += ybdir;
	}

	public int getYBDir() // checks the direction in the y place and returns it
	{
		return ybdir;
	}
}