/******
 * Market
 * Author: Adam Curley
 * 
 * A type of gameboard square that can be used to purchase items for their inventory
 * 
 ******/

import java.util.Scanner;

public class Market {
	private Marker _symbol;
	private Scanner _scanner = new Scanner(System.in);
	private Utility _util = new Utility();

	
	public Market() {
		_symbol = new Marker(ASCIIColor.RED_BACKGROUND + " " + ASCIIColor.RESET);
	}
	
	public Marker getMarketMarker() {
		return _symbol;
	}
	
	public void setMarketMarker(Marker symbol) {
		symbol.setMarker(symbol.getSymbol());
		_symbol = symbol;
	}
	
	/*
	 * This the market menu that allows the player to buy various types of weapons, armor, spells, and potions
	 * They also are able to sell their items for 1/2 the price they purchased them for
	 */
	public void enterMarket(Player p) {
		System.out.println(ASCIIColor.PURPLE_BACKGROUND + "Welcome to the Market!" + ASCIIColor.RESET);
		while (true) {
			System.out.println("0. Exit Market\n" + "1. Buy Weapons\n" + "2. Buy Armor\n" + "3. Buy Potions\n"
					+ "4. Buy Spells\n" + "5. Sell");
			System.out.print("What would you like to do? (Please input corresponding number): ");
			int choice = _util.intCheck(_scanner, "What would you like to do? (Please input corresponding number): ", 5,
					0);

			if (choice == 0) {
				break;
			} else if (choice == 1) {
				System.out.println("Welcome to the Weapons Department!");
				System.out.println("Here is the menu:\n" + "Name/cost/level/damage/required hands\n"
						+ "1.Sword           500     1    800    1\n" + "2.Bow             300     2    500    2\n"
						+ "3.Scythe          1000    6    1100   2\n" + "4.Axe             550     5    850    1\n"
						+ "5.TSwords     	  1400    8    1600   2\n" + "6.Dagger          200     1    250    1");
				System.out.print("What would you like to buy?: ");
				int weaponChoice = _util.intCheck(_scanner, "What would you like to buy?: ", 6, 1);
				Weapon weapon = new Weapon(weaponChoice);
				if (p.getLevel() >= weapon.getRequiredLevel()) {
					p.subtractMoney(weapon.getCost());

					if (p.getMoney() >= 0) {
						if (p.getWeapons().isEmpty()) {
							weapon.setEquipped(true);
						}
						p.addWeapon(weapon);
					} else {
						p.addMoney(weapon.getCost());
						System.out.println("You don't have enough money for this item!");
					}
				} else {
					System.out.println("You are not a high enough level to purchase this item!");
				}
			} else if (choice == 2) {
				System.out.println("Welcome to the Armor Department!");
				System.out.println("Here is the menu\n:" + "Name/cost/required level/damage reduction\n"
						+ "1. Platinum_Shield       150   1   200\n" + "2. Breastplate           350   3   600\n"
						+ "3. Full_Body_Armor       1000  8   1100\n" + "4. Wizard_Shield         1200  10  1500\n"
						+ "5. Guardian_Angel        1000  10  1000");
				System.out.print("What would you like to buy?: ");
				int armorChoice = _util.intCheck(_scanner, "What would you like to buy?: ", 5, 1);
				Armor armor = new Armor(armorChoice);
				if (p.getLevel() >= armor.getRequiredLevel()) {
					p.subtractMoney(armor.getCost());

					if (p.getMoney() >= 0) {
						if (p.getArmors().isEmpty()) {
							armor.setEquipped(true);
						}
						p.addArmor(armor);
					} else {
						p.addMoney(armor.getCost());
						System.out.println("You don't have enough money for this item!");
					}
				} else {
					System.out.println("You are not a high enough level to purchase this item!");
				}
			} else if (choice == 3) {
				System.out.println("Welcome to the Potions Department!");
				System.out.println("Here is the menu:\n"
						+ "Name/cost/required level/attribute increase/attribute affected\n"
						+ "1. Healing_Potion  250     1   100		Health\n"
						+ "2. Strength_Potion 200     1   75		Strength\n"
						+ "3. Magic_Potion    350     2   100		Mana\n"
						+ "4. Luck_Elixir     500     4   65  	Agility\n"
						+ "5. Mermaid_Tears   850     5   100  	Health/Mana/Strength/Agility\n"
						+ "6. Ambrosia        1000    8   150		All Health/Mana/Strength/Dexterity/Defense/Agility");
				System.out.print("What would you like to buy?: ");
				int potionChoice = _util.intCheck(_scanner, "What would you like to buy?: ", 6, 1);
				Potion potion = new Potion(potionChoice);
				if (p.getLevel() >= potion.getRequiredLevel()) {
					p.subtractMoney(potion.getCost());

					if (p.getMoney() >= 0) {
						p.addPotion(potion);
					} else {
						p.addMoney(potion.getCost());
						System.out.println("You don't have enough money for this item!");
					}
				} else {
					System.out.println("You are not a high enough level to purchase this item!");
				}
			} else if (choice == 4) {
				System.out.println("FIRE\n" + "ICE\n" + "LIGHTNING");
				System.out.print("What would you like to buy?: ");
				String typeChoice = spellCheck(_scanner, "What would you like to buy?: ");
				System.out.println(spellSelect(typeChoice));

				System.out.print("What would you like to buy?: ");
				int spellChoice = _util.intCheck(_scanner, "What would you like to buy?: ", 5, 1);

				Spell spell = new Spell(typeChoice, spellChoice);

				p.subtractMoney(spell.getCost());
				if (p.getLevel() >= spell.getRequiredLevel()) {
					if (p.getMoney() >= 0) {
						p.addSpell(spell);
					} else {
						p.addMoney(spell.getCost());
						System.out.println("You don't have enough money for this item!");
					}
				} else {
					System.out.println("You are not a high enough level to purchase this item!");
				}
			} else if (choice == 5) {
				boolean stillSelling = true;
				while (stillSelling) {
					System.out.println("0. Exit Sell\n" + "1. Sell Weapons\n" + "2. Sell Armor\n" + "3. Sell Potions\n"
							+ "4. Sell Spells");
					System.out.print("What would you like to sell? (Please input corresponding number): ");
					int sChoice = _util.intCheck(_scanner,
							"What would you like to sell? (Please input corresponding number): ", 4, 0);
					
					if (sChoice == 0) {
						stillSelling = false;
					}
					else if (sChoice == 1) {
						int count = 0;
						System.out.println("0. Cancel");
						for (Weapon w : p.getWeapons()) {
							System.out.println((count + 1) + ". " + w.getName());
							count++;
						}
						if (count == 0) {
							System.out.println(ASCIIColor.RED_BACKGROUND + "You have no weapons to sell!" + ASCIIColor.RESET);
						} else {
							System.out.print("What would you like to sell?: ");
							int sellChoice = _util.intCheck(_scanner, "What would you like to sell?: ", count, 0);
							
							if (sellChoice > 0) {
								Weapon w = p.getWeapons().get(sellChoice - 1);
								p.addMoney(w.getCost() / 2);
								p.removeWeapon(w);
							}
						}
					} else if (sChoice == 2) {
						int count = 0;
						System.out.println("0. Cancel");
						for (Armor a : p.getArmors()) {
							System.out.println((count + 1) + ". " + a.getName());
							count++;
						}
						if (count == 0) {
							System.out.println("You have no armor to sell!");
						} else {
							System.out.print("What would you like to sell?: ");
							int sellChoice = _util.intCheck(_scanner, "What would you like to sell?: ", count, 0);
							
							if (sellChoice > 0) {
								Armor a = p.getArmors().get(sellChoice - 1);
								p.addMoney(a.getCost() / 2);
								p.removeArmor(a);
							}
						}
					} else if (sChoice == 3) {
						int count = 0;
						System.out.println("0. Cancel");
						for (Potion o : p.getPotions()) {
							System.out.println((count + 1) + ". " + o.getName());
							count++;
						}
						if (count == 0) {
							System.out.println("You have no potions to sell!");
						} else {
							System.out.print("What would you like to sell?: ");
							int sellChoice = _util.intCheck(_scanner, "What would you like to sell?: ", count, 0);

							if (sellChoice > 0) {
								Potion o = p.getPotions().get(sellChoice - 1);
								p.addMoney(o.getCost() / 2);
								p.removePotion(o);
							}
						}
					} else if (sChoice == 4) {
						System.out.println("0. Cancel");
						int count = 0;
						for (Spell s : p.getSpells()) {
							System.out.println((count + 1) + ". " + s.getName());
							count++;
						}
						if (count == 0) {
							System.out.println("You have no spells to sell!");
						} else {
							System.out.print("What would you like to sell?: ");
							int sellChoice = _util.intCheck(_scanner, "What would you like to sell?: ", count, 0);

							if (sellChoice > 0) {
								Spell s = p.getSpells().get(sellChoice - 1);
								p.addMoney(s.getCost() / 2);
								p.removeSpell(s);
							}
						}
					}
				}
			}
		}
	}

	public String spellCheck(Scanner scanner, String message) {
		String string;

		do {
			while (!scanner.hasNext()) {
				System.out.print(message);
				scanner.next();
			}
			string = scanner.next();
		} while (!string.toUpperCase().contains("FIRE") && !string.toUpperCase().contains("ICE")
				&& !string.toUpperCase().contains("LIGHTNING"));

		return string.toUpperCase();
	}

	public String spellSelect(String type) {
		String s = "";

		if (type.equals("FIRE")) {
			s = "Name/cost/required level/damage/mana cost\n" + "1. Flame_Tornado   700     4   850     300\n"
					+ "2. Breath_of_Fire  350     1   450     100\n" + "3. Heat_Wave       450     2   600     150\n"
					+ "4. Lava_Comet      800     7   1000    550\n" + "5. Hell_Storm      600     3   950     600";
		} else if (type.equals("ICE")) {
			s = "Name/cost/required level/damage/mana cost\n" + "1. Snow_Cannon     500     2   650     250\n"
					+ "2. Ice_Blade       250     1   450     100\n" + "3. Frost_Blizzard  750     5   850     350\n"
					+ "4. Arctic_Storm    700     6   800     300";
		} else if (type.equals("LIGHTNING")) {
			s = "Name/cost/required level/damage/mana cost\n"
					+ "1. Lightning_Dagger      400        1       500     150\n"
					+ "2. Thunder_Blast         750        4       950     400\n"
					+ "3. Electric_Arrows       550        5       650     200\n"
					+ "4. Spark_Needles         500        2       600     200";
		}
		return s;
	}
}
