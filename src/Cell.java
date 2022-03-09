
public class Cell {
	private Symbol symbol;
	private int row;
	private int column;
	/**
	 * Constructor
	 * @param row
	 * @param column
	 */
	public Cell(int row, int column) {
		this.row = row;
		this.column = column;
		this.symbol = Symbol.EMPTY;
	}
	/**
	 * Constructor
	 * @param row
	 * @param column
	 * @param symbol
	 */
	public Cell(int row, int column, Symbol symbol) {
		this.row = row;
		this.column = column;
		this.symbol = symbol;
	}
	/**
	 * This function compares two cells
	 */
	@Override
	public boolean equals(Object obj) {
		return (this.symbol==((Cell)obj).symbol);	
	}
	/**
	 * This function checks if cell is empty
	 * @return
	 */
	public boolean isEmpty() {
		return (this.symbol==Symbol.EMPTY);
	}
	/**
	 * This function checks if cell is cross
	 * @return
	 */
	public boolean isCross() {
		return (this.symbol==Symbol.CROSS);
	}
	/**
	 * This function checks if cell is nought
	 * @return
	 */
	public boolean isNought() {
		return (this.symbol==Symbol.NOUGHT);
	}
	/***
	 * This method clears cell
	 */
	public void clear() {
		this.symbol=Symbol.EMPTY;
	}
	/**
	 * This method sets cross
	 */
	public void setCross() {
		this.symbol=Symbol.CROSS;
	}
	/**
	 * This method sets nought
	 */
	public void setNought() {
		this.symbol=Symbol.NOUGHT;
	}
	/**
	 * This function returns symbol as string value
	 */
	@Override
	public String toString() {
		if(this.symbol==Symbol.CROSS) {
			return "X";
		}
		if(this.symbol==Symbol.NOUGHT) {
			return "O";
		}
		return " ";
	}
	/**
	 * This function returns symbol
	 * @return
	 */
	public Symbol getSymbol() {
		return this.symbol;
	}
	/**
	 * This method sets symbol
	 * @param symbol
	 */
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}
	/**
	 * This function returns row
	 * @return
	 */
	public int getRow() {
		return this.row;
	}
	/**
	 * This method sets row
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * This function returns column
	 * @return
	 */
	public int getColumn() {
		return this.column;
	}
	/**
	 * This method sets column
	 * @param column
	 */
	public void setColumn(int column) {
		this.column = column;
	}

}
