package leetcode.reverseknodes;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and
 * return its modified list.
 * 
 * k is a positive integer and is less than or equal to the length of the linked
 * list. If the number of nodes is not a multiple of k then left-out nodes in
 * the end should remain as it is.
 * 
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * 
 * Only constant memory is allowed.
 * 
 * For example, Given this linked list: 1->2->3->4->5
 * 
 * For k = 2, you should return: 2->1->4->3->5
 * 
 * For k = 3, you should return: 3->2->1->4->5
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class ReverseKNodesLinkedList {

	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode reverseKGroup(ListNode head, int k) {

		if (head == null || head.next == null || !ifknodes(head, k))
			return head;

		ListNode curr = head.next;
		ListNode prev = head;
		ListNode next = null;
		int i = 0;
		while (curr != null && i < k - 1) {
			next = curr.next;
			curr.next = head;
			prev.next = next;
			head = curr;
			curr = next;
			i++;
		}
		/**
		 * Important to check next for null as if next is null a node gets lost
		 */
		if (next != null)
			prev.next = reverseKGroup(next, k);
		return head;

	}

	public boolean ifknodes(ListNode node, int k) {

		int count = 0;

		while (node != null) {
			count++;
			node = node.next;
			if (count == k)
				return true;
		}

		return false;
	}
}