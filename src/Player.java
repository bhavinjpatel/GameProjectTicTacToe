
import java.util.Scanner;

public class Player{
	private String name;
	private Symbol symbol; 
	private int wins;//total number of wins
	private int losses;//total number of losses
	private Scanner keyBoard;
	
	/***
	 * Constructor
	 */
	public Player() {
		
	}
	/**
	 * Constructor
	 * @param name
	 * @param symbol
	 * @param keyBoard
	 */
	public Player(String name,Symbol symbol,Scanner keyBoard) {
		this.name=name;
		this.symbol=symbol;
		this.keyBoard=keyBoard;	
	}
	/**
	 * This methods increment number of wins
	 */
	public void incrementWins() {
		this.wins++;
	}
	/**
	 * This methods increment number of losses
	 */
	public void incrementLosses() {
		this.losses++;
	}

	/**
	 * This function returns name
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * This method sets name 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * This function returns symbol for player
	 * @return
	 */
	public Symbol getSymbol() {
		return symbol;
	}
	/**
	 * This method sets symbol for player
	 * @param symbol
	 */
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}
	/**
	 *  This function allows to select cell 
	 */
	public Cell selectCell(Board board) {
		int row = -1;
		int column = 0;
		while (row == -1) {
			//select row
			row = readIntegerValue("Select row [1-3]: ");
			//select column
			column = readIntegerValue("Select column [1-3]: ");
			//check if cell is empty
			if (!board.cellIsEmpty(row, column)) {
				row = -1;
				System.out.println("\nThat cell is already taken. Try again!\n");
			}
		}
		return new Cell(row, column);
	}
	/**
	 * This function checks if user has entered correct value
	 * 
	 * @param message
	 * @return
	 */
	private int readIntegerValue(String message) {
		int value = -1;
		while (value < 0 || value > 2) {
			try {

				System.out.print(message);
				value = keyBoard.nextInt();
				value--;
			} catch (Exception ex) {
				value = -1;
				keyBoard.next();
				System.out.println("\nError: select correct value!\n");
			}
		}
		return value;
	}
	/***
	 * This function returns number of wins
	 * @return
	 */
	public int getWins() {
		return this.wins;
	}
	/**
	 * This method sets number of wins
	 * @param wins
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}
	/**
	 * This function returns number of losses
	 * @return
	 */
	public int getLosses() {
		return this.losses;
	}
	/**
	 *  This method sets number of losses
	 * @param loses
	 */
	public void setLosses(int losses) {
		this.losses = losses;
	}
}
