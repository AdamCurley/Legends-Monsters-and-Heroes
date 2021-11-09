/******
 * Spirit
 * Author: Adam Curley
 * 
 * One of the three types of monsters in L:M&H
 * The list of Spirits is loaded from Spirits.txt
 * 
 * This class implements the TypeReader.java Interface, this is used to gather the spells listed in the various .txt files, method explanations can be found
 * 
 ******/

public class Spirit extends Monster implements TypeReader {
	private AttributeGatherer _reader = new AttributeGatherer();
	
	public Spirit(String type, int character) {
		setupAttributes(type, character);
		this.setStatus("ALIVE");
		this.setType(type);
		this.setHealth(100 * this.getLevel());
	}
	
	public void setupAttributes(String type, int character) {
		if (type.equals("SPIRIT")) {
			String[] s = _reader.readFile("Spirits.txt", character);
			setValues(s);
		}
	}
	
	public void setValues(String[] s) {
		this.setName(s[0]);
		this.setLevel(Integer.parseInt(s[1]));
		this.setDamage(Integer.parseInt(s[2]));
		this.setDefense(Integer.parseInt(s[3]));
		this.setDodgeChance(Integer.parseInt(s[4]));
	}
}
