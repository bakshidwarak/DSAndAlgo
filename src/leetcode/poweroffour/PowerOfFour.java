package leetcode.poweroffour;

/**
 * 342. Power of Four Easy
 * 
 * 268
 * 
 * 127
 * 
 * Favorite
 * 
 * Share Given an integer (signed 32 bits), write a function to check whether it
 * is a power of 4.
 * 
 * Example 1:
 * 
 * Input: 16 Output: true Example 2:
 * 
 * Input: 5 Output: false Follow up: Could you solve it without loops/recursion?
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class PowerOfFour {
	public boolean isPowerOfFour(int num) {
		if (num <= 0 || num == 2)
			return false;
		if (num == 1)
			return true;
		int y = num & ~(num - 1);
		int k = y ^ num;
		if (k != 0)
			return false;

		k = 1;
		while (y != 0) {

			y = y >>> 1;
			k++;
		}

		return k % 2 == 0;

	}
}
