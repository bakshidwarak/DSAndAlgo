package leetcode.houserobber2;

import java.util.Arrays;

/**
 * 213. House Robber II Medium
 * 
 * 669
 * 
 * 19
 * 
 * Favorite
 * 
 * Share You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed. All houses at this place
 * are arranged in a circle. That means the first house is the neighbor of the
 * last one. Meanwhile, adjacent houses have security system connected and it
 * will automatically contact the police if two adjacent houses were broken into
 * on the same night.
 * 
 * Given a list of non-negative integers representing the amount of money of
 * each house, determine the maximum amount of money you can rob tonight without
 * alerting the police.
 * 
 * Example 1:
 * 
 * Input: [2,3,2] Output: 3 Explanation: You cannot rob house 1 (money = 2) and
 * then rob house 3 (money = 2), because they are adjacent houses. Example 2:
 * 
 * Input: [1,2,3,1] Output: 4 Explanation: Rob house 1 (money = 1) and then rob
 * house 3 (money = 3). Total amount you can rob = 1 + 3 = 4.
 * 
 * @author dwaraknathbs
 *
 */
public class HouseRobber2 {
	public int rob(int[] nums) {
		if (nums.length == 0)
			return 0;
		if (nums.length == 1)
			return nums[0];
		if (nums.length == 2)
			return Math.max(nums[0], nums[1]);

		int[][] result = new int[3][nums.length + 1];
		for (int i = 0; i < 3; i++)
			Arrays.fill(result[i], -1);

		/**
		 * Key idea- Think of the solution as max of two arrags. One starting
		 * from 0 with length l-2(exclude the last element). One which starts
		 * with index 1 and includes the last element. DP Requires a 2D space
		 */
		return Math.max(helper(nums, 1, result, nums.length - 1), helper(nums, 0, result, nums.length - 2));

	}

	/**
	 * 
	 * @param nums
	 * @param index
	 * @param result
	 * @param maxLength
	 * @return
	 */
	public int helper(int[] nums, int index, int[][] result, int maxLength) {
		int xindex = nums.length % maxLength;
		if (result[xindex][index] == -1) {
			if (index > maxLength) {
				result[xindex][index] = 0;
			} else if (index == maxLength) {
				result[xindex][index] = nums[index];
			} else {
				int gain = nums[index] + helper(nums, index + 2, result, maxLength);
				int gainWithOutCurrent = helper(nums, index + 1, result, maxLength);

				result[xindex][index] = Math.max(gain, gainWithOutCurrent);

			}
		}

		return result[xindex][index];

	}
}
