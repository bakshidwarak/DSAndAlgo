package leetcode.maximalrectangle;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
 * containing only 1's and return its area.
 * 
 * For example, given the following matrix:
 * 
 * <pre>
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
 * </pre>
 * 
 * Return 6.
 * 
 * @author dwaraknathbs
 *
 */
public class MaximalRectangle {

	public int maximalRectangle(char[][] matrix) {

		if (matrix == null || matrix.length == 0)
			return 0;
		int[] heights = new int[matrix[0].length];
		Arrays.fill(heights, 0);

		int maxArea = 0;
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				if (matrix[row][col] == '1') {
					heights[col] = heights[col] + 1;
				} else {
					heights[col] = 0;
				}
			}
			maxArea = Math.max(maxArea, getHistogramArea(heights));
		}

		return maxArea;

	}

	public int getHistogramArea(int[] heights) {
		int maxArea = 0;
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < heights.length;) {
			if (stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
				stack.push(i++);
			} else {
				int lastIndex = stack.pop();
				int numberOfBars = stack.isEmpty() ? i : i - stack.peek() - 1;
				int area = heights[lastIndex] * numberOfBars;
				maxArea = Math.max(maxArea, area);
			}
		}

		while (!stack.isEmpty()) {
			int lastIndex = stack.pop();
			int numberOfBars = stack.isEmpty() ? heights.length : heights.length - stack.peek() - 1;
			int area = heights[lastIndex] * numberOfBars;
			maxArea = Math.max(maxArea, area);
		}
		return maxArea;
	}
}
