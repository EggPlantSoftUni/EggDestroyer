package eggDestroyer;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class Sprite { //"The Sprite class is a base class for all objects on the Board. We put here all methods and variables that are in Ball, Brick and Paddle objects. Like getImage() or getX() methods."

	protected int x; //declaration of the variable(note that whether they're protected or public does not affect the game, but lets leave them as protected
	protected int y;
	protected int width;
	protected int heigth;
	protected int width1;
	protected int heigth1;
	protected int width2;
	protected int heigth2;
	protected Image image;
	protected Image image1;
	protected Image image2;

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

    public int getWidth() { //gets the width in pixels
        return width;
    }

    public int getHeight() { //like the above but with Height
        return heigth;
    }
    
    public int getWidth1() { //gets the width in pixels
        return width1;
    }

    public int getHeight1() { //like the above but with Height
        return heigth1;
    }
    
    public int getWidth2() { //gets the width in pixels
        return width2;
    }

    public int getHeight2() { //like the above but with Height
        return heigth2;
    }

    public Image getImage() //accesses the image and returns it
    {
      return image;
    }
    
    public Image getImage1() //accesses the image and returns it
    {
      return image1;
    }
    
    public Image getImage2() //accesses the image and returns it
    {
      return image2;
    }

    public Rectangle getRect()
    {
      return new Rectangle(x, y, 
          image.getWidth(null), image.getHeight(null));
      
    }
    
    public Rectangle getRect1()
    {
      return new Rectangle(x, y, 
          image1.getWidth(null), image1.getHeight(null));
      
    }
    public Rectangle getRect2()
    {
      return new Rectangle(x, y, 
          image2.getWidth(null), image1.getHeight(null));
      
    }
    
    public void draw(Graphics g, ImageObserver observer) {
    	if (image != null)
    		g.drawImage(image, x, y, width, heigth, observer);
    }
    
    public void draw1(Graphics g, ImageObserver observer) {
    	if (image1 != null)
    		g.drawImage(image1, x, y, width1, heigth1, observer);
    }
    
    public void draw2(Graphics g, ImageObserver observer) {
    	if (image2 != null)
    		g.drawImage(image2, x, y, width2, heigth2, observer);
    }
    
    
}