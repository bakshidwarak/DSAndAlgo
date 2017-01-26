package trees.assignment.univalsubtrees;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * 
 * Given a binary tree, we need to count the number of unival subtrees (all
 * nodes that have the same value).
 * 
 * @author dwaraknathbs
 *
 */
public class UnivalSubtree {
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

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);

		int res;
		int _size;
		_size = Integer.parseInt(in.nextLine().trim());

		String _str;
		try {
			_str = in.nextLine();
		} catch (Exception e) {
			_str = null;
		}
		Node n = createTree(_str);
		res = findSingleValueTrees(n);
		System.out.println(res);
	}

	/**
	 * If my right and left subtrees are unival, root is unival if its value is
	 * same as either of the children
	 * 
	 * If there is only one subtree ( right or left) for the node, the node is
	 * unival if the subtree is unival and its value is same as the left or
	 * rights value
	 * 
	 * if there are no children, the node is a leaf node and it is unival
	 * 
	 * @param n
	 * @return
	 */
	private static int findSingleValueTrees(Node n) {
		// Had to use atominInteger as all the other Integer types in java are
		// immutable
		java.util.concurrent.atomic.AtomicInteger numUnivals = new java.util.concurrent.atomic.AtomicInteger(0);
		univalTrees(n, numUnivals);
		return numUnivals.get();
	}

	private static boolean univalTrees(Node n, java.util.concurrent.atomic.AtomicInteger numberOfUnivalTrees) {
		if (n == null)
			return true;
		if (n.left == null && n.right == null) {// Leaf nodes are unival
			numberOfUnivalTrees.incrementAndGet();
			return true;
		}

		boolean leftTreeUnival = univalTrees(n.left, numberOfUnivalTrees);
		boolean righTreeUnival = univalTrees(n.right, numberOfUnivalTrees);

		if (leftTreeUnival && righTreeUnival) {// If both the subtrees are
												// unival
			if (n.left != null && n.right != null && n.val == n.right.val && n.val == n.left.val) {

				numberOfUnivalTrees.incrementAndGet();
				return true;
			} else if (n.left != null && n.right == null && n.left.val == n.val) {// If
																					// the
																					// node
																					// has
																					// only
																					// left
																					// subtree
																					// that
																					// is
																					// unival
				numberOfUnivalTrees.incrementAndGet();
				return true;
			} else if (n.right != null && n.left == null && n.right.val == n.val) {// If
																					// the
																					// node
																					// has
																					// only
																					// right
																					// subtree
																					// that
																					// is
																					// unival
				numberOfUnivalTrees.incrementAndGet();
				return true;
			}

		}
		return false;
	}
}