package dp.assignment.coinchange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Making Change
 * 
 * You are given n types of coin denominations of values v(1) < v(2) < ... <
 * v(n) (all integers). Give an algorithm which makes change for an amount of
 * money C with as few coins as possible.
 * 
 * Assume there are multiple coins of every denomination. Assume v(1) = 1, (i.e.
 * there is always a combination that leads to C). There may be multiple ways of
 * reaching C. We want a DP based solution that leads to the method using least
 * number of coins. Input: C and Denominations Array Output: Combination using
 * minimum number of coins (repeat coins ok) that leads to C. Print any one.
 * 
 * For extra credit (read: agony): There may be multiple such combinations.
 * Print all such combinations. Hint: You'll need to do recursion on the DP
 * table.
 * 
 * e.g. Input: Denominations: 1,2,3 C: 4 Output on two lines: 1,3 2,2
 * 
 * Note that test-case output is for the extra-credit case. If you're not doing
 * that (at first, you should ignore extra credit), then read the output
 * appropriately.
 * 
 * @author dwaraknathbs
 *
 */
public class CoinChange {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numberofCoins = scanner.nextInt();
		int[] coins = new int[numberofCoins];
		for (int i = 0; i < numberofCoins; i++)
			coins[i] = scanner.nextInt();

		System.out.println("Total currency value");
		int currency = scanner.nextInt();

		getMinCoins(coins, currency);
		scanner.close();

	}

	/**
	 * Key idea is to build the cache. The min coins required to build currency
	 * X is 1+ the min of the coins required to build X-coins[i]. As and when we
	 * find a min we keep adding the coins chosen in a list
	 * 
	 * @param coins
	 * @param currency
	 * @return
	 */
	private static Integer getMinCoins(int[] coins, int currency) {

		int[] minWays = new int[currency + 1];
		List<Integer>[] coinIndices = new List[currency + 1];

		for (int current = 1; current <= currency; current++) {

			int minVal = Integer.MAX_VALUE - 1;
			int coin = 0;
			if (coinIndices[current] == null) {
				coinIndices[current] = new ArrayList<>();
			}
			for (int i = 0; i < coins.length; i++) {
				if (coins[i] == current) {
					minVal = 1;
					coinIndices[current].add(i);
				} else {
					if (current - coins[i] > 0) {
						int currentMin = 1 + minWays[current - coins[i]];
						if (currentMin <= minVal) {
							minVal = currentMin;
							coinIndices[current].add(i);
						}
					}

				}

			}
			minWays[current] = minVal;

		}

		print(coinIndices, currency, minWays, coins);

		return minWays[currency];

	}

	/**
	 * Recursive routine to print the currency denominations
	 * 
	 * @param coinIndices
	 * @param currency
	 * @param minWays
	 * @param coins
	 */
	private static void print(List<Integer>[] coinIndices, int currency, int[] minWays, int[] coins) {

		Stack<List<Integer>> currencyList = new Stack<>();
		List<Integer> current = new ArrayList<>();
		print(coinIndices, currency, currencyList, current, coins);
		currencyList.forEach(e -> System.out.println(e.stream().map(String::valueOf).collect(Collectors.joining(","))));

	}

	private static void print(List<Integer>[] coinIndices, int currency, Stack<List<Integer>> currencyList,
			List<Integer> current, int[] coins) {
		if (currency <= 0) {
			ArrayList<Integer> temp = new ArrayList<>(current);
			/**
			 * As I push the outcome in the stack I check for the length of the
			 * stack top. If it is greater than the temps length I keep popping
			 * out till the length is = and then push the current combination in
			 * the stack.
			 */
			if (!currencyList.isEmpty()) {
				List tempList = currencyList.peek();
				while (tempList.size() > temp.size() && !currencyList.isEmpty()) {
					currencyList.pop();
					if (!currencyList.isEmpty())
						tempList = currencyList.peek();
				}
			}
			Collections.sort(temp);
			if (currencyList.contains(temp))
				return;
			currencyList.push(temp);

		} else {
			List<Integer> list = coinIndices[currency];
			for (int index : list) {
				/**
				 * Simple back tracking. I add my current choice before
				 * delegating to a version of myself. Once the work is done I
				 * remove the element I added
				 */
				current.add(coins[index]);
				print(coinIndices, currency - coins[index], currencyList, current, coins);
				current.remove(current.size() - 1);
			}
		}

	}

}
