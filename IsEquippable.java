/******
 * IsEquippable
 * Author: Adam Curley
 * 
 * This interface is for objects that a player can equip and weild
 * 
 ******/

public interface IsEquippable {
	/*
	 * All equippable objects need a boolean value to tell the program whether the player currently is wearing/weilding these items
	 */
	public boolean isEquipped();
	public void setEquipped(boolean isEquipped);
}
