/******
 * IsUsable
 * Author: Adam Curley
 * 
 * This interface is for objects that a player can use
 * 
 ******/

public interface IsUsable {
	/*
	 * All usable items have attributes they affect and information on how they affect those attributes
	 */
	public String[] getAttributeAffected();
	public void setAttributeAffect(String[] attributeAffected);
	public void setAttributeIncrease(int attributeIncrease);
	public int getAttributeIncrease();
}
