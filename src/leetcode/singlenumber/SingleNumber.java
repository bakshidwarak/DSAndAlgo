package leetcode.singlenumber;

/**
 * 
 * 136. Single Number
 * 
 * 
 * Given an array of integers, every element appears twice except for one. Find
 * that single one.
 * 
 * Note: Your algorithm should have a linear runtime complexity. Could you
 * implement it without using extra memory?
 * 
 * Show Company Tags Show Tags Show Similar Problems
 * 
 * @author dwaraknathbs
 *
 */
public class SingleNumber {

	/**
	 * Basic idea is if you xor a number with itself it returns 0. So if we keep
	 * xor the number as we go, the duplicate ones get cancelled out and the
	 * only number that is not duplicate is left out
	 * 
	 * @param nums
	 * @return
	 */
	public int singleNumber(int[] nums) {
		int result = 0;
		for (int i = 0; i < nums.length; i++) {
			result = result ^ nums[i];
		}
		return result;
	}
}
