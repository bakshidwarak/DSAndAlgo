package leetcode.rotatelist;

/**
 * 61. Rotate List Medium
 * 
 * 464
 * 
 * 745
 * 
 * Favorite
 * 
 * Share Given a linked list, rotate the list to the right by k places, where k
 * is non-negative.
 * 
 * Example 1:
 * 
 * Input: 1->2->3->4->5->NULL, k = 2 Output: 4->5->1->2->3->NULL Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL rotate 2 steps to the right:
 * 4->5->1->2->3->NULL Example 2:
 * 
 * Input: 0->1->2->NULL, k = 4 Output: 2->0->1->NULL Explanation: rotate 1 steps
 * to the right: 2->0->1->NULL rotate 2 steps to the right: 1->2->0->NULL rotate
 * 3 steps to the right: 0->1->2->NULL rotate 4 steps to the right:
 * 2->0->1->NULL
 * 
 * @author dwaraknathbs
 *
 */
public class RotateList {
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode rotateRight(ListNode head, int k) {

		if (head == null)
			return head;

		int length = findLength(head);
		int shift = k % length;

		ListNode curr = head;
		ListNode prev = null;
		ListNode head1 = head;

		int c = 0;
		while (curr != null && c < shift + 1) {
			prev = curr;
			curr = curr.next;
			c++;
		}
		while (curr != null) {
			prev = curr;
			curr = curr.next;
			head = head.next;
		}
		prev.next = head1;
		ListNode newHead = head.next;
		head.next = null;

		return newHead;

	}

	public int findLength(ListNode head) {
		int count = 0;
		for (; head != null; head = head.next, count++)
			;
		return count;
	}

}
