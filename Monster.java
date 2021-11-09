/******
 * Monster
 * Author: Adam Curley
 * 
 * Monsters are characters that the player battles, they have damage, defense and a dodge chance
 * 
 ******/


public class Monster extends Character {
	private int _damage;
	private int _defense;
	private int _dodgeChance;
	private String _information;

	public String getInformation() {
		return _information;
	}
	
	public void resetInformation() {
		_information = ASCIIColor.RED_BOLD + getName().toUpperCase() + " Level: "
				+ getLevel() + " HP: " + getHealth() + " Damage: "
				+ getDamage() + " Defense: " + getDefense() + " Dodge Chance: "
				+ getDodgeChance() + "% " + "Type: " + getType() + ASCIIColor.RESET;
	}
	
	public void setInformation(String information) {
		_information = information;
	}

	public void increaseDamage(int damage) {
		_damage += damage;
	}

	public void decreaseDamage(int damage) {
		_damage -= damage;
	}

	public void setDamage(int damage) {
		_damage = damage;
	}

	public int getDamage() {
		return _damage;
	}

	public void increaseDefense(int defense) {
		_defense += defense;
	}

	public void decreaseDefense(int defense) {
		_defense -= defense;
	}

	public void setDefense(int defense) {
		_defense = defense;
	}

	public int getDefense() {
		return _defense;
	}

	public void increaseDodgeChance(int dodgeChance) {
		_dodgeChance += dodgeChance;
	}

	public void decreaseDodgeChance(int dodgeChance) {
		_dodgeChance -= dodgeChance;
	}

	public void setDodgeChance(int dodgeChance) {
		_dodgeChance = dodgeChance;
	}

	public int getDodgeChance() {
		return _dodgeChance;
	}
}
