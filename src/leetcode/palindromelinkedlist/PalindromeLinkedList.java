package leetcode.palindromelinkedlist;

/**
 * Given a singly linked list, determine if it is a palindrome.
 * 
 * Follow up: Could you do it in O(n) time and O(1) space?
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class PalindromeLinkedList {

	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public boolean isPalindrome(ListNode head) {
		if (head == null)
			return true;
		/**
		 * Find the center node
		 */
		ListNode center = findCenterNode(head);
		System.out.println("Center=" + center.val);
		printList(head);
		/**
		 * Reverse the linked list to the right of center
		 */
		ListNode second = reverse(center);

		printList(second);
		/**
		 * Compare each node
		 */
		ListNode left = head;
		ListNode right = second;
		while (left != second && right != null) {
			if (left.val == right.val) {
				left = left.next;
				right = right.next;
			} else {
				return false;
			}
		}
		return true;
	}

	public ListNode reverse(ListNode node) {
		if (node == null || node.next == null)
			return node;
		ListNode root = node;
		ListNode curr = node.next;
		ListNode prev = node;
		while (curr != null) {
			ListNode next = curr.next;
			prev.next = next;
			curr.next = root;
			root = curr;

			curr = next;

		}
		return root;

	}

	public void printList(ListNode node) {
		while (node != null) {
			System.out.print(node.val + " ");
			node = node.next;
		}
		System.out.println();
	}

	public ListNode findCenterNode(ListNode head) {

		ListNode left = head;
		ListNode right = head.next;

		while (right != null) {
			left = left.next;
			if (right.next != null) {
				right = right.next.next;
			} else {
				break;
			}
		}

		return left;
	}
}