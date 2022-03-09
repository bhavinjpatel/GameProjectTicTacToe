import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	private Board board;
	private Scanner keyBoard;
	private int totalGames;// total number of games
	private int totalTies;// total number of ties
	private List<Player> players;

	/**
	 * Constructor
	 */
	public Game() {
		this.keyBoard = new Scanner(System.in);
		this.board = new Board();
		this.players = new ArrayList<Player>();
		this.readPlayersFromFile();
	}

	/**
	 * This method displays main menu
	 */
	public void displayMenu() {
		String choice = "";
		while (choice.compareTo("4") != 0) {
			System.out.println("1. Play two persons");
			System.out.println("2. Play against computer");
			System.out.println("3. Display statistics");
			System.out.println("4. Exit");
			System.out.print("Your choice: ");
			choice = keyBoard.nextLine();
			switch (choice) {
			case "1":
				playTwoPerson();
				break;
			case "2":
				playAgainstComputer();
				break;
			case "3":
				displayStatistics();
				break;
			case "4":
				// exit
				break;
			default:
				break;
			}
			this.board.clearBoard();

		}
		keyBoard.close();
	}

	/**
	 * This method allows to play two person game mode
	 */
	private void playTwoPerson() {
		System.out.print("Ente name for player 1: ");
		String name = keyBoard.nextLine();
		Player player1 = getPlayerByName(name);
		if (player1 == null) {
			player1 = new Player(name, Symbol.CROSS, keyBoard);
			players.add(player1);
		} else {
			player1.setSymbol(Symbol.CROSS);
		}
		System.out.print("Ente name for player 2: ");
		name = keyBoard.nextLine();
		Player player2 = getPlayerByName(name);
		if (player2 == null) {
			player2 = new Player(name, Symbol.NOUGHT, keyBoard);
			players.add(player2);
		} else {
			player2.setSymbol(Symbol.NOUGHT);
		}
		
		boolean isPlaying = true;
		while (isPlaying) {
			Cell selectedCell;
			this.board.display();
			System.out.println("\n" + player1.getName() + " is playing now...");
			selectedCell = player1.selectCell(board);
			this.board.fillCell(selectedCell, player1);
			if (this.board.isWinner()) {
				this.board.display();
				System.out.println("\n" + player1.getName() + " is winner.\n");
				isPlaying = false;
				player1.incrementWins();
				player2.incrementLosses();
			} else if (this.board.isTie()) {
				this.board.display();
				System.out.println("\nTie.\n");
				isPlaying = false;
				this.totalTies++;
			} else {
				this.board.display();
				System.out.println("\n" + player2.getName() + " is playing now...");
				selectedCell = player1.selectCell(board);
				this.board.fillCell(selectedCell, player2);
				if (this.board.isWinner()) {
					this.board.display();
					System.out.println("\n" + player2.getName() + " is winner.\n");
					isPlaying = false;
					player2.incrementWins();
					player1.incrementLosses();
				} else if (this.board.isTie()) {
					this.board.display();
					System.out.println("\nTie.\n");
					isPlaying = false;
					this.totalTies++;
				}
			}
		}
		keyBoard.nextLine();
		this.totalGames++;
		savePlayersToFile();
	}

	/**
	 * This method allows to play player against computer
	 */
	private void playAgainstComputer() {
		System.out.print("Ente player name: ");
		String name = keyBoard.nextLine();
		String answer = "";
		while (answer.compareToIgnoreCase("y") != 0 && answer.compareToIgnoreCase("n") != 0) {
			System.out.print("Do you want to play first as \"X\" player ?(y/n): ");
			answer = keyBoard.nextLine();
		}
		Player player1 = getPlayerByName(name);
		Player player2 = getPlayerByName("Computer");
		if (answer.compareToIgnoreCase("y") == 0) {
			if (player1 == null) {
				player1 = new Player(name, Symbol.CROSS, keyBoard);
				players.add(player1);
			} else {
				player1.setSymbol(Symbol.CROSS);
			}
			if (player2 == null) {
				player2 = new Computer(Symbol.NOUGHT);
				players.add(player2);
			} else {
				player2.setSymbol(Symbol.NOUGHT);
			}

		} else {
			if (player1 == null) {
				player1 = new Player(name, Symbol.NOUGHT, keyBoard);
				players.add(player1);
			} else {
				player1.setSymbol(Symbol.NOUGHT);
			}
			if (player2 == null) {
				player2 = new Computer(Symbol.CROSS);
				players.add(player2);
			} else {
				player2.setSymbol(Symbol.CROSS);
			}
		}

		boolean isPlaying = true;
		while (isPlaying) {
			Cell selectedCell;
			if (player1.getSymbol() == Symbol.CROSS) {
				this.board.display();
				System.out.println("\n" + player1.getName() + " is playing now...");
				selectedCell = player1.selectCell(board);
				this.board.fillCell(selectedCell, player1);
			}
			if (this.board.isWinner()) {
				this.board.display();
				System.out.println("\n" + player1.getName() + " is winner.\n");
				isPlaying = false;
				player1.incrementWins();
				player2.incrementLosses();
			} else if (this.board.isTie()) {
				this.board.display();
				System.out.println("\nTie.\n");
				isPlaying = false;
				this.totalTies++;
			} else {
				System.out.println("\n" + player2.getName() + " is playing now ...");
				// check if computer can win immediately
				selectedCell = board.computerCanBlockOrWinPlayer(player2, player2);
				// check if the computer can block next move of player.
				if (selectedCell == null) {
					selectedCell = board.computerCanBlockOrWinPlayer(player1, player2);
					// select random cell1
					if (selectedCell == null) {
						selectedCell = player2.selectCell(board);
					}
				}
				System.out.println(player2.getName() + " has selected cell with coordinate ("
						+ (selectedCell.getRow() + 1) + "," + (selectedCell.getColumn() + 1) + ")");
				this.board.fillCell(selectedCell, player2);
				if (this.board.isWinner()) {
					this.board.display();
					System.out.println("\n" + player2.getName() + " is winner.\n");
					isPlaying = false;
					player2.incrementWins();
					player1.incrementLosses();
				} else if (this.board.isTie()) {
					this.board.display();
					System.out.println("\nTie.\n");
					isPlaying = false;
					this.totalTies++;
				} else {
					if (player1.getSymbol() == Symbol.NOUGHT) {
						this.board.display();
						System.out.println("\n" + player1.getName() + " is playing now...");
						selectedCell = player1.selectCell(board);
						this.board.fillCell(selectedCell, player1);
					}
				}
			}
		}
		keyBoard.nextLine();
		this.totalGames++;
		savePlayersToFile();
	}

	/**
	 * This function allows to search player in list using name
	 * 
	 * @param name
	 * @return
	 */
	private Player getPlayerByName(String name) {
		for (int i = 0; i < this.players.size(); i++) {
			if (players.get(i).getName().compareTo(name) == 0) {
				return players.get(i);
			}
		}
		return null;
	}

	/**
	 * This method displays statistics
	 */
	public void displayStatistics() {
		System.out.println("\nTotal number of games: " + this.totalGames);
		System.out.println("Total number of ties: " + this.totalTies);
		System.out.println("\nPlayers:");
		System.out.printf("%-20s%-20s%-20s\n", "Name", "# wins", "# losses");
		for (int i = 0; i < this.players.size(); i++) {
			System.out.printf("%-20s%-20d%-20d\n", players.get(i).getName(), players.get(i).getWins(),
					players.get(i).getLosses());
		}
	}

	/**
	 * This method allows to save players to file players.txt
	 */
	private void savePlayersToFile() {
		try {
			FileWriter fileWriter = new FileWriter("players.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.printf("%d,%d\n",totalGames,totalTies);
			for (int i = 0; i < this.players.size(); i++) {
				printWriter.printf("%s,%d,%d\n", players.get(i).getName(), players.get(i).getWins(),
						players.get(i).getLosses());
			}
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method reads all players from file
	 */
	private void readPlayersFromFile() {
		File file = new File("players.txt");
		if (file.exists()) {
			players.clear();
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
				String line= bufferedReader.readLine();
				String tokens[] = line.split(",");
				//read total games from file
				this.totalGames=Integer.parseInt(tokens[0]);
				//read total ties from file
				this.totalTies=Integer.parseInt(tokens[1]);
				while ((line = bufferedReader.readLine()) != null) {
					tokens = line.split(",");
					Player newPlayer=null;
					if(tokens[0].compareTo("Computer")==0) {
						newPlayer=new Computer(Symbol.CROSS); 
					}else {
						newPlayer=new Player(tokens[0],Symbol.NOUGHT,keyBoard);
					}
					newPlayer.setWins(Integer.parseInt(tokens[1]));
					newPlayer.setLosses(Integer.parseInt(tokens[2]));
					players.add(newPlayer);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
