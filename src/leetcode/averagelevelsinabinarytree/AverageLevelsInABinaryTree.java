package leetcode.averagelevelsinabinarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Given a non-empty binary tree, return the average value of the nodes on each
 * level in the form of an array.
 * 
 * Example 1:
 * 
 * <pre>
Input:
    3
   / \
  9  20
    /  \
   15   7
 * </pre>
 * 
 * Output: [3, 14.5, 11] Explanation: The average value of nodes on level 0 is
 * 3, on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
 * Note: The range of node's value is in the range of 32-bit signed integer.
 * 
 * @author dwaraknathbs
 *
 */
public class AverageLevelsInABinaryTree {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	static class Pair {
		int level;
		TreeNode node;
		int sum;

		public Pair(int level, TreeNode node) {
			this.level = level;
			this.node = node;
		}
	}

	static class CountSum {
		long sum;
		int count;

		public CountSum(long sum, int count) {
			this.sum = sum;
			this.count = count;
		}
	}

	public List<Double> averageOfLevels(TreeNode root) {

		Map<Integer, CountSum> levels = new HashMap<>();
		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(1, root));
		int maxLevel = 1;
		while (!queue.isEmpty()) {
			Pair current = queue.remove();
			if (current.node != null) {
				if (levels.containsKey(current.level)) {
					CountSum countSum = levels.get(current.level);
					CountSum revised = new CountSum(countSum.sum + current.node.val, countSum.count + 1);
					levels.put(current.level, revised);
				} else {
					CountSum countSum = new CountSum(current.node.val, 1);

					levels.put(current.level, countSum);
					maxLevel++;
				}
				queue.add(new Pair(current.level + 1, current.node.right));
				queue.add(new Pair(current.level + 1, current.node.left));
			}
		}

		List<Double> result = new ArrayList<>();
		for (int i = 1; i <= maxLevel; i++) {
			CountSum current = levels.get(i);
			if (current != null) {
				double average = (double) current.sum / (double) current.count;
				result.add(average);
			}
		}
		return result;
	}

}
