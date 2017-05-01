package strings.assignment;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Longest Substring with At Most Two Distinct Characters Given a string, find
 * the length of the longest substring T that contains at most 2 distinct
 * characters.
 * 
 * For example, Given s = “eceba”,
 * 
 * T is "ece" which its length is 3.
 * 
 * IF there are no such substrings (all same characters), then print nothing If
 * there multiple such strings, then print any one
 * 
 * Solution: Take inspiration from:
 * http://www.geeksforgeeks.org/find-the-longest-substring-with-k-unique-characters-in-a-given-string/
 * 
 * @author dwaraknathbs
 *
 */
public class LongestSubstringWithUtmostTwoDistinctCharacters {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		String res;
		String _strText;
		try {
			_strText = in.nextLine();
		} catch (Exception e) {
			_strText = null;
		}

		res = longestSub(_strText);
		System.out.println(res);

	}

	/**
	 * Basic idea is to have a seen array for each of thee characters. Here the
	 * assumption is the chars are only alphabetic. The array size would vary if
	 * it is ASCII or UTF
	 * 
	 * I maintain a window between current start and current end.
	 * 
	 * @param strText
	 * @return
	 */
	static String longestSub(String strText) {

		char[] chars = strText.toCharArray();
		int[] seen = new int[26];
		int maxStartIndex = 0;
		int maxLength = 0;
		int currentStart = 0;
		int currentEnd = 0;
		for (int i = 0; i < chars.length; i++) {
			/**
			 * As I visit every character I increase the count corresponding to
			 * the character
			 */
			seen[chars[i] - 'a']++;
			currentEnd++;

			/**
			 * Once I process current character I sweep the seen array to check
			 * if the number of unique characters is < 2. If it is >2 I
			 * decrement the count from the start of the window(currentStart)
			 */
			while (!containsMaxKUniqueChars(seen, 2)) {
				seen[chars[currentStart] - 'a']--;
				currentStart++;
			}

			/**
			 * Keep a running track of the maxLength of unique chars thus far
			 */
			if (currentEnd - currentStart > maxLength) {
				maxLength = currentEnd - currentStart;
				maxStartIndex = currentStart;
			}
		}

		/**
		 * Check if my string has atleast 2 unique chars. If not empty string
		 * has to be returned
		 */
		if (countSeenChars(seen) >= 2)

			return strText.substring(maxStartIndex, maxStartIndex + maxLength);

		return "";

	}

	private static long countSeenChars(int[] seen) {

		return Arrays.stream(seen).filter(e -> e > 0).count();
	}

	private static boolean containsMaxKUniqueChars(int[] seen, int k) {
		return countSeenChars(seen) <= k;
	}

}
