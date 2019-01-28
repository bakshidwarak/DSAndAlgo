package leetcode.modeinabst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 501. Find Mode in Binary Search Tree Easy
 * 
 * 492
 * 
 * 181
 * 
 * Favorite
 * 
 * Share Given a binary search tree (BST) with duplicates, find all the mode(s)
 * (the most frequently occurred element) in the given BST.
 * 
 * Assume a BST is defined as follows:
 * 
 * The left subtree of a node contains only nodes with keys less than or equal
 * to the node's key. The right subtree of a node contains only nodes with keys
 * greater than or equal to the node's key. Both the left and right subtrees
 * must also be binary search trees.
 * 
 * 
 * For example: Given BST [1,null,2,2],
 * 
 * 1 \ 2 / 2
 * 
 * 
 * return [2].
 * 
 * Note: If a tree has more than one mode, you can return them in any order.
 * 
 * Follow up: Could you do that without using any extra space? (Assume that the
 * implicit stack space incurred due to recursion does not count).
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class ModeInABST {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public int[] findMode(TreeNode root) {

		int[] inorder = inorder(root);
		if (inorder.length == 0)
			return new int[0];
		if (inorder.length == 1)
			return new int[] { inorder[0] };
		Map<Integer, List<Integer>> map = new HashMap<>();
		int max = 0;
		for (int i = 0; i < inorder.length; i++) {
			int count = 1;
			while (i < inorder.length - 1 && inorder[i] == inorder[i + 1]) {
				count++;
				i++;
			}
			max = Math.max(max, count);
			map.putIfAbsent(count, new ArrayList<>());
			map.get(count).add(inorder[i]);
		}
		int[] result = new int[map.get(max).size()];
		for (int i = 0; i < map.get(max).size(); i++) {
			result[i] = map.get(max).get(i);
		}
		return result;
	}

	public int[] inorder(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		helper(root, result);
		int[] res = new int[result.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = result.get(i);
		}
		return res;
	}

	public void helper(TreeNode root, List<Integer> result) {
		if (root == null)
			return;
		helper(root.left, result);
		result.add(root.val);
		helper(root.right, result);
	}
}
