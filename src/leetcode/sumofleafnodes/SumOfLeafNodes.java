package leetcode.sumofleafnodes;

/**
 * Find the sum of all left leaves in a given binary tree.
 * 
 * <pre>
Example:

    3
   / \
  9  20
    /  \
   15   7
 * 
 * </pre>
 * 
 * There are two left leaves in the binary tree, with values 9 and 15
 * respectively. Return 24.
 * 
 * @author dwaraknathbs
 *
 */
public class SumOfLeafNodes {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	class Solution {
		public int sumOfLeftLeaves(TreeNode root) {

			if (root == null)
				return 0;

			return sumOfLeftLeaves(root.left, true) + sumOfLeftLeaves(root.right, false);

		}

		public int sumOfLeftLeaves(TreeNode root, boolean isLeft) {

			if (root == null)
				return 0;

			if (root.left == null && root.right == null && isLeft) {
				return root.val;
			}

			return sumOfLeftLeaves(root.left, true) + sumOfLeftLeaves(root.right, false);

		}
	}

}
