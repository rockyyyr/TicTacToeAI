package logic;

import java.util.Scanner;

import ai.AI;

/**
 * InterfacePvC.
 * @author Rocky Robson - A00914509
 * @version Oct 5, 2016
 */
public class InterfacePvC {

	private Scanner reader;
	private Board board;
	private boolean p1;
	private AI ai;

	private int playerWins;
	private int aiWins;
	private int draws;
	private int moves;

	/**
	 * Constructor
	 */
	public InterfacePvC() {
		reader = new Scanner(System.in);
		board = new Board();
		ai = new AI();
		p1 = true;
	}

	/**
	 * Runs the game welcoming user to the game and prompting for input
	 */
	public void run() {
		resetGame();
		print("Welcome to tic tac toe");
		System.out.format("Player wins: %d\nComputer wins: %d\nDraws: %d", playerWins, aiWins, draws);
		while (true) {
			board.displayBoard();

			if (p1) {
				print("Player 1's turn");
			}

			boolean proceed = false;
			while (!proceed) {
				int x = 0;
				int y = 0;

				boolean validEntry = false;
				while (!validEntry) {
					String command = reader.nextLine();
					if (command.length() != 2)
						print("Choose a valid space (Must be two digits)");
					else {
						x = Integer.parseInt(command.substring(0, 1));
						y = Integer.parseInt(command.substring(1));
						validEntry = true;
					}
				}

				if (!board.setMove(x, y, 1))
					print("That space is taken. Choose again");
				else {
					moves++;
					if (!board.checkLines()) {
						p1 = !p1;
						board.setBoard(ai.move(board.getBoard(), 2));
						moves++;
						if (!board.checkLines()) {
							p1 = !p1;
							proceed = true;
						} else {
							board.displayBoard();
							print("Computer wins!");
							aiWins++;
							playAgain();
						}
					} else {
						board.displayBoard();
						print("Player 1 wins!");
						playerWins++;
						playAgain();
					}
					if (moves > 9) {
						board.displayBoard();
						print("Its a draw!");
						draws++;
						playAgain();
					}
				}
			}
		}
	}

	/**
	 * Asks the user to play another game. If yes, the game is reset and run()
	 * is called. If no, the application exits
	 */
	private void playAgain() {
		print("Play again? y or n");
		String response = reader.nextLine();
		switch (response) {
		case "y":
			resetGame();
			run();
			break;
		case "n":
			print("GoodBye");
			System.exit(0);
			break;
		default:
			print("Enter y or n");
			playAgain();
		}
	}
	
	/**
	 * Resets the game variables to starting values
	 */
	private void resetGame() {
		moves = 0;
		board.initializeBoard();
		p1 = true;
	}

	/*
	 * System.out.println shortcut
	 */
	private void print(String string) {
		System.out.println(string);
	}

}
