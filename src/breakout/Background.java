package breakout;

import java.applet.*;
import java.awt.*;
import java.net.*;
import java.io.IOException.*;

public class Background extends Applet {
     Image backGround;

     public void init() {

          // set the size of the applet to the size of the background image.
          // Resizing the applet may cause distortion of the image.
          setSize(700, 800);

          // Set the image name to the background you want. Assumes the image 
          // is in the same directory as the class file is
          backGround = getImage(getCodeBase(), "../images/workApplet.png");
          BackGroundPanel bgp = new BackGroundPanel();
          bgp.setLayout(new FlowLayout());
          bgp.setBackGroundImage(backGround);

          // Add the components you want in the Applet to the Panel
          bgp.add(new Button("Button 1"));
          bgp.add(new TextField("isn't this cool?"));
          bgp.add(new Button("Useless Button 2"));

          // set the layout of the applet to Border Layout
          setLayout(new BorderLayout());

          // now adding the panel, adds to the center
          // (by default in Border Layout) of the applet
          add(bgp);
     }
}

class BackGroundPanel extends Panel {
     Image backGround;

     BackGroundPanel() {
          super();
     }

     public void paint(Graphics g) {

          // get the size of this panel (which is the size of the applet),
          // and draw the image
          g.drawImage(getBackGroundImage(), 0, 0,
              (int)getBounds().getWidth(), (int)getBounds().getHeight(), this);
     }

     public void setBackGroundImage(Image backGround) {
          this.backGround = backGround;    
     }

     private Image getBackGroundImage() {
          return backGround;    
     }
}