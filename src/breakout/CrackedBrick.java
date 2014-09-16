package breakout;

import javax.swing.ImageIcon;


public class CrackedBrick extends Sprite { //the Brick Class

    String cracked = "../images/BrokenEgg.png";

    boolean crackedEgg; //bool that indicates whether a brick has been destroyed or not


    public CrackedBrick(int x, int y) { //decleration of a brick's x and y
      this.x = x;
      this.y = y;
      ImageIcon ii = new ImageIcon(this.getClass().getResource(cracked)); //creates the brick image icon
      image = ii.getImage(); //calls the "getImage" from "Sprites.java"
      

      width = image.getWidth(null); //set the width and height of the used image
      heigth = image.getHeight(null);

      crackedEgg = false; //declares that the created bicks have not been destroyed
    }
 

    public boolean isCrackedEgg() //function that checks whether a brick is destroyed or not
    {
      return crackedEgg;
    }

    public void setCrackedEgg(boolean destroyed) //changes the bool of a brick to destroyed == true when the ball hits a brick
    {
      this.crackedEgg = crackedEgg;
    }
    
}