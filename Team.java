/******
 * Team
 * Author: Adam Curley
 * 
 * A custom class for an array of heroes that acts as their team
 * 
 ******/

public class Team {
	private Player[] _players;
	private Marker _symbol;
	private String _name;
	private int _column;
	private int _row;
	private String _state;
	private boolean _inTransit;
	private int _playerCount;
	
	public Team(String name, int playerCount) {
		_playerCount = playerCount;
		_players = new Player[playerCount];
		_symbol = new Marker("H");
		_name = name;
		_inTransit = false;
		_state = "COMMON SQUARE";
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
	
	public Marker getSymbol() {
		return _symbol;
	}
	
	public void setSymbol(Marker marker) {
		_symbol = marker;
	}
	
	public String getName() {
		return _name;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	public int getColumn() {
		return _column;
	}
	
	public int getRow() {
		return _row;
	}
	
	public void setColumn(int column) {
		_column = column;
	}
	
	public void setRow(int row) {
		_row = row;
	}
	
	public void setGameState(String state) {
		_state = state;
	}
	
	public String getGameState() {
		return _state;
	}
	
	public boolean getInTransit() {
		return _inTransit;
	}
	
	public void setInTransit(boolean b) {
		_inTransit = b;
	}
}
