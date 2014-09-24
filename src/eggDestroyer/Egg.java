package eggDestroyer;

import javax.swing.ImageIcon;

public class Egg extends Sprite { // the Egg Class

	String egg = "../images/egg.png"; // creates the path of the used image
	String crackedEgg = "../images/cracked_egg.png";

	boolean destroyed;// bool that indicates whether a egg has been destroyed or
						// not
	boolean cracked;

	public Egg(int x, int y) { // decleration of a egg's x and y
		this.x = x;
		this.y = y;

		ImageIcon ii = new ImageIcon(this.getClass().getResource(egg)); // creates
																		// the
																		// egg
																		// image
																		// icon
		image = ii.getImage(); // calls the "getImage" from "Sprites.java"

		width = image.getWidth(null); // set the width and height of the used
										// image
		heigth = image.getHeight(null);

		ImageIcon ii1 = new ImageIcon(this.getClass().getResource(crackedEgg)); // creates
																				// the
																				// egg
																				// image
																				// icon
		image1 = ii1.getImage();

		width1 = image1.getWidth(null); // set the width and height of the used
										// image
		heigth1 = image1.getHeight(null);

		destroyed = false; // declares that the created egg have not been
							// destroyed
		cracked = false;
	}

	public boolean isDestroyed() // function that checks whether a egg is
									// destroyed or not
	{
		return destroyed;
	}

	public void setDestroyed(boolean destroyed) // changes the bool of a egg to
												// destroyed == true when the
												// ball hits a egg
	{
		this.destroyed = destroyed;
	}

	public boolean isCracked() // function that checks whether a egg is
								// destroyed or not
	{
		return cracked;
	}

	public void setCracked(boolean cracked) {
		this.cracked = cracked;
	}

}