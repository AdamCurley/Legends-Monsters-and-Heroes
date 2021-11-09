/******
 * BoardCell
 * Author: Adam Curley
 * 
 * This object is what fills a gameboard and acts as a square on the board, it contains a marker which is a symbol that fills the square
 * 
 ******/

public class BoardCell {
	private Marker _marker;
	
	public BoardCell(Marker marker) {
		_marker = marker;
	}

	public Marker getBoardCell() {
		return _marker;
	}
	
	public void setMarker(Marker marker) {
		_marker = marker;
	}
}
