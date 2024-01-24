/**
 * @author jonathanlin
 * @version 2023-01-25
 * 
 * The GameMenu class is the menu frame of the Pong game.
 * It allows the player to start the game, view instructions, 
 * view past games, and quit the game.
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class GameMenu {
	//Constant variables to set the screen dimensions
	static final int SCREEN_WIDTH = 1000;
	static final int SCREEN_HEIGHT = (int) (SCREEN_WIDTH * (0.6));
	
	//Declaring variables
	Pong pong;
	Score score;
	File file;
	ImageIcon image;
	
	JFrame menu;
	JPanel p;
	
	JButton startButton;
	JButton instructionsButton;
	JButton pastGamesButton;
	JButton quitButton;
	JLabel logo;
	
	FileWriter fw;
	
	/**
	* GameMenu creates an instance of different objects, sets the frame, 
	* creates the panel to later add to the frame, creates an ImageIcon object,
	* creates buttons, and adds Action Listeners for each button.
	* 
	* @param none
	* @return none
	*/
	GameMenu() {
		//Creating an instance of different objects
		pong = new Pong();
		score = new Score(SCREEN_WIDTH, SCREEN_HEIGHT);
		file = new File("PlayerNames.txt");
		
		//Setting the frame
		menu = new JFrame();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		
		//Creating the panel to later add to the frame
		p = new JPanel();
        p.setLayout(null);
		
		//Creating an ImageIcon object
		image = new ImageIcon("images/PONG.png");
		
		//BUTTONS
		startButton = new JButton("START");
		startButton.setBounds(260, 275, 500, 50);
		
		instructionsButton = new JButton("INSTRUCTIONS");
		instructionsButton.setBounds(260, 350, 500, 50);
		
		pastGamesButton = new JButton("PAST GAMES");
		pastGamesButton.setBounds(260, 425, 500, 50);
		
		quitButton = new JButton("QUIT");
		quitButton.setBounds(260, 500, 500, 50);
		
		//Action Listeners for each button
		startButton.addActionListener(new ActionListener() { 
			/**
			* ActionListener for the start button, opens a JOptionPane to ask 
			* the user to enter the user name of both players and what score they 
			* want to play up to, saves the player names in a text file, disposes 
			* of the current frame and opens a new GameFrame.
			* 
			* @param e
			* @return none
			*/
            public void actionPerformed(ActionEvent e) {
            	//Opening up a "JOptionPane" to ask user to enter the user name of both players and how much they want to play up to
            	pong.player1Username = (String) JOptionPane.showInputDialog(null, "ENTER PLAYER 1 (LEFT SIDE):", "player1");
            	pong.player2Username = (String) JOptionPane.showInputDialog(null, "ENTER PLAYER 2 (RIGHT SIDE):", "player2");
            	pong.maxScore =  JOptionPane.showInputDialog(null, "ENTER SCORE TO PLAY UP TO:", "5");
            	
            	//Saving player names in txt file
            	try {
            		file.delete(); //first clearing previous information
    	            fw = new FileWriter(file, true); //making new file
    	            fw.write(pong.player1Username + "\n"); //adding player1's user name
    	            fw.write(pong.player2Username + "\n"); //adding player2's user name
    	            fw.write(pong.maxScore); //adding the max score
    	            fw.close(); //closing the file writer
    	            
    	        } 
    	        catch (IOException ie) {
    	            System.out.println("An error occurred while writing to the file.");
    	            ie.printStackTrace();
    	        }
            	//Disposing of the current frame and opening new frame
                menu.dispose();
                new GameFrame();
            } 
        });
		
		instructionsButton.addActionListener(new ActionListener() { 
			/**
			* ActionListener for the instructions button, 
			* disposes of the current frame and opens a new Instructions.
			* 
			* @param e
			* @return none
			*/
            public void actionPerformed(ActionEvent e) {
            	//Disposing of the current frame and opening new frame
            	menu.dispose();
            	new Instructions();
            } 
        });
		
		pastGamesButton.addActionListener(new ActionListener() { 
			/**
			* ActionListener for the past games button, 
			* disposes of the current frame and opens a new PastGames.
			* 
			* @param e
			* @return none
			*/
            public void actionPerformed(ActionEvent e) {
            	//Disposing of the current frame and opening new frame
            	menu.dispose();
            	new PastGames();
            } 
        });
		
		quitButton.addActionListener(new ActionListener() { 
			/**
			* ActionListener for the quit button, 
			* disposes of the current frame and quitting the game.
			* 
			* @param e
			* @return none
			*/
            public void actionPerformed(ActionEvent e) {
            	//Disposing of the current frame and quitting the game
            	menu.dispose();
            } 
        });
		
		//LABELS
		logo = new JLabel();
		logo.setIcon(image);
		logo.setBounds(70, 20, 870, 218);
		logo.setHorizontalAlignment(JLabel.CENTER);
		logo.setVerticalAlignment(JLabel.TOP);
		
		//Adding the elements to panel
		p.add(logo);
		p.add(startButton);
		p.add(pastGamesButton);
		p.add(instructionsButton);
		p.add(quitButton);
		p.setBackground(Color.black); //setting the background colour
		
		//Adding the panel to frame
		menu.add(p);
		menu.setVisible(true);
		menu.setLocationRelativeTo(null);
		menu.setResizable(false);

	}
}
