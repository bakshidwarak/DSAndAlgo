

package leetcode.factorcombinations;

import java.util.ArrayList;
import java.util.List;


/**
 * 254. Factor Combinations Numbers can be regarded as product of its factors.
 * For example, 8 = 2 x 2 x 2; = 2 x 4. Write a function that takes an integer n
 * and return all possible combinations of its factors. Note: You may assume
 * that n is always positive. Factors should be greater than 1 and less than n.
 * Examples:
 * 
 * <pre>
input: 1
output: 
[]
input: 37
output: 
[]
input: 12
output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
input: 32
output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
 * </pre>
 * 
 * @author dwbs
 */
public class Factors {

    public static void main(String[] args) {
        Factors f = new Factors();
        f.getFactors(12);

    }

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> factors = new ArrayList<>();
        List<Integer> currentFactors = new ArrayList<>();
        factorsHelper(n, factors, currentFactors, 2);
        return factors;
    }

    public void factorsHelper(int n, List<List<Integer>> factors, List<Integer> currentFactors, int start) {
        if (n <= 1) {
            if (currentFactors.size() > 1) {
                factors.add(new ArrayList<>(currentFactors));
            }
        }

        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                currentFactors.add(i);
                factorsHelper(n / i, factors, currentFactors, i);
                currentFactors.remove(currentFactors.size() - 1);
            }
        }
    }
}
