package trees.assignment.findlargestbst;

import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Largest BST Given a binary tree, find the largest Binary Search Tree (BST),
 * where largest means BST with largest number of nodes in it. The largest BST
 * must include all of its descendants.
 * 
 * Solution: http://www.ideserve.co.in/learn/size-of-largest-bst-in-binary-tree
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class FindLargestBST {
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
		res = findLargestBST(n);
		System.out.println(String.valueOf(res));

	}

	private static int findLargestBST(Node n) {

		MyInt maxCounter = new MyInt();
		findLargestBST(n, maxCounter);
		return maxCounter.getValue();
	}

	/**
	 * Contract is every node returns the maximum element below it, minimum
	 * element below it and the number of nodes in the bst under it
	 * 
	 * @param n
	 * @param maxCounter
	 * @return
	 */
	private static Pair findLargestBST(Node n, MyInt maxCounter) {
		if (n == null)
			return new Pair(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);

		boolean isleaf = n.right == null && n.left == null;
		if (isleaf) {

			maxCounter.value = Math.max(maxCounter.value, 1);
			/**
			 * If it is the leaf node, it is a bst of size one and its value is
			 * both max and min
			 */
			return new Pair(n.val, n.val, 1);
		}

		int count = 0;
		Pair left = findLargestBST(n.left, maxCounter);

		Pair right = findLargestBST(n.right, maxCounter);

		/**
		 * If the current node is < than the lowest element on the right and >
		 * than the highest element on the left it adds to the bst
		 */
		if (n.val <= right.min && n.val > left.max) {
			count = left.bstCount + right.bstCount + 1;
		}

		int minimum = Math.min(Math.min(left != null ? left.min : n.val, right != null ? right.min : n.val), n.val);
		int maximum = Math.max(Math.max(left != null ? left.max : n.val, right != null ? right.max : n.val), n.val);

		Pair p = new Pair(minimum, maximum, count);

		maxCounter.value = Math.max(maxCounter.value, count);
		return p;

	}

	static class MyInt {
		int value;

		void reset() {
			value = 0;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

	}

	static class Pair {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int bstCount = 0;

		public Pair(int min, int mx, int count) {
			super();
			this.min = min;
			this.max = mx;
			this.bstCount = count;
		}

	}
}
