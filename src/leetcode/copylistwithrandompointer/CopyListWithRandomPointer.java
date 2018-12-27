package leetcode.copylistwithrandompointer;

import java.util.HashMap;

/**
 * 138. Copy List with Random Pointer Medium
 * 
 * 1138
 * 
 * 347
 * 
 * Favorite
 * 
 * Share A linked list is given such that each node contains an additional
 * random pointer which could point to any node in the list or null.
 * 
 * Return a deep copy of the list.
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class CopyListWithRandomPointer {
	class RandomListNode {
		int label;
		RandomListNode next, random;

		RandomListNode(int x) {
			this.label = x;
		}
	};

	public RandomListNode copyRandomList(RandomListNode head) {

		HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
		RandomListNode curr = head;
		RandomListNode prev = null;
		RandomListNode newHead = null;
		while (curr != null) {
			RandomListNode newNode = new RandomListNode(curr.label);
			if (prev != null) {
				prev.next = newNode;
			}
			if (newHead == null) {
				newHead = newNode;
			}
			prev = newNode;
			map.put(curr, newNode);
			curr = curr.next;
		}
		curr = head;
		while (curr != null) {
			RandomListNode random = curr.random;
			RandomListNode randomsClone = map.get(random);
			RandomListNode currentClone = map.get(curr);
			currentClone.random = randomsClone;
			curr = curr.next;
		}

		return newHead;

	}
}
