package graphs.assignment.snakesandladder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Snakes and Ladders Matrix Given a snake and ladder rectangular MxN
 * board-game, find the minimum number of dice throws required to reach the
 * final cell from the 1st cell.
 * 
 * Rules are as usual: If after a dice-throw, the player reaches a cell where
 * the ladder starts, the player has to climb up that ladder and if the player
 * reaches a cell that has the mouth of the snake, s/he has to go down to the
 * tail of snake.
 * 
 * For example, in the board given below, it will take minimum 4 throws to reach
 * from 1 to 36. That can be done with the following sequence of throws:
 * (1,6,4,1). There may be more such sequences of the same length viz. (4,2,6,4)
 * etc.
 * 
 * 
 * 
 * Please hard-code input game boards as your test-cases. There are different
 * ways of doing so. e.g. one simple way, is to represent it using a
 * one-dimensional array of length MxN, with each element representing a cell.
 * Values in the array, are the destination cell id for snakes (lower numbers)
 * and ladders (higher numbers).
 * 
 * Solution: http://www.geeksforgeeks.org/snake-ladder-problem-2/
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class SnakeAndLadder {

	static class Pair {
		int index;
		ArrayList<Integer> pathSoFar = new ArrayList<>();

		public Pair(int index, ArrayList<Integer> visitedIndex) {
			super();
			this.index = index;
			pathSoFar = visitedIndex;
		}

	}

	public static void main(String[] args) {
		/**
		 * The key to the problem is the representation. 
		 */
		int[] ladderboard = { 0, 1, 2, 22, 4, 8, 6, 7, 8, 9, 10, 26, 12, 13, 14, 15, 16, 4, 18, 7, 29, 9, 22, 23, 24,
				25, 26, 1, 28, 29, 30, 0 };
		ArrayList<Integer> diceThrows = getMinDicThrows(1, 30, ladderboard);
		diceThrows.stream().forEach(System.out::print);

	}

	/**
	 * It can be percieved as an unweighted directed graph and hence a BFS will give the shortest path.
	 * @param start
	 * @param end
	 * @param ladderboard
	 * @return
	 */
	private static ArrayList<Integer> getMinDicThrows(int start, int end, int[] ladderboard) {
		Queue<Pair> queue = new LinkedList<>();
		boolean[] visited = new boolean[ladderboard.length];
		ArrayList<Integer> startPath = new ArrayList<>();

		queue.add(new Pair(start, startPath));

		while (!queue.isEmpty()) {
			Pair current = queue.remove();
			System.out.println(" At index" + current.index);
			ArrayList<Integer> pathSoFar = current.pathSoFar;
			if (visited[current.index] == true) {
				continue;
			}
			if (current.index == end) {

				return pathSoFar;
			}
			visited[current.index] = true;

			for (int i = current.index + 1; i <= (current.index + 6) && i <= end; i++) {
				ArrayList<Integer> path = new ArrayList<>(pathSoFar);
				path.add(i - current.index);
				queue.add(new Pair(ladderboard[i], path));
			}
		}
		return null;
	}

}
