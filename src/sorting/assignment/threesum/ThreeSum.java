package sorting.assignment.threesum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * 3Sum problem (Here is an example of a problem, where sorting is inevitable,
 * but it doesn't matter which sorting technique you use)
 * 
 * Given an array of N integers, find all triplets that sum to 0 (zero).
 * 
 * Triplets may or may not be consecutive numbers. The array can include
 * duplicate elements. Array is not necessarily sorted.
 * 
 * Print output as shown. i.e. as strings, one per line, comma separated
 * elements. Order of elements inside the answer triplets does not matter. i.e.
 * if your output elements are the same, but only in different order, then it's
 * an acceptable solution. Do not print duplicate triplets. If no such triplets
 * are found, then print nothing.
 * 
 * Interview time: 30 minutes
 * 
 * Solution complexity: Cant' do better than N^2. This is one of those nasty
 * problems, where even N^2 is not intuitive. Solution:
 * http://www.programcreek.com/2012/12/leetcode-3sum/
 * 
 * Some ways this problem is disguised 1.
 * http://www.geeksforgeeks.org/find-number-of-triangles-possible/. 2. Sum
 * exactly to a given integer K 3. Sum to the nearest number to given integer K
 * (sort and do a binary search to nearest)
 * 
 * @author dwaraknathbs
 *
 */
public class ThreeSum {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		String[] res;

		int _intArr_size = 0;
		_intArr_size = Integer.parseInt(in.nextLine().trim());
		int[] _intArr = new int[_intArr_size];
		int _intArr_item;
		for (int _intArr_i = 0; _intArr_i < _intArr_size; _intArr_i++) {
			_intArr_item = Integer.parseInt(in.nextLine().trim());
			_intArr[_intArr_i] = _intArr_item;
		}

		res = printTriplets(_intArr);
		for (int res_i = 0; res_i < res.length; res_i++) {
			System.out.println(String.valueOf(res[res_i]));

		}

	}

	/**
	 * Basic idea is to sort the array.
	 * 
	 * Have two pointers starting one from start and one from end. if the sum of
	 * the current element, next start element and the next end element is <0 it
	 * means we need a more positive number to makeee zero and hence increment
	 * the lower pointer . Else if the number is > 0 which means we need a
	 * lesser value to add and hence we decrement the higher pointer
	 * 
	 * @param _intArr
	 * @return
	 */
	private static String[] printTriplets(int[] _intArr) {
		Arrays.sort(_intArr);

		ArrayList<String> triplets = new ArrayList<>();

		int i = 0;
		for (i = 0; i < _intArr.length; i++) {
			int k = _intArr.length - 1;
			int j = i + 1;
			while (j < k) {
				if (_intArr[i] + _intArr[j] + _intArr[k] < 0) {
					j++;
				} else if (_intArr[i] + _intArr[j] + _intArr[k] > 0) {
					k--;
				} else {
					triplets.add(_intArr[j] + "," + _intArr[k] + "," + _intArr[i]);

					j++;
					k--;
					while (j < k && _intArr[j] == _intArr[j + 1])
						j++;
					while (j < k && _intArr[k] == _intArr[k - 1])
						k--;

				}
			}
		}
		String[] result = new String[triplets.size()];
		return triplets.toArray(result);
	}
}
