/******
 * RPGManager
 * Author: Adam Curley
 * 
 * This code allows users to play rpg games of their choosing
 * 
 * Currently implemented:
 * Legends: Monsters and Heroes
 * 
 ******/

import java.util.Scanner;

public class RPGManager {
	private Scanner _scanner = new Scanner(System.in);
	private int _selectedGame = 0;
	private Utility _util = new Utility();
	
	/*
	 * Welcome users to the program, present them with the list of rpg games and
	 * ask which one they would like to play Based on user input, the game starts.
	 * Once the players exit the game, they return here until they press 0 to exit
	 * the program
	 */
	public void run() {
		while (true) {
			System.out.println("Welcome to RPG Manager!");
			System.out.println("Here is a list of available games:");
			System.out.println("0. Exit");
			System.out.println("1. Legends: Monsters and Heroes");
			System.out.print("What game would you like to play? (Please input corresponding number): ");

			_selectedGame = _util.intCheck(_scanner, "What game would you like to play? (Please input corresponding number): ", 1, 0);

			switch (_selectedGame) {
			case 0:
				System.out.println("EXITING");
				System.exit(0);
			case 1:
				LegendsMandH _legends = new LegendsMandH();
				_legends.Game();
				break;
			default:
				System.out.println("Invalid input!");
				break;
			}
		}
	}
}
