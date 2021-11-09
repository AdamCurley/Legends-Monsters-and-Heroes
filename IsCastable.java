/******
 * IsCastable
 * Author: Adam Curley
 * 
 * This interface is for objects that a player can cast (such as spells)
 * 
 ******/

public interface IsCastable {
	/*
	 * All castable items require mana that can be gotten, set, increased or decreased
	 */
	public void increaseManaCost(int manaCost);
	public void decreaseManaCost(int manaCost);
	public void setMana(int manaCost);
	public int getManaCost();
}
