/******
 * Character
 * Author: Adam Curley
 * 
 * A character can either be a monster or a hero and contains attributes shared by both of those objects.
 * Thus, this class is extended by the Player and Monster classes.
 * 
 ******/

public class Character {
	private int _health;
	private int _level;
	private String _name;
	private String _type;
	private String _status;
	
	public String getName() {
		return _name;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}
	
	public void setStatus(String status) {
		_status = status;
	}
	
	public String getStatus() {
		return _status;
	}
	
	public void increaseHealth(int health) {
		_health += health;
	}
	
	public void decreaseHealth(int health) {
		_health -= health;
	}
	
	public void setHealth(int health) {
		_health = health;
	}
	
	public int getHealth() {
		return _health;
	}
	
	public void increaseLevel(int level) {
		_level += level;
	}
	
	public void decreaseLevel(int level) {
		_level -= level;
	}
	
	public void setLevel(int level) {
		_level = level;
	}
	
	public int getLevel() {
		return _level;
	}
}
