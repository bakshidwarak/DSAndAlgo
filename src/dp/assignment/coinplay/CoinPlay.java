package dp.assignment.coinplay;

import java.io.IOException;
import java.util.Scanner;

/**
 * Coin play Consider a row of n coins of values v1 . . . vn, where n is even.
 * We play a game against an opponent by alternating turns. In each turn, a
 * player selects either the first or last coin from the row, removes it from
 * the row permanently, and receives the value of the coin. Determine the
 * maximum possible amount of money we can definitely win if we move first.
 * 
 * Assume full competency by both players.
 * 
 * Example: Coins given: 8, 15, 3, 7.
 * 
 * Player 1 can start two different ways: either picking 8 or picking 7.
 * Depending on the choice s/he makes, the end reward will be different. We want
 * to find the maximum reward the first player can collect.
 * 
 * 1. …….Player-1 chooses 8. …….Opponent chooses 15. …….Player-1 chooses 7.
 * …….Opponent chooses 3. Total value collected by Player-1 is 15(8 + 7) (Greedy
 * strategy i.e. pick the highest at every step)
 * 
 * 2. …….Player-1 chooses 7. …….Opponent chooses 8. …….Player-2 chooses 15.
 * …….Opponent chooses 3. Total value collected by Player-1 is 22(7 + 15)
 * 
 * Given these two strategies, we want 22 as the answer, and not 15.
 * 
 * Solution:
 * http://www.geeksforgeeks.org/dynamic-programming-set-31-optimal-strategy-for-a-game
 * 
 * @author dwaraknathbs
 *
 */
public class CoinPlay {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);

		int res;

		int _intCoins_size = 0;
		_intCoins_size = Integer.parseInt(in.nextLine().trim());
		int[] _intCoins = new int[_intCoins_size];
		int _intCoins_item;
		for (int _intCoins_i = 0; _intCoins_i < _intCoins_size; _intCoins_i++) {
			_intCoins_item = Integer.parseInt(in.nextLine().trim());
			_intCoins[_intCoins_i] = _intCoins_item;
		}

		res = maxWin(_intCoins);
		System.out.println(String.valueOf(res));

	}

	private static int maxWin(int[] coins) {
		// TODO Auto-generated method stub
		int[][] cache = new int[coins.length][coins.length];
		for (int i = 0; i < cache.length; i++)
			for (int j = 0; j < coins.length; j++)
				cache[i][j] = 0;
		// return maxValueHelper(coins, 0, coins.length - 1);
		// maxValueHelperWithCache(coins, 0, coins.length - 1, cache);

		// return cache[0][coins.length-1];
		maxValueHelperIterative(coins, cache);

		for (int i = 0; i < cache.length; i++) {
			for (int j = 0; j < coins.length; j++)
				System.out.print(cache[i][j] + " ");
			System.out.println();
		}
		return cache[0][coins.length - 1];

	}

	/**
	 * This is a recursive solution. The contract is if I choose a coin, the
	 * opponent will choose a coin that will result in min value for me
	 * 
	 * <pre>
	 * If I choose first coin - My max value is coinValue + Min(maxValueFrom(start+2 to end)- if the player 2 chooses the first coin in the remaining array
	 *                                        				Min(maxValueFrom(start+1,end -1)- if the player 2 chooses the last coin in the remaining array
	 *                                        
	 * If I choose last coin - My max value is coinValue + 	Min(maxValueFrom(start+1 to end-1)- if the player 2 chooses the first coin in the remaining array
	 * 													 	Min(maxValueFrom(start to end-2)- if the player 2 chooses the l coin in the remaining array
	 * </pre>
	 * 
	 * @param coins
	 * @param start
	 * @param end
	 * @return
	 */
	private static int maxValueHelperRecursive(int[] coins, int start, int end) {
		if (start == end)
			return coins[start];
		if (end - start == 1) {
			return Math.max(coins[start], coins[end]);
		}
		int startChoice = coins[start];

		startChoice += Math.min(maxValueHelperRecursive(coins, start + 2, end),
				maxValueHelperRecursive(coins, start + 1, end - 1));

		int endChoice = coins[end];

		endChoice += Math.min(maxValueHelperRecursive(coins, start + 1, end - 1),
				maxValueHelperRecursive(coins, start, end - 2));

		if (startChoice > endChoice) {

			return startChoice;
		}

		return endChoice;
	}

	/**
	 * Clearly there are repeated subproblems and it heps to build a cache to
	 * store the previously run results
	 * 
	 * @param coins
	 * @param start
	 * @param end
	 * @param cache
	 * @return
	 */
	private static int maxValueHelperWithCache(int[] coins, int start, int end, int[][] cache) {
		if (cache[start][end] == -1) {
			if (start == end)
				cache[start][end] = coins[start];
			else if (end - start == 1) {
				cache[start][end] = Math.max(coins[start], coins[end]);
			} else {
				int startChoice = coins[start];

				startChoice += Math.min(maxValueHelperWithCache(coins, start + 2, end, cache),
						maxValueHelperWithCache(coins, start + 1, end - 1, cache));

				int endChoice = coins[end];

				endChoice += Math.min(maxValueHelperWithCache(coins, start + 1, end - 1, cache),
						maxValueHelperWithCache(coins, start, end - 2, cache));

				if (startChoice > endChoice) {

					cache[start][end] = startChoice;
				} else

					cache[start][end] = endChoice;
			}
		}
		return cache[start][end];
	}

	/**
	 * Knowing the structure of the cache, it is better to build it than calling
	 * it recursively. Note that the right most diagonal top element gives the
	 * max value. We focus on building the cache row wise
	 * 
	 * @param coins
	 * @param cache
	 * @return
	 */
	private static int maxValueHelperIterative(int[] coins, int[][] cache) {
		for (int i = 0; i < coins.length; i++)
			for (int start = 0, end = i; end < coins.length; end++, start++) {

				if (end - start == 1) {
					cache[start][end] = Math.max(coins[start], coins[end]);
				} else {
					int startChoice = coins[start];

					int y = start + 1 < coins.length && end - 1 >= 0 ? cache[start + 1][end - 1] : 0;

					int x = start + 2 < coins.length ? cache[start + 2][end] : 0;
					startChoice += Math.min(x, y);

					int endChoice = coins[end];

					int z = end - 2 >= 0 ? cache[start][end - 2] : 0;
					endChoice += Math.min(y, z);

					if (startChoice > endChoice) {

						cache[start][end] = startChoice;
					} else

						cache[start][end] = endChoice;
				}
			}
		return cache[0][coins.length - 1];
	}

}
