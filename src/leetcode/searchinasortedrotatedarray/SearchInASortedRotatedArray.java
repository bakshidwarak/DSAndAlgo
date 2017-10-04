

package leetcode.searchinasortedrotatedarray;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown
 * to you beforehand. (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2). You are
 * given a target value to search. If found in the array return its index,
 * otherwise return -1. You may assume no duplicate exists in the array.
 * 
 * @author dwaraknathbs
 */
public class SearchInASortedRotatedArray {

    public int search(int[] nums, int target) {

        return searchHelper(nums, target, 0, nums.length - 1);

    }

    public int searchHelper(int[] nums, int target, int low, int high) {

        if (low > high)
            return -1;

        int mid = (low + high) / 2;

        if (nums[mid] == target)
            return mid;

        /**
         * No rotation check like a normal binary search
         */
        if (nums[mid] >= nums[low] && nums[mid] <= nums[high]) {// No rotation
            if (target < nums[mid]) {
                return searchHelper(nums, target, low, mid - 1);
            } else {
                return searchHelper(nums, target, mid + 1, high);
            }
        }
        /**
         * Pvot is on the right , which means if the target lies to the left go left else go right
         */
        if (nums[mid] >= nums[low] && nums[mid] >= nums[high]) {// Pivot on
                                                                // right
            if (target >= nums[low] && target <= nums[mid]) {
                return searchHelper(nums, target, low, mid - 1);
            } else {
                return searchHelper(nums, target, mid + 1, high);
            }
        }
        /**
         * Pivot is on the left, which means if target on right go right, else left
         */
        if (nums[mid] <= nums[low] && nums[mid] <= nums[high]) {// Pivot on left
            if (target <= nums[high] && target > nums[mid]) {
                return searchHelper(nums, target, mid + 1, high);
            } else {
                return searchHelper(nums, target, low, mid - 1);
            }

        }
        return -1;
    }

}
