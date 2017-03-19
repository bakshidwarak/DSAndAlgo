package linkedlist.assignment.clonespeciallist;

/**
 * Clone a special list (Courtesy, GfG)
 * 
 * You are given a Double Link List with one pointer of each node pointing to
 * the next node just like in a single link list. The second pointer however CAN
 * point to any node in the list and not just the previous node. Now write a
 * program in O(n) time to duplicate this list. That is, write a program which
 * will create a copy of this list.
 * 
 * Let us call the second pointer as arbit pointer as it can point to any
 * arbitrary node in the linked list.
 * 
 * 
 * 
 * 
 * 
 * (Courtesy, GfG)
 * 
 * Like most linked list problems, please do this with constant extra memory.
 * 
 * Solution:
 * http://www.geeksforgeeks.org/a-linked-list-with-next-and-arbit-pointer/
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class CloneSpecialLinkedList {

	static class Node {
		int data;
		Node next;
		Node arbitaray;

		public Node(int data) {
			super();
			this.data = data;
		}

	}

	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = null;
		n5.arbitaray = n2;
		n4.arbitaray = n3;
		n3.arbitaray = n5;
		n2.arbitaray = n1;
		n1.arbitaray = n3;
		System.out.println("Before Cloning");
		print(n1);

		Node clonedNode = clone(n1);
		System.out.println(" After Cloned Node");
		print(clonedNode);
	}

	private static void print(Node n1) {
		Node curr = n1;
		while (curr != null) {
			System.out.print("data " + curr.data + " " + curr + " ");
			String arbitaray = curr.arbitaray != null ? Integer.toString(curr.arbitaray.data) + " " + curr.arbitaray
					: "null";
			System.out.print("arbitarary " + arbitaray);
			System.out.println();
			curr = curr.next;
		}
	}

	/**
	 * Key idea is to create a new node insert it in between the current node and its next. 
	 * 
	 * Have another traversal and keep assigning the arbitaray pointer
	 * @param n1
	 * @return
	 */
	private static Node clone(Node n1) {
		Node curr = n1;
		Node newHead = null;

		while (curr != null) {
			Node n = new Node(curr.data);
			n.next = curr.next;
			curr.next = n;
			curr = n.next;

			if (newHead == null)
				newHead = n;
		}

		curr = n1;
		while (curr != null) {
			if (curr.next != null && curr.arbitaray != null)
				curr.next.arbitaray = curr.arbitaray.next;

			curr = curr.next.next;
		}

		curr = newHead;
		while (curr != null) {
			if (curr.next != null)
				curr.next = curr.next.next;

			curr = curr.next;
		}

		return newHead;
	}

}
