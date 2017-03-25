package recursion.assignment.pow;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Double power Implement a power function to raise a double to an int power,
 * including negative powers.
 * 
 * e.g. pow(double d, int p) should give 'd' raised to power 'p'.
 * 
 * Of course, please don't use in-built methods like pow(). Idea is to implement
 * that using recursion.
 * 
 * 
 * Solution:
 * http://stackoverflow.com/questions/101439/the-most-efficient-way-to-implement-an-integer-based-power-function-powint-int
 * 
 * Suggested time: 10 minutes to do a brute-force and 15 with a trick that
 * optimizes it.
 * 
 * Note: In HackerRank, Double is simulated with "float".
 * 
 * @author dwaraknathbs
 *
 */
public class Power {

	/*
	 * Complete the function below.
	 */

	static float pow(float dblbase, int ipower) {
		if (ipower < 0) {
			dblbase = 1 / dblbase;
			ipower = ipower * -1;
		}

		float[] powerTable = new float[ipower + 1];
		Arrays.fill(powerTable, 0);

		return powerHelperFast(dblbase, ipower, powerTable);

	}

	/**
	 * Straightforward implementation by recursively multiplying with lower
	 * powers
	 * 
	 * @param num
	 * @param pow
	 * @return
	 */
	static float powerHelper(float num, int pow) {
		System.out.println(num + "," + pow);
		if (pow == 0) {
			return 1;
		}
		return num * powerHelper(num, pow - 1);
	}

	/**
	 * Optimize by making two recursive calls a^n = a^n/2 * a^n/2
	 * 
	 * In this way we could memoize and do it faster
	 * @param num
	 * @param pow
	 * @param powerTable
	 * @return
	 */
	static float powerHelperFast(float num, int pow, float[] powerTable) {
		if (powerTable[pow] == 0) {
			System.out.println(num + "," + pow);
			if (pow == 0) {
				powerTable[pow] = 1;

			} else if (pow == 1) {
				powerTable[pow] = num;
			} else {
				powerTable[pow] = powerHelperFast(num, pow / 2, powerTable)
						* powerHelperFast(num, pow - pow / 2, powerTable);
			}
		}
		return powerTable[pow];

	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		float res;
		float _dblbase;
		_dblbase = Float.parseFloat(in.nextLine().trim());

		int _ipower;
		_ipower = Integer.parseInt(in.nextLine().trim());

		res = pow(_dblbase, _ipower);
		System.out.println(String.valueOf(res));

	}

}
