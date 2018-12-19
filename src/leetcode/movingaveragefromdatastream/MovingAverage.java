package leetcode.movingaveragefromdatastream;

import java.util.LinkedList;

/**
 * 346. Moving Average from Data Stream Easy
 * 
 * 217
 * 
 * 28
 * 
 * Favorite
 * 
 * Share Given a stream of integers and a window size, calculate the moving
 * average of all integers in the sliding window.
 * 
 * Example:
 * 
 * MovingAverage m = new MovingAverage(3); m.next(1) = 1 m.next(10) = (1 + 10) /
 * 2 m.next(3) = (1 + 10 + 3) / 3 m.next(5) = (10 + 3 + 5) / 3
 * 
 * @author dwaraknathbs
 *
 */
public class MovingAverage {
	/** Initialize your data structure here. */
	private int size;
	int currentSize = 0;
	int runningSum = 0;
	java.util.Deque<Integer> queue = new LinkedList<>();

	public MovingAverage(int size) {
		this.size = size;
	}

	public double next(int val) {
		if (currentSize == size) {
			int toBeRemoved = queue.removeLast();
			runningSum -= toBeRemoved;
			currentSize--;
		}
		currentSize++;
		runningSum += val;
		queue.addFirst(val);

		return (double) runningSum / currentSize;
	}
}
