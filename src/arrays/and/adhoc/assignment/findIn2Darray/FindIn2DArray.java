package arrays.and.adhoc.assignment.findIn2Darray;

import java.util.Scanner;

/**
 * Find a number in a sorted 2D array.
 * 
 * You're given a 2d array (N x M) where all the numbers (integers) in the array
 * are in increasing order from left to right and top to bottom. You're also
 * given a target number, to be searched inside the array. What is the best way
 * to search and determine if a target number is in the array?
 * 
 * Solution: take inspiration from
 * http://www.geeksforgeeks.org/search-in-row-wise-and-column-wise-sorted-matrix/
 * 
 * (Notice that this can be a very deceptive problem in a good way. Solution
 * seems difficult, but it's actually quite simple.)
 * 
 * @author dwaraknathbs
 *
 */
public class FindIn2DArray {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the number of rows");
		int rows = scanner.nextInt();
		System.out.println("Enter the number of columns");
		int cols = scanner.nextInt();
		int[][] array = new int[rows][cols];
		System.out.println("Enter the elements in array");
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				array[i][j] = scanner.nextInt();
			}
		}

		System.out.println("Enter the value to search");

		int element = scanner.nextInt();

		for (int row = 0, col = cols - 1; row < rows && col >= 0;) {
			// Start from top right
			int currentElement = array[row][col];
			if (currentElement == element) {
				System.out.println(String.format("Element %d found at position %d,%d", element, row, col));
				return;
			}
			if (currentElement > element) {
				col--;// Go left in the column

			} else {
				row++;// element is greater so go down to next row
			}

		}
		System.out.println("Element Not found");

	}

}
