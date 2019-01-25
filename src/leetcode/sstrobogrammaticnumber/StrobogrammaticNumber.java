package leetcode.sstrobogrammaticnumber;

/**
 * 246. Strobogrammatic Number Easy
 * 
 * 104
 * 
 * 237
 * 
 * Favorite
 * 
 * Share A strobogrammatic number is a number that looks the same when rotated
 * 180 degrees (looked at upside down).
 * 
 * Write a function to determine if a number is strobogrammatic. The number is
 * represented as a string.
 * 
 * Example 1:
 * 
 * Input: "69" Output: true Example 2:
 * 
 * Input: "88" Output: true Example 3:
 * 
 * Input: "962" Output: false
 * 
 * @author dwaraknathbs
 *
 */
public class StrobogrammaticNumber {

	public boolean isStrobogrammatic(String num) {

		int[] subs = new int[] { 0, 1, -1, -1, -1, -1, 9, -1, 8, 6 };

		int start = 0;
		int end = num.length() - 1;

		while (start <= end) {
			int curr = Integer.parseInt(num.substring(start, start + 1));
			int endNum = Integer.parseInt(num.substring(end, end + 1));
			if (curr != subs[endNum])
				return false;
			start++;
			end--;

		}
		return true;

	}

}
