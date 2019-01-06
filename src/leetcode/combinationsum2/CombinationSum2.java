package leetcode.combinationsum2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. Combination Sum II Medium
 * 
 * 634
 * 
 * 34
 * 
 * Favorite
 * 
 * Share Given a collection of candidate numbers (candidates) and a target
 * number (target), find all unique combinations in candidates where the
 * candidate numbers sums to target.
 * 
 * Each number in candidates may only be used once in the combination.
 * 
 * Note:
 * 
 * All numbers (including target) will be positive integers. The solution set
 * must not contain duplicate combinations. Example 1:
 * 
 * Input: candidates = [10,1,2,7,6,1,5], target = 8, A solution set is: [ [1,
 * 7], [1, 2, 5], [2, 6], [1, 1, 6] ] Example 2:
 * 
 * Input: candidates = [2,5,2,1,2], target = 5, A solution set is: [ [1,2,2],
 * [5] ]
 * 
 * @author dwaraknathbs
 *
 */
public class CombinationSum2 {
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Arrays.sort(candidates);
		List<Integer> current = new ArrayList<>();
		List<List<Integer>> result = new ArrayList<>();

		helper(candidates, target, 0, current, result);

		return result;

	}

	public void helper(int[] candidates, int target, int index, List<Integer> current, List<List<Integer>> result) {

		if (target < 0) {
			return;
		}

		if (target == 0) {
			result.add(new ArrayList<>(current));
			return;
		}
		if (index == candidates.length)
			return;

		/**
		 * From my current index, I find all the arrays with sum=target-candidates[i]
		 */
		for (int i = index; i < candidates.length; i++) {
			if (i != index && candidates[i] == candidates[i - 1]) {
				continue;
			}
			if (candidates[i] <= target) {
				current.add(candidates[i]);
				helper(candidates, target - candidates[i], i + 1, current, result);
				current.remove(current.size() - 1);
			} else {
				break;
			}

		}

	}
}
