package breakout;
import javax.swing.*;	

import java.awt.*;
import java.awt.event.*;

public class Restart extends JFrame {

    private static final ActionListener Res = restart();

	public Restart() //creates the window frame and names it
    {
        /*setTitle("Breakout"); //naming
        setDefaultCloseOperation(EXIT_ON_CLOSE); //exit mechanism
        setSize(Commons.WIDTH, Commons.HEIGTH); //sets width and height
        setLocationRelativeTo(null); //normal position of the window placement
        setIgnoreRepaint(true); //better performance ( we this it disable the native desktop repaint)
        setResizable(false); //you can't reside the window
        setVisible(true); //you can see the window*/
        JButton button = new JButton("restart");
        add(button, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        button.addActionListener(Res);
    }

    public static ActionListener restart () { //main
        new Breakout(); //calls the game 
		return null;
    }
}