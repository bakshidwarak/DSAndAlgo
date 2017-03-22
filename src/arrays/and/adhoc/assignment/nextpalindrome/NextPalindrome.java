package arrays.and.adhoc.assignment.nextpalindrome;

import java.io.IOException;
import java.util.Scanner;

/**
 * Given a number, find the next palindromic number (This question is quite
 * rampant. Not sure why)
 * 
 * Given a number, find the next smallest palindromic number, larger than this
 * number. e.g Input: 23545 Output: 23632 (This is a palindromic number, and
 * bigger than the input. There is no palindromic number less than this and
 * bigger than the input)
 * 
 * Input: 99 Output: 101
 * 
 * Input: 6789876 Output: 6790976 (Note that the input may or may not be a
 * palindrome itself)
 * 
 * Input: 8998 (Note that input can have even number of digits) Output: 9009
 * 
 * 
 * 
 * 
 * Solution:
 * http://www.geeksforgeeks.org/given-a-number-find-next-smallest-palindrome-larger-than-this-number/
 * 
 * @author dwaraknathbs
 *
 */
public class NextPalindrome {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int res;
		int _iInputNum;
		_iInputNum = Integer.parseInt(in.nextLine().trim());

		res = nextPalindrome(_iInputNum);
		System.out.println(String.valueOf(res));

	}

	private static int nextPalindrome(int _iInputNum) {

		int num = _iInputNum;
		int length = getNumberOfDigits(num);
		int[] numArray = getNumberAsArrray(_iInputNum, length);

		int result = getNextPalindromicNumber(numArray);

		return result;
	}

	private static int[] getNumberAsArrray(int _iInputNum, int length) {
		int num;
		int[] numArray = new int[length];
		num = _iInputNum;
		int i = length - 1;
		while (num > 0) {

			numArray[i] = num % 10;
			num = num / 10;
			i--;
		}
		return numArray;
	}

	private static int getNumberOfDigits(int num) {
		int length = 0;
		while (num > 0) {
			length++;
			num = num / 10;
		}
		return length;
	}

	/**
	 * We could keep increasing the number till we find a palindrome. This will
	 * be of O(sqrt(n)). We can do better than that by divide and conquer.
	 * 
	 * Key Idea: Starting from middle we keep comparing the numbers to right and
	 * numbers to left.
	 * 
	 * Case 1: If the left and right numbers are equal( i. e the input number is
	 * a palindrome) -> Increment the middle number, propagate the carry to the
	 * left and mirror the left side to the right
	 * 
	 * Case 2 : If the number at left > number at right -> Simply mirror the
	 * left to right
	 * 
	 * Case 3 : If the number to right is > number to Left ->Increment the
	 * middle number, propagate the carry to the left and mirror the left side
	 * to the right
	 * 
	 * In all the cases we increment the number, there is a chance all the
	 * numbers are 9. In which case there is a carry of 1 that needs to be
	 * handled. In which case we add one to both left and right
	 * 
	 * @param arr
	 * @return
	 */
	private static int getNextPalindromicNumber(int[] arr) {

		int left;
		int right;
		boolean appendOne = false;

		int length = arr.length;
		int mid = (length - 1) / 2;
		if (length % 2 == 0) {
			left = mid;
			right = mid + 1;
		} else {
			left = mid;
			right = mid;
		}
		int i = left;
		int j = right;
		for (; i >= 0 && j < length; j++, i--) {
			if (arr[i] != arr[j])
				break;
		}

		/**
		 * Check if the num to the left and right match
		 */
		if (i >= 0 && j < length) {
			/**
			 * The given number is not a palindrome and we found that the left
			 * is greater than right
			 */
			if (arr[i] > arr[j]) {
				mirrorLeftToRight(arr, left, right);
			} else {
				/**
				 * If number on the right is greater it means we need to
				 * increment the mid to get a larger number. We increment the
				 * number from the middle and if the carry is overflowing the
				 * appendOne is set to true
				 */
				appendOne = incrementFromMid(arr, left, appendOne);

				mirrorLeftToRight(arr, left, right);
			}

		} else {
			/**
			 * The number is already a palindrome in which case we increment the
			 * number in the middle and propagate carry and handle overflow
			 */
			appendOne = incrementFromMid(arr, left, appendOne);
			mirrorLeftToRight(arr, left, right);
		}

		int result = convertArrayToIntAndAppendOneIfNeedBe(arr, appendOne);
		return result;
	}

	/**
	 * Converts a given int array to int. If appendOne is set then it adds one
	 * to beginning and end of the number
	 * 
	 * @param arr
	 * @param appendOne
	 * @return
	 */
	private static int convertArrayToIntAndAppendOneIfNeedBe(int[] arr, boolean appendOne) {
		int result = 0;
		for (int k = 0; k < arr.length; k++) {
			result = result * 10 + arr[k];
		}

		if (appendOne) {
			result = (int) (1 * Math.pow(10, 2) + 1);
		}
		return result;
	}

	/**
	 * Increment the middle element and propagate the carry to the left
	 * 
	 * @param arr
	 * @param left
	 * @param appendOne
	 * @return
	 */
	private static boolean incrementFromMid(int[] arr, int left, boolean appendOne) {
		int carry = 1;

		int k = left;
		while (k >= 0) {
			int num = arr[k] + carry;
			arr[k] = num % 10;
			carry = num / 10;
			k--;
		}
		if (carry != 0)
			appendOne = true;
		return appendOne;
	}

	/**
	 * Mirror the left side of the array to the right
	 * 
	 * @param arr
	 * @param left
	 * @param right
	 */
	private static void mirrorLeftToRight(int[] arr, int left, int right) {

		int k = left;
		int p = right;
		while (k >= 0) {

			arr[p] = arr[k];
			k--;
			p++;
		}
	}

}
