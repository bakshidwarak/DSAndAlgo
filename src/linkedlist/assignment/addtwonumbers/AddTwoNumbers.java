package linkedlist.assignment.addtwonumbers;

import java.io.IOException;
import java.util.Scanner;

/**
 * Add two numbers A number is represented by a linked list, with the head of
 * the linked list being the least significant digit. For example 12 is
 * represented as 2->1, and 23 is represented as 3->2 Adding 2->1 and 3->2
 * should produce 5->3 which is equivalent to (35).
 * 
 * Write a function which adds two linked lists returning the sum in form of a
 * linked list. Try to use constant memory.
 * 
 * @author dwaraknathbs
 *
 */
public class AddTwoNumbers {
	public static class LinkedListNode {
		int val;
		LinkedListNode next;

		LinkedListNode(int node_value) {
			val = node_value;
			next = null;
		}
	};

	public static LinkedListNode _insert_node_into_singlylinkedlist(LinkedListNode head, LinkedListNode tail, int val) {
		if (head == null) {
			head = new LinkedListNode(val);
			tail = head;
		} else {
			tail.next = new LinkedListNode(val);
			tail = tail.next;
		}
		return tail;
	}

	static LinkedListNode addNumbers(LinkedListNode l1, LinkedListNode l2) {

		/**
		 * If either number is zero return the other
		 */
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;

		LinkedListNode first = l1;
		LinkedListNode second = l2;
		LinkedListNode prev = null;
		int carry = 0;
		/**
		 * When both first and second are present, keep adding and taking the
		 * carry forward. Appending vals to first to reuse memory
		 */
		while (first != null && second != null) {
			/**
			 * Make sure we maintain reference for the last element of the first
			 * number
			 */
			prev = first;
			int sum = first.val + second.val + carry;

			carry = sum / 10;

			sum = sum % 10;
			first.val = sum;

			first = first.next;
			second = second.next;
		}

		/**
		 * First number was lesser in digits than second. Continue traversing
		 * second. Append the output to the last element of first
		 */
		while (second != null) {
			int sum = second.val + carry;

			carry = sum / 10;

			sum = sum % 10;

			second.val = sum;
			prev.next = second;
			prev = second;
			second = second.next;

		}

		/**
		 * if first had more digits than second. Keep adding the second number
		 * with carry
		 */
		while (first != null) {
			prev = first;
			int sum = first.val + carry;

			carry = sum / 10;

			sum = sum % 10;

			first.val = sum;

			first = first.next;

		}

		/**
		 * If after adding all there exists a carry. Create a new node for the
		 * carry and append it to prev
		 */
		if (carry != 0) {
			LinkedListNode carryNode = new LinkedListNode(carry);
			prev.next = carryNode;
		}
		return l1;

	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);

		LinkedListNode res;

		int _l1_size = 0;
		_l1_size = Integer.parseInt(in.nextLine());
		int _l1_i;
		int _l1_item;
		LinkedListNode _l1 = null;
		LinkedListNode _l1_tail = null;
		for (_l1_i = 0; _l1_i < _l1_size; _l1_i++) {
			_l1_item = Integer.parseInt(in.nextLine().trim());
			if (_l1_i == 0) {
				_l1 = _insert_node_into_singlylinkedlist(_l1, _l1_tail, _l1_item);
				_l1_tail = _l1;
			} else {
				_l1_tail = _insert_node_into_singlylinkedlist(_l1, _l1_tail, _l1_item);
			}
		}

		int _l2_size = 0;
		_l2_size = Integer.parseInt(in.nextLine());
		int _l2_i;
		int _l2_item;
		LinkedListNode _l2 = null;
		LinkedListNode _l2_tail = null;
		for (_l2_i = 0; _l2_i < _l2_size; _l2_i++) {
			_l2_item = Integer.parseInt(in.nextLine().trim());
			if (_l2_i == 0) {
				_l2 = _insert_node_into_singlylinkedlist(_l2, _l2_tail, _l2_item);
				_l2_tail = _l2;
			} else {
				_l2_tail = _insert_node_into_singlylinkedlist(_l2, _l2_tail, _l2_item);
			}
		}

		res = addNumbers(_l1, _l2);
		while (res != null) {
			System.out.println(String.valueOf(res.val));

			res = res.next;
		}

	}
}
