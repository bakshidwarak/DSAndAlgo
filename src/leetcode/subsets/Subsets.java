package leetcode.subsets;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. Subsets Medium
 * 
 * 1577
 * 
 * 40
 * 
 * Favorite
 * 
 * Share Given a set of distinct integers, nums, return all possible subsets
 * (the power set).
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * Example:
 * 
 * Input: nums = [1,2,3] Output: [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2],
 * [] ]
 * 
 * @author dwaraknathbs
 *
 */
public class Subsets {
	public List<List<Integer>> subsets(int[] nums) {
		// Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> current = new ArrayList<>();
		helper(nums, 0, current, result);
		return result;
	}

	public void helper(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {

		result.add(new ArrayList<>(current));

		for (int i = index; i < nums.length; i++) {
			current.add(nums[i]);
			helper(nums, i + 1, current, result);
			current.remove(current.size() - 1);
		}

	}

}
