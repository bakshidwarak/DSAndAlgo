package trees.assignment.lca;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class LeastCommonAncestor {
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

	static Node findLCA(Node root, int n1, int n2) {

		// return findLCAPathApproach(root, n1, n2);
		return findLCAOneTraversal(root, n1, n2);

	}

	/**
	 * This is a path based approach, find the path of the elements and compare
	 * 
	 * the paths till they are same. return the last common element Two
	 * traversals and O(n) space for storing the path
	 */
	private static Node findLCAPathApproach(Node root, int n1, int n2) {
		ArrayList<Node> n1Path = new ArrayList<>();
		ArrayList<Node> n2Path = new ArrayList<>();
		findLCAWithPath(root, n1, n1Path);
		findLCAWithPath(root, n2, n2Path);
		int i = 0;
		while (i < n1Path.size() && i < n2Path.size() && n1Path.get(i) == n2Path.get(i)) {
			i++;
		}
		return n1Path.get(i - 1);
	}

	/**
	 * This is a One traversal approach.
	 * 
	 * if left has n1 or n2 and right has n1 or n2, the root is the LCA
	 * 
	 * if left is not null ( n1 or n2 was present in the left) and right is
	 * null(n1 or n2 is not present=> both n1 qnd n2 were on the left), the LCA
	 * is on the left side of the root
	 * 
	 * if right is not null ( n1 or n2 was present in the left) and left is null
	 * (n1 or n2 is not present=> both n1 qnd n2 were on the right), the LCA is
	 * on the right of the root
	 * 
	 * @param root
	 * @param n1
	 * @param n2
	 * @return
	 */
	private static Node findLCAOneTraversal(Node root, int n1, int n2) {
		if (root == null)
			return null;
		if (n1 == root.val || n2 == root.val) {
			return root;
		}
		Node left = findLCAOneTraversal(root.left, n1, n2);
		Node right = findLCAOneTraversal(root.right, n1, n2);
		// If one of the elements is in my left and other in my right I am the
		// LCA
		if (left != null && right != null)
			return root;

		// If both vals were on my left. My left is the LCA
		if (left != null)
			return left;

		// if both vals were on my right My right is the LCA
		if (right != null)
			return right;
		// Both the elements not found
		return null;
	}

	/**
	 * This is a path based approach, find the path of the elements and compare
	 * the paths till they are same. return the last common element
	 * 
	 * @param root
	 * @param n1
	 * @param path
	 * @return
	 */
	private static boolean findLCAWithPath(Node root, int n1, ArrayList<Node> path) {
		if (root == null)
			return false;
		path.add(root);

		if (root.val == n1)
			return true;

		boolean elementFoundInLeft = root.left != null && findLCAWithPath(root.left, n1, path);
		boolean elementFoundInRight = findLCAWithPath(root.right, n1, path);
		if (elementFoundInLeft || elementFoundInRight)
			return true;
		else
			path.remove(path.size() - 1);
		return false;

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

		int _n1;
		_n1 = Integer.parseInt(in.nextLine().trim());

		int _n2;
		_n2 = Integer.parseInt(in.nextLine().trim());
		Node root = createTree(_str);
		Node res = findLCA(root, _n1, _n2);
		System.out.print(res.val);
	}
}