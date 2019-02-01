package leetcode.sumofsquarenumbers;

/**
 * 633. Sum of Square Numbers Easy
 * 
 * 288
 * 
 * 199
 * 
 * Favorite
 * 
 * Share Given a non-negative integer c, your task is to decide whether there're
 * two integers a and b such that a2 + b2 = c.
 * 
 * Example 1: Input: 5 Output: True Explanation: 1 * 1 + 2 * 2 = 5 Example 2:
 * Input: 3 Output: False
 * 
 * @author dwaraknathbs
 *
 */
public class SumOfSquareNumbers {
	public boolean judgeSquareSum(int c) {
		return helper(0, (int) Math.sqrt(c), c);
	}

	public boolean helper(int start, int end, int num) {
		if (start > end)
			return false;
		int curr = start * start + end * end - num;
		if (curr == 0)
			return true;
		if (curr > 0)
			return helper(start, end - 1, num);
		return helper(start + 1, end, num);

	}
}
