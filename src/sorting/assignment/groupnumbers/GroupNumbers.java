package sorting.assignment.groupnumbers;

import java.io.IOException;
import java.util.Scanner;

/**
 * Group the numbers (If this question feels easy, that's because it is)
 * 
 * Given an array of numbers, positive integers only, group them in-place into
 * evens and odds.
 * 
 * Input: Integer array, positive integers only, repeats possible. Output: The
 * same integer array, with evens on left side and odds on the right side. There
 * is no need to preserve order within odds or within evens.
 * 
 * (Understand that Grouping is just a special case of sorting. It's cognitively
 * much less complex, and can be done with easier methods, which we may not
 * label as sorting, but are just a special case of sorting)
 * 
 * 
 * Solution Complexity: Aim for O(N), in-place. Ideally you'd do it in one pass
 * of the array.
 * 
 * Test-cases: As long as you can ascertain that your solution is right, don't
 * worry if the test-cases don't pass exactly as they are given here. They
 * expect evens to the left and odds to the right. Plus you may have different
 * order.
 * 
 * 
 * Interview time: 15 minutes.
 * 
 * Solution: This is a trivial problem :-)
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class GroupNumbers {

	/**
	 * Basically it is similar to the partition we did around the pivot just
	 * that instead of lesser or greater we check if it is odd or even.
	 * 
	 * The idea is if an element is odd we swap it with the last element and
	 * reduce the count
	 * 
	 * Invariant=== elements before i -- even , elements after j --- odd
	 * 
	 * @param intArr
	 * @return
	 */

	static int[] groupNumbers(int[] intArr) {

		int i = 0;
		int j = intArr.length - 1;

		while (i < j) {
			if (intArr[i] % 2 != 0) {
				swap(intArr, i, j);
				j--;
				continue;
			}
			i++;
		}

		return intArr;

	}

	public static void swap(int[] input, int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}

	public static void main(String[] args) throws IOException {
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

		res = groupNumbers(_intArr);
		for (int res_i = 0; res_i < res.length; res_i++) {
			System.out.println(String.valueOf(res[res_i]));

		}

	}

}
