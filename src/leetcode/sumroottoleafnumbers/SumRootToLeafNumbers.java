package leetcode.sumroottoleafnumbers;

import java.util.ArrayList;
import java.util.List;

/**
 * 129. Sum Root to Leaf Numbers Medium
 * 
 * 563
 * 
 * 22
 * 
 * Favorite
 * 
 * Share Given a binary tree containing digits from 0-9 only, each root-to-leaf
 * path could represent a number.
 * 
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * 
 * Find the total sum of all root-to-leaf numbers.
 * 
 * Note: A leaf is a node with no children.
 * 
 * Example:
 * 
 * <pre>
Input: [1,2,3]
    1
   / \
  2   3
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
Example 2:

Input: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.
 * </pre>
 * 
 * @author dwaraknathbs
 *
 */
public class SumRootToLeafNumbers {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public int sumNumbers(TreeNode root) {
		if (root == null)
			return 0;

		List<Integer> current = new ArrayList<>();
		List<List<Integer>> result = new ArrayList<>();

		helper(root, result, current);
		int sum = 0;
		for (List<Integer> list : result) {
			int number = 0;
			for (int num : list) {
				number = number * 10 + num;

			}
			sum += number;
		}
		return sum;

	}

	public void helper(TreeNode root, List<List<Integer>> result, List<Integer> current) {
		if (root.left == null && root.right == null) {
			current.add(root.val);
			result.add(new ArrayList<>(current));
			current.remove(current.size() - 1);
			return;
		}

		current.add(root.val);
		if (root.left != null)
			helper(root.left, result, current);
		if (root.right != null)
			helper(root.right, result, current);
		current.remove(current.size() - 1);
	}
}
