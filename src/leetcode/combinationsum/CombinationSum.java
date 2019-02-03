package leetcode.combinationsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. Combination Sum Medium
 * 
 * 1602
 * 
 * 45
 * 
 * Favorite
 * 
 * Share Given a set of candidate numbers (candidates) (without duplicates) and
 * a target number (target), find all unique combinations in candidates where
 * the candidate numbers sums to target.
 * 
 * The same repeated number may be chosen from candidates unlimited number of
 * times.
 * 
 * Note:
 * 
 * All numbers (including target) will be positive integers. The solution set
 * must not contain duplicate combinations. Example 1:
 * 
 * Input: candidates = [2,3,6,7], target = 7, A solution set is: [ [7], [2,2,3]
 * ] Example 2:
 * 
 * Input: candidates = [2,3,5], target = 8, A solution set is: [ [2,2,2,2],
 * [2,3,3], [3,5] ]
 * 
 * @author dwaraknathbs
 *
 */
public class CombinationSum {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {

		Arrays.sort(candidates);
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> current = new ArrayList<>();

		helper(candidates, 0, target, current, result);

		return result;

	}

	public void helper(int[] nums, int start, int target, List<Integer> current, List<List<Integer>> result) {
		if (target == 0) {
			result.add(new ArrayList<>(current));
			return;
		}
		if (target < 0) {
			return;
		}
		for (int i = start; i < nums.length; i++) {
			if (nums[i] <= target) {
				current.add(nums[i]);
				helper(nums, i, target - nums[i], current, result);
				current.remove(current.size() - 1);
			}

		}
	}

}
