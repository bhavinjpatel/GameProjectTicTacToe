
public class Board {
	private final int SIZE = 3;
	private Cell[][] cells;


	/**
	 * Constructor with arguments
	 */
	public Board() {
		init();
	}

	/**
	 * init board
	 */
	private void init() {
		this.cells = new Cell[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				this.cells[i][j] = new Cell(i, j, Symbol.EMPTY);
			}
		}
	}
	
	/**
	 * This method clears board
	 */
	public void clearBoard() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				this.cells[i][j].clear();
			}
		}
	}

	/**
	 * This method displays board
	 */
	public void display() {
		System.out.print("   ");
		for (int i = 0; i < SIZE; i++) {
			System.out.print((i + 1) + "   ");
		}
		System.out.println("\n -------------");
		for (int i = 0; i < SIZE; i++) {
			System.out.print((i + 1) + "| ");
			for (int j = 0; j < SIZE; j++) {
				System.out.print(this.cells[i][j].toString() + " | ");
			}
			System.out.println("\n -------------");
		}
	}

	/**
	 * Check winner
	 *
	 * @return
	 */
	public boolean isWinner() {
		// check rows
		for (int j = 0; j < SIZE; j++) {
			if (cells[0][j].equals(cells[1][j]) && cells[0][j].equals(cells[2][j])
					&& (cells[0][j].isCross() || cells[0][j].isNought())) {
				return true;
			}
		}
		// check columns
		for (int i = 0; i < SIZE; i++) {
			if (cells[i][0].equals(cells[i][1]) && cells[i][0].equals(cells[i][2])
					&& (cells[i][0].isCross() || cells[i][0].isNought())) {
				return true;
			}
		}

		// check diagonal \
		if (cells[0][0].equals(cells[1][1]) && cells[0][0].equals(cells[2][2])
				&& (cells[0][0].isCross() || cells[0][0].isNought())) {
			return true;
		}
		// check diagonal /
		if (cells[0][2].equals(cells[1][1]) && cells[0][2].equals(cells[2][0])
				&& (cells[0][2].isCross() || cells[0][2].isNought())) {
			return true;
		}
		return false;
	}

	/**
	 * This function checks if computer can block next move of human or the computer to win immediately.
	 * @param player
	 * @param computer
	 * @return
	 */
	public Cell computerCanBlockOrWinPlayer(Player player,Player computer) {
		// check rows
		for (int j = 0; j < SIZE; j++) {
			if ((cells[0][j].equals(cells[1][j]) && cells[0][j].getSymbol() == player.getSymbol()
					&& cells[2][j].isEmpty())) {
				return cells[2][j];
			}
			if ((cells[0][j].equals(cells[2][j]) && cells[0][j].getSymbol() == player.getSymbol()
					&& cells[1][j].isEmpty())) {
				return cells[1][j];
			}
			if ((cells[1][j].equals(cells[2][j]) && cells[1][j].getSymbol() == player.getSymbol()
					&& cells[0][j].isEmpty())) {
				return cells[0][j];
			}
		}
		// check columns
		for (int j = 0; j < SIZE; j++) {
			if ((cells[j][0].equals(cells[j][1]) && cells[j][0].getSymbol() == player.getSymbol()
					&& cells[j][2].isEmpty())) {
				return cells[j][2];
			}
			if ((cells[j][0].equals(cells[j][2]) && cells[j][0].getSymbol() == player.getSymbol()
					&& cells[j][1].isEmpty())) {
				return cells[j][1];
			}
			if ((cells[j][1].equals(cells[j][2]) && cells[j][1].getSymbol() == player.getSymbol()
					&& cells[j][0].isEmpty())) {
				return cells[j][0];
			}
		}
		// check diagonal \
		if (cells[0][0].equals(cells[1][1])  && cells[0][0].getSymbol() == player.getSymbol() && (cells[2][2].isEmpty())) {
			return cells[2][2];
		}
		if (cells[0][0].equals(cells[2][2])  && cells[0][0].getSymbol() == player.getSymbol() && (cells[1][1].isEmpty())) {
			return cells[1][1];
		}
		if (cells[1][1].equals(cells[2][2])  && cells[1][1].getSymbol() == player.getSymbol() && (cells[0][0].isEmpty())) {
			return cells[0][0];
		}
		// check diagonal /
		if (cells[0][2].equals(cells[1][1]) && cells[0][2].getSymbol() == player.getSymbol() && (cells[2][0].isEmpty())) {
			return cells[2][0];
		}
		if (cells[0][2].equals(cells[2][0]) && cells[0][2].getSymbol() == player.getSymbol() && (cells[1][1].isEmpty())) {
			return cells[1][1];
		}
		if (cells[2][0].equals(cells[1][1]) && cells[2][0].getSymbol() == player.getSymbol() && (cells[0][2].isEmpty())) {
			return cells[0][2];
		}
		return null;
	}

	/**
	 * This function checks if all cells are filled
	 *
	 * @return
	 */
	public boolean isTie() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (this.cells[i][j].isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * This method checks if cell is empty
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean cellIsEmpty(int row, int column) {
		return this.cells[row][column].isEmpty();
	}
	/**
	 * This method fills selected cell by computer or by player
	 * @param selectedCell
	 * @param player
	 */
	public void fillCell(Cell selectedCell,Player player) {
		this.cells[selectedCell.getRow()][selectedCell.getColumn()].setSymbol(player.getSymbol());
	}

}
