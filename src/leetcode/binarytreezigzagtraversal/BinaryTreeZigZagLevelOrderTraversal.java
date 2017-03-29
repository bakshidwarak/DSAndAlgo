package leetcode.binarytreezigzagtraversal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 103. Binary Tree Zigzag Level Order Traversal
 * 
 * Given a binary tree, return the zigzag level order traversal of its nodes'
 * values. (ie, from left to right, then right to left for the next level and
 * alternate between).
 * 
 * <pre>
For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
 * </pre>
 * 
 * @author dwaraknathbs
 *
 */
public class BinaryTreeZigZagLevelOrderTraversal {

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

	static class NodeOrder {
		TreeNode node;
		int level;

		public NodeOrder(TreeNode node, int level) {
			this.node = node;
			this.level = level;

		}
	}

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

		List<List<Integer>> levels = new ArrayList<>();
		Queue<NodeOrder> queue = new LinkedList<>();
		HashMap<Integer, List<Integer>> levelElements = new HashMap<>();
		queue.add(new NodeOrder(root, 1));
		while (!queue.isEmpty()) {
			NodeOrder current = queue.remove();
			int order = current.level;

			List<Integer> currentElements = levelElements.get(order);
			if (currentElements == null) {
				currentElements = new ArrayList<>();

			}
			currentElements.add(current.node.val);
			levelElements.put(order, currentElements);

			addNodeIfExistsToQueue(queue, current.node.left, order + 1);
			addNodeIfExistsToQueue(queue, current.node.right, order + 1);

		}

		for (int entry : levelElements.keySet()) {
			List<Integer> elements = levelElements.get(entry);
			if (entry % 2 == 0) {
				Collections.reverse(elements);
			}

			levels.add(elements);
		}
		return levels;

	}

	private void addNodeIfExistsToQueue(Queue<NodeOrder> queue, TreeNode node, int order) {
		if (node == null)
			return;
		queue.add(new NodeOrder(node, order));
	}
}
