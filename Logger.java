/******
 * Logger
 * Author: Adam Curley
 * 
 * This class can be used to log other values in the future
 * 
 ******/

import java.util.ArrayList;

public class Logger {
	private ArrayList<int[]> _moves;
	private ArrayList<int[]> _markets;
	
	/*
	 * Constructor that will keep all logs
	 */
	public Logger() {
		_moves = new ArrayList<int[]>();
		int[] baseMove = new int[2];
		baseMove[0] = 0;
		baseMove[1] = 0;
		_moves.add(baseMove);
		
		_markets = new ArrayList<int[]>();
	}
	
	/*
	 * add a move to the moves log
	 */
	public void addMove(int[] move) {
		_moves.add(move);
	}
	
	/*
	 * remove a move from the log to make it available again
	 */
	public void removeMove(int[] move) {
		for (int i = 0; i < _moves.size(); i++) {
			if (_moves.get(i)[0] == move[0] && _moves.get(i)[1] == move[1]) {
				_moves.remove(i);
			}
		}
	}
	
	/*
	 * Check if a move is made and return a boolean value corresponding
	 */
	public boolean isMoveMade(int[] move) {
		for (int[] i : _moves) {
			if (i[0] == move[0] && i[1] == move[1]) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * gets the moves log for checking
	 */
	public ArrayList<int[]> getMoves() {
		return _moves;
	}
	
	/*
	 * reset the moves log
	 */
	public void resetMoves() {
		_moves = new ArrayList<int[]>();
		int[] baseMove = new int[2];
		baseMove[0] = 0;
		baseMove[1] = 0;
		_moves.add(baseMove);
	}
	
	/*
	 * Add a market
	 */
	public void addMarket(int[] market) {
		_markets.add(market);
	}
	
	/*
	 * remove a market
	 */
	public void removeMarket(int[] market) {
		for (int i = 0; i < _markets.size(); i++) {
			if (_markets.get(i)[0] == market[0] && _markets.get(i)[1] == market[1]) {
				_markets.remove(i);
			}
		}
	}
	
	/*
	 * check if a market exists in a square on the gameboard
	 */
	public boolean isMarket(int[] market) {
		for (int[] i : _markets) {
			if (i[0] == market[0] && i[1] == market[1]) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * gets the moves log for checking
	 */
	public ArrayList<int[]> getMarkets() {
		return _markets;
	}
	
	/*
	 * reset the moves log
	 */
	public void resetMarkets() {
		_markets = new ArrayList<int[]>();
		int[] baseMove = new int[2];
		baseMove[0] = 0;
		baseMove[1] = 0;
		_markets.add(baseMove);
	}
}
