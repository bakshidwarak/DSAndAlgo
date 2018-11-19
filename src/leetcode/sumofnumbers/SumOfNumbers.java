package leetcode.sumofnumbers;

/**
 * You are given two non-empty linked lists representing two non-negative
 * integers. The digits are stored in reverse order and each of their nodes
 * contain a single digit. Add the two numbers and return it as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 * 
 * Example:
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 0 -> 8 Explanation: 342 +
 * 465 = 807.
 * 
 * LC-2
 * 
 * @author dwaraknathbs
 *
 */
public class SumOfNumbers {

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode head = null;
		ListNode result = null;
		int carry = 0;
		while (l1 != null || l2 != null) {
			int num = 0;
			if (l1 != null)
				num += l1.val;
			if (l2 != null)
				num += l2.val;
			num = num + carry;
			int val = num % 10;
			carry = num / 10;
			ListNode current = new ListNode(val);
			if (result == null) {

				head = current;
			} else {
				result.next = current;

			}
			result = current;

			if (l1 != null)
				l1 = l1.next;
			if (l2 != null)
				l2 = l2.next;
		}
		// Remember to add an extra node for carry
		if (carry != 0) {
			ListNode current = new ListNode(carry);
			if (result != null)
				result.next = current;
		}
		return head;
	}
}
