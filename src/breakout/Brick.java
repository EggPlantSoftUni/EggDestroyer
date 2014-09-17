package breakout;

import javax.swing.ImageIcon;


public class Brick extends Sprite { //the Brick Class

    String brickie = "../images/brickie.png"; //creates the path of the used image
    String crackedbrickie = "../images/brickie_cracked.png";

    boolean destroyed;//bool that indicates whether a brick has been destroyed or not
    boolean cracked;


    public Brick(int x, int y) { //decleration of a brick's x and y
      this.x = x;
      this.y = y;

      ImageIcon ii = new ImageIcon(this.getClass().getResource(brickie)); //creates the brick image icon
      image = ii.getImage(); //calls the "getImage" from "Sprites.java"
      
      ImageIcon iii = new ImageIcon(this.getClass().getResource(crackedbrickie)); //creates the brick image icon
      image1 = iii.getImage();

      width = image.getWidth(null); //set the width and height of the used image
      heigth = image.getHeight(null);

      destroyed = false; //declares that the created bicks have not been destroyed
      cracked = false;
    }

    public boolean isDestroyed() //function that checks whether a brick is destroyed or not
    {
      return destroyed;
    }

    public void setDestroyed(boolean destroyed) //changes the bool of a brick to destroyed == true when the ball hits a brick
    {
      this.destroyed = destroyed;
    }
    public boolean isCracked() //function that checks whether a brick is destroyed or not
    {
      return cracked;
    }

    public void setCracked(boolean cracked) //changes the bool of a brick to destroyed == true when the ball hits a brick
    {
      this.cracked = cracked;
    }
}