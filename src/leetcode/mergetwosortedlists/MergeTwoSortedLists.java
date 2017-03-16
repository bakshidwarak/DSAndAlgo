package leetcode.mergetwosortedlists;

/**
 * Merge two sorted linked lists and return it as a new list. The new list
 * should be made by splicing together the nodes of the first two lists.
 * 
 * Show Company Tags Show Tags Show Similar Problems
 * 
 * 21. Merge Two Sorted Lists
 * 
 * @author dwaraknathbs
 *
 */
public class MergeTwoSortedLists {

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(4);
		n1.next = n2;
		n2.next = n3;
		ListNode head = mergeTwoLists(null, n1);

		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}

	}

	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

		ListNode newHead = null;
		ListNode node1 = l1;
		ListNode node2 = l2;
		ListNode head = null;
		;

		while (node1 != null && node2 != null) {
			if (node1.val < node2.val) {
				if (newHead == null) {
					newHead = node1;
					head = newHead;
				} else {
					newHead.next = node1;
				}
				newHead = node1;
				node1 = node1.next;
			} else {
				if (newHead == null) {
					newHead = node2;
					head = newHead;
				} else {
					newHead.next = node2;
				}
				newHead = node2;
				node2 = node2.next;
			}

		}
		while (node1 != null) {
			if (newHead == null) {
				newHead = node1;
				head = newHead;
			} else {
				newHead.next = node1;
			}
			newHead = node1;
			node1 = node1.next;

		}

		while (node2 != null) {
			if (newHead == null) {
				newHead = node2;
				head = newHead;
			} else {
				newHead.next = node2;
			}
			newHead = node2;
			node2 = node2.next;

		}

		return head;

	}
}
