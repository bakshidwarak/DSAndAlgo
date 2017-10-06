package leetcode.twosumbinarysearchtree;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

/**
 * Given a Binary Search Tree and a target number, return true if there exist
 * two elements in the BST such that their sum is equal to the given target.
 * 
 * <pre>
Example 1:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True
Example 2:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False
 * </pre>
 * 
 * @author dwaraknathbs
 *
 */
public class TwoSumBinarySearchTree {
	public boolean findTarget(TreeNode root, int k) {
		List<Integer> inorder = new ArrayList<>();
		inorderTraversal(root, inorder);
		int i = 0;
		int j = inorder.size() - 1;
		for (; i < j;) {
			int sum = inorder.get(i) + inorder.get(j);
			if (sum == k) {
				return true;
			}
			if (sum < k) {
				i++;
			} else {
				j--;
			}
		}
		return false;
	}

	public void inorderTraversal(TreeNode root, List<Integer> inorder) {
		if (root == null)
			return;
		inorderTraversal(root.left, inorder);
		inorder.add(root.val);
		inorderTraversal(root.right, inorder);
	}
}