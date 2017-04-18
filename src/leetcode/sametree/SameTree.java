package leetcode.sametree;

/**
 * 
 * 100. Same Tree
 * 
 * Given two binary trees, write a function to check if they are equal or not.
 * 
 * Two binary trees are considered equal if they are structurally identical and
 * the nodes have the same value.
 * 
 * @author dwaraknathbs
 *
 */
public class SameTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public boolean isSameTree(TreeNode p, TreeNode q) {

		if (p == q)
			return true;

		if (p == null || q == null)
			return false;

		return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

	}

}
