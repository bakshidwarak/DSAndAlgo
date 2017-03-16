package leetcode.depthofabinarytree;

/**
 * 104. Maximum Depth of Binary Tree
 * 
 * Given a binary tree, find its maximum depth.
 * 
 * The maximum depth is the number of nodes along the longest path from the root
 * node down to the farthest leaf node.
 * 
 * @author dwaraknathbs
 *
 */
public class DepthOfABinaryTree {

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(1);
		TreeNode node3 = new TreeNode(1);
		TreeNode node4 = new TreeNode(1);
		TreeNode node5 = new TreeNode(1);
		TreeNode node6 = new TreeNode(1);

		TreeNode node7 = new TreeNode(1);
		TreeNode node8 = new TreeNode(1);

		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		node4.left = node6;
		node5.left = node7;
		node5.right = node8;

		System.out.println(maxDepth(node1));

	}

	public static int maxDepth(TreeNode root) {

		if (root == null)
			return 0;

		return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));

	}

}
