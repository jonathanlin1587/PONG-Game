/**
 * @author jonathanlin
 * @version 2023-01-25
 * 
 * The Paddle class are the paddles in the Pong game.
 * It extends the Rectangle class, and has properties for number, yVelocity, and speed.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Paddle extends Rectangle{
	//Declaring variables for the paddle class
	int number;
	int yVel;
	int speed = 10;
	
	/**
     * Constructor of the Paddle class
     * 
     * @param x 		x-coordinate of the paddle
     * @param y 		y-coordinate of the paddle
     * @param pWidth 	width of the paddle
     * @param pHeight 	height of the paddle
     * @param number 	number of the paddle
     * @return none
     */
	Paddle(int x, int y, int pWidth, int pHeight, int number) {
		super(x,y,pWidth,pHeight);
		this.number = number;
	}
	
	/**
     * keyPressed method is called when a key is pressed.
     * 
     * @param e 	the key event that is performed
     * @return none
     */
	public void keyPressed(KeyEvent e) {
		switch(number) {
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(-speed);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(speed);
				move();
			}
			break;
		case 2:
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(-speed);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(speed);
				move();
			}
			break;
		}	
	}
	
	/**
     * keyReleased method is called when a key is released.
     * 
     * @param e 	the key event that is performed
     * @return none
     */
	public void keyReleased(KeyEvent e) {
		switch(number) {
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(0);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(0);
				move();
			}
			break;
		case 2:
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(0);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(0);
				move();
			}
			break;
		}
		
	}
	
	/**
     * setYDirection method is the setter method for yDirection
     * 
     * @param yDirection
     * @return none
     */
	public void setYDirection(int yDirection) {
		yVel = yDirection;
	}
	
	/**
     * move method moves the paddles
     * 
     * @param none
     * @return none
     */
	public void move() {
		y = y + yVel;
	}
	
	/**
     * draw method draws the paddles
     * 
     * @param g
     * @return none
     */
	public void draw(Graphics g) {
		if(number == 1) {
			g.setColor(Color.white);
		}
		else {
			g.setColor(Color.white);
		}
		g.fillRect(x, y, width, height);
	}

	/**
     * getNumber method is the getter method for number
     * 
     * @param none
     * @return number
     */
	public int getNumber() {
		return number;
	}

	/**
     * setNumber method is the setter method for number
     * 
     * @param number
     * @return none
     */
	public void setNumber(int number) {
		this.number = number;
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
     * getSpeed method is the getter method for speed
     * 
     * @param none
     * @return speed
     */
	public int getSpeed() {
		return speed;
	}

	/**
     * setSpeed method is the setter method for speed
     * 
     * @param speed
     * @return none
     */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
