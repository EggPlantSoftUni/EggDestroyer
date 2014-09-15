package breakout;

import javax.swing.JFrame;

public class Breakout extends JFrame {

    public Breakout() //creates the window frame and names it
    {
        add(new Board()); //creation
        setTitle("Breakout"); //naming
        setDefaultCloseOperation(EXIT_ON_CLOSE); //exit mechanism
        setSize(Commons.WIDTH, Commons.HEIGTH); //sets width and height
        setLocationRelativeTo(null); //normal position of the window placement
        setIgnoreRepaint(true); //better performance ( we this it disable the native desktop repaint)
        setResizable(false); //you can't reside the window
        setVisible(true); //you can see the window
    }

    public static void main(String[] args) { //main
        new Breakout(); //calls the game 
    }
}