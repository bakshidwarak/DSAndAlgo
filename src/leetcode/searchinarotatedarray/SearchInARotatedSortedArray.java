package leetcode.searchinarotatedarray;

/**
 * 33. Search in Rotated Sorted Array
 * 
 * 
 * Suppose an array sorted in ascending order is rotated at some pivot unknown
 * to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * You are given a target value to search. If found in the array return its
 * index, otherwise return -1.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * @author dwaraknathbs
 *
 */
public class SearchInARotatedSortedArray {

	public static void main(String[] args) {
		int[] nums = { 8, 1, 2, 3, 4, 5, 6, 7 };
		System.out.println(search(nums, 7));

	}

	public static int search(int[] nums, int target) {
		int start = 0;
		int end = nums.length - 1;
		while (start <= end) {
			int mid = start + (end - start) / 2;
			if (nums[mid] == target)
				return mid;
			if (nums[mid] <= nums[end]) {
				if (nums[mid] < target && target <= nums[end]) {
					start = mid + 1;
				} else {
					end = mid - 1;
				}
			} else {
				if (nums[mid] > target && target >= nums[start]) {
					end = mid - 1;
				} else {
					start = mid + 1;
				}
			}
		}

		return -1;

	}

}
