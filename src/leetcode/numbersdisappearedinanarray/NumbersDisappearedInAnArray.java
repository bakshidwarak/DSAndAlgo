package leetcode.numbersdisappearedinanarray;

import java.util.ArrayList;
import java.util.List;

/**
 * 448. Find All Numbers Disappeared in an Array
 * 
 * 
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some
 * elements appear twice and others appear once.
 * 
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 * 
 * Could you do it without extra space and in O(n) runtime? You may assume the
 * returned list does not count as extra space.
 * 
 * Example:
 * 
 * Input: [4,3,2,7,8,2,3,1]
 * 
 * Output: [5,6]
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class NumbersDisappearedInAnArray {

	public static void main(String[] args) {
		int[] nums={4,3,2,7,8,2,3,1};
		findDisappearedNumbers(nums).forEach(System.out::println);

	}

	/**
	 * Pattern: Whenever there is a problem with a fixed range defined, we can
	 * accomplish O(n) by marking the elements by doing a simple arithmetic
	 * operation. In this case as we see the index we go an make the element at
	 * that index negative. After we complete one round, we again sweep the list
	 * to look for indices which are positive and those are the indices that are
	 * missing in the array
	 * 
	 * @param nums
	 * @return
	 */
	public static List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> result = new ArrayList<>();
		for (int num : nums) {
			int val = Math.abs(num) - 1;
			if (nums[val] > 0) {
				nums[val] = -nums[val];
			}
		}

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) {
				result.add(i + 1);
			}
		}

		return result;
	}
}
