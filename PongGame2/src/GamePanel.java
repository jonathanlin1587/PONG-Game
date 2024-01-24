/**
 * @author jonathanlin
 * @version 2023-01-25
 * 
 * GamePanel class is responsible for creating the 
 * game panel, it contains the game loop and 
 * the game logic. It creates an instance of Pong 
 * and Score objects, and sets up the panel properties.
*/

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
	//Constant variables to set the dimensions of screen, ball, and paddle
	static final int SCREEN_WIDTH = 1000;
	static final int SCREEN_HEIGHT = (int) (SCREEN_WIDTH * (0.6));
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;  
	final Dimension SCREEN_SIZE = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
	boolean gameRunning = true;
	
	//Declaring variables
	Pong pong;
	Score score;
	
	Thread gThread;
	Image image;
	Graphics graphics;
	Random rand;
	Paddle pad1;
	Paddle pad2;
	Ball ball;
	GameFrame frame;
	
	/**
	* GamePanel class is responsible for creating the game panel,
	* it contains the game loop and the game logic. 
	* It creates an instance of Pong and Score objects, and sets up the panel properties.
	*
	* @param frame
	* @return none
	*/
	GamePanel(GameFrame frame) {
		this.frame = frame;
		newPaddles();
		newBall();
		pong = new Pong();
		score = new Score(SCREEN_WIDTH, SCREEN_HEIGHT);
		this.setFocusable(true);
		this.setPreferredSize(SCREEN_SIZE);
		this.addKeyListener(new AL());
		
		gThread = new Thread(this);
		gThread.start();
	}
	
	/**
	* newBal method creates a new ball.
	*
	* @param none
	* @return none
	*/
	public void newBall() {
		rand = new Random();
		ball = new Ball((SCREEN_WIDTH/2) - (BALL_DIAMETER/2), rand.nextInt(SCREEN_HEIGHT-BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
	}

	/**
	* newPaddles method creates new paddles.
	*
	* @param none
	* @return none
	*/
	public void newPaddles() {
		pad1 = new Paddle(0, (SCREEN_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		pad2 = new Paddle(SCREEN_WIDTH-PADDLE_WIDTH, (SCREEN_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
	}
	
	/**
	* paint method pants the game panel.
	*
	* @param none
	* @return none
	*/
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}
	
	/**
	* draw method draws the paddle and ball onto the screen.
	*
	* @param none
	* @return none
	*/
	public void draw(Graphics g) {
		pad1.draw(g);
		pad2.draw(g);
		ball.draw(g);
		score.draw(g);
	}
	
	/**
	* move method is used to move the paddles and ball.
	*
	* @param none
	* @return none
	*/
	public void move() {
		pad1.move();
		pad2.move();
		ball.move();
	}
	
	/**
	* checkCollision method checks for collisions;
	* it checks if the ball hits the walls, hits the paddles,
	* and also increases player scores when the ball hits 
	* the right or left wall
	*
	* @param none
	* @return none
	*/
	public void checkCollision() {
	    // Check if ball hits top or bottom edge of screen
		if(ball.getY() <= 0) {
			ball.setYDirection(-ball.getyVel());
		}
		else if(ball.getY() >= SCREEN_HEIGHT-BALL_DIAMETER) {
			ball.setYDirection(-ball.getyVel());
		}
		
		// Check if ball hits paddle 1
	    if (ball.intersects(pad1)) {
	        ball.setxVel(Math.abs(ball.getxVel()) + 1);
	        if (ball.getxVel() > 0) {
	            ball.setyVel(ball.getyVel() + 1);
	        } 
	        else {
	            ball.setyVel(ball.getyVel() - 1);
	        }
	        ball.setXDirection(ball.getxVel());
	        ball.setYDirection(ball.getyVel());
	    }
	    
		
	    // Check if ball hits paddle 2
	    if (ball.intersects(pad2)) {
	        ball.setxVel(Math.abs(ball.getxVel()) + 1);
	        if (ball.getxVel() > 0) {
	            ball.setyVel(ball.getyVel() + 1);
	        } 
	        else {
	            ball.setyVel(ball.getyVel() - 1);
	        }
	        ball.setXDirection(-ball.getxVel());
	        ball.setYDirection(ball.getyVel());
	    }
		
		// Check if paddles hit top or bottom edge of screen
	    if (pad1.getY() <= 0) {
	        pad1.y = 0;
	    } 
	    else if (pad1.getY() >= SCREEN_HEIGHT - PADDLE_HEIGHT) {
	    	pad1.y = SCREEN_HEIGHT-PADDLE_HEIGHT;
	    }

	    if (pad2.getY() <= 0) {
	    	pad2.y = 0;
	    } 
	    else if (pad2.getY() >= SCREEN_HEIGHT - PADDLE_HEIGHT) {
	    	pad2.y = SCREEN_HEIGHT-PADDLE_HEIGHT;
	    }
	    
	    // Check if ball hits left or right edge of screen
		if(ball.x >= SCREEN_WIDTH-BALL_DIAMETER) {
			//System.out.println("(before incrementing) Player 1 name: " + pong.score.p1);
			score.incrementPlayer1(); //+player1 score
			newPaddles();
			newBall();
		}
		
		if(ball.x <= 0) {
			score.incrementPlayer2(); //+player2 score
			newPaddles();
			newBall();
		}
	}
	/**
	* run method is the game loop in which the game runs
	*
	* @param none
	* @return none
	*/
	public void run() {
		//game loop
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(gameRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				move();
				checkCollision();
				repaint();
				try {
					Thread.sleep(10);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if(score.isGameOver()) {
					gameRunning = false;
					GameOverHandler gameOverHandler = new GameOverHandler( );
	                gameOverHandler.handleGameOver(score.getWinner(), frame);
				}
				delta--;
			}
		}	
	}
	
	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			pad1.keyPressed(e);
			pad2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			pad1.keyReleased(e);
			pad2.keyReleased(e);
		}
	}
}