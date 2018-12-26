package leetcode.flattenbinarytreetolinkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 114. Flatten Binary Tree to Linked List Medium
 * 
 * 1089
 * 
 * 131
 * 
 * Favorite
 * 
 * Share Given a binary tree, flatten it to a linked list in-place.
 * 
 * For example, given the following tree:
 * 
 * <pre>

    1
   / \
  2   5
 / \   \
3   4   6
 * </pre>
 * 
 * The flattened tree should look like:
 * 
 * <pre>
1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
 * </pre>
 * 
 * @author dwaraknathbs
 *
 */
public class FlattenBinaryTreeToLinkedList {
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			val = x;
		}
	}

	public void flatten(TreeNode root) {
		List<TreeNode> nodes = new ArrayList<>();
		if (root == null)
			return;
		helper(root, nodes);
		TreeNode prev = null;
		for (TreeNode node : nodes) {

			if (prev != null) {
				prev.right = node;
				prev.left = null;
			}
			prev = node;

		}
	}

	public void helper(TreeNode root, List<TreeNode> nodes) {
		if (root == null)
			return;
		nodes.add(root);
		helper(root.left, nodes);
		helper(root.right, nodes);

	}

}
