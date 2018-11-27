package leetcode.removenthnodefromlast;

/**
 * 19. Remove Nth Node From End of List Medium 1330 105
 * 
 * 
 * Given a linked list, remove the n-th node from the end of list and return its
 * head.
 * 
 * Example:
 * 
 * Given linked list: 1->2->3->4->5, and n = 2.
 * 
 * After removing the second node from the end, the linked list becomes
 * 1->2->3->5. Note:
 * 
 * Given n will always be valid.
 * 
 * Follow up:
 * 
 * Could you do this in one pass?
 * 
 * @author dwaraknathbs
 *
 */
public class RemoveNthNodeFromLast {

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode removeNthFromEnd(ListNode head, int n) {

		if (head == null)
			return head;

		ListNode slow = head;
		ListNode fast = head;
		ListNode prev = head;
		int k = 0;
		while (fast != null && k < n) {

			fast = fast.next;
			k++;
		}

		if (fast == null) {
			head = head.next;
		}

		while (fast != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next;

		}
		if (prev != null)
			prev.next = slow.next;

		return head;

	}
}
