package leetcode.leafsimilartrees;

import java.util.ArrayList;
import java.util.List;

/**
 * 872. Leaf-Similar Trees Easy
 * 
 * 284
 * 
 * 15
 * 
 * Favorite
 * 
 * Share Consider all the leaves of a binary tree. From left to right order, the
 * values of those leaves form a leaf value sequence.
 * 
 * 
 * 
 * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9,
 * 8).
 * 
 * Two binary trees are considered leaf-similar if their leaf value sequence is
 * the same.
 * 
 * Return true if and only if the two given trees with head nodes root1 and
 * root2 are leaf-similar.
 * 
 * 
 * 
 * Note:
 * 
 * Both of the given trees will have between 1 and 100 nodes.
 * 
 * @author dwaraknathbs
 *
 */
public class LeafSimilarTrees {
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public boolean leafSimilar(TreeNode root1, TreeNode root2) {
		List<Integer> seq1 = new ArrayList<>();
		List<Integer> seq2 = new ArrayList<>();
		dfs(root1, seq1);
		dfs(root2, seq2);

		if (seq1.size() != seq2.size())
			return false;
		for (int i = 0; i < seq1.size(); i++) {
			if (seq1.get(i) != seq2.get(i))
				return false;
		}

		return true;
	}

	public void dfs(TreeNode root, List<Integer> seq) {
		if (root == null)
			return;
		if (root.left == null && root.right == null) {
			seq.add(root.val);
			return;
		}

		dfs(root.left, seq);
		dfs(root.right, seq);

	}
}
