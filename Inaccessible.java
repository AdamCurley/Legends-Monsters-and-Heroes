/******
 * Inaccessible
 * Author: Adam Curley
 * 
 * A type of gameboard square that cannot be entered by the player
 * 
 ******/

public class Inaccessible {
	private Marker _symbol;
	
	public Inaccessible() {
		_symbol = new Marker(ASCIIColor.WHITE_BACKGROUND + "  " + ASCIIColor.RESET);
	}
	
	public Marker getInaccessibleMarker() {
		return _symbol;
	}
	
	public void setInaccessibleMarker(Marker symbol) {
		symbol.setMarker(symbol.getSymbol());
		_symbol = symbol;
	}	
}
