import java.util.Random;

public class Computer extends Player{
	private Random rand;
	/**
	 * Constructor
	 * @param symbol
	 */
	public Computer(Symbol symbol) {
		super("Computer", symbol,null);
		this.rand = new Random();
	}
	/**
	 * This function allows to select random cell 
	 * @param board
	 * @return
	 */
	@Override
	public Cell selectCell(Board board) {
		int row = -1;
		int column = 0;
		while (row == -1) {
			row = rand.nextInt(3);//generate random row
			column = rand.nextInt(3);//generate random column
			//Check if cell is empty
			if (!board.cellIsEmpty(row, column)) {
				row = -1;
			}
		}
		//return cell
		return new Cell(row, column);
	}
}
