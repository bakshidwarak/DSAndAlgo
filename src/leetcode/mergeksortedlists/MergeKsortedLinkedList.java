package leetcode.mergeksortedlists;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and
 * describe its complexity.
 * 
 * @author dwaraknathbs
 *
 */
public class MergeKsortedLinkedList {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode mergeKLists(ListNode[] lists) {

		ListNode node = null;

		PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparing(l -> l.val));

		for (int i = 0; i < lists.length; i++) {
			if (lists[i] != null)
				pq.add(lists[i]);
		}
		ListNode head = node;
		ListNode prev = null;
		while (!pq.isEmpty()) {
			ListNode current = pq.poll();
			node = new ListNode(current.val);
			if (head == null) {
				head = node;
			}
			if (prev != null) {
				prev.next = node;
			}
			prev = node;
			node = node.next;

			if (current.next != null)
				pq.add(current.next);

		}

		return head;

	}
}