/**
 * @author jonathanlin
 * @version 2023-01-25
 * 
 * Pong Class is Main Class for the Pong game.
 * It contains the main method that calls the TitlePage panel
 */

public class Pong {
	//variables for the entire project
	public String player1Username; //stores the username for player 1
	public String player2Username; //stores the username for player 2
	public String maxScore; //the max score the game goes up to, chosen by the player
	
	/**
	 * The main method starts the code by calling the TitlePage panel
	 * 
	 * @param args The arguments passed to the main method
	 * @return none
	 */
	public static void main(String[] args) {
		//starting the code by calling the TitlePage panel
		new TitlePage();
	}

}
