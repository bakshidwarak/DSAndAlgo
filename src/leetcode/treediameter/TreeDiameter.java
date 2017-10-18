package leetcode.treediameter;

/**
 * Given a binary tree, you need to compute the length of the diameter of the
 * tree. The diameter of a binary tree is the length of the longest path between
 * any two nodes in a tree. This path may or may not pass through the root.
 * 
 * Example: Given a binary tree
 * 
 * <pre>
          1
         / \
        2   3
       / \     
      4   5
 * </pre>
 * 
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * 
 * Note: The length of path between two nodes is represented by the number of
 * edges between them.
 * 
 * @author dwaraknathbs
 *
 */
public class TreeDiameter {
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public int diameterOfBinaryTree(TreeNode root) {
		int mydepth = getDepth(root);
		int ldia = diameterOfBinaryTree(root.left);
		int rdia = diameterOfBinaryTree(root.right);

		return Math.max(mydepth, ldia + rdia);
	}

	public int getDepth(TreeNode root) {
		if (root == null)
			return 0;

		int maxDepth = Math.max(getDepth(root.left), getDepth(root.right));
		return maxDepth + 1;
	}
}
