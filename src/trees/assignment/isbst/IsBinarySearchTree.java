package trees.assignment.isbst;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *  Given a Binary Tree, check if it
 * is a Binary Search Tree (BST). A valid BST doesn't have to be complete or
 * balanced. Duplicate elements are not allowed in a BST.
 * 
 * Solution: O(N) time:
 * http://articles.leetcode.com/2010/09/determine-if-binary-tree-is-binary.html
 * 
 * @author dwaraknathbs
 *
 */
public class IsBinarySearchTree {

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		final String fileName = System.getenv("OUTPUT_PATH");
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
		boolean res;
		int _size;
		_size = Integer.parseInt(in.nextLine().trim());

		String _str;
		try {
			_str = in.nextLine();
		} catch (Exception e) {
			_str = null;
		}
		Node n = createTree(_str);
		res = isBST(n);
		bw.write(String.valueOf(res ? 1 : 0));
		bw.newLine();

		bw.close();

	}

	

	static boolean isBST(Node node) {
		return isBSTHelper(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private static boolean isBSTHelper(Node node, int minValue, int maxValue) {

		if (node == null)
			return true;
		if (node.val > minValue && node.val < maxValue)
			return isBSTHelper(node.left, minValue, node.val) && isBSTHelper(node.right, node.val, maxValue);
		return false;
	}

	static boolean isBSTInorderHelper(Node node, int prevVal) {
		if (node == null)
			return true;
		if (isBSTInorderHelper(node.left, prevVal)) {

			if (node.val > prevVal) {
				int data = node.val;
				return isBSTInorderHelper(node.right, data);
			} else
				return false;

		}
		return false;
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

	static class Node {
		int val;
		Node left;
		Node right;

		public Node(int value) {
			this.val = value;
		}
	}
}