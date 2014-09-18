package eggDestroyer;

import java.util.Random;

import javax.swing.ImageIcon;

public class Bonus extends Sprite { //holds the definitions of bonus

   private int xbdir; //declares the direction of motion of the bonus
   private int ybdir;
   protected String bonus = "../images/bonus.png"; //declares the path of the bonus image
   protected String death = "../images/death.png";
   protected String fangBonus = "../images/fangBonus.png";
   boolean existing;
   boolean notExisting;

   public Bonus() { 

     xbdir = 0; //sets the initial motion of the bonus
     ybdir = 1; //sets the initial motion of the bonus downward

     ImageIcon ii = new ImageIcon(this.getClass().getResource(bonus)); //creates the used icon for the bonus
     image = ii.getImage(); //calls the "getImage" function to display it
     
     width = image.getWidth(null); //sets the width of the bonus
     heigth = image.getHeight(null); //sets the heights of the bonus
     
     ImageIcon ii1 = new ImageIcon(this.getClass().getResource(death)); 
     image1 = ii1.getImage();

     width1 = image1.getWidth(null);
     heigth1 = image1.getHeight(null); 
     
     ImageIcon ii2 = new ImageIcon(this.getClass().getResource(fangBonus));
     image2 = ii2.getImage();
     
     width2 = image2.getWidth(null); 
     heigth2 = image2.getHeight(null);
     
     resetState(); //places the bonus in its initial position
     
     existing = false;
    }


    public void move() //class for bonus motion
    {
      x += xbdir; //each time the timer calls the function the x position is either increases or decreased with a set amount of pixels 
      y += ybdir; //same as x

    }

    public void resetState() //declares the initial position of the bonus
    {
      Random rand = new Random();
      int randomNum = rand.nextInt(290) + 10;
      x = 200;
      y = 10;
    }
    
  
}