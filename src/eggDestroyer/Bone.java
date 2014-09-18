package eggDestroyer;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class Bone extends Sprite implements Commons { //declares the bone stuff

    String bone = "../images/bone.png"; //creates the path to the used image

    int dx; //declares motion in the x plane

    public Bone() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(bone)); //creates the icon used for the bone
        image = ii.getImage(); //calls the "getImage" function to creates the image

        width = image.getWidth(null); //sets the width and height
        heigth = image.getHeight(null);

        resetState(); //declares the initial position

    }

    public void move() { //class of bone motion
        x += dx; //the bone moves only in the x place with a dx change in position
        if (x <= 2) //sets a limit to the bone' motion to the left of the screen
          x = 2; //if the user tries to go beyond that point, the bone does not move
        if (x >= Commons.BONE_RIGHT) //same as above, but for the right end of the screen
          x = Commons.BONE_RIGHT;
    }

    public void keyPressed(KeyEvent e) { //sets the motion

        int key = e.getKeyCode(); 

        if (key == KeyEvent.VK_LEFT) { 
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e) { //stops the bone from moving when the key is released
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) { //set to 0, hence no motion
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) { //set to 0, hence no motion
            dx = 0;
        }
    }


    public void resetState() { //declares the initial positions of the bone
        x = 400;
        y = 640;

    }
}