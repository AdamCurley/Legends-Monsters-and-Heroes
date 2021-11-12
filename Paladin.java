/******
 * Paladin
 * Author: Adam Curley
 * 
 * One of the three types of heroes in L:M&H
 * The list of Paladins is loaded from Paladins.txt
 * 
 * This class implements the TypeReader.java Interface, this is used to gather the spells listed in the various .txt files, method explanations can be found
 * 
 ******/

public class Paladin extends Player implements TypeReader {
	private AttributeGatherer _reader = new AttributeGatherer();
	
	public Paladin(String name, String type, Marker symbol, int character) {
		setupAttributes(type, character);
		this.setName(name);
		this.setType(type);
		this.setSymbol(new Marker(symbol.getSymbol() + " "));
		this.setLevel(1);
		this.setStatus("ALIVE");
		this.setHealth(100 * this.getLevel());
		this.resetInformation();
	}
	
	public void setupAttributes(String type, int character) {
		if (type.equals("PALADIN")) {
			String[] s = _reader.readFile("Paladins.txt", character);
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
