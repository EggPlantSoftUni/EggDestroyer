package breakout;

import javax.swing.ImageIcon;


public class Background extends Sprite { //the Brick Class

    String background = "../images/background2.png"; //creates the path of the used image
    String backgroundOver = "../images/backgroundEnd.png";


    public Background(int x, int y) { //decleration of a brick's x and y
      this.x = x;
      this.y = y;

      ImageIcon ii = new ImageIcon(this.getClass().getResource(background)); //creates the brick image icon
      image = ii.getImage(); //calls the "getImage" from "Sprites.java"
      
      ImageIcon iii = new ImageIcon(this.getClass().getResource(backgroundOver)); //creates the brick image icon
      image1 = iii.getImage(); //calls the "getImage" from "Sprites.java"

      width = image.getWidth(null); //set the width and height of the used image
      heigth = image.getHeight(null);
    }
    
}