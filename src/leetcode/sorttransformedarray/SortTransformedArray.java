package leetcode.sorttransformedarray;

/**
 * 360. Sort Transformed Array Medium
 * 
 * 199
 * 
 * 59
 * 
 * Favorite
 * 
 * Share Given a sorted array of integers nums and integer values a, b and c.
 * Apply a quadratic function of the form f(x) = ax2 + bx + c to each element x
 * in the array.
 * 
 * The returned array must be in sorted order.
 * 
 * Expected time complexity: O(n)
 * 
 * Example 1:
 * 
 * Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5 Output: [3,9,15,33] Example 2:
 * 
 * Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5 Output: [-23,-5,1,7]
 * 
 * @author dwaraknathbs
 *
 */
public class SortTransformedArray {
	public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
		int[] result = new int[nums.length];
		int start = 0;
		int end = nums.length - 1;
		int i = start;
		int j = end;
		while (start <= end) {
			int fromFirst = calc(nums[start], a, b, c);
			int fromLast = calc(nums[end], a, b, c);
			if (a <= 0) {

				if (fromFirst > fromLast) {
					result[i++] = fromLast;
					end--;
				} else {
					result[i++] = fromFirst;
					start++;
				}
			} else {
				if (fromFirst > fromLast) {
					result[j--] = fromFirst;
					start++;
				} else {
					result[j--] = fromLast;
					end--;
				}

			}
		}

		return result;

	}

	public int calc(int num, int a, int b, int c) {
		return a * num * num + b * num + c;
	}

}
