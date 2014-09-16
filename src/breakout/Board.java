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
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;


public class Board extends JPanel implements Commons { //this contains the game design, mechanics and logic

    Image ii; //declares the image
    Timer timer; //declares the timer
    String message = "Game Over"; //declares the game over message
    Ball ball; //declares the ball
    Paddle paddle; //declares the paddle
    Brick bricks[]; 
    ArrayList<Bonus> bonusList;//declares the sum of bricks as an array(in this game they are [30])
    int score = 0;
    int brickpoints = 10;
    int bonuspoints = 30;
    
    boolean ingame = true; //checks whether an instance of the game is active
    int timerId; //timerID ;_;


    public Board() {

        addKeyListener(new TAdapter()); //calls the "TAdapter" to add a key listener
        setFocusable(true); //no idea lol

        bricks = new Brick[36]; //sets an array with the number of bricks used
        bonusList = new ArrayList<Bonus>();
        setDoubleBuffered(true); //double buffer set working
        timer = new Timer(); //creates the game timer
        timer.scheduleAtFixedRate(new ScheduleTask(), 1000, 10); //sets the timer delay to 1000 and the callback time to 10
    }

        public void addNotify() { //no idea lol, has something to do with the class beneath
            super.addNotify();
            gameInit();
        }

    public void gameInit() { //creates stuff

        ball = new Ball(); //creates the ball
        paddle = new Paddle(); //creates the paddle


        int k = 0; //this whole things creates the bricks from the array (30 bricks in total)
        for (int i = 0; i < 6; i++) { // in 5 rows
            for (int j = 0; j < 6; j++) { //with 6 bricks in each row
                bricks[k] = new Brick(j * 40 + 30, i * 10 + 50); //sets the coordinates of each brick(the first brick is at (30,50) and each brick has a width of 40 and a height of 10
                k++;
            }
        }
    }


    public void paint(Graphics g) { //function for painting and displaying
        super.paint(g);

        if (ingame) { //paints/repaints if the game is in process (refer to "in game")

            for (int i = 0; i < 36; i++) { //for each of the 30 bricks
                if (!bricks[i].isDestroyed()) //checks if the brick has been destroyed
                	bricks[i].draw(g,  this);
            }
            
            for (Bonus bonum : bonusList)
            	bonum.draw(g,  this);
            
        	paddle.draw(g, this);
        	ball.draw(g, this);
        	Font font = new Font("Verdana", Font.BOLD, 18); //declares the fond
            FontMetrics metr = this.getFontMetrics(font); //sets the fond
        	String scorestring = Integer.toString(score);
        	g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawString(scorestring,
                         (Commons.WIDTH - this.getFontMetrics(font).stringWidth(scorestring)) - 20, //game over message
                         Commons.WIDTH - 280);
        } else { //if the game has ended

            Font font = new Font("Verdana", Font.BOLD, 18); //declares the fond
            FontMetrics metr = this.getFontMetrics(font); //sets the fond

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
            paddle.move(); 
            for (Bonus bonus : bonusList)
               	bonus.move();
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

        for (int i = 0, j = 0; i < 36; i++) { //counts the destroyed bricks
            if (bricks[i].isDestroyed()) {
                j++;
            }
            if (j == 36) { //if you have destroyed all 30 you win
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
        
        
        for (int index = bonusList.size() - 1; 0 <= index; index--) {
        	Bonus bonus = bonusList.get(index);
            if ((bonus.getRect()).intersects(paddle.getRect())) {
            	score += bonuspoints;
            	bonusList.remove(index);
            }
            else if (bonus.getRect().getMaxY() > Commons.BOTTOM) {
            	bonusList.remove(index);
            }
        }

        for (int i = 0; i < 36; i++) { //for each of the 30 bricks
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

                Brick brick = bricks[i];
                if (!brick.isDestroyed()) { //if the brick has not yet been destroyed
                    if (brick.getRect().contains(pointRight)) { //sets the motion after the collision left
                        ball.setXDir(-1);
                    }

                    else if (brick.getRect().contains(pointLeft)) { //sets is right
                        ball.setXDir(1);
                    }

                    if (brick.getRect().contains(pointTop)) { //sets it downwards
                        ball.setYDir(1);
                    }

                    else if (brick.getRect().contains(pointBottom)) { //sets is upwards
                        ball.setYDir(-1);
                    }

                    brick.setDestroyed(true); //destroys the brick
                    score += brickpoints;
                    
                    Random rand = new Random();
                    if (rand.nextInt(4) == 0) {
	                    Bonus bonus = new Bonus();
	                    bonus.setX(brick.getX() + (brick.getWidth() - bonus.getWidth()) / 2);
	                    bonus.setY(brick.getY() + (brick.getHeight() - bonus.getHeight()) / 2);
	                    bonusList.add(bonus);
                    }    
                }
            }
        }
    }
}