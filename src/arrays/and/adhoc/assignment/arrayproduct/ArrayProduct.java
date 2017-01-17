package arrays.and.adhoc.assignment.arrayproduct;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Given an array of numbers, nums, return an array of numbers products, where
 * products[i] is the product of all nums[j], j != i.
 * 
 * Input : [1, 2, 3, 4, 5] Output: [(2*3*4*5), (1*3*4*5), (1*2*4*5), (1*2*3*5),
 * (1*2*3*4)] = [120, 60, 40, 30, 24] You must do this in O(N) without using
 * division.
 * 
 * @author dwaraknathbs
 *
 */
public class ArrayProduct {
	
	

	public static void main(String a[]) throws Exception {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String args[]=line.split("\\s");
		int zeroesCount = 0;
		int[] originalArray = new int[args.length];
		int[] productsArray = new int[args.length];
		// Initialize the array to 0s
		for (int i = 0; i < productsArray.length; i++) {
			productsArray[i] = 0;
		}
		// Convert the string Array to int array( Java intricacy)
		for (int i = 0; i < args.length; i++) {
			int intValue = Integer.parseInt(args[i]);
			originalArray[i] = intValue;
			if (intValue == 0)
				zeroesCount++;// Count the number of zeroes
		}
		// If the number of zeroes is greater than 1 all the products will be
		// zero
		if (zeroesCount >= 2) {
			printArray(productsArray);
			return;
		}
		/**
		 * a divides a*b*c i.e a*b*c is a multiple of a, which means if we need
		 * to compute (a*b*c)/a , it is equivalent to not including a at all in
		 * the product
		 * 
		 * (a*b*c)/a = b*c [ Not multiply by a at all in the first place
		 * 
		 * 
		 * Hence first compute the cumulative products starting from 1 sans the
		 * last item and again traverse back and compute the cumulative product
		 * sans the first item
		 */
		int p = 1; 
		for (int i = 0; i < originalArray.length; i++) {
			productsArray[i] = p;
			p *= originalArray[i];
		}
		p = 1;
		for (int i = originalArray.length - 1; i >= 0; i--) {
			productsArray[i] *= p;
			p *= originalArray[i];
		}
		printArray(productsArray);
	}

	public static void printArray(int[] array) {
		Arrays.stream(array).forEach(System.out::print);
	}

}
