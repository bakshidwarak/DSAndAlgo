package leetcode.removeduplicates;

/**
 *
 * 26. Remove Duplicates from Sorted Array
 * 
 * 
 * Given a sorted array, remove the duplicates in place such that each element
 * appear only once and return the new length.
 * 
 * Do not allocate extra space for another array, you must do this in place with
 * constant memory.
 * 
 * For example, Given input array nums = [1,1,2],
 * 
 * Your function should return length = 2, with the first two elements of nums
 * being 1 and 2 respectively. It doesn't matter what you leave beyond the new
 * length.
 * 
 * Show Company Tags Show Tags Show Similar Problems
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class RemoveDuplicatesInASortedArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * Two pointer technique.
	 * 
	 * Have one pointer to traverse the array and have another to maintain the
	 * index if the duplicates were removed
	 * 
	 * When I find an element not same as the previous element, I replace the
	 * Jth element and increment j
	 * 
	 * Finally the value of J gives the length of the array without duplicates
	 * 
	 * Key Idea- I dont really need to swap elements or remove it, all I do is
	 * virtually keep replacing duplicate elements such that all unnecesary
	 * elements are at the end of the array
	 * 
	 * @param nums
	 * @return
	 */
	public int removeDuplicates(int[] nums) {

		int i = 1;
		int j = 1;
		while (i < nums.length) {
			if (nums[i] != nums[i - 1]) {
				nums[j] = nums[i];
				j++;
			}
			i++;
		}
		return j;
	}

}
