package dp.assignment.robbery;

import java.io.IOException;
import java.util.Scanner;

/**
 * Robbery There are n houses built in a line, each of which contains some value
 * in it. A thief is going to steal the maximal value in these houses, but he
 * cannot steal in two adjacent houses because the owner of a stolen house will
 * tell his two neighbors on the left and right side. What is the maximal stolen
 * value?
 * 
 * For example, if there are four houses with values {6, 1, 2, 7}, the maximal
 * stolen value is 13 when the first and fourth houses are stolen.
 * 
 * Solution:
 * http://codercareer.blogspot.com/2013/02/no-44-maximal-stolen-values.html
 * 
 * @author dwaraknathbs
 *
 */
public class Robbery {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int res;

		int _arrHouseValues_size = 0;
		_arrHouseValues_size = Integer.parseInt(in.nextLine().trim());
		int[] _arrHouseValues = new int[_arrHouseValues_size];
		int _arrHouseValues_item;
		for (int _arrHouseValues_i = 0; _arrHouseValues_i < _arrHouseValues_size; _arrHouseValues_i++) {
			_arrHouseValues_item = Integer.parseInt(in.nextLine().trim());
			_arrHouseValues[_arrHouseValues_i] = _arrHouseValues_item;
		}

		res = maxStolenValue(_arrHouseValues);
		System.out.println(String.valueOf(res));
		in.close();

	}

	private static int maxStolenValue(int[] houses) {

		return maxStolenValueIterative(houses);

	}

	/**
	 * Recursive solution- Idea is if I start from ith position, I cannot steal
	 * from i+1. Iteratively passing i+2 to the same method to check for max.
	 * 
	 * @param houses
	 * @param start
	 * @return
	 */
	private static int maxStolenValueRecursive(int[] houses, int start) {
		int max = 0;
		for (int i = start; i < houses.length; i++) {
			int right = (i + 2 < houses.length) ? houses[i] + maxStolenValueRecursive(houses, i + 2) : houses[i];

			max = Math.max(right, max);
		}
		return max;
	}

	/**
	 * Overlapping subproblem, hence caching the max value till i+2 in a cache
	 * so that we can look it up when needed.
	 * 
	 * @param houses
	 * @param start
	 * @param cache
	 * @return
	 */
	private static int maxStolenValueRecursiveCache(int[] houses, int start, int[] cache) {
		if (cache[start] == 0) {
			int max = 0;
			for (int i = start; i < houses.length; i++) {
				int right = (i + 2 < houses.length) ? houses[i] + maxStolenValueRecursiveCache(houses, i + 2, cache)
						: houses[i];
				max = Math.max(right, max);
			}
			cache[start] = max;
		}

		return cache[start];
	}

	/**
	 * Iteratively build cache . Note that the cache should be built from the
	 * end. There is no more houses to steal and build downwards
	 * 
	 * @param houses
	 * @param cache
	 * @return
	 */
	private static int maxStolenValueIterative(int[] houses) {
		int[] cache = new int[houses.length];
		for (int start = houses.length - 1; start >= 0; start--) {
			int max = 0;
			for (int i = start; i < houses.length; i++) {
				int right = (i + 2 < houses.length) ? houses[i] + cache[i + 2] : houses[i];
				max = Math.max(right, max);
			}
			cache[start] = max;
		}

		return cache[0];
	}

}
