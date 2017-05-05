package leetcode.deletenodeinalinkedlist;

/**
 * Write a function to delete a node (except the tail) in a singly linked list,
 * given only access to that node.
 * 
 * Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node
 * with value 3, the linked list should become 1 -> 2 -> 4 after calling your
 * function.
 * 
 * Show Company Tags Show Tags Show Similar Problems
 * 
 * @author dwaraknathbs
 *
 */
public class DeleteNodeInALinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public void deleteNode(ListNode node) {
		if (node == null || node.next == null)
			return;

		/**
		 * We cannot effectively remove the node as we do not have a prev
		 * pointer. What we could do is to copy the data of the next node and
		 * make next point to the pointers next
		 */
		node.val = node.next.val;
		node.next = node.next.next;
	}

}
