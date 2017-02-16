package linkedlist.assignment.minstack;

import java.util.Stack;

/**
 * Implement a Min Stack Your goal for this problem is to implement an
 * additional method: "getMinimum()" for a stack. The method, as the name
 * suggests, returns the minimum element in the entire stack. It shouldn't pop
 * that element; it should only peek i.e. return the value. If time permits, you
 * may implement other methods of the stack, but for now, focus on getMinimum().
 * 
 * Constraint: getMinimum() should return in constant time.
 * 
 * e.g: 1. If the stack has 1,2,3,4,5 in that order from bottom to top, then the
 * call to getMinimum() should return 1 2. If the stack has 1,5,3,0 in that
 * order from bottom to top, then the call to getMinimum() should return 0 3. If
 * the stack is empty, it should return NULL
 * 
 * 
 * [We did this problem in the class, but here is a good solution too:
 * http://articles.leetcode.com/stack-that-supports-push-pop-and-getmin/]
 * 
 * @author dwaraknathbs
 *
 */
public class MinStack {

	int size;
	/**
	 * Have a stack for elements and have another stack for min values. As you
	 * push, peek the min stack to see if the element is <= the peek element in
	 * the min stack
	 */
	Stack<Integer> elementStack = new Stack<>();
	Stack<Integer> minStack = new Stack<>();

	public MinStack(int i) {
		size = i;
	}

	public static void main(String[] args) {

		MinStack minStack = new MinStack(10);
		minStack.push(5);
		System.out.println("current min " + minStack.getMin());
		minStack.push(8);
		minStack.push(2);
		minStack.push(15);
		System.out.println("current min " + minStack.getMin());
		minStack.push(9);
		minStack.pop();
		minStack.pop();
		System.out.println("current min " + minStack.getMin());
		minStack.pop();
		minStack.push(5);
		System.out.println("current min " + minStack.getMin());
		minStack.push(3);
		System.out.println("current min " + minStack.getMin());
		minStack.pop();
		minStack.pop();
		System.out.println("current min " + minStack.getMin());
		minStack.pop();
		minStack.pop();
		System.out.println(minStack.peek());

	}

	public void push(int ele) {
		if (isFull())
			throw new RuntimeException("Stack is full");
		elementStack.push(ele);
		int currentMin = ele;
		if (!minStack.isEmpty())
			currentMin = minStack.peek();
		if (ele <= currentMin) {
			minStack.push(ele);

		}
		display();
	}

	private boolean isFull() {
		// TODO Auto-generated method stub
		return elementStack.size() == size;
	}

	public int pop() {
		if (isEmpty())
			throw new RuntimeException("Cannot pop from empty stack");
		int ele = elementStack.pop();
		int currentMin = minStack.isEmpty() ? Integer.MIN_VALUE : minStack.peek();
		if (ele == currentMin)
			minStack.pop();
		display();
		return ele;
	}

	private boolean isEmpty() {
		// TODO Auto-generated method stub
		return elementStack.isEmpty();
	}

	public int peek() {
		if (isEmpty())
			throw new RuntimeException("Cannot peek from empty stack");
		return elementStack.peek();
	}

	public int getMin() {
		if (isEmpty())
			throw new RuntimeException("Cannot peek from empty stack");
		return minStack.peek();
	}

	public void display() {
		elementStack.stream().map(val -> String.valueOf(val).concat(" ")).forEach(System.out::print);
		System.out.println();
	}

}
