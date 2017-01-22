package trees.assignment.bsttodll;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Convert a BST into a Circular Doubly Linked List
 * ===========================================================================
 * Write a recursive function treeToList(Node root) that takes a BST and
 * rearranges the internal pointers to make a circular doubly linked list out of
 * the tree nodes. The "previous" pointers should be stored in the "Left" field
 * and the "next" pointers should be stored in the "Right" field. The list
 * should be arranged so that the nodes are in increasing order. Return the head
 * pointer to the new list. The operation can be done in O(n) time.
 * 
 * @author dwaraknathbs
 *
 */
public class BSTToDLL {
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
		BSTtoLL(n);

	}

	static void BSTtoLL(Node root) {

		Node[] metaNodes = new Node[2];
		// I need to modify the contract to take an array instead as Java is
		// pass by value and we cannot directly assign a reference. Instead I am
		// trying to cheat Java by passing an array and manipulating the array
		BSTToDoublyLL(root, metaNodes);

		Node curr = metaNodes[0];
		do {
			System.out.print(curr.val + " ");
			curr = curr.right;

		} while (curr != metaNodes[0]);

	}

	private static void BSTToDoublyLL(Node root, Node[] metaNodes) {
		if (root == null)
			return;

		BSTToDoublyLL(root.left, metaNodes);
		Node head = metaNodes[0];
		Node prev = metaNodes[1];
		root.left = prev;
		if (prev == null)
			head = root;
		else
			prev.right = root;

		head.left = root;

		Node right = root.right;

		root.right = head;
		prev = root;
		metaNodes[0] = head;
		metaNodes[1] = prev;

		BSTToDoublyLL(right, metaNodes);

	}
}
