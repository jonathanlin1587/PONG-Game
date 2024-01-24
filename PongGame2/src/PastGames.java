/**
 * @author jonathanlin
 * @version 2023-01-25
 * 
 * The PastGames class is responsible for displaying the past games played in the Pong game.
 * It extends the JFrame class and has properties for menu, panel, back button, label, and text area.
 * It also reads from a file "PastGames.txt" and displays the past games in a text area.
*/

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class PastGames {
	//Constant variables to set the dimensions of screen
	static final int SCREEN_WIDTH = 1000;
	static final int SCREEN_HEIGHT = (int) (SCREEN_WIDTH * (0.6));
	
	//Declaring variables
	JFrame menu;
	JPanel p;
	JButton backButton;
	JLabel label;
	JTextArea textArea;
	
	BufferedReader reader;
	LinkedList<String> lines;
	String line;
	
	/**
	 * Constructor for the PastGames class.
	 * It creates the frame, panel, back button, label, and text area.
	 * It also reads from the "PastGames.txt" file and displays the past games in the text area.
	 * 
	 * @param none
	 * @return none
	*/
	PastGames() {
		menu = new JFrame();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		
		p = new JPanel();
        p.setLayout(null);
        
        //Back Button
        backButton = new JButton("BACK");
      	backButton.setBounds(15, 15, 160, 75);
		
      	//Title
		label = new JLabel();
		label.setText("PAST GAMES");
		label.setForeground(Color.white);
		label.setFont(new Font("Consolas", Font.PLAIN,60));
		label.setBounds(325, 25, 400, 100);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.TOP);
        
		//Where the text of the past games go
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(Color.black);
        textArea.setForeground(Color.white);
        textArea.setFont(new Font("Consolas", Font.PLAIN,35));
        textArea.setBounds(385, 115, 400, 500);        
        textArea.setVisible(true);
        
        //Using BufferedReader to read the past games from the txt file and storing them into a linked list
        try {
            reader = new BufferedReader(new FileReader("PastGames.txt"));
            lines = new LinkedList<String>();
            //keep reading until there the next line in the txt file is null
            while ((line = reader.readLine()) != null) {
                lines.add(line); //adding each line into the linked list
                if (lines.size() > 10) { //if there are more than 10 lines in the linked list
                    lines.remove(); //use the .remove() which will delete the first in (so that you can another item without going past 10)
                }
            }
            //adding each line into the text area
            for (int i = 0; i < lines.size(); i++) {
                textArea.append(lines.get(i) + "\n");
            }
            reader.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
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
        
        //adding elements to panel and frame
		p.setBackground(Color.black);
		menu.add(textArea, BorderLayout.CENTER);
		p.add(backButton);
		p.add(label);
        menu.add(p);
		menu.setVisible(true);
		menu.setLocationRelativeTo(null);
		menu.setResizable(false);
	}
}
