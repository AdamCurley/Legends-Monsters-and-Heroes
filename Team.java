/******
 * Team
 * Author: Adam Curley
 * 
 * A custom class for an array of heroes that acts as their team
 * 
 ******/

public class Team {
	private Player[] _players;
	private String _name;
	private boolean _inTransit;
	private int _playerCount;
	
	public Team(String name, int playerCount) {
		_playerCount = playerCount;
		_players = new Player[playerCount];
		_name = name;
		_inTransit = false;
	}
	
	public int getPlayerCount() {
		return _playerCount;
	}
	
	public void setPlayerCount(int count) {
		_playerCount = count;
	}
	
	public void addMember(Player p, int index) {
		_players[index] = p;
	}
	
	public Player[] getTeam() {
		return _players;
	}
	
	public String getName() {
		return _name;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	public boolean getInTransit() {
		return _inTransit;
	}
	
	public void setInTransit(boolean b) {
		_inTransit = b;
	}
}
