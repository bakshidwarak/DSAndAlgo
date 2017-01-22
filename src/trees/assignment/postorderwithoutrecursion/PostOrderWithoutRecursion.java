package trees.assignment.postorderwithoutrecursion;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * Write a function to traverse a Binary tree PostOrder, without using
 * recursion. As you traverse, please print contents of the nodes. (Bonus: Spend
 * 1 minute more and also do it with recursion)
 * 
 * @author dwaraknathbs
 *
 */
public class PostOrderWithoutRecursion {

	static class Node {
		int val;
		Node left;
		Node right;

		public Node(int value) {
			this.val = value;
		}
	}

	static Node createTree(String data) {
		if (data == null || data.length() == 0)
			return null;
		StringTokenizer st = new StringTokenizer(data, " ");
		return deserialize(st);
	}

	static Node deserialize(StringTokenizer st) {
		if (!st.hasMoreTokens())
			return null;
		String s = st.nextToken();
		if (s.equals("#"))
			return null;
		Node root = new Node(Integer.valueOf(s));
		root.left = deserialize(st);
		root.right = deserialize(st);

		return root;
	}

	static void printInorder(Node root) {
		if (root == null)
			return;
		printInorder(root.left);
		System.out.print(root.val + " ");
		printInorder(root.right);
	}

	static void postorderTraversal(Node root) {
		Stack<Node> stack = new Stack<>();
		HashSet<Node> visitedNodes = new HashSet<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			Node n = stack.pop();
			if (n == null)
				continue;
			if (bothChildrenNull(n) || leftNullAndRightVisited(visitedNodes, n)
					|| rightNullAndLeftVisited(visitedNodes, n) || bothNodesVisited(visitedNodes, n)) {
				visitedNodes.add(n);
				System.out.print(n.val + " ");
			} else {
				stack.push(n);

				stack.push(n.right);

				stack.push(n.left);
			}

		}

	}

	static void postorderTraversalTwoStack(Node root) {
		Stack<Node> stack = new Stack<>();
		Stack<Node> outputStack = new Stack<>();

		stack.push(root);

		while (!stack.isEmpty()) {
			Node n = stack.pop();

			outputStack.push(n);

			if (n.left != null)
				stack.push(n.left);

			if (n.right != null)
				stack.push(n.right);

		}

		while (!outputStack.isEmpty()) {
			Node node = outputStack.pop();
			System.out.print(node.val + " ");
		}

	}

	static void postorderTraversalRecursive(Node root) {
		if (root == null)
			return;
		postorderTraversalRecursive(root.left);
		postorderTraversalRecursive(root.right);
		System.out.print(root.val + " ");
	}

	private static boolean bothNodesVisited(HashSet<Node> visitedNodes, Node n) {
		return visitedNodes.contains(n.left) && visitedNodes.contains(n.right);
	}

	private static boolean rightNullAndLeftVisited(HashSet<Node> visitedNodes, Node n) {
		return n.right != null && visitedNodes.contains(n.right) && n.left == null;
	}

	private static boolean leftNullAndRightVisited(HashSet<Node> visitedNodes, Node n) {
		return n.left != null && visitedNodes.contains(n.left) && n.right == null;
	}

	private static boolean bothChildrenNull(Node n) {
		return n.left == null && n.right == null;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int _size;
		_size = Integer.parseInt(in.nextLine().trim());

		String _str;
		try {
			_str = in.nextLine();
		} catch (Exception e) {
			_str = null;
		}
		Node n = createTree(_str);
		Node n1 = new Node(13);
		n1.left = new Node(2);
		// printInorder(n);
		postorderTraversal(n);
		System.out.println();
		postorderTraversalRecursive(n);
		System.out.println();
		postorderTraversalTwoStack(n);

	}
}