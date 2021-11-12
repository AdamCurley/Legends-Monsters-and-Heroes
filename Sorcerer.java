/******
 * Sorcerer
 * Author: Adam Curley
 * 
 * One of the three types of heroes in L:M&H
 * The list of Sorcerers is loaded from Sorcerers.txt
 * 
 * This class implements the TypeReader.java Interface, this is used to gather the spells listed in the various .txt files, method explanations can be found
 * 
 ******/

public class Sorcerer extends Player implements TypeReader {
	private AttributeGatherer _reader = new AttributeGatherer();
	
	public Sorcerer(String name, String type, Marker symbol, int character) {
		setupAttributes(type, character);
		this.setName(name);
		this.setLevel(1);
		this.setStatus("ALIVE");
		this.setSymbol(new Marker(symbol.getSymbol() + " "));
		this.setHealth(100 * this.getLevel());
		this.setType(type);
		this.resetInformation();
	}
	
	public void setupAttributes(String type, int character) {
		if (type.equals("SORCERER")) {
			String[] s = _reader.readFile("Sorcerers.txt", character);
			setValues(s);
		}
	}
	
	public void setValues(String[] s) {
		this.setMana(Integer.parseInt(s[1]));
		this.setStrength(Integer.parseInt(s[2]));
		this.setAgility(Integer.parseInt(s[3]));
		this.setDexterity(Integer.parseInt(s[4]));
		this.addMoney(Integer.parseInt(s[5]));
		this.setExp(Integer.parseInt(s[6]));
	}
}
