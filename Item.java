/******
 * Item
 * Author: Adam Curley
 * 
 * An item is a part of a game that a player can either use and equip all items
 * use these methods and attributes universally.
 * Thus, all items must extend this class
 * 
 ******/

public class Item {
	private String _name;
	private int _requiredLevel;
	private int _cost;
	
	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}
	
	public void increaseRequiredLevel(int requiredLevel) {
		_requiredLevel += requiredLevel;
	}
	
	public void decreaseRequiredLevel(int requiredLevel) {
		_requiredLevel -= requiredLevel;
	}
	
	public void setRequiredLevel(int requiredLevel) {
		_requiredLevel = requiredLevel;
	}
	
	public int getRequiredLevel() {
		return _requiredLevel;
	}
	
	public void increaseCost(int cost) {
		_cost += cost;
	}
	
	public void decreaseCost(int cost) {
		_cost -= cost;
	}
	
	public void setCost(int cost) {
		_cost = cost;
	}
	
	public int getCost() {
		return _cost;
	}
}
