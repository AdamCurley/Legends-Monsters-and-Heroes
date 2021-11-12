
/******
 * Controller
 * Author: Adam Curley
 * 
 * This is the controller that interprets the user's inputs on the gameboard
 * 
 ******/

import java.util.Scanner;

public class Controller {
	private int[] _move = new int[2];
	private String _information;

	/*
	 * Based on if the heroes are moving or on a market or common square, inform
	 * them which controls are available
	 */
	public String getControls(Player p) {
		String s;
		if (p.getGameState().equals("MARKET")) {
			s = "W/w: move up\n" + "A/a: move left\n" + "S/s: move down\n" + "D/d: move right\n"
					+ "O/o: open inventory\n" + "Y/y: end turn \n" + "I/i: show information\n" + "M/m: display map\n"
					+ "E/e: enter market\n" + "H/h: help";
		}
		else {
			s = "W/w: move up\n" + "A/a: move left\n" + "S/s: move down\n" + "D/d: move right\n"
					+ "O/o: open inventory\n" + "Y/y: end turn \n" + "I/i: show information\n" + "M/m: display map\n"
					+ "H/h: help";
		}

		return s;
	}

	/*
	 * If the heroes have decided to move, interpret W, A, S, or D as moving Up,
	 * Left, Right, or Down on the board respectively
	 */
	public int[] controlChoice(String choice, Gameboard gameboard, Player p, Logger logger) {
		int[] position = new int[2];
		position[0] = p.getRow();
		position[1] = p.getColumn();
		_move = gameboard.fixMove(position[0], position[1]);
		String originalGameState = p.getGameState();
		if (p.getGameState().equals("MARKET")) {
			gameboard.getGameboard()[_move[0]][_move[1]]
					.setMarker(new Marker(ASCIIColor.RED_BACKGROUND + "  " + ASCIIColor.RESET));
			p.setGameState("COMMON SQUARE");
		} else {
			gameboard.getGameboard()[_move[0]][_move[1]].setMarker(new Marker("  "));
		}

		logger.removeMove(position);

		switch (choice) {
		case "W":
			if (position[0] - 1 != 0) {
				p.setRow(position[0] - 1);
				_move[0] = p.getRow();
				_move[1] = p.getColumn();
				if (logger.isMoveMade(_move) == false) {
					if (logger.isMarket(_move)) {
						p.setGameState("MARKET");
					}
					logger.addMove(_move);
					return _move;
				} else {
					p.setRow(position[0]);
					p.setColumn(position[1]);
				}
			}
			break;
		case "A":
			if (position[1] - 1 != 0) {
				p.setColumn(position[1] - 1);
				_move[0] = p.getRow();
				_move[1] = p.getColumn();
				if (logger.isMoveMade(_move) == false) {
					if (logger.isMarket(_move)) {
						p.setGameState("MARKET");
					}
					logger.addMove(_move);
					return _move;
				} else {
					p.setRow(position[0]);
					p.setColumn(position[1]);
				}
			}
			break;
		case "S":
			if (gameboard.getRows() >= position[0] + 1) {
				p.setRow(position[0] + 1);
				_move[0] = p.getRow();
				_move[1] = p.getColumn();
				if (logger.isMoveMade(_move) == false) {
					if (logger.isMarket(_move)) {
						p.setGameState("MARKET");
					}
					logger.addMove(_move);
					return _move;
				} else {
					p.setRow(position[0]);
					p.setColumn(position[1]);
				}
			}
			break;
		case "D":
			if (gameboard.getColumns() >= position[1] + 1) {
				p.setColumn(position[1] + 1);
				_move[0] = p.getRow();
				_move[1] = p.getColumn();
				if (logger.isMoveMade(_move) == false) {
					if (logger.isMarket(_move)) {
						p.setGameState("MARKET");
					}
					logger.addMove(_move);
					return _move;
				} else {
					p.setRow(position[0]);
					p.setColumn(position[1]);
				}
			}
			break;
		}

		_move = gameboard.fixMove(position[0], position[1]);
		if (originalGameState.equals("MARKET")) {
			p.setGameState("MARKET");
		}
		logger.addMove(position);
		return position;
	}

	/*
	 * Gather the hero's information including their skills and stats
	 */
	public String getInformation(Player p, Monster m) {
		if (p.getGameState().equals("COMMON SQUARE") || p.getGameState().equals("MARKET")) {
			_information = "Name: " + p.getName() + "\n" + "Class: " + p.getType() + "\n" + "Level: " + p.getLevel()
					+ "\n" + "Health: " + p.getHealth() + "\n" + "Mana: " + p.getMana() + "\n" + "Strength: "
					+ p.getStrength() + "\n" + "Agility: " + p.getAgility() + "\n" + "Dexterity: " + p.getDexterity()
					+ "\n" + "Money: " + "$" + p.getMoney() + "\n" + "Exp: " + p.getExp();
		} else if (p.getGameState().equals("FIGHT")) {
			_information = "Name: " + p.getName() + "\n" + "Class: " + p.getType() + "\n" + "Level: " + p.getLevel()
					+ "\n" + "Health: " + p.getHealth() + "\n" + "Mana: " + p.getMana() + "\n" + "\n" + "MONSTER:"
					+ m.getName() + "\n" + "Health: " + m.getHealth() + "\n" + "Defense: " + m.getDefense() + "\n"
					+ "Damage: " + m.getDamage();
		}

		return _information;
	}

	/*
	 * Checks to make sure scanner input is one of the control options
	 */
	public String controlCheck(Scanner scanner, String message, boolean marketAvailable) {
		String string;

		if (marketAvailable) {
			do {
				while (!scanner.hasNext()) {
					System.out.print(message);
					scanner.next();
				}
				string = scanner.next();
			} while (!string.toUpperCase().contains("H") && !string.toUpperCase().contains("I")
					&& !string.toUpperCase().contains("O") && !string.toUpperCase().contains("M")
					&& !string.toUpperCase().contains("Y") && !string.toUpperCase().contains("E")
					&& !string.toUpperCase().contains("W") && !string.toUpperCase().contains("A")
					&& !string.toUpperCase().contains("S") && !string.toUpperCase().contains("D"));
		} else {
			do {
				while (!scanner.hasNext()) {
					System.out.print(message);
					scanner.next();
				}
				string = scanner.next();
			} while (!string.toUpperCase().contains("H") && !string.toUpperCase().contains("I")
					&& !string.toUpperCase().contains("O") && !string.toUpperCase().contains("M")
					&& !string.toUpperCase().contains("Y") && !string.toUpperCase().contains("W")
					&& !string.toUpperCase().contains("A") && !string.toUpperCase().contains("S")
					&& !string.toUpperCase().contains("D"));
		}

		return string.toUpperCase();
	}
}
