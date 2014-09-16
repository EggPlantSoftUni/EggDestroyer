package breakout;

import javax.swing.ImageIcon;


public class Brick extends Sprite { //the Brick Class

    String brickie = "../images/brickie.png"; //creates the path of the used image

    boolean destroyed; //bool that indicates whether a brick has been destroyed or not
    
    String brBrickie = "../images/brikieGreen.png";
    
    boolean cracked;  // bool that indicates whether an egg is cracked or not 

    
    
    public Brick(int x, int y) { //decleration of a brick's x and y
      this.x = x;
      this.y = y;
      
	      if (!destroyed && !cracked) {	
	      ImageIcon ii = new ImageIcon(this.getClass().getResource(brickie)); //creates the brick image icon
	      image = ii.getImage(); //calls the "getImage" from "Sprites.java"
	      }   
	      else{
	    	  ImageIcon ii = new ImageIcon(this.getClass().getResource(brBrickie)); //creates the brick image icon
	          image = ii.getImage(); //calls the "getImage" from "Sprites.java"
	      }
	      
      width = image.getWidth(null); //set the width and height of the used image
      heigth = image.getHeight(null);

      destroyed = false; //declares that the created bicks have not been destroyed
     // cracked = false;
    }
    
        
    
    public boolean isDestroyed() //function that checks whether a brick is destroyed or not
    {
      return this.destroyed;
    }

    public void setDestroyed(boolean destroyed) //changes the bool of a brick to destroyed == true when the ball hits a brick
    {
      this.destroyed = destroyed;
    }
    
    public boolean isCracked(){
    	return this.cracked;
    }
    
    public void setCracked(boolean cracked){
    	this.cracked = cracked;
    }
    
}