package leetcode.kthsmallestinabst;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth
 * smallest element in it.
 * 
 * Note: You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * 
 * Follow up: What if the BST is modified (insert/delete operations) often and
 * you need to find the kth smallest frequently? How would you optimize the
 * kthSmallest routine?
 * 
 * @author dwaraknathbs
 *
 */
public class KthSmallestInABST {

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public int kthSmallest(TreeNode root, int k) {
		List<Integer> nums = new ArrayList<>();
		kthSmallestHelper(root, nums);
		return nums.get(k - 1);

	}

	public void kthSmallestHelper(TreeNode root, List<Integer> nums) {

		if (root.left != null) {
			kthSmallestHelper(root.left, nums);
		}
		nums.add(root.val);

		if (root.right != null)
			kthSmallestHelper(root.right, nums);

	}
}