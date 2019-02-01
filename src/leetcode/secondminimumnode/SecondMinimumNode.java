package leetcode.secondminimumnode;

/**
 * 671. Second Minimum Node In a Binary Tree Easy
 * 
 * 323
 * 
 * 482
 * 
 * Favorite
 * 
 * Share Given a non-empty special binary tree consisting of nodes with the
 * non-negative value, where each node in this tree has exactly two or zero
 * sub-node. If the node has two sub-nodes, then this node's value is the
 * smaller value among its two sub-nodes.
 * 
 * Given such a binary tree, you need to output the second minimum value in the
 * set made of all the nodes' value in the whole tree.
 * 
 * If no such second minimum value exists, output -1 instead.
 * 
 * <pre>
Example 1:
Input: 
    2
   / \
  2   5
     / \
    5   7
 * </pre>
 * 
 * Output: 5 Explanation: The smallest value is 2, the second smallest value is
 * 5. Example 2: Input:
 * 
 * <pre>
    2
   / \
  2   2
 * </pre>
 * 
 * Output: -1 Explanation: The smallest value is 2, but there isn't any second
 * smallest value.
 * 
 * @author dwaraknathbs
 *
 */
public class SecondMinimumNode {
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public int findSecondMinimumValue(TreeNode root) {

		if (root == null)
			return -1;

		if (root.left == null)
			return -1;

		if (root.left.val > root.val && root.left.val <= root.right.val) {
			return root.left.val;
		} else if (root.right.val > root.val && root.right.val < root.left.val) {
			return root.right.val;
		} else {
			int secondMin = Math.min(getMinimum(root.left, root.val), getMinimum(root.right, root.val));
			if (secondMin == Integer.MAX_VALUE)
				return -1;
			else
				return secondMin;
		}
	}

	public int getMinimum(TreeNode root, int val) {
		if (root.val > val)
			return root.val;
		if (root.left == null)
			return Integer.MAX_VALUE;
		return Math.min(getMinimum(root.left, root.val), getMinimum(root.right, root.val));
	}
}
