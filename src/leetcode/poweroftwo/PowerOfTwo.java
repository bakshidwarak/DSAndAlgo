package leetcode.poweroftwo;

/**
 * 231. Power of Two Easy
 * 
 * 362
 * 
 * 100
 * 
 * Favorite
 * 
 * Share Given an integer, write a function to determine if it is a power of
 * two.
 * 
 * Example 1:
 * 
 * Input: 1 Output: true Explanation: 20 = 1 Example 2:
 * 
 * Input: 16 Output: true Explanation: 24 = 16 Example 3:
 * 
 * Input: 218 Output: false
 * 
 * @author dwaraknathbs
 *
 */
public class PowerOfTwo {
	public boolean isPowerOfTwo(int n) {
		if (n <= 0)
			return false;
		int y = n & ~(n - 1);
		y = y ^ n;

		return y == 0;

	}

}
