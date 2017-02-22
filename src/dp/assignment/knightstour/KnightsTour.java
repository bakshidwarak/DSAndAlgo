package dp.assignment.knightstour;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Knight's tour! Given a phone keypad as shown below:
 * 
 * <pre>
 * 1 2 3 
 * 4 5 6 
 * 7 8 9 
 *   0
 * </pre>
 * 
 * How many different 10-digit numbers can be formed starting from 1? The
 * constraint is that the movement from 1 digit to the next is similar to the
 * movement of the Knight in a chess game.
 * 
 * For eg. if we are at 1 then the next digit can be either 6 or 8 if we are at
 * 6 then the next digit can be 1, 7 or 0.
 * 
 * Repetition of digits are allowed - 1616161616 is a valid number. The problem
 * requires us to just give the count of 10-digit numbers and not necessarily
 * list the numbers.
 * 
 * Find a polynomial time solution, based on Dynamic Programming.
 * 
 * Solution:
 * http://stackoverflow.com/questions/2893470/generate-10-digit-number-using-a-phone-keypad
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class KnightsTour {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int res;
		int _startdigit;
		_startdigit = Integer.parseInt(in.nextLine().trim());

		int _phonenumberlength;
		_phonenumberlength = Integer.parseInt(in.nextLine().trim());

		res = numPhoneNumbers(_startdigit, _phonenumberlength);
		System.out.println(String.valueOf(res));

	}

	private static int numPhoneNumbers(int _startdigit, int _phonenumberlength) {

		int startx = 0;
		int starty = 0;
		int[][] numPad = new int[4][3];
		for (int i = 0, num = 1; i < numPad.length; i++) {
			for (int j = 0; j < numPad[i].length; j++) {
				if (num == _startdigit) {
					startx = i;
					starty = j;
				}
				numPad[i][j] = num++;
			}

		}
		numPad[3][0] = -1;
		numPad[3][2] = -1;
		numPad[3][1] = 0;

		ArrayList<Integer> sb = new ArrayList<>();
		int[][] cache = new int[10][_phonenumberlength + 1];
		Arrays.stream(cache).forEach(a -> Arrays.fill(a, -1));
		return numPhoneNumbersIterative(numPad, startx, starty, _phonenumberlength);
	}

	/**
	 * Recursive solution- Basically number of N digit numbers starting from 1 =
	 * Number of N-1 digit numbers starting from 6 + Number of N-1 digit numbers
	 * starting from 8 and so on.
	 * 
	 * @param numPad
	 * @param startx
	 * @param starty
	 * @param phonenumberlength
	 * @param sb
	 * @return
	 */
	private static int numPhoneNumbersRecursive(int[][] numPad, int startx, int starty, int phonenumberlength,
			ArrayList<Integer> sb) {

		if (phonenumberlength == 1) {
			sb.add(numPad[startx][starty]);
			sb.forEach(System.out::print);
			System.out.println();
			sb.remove(sb.size() - 1);
			return 1;

		}
		int[] range = { -1, -2, 1, 2 };

		int count = 0;
		for (int i = 0; i < range.length; i++)
			for (int k = 0; k < range.length; k++) {
				if (Math.abs(range[i]) == Math.abs(range[k]))
					continue;

				int x = startx + range[i];
				int y = starty + range[k];

				if (x < 0 || x > 3 || y < 0 || y > 2 || numPad[x][y] == -1) {
					continue;
				}

				sb.add(numPad[startx][starty]);

				count += numPhoneNumbersRecursive(numPad, x, y, phonenumberlength - 1, sb);
				sb.remove(sb.size() - 1);

			}

		return count;
	}

	/**
	 * Adding a simple cache to store the result of the number to its length
	 * 
	 * @param numPad
	 * @param startx
	 * @param starty
	 * @param phonenumberlength
	 * @param cache
	 * @return
	 */
	private static int numPhoneNumbersRecursiveCache(int[][] numPad, int startx, int starty, int phonenumberlength,
			int[][] cache) {

		if (cache[numPad[startx][starty]][phonenumberlength] == -1) {
			if (phonenumberlength == 1) {
				cache[numPad[startx][starty]][phonenumberlength] = 1;

			} else {
				int[] range = { -1, -2, 1, 2 };

				int count = 0;
				for (int i = 0; i < range.length; i++)
					for (int k = 0; k < range.length; k++) {
						if (Math.abs(range[i]) == Math.abs(range[k]))
							continue;

						int x = startx + range[i];
						int y = starty + range[k];

						if (x < 0 || x > 3 || y < 0 || y > 2 || numPad[x][y] == -1) {
							continue;
						}

						count += numPhoneNumbersRecursiveCache(numPad, x, y, phonenumberlength - 1, cache);

					}
				cache[numPad[startx][starty]][phonenumberlength] = count;

			}
		}
		return cache[numPad[startx][starty]][phonenumberlength];
	}

	/**
	 * Building the cache bottom up. However we need to note that cache is a 3D
	 * array instead of 2D in previous case as we need to keep track of the
	 * startx and y to track the Knight path property
	 * 
	 * @param numPad
	 * @param startx
	 * @param starty
	 * @param phonenumberlength
	 * @return
	 */
	private static int numPhoneNumbersIterative(int[][] numPad, int startx, int starty, int phonenumberlength) {

		int[][][] cache = new int[4][3][phonenumberlength + 1];
		for (int x = 0; x < numPad.length; x++)
			for (int y = 0; y < numPad[x].length; y++)
				cache[x][y][1] = 1;
		for (int length = 2; length <= phonenumberlength; length++)
			for (int currx = 0; currx < numPad.length; currx++)
				for (int curry = 0; curry < numPad[currx].length; curry++)

				{

					int[] range = { -1, -2, 1, 2 };

					int count = 0;
					for (int i = 0; i < range.length; i++)
						for (int k = 0; k < range.length; k++) {
							if (Math.abs(range[i]) == Math.abs(range[k]))
								continue;

							int x = currx + range[i];
							int y = curry + range[k];

							if (x < 0 || x > 3 || y < 0 || y > 2 || numPad[x][y] == -1) {
								continue;
							}

							count += cache[x][y][length - 1];

						}
					cache[currx][curry][length] = count;

				}
		return cache[startx][starty][phonenumberlength];
	}
}
