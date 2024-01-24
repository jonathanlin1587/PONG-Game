/**
 * @author jonathanlin
 * @version 2023-01-25
 * 
 * GameOverHandler class is responsible for handling the game over event.
 * When the game is over, it shows a dialog box to display 
 * the winner and prompts the user to play again or exit the game.
*/

import java.awt.*;
import javax.swing.*;

public class GameOverHandler {
	
	/**
	* handleGameOver method takes a string as
	* a parameter and prompts the user to play 
	* again or exit the game.
	*
	* @param winner
	* @param frame
	* @return none
	*/
    public void handleGameOver(String winner, GameFrame frame) {
        JOptionPane.showMessageDialog(null, "The winner is " + winner); //displays a JOptionPane that shows who the winner is
        int choice = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "New Game", JOptionPane.YES_NO_OPTION); //displays a JOptionPane to ask if they want to play again
        if (choice == JOptionPane.YES_OPTION) { //if YES
        	//start a new game
        	new GameFrame();
        }
        else { //if NO
            //exit the program
        	frame.dispose();
        	new GameMenu();
        }
    }
    

}