/**
 * @author jonathanlin
 * @version 2023-01-25
 * 
 * Score class keeps track of the scores of the 
 * players and determines if the game is over or not.
 * It also includes a method to increment the score of player 1 or player 2.
 * It also includes methods to read and write player names and scores to a file.
*/

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Score extends Rectangle {
	static int SCREEN_WIDTH;
	static int SCREEN_HEIGHT;
	String max_score;
	int player1Score = 0; //player 1 score
	int player2Score = 0; //player 2 score
	boolean gameOver;
	private String winner;
	String player1Name = "";
	String player2Name = "";
	File file;
	
	/**
	* Constructor for the Score class
	* 
	* @param SCREEN_WIDTH 		the width of the screen
	* @param SCREEN_HEIGHT 		the height of the screen
	* @return none
	*/
	Score(int SCREEN_WIDTH, int SCREEN_HEIGHT) {
		Score.SCREEN_WIDTH = SCREEN_WIDTH;
		Score.SCREEN_HEIGHT = SCREEN_HEIGHT;
		gameOver = false;
		file = new File("PlayerNames.txt");
		
		try {
			BufferedReader in = new BufferedReader(new FileReader("PlayerNames.txt"));
			player1Name = in.readLine();
			player2Name = in.readLine();
			max_score = in.readLine();
		}
		catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
	}
	/**
	* draw is a method that draws the score onto the panel
	*
	* @param g
	* @return none
	*/
	public void draw (Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Consolas", Font.PLAIN, 60));
		
		g.drawLine(SCREEN_WIDTH/2, 0, SCREEN_WIDTH/2, SCREEN_HEIGHT);

		g.drawString(String.valueOf(player1Score/10) + String.valueOf(player1Score%10), (SCREEN_WIDTH/2) - 85, 50);
		g.drawString(String.valueOf(player2Score/10) + String.valueOf(player2Score%10), (SCREEN_WIDTH/2) + 20, 50);
	}
	
	/**
	* incrementPlayer1 method increments player 1's score if the game isn't over
	* if the game is over, this method sets the winner to player 1
	*
	* @param none
	* @return none
	*/
	public void incrementPlayer1() {
        if(!gameOver) {
			if (player1Score < Integer.valueOf(max_score)-1) {
	            player1Score++;
	        }
	        else {
	        	player1Score++;
	            gameOver = true;
	            winner = player1Name;
	        }
        }
	}
	
	/**
	* incrementPlayer2 method increments player 2's score if the game isn't over
	* if the game is over, this method sets the winner to player 2
	*
	* @param none
	* @return none
	*/
	public void incrementPlayer2() {
		if(!gameOver) {
	        if (player2Score < Integer.valueOf(max_score)-1) {
	            player2Score++;
	        }
	        else {
	        	player2Score++;
	            gameOver = true;
	            winner = player2Name;
	        }
		}
	}
	/**
	* isGameOver method checks if the game is over.
	* It also prints the scores into a txt file
	* 
	* @param none
	* @return gameOver
	*/
	public boolean isGameOver() {
		if (gameOver) {
	        try {
	            FileWriter fw = new FileWriter("PastGames.txt", true);
	            fw.write(player1Name + ": " + player1Score + " " + player2Name + ": " + player2Score + "\n");
	            fw.close();
	        } 
	        catch (IOException e) {
	            System.out.println("An error occurred while writing to the file.");
	            e.printStackTrace();
	        }     
	    }
	    return gameOver;
    }
	
	/**
	* getWinner method is a getter method for the variable winner
	*
	* @param none
	* @return winer
	*/
    public String getWinner() {
        return winner;
    }
    
    /**
	* getPlayer1Name method is a getter method for the variable player1Name
	*
	* @param none
	* @return player1Name
	*/
	public String getPlayer1Name() {
		return player1Name;
	}

	/**
	* setPlayer1Name method is a setter method for the variable player1Name
	*
	* @param player1Name
	* @return none
	*/
	public void setPlayer1Name(String player1Name) {
		this.player1Name = player1Name;
	}

	/**
	* getPlayer2Name method is a getter method for the variable player2Name
	*
	* @param none
	* @return player2Name
	*/
	public String getPlayer2Name() {
		return player2Name;
	}
	
	/**
	* setPlayer2Name method is a setter method for the variable player2Name
	*
	* @param player2Name
	* @return none
	*/
	public void setPlayer2Name(String player2Name) {
		this.player2Name = player2Name;
	}
    
}
