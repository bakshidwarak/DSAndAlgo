package leetcode.addtwonumbersII;

import java.util.Stack;

/**
 * 445. Add Two Numbers II Medium
 * 
 * 599
 * 
 * 82
 * 
 * Favorite
 * 
 * Share You are given two non-empty linked lists representing two non-negative
 * integers. The most significant digit comes first and each of their nodes
 * contain a single digit. Add the two numbers and return it as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 * 
 * Follow up: What if you cannot modify the input lists? In other words,
 * reversing the lists is not allowed.
 * 
 * Example:
 * 
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 8 -> 0 -> 7
 * 
 * @author dwaraknathbs
 *
 */
public class AddTwoNumbersII {
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		Stack<ListNode> first = getNumber(l1);
		Stack<ListNode> second = getNumber(l2);
		Stack<ListNode> result = new Stack<>();
		int carry = 0;
		while (!first.isEmpty() && !second.isEmpty()) {
			ListNode num1 = first.pop();
			ListNode num2 = second.pop();
			int sum = carry + num1.val + num2.val;
			ListNode curr = new ListNode(sum % 10);
			carry = sum / 10;
			result.push(curr);
		}
		while (!first.isEmpty()) {
			ListNode num1 = first.pop();

			int sum = carry + num1.val;
			ListNode curr = new ListNode(sum % 10);
			carry = sum / 10;
			result.push(curr);
		}
		while (!second.isEmpty()) {

			ListNode num2 = second.pop();
			int sum = carry + num2.val;
			ListNode curr = new ListNode(sum % 10);
			carry = sum / 10;
			result.push(curr);
		}
		if (carry != 0) {
			result.push(new ListNode(carry));
		}

		ListNode head = null;
		ListNode prev = null;
		while (!result.isEmpty()) {
			ListNode curr = result.pop();
			if (head == null)
				head = curr;
			if (prev != null)
				prev.next = curr;
			prev = curr;
		}

		return head;
	}

	public Stack<ListNode> getNumber(ListNode l) {
		Stack<ListNode> stack = new Stack<>();

		ListNode temp = l;

		while (temp != null) {

			stack.push(temp);
			temp = temp.next;

		}

		return stack;
	}

}
