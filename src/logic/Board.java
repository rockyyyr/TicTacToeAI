package logic;

/**
 * Board. Creates a Tic Tac Toe game board and provides methods for displaying
 * the board, setting moves and checking for winners
 * @author Rocky Robson - A00914509
 * @version Oct 5, 2016
 */
public class Board {

	private static final int SIZE = 3;
	private int[][] board;

	/**
	 * Default constructor
	 */
	public Board() {
		 board = new int[SIZE][SIZE];
	}

	/**
	 * Returns the game board
	 * @return The game board as an 2D int array
	 */
	public int[][] getBoard() {
		return this.board;
	}

	/**
	 * Sets a new board replacing all current board values
	 * @param board The new board to be set
	 */
	public void setBoard(int[][] board) {
		this.board = board;
	}

	/**
	 * Initializes the board reseting all values to 0
	 */
	public void initializeBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++)
				board[i][j] = 0;
		}
	}

	/**
	 * Displays a graphical representation of the game board to the console
	 */
	public void displayBoard() {
		print("");
		print(xo(0, 0) + " | " + xo(0, 1) + " | " + xo(0, 2));
		print("------------");
		print(xo(1, 0) + " | " + xo(1, 1) + " | " + xo(1, 2));
		print("------------");
		print(xo(2, 0) + " | " + xo(2, 1) + " | " + xo(2, 2));
		print("");
	}

	/**
	 * Sets a specified space on the game board for a specified player
	 * @param x The specified x index
	 * @param y The specified y index
	 * @param player The specified player
	 * @return True if and only if an empty space was chosen and the move was
	 *         legal. False if the specified space is not empty
	 */
	public boolean setMove(int x, int y, int player) {
		if (board[x][y] == 0) {
			board[x][y] = player;
			return true;
		}
		return false;
	}

	/**
	 * Determines if a winning move was made by check rows, columns and
	 * diagonals for equality, equaling 1 or 2
	 * @param current The current game state to check for a winner
	 * @return 1 if player 1 is the winner. 2 if player 2. If no winning move
	 *         was made, 0 is returned
	 */
	public static int checkForWinner(int[][] current) {
		// check diagonal
		if (current[0][0] == 1 && current[0][0] == current[1][1] && current[1][1] == current[2][2]
				|| current[0][2] == 1 && current[0][2] == current[1][1] && current[1][1] == current[2][0])
			return 1;

		if (current[0][0] == 2 && current[0][0] == current[1][1] && current[1][1] == current[2][2]
				|| current[0][2] == 2 && current[0][2] == current[1][1] && current[1][1] == current[2][0])
			return 2;

		// check rows/cols
		for (int i = 0; i < current.length; i++) {
			if (current[0][i] == 1 && current[0][i] == current[1][i] && current[1][i] == current[2][i]
					|| current[i][0] == 1 && current[i][0] == current[i][1] && current[i][1] == current[i][2])
				return 1;

			if (current[0][i] == 2 && current[0][i] == current[1][i] && current[1][i] == current[2][i]
					|| current[i][0] == 2 && current[i][0] == current[i][1] && current[i][1] == current[i][2])
				return 2;
		}
		return 0;
	}

	/**
	 * Checks rows, columns and diagonals for equality not equal to zero to
	 * determine if a winning move was made.
	 * @return True if there was a winning move made, otherwise false
	 */
	public boolean checkLines() {

		if (board[0][0] != 0 && board[0][0] == board[0][1] && board[0][1] == board[0][2])
			return true;
		if (board[1][0] != 0 && board[1][0] == board[1][1] && board[1][1] == board[1][2])
			return true;
		if (board[2][0] != 0 && board[2][0] == board[2][1] && board[2][1] == board[2][2])
			return true;

		if (board[0][0] != 0 && board[0][0] == board[1][0] && board[1][0] == board[2][0])
			return true;
		if (board[0][1] != 0 && board[0][1] == board[1][1] && board[1][1] == board[2][1])
			return true;
		if (board[0][2] != 0 && board[0][2] == board[1][2] && board[1][2] == board[2][2])
			return true;

		// Diagonals
		if (board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2])
			return true;
		if (board[0][2] != 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0])
			return true;

		return false;
	}

	/**
	 * Returns either an X, O, or empty space depending on the values in the
	 * board array
	 * @param x The x index to be checked
	 * @param y The y index to be checked
	 * @return " ", "X" or "O" if the index value equals 0, 1 or 2 respectively
	 */
	private String xo(int x, int y) {
		switch (board[x][y]) {
		case 0:
			return "  ";
		case 1:
			return "X";
		case 2:
			return "O";
		}
		return null;
	}

	/**
	 * Display a string to the console
	 * @param string String to be displayed
	 */
	private void print(String string) {
		System.out.println(string);
	}

	public static void displayBoard(int[][] current) {
		System.out.println("");
		System.out.println(xo(current, 0, 0) + " | " + xo(current, 0, 1) + " | " + xo(current, 0, 2));
		System.out.println("------------");
		System.out.println(xo(current, 1, 0) + " | " + xo(current, 1, 1) + " | " + xo(current, 1, 2));
		System.out.println("------------");
		System.out.println(xo(current, 2, 0) + " | " + xo(current, 2, 1) + " | " + xo(current, 2, 2));
		System.out.println("");
	}

	private static String xo(int[][] current, int x, int y) {
		switch (current[x][y]) {
		case 0:
			return "  ";
		case 1:
			return "X";
		case 2:
			return "O";
		}
		return null;
	}

}
