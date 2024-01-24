/**
 * @author jonathanlin
 * @version 2023-01-25
 * 
 * The Instructions class is displays the instructions menu in the Pong game.
 * It contains a JFrame and JPanel to display the menu, as well as buttons to navigate to the
 * start of the game or back to the main menu.
*/

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Instructions {
	//Constant variables to set the dimensions of screen
	static final int SCREEN_WIDTH = 1000;
	static final int SCREEN_HEIGHT = (int) (SCREEN_WIDTH * (0.6));
	
	//Declaring variables
	Pong pong;
	File file;
	JFrame menu;
	JPanel p;
	JButton startButton;
	JButton backButton;
	JLabel title;
	ImageIcon image;
	
	/**
	 * Constructor for the Instructions class. 
	 * It initializes the variables, sets up the layout 
	 * and action listeners for the buttons, and displays the menu.
	 * 
	 * @param none
	 * @return none
	 */
	Instructions() {
		pong = new Pong();
		file = new File("PlayerNames.txt");
		menu = new JFrame();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		
		p = new JPanel();
        p.setLayout(null);
        
        //BUTTONS
        startButton = new JButton("START");
      	startButton.setBounds(SCREEN_WIDTH-185, 30, 160, 75);
      	
      	backButton = new JButton("BACK");
      	backButton.setBounds(30, 30, 160, 75);
        
		image = new ImageIcon("images/InstructionsMenu.jpg");
		
		title = new JLabel();
		title.setIcon(image);
		title.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.TOP);
		
		//Action Listeners
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
            	pong.player1Username = (String) JOptionPane.showInputDialog(null, "ENTER PLAYER 1 (LEFT SIDE):", "player1"); 
            	pong.player2Username = (String) JOptionPane.showInputDialog(null, "ENTER PLAYER 2 (RIGHT SIDE):", "player2");
            	pong.maxScore =  JOptionPane.showInputDialog(null, "ENTER SCORE TO PLAY UP TO:", "5");
            	
            	//saving player names and max score in txt file
            	try {
            		file.delete();
    	            FileWriter fw = new FileWriter(file, true);
    	            fw.write(pong.player1Username + "\n");
    	            fw.write(pong.player2Username + "\n");
    	            fw.write(pong.maxScore);
    	            fw.close();
    	            
    	        } 
    	        catch (IOException ie) {
    	            System.out.println("An error occurred while writing to the file.");
    	            ie.printStackTrace();
    	        }
            	
                menu.dispose();
                new GameFrame();
            } 
        });
		
		backButton.addActionListener(new ActionListener() {
			/**
			* ActionListener for the back button, re-opens
			* the main menu frame and disposes current frame.
			* 
			* @param e
			* @return none
			*/
            public void actionPerformed(ActionEvent e) {
            	menu.dispose();
            	new GameMenu();
            } 
        });
		
		//adding all the elements to the panel
		p.add(startButton);
		p.add(backButton);
		p.add(title);
		
		//adding the panel to the frame
		menu.add(p);
		menu.setVisible(true);
		menu.setLocationRelativeTo(null);
		menu.setResizable(false);
	}
}
