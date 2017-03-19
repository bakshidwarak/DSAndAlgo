package linkedlist.assignment.mergesort;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * Merge Sort a Linked List Sort a singly linked list using merge sort. Sort
 * ascending.
 * 
 * (Trivia question: Merge Sort, in general is a good method of sorting
 * anything, because its worst case performance is better than most other
 * sorting methods. For Linked Lists however, Merge Sort is highly preferred,
 * because it has a distinct advantage. What is that advantage?)
 * 
 * Solution:
 * https://docs.google.com/document/d/1UBFl633FsCZLq2Vejihj2g80Vdl9BNF6-N1D4J-clsI/edit?usp=sharing
 * 
 * @author dwaraknathbs
 *
 */
public class MergeSortLinkedList {
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

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);

		LinkedListNode res;

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

		res = mergeSortList(_pList);
		while (res != null) {
			System.out.println(String.valueOf(res.val));

			res = res.next;
		}

	}

	/**
	 * Similar to merge sort , instead of indices we need to split based on
	 * length
	 * 
	 * @param pList
	 * @return
	 */
	static LinkedListNode mergeSortList(LinkedListNode pList) {

		int length = 0;
		LinkedListNode curr = pList;
		while (curr != null) {
			length++;
			curr = curr.next;
		}

		return mergeSortHelper(pList, length);

	}

	private static LinkedListNode mergeSortHelper(LinkedListNode pList, int length) {
		if (pList == null)
			return pList;
		if (length == 1 || length == 0)
			return pList;

		int midLength = length / 2;

		int currentLength = 0;
		LinkedListNode curr = pList;
		LinkedListNode prev = null;
		while (curr != null && currentLength < midLength) {
			prev = curr;
			currentLength++;
			curr = curr.next;
		}
		/**
		 * This is a key step to cut off the lists once they split. Else during
		 * merge we encounter an infinite loop
		 */
		if (prev != null)
			prev.next = null;

		LinkedListNode node1 = mergeSortHelper(pList, midLength);
		LinkedListNode node2 = mergeSortHelper(curr, length - midLength);

		return merge(node1, node2);
	}

	private static LinkedListNode merge(LinkedListNode node1, LinkedListNode node2) {

		LinkedListNode newHead = null;
		LinkedListNode head = null;

		while (node1 != null && node2 != null) {
			if (node1.val < node2.val) {
				/**
				 * This part is a duplictae code. There is no way to extract a
				 * method as java is call by value and we cannot replace an
				 * instance
				 */
				if (head == null) {
					newHead = node1;
					head = newHead;
				} else {
					newHead.next = node1;
					newHead = newHead.next;
				}
				node1 = node1.next;
			} else {
				if (head == null) {
					newHead = node2;
					head = newHead;
				} else {
					newHead.next = node2;
					newHead = newHead.next;
				}

				node2 = node2.next;
			}

		}

		while (node1 != null && node2 == null) {
			if (head == null) {
				newHead = node1;
				head = newHead;
			} else {
				newHead.next = node1;
				newHead = newHead.next;
			}
			node1 = node1.next;

		}

		while (node2 != null && node1 == null) {
			if (head == null) {
				newHead = node2;
				head = newHead;
			} else {
				newHead.next = node2;
				newHead = newHead.next;
			}
			node2 = node2.next;

		}
		return head;
	}

}
