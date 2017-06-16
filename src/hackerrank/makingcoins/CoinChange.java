

package hackerrank.makingcoins;

import java.util.Scanner;

/**
 * Check out the resources on the page's right side to learn more about dynamic programming. The video tutorial is by Gayle Laakmann McDowell, author of the best-selling interview book Cracking the Coding Interview.
Given a number of dollars, , and a list of dollar values for  distinct coins, , find and print the number of different ways you can make change for  dollars if each coin is available in an infinite quantity.

Hints:

You can solve this problem recursively, but you must optimize your solution to eliminate overlapping subproblems using Dynamic Programming if you wish to pass all test cases. More specifically, think of ways to store the checked solutions and use the stored values to avoid repeatedly calculating the same values.
Think about the degenerate cases: 
How many ways can you make change for  dollars?
How many ways can you make change for less than  dollars if you have no coins?
If you are having trouble defining the storage for your precomputed values, then think about it in terms of the base case .
Input Format

The first line contain two space-separated integers describing the respective values of  and . 
The second line contains  space-separated integers describing the respective values of , where each integer denotes the dollar value of a distinct coin available in an infinite quantity.

Constraints

The list of coins contains  distinct integers where each integer denotes the dollar value of a coin available in an infinite quantity.
Output Format

Print a single integer denoting the number of ways we can make change for  dollars using an infinite supply of our  types of coins.

Sample Input 0

4 3
1 2 3 
Sample Output 0

4
Explanation 0 
For  and  there are four solutions:

Thus, we print  on a new line.

Sample Input 1

10 4
2 5 3 6
Sample Output 1

5
Explanation 1 
For  and  there are five solutions:

Thus, we print  on a new line.
 * @author dwaraknathbs
 *
 */
public class CoinChange {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int coins[] = new int[m];
        for (int coins_i = 0; coins_i < m; coins_i++) {
            coins[coins_i] = in.nextInt();
        }

        System.out.println(countCoins(coins, n));
    }

    static long countCoins(int[] coins, int money) {
        long[][] cache = new long[money + 1][coins.length + 1];
        for (int i = 0; i < cache.length; i++)
            for (int j = 0; j < cache[0].length; j++) {
                cache[i][j] = -1;
            }
        return countCoinsHelper(money, coins, 0, cache);
    }

    static long countCoinsHelper(int money, int[] coins, int index, long[][] cache) {
        if (cache[money][index] == -1) {
            if (money == 0) {
                cache[money][index] = 1;
                return 1;
            }
            if (index >= coins.length) {
                cache[money][index] = 0;
                return 0;
            }
            long numWays = 0;
            int amount = 0;
            while (amount <= money) {
                int amountRemaining = money - amount;
                if (amountRemaining >= 0)
                    numWays += countCoinsHelper(amountRemaining, coins, index + 1, cache);

                amount += coins[index];
            }
            cache[money][index] = numWays;
        }
        return cache[money][index];

    }
}
