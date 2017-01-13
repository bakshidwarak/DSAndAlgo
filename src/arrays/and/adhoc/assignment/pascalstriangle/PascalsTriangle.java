package arrays.and.adhoc.assignment.pascalstriangle;

import java.util.Scanner;
/**
 * Pascal’s triangle is a triangular array of the binomial coefficients. Write a function that takes an integer value n as input and prints first n lines of the Pascal’s triangle. Following are the first 6 rows of Pascal’s Triangle.

			1  
			1 1 
			1 2 1 
			1 3 3 1 
			1 4 6 4 1 
			1 5 10 10 5 1 
 * @author dwaraknathbs
 *
 */
public class PascalsTriangle {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.close();
		printPascalsTrinagleUsingArrays(n);
		System.out.println();
		printPascalsTrinagleNoArrays(n);
	}

	private static void printPascalsTrinagleUsingArrays(int n) {
		int pascal[][] = new int[n][];
		for (int i = 0; i < n; i++) {
			pascal[i] = new int[i + 1];
			int j = 0;

			while (j < i + 1) {
				if (i == 0 || i == j || j == 0) {
					pascal[i][j] = 1;
				} else {
					pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
				}
				j++;
			}

		}
		for (int i = 0; i < n; i++) {
			System.out.println();
			for (int j = 0; j < i + 1; j++) {
				System.out.print(pascal[i][j] + " ");
			}
		}
	}

	private static void printPascalsTrinagleNoArrays(int n) {

		for (int line = 1; line <= n; line++) {
			int c = 1;
			for (int j = 1; j <= line; j++) {
				System.out.print(c + " ");
				c = c * (line - j) / j;
			}
			System.out.println();

		}

	}

}
