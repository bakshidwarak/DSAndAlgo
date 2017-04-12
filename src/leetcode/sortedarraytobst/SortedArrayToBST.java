package leetcode.sortedarraytobst;

/**
 * 
 * 108. Convert Sorted Array to Binary Search Tree
 * 
 * 
 * 
 * Given an array where elements are sorted in ascending order, convert it to a
 * height balanced BST.
 * 
 * @author dwaraknathbs
 *
 */
public class SortedArrayToBST {

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * Simple template to a recursive function. Have a helper and recurse
	 * 
	 * @param nums
	 * @return
	 */
	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums.length == 0)
			return null;

		return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
	}

	/**
	 * As the array is sorted. The middle will become the root. Left of the root
	 * is the same problem with left array and right is same problem with right
	 * array
	 * 
	 * @param nums
	 * @param start
	 * @param end
	 * @return
	 */
	public TreeNode sortedArrayToBSTHelper(int[] nums, int start, int end) {
		if (start > end)
			return null;

		if (start == end) {
			return new TreeNode(nums[start]);
		}
		int mid = (start + end) / 2;

		TreeNode n = new TreeNode(nums[mid]);
		n.left = sortedArrayToBSTHelper(nums, start, mid - 1);
		n.right = sortedArrayToBSTHelper(nums, mid + 1, end);

		return n;
	}

}
