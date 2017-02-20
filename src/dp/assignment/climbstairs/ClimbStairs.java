package dp.assignment.climbstairs;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Count ways to reach the n’th stair (This is simple. Make me proud)
 * 
 * There are n stairs, a person standing at the bottom wants to reach the top.
 * The person can climb either 1 stair or 2 stairs at a time. Count the number
 * of ways, the person can reach the top.
 * 
 * Input: n = 1 Output: 1 There is only one way to climb 1 stair
 * 
 * Input: n = 2 Output: 2 There are two ways: (1, 1) and (2)
 * 
 * Input: n = 4 Output: 5 (1, 1, 1, 1), (1, 1, 2), (2, 1, 1), (1, 2, 1), (2, 2)
 * 
 * Solve the problem for general case i.e. For N stairs, and different kinds of
 * steps that can be taken (e.g. instead of only 1 or 2 steps, it could be 2, 3
 * and 5 steps at a time)
 * 
 * Source and Solution: http://www.geeksforgeeks.org/count-ways-reach-nth-stair/
 * 
 * @author dwaraknathbs
 *
 */
public class ClimbStairs {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);

		int res;

		int _numSteps_size = 0;
		_numSteps_size = Integer.parseInt(in.nextLine().trim());
		int[] _numSteps = new int[_numSteps_size];
		int _numSteps_item;
		for (int _numSteps_i = 0; _numSteps_i < _numSteps_size; _numSteps_i++) {
			_numSteps_item = Integer.parseInt(in.nextLine().trim());
			_numSteps[_numSteps_i] = _numSteps_item;
		}

		int _numStairs;
		_numStairs = Integer.parseInt(in.nextLine().trim());

		res = numWaysToClimb(_numSteps, _numStairs);
		System.out.println(String.valueOf(res));

	}

	static int numWaysToClimb(int[] numSteps, int numStairs) {
		int[] cache = new int[numStairs + 1];
		Arrays.fill(cache, -1);
		numWaysToClimbIterative(numSteps, numStairs, cache);
		return cache[numStairs];

	}

	/**
	 * Number of ways to reach stair N is the sum of the number of ways to reach
	 * stair N-1, N-2.. and N-k
	 * 
	 * @param _numSteps
	 * @param _numStairs
	 * @return
	 */
	private static int numWaysToClimbRecursive(int[] _numSteps, int _numStairs) {
		if (_numStairs <= 0)
			return 0;

		int count = 0;
		for (int i = 0; i < _numSteps.length && _numSteps[i] <= _numStairs; i++) {
			if (_numStairs == _numSteps[i])
				count += 1;
			count += numWaysToClimbRecursive(_numSteps, _numStairs - _numSteps[i]);
		}
		return count;
	}

	/**
	 * Avoiding solving the problems already soved by storing the previous
	 * results in the cache
	 * 
	 * @param _numSteps
	 * @param _numStairs
	 * @param cache
	 * @return
	 */
	private static int numWaysToClimbRecursiveCache(int[] _numSteps, int _numStairs, int[] cache) {
		if (cache[_numStairs] == -1) {
			if (_numStairs <= 0)
				cache[_numStairs] = 0;

			int count = 0;
			for (int i = 0; i < _numSteps.length && _numSteps[i] <= _numStairs; i++) {
				if (_numStairs == _numSteps[i])
					count += 1;
				count += numWaysToClimbRecursive(_numSteps, _numStairs - _numSteps[i]);
			}
			cache[_numStairs] = count;
		}
		return cache[_numStairs];
	}

	/**
	 * The cache can be build bottom up
	 * 
	 * @param _numSteps
	 * @param _numStairs
	 * @param cache
	 * @return
	 */
	private static int numWaysToClimbIterative(int[] _numSteps, int _numStairs, int[] cache) {
		for (int stairs = 0; stairs <= _numStairs; stairs++) {
			int count = 0;
			for (int i = 0; i < _numSteps.length && _numSteps[i] <= stairs; i++) {

				if (stairs == _numSteps[i])
					count += 1;
				count += cache[stairs - _numSteps[i]];
			}
			cache[stairs] = count;
		}
		return cache[_numStairs];
	}
}
