package eggDestroyer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;

public class EggDestroyer extends JFrame {

    public EggDestroyer() //creates the window frame and names it
    {
        add(new Board()); //creation
        setTitle("EggDestroyer"); //naming
        setDefaultCloseOperation(EXIT_ON_CLOSE); //exit mechanism
        setSize(Commons.WIDTH, Commons.HEIGTH); //sets width and height
        setLocationRelativeTo(null); //normal position of the window placement
        setIgnoreRepaint(true); //better performance ( we this it disable the native desktop repaint)
        setResizable(false); //you can't reside the window
        setVisible(true); //you can see the window
    }

    public static void main(String[] args) { //main
        new EggDestroyer(); //calls the game 
    }
}