/******
 * LegendsMandH
 * Author: Adam Curley
 * 
 * This is the main object for the game Legends: Monsters and Heroes
 * 
 * This class implements the Game Inferface, thus it requires a Game Method that begins an instance of the game
 * 
 ******/

import java.util.Scanner;

public class LegendsMandH extends LegendsMandHUtility implements Game {
	private Team _team;
	private Gameboard _gameboard;
	private Market _market = new Market();
	private Inaccessible _inaccessible = new Inaccessible();
	private Logger _logger = new Logger();
	private Utility _util = new Utility();
	private Scanner _scanner = new Scanner(System.in);
	
	/*
	 * When a game starts, get all of the information for the players and their team
	 */
	public LegendsMandH() {
		System.out.println(ASCIIColor.CYAN_BACKGROUND + "Welcome to Legends: Monsters and Heroes!" + ASCIIColor.RESET);
		
		System.out.print("How many players are there?: ");
		int count = _util.intCheck(_scanner, "How many players are there?: ", 3, 1);
			
		String[] names = this.AskForPlayerNames(count, false);
		_team = this.CreateTeam(names);
		_gameboard = new Gameboard(8, 8);
	}
	
	/*
	 * Workflow:
	 * 	1. Reset the gameboard
	 *  2. Place the team of heroes on the board (in a set location)
	 *  3. Populate the board with 20% inaccessible squares, 30% markets and 50% common squares
	 *  4. Begin an instance of a Legends: Monsters and Heroes game using all of this information
	 *  5. After the game ends, inform the players and return them to the main menu
	 */
	public void Game() {
		_gameboard.resetGameboard();
		
		_gameboard.place(_team.getSymbol(), _logger, _team, false, false);
		
		int tileCount = _gameboard.getColumns() * _gameboard.getRows();
		for (int i = 0; i < tileCount*.20; i++) {
			_gameboard.place(_inaccessible.getInaccessibleMarker(), _logger, null, true, false);
		}
		for (int i = 0; i < tileCount*.30; i++) {
			_gameboard.place(_market.getMarketMarker(), _logger, null, true, true);
		}
		
		System.out.println(LMHGame(_team, _market, _gameboard, _logger));
	}
}