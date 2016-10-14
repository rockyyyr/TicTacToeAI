package ai;

/**
 * GameState. Stores values related to generated game states including board
 * positions, score and depth of the game state
 * @author Rocky Robson - A00914509
 * @version Oct 7, 2016
 */
public class GameState {

	private int[][] board;
	private int score;
	private int depth;

	/**
	 * Constructor
	 * @param board 2D array that represents the board
	 * @param score Score of the game state
	 * @param depth Depth of the game state
	 */
	public GameState(int[][] board, int score, int depth) {
		this.board = board;
		this.score = score;
		this.depth = depth;
	}

	/**
	 * Returns the game state
	 * @return board Game state to be returned
	 */
	public final int[][] getBoard() {
		return board;
	}

	/**
	 * Returns the score of the game state
	 * @return score Score of the game state as an int
	 */
	public final int getScore() {
		return score;
	}

	/**
	 * Returns the depth of the game state
	 * @return depth The depth of the game state as an int
	 */
	public final int getDepth() {
		return depth;
	}

	/**
	 * Sets a new board state for this game state
	 * @param board board to set
	 */
	public final void setBoard(int[][] board) {
		this.board = board;
	}

}
