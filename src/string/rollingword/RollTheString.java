package string.rollingword;

import java.util.Arrays;

public class RollTheString {

	public static void main(String[] args) {
		System.out.println(rollTheStringFaster("vwxyz", new int[] { 1, 2, 3, 4, 5 }));
		System.out.println(rollTheStringFaster("abz", new int[] { 1, 2, 3 }));
	}

	/**
	 * Rolls the character array by performing each of the operation
	 * 
	 * O(mn) where m is length of the rolls array and n is the length of the
	 * string
	 * 
	 * @param s
	 * @param roll
	 * @return
	 */
	static String rollTheString(String s, int[] roll) {

		char[] strArray = s.toCharArray();
		/**
		 * For each value in the rolls array
		 */
		for (int i = 0; i < roll.length; i++) {
			int currentroll = roll[i];
			/**
			 * Roll characters till the roll element
			 */
			for (int j = 0; j < currentroll; j++) {
				rollTheString(strArray, j);
			}
		}
		return String.valueOf(strArray);

	}

	/**
	 * Rolls the character in index j of the char array by 1
	 * 
	 * @param strArray
	 * @param j
	 */
	private static void rollTheString(char[] strArray, int j) {
		/**
		 * As the character range is given as lower case a-z keeping an array
		 * for convenience
		 */
		char[] alphabets = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
				's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

		/**
		 * Finding out the index of the next character after rolling. Similar to
		 * handling of circular arrays
		 */
		int index = ((strArray[j] - 'a') + 1) % 26;
		strArray[j] = alphabets[index];

	}

	/**
	 * Basic idea is I create a prefixSum array. At any ith index in the array,
	 * the total sum of all the elements till i gives that total number of
	 * transformations for the ith element of the string
	 * 
	 * How we do is as we see a particular roll, we increment the first element
	 * by 1 ( as irrespective of the value of the roll the first element will
	 * undergo a transformation) and decrement the rolli by 1
	 * 
	 * Finally we sweep the prefix array and maintain a running total. For any
	 * index i of the char array we perform the running total number of
	 * transformations
	 * 
	 * Complexity O(m+n)
	 * 
	 * @param s
	 * @param roll
	 * @return
	 */
	static String rollTheStringFaster(String s, int[] roll) {

		char[] strArray = s.toCharArray();

		int[] prefixSum = new int[s.length()];
		Arrays.fill(prefixSum, 0);
		/**
		 * Creating the prefix array
		 */
		for (int i = 0; i < roll.length; i++) {
			int currentroll = roll[i];
			prefixSum[0]++;
			if (currentroll < roll.length)
				prefixSum[currentroll]--;
		}
		int transforms = 0;
		for (int i = 0; i < prefixSum.length; i++) {
			transforms += prefixSum[i];
			rollTheStringNTimes(strArray, i, transforms);
		}
		return String.valueOf(strArray);

	}

	/**
	 * Rolls the character in index j of the char array by n
	 * 
	 * @param strArray
	 * @param j
	 */
	private static void rollTheStringNTimes(char[] strArray, int j, int n) {
		/**
		 * As the character range is given as lower case a-z keeping an array
		 * for convenience
		 */
		char[] alphabets = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
				's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

		/**
		 * Finding out the index of the next character after rolling. Similar to
		 * handling of circular arrays
		 */
		int index = ((strArray[j] - 'a') + n) % 26;
		strArray[j] = alphabets[index];

	}

}
