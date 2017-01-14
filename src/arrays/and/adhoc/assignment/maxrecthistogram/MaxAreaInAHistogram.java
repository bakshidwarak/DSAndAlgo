package arrays.and.adhoc.assignment.maxrecthistogram;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Find the largest rectangular area possible in a given histogram where the
 * largest rectangle can be made of a number of contiguous bars. For simplicity,
 * assume that all bars have same width and the width is 1 unit.
 * 
 * For example, consider the following histogram with 7 bars of heights {6, 2,5,
 * 4, 5, 2, 6}. The largest possible rectangle possible is 12
 * 
 * @author dwaraknathbs
 *
 */
public class MaxAreaInAHistogram {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		String[] inpString = input.split(",");
		scanner.close();
		List<Integer> bars = Arrays.stream(inpString).map(Integer::parseInt).collect(Collectors.toList());
		System.out.println(maxAreaInHistogram(bars.toArray(new Integer[bars.size()])));

	}

	/**
	 * The key idea is to keep track of start and end of every sub-rectangle
	 * formed. When a bar is first read, it could either start a new sub
	 * rectangle or add to the previous rectangle When will it start a new
	 * sub-rectangle? When the value of the bar is < the bar to its left Add
	 * every new sub-rectangle to the stack When will it add to the prev sub
	 * rectangle ? when it is >= the previous previous bar- Keep pushing to the
	 * stack When will the current sub-rectangle end? When the value of the bar
	 * is < the bar to its left. It starts a new sub rectangle and ends the
	 * previous sub-rectangle\ Pop the previous bar out of the stack and
	 * calculate the area contributed by the bar to its sub-rectangle
	 * 
	 * Maintain a running max and return the max rectangle
	 * 
	 * @param a
	 * @return
	 */
	private static int maxAreaInHistogram(Integer[] a) {

		Deque<Integer> stack = new LinkedList<>();
		int maxArea = 0;
		for (int i = 0; i < a.length;) {
			if (stack.isEmpty() || a[i] >= a[stack.peekFirst()]) {
				stack.push(i++);
			} else {
				int index = stack.pop();

				/**
				 * Calculation of area is key in solving this. When we pop out a
				 * bar ( after encountering a bar < than the top) we need to
				 * calculate the area contributed by the bar being popped out.
				 * 
				 * Area contributed by the popped bar= height of the bar * (
				 * number of contiguous bars >= height say M) M=current index -
				 * new top of the stack -1
				 * 
				 * if after popping the stack is empty just multiply the height
				 * by the current index
				 */
				int area = a[index] * (stack.isEmpty() ? i : i - stack.peekFirst() - 1);
				maxArea = Math.max(maxArea, area);
			}
		}

		/**
		 * If the stack is not empty , it contains all the bars that contribute
		 * to the max area. We pop one at a time and calcluate the area
		 * contributed by the bar with same logic as above. if the stack is
		 * empty which means we have encountered the least element in the array
		 * and hence we multiply it by the length of the array
		 */
		while (!stack.isEmpty()) {
			int index = stack.pop();
			int area = a[index] * (stack.isEmpty() ? a.length : a.length - index - stack.peekFirst() - 1);
			maxArea = Math.max(maxArea, area);
		}

		return maxArea;
	}

}
