package leetcode.totalhammingdistance;

/**
 * The Hamming distance between two integers is the number of positions at which
 * the corresponding bits are different.
 * 
 * Now your job is to find the total Hamming distance between all pairs of the
 * given numbers.
 * 
 * Example: Input: 4, 14, 2
 * 
 * Output: 6
 * 
 * Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is
 * 0010 (just showing the four bits relevant in this case). So the answer will
 * be: HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) =
 * 2 + 2 + 2 = 6. Note: Elements of the given array are in the range of 0 to
 * 10^9 Length of the array will not exceed 10^4.
 * 
 * @author dwaraknathbs
 *
 */
public class TotalHammingDistance {

	public int totalHammingDistance(int[] nums) {

		int[] digits = new int[32];
		for (int i = 0; i < nums.length; i++) {
			int number = nums[i];
			int mask = 1;
			int digit = 0;
			while (digit < 32) {
				int k = number & mask;
				if (k == 1) {
					digits[digit]++;

				}
				number = number >>> 1;
				digit++;
			}

		}

		/**
		 * Key idea is to have an array of size 32 and keep counting the total
		 * number of 1s at each place. The total count can range from 0 to n. If
		 * the total==n or ==0, then the place doesnt contribute to the hamming
		 * distance. If not the total hamming distance =<br>
		 * 
		 * Total Hamming distance= count (at a particular place) * ( n- count)
		 */
		int distance = 0;
		for (int i = 0; i < digits.length; i++) {
			distance += digits[i] * (nums.length - digits[i]);
		}

		return distance;

	}

}
