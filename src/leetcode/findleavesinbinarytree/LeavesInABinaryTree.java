package leetcode.findleavesinbinarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 366. Find Leaves of Binary Tree
 * 
 * 
 * Given a binary tree, collect a tree's nodes as if you were doing this:
 * Collect and remove all leaves, repeat until the tree is empty.
 * 
 * <pre>
Example:
Given binary tree 
          1
         / \
        2   3
       / \     
      4   5    
Returns [4, 5, 3], [2], [1].

Explanation:
1. Removing the leaves [4, 5, 3] would result in this tree:

          1
         / 
        2          
2. Now removing the leaf [2] would result in this tree:

          1          
3. Now removing the leaf [1] would result in the empty tree:

          []         
Returns [4, 5, 3], [2], [1].
 * </pre>
 * 
 * @author dwaraknathbs
 *
 */
public class LeavesInABinaryTree {

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

	public List<List<Integer>> findLeaves(TreeNode root) {

		List<List<Integer>> output = new ArrayList<>();
		TreeNode temp = root;

		while (temp != null) {
			List<Integer> currentLeaf = new ArrayList<>();
			if (temp.left == null && temp.right == null) {
				currentLeaf.add(temp.val);
				temp = null;
			} else {
				dfs(temp, null, currentLeaf);
			}
			output.add(currentLeaf);
		}
		return output;
	}

	public void dfs(TreeNode node, TreeNode parent, List<Integer> currentLeaf) {
		if (node == null)
			return;
		if (node.left == null && node.right == null) {
			currentLeaf.add(node.val);
			if (parent != null) {
				if (parent.left == node)
					parent.left = null;
				if (parent.right == node)
					parent.right = null;
			}
			return;
		}
		dfs(node.left, node, currentLeaf);
		dfs(node.right, node, currentLeaf);
	}
}
