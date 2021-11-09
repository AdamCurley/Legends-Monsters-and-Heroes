/******
 * LegendsMandHUtility
 * Author: Adam Curley
 * 
 * This code contains any methods that can be used in Legends: Monsters and Heroes and subsequent variations
 * 
 ******/

import java.util.Random;
import java.util.Scanner;

public class LegendsMandHUtility extends RPGUtility {
	private Utility _util = new Utility();
	private Scanner _scanner = new Scanner(System.in);
	private Controller _controller = new Controller();
	private Random _r = new Random();
	private Combat _combat;
	private String _choice = "";
	private String _moveCheck = "";

	/*
	 * A (regular) game of Legends
	 * Workflow:
	 * 	1. A game starts with the heroes in on the move
	 *  2. They can choose to either move up, down, left, or right or they can stay at the square they're at (or quit the game)
	 *  3. After the heroes move they have a 30% to begin a fight (if they move to a common square)
	 *  4. If they enter a fight, skip to step 7, if they do not, proceed to step 5
	 *  5. Each player in the team now has their own turn, they can view their inventory and use/change their items, view the map, view their stats of if they're on
	 *  a market square, purchase new items
	 *  6. Once a player has finished their turn they can press 'Y' to proceed to the next player of if they're the last player we return to step 2.
	 *  7. If the users are unlucky enough to enter a fight they will engage in combat and once that is completed we return to step 5.
	 */
	public String LMHGame(Team t, Market market, Gameboard gameboard, Logger logger) {
		t.setInTransit(true);
		System.out.println(_controller.getControls(t));
		System.out.println(ASCIIColor.PURPLE_BACKGROUND + "Welcome to the world!" + ASCIIColor.RESET);

		while (true) {
			t.setInTransit(true);
			gameboard.printGameboard();

			boolean stillMoving = true;
			while (stillMoving) {
				System.out.print(t.getName() + " which direction would you like to move?: ");
				_moveCheck = _controller.movementCheck(_scanner,
						t.getName() + " which direction would you like to move?: ");

				if (_moveCheck.equals("Q")) {
					break;
				} else if (_moveCheck.equals("H")) {
					System.out.println(_controller.getControls(t));
				} else if (_moveCheck.equals("X")) {
					stillMoving = false;
				} else {
					int[] move = _controller.controlChoice(_moveCheck, gameboard, t, logger);
					move = gameboard.fixMove(move[0], move[1]);
					if (t.getGameState().equals("MARKET")) {
						gameboard.getGameboard()[move[0]][move[1]].setMarker(
								new Marker(ASCIIColor.RED_BACKGROUND + t.getSymbol().getSymbol() + ASCIIColor.RESET));
					} else {
						gameboard.getGameboard()[move[0]][move[1]].setMarker(t.getSymbol());
						int rollDice = _r.nextInt(99);
						if (rollDice > 69) {
							_combat = new Combat(t);
							_combat.combat();
						}
					}

					stillMoving = false;
				}
			}
			t.setInTransit(false);
			if (_moveCheck.equals("Q")) {
				break;
			}

			gameboard.printGameboard();

			for (Player p : t.getTeam()) {
				boolean stillThinking = true;
				while (stillThinking) {

					System.out.print(p.getName() + " what would you like to do?: ");
					if (t.getGameState().equals("MARKET")) {
						_choice = _controller.controlCheck(_scanner, "What would you like to do?: ", true);
					} else {
						_choice = _controller.controlCheck(_scanner, "What would you like to do?: ", false);
					}

					if (_choice.equals("M")) {
						gameboard.printGameboard();
					} else if (_choice.equals("O")) {
						p.getInventory();
						if (p.getWeaponCount() >= 2) {
							System.out.print("Would you like to change weapons?: ");
							String choice = _util.yesNoCheck(_scanner, "Would you like to change weapons?: ");
							if (choice.equals("Y")) {
								int count = 0;
								for (Weapon w : p.getWeapons()) {
									System.out.println(count + ". " + w.getName());
									count++;
									w.setEquipped(false);
								}
								System.out.print("Which weapon will you Equip?: ");
								int weaponChoice = _util.intCheck(_scanner, "Which weapon will you Equip?: ", count, 0);

								p.getWeapons().get(weaponChoice).setEquipped(true);
							}
						}
						if (p.getArmorCount() >= 2) {
							System.out.print("Would you like to change armor?: ");
							String choice = _util.yesNoCheck(_scanner, "Would you like to change armor?: ");
							if (choice.equals("Y")) {
								int count = 0;
								for (Armor a : p.getArmors()) {
									System.out.println(count + ". " + a.getName());
									count++;
									a.setEquipped(false);
								}
								System.out.print("Which armor will you Equip?: ");
								int armorChoice = _util.intCheck(_scanner, "Which weapon will you Equip?: ", count, 0);

								p.getArmors().get(armorChoice).setEquipped(true);
							}
						}
						if (!p.getPotions().isEmpty()) {
							System.out.print("Would you like to use a potion?: ");
							String choice = _util.yesNoCheck(_scanner, "Would you like to use a potion?: ");
							if (choice.equals("Y")) {
								int count = 0;
								for (Potion o : p.getPotions()) {
									System.out.println(count + ". " + o.getName());
									count++;
								}
								System.out.print("Which potion will you use?: ");
								int potionChoice = _util.intCheck(_scanner, "Which potion will you use?: ", count, 0);
								Potion targetPotion = p.getPotions().get(potionChoice);

								for (String attribute : targetPotion.getAttributeAffected()) {
									if (attribute.equals("Health")) {
										p.increaseHealth(targetPotion.getAttributeIncrease());
										System.out.println(ASCIIColor.GREEN_BOLD + p.getName() + ASCIIColor.RESET
												+ "'s health increases by " + targetPotion.getAttributeIncrease());
										System.out.println(p.getName() + " current health: " + p.getHealth());
									} else if (attribute.equals("Mana")) {
										p.increaseMana(targetPotion.getAttributeIncrease());
										System.out.println(ASCIIColor.GREEN_BOLD + p.getName() + ASCIIColor.RESET
												+ "'s mana increases by " + targetPotion.getAttributeIncrease());
									} else if (attribute.equals("Strength")) {
										p.increaseStrength(targetPotion.getAttributeIncrease());
										System.out.println(ASCIIColor.GREEN_BOLD + p.getName() + ASCIIColor.RESET
												+ "'s strength increases by " + targetPotion.getAttributeIncrease());
									} else if (attribute.equals("Agility")) {
										p.increaseAgility(targetPotion.getAttributeIncrease());
										System.out.println(ASCIIColor.GREEN_BOLD + p.getName() + ASCIIColor.RESET
												+ "'s agility increases by " + targetPotion.getAttributeIncrease());
									} else if (attribute.equals("Dexterity")) {
										p.increaseDexterity(targetPotion.getAttributeIncrease());
										System.out.println(ASCIIColor.GREEN_BOLD + p.getName() + ASCIIColor.RESET
												+ "'s dexterity increases by " + targetPotion.getAttributeIncrease());
									}
								}

								p.getPotions().remove(potionChoice);
							}
						}
					} else if (_choice.equals("E")) {
						market.enterMarket(p);
					} else if (_choice.equals("I")) {
						System.out.println(_controller.getInformation(t, p, null));
					} else if (_choice.equals("H")) {
						System.out.println(_controller.getControls(t));
					} else if (_choice.equals("Y")) {
						stillThinking = false;
					}
				}
			}
		}
		return ASCIIColor.RED_BACKGROUND + "GAME OVER!" + ASCIIColor.RESET;
	}

	/*
	 * Checks to make sure scanner input is a proper class
	 */
	public String classCheck(Scanner scanner, String message) {
		String string;

		do {
			while (!scanner.hasNext()) {
				System.out.print(message);
				scanner.next();
			}
			string = scanner.next();
		} while (!string.toUpperCase().contains("PALADIN") && !string.toUpperCase().contains("WARRIOR")
				&& !string.toUpperCase().contains("SORCERER"));

		return string.toUpperCase();
	}

	/*
	 * Gather the options for the player's base character based on their type of character selected
	 */
	public String characterSelect(String type) {
		String s;

		if (type.equals("PALADIN")) {
			s = ASCIIColor.CYAN_BACKGROUND + "Name/mana/strength/agility/dexterity/starting money/starting experience"
					+ ASCIIColor.RESET + "\n" + "1. Parzival             300     750     650     700     2500    7\n"
					+ "2. Sehanine_Moonbow     300     750     700     700     2500    7\n"
					+ "3. Skoraeus_Stonebones  250     650     600     350     2500    4\n"
					+ "4. Garl_Glittergold     100     600     500     400     2500    5\n"
					+ "5. Amaryllis_Astra      500     500     500     500     2500    5\n"
					+ "6. Caliber_Heist        400     400     400     400     2500    8";
			return s;

		} else if (type.equals("SORCERER")) {
			s = ASCIIColor.CYAN_BACKGROUND + "Name/mana/strength/agility/dexterity/starting money/starting experience"
					+ ASCIIColor.RESET + "\n" + "1. Rillifane_Rallathil     1300    750     450     500     2500    9\n"
					+ "2. Segojan_Earthcaller     900     800     500     650     2500    5\n"
					+ "3. Reign_Havoc             800     800     800     800     2500    8\n"
					+ "4. Reverie_Ashels          900     800     700     400     2500    7\n"
					+ "5. Kalabar                 800     850     400     600     2500    6\n"
					+ "6. Skye_Soar               1000    700     400     500     2500    5";
			return s;
		} else if (type.equals("WARRIOR")) {
			s = ASCIIColor.CYAN_BACKGROUND + "Name/mana/strength/agility/dexterity/starting money/starting experience"
					+ ASCIIColor.RESET + "\n" + "1. Gaerdal_Ironhand    100     700     500     600     1354    7\n"
					+ "2. Sehanine_Monnbow    600     700     800     500     2500    8\n"
					+ "3. Muamman_Duathall    300     900     500     750     2546    6\n"
					+ "4. Flandal_Steelskin   200     750     650     700     2500    7\n"
					+ "5. Undefeated_Yoj      400     800     400     700     2500    7\n"
					+ "6. Eunoia_Cyn          400     700     800     600     2500    6";
			return s;
		}

		return null;
	}
	
	/*
	 * Create a team of heroes for a game of Legends
	 */
	public Team CreateTeam(String[] names) {

		System.out.print("What is your team name?: ");
		String teamName = _util.stringCheck(_scanner, "What is your team name?: ");

		Team team = new Team(teamName, names.length);

		for (int i = 0; i < names.length; i++) {

			System.out.println(ASCIIColor.CYAN_BACKGROUND + "Classes:" + ASCIIColor.RESET + "\n" + "PALADIN\n"
					+ "WARRIOR\n" + "SORCERER");

			System.out.print("Enter " + names[i] + "'s Class: ");
			String type = classCheck(_scanner, "Enter " + names[i] + "'s Class: ");

			System.out.println(characterSelect(type));

			System.out.print("Enter " + names[i] + "'s Base Character (enter number): ");
			int character = _util.intCheck(_scanner, "Enter " + names[i] + "'s Base Character (enter number): ", 6, 1);

			if (type.equals("PALADIN")) {
				Paladin paladin = new Paladin(names[i], type, character);
				team.addMember(paladin, i);
			} else if (type.equals("SORCERER")) {
				Sorcerer sorcerer = new Sorcerer(names[i], type, character);
				team.addMember(sorcerer, i);
			} else if (type.equals("WARRIOR")) {
				Warrior warrior = new Warrior(names[i], type, character);
				team.addMember(warrior, i);
			}
		}

		return team;
	}
}
