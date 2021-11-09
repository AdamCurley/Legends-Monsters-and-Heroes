import java.util.Random;

/******
 * Gameboard 
 * Author: Adam Curley
 * 
 * This code creates a gameboard that can be used for a wide variety of games
 * 
 ******/

public class Gameboard {
	private static BoardCell[][] _gameboard;
	private static int _columns, _rows;
	private int[] _fixedSize;
	private Random _r = new Random();

	/*
	 * Gameboard constructor, establishes the board's rows and columns and then
	 * "fixes" those values for use in the form of an array. Gameboard is
	 * instantiated as a box array and is filled with symbols to make it look like a
	 * real gameboard.
	 */
	public Gameboard(int rows, int columns) {
		_columns = columns;
		_rows = rows;
		_fixedSize = fixSize(_rows, _columns);

		_gameboard = new BoardCell[_fixedSize[0]][_fixedSize[1]];
		_gameboard = fillGameboard(_gameboard);
	}

	/*
	 * gets the number of columns
	 */
	public int getColumns() {
		return _columns;
	}

	/*
	 * gets the number of rows
	 */
	public int getRows() {
		return _rows;
	}

	/*
	 * gets the "fixed" size of the board (the actual size of the box array)
	 */
	public int[] getFixedSize() {
		return _fixedSize;
	}

	/*
	 * sets the gameboard to a new size and adjusts all values accordingly
	 */
	public BoardCell[][] setGameboard(int rows, int columns) {
		_columns = columns;
		_rows = rows;
		_fixedSize = fixSize(_rows, _columns);

		_gameboard = new BoardCell[_fixedSize[0]][_fixedSize[1]];
		_gameboard = fillGameboard(_gameboard);

		return _gameboard;
	}

	/*
	 * reset the gameboard to its "empty" form at the same size
	 */
	public BoardCell[][] resetGameboard() {
		_gameboard = new BoardCell[_fixedSize[0]][_fixedSize[1]];
		_gameboard = fillGameboard(_gameboard);

		return _gameboard;
	}

	/*
	 * fills the box array with symbols in corresponding spots to make it look like
	 * an actual gameboard
	 */
	public BoardCell[][] fillGameboard(BoardCell[][] gameboard) {

		for (int i = 0; i < getFixedSize()[0]; i++) {
			for (int j = 0; j < getFixedSize()[1]; j++) {
				if (i % 2 == 0) {
					if (j % 2 == 0) {
						gameboard[i][j] = new BoardCell(new Marker(ASCIIColor.YELLOW_BOLD + "+" + ASCIIColor.RESET));
					} else {
						gameboard[i][j] = new BoardCell(new Marker(ASCIIColor.YELLOW_BOLD + "-" + ASCIIColor.RESET));
					}
				} else {
					if (j % 2 == 0) {
						gameboard[i][j] = new BoardCell(new Marker(ASCIIColor.YELLOW_BOLD + "|" + ASCIIColor.RESET));
					} else {
						gameboard[i][j] = new BoardCell(new Marker(" "));
					}
				}
			}
		}
		return gameboard;
	}

	/*
	 * get the gameboard
	 */
	public BoardCell[][] getGameboard() {
		return _gameboard;
	}

	/*
	 * place a Marker on the gameboard and log its location so it cant be filled again
	 * Also, check booleans to see the location should be random or if a market is being placed
	 */
	public void place(Marker symbol, Logger logger, Team t, boolean random, boolean isMarket) {
		int[] move = new int[2];
		int row = 0;
		int column = 0;

		while (logger.isMoveMade(move) || logger.isMarket(move)) {
			if (random) {
				row = _r.nextInt(getRows()) + 1;
				column = _r.nextInt(getColumns()) + 1;
			} else {
				row = getRows() / 2;
				column = getColumns() / 2;
			}
			move[0] = row;
			move[1] = column;
		}

		if (isMarket) {
			logger.addMarket(move);
		} else {
			logger.addMove(move);
		}

		move = fixMove(row, column);

		if (t != null) {
			t.setColumn(column);
			t.setRow(row);
		}

		_gameboard[move[0]][move[1]].setMarker(symbol);
	}

	/*
	 * prints out the gameboard to console
	 */
	public void printGameboard() {
		for (int i = 0; i < getFixedSize()[0]; i++) {
			if (i > 0) {
				System.out.println();
			}
			for (int j = 0; j < getFixedSize()[1]; j++) {
				System.out.print(_gameboard[i][j].getBoardCell().getSymbol());
			}
			if (i == getFixedSize()[0] - 1) {
				System.out.println();
			}
		}
	}

	/*
	 * adjusts the column and row numbers specified for an array of proper size to
	 * fit all of the cosmetic components
	 */
	public int[] fixSize(int rows, int columns) {
		int fixedColumns = 3;
		int fixedRows = 3;
		int[] dimensions = new int[2];

		for (int i = 1; i < rows; i++) {
			fixedRows += 2;
		}
		dimensions[0] = fixedRows;

		for (int i = 1; i < columns; i++) {
			fixedColumns += 2;
		}
		dimensions[1] = fixedColumns;

		return dimensions;
	}

	/*
	 * adjusts moves so that their values correspond with a board of the given size
	 * since the array does not perfectly match the values the user gives
	 */
	public int[] fixMove(int row, int column) {
		int fixedColumn = 1;
		int fixedRow = 1;
		int[] dimensions = new int[2];

		for (int i = 1; i < row; i++) {
			fixedRow += 2;
		}
		dimensions[0] = fixedRow;

		for (int i = 1; i < column; i++) {
			fixedColumn += 2;
		}
		dimensions[1] = fixedColumn;

		return dimensions;
	}
}
