package leetcode.validtrianglenumber;

import java.util.Arrays;

/**
 * 611. Valid Triangle Number Medium
 * 
 * 481
 * 
 * 61
 * 
 * Favorite
 * 
 * Share Given an array consists of non-negative integers, your task is to count
 * the number of triplets chosen from the array that can make triangles if we
 * take them as side lengths of a triangle. Example 1: Input: [2,2,3,4] Output:
 * 3 Explanation: Valid combinations are: 2,3,4 (using the first 2) 2,3,4 (using
 * the second 2) 2,2,3 Note: The length of the given array won't exceed 1000.
 * The integers in the given array are in the range of [0, 1000].
 * 
 * @author dwaraknathbs
 *
 */
public class ValidTriangleNumber {
	public int triangleNumber(int[] nums) {
		Arrays.sort(nums);

		int count = 0;

		for (int i = 0; i < nums.length - 2; i++) {
			if (nums[i] == 0)
				continue;
			int j = i + 1;
			int k = i + 2;
			while (j < nums.length - 1) {

				if (nums[j] == 0) {
					j++;
					continue;
				}
				while (k < nums.length && nums[i] + nums[j] > nums[k]) {

					k++;

				}
				count += k - j - 1;
				j++;

			}
		}
		return count;

	}
}
