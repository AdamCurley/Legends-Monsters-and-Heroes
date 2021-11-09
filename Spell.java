/******
 * Spell
 * Author: Adam Curley
 * 
 * This class is a spell object to be casted by the player, allowing them to cast various types of spells
 * that deal damage to the opposing monster and deteriorate their skills
 * The list of spells loaded from FireSpells.txt, IceSpells.txt, and LightningSpells.txt
 * 
 * This class implements the IsCastable.java and IsAttackable.java Interfaces, all class method explanations can be found in those files
 * 
 * This class implements the TypeReader.java Interface, this is used to gather the spells listed in the various .txt files, method explanations can be found in that file
 * 
 ******/

public class Spell extends Item implements IsCastable, TypeReader, IsAttackable {
	private AttributeGatherer _reader = new AttributeGatherer();
	private int _damage;
	private int _manaCost;
	private String _type;

	public Spell(String type, int choice) {
		_type = type;
		if (choice != 0) {
			setupAttributes(type, choice);
		}
	}
	
	public void setupAttributes(String type, int character) {
		if (type.equals("FIRE")) {
			String[] s = _reader.readFile("FireSpells.txt", character);
			setValues(s);
		}
		else if (type.equals("LIGHTNING")) {
			String[] s = _reader.readFile("LightningSpells.txt", character);
			setValues(s);
		}
		else if (type.equals("ICE")) {
			String[] s = _reader.readFile("IceSpells.txt", character);
			setValues(s);
		}
	}
	
	public void setValues(String[] s) {
		this.setName(s[0]);
		this.setCost(Integer.parseInt(s[1]));
		this.setRequiredLevel(Integer.parseInt(s[2]));
		_damage = Integer.parseInt(s[3]);
		_manaCost = Integer.parseInt(s[4]);
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

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}
	
	public void increaseManaCost(int manaCost) {
		_manaCost += manaCost;
	}
	
	public void decreaseManaCost(int manaCost) {
		_manaCost -= manaCost;
	}
	
	public void setMana(int manaCost) {
		_manaCost = manaCost;
	}
	
	public int getManaCost() {
		return _manaCost;
	}
}