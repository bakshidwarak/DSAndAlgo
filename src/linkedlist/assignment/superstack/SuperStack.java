package linkedlist.assignment.superstack;

import java.util.Scanner;

/**
 * Super Stack Implement a simple stack that accepts the following commands and
 * performs the operations associated with them: push k: Push integer k onto the
 * top of the stack. pop: Pop the top element from the stack. It is guaranteed
 * that this will not be called on an empty stack. inc e k: Add k to each of the
 * bottom e elements of the stack.
 * 
 * Complete the superStack function in the editor below. It has one parameter:
 * an array of n strings, operations. The function must create an empty stack
 * and perform each of the n operations in order and, after performing each
 * operation, print the value of the stack's top element on a new line; if the
 * stack is empty, print EMPTY instead.
 * 
 * Input Format Locked stub code in the editor reads the following input from
 * stdin and passes it to the function: The first line contains an integer, n,
 * denoting the number of operations to perform on the stack. Each line i of the
 * n subsequent lines contains a space-separated string describing operationsi.
 * 
 * Constraints 1 ≤ n ≤ 2 × 10^5 -10^9 ≤ k ≤ 10^9 1 ≤ e ≤ |S|, where |S| is the
 * size of the stack at the time of the operation. It is guaranteed that pop is
 * never called on an empty stack.
 * 
 * Output Format After performing each operation, print the value of the stack's
 * top element on a new line; if the stack is empty, print EMPTY instead.
 * 
 * @author dwaraknathbs
 *
 */
public class SuperStack {

	static class Stack {
		long[] elements = new long[200000];
		int top = -1;

		void push(long element) {
			elements[++top] = element;
			System.out.println(elements[top]);
		}

		long pop() {

			long popped = elements[top--];
			if (top == -1) {
				System.out.println("EMPTY");

			} else {
				System.out.println(elements[top]);
			}

			return popped;
		}

		long peek() {
			return elements[top];
		}

		void inc(long e, long val) {
			for (int i = 0; i < e && i <= top; i++) {
				elements[i] = elements[i] + val;
			}
			System.out.println(elements[top]);
		}
	}

	static void superStack(String[] operations) {
		Stack stack = new Stack();

		for (String str : operations) {
			String[] operand = str.split("\\s");
			switch (operand[0].toLowerCase()) {
			case "push":
				long val = Long.parseLong(operand[1]);
				stack.push(val);

				break;
			case "pop":
				stack.pop();
				break;
			case "inc":
				long e = Long.parseLong(operand[1]);
				long k = Long.parseLong(operand[2]);
				stack.inc(e, k);
				break;
			}
		}

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int _operations_size = 0;
		_operations_size = Integer.parseInt(in.nextLine().trim());
		String[] _operations = new String[_operations_size];
		String _operations_item;
		for (int _operations_i = 0; _operations_i < _operations_size; _operations_i++) {
			try {
				_operations_item = in.nextLine();
			} catch (Exception e) {
				_operations_item = null;
			}
			_operations[_operations_i] = _operations_item;
		}

		superStack(_operations);

	}

}
