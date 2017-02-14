package linkedlist.assignment.reverselinkedlistofsizek;

import java.util.Scanner;

/**
 * Reverse a Linked List in groups of given size Given a linked list, write a
 * function to reverse every k nodes (where k is an input to the function).
 * 
 * Example: Inputs: 1->2->3->4->5->6->7->8->NULL and k = 3 Output:
 * 3->2->1->6->5->4->8->7->NULL.
 * 
 * Inputs: 1->2->3->4->5->6->7->8->NULL and k = 5 Output:
 * 5->4->3->2->1->8->7->6->NULL.
 * 
 * @author dwaraknathbs
 *
 */
public class ReverseLinkedListsWithIntervalK {
	
	static class LinkedListNode{
		int val;
		LinkedListNode next;
		public LinkedListNode(int val) {
			super();
			this.val = val;
		}
		
	}

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

		int k=in.nextInt();
		res = reverse(_pList,k);
		while (res != null) {
			System.out.println(String.valueOf(res.val));
			res = res.next;
		}

	}

	/**
	 * 
	 *  Recursively reverse every K nodes.
	 *   
	 *  Reversing the K nodes can be done by making every element the root.
	 * @param node
	 * @param k
	 * @return
	 */
	private static LinkedListNode reverse(LinkedListNode node, int k) {
		if(node==null) return node;
		LinkedListNode curr=node.next;
		LinkedListNode prev=node;
		LinkedListNode next = null;
		int i=0;
		
		while(i<k-1 && curr!=null){
			
			next=curr.next;
			prev.next=next;
			curr.next=node;
			node=curr;
			curr=next;
			i++;
		}
		prev.next=reverse(next,k);
		return node;
	}

}
