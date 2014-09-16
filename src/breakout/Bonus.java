package breakout;

import java.util.Random;

import javax.swing.ImageIcon;

public class Bonus extends Sprite { //holds the definitions of ball

   private int xbdir; //declares the direction of motion of the ball
   private int ybdir;
   protected String bonus = "../images/bonus.png"; //declares the path of the ball image
   boolean existing;

   public Bonus() { 

     xbdir = 0; //sets the initial motion of the ball to the right (+1x direction == right)
     ybdir = 1; //sets the initial motion of the ball upward (-1y direction == up

     ImageIcon ii = new ImageIcon(this.getClass().getResource(bonus)); //creates the used icon for the ball
     image = ii.getImage(); //calls the "getImage" function to display it

     width = image.getWidth(null); //sets the width of the ball
     heigth = image.getHeight(null); //sets the heights of the ball
     resetState(); //places the ball in its initial position
     
     existing = false;
    }


    public void move() //class for ball motion
    {
      x += xbdir; //each time the timer calls the function the x position is either increases or decreased with a set amount of pixels 
      y += ybdir; //same as x

    }

    public void resetState() //declares the initial position of the ball
    {
      Random rand = new Random();
      int randomNum = rand.nextInt((290-10) + 10) + 10;
      x = 200;
      y = 10;
    }
    public int getYBDir() //checks the direction in the y place and returns it
    {
      return ybdir;
    }
    public boolean isExisting() //function that checks whether a brick is destroyed or not
    {
      return existing;
    }

    public void setExisting(boolean existing) //changes the bool of a brick to destroyed == true when the ball hits a brick
    {
      this.existing = existing;
    }
}