package breakout;

import javax.swing.ImageIcon;


public class CrackedBrick extends Sprite { //the Brick Class

    String crackedBrick = "../images/BrokenEgg.png"; //creates the path of the used image

     //bool that indicates whether a brick has been destroyed or not
    boolean cracked;



    public CrackedBrick(int x, int y) { //decleration of a brick's x and y
      this.x = x;
      this.y = y;
      ImageIcon ii2 = new ImageIcon(this.getClass().getResource(crackedBrick)); //creates the brick image icon
      image = ii2.getImage(); //calls the "getImage" from "Sprites.java"
      

      width = image.getWidth(null); //set the width and height of the used image
      heigth = image.getHeight(null);

      cracked = false; //declares that the created bicks have not been destroyed
 
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