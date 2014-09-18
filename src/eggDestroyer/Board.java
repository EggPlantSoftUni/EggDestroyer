package eggDestroyer;

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
	private Background bg;
    Image ii; //declares the image
    Timer timer;
    String message = "Game Over"; //declares the game over message
    Ball ball; //declares the ball
    Bullet bullet;
    Bone bone; //declares the bone
    Egg eggs[];//declares the sum of bricks as an array(in this game they are [68])
    int[] count = new int[68];
    int score = 0;
    String scr = Integer.toString(score);
    ArrayList<Bonus> bonusList;
    ArrayList<Bonus> bonusList1;
    ArrayList<Bonus> bonusList2;
    ArrayList<Bullet> bulletList;
    int brickpoints = 10;
    int bonuspoints = 30;
    public boolean isDeath=false;
    public boolean isShoot=false;
    public boolean readyToShoot = false;
    int bulletsShot = 0;
    Sound play;
    SoundBrake brake;
    String scoreMsg = "Your score: ";
    String fangBang = "FANGBANG!!!";
    boolean isInvisible = false;
    boolean victory = false;
    String vicString = "Victory";
    
    boolean ingame = true; //checks whether an instance of the game is active
    int timerId; //timerID ;_;


    public Board() {
    	
        addKeyListener(new TAdapter()); //calls the "TAdapter" to add a key listener
        setFocusable(true);
        eggs = new Egg[68]; //sets an array with the number of eggs used
        bonusList = new ArrayList<Bonus>();
        bonusList1 = new ArrayList<Bonus>();
        bonusList2 = new ArrayList<Bonus>();
        setDoubleBuffered(true); //double buffer set working
        timer = new Timer(); //creates the game timer
        timer.scheduleAtFixedRate(new ScheduleTask(), 1000, 5); //sets the timer delay to 1000 and the callback time to 10
        bulletList = new ArrayList<Bullet>();
    }

        public void addNotify() {
            super.addNotify();
            gameInit();
        }

    public void gameInit() { //initialize game

        ball = new Ball(); //creates the ball
        bone = new Bone(); //creates the bone
        bg = new Background(0,0); //creates the background
        bullet = new Bullet();
        try {
			play = new Sound();
		} catch (Exception e) {
			e.printStackTrace();
		}
        

        int k = 0; //this whole things creates the eggs from the array (68 eggs in total)
        for (int i = 1; i < 5; i++) { // in 5 rows
            for (int j = 0; j < 17; j++) { //with 6 bricks in each row
                eggs[k] = new Egg(j * 32 + 15, i * 40 + 20); //sets the coordinates of each egg
                k++;
            }
        }
    }
    public void shots(){
    	  if (bullet.isShot() && readyToShoot && bulletsShot < 15){
    	   Bullet bullet1 = new Bullet();
    	   bullet1.setX(bone.getX() + (bone.getWidth() - bullet1.getWidth()) / 2);
    	         bullet1.setY(bone.getY() + (bone.getHeight() - bullet1.getHeight()) / 2);
    	         bulletList.add(bullet1);
    	         bulletsShot++;
    	  } else if(readyToShoot && bulletsShot >= 15){
    		  readyToShoot = false;
    	  }
	 }


    public void paint(Graphics g) { //function for painting and displaying
        super.paint(g);

        if (ingame) { //paints/repaints if the game is in process (refer to "in game")
        	bg.draw(g, this);        

        	for (int i = 0; i < 68; i++) { //for each of the 68 eggs
                if (!eggs[i].isDestroyed()){ 
                	if (!eggs[i].isCracked() && !eggs[i].isDestroyed()){
                		eggs[i].draw(g,  this);
                	}
                	else{
                		eggs[i].draw1(g, this);
                	}
                }       	
        	}
        	
            for (Bonus bonum : bonusList) {
            	bonum.draw(g, this);
			}
            for (Bonus bonum : bonusList1) {
            	bonum.draw1(g, this);
			}
            for (Bonus bonum : bonusList2) {
            	bonum.draw2(g, this);
			}
            for (Bullet bul : bulletList){
                bul.draw(g,  this);
			}
            if (readyToShoot)
            {
                    	Font font2 = new Font("Verdana", Font.BOLD, 20); //declares the font
                        FontMetrics metr2 = this.getFontMetrics(font2); //sets the font
                        g.setColor(Color.RED);
                        g.setFont(font2);
                        g.drawString(fangBang,
                                     (Commons.WIDTH - metr2.stringWidth(fangBang)) / 2, //ready to shoot message
                                     Commons.WIDTH / 2+20);	
            }
            if(!isInvisible){
            bone.draw(g, this);}
        	ball.draw(g, this);
        	
        	String scorestring ="Score: " + Integer.toString(score); //ingame score
        	Font font = new Font("Verdana", Font.BOLD, 14); //declares the font
            FontMetrics metr = this.getFontMetrics(font); //sets the font
        	g.setColor(Color.RED);
            g.setFont(font);
            g.drawString(scorestring,
                         (Commons.WIDTH - this.getFontMetrics(font).stringWidth(scorestring)) - 500, //game over message
                         Commons.HEIGTH - 30);
        }else if(victory){
        	bg.draw2(g, this);
        	Font font1 = new Font("Verdana", Font.BOLD, 35); //declares the fond
            FontMetrics metr1 = this.getFontMetrics(font1); //sets the fond
         g.setColor(Color.RED);
            g.setFont(font1);
            g.drawString(vicString,
                         (Commons.WIDTH - metr1.stringWidth(vicString)) / 2, //victory message
                         Commons.WIDTH / 2-10);
            
            Font font2 = new Font("Verdana", Font.BOLD, 20); //declares the fond
            FontMetrics metr2 = this.getFontMetrics(font2); //sets the fond
         g.setColor(Color.RED);
            g.setFont(font2);
            g.drawString(scoreMsg + score,
                         (Commons.WIDTH - metr2.stringWidth(scoreMsg)) / 2, //victory score
                         Commons.WIDTH / 2+20);
        } 
        else { //if the game has ended

        	bg.draw1(g, this);
        	Font font1 = new Font("Verdana", Font.BOLD, 35); //declares the fond
            FontMetrics metr1 = this.getFontMetrics(font1); //sets the fond
         g.setColor(Color.BLACK);
            g.setFont(font1);
            g.drawString(message,
                         (Commons.WIDTH - metr1.stringWidth(message)) / 2, //game over message
                         Commons.WIDTH / 2-10);
            
            Font font2 = new Font("Verdana", Font.BOLD, 20); //declares the fond
            FontMetrics metr2 = this.getFontMetrics(font2); //sets the fond
         g.setColor(Color.BLACK);
            g.setFont(font2);
            g.drawString(scoreMsg + score,
                         (Commons.WIDTH - metr2.stringWidth(scoreMsg)) / 2, //game over message
                         Commons.WIDTH / 2+20);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose(); //stops drawing
    }

    private class TAdapter extends KeyAdapter { //TAdapter extending the KeyAdapter
    	
        public void keyReleased(KeyEvent e) { //catches the key release
            bone.keyReleased(e);
            bullet.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) { //catches the key release
            bone.keyPressed(e);
            bullet.keyPressed(e);
        }
    }


    class ScheduleTask extends TimerTask { //for each call of the timer it calls these function

        public void run() { //runs them (calls them), these are called every 10 msecs
            ball.move(); //ball moves
            bone.move();
            shots();
            for (Bullet bul : bulletList)
                bul.move();
            for (Bonus bonus : bonusList)
               	bonus.move();
            for (Bonus bonus : bonusList1)
               	bonus.move();
            for (Bonus bonus : bonusList2)
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
        for (int i = 0, j = 0; i < 68; i++) { //counts the destroyed eggs
            if (eggs[i].isDestroyed()) {
                j++;
            }
            if (j == 68) { //if you have destroyed all 68 eggs you win
                victory = true;
               stopGame();
            }
        }

        if ((ball.getRect()).intersects(bone.getRect())) { //if the ball and bone intersect the motion is changed

            int paddleLPos = (int)bone.getRect().getMinX(); //gets the min x of the bone
            int ballLPos = (int)ball.getRect().getMinX(); //gets the min x of the ball

            int first = paddleLPos + 8; //splits the bone into 4 parts that will give the ball a different direction
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
            if ((bonus.getRect()).intersects(bone.getRect())) {
            	score += bonuspoints;
            	bonusList.remove(index);
            } else if (bonus.getRect().getMaxY() > Commons.BOTTOM) {
            	bonusList.remove(index);
            }
        }
        for (int index = bonusList1.size() - 1; 0 <= index; index--) {
        	Bonus bonus = bonusList1.get(index);
            if ((bonus.getRect()).intersects(bone.getRect())) {
            	isInvisible = true;
            	bonusList1.remove(index);
            } else if (bonus.getRect().getMaxY() > Commons.BOTTOM) {
            	bonusList1.remove(index);
            }
        }
        for (int index = bonusList2.size() - 1; 0 <= index; index--) {
        	Bonus bonus = bonusList2.get(index);
            if ((bonus.getRect()).intersects(bone.getRect())) {
            	readyToShoot = true;
        		bulletsShot = 0;
        		bonusList2.remove(index);
            } else if (bonus.getRect().getMaxY() > Commons.BOTTOM) {
            	bonusList2.remove(index);
            }
        }

        for (int i = 0; i < eggs.length; i++) { //for each of the 68 eggs
            if ((ball.getRect()).intersects(eggs[i].getRect())) { //checks if the balls has hit a egg
                int ballLeft = (int)ball.getRect().getMinX();
                int ballHeight = (int)ball.getRect().getHeight();
                int ballWidth = (int)ball.getRect().getWidth();
                int ballTop = (int)ball.getRect().getMinY();

                Point pointRight =
                    new Point(ballLeft + ballWidth + 1, ballTop); //the is the left side of the eggs
                Point pointLeft = new Point(ballLeft - 1, ballTop); //the is the right side of the eggs
                Point pointTop = new Point(ballLeft, ballTop - 1); //this is the down side of the eggs
                Point pointBottom =
                    new Point(ballLeft, ballTop + ballHeight + 1); //this is the up side of the eggs

                Egg brick = eggs[i];
                if (!brick.isDestroyed()) { //if the egg has not yet been destroyed
                	if (!brick.isCracked()){
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

                        brick.setCracked(true); //destroys the egg
                        
                	}
                	else {
                		if (brick.getRect1().contains(pointRight)) { //sets the motion after the collision left
                            ball.setXDir(-1);
                        }

                        else if (brick.getRect1().contains(pointLeft)) { //sets is right
                            ball.setXDir(1);
                        }

                        if (brick.getRect1().contains(pointTop)) { //sets it downwards
                            ball.setYDir(1);
                        }

                        else if (brick.getRect1().contains(pointBottom)) { //sets is upwards
                            ball.setYDir(-1);
                        }

                        brick.setDestroyed(true); //destroys the egg
                        score += brickpoints;
                        try {
            				brake = new SoundBrake();
            			} catch (Exception e) {
            				e.printStackTrace();
            			}
                        Random rand = new Random();
                        if (rand.nextInt(10) == 0) {
    	                    Bonus bonus = new Bonus();
    	                    bonus.setX(brick.getX() + (brick.getWidth() - bonus.getWidth()) / 2);
    	                    bonus.setY(brick.getY() + (brick.getHeight() - bonus.getHeight()) / 2);
    	                    bonusList.add(bonus);
                        } else if(rand.nextInt(10) == 5){
                        	Bonus bonus = new Bonus();
    	                    bonus.setX(brick.getX() + (brick.getWidth() - bonus.getWidth()) / 2);
    	                    bonus.setY(brick.getY() + (brick.getHeight() - bonus.getHeight()) / 2);
    	                    bonusList2.add(bonus);
                        } else if(rand.nextInt(10) == 7){
                        	Bonus bonus = new Bonus();
    	                    bonus.setX(brick.getX() + (brick.getWidth() - bonus.getWidth()) / 2);
    	                    bonus.setY(brick.getY() + (brick.getHeight() - bonus.getHeight()) / 2);
    	                    bonusList1.add(bonus);
                        }
                }
            }
        }
            if(bulletList.size() > 0) {
            for (int index = bulletList.size() - 1; 0 <= index; index--) {
            	Bullet bullet = bulletList.get(index);
            	Egg brick = eggs[i];
                if ((bullet.getRect()).intersects(eggs[i].getRect()) && !brick.isDestroyed()) {
                    	if (!brick.isCracked()){
                	
                	bulletList.remove(index);
                	brick.setCracked(true);
                	
                	} else{
                    	score += brickpoints;
                    	bulletList.remove(index);
                    	brick.setDestroyed(true);
                    	/*try {
            				brake = new SoundBrake();
            			} catch (Exception e) {
            				e.printStackTrace();
            			}*/
                }}
                else if (bullet.getRect().getMaxY() < 0) {
                	bulletList.remove(index);
                }
            }
            }
}}}