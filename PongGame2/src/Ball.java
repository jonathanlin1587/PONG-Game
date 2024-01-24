/**
 * @author jonathanlin
 * @version 2023-01-25
 * 
 * The Ball class is the ball in the Pong game.
 * It extends the Rectangle class, and has properties 
 * for random, xVelocity, yVelocity, and iSpeed.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle {
	//Declaring variables for the Ball class
	Random random;
	int xVel;
	int yVel;
	int iSpeed = 3;
	
	/**
     * Constructor of the Ball class
     * 
     * @param x x-coordinate of the ball
     * @param y y-coordinate of the ball
     * @param width width of the ball
     * @param height height of the ball
     * @return none
     */
	Ball (int x, int y, int width, int height) {
		super(x, y, width, height);
		
		random = new Random();
		int randXDirection = random.nextInt(2);
		if (randXDirection == 0) {
			randXDirection--;
		}
		setXDirection(randXDirection*iSpeed);
		
		int randYDirection = random.nextInt(2);
		if (randYDirection == 0) {
			randYDirection--;
		}
		setYDirection(randYDirection*iSpeed);
		
	}
	/**
     * setXDirection method is the setter method for randomXDirection
     * 
     * @param randomXDirection
     * @return none
     */
	public void setXDirection(int randomXDirection) {
		xVel = randomXDirection;
	}
	
	/**
     * setYDirection method is the setter method for randomYDirection
     * 
     * @param randomYDirection
     * @return none
     */
	public void setYDirection(int randomYDirection) {
		yVel = randomYDirection;
	}
	
	/**
     * move method moves the ball
     * 
     * @param none
     * @return none
     */
	public void move() {
		x += xVel;
		y += yVel;
	}
	
	/**
     * draw method draws the ball
     * 
     * @param g
     * @return none
     */
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}

	/**
     * Random method is the getter method for random
     * 
     * @param none
     * @return random
     */
	public Random getRandom() {
		return random;
	}

	/**
     * setRandom method is the setter method for random
     * 
     * @param random
     * @return none
     */
	public void setRandom(Random random) {
		this.random = random;
	}
	
	/**
     * getxVel method is the getter method for xVel
     * 
     * @param none
     * @return xVel
     */
	public int getxVel() {
		return xVel;
	}

	/**
     * setxVel method is the setter method for xVel
     * 
     * @param xVel
     * @return none
     */
	public void setxVel(int xVel) {
		this.xVel = xVel;
	}
	
	/**
     * getyVel method is the getter method for yVel
     * 
     * @param none
     * @return yVel
     */
	public int getyVel() {
		return yVel;
	}

	/**
     * setyVel method is the setter method for yVel
     * 
     * @param yVel
     * @return none
     */
	public void setyVel(int yVel) {
		this.yVel = yVel;
	}

	/**
     * getiSpeed method is the getter method for getiSpeed
     * 
     * @param none
     * @return iSpeed
     */
	public int getiSpeed() {
		return iSpeed;
	}

	/**
     * setiSpeed method is the setter method for setiSpeed
     * 
     * @param iSpeed
     * @return none
     */
	public void setiSpeed(int iSpeed) {
		this.iSpeed = iSpeed;
	}
}
