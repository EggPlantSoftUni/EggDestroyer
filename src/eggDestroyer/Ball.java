package eggDestroyer;

import javax.swing.ImageIcon;

public class Ball extends Sprite implements Commons { //holds the definitions of ball

   private int xdir; //declares the direction of motion of the ball
   private int ydir;
   protected String ball = "../images/ball.png"; //declares the path of the ball image

   public Ball() { 

     xdir = 1; //sets the initial motion of the ball to the right (+1x direction == right)
     ydir = -1; //sets the initial motion of the ball upward (-1y direction == up

     ImageIcon ii = new ImageIcon(this.getClass().getResource(ball)); //creates the used icon for the ball
     image = ii.getImage(); //calls the "getImage" function to display it

     width = image.getWidth(null); //sets the width of the ball
     heigth = image.getHeight(null); //sets the heights of the ball
     resetState(); //places the ball in its initial position
    }
   
    public void move() //class for ball motion
    {
      x += xdir; //each time the timer calls the function the x position is either increases or decreased with a set amount of pixels 
      y += ydir; //same as x

      if (x == 0) { //if the ball hits the left side of the screen its motion is changed to the right (+1x direction == right)
        setXDir(1);
      }

      if (x == BALL_RIGHT) { //if the ball hits the right side of the screen its motion is changed to the left (-1x direction == left)
        setXDir(-1);
      }

      if (y == 0) { //if the ball hits the roof of the window it is reflected towards the bottom (+1y direction == down)
        setYDir(1);
      }
    }

    public void resetState() //declares the initial position of the ball
    {
      x = 230;
      y = 10;//355;
    }

    public void setXDir(int x) //sets the direction of the ball
    {
      xdir = x;
    }

    public void setYDir(int y) //same as above
    {
      ydir = y;
    }

    public int getYDir() //checks the direction in the y place and returns it
    {
      return ydir;
    }
}