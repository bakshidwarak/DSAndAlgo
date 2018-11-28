package leetcode.swapnodesinpairs;

/**
 * 24. Swap Nodes in Pairs Medium 785 65
 * 
 * 
 * Given a linked list, swap every two adjacent nodes and return its head.
 * 
 * Example:
 * 
 * Given 1->2->3->4, you should return the list as 2->1->4->3. Note:
 * 
 * Your algorithm should use only constant extra space. You may not modify the
 * values in the list's nodes, only nodes itself may be changed.
 * 
 * @author dwaraknathbs
 *
 */
public class SwapNodesInPairs {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode swapPairs(ListNode head) {
		if (head == null)
			return head;

		return swapInPairs(head);

	}

	public ListNode swapInPairs(ListNode node) {
		if (node == null || node.next == null)
			return node;
		ListNode next = node.next;
		ListNode next2next = next.next;
		next.next = node;
		node.next = swapInPairs(next2next);
		return next;
	}
}
