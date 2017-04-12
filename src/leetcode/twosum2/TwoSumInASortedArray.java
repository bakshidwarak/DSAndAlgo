package leetcode.twosum2;

/**
 * 
 * 167. Two Sum II - Input array is sorted
 * 
 * Given an array of integers that is already sorted in ascending order, find
 * two numbers such that they add up to a specific target number.
 * 
 * The function twoSum should return indices of the two numbers such that they
 * add up to the target, where index1 must be less than index2. Please note that
 * your returned answers (both index1 and index2) are not zero-based.
 * 
 * You may assume that each input would have exactly one solution and you may
 * not use the same element twice.
 * 
 * Input: numbers={2, 7, 11, 15}, target=9 Output: index1=1, index2=2
 * 
 * Show Company Tags Show Tags Show Similar Problems
 * 
 * @author dwaraknathbs
 *
 */
public class TwoSumInASortedArray {

	public static void main(String[] args) {
		int[] numbers = { 2, 7, 11, 15 };
		int target = 9;
		int[] output = twoSum(numbers, target);

	}

	/**
	 * Classy problem of invariant. Here the key is that the array is sorted. In
	 * which case we start adding an element from the beginning of the array and
	 * from the end of the array. If the total is greater than the target we
	 * know that we need a lesser number hence we decrement the pointer from
	 * right( which will give a lower number). If the sum was lesser than the
	 * number we increase the left pointer ( the next number will be greater)
	 * and we do this till we reach a sum=target
	 * 
	 * @param numbers
	 * @param target
	 * @return
	 */
	public static int[] twoSum(int[] numbers, int target) {

		int i = 0;
		int j = numbers.length - 1;
		while (i < j) {
			if (numbers[i] + numbers[j] == target) {
				return new int[] { i + 1, j + 1 };
			}
			if (numbers[i] + numbers[j] > target) {
				j--;
			} else {
				i++;
			}
		}
		return null;

	}

}
