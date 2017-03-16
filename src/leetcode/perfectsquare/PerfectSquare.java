package leetcode.perfectsquare;

/**
 * Given a positive integer num, write a function which returns True if num is a
 * perfect square else False.
 * 
 * Note: Do not use any built-in library function such as sqrt.
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class PerfectSquare {

	public static void main(String[] args) {
		System.out.println(isPerfectSquare(2147483647));

	}

	public static boolean isPerfectSquareTimeConsuming(int num) {

		int i = 1;
		while (i <= num) {
			int val = i * i;
			if (val == num)
				return true;
			i++;
		}

		return false;

	}

	public static boolean isPerfectSquare(int num) {

		long low = 1;
		long high = num;
		return isPerfectSquareBinarySearch(num, low, high);

	}

	private static boolean isPerfectSquareBinarySearch(int num, long low, long high) {
		if (low > high)
			return false;

		long mid = (low + high) / 2;

		long square = mid * mid;

		if (square == num)
			return true;

		if (square > num) {
			return isPerfectSquareBinarySearch(num, low, mid - 1);
		}
		return isPerfectSquareBinarySearch(num, mid + 1, high);

	}
}
