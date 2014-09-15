package breakout;

import javax.swing.ImageIcon;


public class Brick extends Sprite { //the Brick Class

    String brickie = "../images/brickie.png"; //creates the path of the used image

    boolean destroyed; //bool that indicates whether a brick has been destroyed or not


    public Brick(int x, int y) { //decleration of a brick's x and y
      this.x = x;
      this.y = y;

      ImageIcon ii = new ImageIcon(this.getClass().getResource(brickie)); //creates the brick image icon
      image = ii.getImage(); //calls the "getImage" from "Sprites.java"

      width = image.getWidth(null); //set the width and height of the used image
      heigth = image.getHeight(null);

      destroyed = false; //declares that the created bicks have not been destroyed
    }

    public boolean isDestroyed() //function that checks whether a brick is destroyed or not
    {
      return destroyed;
    }

    public void setDestroyed(boolean destroyed) //changes the bool of a brick to destroyed == true when the ball hits a brick
    {
      this.destroyed = destroyed;
    }
    
}