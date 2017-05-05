package leetcode.removeelementsfromlinkedlist;

/**
 * Remove all elements from a linked list of integers that have value val.
 * 
 * Example Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6 Return: 1 --> 2
 * --> 3 --> 4 --> 5
 * 
 * Credits: Special thanks to @mithmatt for adding this problem and creating all
 * test cases.
 * 
 * @author dwaraknathbs
 *
 */
public class RemoveElementsFromALinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode removeElements(ListNode head, int val) {

		if (head == null)
			return null;

		ListNode current = head;
		ListNode prev = null;

		while (current != null) {
			if (current.val == val) {
				if (prev == null) {
					head = head.next;

				} else {
					prev.next = current.next;

				}

			} else {
				prev = current;

			}
			current = current.next;

		}
		return head;
	}

}
