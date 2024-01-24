/**
 * @author jonathanlin
 * @version 2023-01-25
 * 
 * GameFrame class is responsible for creating 
 * the main frame of the game, it creates an 
 * instance of the GamePanel and Pong objects, 
 * and sets up the frame properties.
*/

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame implements ActionListener {
	//Declaring variables
	Pong pong;
	GamePanel panel;
	
	/**
	* GameFrame class creates the main frame of the game,
	* it creates an instance of the GamePanel and Pong 
	* objects, and sets up the frame properties.
	* 
	* @param none
	* @return none
	*/
	GameFrame() {
		//Initializing variables
		pong = new Pong();
		panel = new GamePanel(this);
		
		//FRAME
		this.add(panel);
		this.setTitle("PONG");
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	/**
	* An override method of actionPerformed in the ActionListener Interface.
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
