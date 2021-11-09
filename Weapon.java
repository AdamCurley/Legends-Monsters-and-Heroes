/******
 * Weapon
 * Author: Adam Curley
 * 
 * This class is a weapon object to be equipped and attack with by the player, increasing their damage output
 * The list of weapons loaded from Weaponry.txt
 * 
 * This class implements the IsEquippable.java and isAttackable.java Interfaces, all class method explanations can be found in those files
 * 
 * This class implements the Reader.java Interface, this is used to gather the weapons listed in Weaponry.txt, method explanations can be found in that file
 * 
 ******/

public class Weapon extends Item implements IsEquippable, IsAttackable, Reader {
	private AttributeGatherer _reader = new AttributeGatherer();
	private int _damage;
	private int _requiredHands;
	private boolean _isEquipped;
	
	public Weapon(int choice) {
		if (choice != 0) {
			setupAttributes(choice);
		}
	}
	
	public void setupAttributes(int choice) {
		String[] s = _reader.readFile("Weaponry.txt", choice);
		setValues(s);
	}
	
	public void setValues(String[] s) {
		this.setName(s[0]);
		this.setCost(Integer.parseInt(s[1]));
		this.setRequiredLevel(Integer.parseInt(s[2]));
		_damage = Integer.parseInt(s[3]);
		_requiredHands = Integer.parseInt(s[4]);
	}
	
	public boolean isEquipped() {
		return _isEquipped;
	}
	
	public void setEquipped(boolean isEquipped) {
		_isEquipped = isEquipped;
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
	
	public void setRequiredHands(int requiredHands) {
		_requiredHands = requiredHands;
	}
	
	public int getRequiredHands() {
		return _requiredHands;
	}
}
