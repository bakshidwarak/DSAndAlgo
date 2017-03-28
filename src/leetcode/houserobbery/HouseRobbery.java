package leetcode.houserobbery;

/**
 * 198. House Robber You are a professional robber planning to rob houses along
 * a street. Each house has a certain amount of money stashed, the only
 * constraint stopping you from robbing each of them is that adjacent houses
 * have security system connected and it will automatically contact the police
 * if two adjacent houses were broken into on the same night. Given a list of
 * non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting
 * the police.
 */
public class HouseRobbery {
	public int rob(int[] nums) {
		int cache[] = new int[nums.length + 2];
		cache[nums.length] = 0;
		cache[nums.length + 1] = 0;

		for (int i = nums.length - 1; i >= 0; i--) {
			int gain_with_current = nums[i] + cache[i + 2];
			int gain_without_current = cache[i + 1];
			cache[i] = Math.max(gain_with_current, gain_without_current);

		}
		return cache[0];
	}

}
