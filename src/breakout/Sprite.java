package breakout;

import java.awt.Image;
import java.awt.Rectangle;

public class Sprite { //"The Sprite class is a base class for all objects on the Board. We put here all methods and variables that are in Ball, Brick and Paddle objects. Like getImage() or getX() methods."

	protected int x; //declaration of the variable(note that whether they're protected or public does not affect the game, but lets leave them as protected
	protected int y;
	protected int width;
	protected int heigth;
	protected Image image;


    public void setX(int x) { //setsX
        this.x = x;
    }

    public int getX() { //getsX
        return x;
    }

    public void setY(int y) { //like X but with Y
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getWidth() { //gets the width in pixes(used for bricks, etc)
        return width;
    }

    public int getHeight() { //like the above but with Height
        return heigth;
    }

    Image getImage() //accesses the image and returns it
    {
      return image;
    }

    Rectangle getRect() //used for brick
    {
      return new Rectangle(x, y, 
          image.getWidth(null), image.getHeight(null));
      
    }
}