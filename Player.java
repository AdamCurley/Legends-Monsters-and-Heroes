
/******
 * Player
 * Author: Adam Curley
 * 
 * This code is used to create player's and grants them their attributes
 * 
 ******/

import java.util.ArrayList;

public class Player extends Character {
	private int _money;
	private int _exp;
	private int _mana;
	private int _strength;
	private int _dexterity;
	private int _agility;
	private int _armorCount = 0;
	private int _weaponCount = 0;
	private String _information;
	private ArrayList<Weapon> _weapons = new ArrayList<Weapon>();
	private ArrayList<Armor> _armor = new ArrayList<Armor>();
	private ArrayList<Potion> _potions = new ArrayList<Potion>();
	private ArrayList<Spell> _spells = new ArrayList<Spell>();

	public String getInformation() {
		return _information;
	}

	public void resetInformation() {
		_information = ASCIIColor.GREEN_BOLD + getName().toUpperCase() + " Level: " + getLevel() + " HP: " + getHealth()
				+ " Mana: " + getMana() + " Strength: " + getStrength() + " Agility: " + getAgility() + " Dexterity: "
				+ getDexterity() + ASCIIColor.RESET;
		;
	}

	public void setInformation(String information) {
		_information = information;
	}

	/*
	 * gets the player's money
	 */
	public int getMoney() {
		return _money;
	}

	/*
	 * adds money to the player's money
	 */
	public void addMoney(int money) {
		_money += money;
	}

	/*
	 * Subtracts money from the player's money
	 */
	public void subtractMoney(int money) {
		_money -= money;
	}

	public void increaseExp(int exp) {
		_exp += exp;
	}

	public void decreaseExp(int exp) {
		_exp -= exp;
	}

	public void setExp(int exp) {
		_exp = exp;
	}

	public int getExp() {
		return _exp;
	}

	public void increaseMana(int mana) {
		_mana += mana;
	}

	public void decreaseMana(int mana) {
		_mana -= mana;
	}

	public void setMana(int mana) {
		_mana = mana;
	}

	public int getMana() {
		return _mana;
	}

	public void increaseStrength(int strength) {
		_strength += strength;
	}

	public void decreaseStrength(int strength) {
		_strength -= strength;
	}

	public void setStrength(int strength) {
		_strength = strength;
	}

	public int getStrength() {
		return _strength;
	}

	public void increaseDexterity(int dexterity) {
		_dexterity += dexterity;
	}

	public void decreaseDexterity(int dexterity) {
		_dexterity -= dexterity;
	}

	public void setDexterity(int dexterity) {
		_dexterity = dexterity;
	}

	public int getDexterity() {
		return _dexterity;
	}

	public void increaseAgility(int agility) {
		_agility += agility;
	}

	public void decreaseAgility(int agility) {
		_agility -= agility;
	}

	public void setAgility(int agility) {
		_agility = agility;
	}

	public int getAgility() {
		return _agility;
	}

	public void addWeapon(Weapon weapon) {
		_weapons.add(weapon);
		_weaponCount++;
	}

	public int getWeaponCount() {
		return _weaponCount;
	}

	public void removeWeapon(Weapon weapon) {
		_weapons.remove(weapon);
		_weaponCount--;
	}

	public Weapon getWeapon(Weapon weapon) {
		int index = _weapons.indexOf(weapon);
		return _weapons.get(index);
	}

	public ArrayList<Weapon> getWeapons() {
		return _weapons;
	}

	public void addArmor(Armor armor) {
		_armor.add(armor);
		_armorCount++;
	}

	public void removeArmor(Armor armor) {
		_armor.remove(armor);
		_armorCount--;
	}

	public int getArmorCount() {
		return _armorCount;
	}

	public Armor getArmor(Armor armor) {
		int index = _armor.indexOf(armor);
		return _armor.get(index);
	}

	public ArrayList<Armor> getArmors() {
		return _armor;
	}

	public void addPotion(Potion potion) {
		_potions.add(potion);
	}

	public void removePotion(Potion potion) {
		_potions.remove(potion);
	}

	public Potion getPotion(Potion potion) {
		int index = _potions.indexOf(potion);
		return _potions.get(index);
	}

	public ArrayList<Potion> getPotions() {
		return _potions;
	}

	public void addSpell(Spell spell) {
		_spells.add(spell);
	}

	public void removeSpell(Spell spell) {
		_spells.remove(spell);
	}

	public Spell getSpell(Spell spell) {
		int index = _spells.indexOf(spell);
		return _spells.get(index);
	}

	public ArrayList<Spell> getSpells() {
		return _spells;
	}

	/*
	 * Go through each one the player's weapons, spells, armor, and potions and display them
	 * Also inform the user of which of their weapons and armor are equipped (1 max)
	 */
	public void getInventory() {
		System.out.println(ASCIIColor.YELLOW_BACKGROUND + "Weapons:" + ASCIIColor.RESET);
		for (Weapon w : _weapons) {
			if (w.isEquipped()) {
				System.out.println(w.getName() + ASCIIColor.BLUE_BACKGROUND + " EQUIPPED" + ASCIIColor.RESET);
			} else {
				System.out.println(w.getName());
			}
		}
		if (_weapons.isEmpty()) {
			System.out.println("You have no weapons!");
		}

		System.out.println(ASCIIColor.YELLOW_BACKGROUND + "Armor:" + ASCIIColor.RESET);
		for (Armor a : _armor) {
			if (a.isEquipped()) {
				System.out.println(a.getName() + " " + ASCIIColor.BLUE_BACKGROUND + "EQUIPPED" + ASCIIColor.RESET);
			} else {
				System.out.println(a.getName());
			}
		}
		if (_armor.isEmpty()) {
			System.out.println("You have no armor!");
		}

		System.out.println(ASCIIColor.YELLOW_BACKGROUND + "Potions:" + ASCIIColor.RESET);
		for (Potion o : _potions) {
			System.out.println(o.getName());
		}
		if (_potions.isEmpty()) {
			System.out.println("You have no potions!");
		}

		System.out.println(ASCIIColor.YELLOW_BACKGROUND + "Spells:" + ASCIIColor.RESET);
		for (Spell s : _spells) {
			System.out.println(s.getName());
		}
		if (_spells.isEmpty()) {
			System.out.println("You have no spells!");
		}
	}

	public Weapon getEquippedWeapon() {
		for (Weapon w : _weapons) {
			if (w.isEquipped()) {
				return w;
			}
		}
		return null;
	}

	public Armor getEquippedArmor() {
		for (Armor a : _armor) {
			if (a.isEquipped()) {
				return a;
			}
		}
		return null;
	}
	
	/*
	 * Check if the player has based the exp threshold necessary to levelup, and if they have call the logic used to level them up
	 */
	public void checkIfLeveledUp() {
		int threshold = this.getLevel() * 10;
		if (this.getExp() >= threshold) {
			levelUp();
		}
	}
	
	/*
	 * Level up the player, increasing their stats and skills and, of course, their level
	 */
	public void levelUp() {
		double manaInc = _mana*1.1;
		double strengthInc =  _strength * 0.5;
		double agilityInc = _agility * 0.5;
		double dexterityInc = _dexterity * 0.5;
		
		this.setMana((int) manaInc);
		
		if (this.getType().equals("PALADIN")) {
			strengthInc = strengthInc * 0.05;
			dexterityInc = dexterityInc * 0.05;
		}
		if (this.getType().equals("WARRIOR")) {
			dexterityInc = dexterityInc * 0.05;
			agilityInc = agilityInc * 0.05;
		}
		if (this.getType().equals("SORCERER")) {
			strengthInc = strengthInc * 0.05;
			agilityInc = agilityInc * 0.05;
		}
		
		this.increaseLevel(1);
		this.setHealth(100*this.getLevel());
		this.increaseStrength((int) strengthInc);
		this.increaseAgility((int) agilityInc);
		this.increaseDexterity((int) dexterityInc);
		System.out.println(this.getName() + " leveled Up");
	}

	public void setMoney(int money) {
		_money = money;
	}
}
