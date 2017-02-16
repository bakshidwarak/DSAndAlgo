package trees.assignment.clonebinarytree;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Clone a binary tree Given a binary tree (represented by its root node, like
 * usual), clone it. Return the root node of the cloned tree.
 * 
 * 
 * 
 * 
 * 
 * 
 * Solution:
 * http://crackprogramming.blogspot.com/2012/11/make-copy-of-binary-tree-given-pointer.html
 * 
 * Remember: Cloning or copying a tree is best done recursively. Notice how
 * clean and succinct the code is. Some of you may be tempted to do it
 * breadth-first. But that's more complicated to handle in implementation.
 * 
 * @author dwaraknathbs
 *
 */
public class CloneBTree {
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
		Node clone = cloneTree(n);
		printInorder(clone);
	}

	private static Node cloneTree(Node n) {
		if (n == null)
			return n;

		Node newNode = new Node(n.val);
		newNode.left = cloneTree(n.left);
		newNode.right = cloneTree(n.right);
		return newNode;
	}
}
