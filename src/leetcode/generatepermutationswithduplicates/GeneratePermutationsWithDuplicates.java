package leetcode.generatepermutationswithduplicates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Given a collection of numbers that might contain duplicates, return all
 * possible unique permutations.
 * 
 * For example, [1,1,2] have the following unique permutations: [ [1,1,2],
 * [1,2,1], [2,1,1] ] Show Company Tags Show Tags Show Similar Problems
 * 
 * @author dwaraknathbs
 *
 */
public class GeneratePermutationsWithDuplicates {

	public static void main(String[] args) {
		GeneratePermutationsWithDuplicates perm= new GeneratePermutationsWithDuplicates();
		int[] nums={1,1,2,3,4};
		perm.permuteUnique(nums).forEach(System.out::println);;

	}

	public List<List<Integer>> permuteUnique(int[] nums) {

		List<List<Integer>> permutations = new ArrayList<>();
		generatePermutations(nums, 0, permutations);
		return new ArrayList<>(permutations);
	}

	public void generatePermutations(int[] nums, int start, List<List<Integer>> permutations) {

		if (start == nums.length) {
			ArrayList<Integer> current = new ArrayList<>();
			for (int i = 0; i < nums.length; i++) {
				current.add(nums[i]);

			}
			permutations.add(current);
			return;
		}

		/**
		 * Here the input array can contain duplicates which is key. If we do
		 * usual generate permutations we might end up having duplicate
		 * permutations. Hence we have a hashset to track the number that is
		 * currently being chosen as start number.In the case of a duplicate
		 * number we will avoid the rework
		 */
		HashSet<Integer> startElements = new HashSet<Integer>();

		for (int i = start; i < nums.length; i++) {
			if (!startElements.contains(nums[i])) {
				swap(nums, i, start);
				generatePermutations(nums, start + 1, permutations);
				swap(nums, start, i);
				startElements.add(nums[i]);
			}
		}

	}

	public void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

}
