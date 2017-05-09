package leetcode.movezeroes;

/**
 * 
 * 283. Move Zeroes
 * 
 * 
 * Given an array nums, write a function to move all 0's to the end of it while
 * maintaining the relative order of the non-zero elements.
 * 
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums
 * should be [1, 3, 12, 0, 0].
 * 
 * Note: You must do this in-place without making a copy of the array. Minimize
 * the total number of operations.
 * 
 * @author dwaraknathbs
 *
 */
public class MoveZeroes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * Simple O(n) solution. Keep moving all the non-zero values as we encounter
	 * to the front of the list
	 * 
	 * @param nums
	 */
	public void moveZeroes(int[] nums) {
		int zeroIndex = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				swap(nums, i, zeroIndex);
				zeroIndex++;
			}
		}
	}

	/**
	 * Alternate solution. This is O(n) too but marginally slow. Also a bit
	 * complex to code. Basically have two pointers one for zero and one for non
	 * zero. As and when you find a zero element, find the next non-zero element
	 * and swap them
	 * 
	 * @param nums
	 */
	public void moveZeroesAlternate(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			while (i < nums.length && nums[i] != 0)
				i++;
			int j = i + 1;
			while (j < nums.length && nums[j] == 0)
				j++;
			if (i < nums.length && j < nums.length)
				swap(nums, i, j);
		}
	}

	public void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

}
