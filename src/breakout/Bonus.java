package breakout;

import java.util.Random;

import javax.swing.ImageIcon;

public class Bonus extends Sprite { //holds the definitions of ball

   private int xbdir; //declares the direction of motion of the ball
   private int ybdir;
   protected String bonus = "../images/bonus2.png"; //declares the path of the ball image
   protected String death = "../images/death.png";
   protected String shoot = "../images/fangBonus.png";
   boolean existing;
   boolean notExisting;

   public Bonus() { 

     xbdir = 0; //sets the initial motion of the ball to the right (+1x direction == right)
     ybdir = 1; //sets the initial motion of the ball upward (-1y direction == up

     ImageIcon ii = new ImageIcon(this.getClass().getResource(bonus)); //creates the used icon for the ball
     image = ii.getImage(); //calls the "getImage" function to display it
     
     width = image.getWidth(null); //sets the width of the ball
     heigth = image.getHeight(null); //sets the heights of the ball
     
     ImageIcon iv = new ImageIcon(this.getClass().getResource(shoot)); //creates the used icon for the ball
     image2 = iv.getImage(); //calls the "getImage" function to display it
     
     width2 = image2.getWidth(null); //sets the width of the ball
     heigth2 = image2.getHeight(null); //sets the heights of the ball
     
     ImageIcon iii = new ImageIcon(this.getClass().getResource(death)); //creates the used icon for the ball
     image1 = iii.getImage(); //calls the "getImage" function to display it

     width1 = image1.getWidth(null); //sets the width of the ball
     heigth1 = image1.getHeight(null); //sets the heights of the ball
     
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
      int randomNum = rand.nextInt(290) + 10;
      x = 200;
      y = 10;
    }
    
  
}