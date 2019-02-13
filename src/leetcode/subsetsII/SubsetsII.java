package leetcode.subsetsII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. Subsets II Medium
 * 
 * 743
 * 
 * 40
 * 
 * Favorite
 * 
 * Share Given a collection of integers that might contain duplicates, nums,
 * return all possible subsets (the power set).
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * Example:
 * 
 * Input: [1,2,2] Output: [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
 * 
 * @author dwaraknathbs
 *
 */
public class SubsetsII {
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> current = new ArrayList<>();
		helper(nums, 0, current, result);
		return result;
	}

	public void helper(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {

		result.add(new ArrayList<>(current));

		for (int i = index; i < nums.length; i++) {
			if (i == index || nums[i] != nums[i - 1]) {
				current.add(nums[i]);
				helper(nums, i + 1, current, result);
				current.remove(current.size() - 1);
			}
		}

	}

}
