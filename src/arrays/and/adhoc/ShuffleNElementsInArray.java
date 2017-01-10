package arrays.and.adhoc;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Given an array of size N shuffle it in such a way that all permutations are
 * equally likely
 * 
 * @author dwaraknathbs
 *
 */
public class ShuffleNElementsInArray {

	public static void main(String[] args) {
		int[] integerArray = { 1, 2, 3, 4, 5 };
		int count = 0;
		while (count < 100) {
			int[] shuffledArray = shuffleArray(integerArray);
			Arrays.stream(shuffledArray).forEach(System.out::print);
			System.out.println();
			count++;
		}

	}

	private static int[] shuffleArray(int[] integerArray) {
		for (int i = 0; i < integerArray.length; i++) {
			int random = ThreadLocalRandom.current().nextInt(i, integerArray.length);
			swap(integerArray, i, random);

		}
		return integerArray;
	}

	private static void swap(int[] integerArray, int i, int random) {
		int temp = integerArray[i];
		integerArray[i] = integerArray[random];
		integerArray[random] = temp;

	}

}
