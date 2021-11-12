/******
 * RPGUtility
 * Author: Adam Curley
 * 
 * This code contains any methods that can be used in ANY rpg game
 * 
 ******/

import java.util.Scanner;

public class RPGUtility {
	private Utility _util = new Utility();
	private Scanner _scanner = new Scanner(System.in);

	/*
	 * Ask for the player's names, if a symbol is required for the game in question
	 * the method will ask for one, returns the players as an array
	 */
	public String[] AskForPlayerNames(int count, boolean askForSymbol) {
		String[] nameArray = new String[count];

		for (int i = 0; i < nameArray.length; i++) {
			System.out.print("Enter Player " + (i + 1) + " Name: ");
			String name = _util.stringCheck(_scanner, "Enter Player " + (i + 1) + " Name: ");

			nameArray[i] = name;
		}

		return nameArray;
	}
	
	/*
	 * Ask for the dimensions of the gameboard, and create a gameboard, then return it
	 */
	public Gameboard AskForDimensions() {
		System.out.print("How many rows do you want the board to have?: ");
		int rows = _util.intCheck(_scanner, "How many rows do you want the board to have?: ", 20, 3);

		System.out.print("How many columns do you want the board to have?: ");
		int columns = _util.intCheck(_scanner, "How many columns do you want the board to have?: ", 20, 3);

		Gameboard gameboard = new Gameboard(rows, columns);

		return gameboard;
	}
}
