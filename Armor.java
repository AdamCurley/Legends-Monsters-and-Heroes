/******
 * Armor
 * Author: Adam Curley
 * 
 * This class is an armor object to be equipped by the player, increasing their damage resistance
 * The list of armors loaded from Armory.txt
 * 
 * This class implements the IsEquippable.java Interface, all class method explanations can be found in that file
 * 
 * This class implements the Reader.java Interface, this is used to gather the armor listed in Armory.txt, method explanations can be found in that file
 * 
 ******/


public class Armor extends Item implements IsEquippable, Reader {
	private AttributeGatherer _reader = new AttributeGatherer();
	private int _damageReduction;
	private boolean _isEquipped;
	
	public Armor(int choice) {
		if (choice != 0) {
			setupAttributes(choice);
		}
	}
	
	public void setupAttributes(int choice) {
		String[] s = _reader.readFile("Armory.txt", choice);
		setValues(s);
	}
	
	public void setValues(String[] s) {
		this.setName(s[0]);
		this.setCost(Integer.parseInt(s[1]));
		this.setRequiredLevel(Integer.parseInt(s[2]));
		_damageReduction = Integer.parseInt(s[3]);
	}
	
	public void increaseDamageReduction(int damageReduction) {
		_damageReduction += damageReduction;
	}
	
	public void decreaseDamageReduction(int damageReduction) {
		_damageReduction -= damageReduction;
	}
	
	public void setDamage(int damageReduction) {
		_damageReduction = damageReduction;
	}
	
	public int getDamageReduction() {
		return _damageReduction;
	}
	
	public boolean isEquipped() {
		return _isEquipped;
	}
	
	public void setEquipped(boolean isEquipped) {
		_isEquipped = isEquipped;
	}
}