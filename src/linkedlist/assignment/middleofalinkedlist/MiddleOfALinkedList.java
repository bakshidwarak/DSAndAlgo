package linkedlist.assignment.middleofalinkedlist;

import java.util.Scanner;

/**
 * Find the middle element in a singly linked list Find the middle element of a
 * singly linked list. Constraint: Do it in one pass over the list. If it is
 * even number of elements, then output the 2nd of the middle two elements.
 * 
 * e.g. 1. (standard positive case) For input: 1->2->3->nil, Output should be: 2
 * 2. (positive case with longer list, odd # of nodes). For input:
 * 1->11->45->12->67->89->91->nil, Output should be 12 3. (positive case with
 * longer list, even # of nodes). For input: 1->11->45->12->67->89->nil, Output
 * should be 12 (2nd of the middle two) 4. For input: nil, output should be nil
 * 
 * Solution :
 * https://docs.google.com/document/d/1UBFl633FsCZLq2Vejihj2g80Vdl9BNF6-N1D4J-clsI/edit?usp=sharing
 * 
 * @author dwaraknathbs
 *
 */
public class MiddleOfALinkedList {

	public static class LinkedListNode {
		int val;
		LinkedListNode next;

		LinkedListNode(int node_value) {
			val = node_value;
			next = null;
		}
	};

	public static LinkedListNode _insert_node_into_singlylinkedlist(LinkedListNode head, LinkedListNode tail, int val) {
		if (head == null) {
			head = new LinkedListNode(val);
			tail = head;
		} else {
			tail.next = new LinkedListNode(val);
			tail = tail.next;
		}
		return tail;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int res;

		int _pList_size = 0;
		_pList_size = Integer.parseInt(in.nextLine());
		int _pList_i;
		int _pList_item;
		LinkedListNode _pList = null;
		LinkedListNode _pList_tail = null;
		for (_pList_i = 0; _pList_i < _pList_size; _pList_i++) {
			_pList_item = Integer.parseInt(in.nextLine().trim());
			if (_pList_i == 0) {
				_pList = _insert_node_into_singlylinkedlist(_pList, _pList_tail, _pList_item);
				_pList_tail = _pList;
			} else {
				_pList_tail = _insert_node_into_singlylinkedlist(_pList, _pList_tail, _pList_item);
			}
		}

		res = findMiddleNode(_pList);
		System.out.println(String.valueOf(res));

	}

	/**
	 * Have a fast ptr and a slow ptr. Fast ptr jumps two nodes at once. The
	 * node that head points to when the fast ptr reaches the end is the middle
	 * element
	 * 
	 * @param head
	 * @return
	 */
	private static int findMiddleNode(LinkedListNode head) {
		if (head == null)
			return 0;
		LinkedListNode fastPtr = head;
		while (fastPtr != null && fastPtr.next != null) {
			fastPtr = fastPtr.next.next;
			head = head.next;
		}
		return head.val;

	}

}
