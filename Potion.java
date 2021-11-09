/******
 * Potion
 * Author: Adam Curley
 * 
 * This class is a potion object to be used by the player, increasing their skills and stats
 * The list of potions loaded from Potions.txt
 * 
 * This class implements the IsUsable.java Interface, all class method explanations can be found in that file
 * 
 * This class implements the Reader.java Interface, this is used to gather the potions listed in Potions.txt, method explanations can be found in that file
 * 
 ******/

public class Potion extends Item implements IsUsable, Reader {
	private AttributeGatherer _reader = new AttributeGatherer();
	private int _attributeIncrease;
	private String[] _attributeAffected;
	
	public Potion(int choice) {
		if (choice != 0) {
			setupAttributes(choice);
		}
	}
	
	public void setupAttributes(int choice) {
		String[] s = _reader.readFile("Potions.txt", choice);
		setValues(s);
	}
	
	public void setValues(String[] s) {
		this.setName(s[0]);
		this.setCost(Integer.parseInt(s[1]));
		this.setRequiredLevel(Integer.parseInt(s[2]));
		_attributeIncrease = Integer.parseInt(s[3]);
		_attributeAffected = s[4].split("/");
	}
	
	public String[] getAttributeAffected() {
		return _attributeAffected;
	}

	public void setAttributeAffect(String[] attributeAffected) {
		_attributeAffected = attributeAffected;
	}
	
	public void setAttributeIncrease(int attributeIncrease) {
		_attributeIncrease = attributeIncrease;
	}
	
	public int getAttributeIncrease() {
		return _attributeIncrease;
	}
}

