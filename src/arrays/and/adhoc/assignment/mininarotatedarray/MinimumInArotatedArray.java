package arrays.and.adhoc.assignment.mininarotatedarray;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Find minimum in a rotated, sorted array (Relatively easy problem, but still
 * popular)
 * 
 * We're given an array with sorted numbers. The array has been rotated an
 * unknown number of times. We need to figure out the minimum number in such an
 * array. What would be a fast method that uses only constant space?
 * 
 * 
 * Solution:
 * http://www.geeksforgeeks.org/find-minimum-element-in-a-sorted-and-rotated-array/
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class MinimumInArotatedArray {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);

		int res;

		int _arr_size = 0;
		_arr_size = Integer.parseInt(in.nextLine().trim());
		int[] _arr = new int[_arr_size];
		int _arr_item;
		for (int _arr_i = 0; _arr_i < _arr_size; _arr_i++) {
			_arr_item = Integer.parseInt(in.nextLine().trim());
			_arr[_arr_i] = _arr_item;
		}

		res = findMinimum(_arr);
		System.out.println(String.valueOf(res));

	}

	private static int findMinimum(int[] arr) {
		// TODO Auto-generated method stub
		return findMinimum(arr, 0, arr.length - 1);
	}

	/**
	 * Key idea is like binary search. The element which is lowest will always
	 * have both its neighbours greater than itself. We need to find it
	 * recursively dividing
	 * 
	 * @param arr
	 * @param low
	 * @param high
	 * @return
	 */
	private static int findMinimum(int[] arr, int low, int high) {

		/**
		 * Exit condition if low==high
		 */
		if (low == high) {
			return arr[low];
		}

		/**
		 * Get the middle element
		 */
		int mid = (low + high) / 2;

		/**
		 * Check if mid element is the lowest element
		 */
		if (mid > 0 && arr[mid] < arr[mid - 1])
			return arr[mid];

		/**
		 * If mid element is lesser than the high element the lowest element is
		 * to the right of mid
		 */
		if (arr[mid] > arr[high]) {
			return findMinimum(arr, mid + 1, high);
		}
		return findMinimum(arr, low, mid );
	}

}
