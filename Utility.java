/******
 * Utility
 * Author: Adam Curley
 * 
 * This code contains any methods that can be used in games for checking input or logging information about the games
 * 
 ******/

import java.util.Scanner;

public class Utility {

	/*
	 * Checks to make sure scanner input is an integer
	 */
	public int intCheck(Scanner scanner, String message, int max, int min) {
		int number;

		do {
			while (!scanner.hasNextInt()) {
				System.out.print(message);
				scanner.next();
			}
			number = scanner.nextInt();
		} while (number < min || number > max);

		return number;
	}

	/*
	 * Checks to make sure scanner input is a Y or N
	 */
	public String yesNoCheck(Scanner scanner, String message) {
		String string;

		do {
			while (!scanner.hasNext()) {
				System.out.print(message);
				scanner.next();
			}
			string = scanner.next();
		} while (!string.toUpperCase().contains("Y") && !string.toUpperCase().contains("N"));

		return string.toUpperCase();
	}

	/*
	 * Checks to make sure scanner input is a string
	 */
	public String stringCheck(Scanner scanner, String message) {
		String string;

		do {
			while (!scanner.hasNext()) {
				System.out.print(message);
				scanner.next();
			}
			string = scanner.next();
		} while (string == null);

		string.replaceAll("[^a-zA-Z0-9]", "");
		return string;
	}

	/*
	 * Checks to make sure scanner input is a single digit string
	 */
	public Marker symbolCheck(Scanner scanner, String message) {
		String string;

		do {
			while (!scanner.hasNext()) {
				System.out.print(message);
				scanner.next();
			}
			string = scanner.next();
		} while (string.length() != 1);
		
		Marker marker = new Marker(string);
		return marker;
	}
}