package leetcode.searchinsertposition;

/**
 * 35. Search Insert Position Easy
 * 
 * 1029
 * 
 * 151
 * 
 * Favorite
 * 
 * Share Given a sorted array and a target value, return the index if the target
 * is found. If not, return the index where it would be if it were inserted in
 * order.
 * 
 * You may assume no duplicates in the array.
 * 
 * Example 1:
 * 
 * Input: [1,3,5,6], 5 Output: 2 Example 2:
 * 
 * Input: [1,3,5,6], 2 Output: 1 Example 3:
 * 
 * Input: [1,3,5,6], 7 Output: 4 Example 4:
 * 
 * Input: [1,3,5,6], 0 Output: 0
 * 
 * @author dwaraknathbs
 *
 */
public class SearchInsertPosition {
	public int searchInsert(int[] nums, int target) {

		if (nums[0] > target)
			return 0;
		if (nums[nums.length - 1] < target)
			return nums.length;

		return helper(nums, 0, nums.length - 1, target);

	}

	public int helper(int[] nums, int start, int end, int target) {
		int mid = (start + end) / 2;
		if (nums[mid] == target) {
			return mid;
		} else if (mid > 0 && nums[mid - 1] < target && nums[mid] > target) {
			return mid;
		} else {
			if (nums[mid] < target) {
				return helper(nums, mid + 1, end, target);
			} else
				return helper(nums, start, mid - 1, target);
		}

	}
}
