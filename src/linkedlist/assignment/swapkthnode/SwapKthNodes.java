package linkedlist.assignment.swapkthnode;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class SwapKthNodes {
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

		int _iK;
		_iK = Integer.parseInt(in.nextLine().trim());

		res = swapNodes(_pList, _iK);
		while (res != null) {
			System.out.println(String.valueOf(res.val));

			res = res.next;
		}

	}

	private static LinkedListNode swapNodes(LinkedListNode _pList, int _iK) {

		LinkedListNode kthNodeFromFirst = _pList;
		LinkedListNode prevToKth = null;
		;
		LinkedListNode kthNodeFromBack = _pList;
		LinkedListNode prevToKthFromBack = _pList;

		LinkedListNode current = _pList;

		int count = 1;
		while (current!=null) {
			if(count==_iK) break;
			prevToKth = current;
			current = current.next;
			count++;
		}

		// current is at K now
		kthNodeFromFirst = current;
		// start from k+1;
		if (current != null) {// else k= lenghth of the list
			current = current.next;

			while (current != null) {
				prevToKthFromBack = kthNodeFromBack;
				kthNodeFromBack = kthNodeFromBack.next;
				current = current.next;
			}

			
			if (prevToKth != null) {
				prevToKth.next = kthNodeFromBack;
			} else {
				// replacing the head
				_pList = kthNodeFromBack;
			}
			if(prevToKthFromBack!=null){
				prevToKthFromBack.next = kthNodeFromFirst;
			}
			else
			{
				_pList=kthNodeFromFirst;
			}
			
			LinkedListNode temp = kthNodeFromBack.next;
			
			kthNodeFromBack.next=kthNodeFromFirst.next;
			
			kthNodeFromFirst.next = temp;
			
			
		}

		return _pList;
	}

}
