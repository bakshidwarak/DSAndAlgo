package linkedlist.assignment.zipunzip;

import java.util.Scanner;

/**
 * Zip! Zip a linked list from it two ends.
 * 
 * e.g. Input: 1->2->3->4->5->6 Output: 1->6->2->5->3->4
 * 
 * Suggested time: 30 minutes
 * 
 * 
 * Solution:
 * http://programming-puzzle.blogspot.com/2014/02/zip-of-linked-list.html
 * 
 * [Extra credit: Zip two separate lists and unzip them back into original
 * lists. i.e. unzip(zip(L1,L2)) should return L1 and L2]
 * 
 * @author dwaraknathbs
 *
 */
public class LinkedListZipper {

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

		res = Zip(_pList);
		while (res != null) {
			System.out.println(String.valueOf(res.val));
			res = res.next;
		}

	}

	private static LinkedListNode Zip(LinkedListNode _pList) {

		LinkedListNode middleElement = splitListAtMiddle(_pList);
		LinkedListNode reversedListHead = reverseFromList(middleElement);
		LinkedListNode head = _pList;
		while (head != null && reversedListHead != null) {
			LinkedListNode temp = head.next;
			LinkedListNode reverseNext = reversedListHead.next;
			head.next = reversedListHead;
			reversedListHead.next = temp;

			reversedListHead = reverseNext;
			head = temp;
		}
		LinkedListNode curr=_pList;
		LinkedListNode prev = null;
		if(head!=null){
			while(curr!=null){
				prev=curr;
				curr=curr.next;
			}
			prev.next=head;
		}
		
		if(reversedListHead!=null){
			while(curr!=null){
				prev=curr;
				curr=curr.next;
			}
			prev.next=reversedListHead;
		}
		
		return _pList;

	}

	private static LinkedListNode reverseFromList(LinkedListNode middleElement) {
		LinkedListNode head = middleElement;
		LinkedListNode currentNode = head.next;
		LinkedListNode prev = head;
		while (currentNode != null) {

			LinkedListNode next = currentNode.next;

			currentNode.next = head;
			prev.next = next;
			head = currentNode;
			currentNode = next;

		}
		return head;
	}

	private static LinkedListNode splitListAtMiddle(LinkedListNode head) {
		if (head == null)
			return head;
		LinkedListNode fastPtr = head;
		LinkedListNode prev = null;
		while (fastPtr != null && fastPtr.next != null) {
			prev = head;
			fastPtr = fastPtr.next.next;
			head = head.next;
		}
		/**
		 * Splitting the list into two by making the previous' next point to
		 * null
		 */
		if (prev != null)
			prev.next = null;
		return head;
	}

}
