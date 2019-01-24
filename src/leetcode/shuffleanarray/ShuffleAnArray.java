package leetcode.shuffleanarray;

import java.util.HashSet;

/**
 * 384. Shuffle an Array Medium
 * 
 * 205
 * 
 * 513
 * 
 * Favorite
 * 
 * Share Shuffle a set of numbers without duplicates.
 * 
 * Example:
 * 
 * // Init an array with set 1, 2, and 3. int[] nums = {1,2,3}; Solution
 * solution = new Solution(nums);
 * 
 * // Shuffle the array [1,2,3] and return its result. Any permutation of
 * [1,2,3] must equally likely to be returned. solution.shuffle();
 * 
 * // Resets the array back to its original configuration [1,2,3].
 * solution.reset();
 * 
 * // Returns the random shuffling of array [1,2,3]. solution.shuffle();
 * Accepted 65.1K Submissions 132.4K
 * 
 * @author dwaraknathbs
 *
 */
public class ShuffleAnArray {

	int[] nums;
	int[] permutation;
	HashSet<Integer> taken = new HashSet<>();

	public ShuffleAnArray(int[] nums) {
		this.nums = nums;
		this.permutation = nums;
	}

	/** Resets the array to its original configuration and return it. */
	public int[] reset() {
		permutation = nums;
		taken.clear();
		return permutation;
	}

	/** Returns a random shuffling of the array. */
	public int[] shuffle() {
		int[] result = new int[nums.length];
		for (int i = 0; i < permutation.length;) {
			int rand = (int) (Math.random() * (permutation.length - 0)) + 0;
			if (taken.contains(rand))
				continue;
			result[i++] = permutation[rand];
			taken.add(rand);
		}
		taken.clear();
		permutation = result;
		return result;
	}

}
