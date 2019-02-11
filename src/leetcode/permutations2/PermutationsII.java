package leetcode.permutations2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 47. Permutations II Medium
 * 
 * 812
 * 
 * 40
 * 
 * Favorite
 * 
 * Share Given a collection of numbers that might contain duplicates, return all
 * possible unique permutations.
 * 
 * Example:
 * 
 * Input: [1,1,2] Output: [ [1,1,2], [1,2,1], [2,1,1] ]
 * 
 * @author dwaraknathbs
 *
 */
public class PermutationsII {
	public List<List<Integer>> permuteUnique(int[] nums) {
		Arrays.sort(nums);
		int[] current = new int[nums.length];
		List<List<Integer>> result = new ArrayList<>();
		Set<Integer> taken = new HashSet<>();
		helper(nums, 0, current, result, taken);
		return result;

	}

	public void helper(int[] nums, int index, int[] current, List<List<Integer>> result, Set<Integer> taken) {
		if (index == nums.length) {
			List<Integer> ans = new ArrayList<>();
			for (int num : current) {
				ans.add(num);
			}
			result.add(ans);
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (taken.contains(i)) {
				continue;
			}
			if (i > 0 && nums[i - 1] == nums[i] && !taken.contains(i - 1))
				continue;

			current[index] = nums[i];
			taken.add(i);
			helper(nums, index + 1, current, result, taken);
			taken.remove(i);
		}
	}

}
