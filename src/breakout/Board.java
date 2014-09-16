package breakout;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;


public class Board extends JPanel implements Commons { //this contains the game design, mechanics and logic
	private Background bg;
    Image ii; //declares the image
    Timer timer; //declares the timer
    String message = "Game Over"; //declares the game over message
    Ball ball; //declares the ball
    Paddle paddle; //declares the paddle
    Brick bricks[];//declares the sum of bricks as an array(in this game they are [30])
    int[] count = new int[68];
    int destroyedCount = 0;
    int score = 0;
    String scr = Integer.toString(score);
    Font font = new Font("Verdana", Font.BOLD, 18); //declares the fond
    FontMetrics metr = this.getFontMetrics(font); //sets the fond
    

    boolean ingame = true; //checks whether an instance of the game is active
    int timerId; //timerID ;_;


    public Board() {

        addKeyListener(new TAdapter()); //calls the "TAdapter" to add a key listener
        setFocusable(true); //no idea lol


        bricks = new Brick[68]; //sets an array with the number of bricks used
        setDoubleBuffered(true); //double buffer set working
        timer = new Timer(); //creates the game timer
        timer.scheduleAtFixedRate(new ScheduleTask(), 1000, 6); //sets the timer delay to 1000 and the callback time to 10

    }

        public void addNotify() { //no idea lol, has something to do with the class beneath
            super.addNotify();
            gameInit();
        }

    public void gameInit() { //creates stuff

        ball = new Ball(); //creates the ball
        paddle = new Paddle(); //creates the paddle
        bg = new Background(0,0);


        int k = 0; //this whole things creates the bricks from the array (30 bricks in total)
        for (int i = 1; i < 5; i++) { // in 5 rows
            for (int j = 0; j < 17; j++) { //with 6 bricks in each row
                bricks[k] = new Brick(j * 32 + 15, i * 40 + 20); //sets the coordinates of each brick(the first brick is at (30,50) and each brick has a width of 40 and a height of 10

                k++;
            }
        }
    }


    public void paint(Graphics g) { //function for painting and displaying
        super.paint(g);

        if (ingame) { //paints/repaints if the game is in process (refer to "in game")
        	g.drawImage(bg.getImage(), bg.getX(), bg.getY(), //draws the paddle
                    bg.getWidth(), bg.getHeight(), this);
            g.drawImage(ball.getImage(), ball.getX(), ball.getY(), //draws the ball
                        ball.getWidth(), ball.getHeight(), this); 
            g.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(), //draws the paddle
                        paddle.getWidth(), paddle.getHeight(), this);
            g.drawString(scr, (Commons.WIDTH - metr.stringWidth(message)) / 2, //game over message
            Commons.WIDTH / 2);
            
         

            for (int i = 0; i < 68; i++) { //for each of the 30 bricks
                if (!bricks[i].isDestroyed()) //checks if the brick has been destroyed
                    g.drawImage(bricks[i].getImage(), bricks[i].getX(), //draws bricks if they're not destroyed
                                bricks[i].getY(), bricks[i].getWidth(),
                                bricks[i].getHeight(), this);
            }
        } else { //if the game has ended

            g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawString(message,
                         (Commons.WIDTH - metr.stringWidth(message)) / 2, //game over message
                         Commons.WIDTH / 2);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose(); //toolkit no fucking idea 
    }

    private class TAdapter extends KeyAdapter { //TAdapter extending the KeyAdapter

        public void keyReleased(KeyEvent e) { //catches the key release
            paddle.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) { //catches the key release
            paddle.keyPressed(e);
        }
    }


    class ScheduleTask extends TimerTask { //for each call of the timer it calls these function

        public void run() { //runs them (calls them), these are called every 10 msecs

            ball.move(); //ball moves
            paddle.move(); //paddle moves
            checkCollision(); //checks for a collision
            repaint(); //repaints the new screen with new position and remaining bricks

        }
    }

    public void stopGame() { //stops the game
        ingame = false;
        timer.cancel();
    }


    public void checkCollision() { //checks for collision

        if (ball.getRect().getMaxY() > Commons.BOTTOM) { //gets the max y of  ball and checks if it has reached the end of the screen
            stopGame(); //Game Over
        }

        for (int i = 0, j = 0; i < 68; i++) { //counts the destroyed bricks
            if (bricks[i].isDestroyed()) {
                j++;
            }
            if (j == 68) { //if you have destroyed all 30 you win
                message = "Victory";
                stopGame();
            }
        }

        if ((ball.getRect()).intersects(paddle.getRect())) { //if the ball and paddle intersect the motion is changed

            int paddleLPos = (int)paddle.getRect().getMinX(); //gets the min x of the paddle
            int ballLPos = (int)ball.getRect().getMinX(); //gets the min x of the ball

            int first = paddleLPos + 8; //splits the paddle into 4 parts that will give the ball a different direction
            int second = paddleLPos + 16;
            int third = paddleLPos + 24;
            int fourth = paddleLPos + 32;

            if (ballLPos < first) { //if the ball hits the first 7 pixels it is set with left-upward motion
                ball.setXDir(-1);
                ball.setYDir(-1);
            }

            if (ballLPos >= first && ballLPos < second) { //if the ball hits 8-15 pixel it is set left and upwards with the current y direction
                ball.setXDir(-1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos >= second && ballLPos < third) { //sets the ball upwards
                ball.setXDir(0);
                ball.setYDir(-1);
            }

            if (ballLPos >= third && ballLPos < fourth) { //sets the ball to the right and upwards with the current y direction
                ball.setXDir(1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos > fourth) { //sets the ball right and upwards
                ball.setXDir(1);
                ball.setYDir(-1);
            }


        }


        for (int i = 0; i < 68; i++) { //for each of the 30 bricks
            if ((ball.getRect()).intersects(bricks[i].getRect())) { //checks if the balls has hit a brick
                int ballLeft = (int)ball.getRect().getMinX();
                int ballHeight = (int)ball.getRect().getHeight();
                int ballWidth = (int)ball.getRect().getWidth();
                int ballTop = (int)ball.getRect().getMinY();

                Point pointRight =
                    new Point(ballLeft + ballWidth + 1, ballTop); //the is the left side of the bricks
                Point pointLeft = new Point(ballLeft - 1, ballTop); //the is the right side of the bricks
                Point pointTop = new Point(ballLeft, ballTop - 1); //this is the down side of the bricks
                Point pointBottom =
                    new Point(ballLeft, ballTop + ballHeight + 1); //this is the up side of the bricks
                
  
                if (!bricks[i].isDestroyed()) { //if the brick has not yet been destroyed
                    if (bricks[i].getRect().contains(pointRight)) { //sets the motion after the collision left
                        ball.setXDir(-1);
                    }

                    else if (bricks[i].getRect().contains(pointLeft)) { //sets is right
                        ball.setXDir(1);
                    }

                    if (bricks[i].getRect().contains(pointTop)) { //sets it downwards
                        ball.setYDir(1);
                    }

                    else if (bricks[i].getRect().contains(pointBottom)) { //sets is upwards
                        ball.setYDir(-1);
                    }
                    bricks[i].setDestroyed(true);
                    /*bricks[i].setDestroyed(false); // sets the brick not destroyet after the first hit
                    count[i]++; // sets the brick coutner ++
                    if(count[i] == 2) bricks[i].setDestroyed(true); //destroys the brick if it is hitted 2 times                 
*/                    
                }
            }
        }
        
    }
}