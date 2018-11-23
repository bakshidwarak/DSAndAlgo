package leetcode.postordertraversalnarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 590. N-ary Tree Postorder Traversal Easy 157 25
 * 
 * 
 * Given an n-ary tree, return the postorder traversal of its nodes' values.
 * 
 * For example, given a 3-ary tree:
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Return its postorder traversal as: [5,6,3,2,4,1].
 * 
 * 
 * Note:
 * 
 * Recursive solution is trivial, could you do it iteratively?
 * 
 * @author dwaraknathbs
 *
 */
public class PostOrderTraversalNaryTree {

	// Definition for a Node.
	static class Node {
		public int val;
		public List<Node> children;

		public Node() {
		}

		public Node(int _val, List<Node> _children) {
			val = _val;
			children = _children;
		}
	};

	public List<Integer> postorder(Node root) {

		List<Integer> result = new ArrayList<>();
		postOrderHelper(root, result);
		return result;

	}

	public List<Integer> postorderIterative(Node root) {

		Stack<Node> stack = new Stack<>();
		List<Integer> result = new ArrayList<>();
		if (root != null)
			stack.push(root);

		while (!stack.isEmpty()) {
			Node node = stack.pop();
			result.add(node.val);
			for (Node child : node.children) {
				if (child != null)
					stack.push(child);
			}

		}
		Collections.reverse(result);
		return result;

	}

	public void postOrderHelper(Node root, List<Integer> result) {
		if (root == null)
			return;
		for (Node child : root.children)
			postOrderHelper(child, result);

		result.add(root.val);

	}
}