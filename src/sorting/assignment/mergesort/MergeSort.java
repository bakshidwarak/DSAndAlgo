package sorting.assignment.mergesort;

import java.util.Scanner;

/**
 * Implement Merge Sort
 * 
 * 
 * Please convert the standard merge-sort algorithm into code.
 * 
 * Input: Integers in an array, duplicates possible Output: Same integers in
 * ascending order, in a new array. Preserve the input array.
 * 
 * (Goal of this homework problem: The concept of partitioning, concept of
 * merging partitions, the clarity that merge-sort needs extra space and why
 * worst-case is better than QuickSort viz. O(NLogN))
 * 
 * Suggested time: 30 minutes
 * 
 * Solution: http://www.codenlearn.com/2011/10/simple-merge-sort.html Trivia: In
 * the solution above, what is the Complexity of memory requirement? Can we do
 * better than that?
 * 
 * @author dwaraknathbs
 *
 */
public class MergeSort {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int[] res;

		int _intArr_size = 0;
		_intArr_size = Integer.parseInt(in.nextLine().trim());
		int[] _intArr = new int[_intArr_size];
		int _intArr_item;
		for (int _intArr_i = 0; _intArr_i < _intArr_size; _intArr_i++) {
			_intArr_item = Integer.parseInt(in.nextLine().trim());
			_intArr[_intArr_i] = _intArr_item;
		}

		res = mergeSort(_intArr);
		for (int res_i = 0; res_i < res.length; res_i++) {
			System.out.println(String.valueOf(res[res_i]));

		}

	}

	static int[] mergeSort(int[] intArr) {

		return mergeSortHelper(intArr, 0, intArr.length - 1);

	}

	static int[] mergeSortHelper(int[] input, int start, int end) {

		if (start == end) {
			int[] result = new int[1];
			result[0] = input[start];
			return result;
		}

		int mid = (start + end) / 2;
		int[] left = mergeSortHelper(input, start, mid);
		int[] right = mergeSortHelper(input, mid + 1, end);
		return merge(left, right);
	}

	static int[] merge(int[] left, int[] right) {

		int[] result = new int[left.length + right.length];
		int i = 0;
		int j = 0;
		int k = 0;

		while (i < left.length && j < right.length) {
			if (left[i] < right[j]) {
				result[k++] = left[i++];

			} else {
				result[k++] = right[j++];
			}
		}

		while (i < left.length) {
			result[k++] = left[i++];
		}
		while (j < right.length) {
			result[k++] = right[j++];
		}

		return result;
	}
}
