package breakout;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class Paddle extends Sprite implements Commons { //declares the paddle stuff

    String paddle = "../images/paddle.png"; //creates the path to the used image

    int dx; //declares motion in the x plane

    public Paddle() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(paddle)); //creates the icon used for the paddle
        image = ii.getImage(); //calls the "getImage" function to creates the image

        width = image.getWidth(null); //sets the width and height
        heigth = image.getHeight(null);

        resetState(); //declares the initial position

    }

    public void move() { //class of paddle motion
        x += dx; //the paddle moves only in the x place with a dx change in position
        if (x <= 2) //sets a limit to the paddles' motion to the left of the screen (it's two because each dx change is of 2 pixles)
          x = 2; //if the user tries to go beyond that point, the paddle does not move
        if (x >= Commons.PADDLE_RIGHT) //same as above, but for the right end of the screen
          x = Commons.PADDLE_RIGHT;
    }

    public void keyPressed(KeyEvent e) { //sets the motion of the balls

        int key = e.getKeyCode(); //gets the key pressed

        if (key == KeyEvent.VK_LEFT) { //if it's left, the ball changes its position with -2x (-1x direction == left)
            dx = -2;

        }

        if (key == KeyEvent.VK_RIGHT) { ////if it's left, the ball changes its position with 2x (+1x direction == right)
            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e) { //stops the paddle from moving when the key is released
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) { //set to 0, hence no motion
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) { //set to 0, hence no motion
            dx = 0;
        }
    }


    public void resetState() { //declares the initial positions of the paddle
        x = 400;
        y = 650;

    }
}