package logic;

import java.util.Scanner;

/**
 * InterfacePvP. This class controls the user interface for a 'player vs
 * player' game of Tic Tac Toe.
 * @author Rocky Robson - A00914509
 * @version Oct 5, 2016
 */
public class InterfacePvP {

	private Scanner reader;
	private Board board;
	private boolean p1;
	private int player;

	/**
	 * Default constructor
	 */
	public InterfacePvP() {
		reader = new Scanner(System.in);
		board = new Board();
		p1 = true;
	}
	
	/**
	 * Run the game
	 */
	public void run() {
		print("Welcome to tic tac toe");
		boolean gameOver = false;

		while (!gameOver) {
			board.displayBoard();

			if (p1) {
				player = 1;
				print("Player 1's turn");
			} else {
				player = 2;
				print("Player 2's turn");
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

				if (!board.setMove(x, y, player))
					print("That space is taken. Choose again");
				else {
					if (!board.checkLines()) {
						p1 = !p1;
						proceed = true;
					} else {
						board.displayBoard();
						print("*Player " + player + " wins!*");
						gameOver = true;
					}
				}
			}
		}
	}

	/*
	 * System.out.println shortcut
	 */
	private void print(String string) {
		System.out.println(string);
	}

}
