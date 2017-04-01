package linkedlist.assignment.slidingwindow;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Sliding Window Maximum January 25, 2011 by 1337c0d3r 99 Replies A long array
 * A[] is given to you. There is a sliding window of size w which is moving from
 * the very left of the array to the very right. You can only see the w numbers
 * in the window. Each time the sliding window moves rightwards by one position.
 * Following is an example: The array is [1 3 -1 -3 5 3 6 7], and w is 3.
 * 
 * Window position Max --------------- ----- [1 3 -1] -3 5 3 6 7 3 1 [3 -1 -3] 3
 * 6 7 3 1 3 [-1 -3 5] 3 6 7 5 1 3 -1 [-3 5 3] 6 7 5 1 3 -1 -3 [5 3 6] 7 6 1 3
 * -1 -3 5 [3 6 7] 7 Input: A long array A[], and a window width w Output: An
 * array B[], B[i] is the maximum value of from A[i] to A[i+w-1] Requirement:
 * Find a good optimal way to get B[i]
 * 
 * @author dwaraknathbs
 *
 */
public class SlidingWindowMaximum {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}
		int w = scanner.nextInt();

		int b[] = new int[n - w + 1];
		slideWindowMax(a, b, w);
		Arrays.stream(b).forEach(System.out::println);

	}

	/**
	 * Idea is to use a double ended queue. The front of the queue always gives
	 * the max.
	 * 
	 * Always add at the last
	 * 
	 * @param a
	 * @param b
	 * @param w
	 */
	private static void slideWindowMax(int[] a, int[] b, int w) {

		Deque<Integer> deque = new LinkedList<>();
		/**
		 * First loop runs till w to fill the deque first
		 */
		for (int k = 0; k < w; k++) {
			/**
			 * We can safely pop all elements lesser than the current element
			 * from the last
			 */
			while (!deque.isEmpty() && a[k] >= deque.peekLast()) {
				deque.removeLast();
			}
			/**
			 * Add the index of current element to the end of the dequeue
			 */
			deque.addLast(k);
		}
		/**
		 * From w process one element at a time
		 */
		for (int k = w; k < a.length; k++) {
			/**
			 * k-w gives the index of the array where the max of the window is
			 * stored. Front of the queue always gives the max of that window
			 */
			b[k - w] = a[deque.peekFirst()];
			/**
			 * Keep looking for items less than the current element and removing
			 * from the queue
			 */
			while (!deque.isEmpty() && a[k] >= a[deque.peekLast()])
				deque.removeLast();
			/**
			 * The current window is k lies between w and n. if the element at
			 * front is in the window it should be greater than k-w. If so we
			 * need to opo the front as the max value is no longer relevant
			 */
			while (!deque.isEmpty() && deque.peekFirst() < k - w) {
				deque.removeFirst();
			}
			deque.addLast(k);
		}
		/**
		 * For the last window
		 */
		b[a.length - w] = a[deque.peekFirst()];

	}

}
