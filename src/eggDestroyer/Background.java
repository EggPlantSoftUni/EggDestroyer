package eggDestroyer;

import javax.swing.ImageIcon;


public class Background extends Sprite { //the Background Class

    String background = "../images/mainbg.png"; //creates the path of the used image
    String gameOverBg = "../images/gameoverbg.png";
    String victoryBg = "../images/victorybg.png";


    public Background(int x, int y) { //decleration of a background's x and y
      this.x = x;
      this.y = y;

      ImageIcon ii = new ImageIcon(this.getClass().getResource(background)); //creates the background image icon
      image = ii.getImage(); //calls the "getImage" from "Sprites.java"
      width = image.getWidth(null); //set the width and height of the used image
      heigth = image.getHeight(null);
      
      ImageIcon ii1 = new ImageIcon(this.getClass().getResource(gameOverBg)); //creates the game over background image icon
      image1 = ii1.getImage(); //calls the "getImage" from "Sprites.java"

      width1 = image1.getWidth(null); //set the width and height of the used image
      heigth1 = image1.getHeight(null);
      
      ImageIcon ii2 = new ImageIcon(this.getClass().getResource(victoryBg)); //creates the victory background image icon
      image2 = ii2.getImage(); //calls the "getImage" from "Sprites.java"

      width2 = image2.getWidth(null); //set the width and height of the used image
      heigth2 = image2.getHeight(null);
    }
    
}