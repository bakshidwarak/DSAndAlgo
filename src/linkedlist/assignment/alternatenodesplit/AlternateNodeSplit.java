package linkedlist.assignment.alternatenodesplit;

import java.util.Scanner;

/**
 * Alternative Node Split Given a linked list, split it into two such that every
 * other node goes into the new list. For lists with odd number of nodes, first
 * one should be longer. For example: an input list: {a, b, c, d, e, f, g}
 * results in {a, c, e, g} and {b, d, f}.
 * 
 * Solution:
 * https://docs.google.com/document/d/1UBFl633FsCZLq2Vejihj2g80Vdl9BNF6-N1D4J-clsI/edit?usp=sharing
 * 
 * @author dwaraknathbs
 *
 */
public class AlternateNodeSplit {

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

		alternativeSplit(_pList);

	}

	/**
	 * Simple pointer logic. Maintain two current pointers one for each split
	 * and manipulate the next
	 * 
	 * @param _pList
	 */
	private static void alternativeSplit(LinkedListNode _pList) {
		if (_pList == null)
			return;

		LinkedListNode nextList = null;
		if (_pList.next != null) {
			nextList = _pList.next;
		}

		LinkedListNode head1 = _pList, head2 = nextList;
		LinkedListNode curr1 = _pList, curr2 = nextList;
		while (curr1 != null && curr2 != null) {
			curr1.next = curr2.next;
			if (curr1.next != null) {
				curr2.next = curr1.next.next;
			}

			curr1 = curr1.next;
			curr2 = curr2.next;

		}

		printList(head1);
		printList(head2);
	}

	private static void printList(LinkedListNode head1) {
		StringBuilder sb = new StringBuilder();
		while (head1 != null) {
			sb.append(head1.val);
			head1 = head1.next;
			if (head1 != null)
				sb.append(",");
		}
		System.out.println(sb.toString());
	}

}
