package ai;

import java.util.ArrayList;
import java.util.List;

import logic.Board;

/**
 * AI. A (hopefully) unbeatable tic tac toe bot
 * @author Rocky Robson - A00914509
 * @version Oct 5, 2016
 */
public class AI {

	private List<Integer> results;

	/**
	 * Default constructor.
	 */
	public AI() {
		results = new ArrayList<>();
	}

	/**
	 * Checks to see if a winning move was made
	 * @param current The current game state
	 * @return If the player won, -1 is returned. If the AI won, 1 is returned.
	 *         if there is no winner, 0 is returned;
	 */
	private int getScore(int[][] current) {
		if (Board.checkForWinner(current) == 1)
			return -1;
		if (Board.checkForWinner(current) == 2)
			return 1;
		return 0;
	}

	/**
	 * Switches the current player who's turn it is on the current game states
	 * being generated
	 * @param player Current player
	 * @return The inverse of the current player
	 */
	private int switchPlayer(int player) {
		return player == 1 ? 2 : 1;
	}

	/**
	 * Generate successive game states.
	 * @param current The current board state to build upon
	 * @param player The player who's turn it is when successive game states are
	 *        generated
	 * @return If the game is not over or there is moves to be made a List of
	 *         game states is returned. If there is no moves to be made, no game
	 *         states will be generated so null will be returned
	 */
	private List<int[][]> generateStates(int[][] current, int player) {
		List<int[][]> list = new ArrayList<>();

		for (int i = 0; i < current.length; i++) {
			for (int j = 0; j < current.length; j++) {
				if (current[i][j] == 0) {
					// create a temp copy of current state
					int[][] temp = new int[3][3];
					for (int k = 0; k < temp.length; k++) {
						for (int k2 = 0; k2 < temp.length; k2++) {
							temp[k][k2] = current[k][k2];
						}
					}
					if (player == 1)
						temp[i][j] = 1;
					if (player == 2)
						temp[i][j] = 2;
					list.add(temp);
				}
			}
		}
		return list.isEmpty() ? null : list;
	}

	/**
	 * Determines if the game has ended
	 * @param current Current game state to be tested
	 * @return True if the game is over
	 */
	private boolean gameOver(int[][] current) {
		if (Board.checkForWinner(current) != 0)
			return true;
		return false;
	}

	/**
	 * Analyzes the game states scores and computes which is the best game state
	 * @param states States to be analyzed
	 * @param player The current player
	 * @return The game state with the best score
	 */
	private GameState getResult(List<GameState> states, int player) {
		GameState result = states.get(0);

		if (player == 1) {
			for (int i = 1; i < states.size(); i++) {
				if (states.get(i).getScore() < result.getScore()
						|| states.get(i).getScore() == result.getScore()
								&& states.get(i).getDepth() < result.getDepth()) {
					result = states.get(i);
				}
			}
		} else {
			for (int i = 0; i < states.size(); i++) {
				if (states.get(i).getScore() > result.getScore()
						|| states.get(i).getScore() == result.getScore()
								&& states.get(i).getDepth() < result.getDepth()) {
					result = states.get(i);
				}
			}
		}
		return result;
	}

	/**
	 * Calls generateStates() and records all possible game state scores
	 * @param current Current game state
	 * @param player Current player
	 * @return game state score
	 */
	private GameState minimax(int[][] current, int player, int depth, boolean reset) {
		List<int[][]> generatedStates = generateStates(current, player);

		if (gameOver(current) || generatedStates == null)
			return new GameState(current, getScore(current), depth);

		List<GameState> gameStates = new ArrayList<>();
		for (int i = 0; i < generatedStates.size(); i++)
			gameStates.add(minimax(generatedStates.get(i), switchPlayer(player), depth + 1, true));

		GameState gs = getResult(gameStates, player);

		if (reset)
			gs.setBoard(current);

		return gs;
	}

	/**
	 * Analyzes game state scores and chooses the best move
	 * @param current The current game state
	 * @param player The current player
	 * @return The game state with computers best move added
	 */
	public int[][] move(int[][] current, int player) {
		return minimax(current, player, 0, false).getBoard();
	}

}
