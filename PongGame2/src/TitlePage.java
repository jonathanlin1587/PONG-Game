/**
 * @author jonathanlin
 * @version 2023-01-25
 * 
 * The Title class gives an introduction to the game
 * It contains my name, the teachers name, the course code, 
 * the name of the game and the date.
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class TitlePage {
	static final int SCREEN_WIDTH = 1000;
	static final int SCREEN_HEIGHT = (int) (SCREEN_WIDTH * (0.6));
	
	/**
	* TitlePage creates a JFrame, a JPanel and a JButton
	* It is the constructor for the TitlePage class.
	* There is a button on the screen that allows the user
	* to enter into the game
	* 
	* @param none
	* @return none
	*/
	TitlePage() {
		JFrame menu;
		JPanel p;
		ImageIcon image;
		JButton enterButton;
		JLabel title;
		
		//Setting the frame
		menu = new JFrame();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		
		//Creating the panel to later add to the frame
		p = new JPanel();
        p.setLayout(null);
        
        //Button
		enterButton = new JButton("ENTER GAME");
		enterButton.setBounds(260, 450, 500, 50);

		image = new ImageIcon("images/TitlePage.jpg");
		
		title = new JLabel();
		title.setIcon(image);
		title.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.TOP);
		
		enterButton.addActionListener(new ActionListener() { 
			/**
			* ActionListener for the enter button, 
			* disposes of the current frame and opens a new GameMenu.
			* 
			* @param e
			* @return none
			*/
            public void actionPerformed(ActionEvent e) {
            	//Disposing of the current frame and opening new frame
            	menu.dispose();
            	new GameMenu();
            } 
        });
		
		//Adding elements to panel
		p.add(enterButton);
		p.add(title);
		p.setBackground(Color.black); //setting the background colour
		
		//Adding the panel to frame
		menu.add(p);
		menu.setVisible(true);
		menu.setLocationRelativeTo(null);
		menu.setResizable(false);
	}
}
