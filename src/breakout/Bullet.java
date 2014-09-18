package breakout;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Bullet extends Sprite { //holds the definitions of ball

   private int ybdir;
   private int xbdir;
   protected String bullet = "../images/small_fangbang.png"; //declares the path of the ball image
   public static boolean shot = false;
   
   
   public Bullet() { 

     ybdir = -2; //sets the initial motion of the ball upward (-1y direction == up

     ImageIcon ii = new ImageIcon(this.getClass().getResource(bullet)); //creates the used icon for the ball
     image = ii.getImage(); //calls the "getImage" function to display it

     width = image.getWidth(null); //sets the width of the ball
     heigth = image.getHeight(null); //sets the heights of the ball
     shot = false;
     
    }
   
   public void keyPressed (KeyEvent e) { //sets the motion of the balls

       int key = e.getKeyCode(); //gets the key pressed

       if (key == KeyEvent.VK_SPACE) {
           shot = true;
       }
   }
   
   public void keyReleased (KeyEvent e) { //sets the motion of the balls

       int key = e.getKeyCode(); //gets the key pressed

       if (key == KeyEvent.VK_SPACE) {
           shot = false;
       }
   }
   
   
   public boolean isShot (){
	   return shot;
   }
   
   
   

    public void move() //class for ball motion
    {
      x += xbdir;
      y += ybdir; //same as x

    }
    public int getYBDir() //checks the direction in the y place and returns it
    {
      return ybdir;
    }
}