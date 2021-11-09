/******
 * Marker
 * Author: Adam Curley
 * 
 * A custom object for symbols on the gameboard
 * 
 ******/

public class Marker {
	private String _symbol;
	
	public Marker(String symbol) {
		_symbol = symbol;
	}

	public void setMarker(String symbol) {
		_symbol = symbol;
	}
	
	public String getSymbol() {
		return _symbol;
	}
}
