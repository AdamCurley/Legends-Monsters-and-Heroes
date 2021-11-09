/******
 * Combat
 * Author: Adam Curley
 * 
 * This contains all of the logic used for the combat in the game contained in a single method called "combat()"
 * that gets called whenever a fight begins
 * 
 ******/

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Combat {
	private Random _r = new Random();
	private Utility _util = new Utility();
	private Team _heroes;
	private MonsterTeam _monsters;
	private int _maxLevel = 0;
	private Dragon _dragon;
	private Exoskeleton _exoskeleton;
	private Spirit _spirit;
	private String _winningTeam;
	private int _heroCount;
	private int _monsterCount;
	private Scanner _scanner = new Scanner(System.in);

	/*
	 * When a fight begins, generate a group of monsters to battle the heroes
	 * this team of monsters is randomly generated, but are only as strong as
	 * the most powerful hero.
	 */
	public Combat(Team heroes) {
		_heroes = heroes;
		for (Player p : _heroes.getTeam()) {
			if (p.getLevel() > _maxLevel) {
				_maxLevel = p.getLevel();
			}
		}

		_monsters = new MonsterTeam(_heroes.getPlayerCount());
		for (int i = 0; i < _heroes.getPlayerCount(); i++) {
			int roll = _r.nextInt(99);

			if (roll >= 0 && roll < 33) {
				boolean satisfactory = false;
				while (!satisfactory) {
					_dragon = new Dragon("DRAGON", (_r.nextInt(11)) + 1);
					_dragon.resetInformation();
					if (_dragon.getLevel() <= _maxLevel) {
						satisfactory = true;
					}
				}

				_monsters.addMember(_dragon, i);
			} else if (roll >= 33 && roll < 66) {
				boolean satisfactory = false;
				while (!satisfactory) {
					_exoskeleton = new Exoskeleton("EXOSKELETON", (_r.nextInt(11)) + 1);
					_exoskeleton.resetInformation();
					if (_exoskeleton.getLevel() <= _maxLevel) {
						satisfactory = true;
					}
				}

				_monsters.addMember(_exoskeleton, i);
			} else if (roll >= 66 && roll <= 99) {
				boolean satisfactory = false;
				while (!satisfactory) {
					_spirit = new Spirit("SPIRIT", (_r.nextInt(10)) + 1);
					_spirit.resetInformation();
					if (_spirit.getLevel() <= _maxLevel) {
						satisfactory = true;
					}
				}

				_monsters.addMember(_spirit, i);
			}
		}
	}

	/*
	 * Workflow:
	 * 	1. Inform the heroes that a monster(s) have appeared
	 *  2. Show the stats and skills of the monsters and heroes
	 *  3. For each living hero they can either:
	 *  	A. Attack
	 *  	B. Cast a Spell
	 *		C. Drink a Potion
	 *		D. Change their weapons/Armor
	 *	4. Each action either results in the monsters losing health or the heroes increasing their stats/skills
	 *  5. After each hero has performed an action, each living monster randomly attacks one of the living heroes
	 *  6. If either a monster or hero dies/faints they are "removed" from the fight
	 *  7. At the end of each round there's a check for how many heroes and monsters are living
	 *  	7a. If there are any living heroes or monsters, return to step 2
	 *  	7b. If one team has been completely eliminated, move to step 8
	 *  8. Based on which team is victorious, either:
	 *  	8a. Reward all living heroes 2 exp for each hero defeated and $100 * the level of each monster and revive all fainted heroes to 1/2 their maximum HP
	 *  	8b. Revive all fainted heroes to 1/3 their max HP and take half of their money
	 *  9. Return to the gameboard
	 */
	public void combat() {
		if (_heroes.getPlayerCount() == 1) {
			System.out.println(ASCIIColor.RED_BACKGROUND + "A Monster Has Appeared!" + ASCIIColor.RESET);
		} else {
			System.out.println(ASCIIColor.RED_BACKGROUND + "Monsters Have Appeared!" + ASCIIColor.RESET);
		}

		while (true) {
			for (Monster m : _monsters.getTeam()) {
				System.out.println(m.getInformation());
			}

			for (Player o : _heroes.getTeam()) {
				System.out.println(o.getInformation());
			}

			for (Player p : _heroes.getTeam()) {
				if (p.getStatus().equals("ALIVE")) {

					System.out.println("ACTIONS: \n" + "1. ATTACK\n" + "2. CAST A SPELL\n" + "3. USE A POTION\n"
							+ "4. CHANGE WEAPON AND/OR ARMOR");
					System.out.print(p.getName() + " What will you do?: ");
					
					int choice = _util.intCheck(_scanner, p.getName() + " What will you do?: ", 4, 1);
					boolean valid = false;
					while(!valid) {
						if (choice == 3) {
							if (p.getPotions().isEmpty()) {
								System.out.println("You have no potions!");
							}
							else {
								valid = true;
							}
							choice = _util.intCheck(_scanner, p.getName() + " What will you do?: ", 4, 1);
						}
						if (choice == 2) {
							if (p.getSpells().isEmpty()) {
								System.out.println("You have no spell!");
							}
							else {
								valid = true;
							}
							choice = _util.intCheck(_scanner, p.getName() + " What will you do?: ", 4, 1);
						}
						else {
							valid = true;
						}
						
					}
					

					if (choice == 1) {
						Monster target;
						ArrayList<Integer> badTargets = new ArrayList<Integer>();
						if (_monsters.getMonsterCount() >= 2) {
							int count = 0;
							for (Monster m : _monsters.getTeam()) {
								if (m.getStatus().equals("ALIVE")) {
									System.out.println(count + ". " + m.getName());
								} else {
									badTargets.add(count);
								}
								count++;
							}
							System.out.print("Which monster will you attack?: ");
							int targetChoice = _util.intCheck(_scanner, null, count, 0);
							while (badTargets.contains(targetChoice)) {
								targetChoice = _util.intCheck(_scanner, null, count, 0);
							}
							target = _monsters.getTeam()[targetChoice];
						} else {
							target = _monsters.getTeam()[0];
						}

						int dodge = _r.nextInt(99);
						if (dodge < target.getDodgeChance() - 1) {
							System.out.println(ASCIIColor.RED_BOLD + target.getName() + ASCIIColor.RESET + " Dodges!");
						} else {
							double damage = 0;
							if (p.getWeapons().isEmpty()) {
								damage = (p.getStrength() - target.getDefense()) * 0.05;
								target.decreaseHealth((int) damage);
								target.resetInformation();
							} else {
								damage = (p.getStrength() + p.getEquippedWeapon().getDamage() - target.getDefense())
										* 0.05;
								target.decreaseHealth((int) damage);
								target.resetInformation();
							}

							if (target.getHealth() < 0) {
								target.setHealth(0);
							}

							System.out.println(ASCIIColor.GREEN_BOLD + p.getName() + ASCIIColor.RESET + " attacks "
									+ ASCIIColor.RED_BOLD + target.getName() + ASCIIColor.RESET + " for " + damage
									+ " damage!");

							if (target.getHealth() == 0) {
								System.out.println(
										ASCIIColor.RED_BOLD + target.getName() + ASCIIColor.RESET + " is slain!");
								for (Monster m : _monsters.getTeam()) {
									if (m == target) {
										m.setInformation(ASCIIColor.RED_BOLD + m.getName().toUpperCase() + " DECEASED"
												+ ASCIIColor.RESET);
										m.setStatus("DECEASED");
									}
								}
							}
						}
					}
					if (choice == 2) {
						Monster target;
						int spellCount = 0;
						for (Spell s : p.getSpells()) {
							System.out.println(spellCount + ". " + s.getName());
						}

						System.out.print("Which spell will you use?: ");
						int spellChoice = _util.intCheck(_scanner, "Which spell will you use?: ", spellCount, 0);

						Spell targetSpell = p.getSpells().get(spellChoice);
						boolean canUseSpell = false;

						p.decreaseMana(targetSpell.getManaCost());
						if (p.getMana() < 0) {
							p.increaseMana(targetSpell.getManaCost());
							System.out.println("You don't have enough mana to cast this spell");
						} else {
							canUseSpell = true;
						}

						if (canUseSpell) {
							ArrayList<Integer> badTargets = new ArrayList<Integer>();
							if (_monsters.getMonsterCount() >= 2) {
								int count = 0;
								for (Monster m : _monsters.getTeam()) {
									if (m.getStatus().equals("ALIVE")) {
										System.out.println(count + ". " + m.getName());
									} else {
										badTargets.add(count);
									}
									count++;
								}
								System.out.print("Which monster will you attack?: ");
								int targetChoice = _util.intCheck(_scanner, null, count, 0);
								while (badTargets.contains(targetChoice)) {
									targetChoice = _util.intCheck(_scanner, null, count, 0);
								}
								target = _monsters.getTeam()[targetChoice];
							} else {
								target = _monsters.getTeam()[0];
							}

							int dodge = _r.nextInt(99);
							if (dodge < target.getDodgeChance() - 1) {
								System.out.println(
										ASCIIColor.RED_BOLD + target.getName() + ASCIIColor.RESET + " Dodges!");
							} else {
								double damage = 0;
								damage = targetSpell.getDamage() + (p.getDexterity() / 10000) * targetSpell.getDamage();
								target.decreaseHealth((int) damage);
								target.resetInformation();

								if (target.getHealth() < 0) {
									target.setHealth(0);
								}

								System.out.println(ASCIIColor.GREEN_BOLD + p.getName() + ASCIIColor.RESET + " attacks "
										+ ASCIIColor.RED_BOLD + target.getName() + ASCIIColor.RESET + " for " + damage
										+ " damage!");

								if (target.getHealth() == 0) {
									System.out.println(
											ASCIIColor.RED_BOLD + target.getName() + ASCIIColor.RESET + " is slain!");
									for (Monster m : _monsters.getTeam()) {
										if (m == target) {
											m.setInformation(ASCIIColor.RED_BOLD + m.getName().toUpperCase()
													+ " DECEASED" + ASCIIColor.RESET);
											m.setStatus("DECEASED");
										}
									}
								}
								else {
									if (targetSpell.getType().equals("ICE")) {
										if (target.getDamage() >= 100) {
											target.decreaseDamage(100);
										}
										else {
											target.setDamage(0);
										}
									}
									else if (targetSpell.getType().equals("FIRE")) {
										if (target.getDefense() >= 100) {
											target.decreaseDefense(100);
										}
										else {
											target.setDefense(0);
										}
									}
									else if (targetSpell.getType().equals("LIGHTNING")) {
										if (target.getDodgeChance() >= 10) {
											target.decreaseDodgeChance(10);
										}
										else {
											target.setDodgeChance(0);
										}
									}
								}
							}
							p.getSpells().remove(spellChoice);
						}
					}
					if (choice == 3) {
						int count = 0;
						for (Potion o : p.getPotions()) {
							System.out.println(count + ". " + o.getName());
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
					if (choice == 4) {
						p.getInventory();
					}

					_heroCount = 0;
					for (Monster m : _monsters.getTeam()) {
						if (m.getStatus().equals("ALIVE")) {
							_heroCount++;
						}
					}

					if (_heroCount == 0) {
						System.out
								.println(ASCIIColor.GREEN_BACKGROUND + _heroes.getName() + " win!" + ASCIIColor.RESET);
						_winningTeam = "HEROES";
						break;
					}
				}
			}
			if (_heroCount == 0) {
				break;
			}

			for (Monster m : _monsters.getTeam()) {
				if (m.getStatus().equals("ALIVE")) {
					Player p;
					while (true) {
						int target = _r.nextInt(_heroes.getPlayerCount());
						p = _heroes.getTeam()[target];
						if (p.getHealth() > 0) {
							break;
						}
					}

					int dodge = _r.nextInt(99);
					if (dodge < (p.getAgility() * 0.002) - 1) {
						System.out.println(ASCIIColor.GREEN_BOLD + p.getName() + ASCIIColor.RESET + " Dodges!");
					} else {
						double damage = 0;
						if (p.getArmors().isEmpty()) {
							damage = m.getDamage() * 0.05;
						} else {
							damage = (m.getDamage() - p.getEquippedArmor().getDamageReduction()) * 0.05;
						}
						p.decreaseHealth((int) damage);
						p.getHealth();
						p.resetInformation();

						if (p.getHealth() < 0) {
							p.setHealth(0);
						}

						System.out.println(ASCIIColor.RED_BOLD + m.getName() + ASCIIColor.RESET + " attacks "
								+ ASCIIColor.GREEN_BOLD + p.getName() + ASCIIColor.RESET + " for " + damage
								+ " damage!");

						if (p.getHealth() == 0) {
							System.out.println(ASCIIColor.GREEN_BOLD + p.getName() + ASCIIColor.RESET + " is slain!");
							for (Player o : _heroes.getTeam()) {
								if (p == o) {
									o.setInformation(ASCIIColor.GREEN_BOLD + o.getName().toUpperCase() + " FAINTED"
											+ ASCIIColor.RESET);
									o.setStatus("FAINTED");
								}
							}
						}
					}

					_monsterCount = 0;
					for (Player h : _heroes.getTeam()) {
						if (h.getStatus().equals("ALIVE")) {
							_monsterCount++;
						}
					}

					if (_monsterCount == 0) {
						System.out.println(ASCIIColor.RED_BACKGROUND + "Monsters win!" + ASCIIColor.RESET);
						_winningTeam = "MONSTERS";
						break;
					}
				}
				for (Player o : _heroes.getTeam()) {
					double healthInc = o.getHealth() * 0.05;
					double manaInc = o.getMana() * 0.05;
					o.increaseHealth((int) healthInc);
					o.increaseMana((int) manaInc);
				}
			}
			if (_monsterCount == 0) {
				break;
			}
		}
		if (_winningTeam.equals("HEROES")) {
			for (Player p : _heroes.getTeam()) {
				if (p.getStatus().equals("ALIVE")) {
					for (Monster m : _monsters.getTeam()) {
						p.addMoney(100 * m.getLevel());
						p.increaseExp(2);
					}
				} else {
					p.setHealth(50 * p.getLevel());
					p.setStatus("ALIVE");
				}

				p.checkIfLeveledUp();
			}
		} else if (_winningTeam.equals("MONSTERS")) {
			for (Player p : _heroes.getTeam()) {
				p.setHealth(30 * p.getLevel());
				p.setMoney(p.getMoney() / 2);
			}
		}
	}
}
