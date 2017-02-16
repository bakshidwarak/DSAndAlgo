package trees.assignment.flipbinarytree;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Flip a tree Reverse a general binary tree, i.e. flip it from right to left.
 * 
 * So for example if we had the binary tree
 * 
 * <pre>
     6
   /   \
  3     4
 / \   / \
7   3 8   1
Reversing it would create

     6
   /   \
  4     3
 / \   / \
1   8 3   7
 * </pre>
 * 
 * @author dwaraknathbs
 *
 */
public class FlipBTree {
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
		Node flip = flipTree(n);
		printInorder(flip);
	}

	private static Node flipTree(Node n) {
		if (n == null) {
			return n;

		}
		Node temp = n.right;
		n.right = n.left;
		n.left = temp;
		flipTree(n.left);
		flipTree(n.right);
		return n;
	}
}
