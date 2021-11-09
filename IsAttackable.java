/******
 * IsAttackable
 * Author: Adam Curley
 * 
 * This interface is for objects that a player can use to attack with
 * 
 ******/

public interface IsAttackable {
	/*
	 * All attackable objects have a damage that can amplify the player's strength
	 */
	public void increaseDamage(int damage);
	public void decreaseDamage(int damage);
	public void setDamage(int damage);
	public int getDamage();
}
