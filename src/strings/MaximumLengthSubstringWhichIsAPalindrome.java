package strings;

/**
 * Given a string find the maximum length substring which is a palindrome
 * 
 * @author dwaraknathbs
 *
 */
public class MaximumLengthSubstringWhichIsAPalindrome {

	/**
	 * O(n3) solution. Two for loops and one for checking the palindrome. Have a
	 * outer loop for length, inner one for the beginning and check if each
	 * substring is a plaindrome and return when you find one as we are starting
	 * from the length
	 * 
	 * @param string
	 * @return
	 */
	private static String maxLengthPalindrome(String string) {
		for (int l = string.length(); l >= 1; l--) {
			for (int beg = 0; beg + l - 1 < string.length(); beg++) {
				if (isPalindrome(string, beg, beg + l - 1))
					return string.substring(beg, beg + l);

			}
		}
		return null;
	}

	private static boolean isPalindrome(String string, int beg, int l) {
		char[] chars = string.toCharArray();
		for (int i = beg, j = l; i <= j; i++, j--) {
			if (chars[i] != chars[j])
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		String maxLengthPalindrome = maxLengthPalindrome("aabaab");
		System.out.println(maxLengthPalindrome);
		maxLengthPalindrome = maxLengthPalindromeCentersApproach("aaaabb");
		System.out.println(maxLengthPalindrome);

	}

	/**
	 * Palindrome has a property.
	 * 
	 * If a String X is not a palindrome, string aXa is also not a palindrome
	 * 
	 * For a String aXb to be a palindrome X should be a palindrome and a and b
	 * should be same
	 * 
	 * The idea is to take each of the centers in the string and keep expanding
	 * 
	 * @param string
	 * @return
	 */
	private static String maxLengthPalindromeCentersApproach(String string) {
		int mxPaliBegin = 0;
		int maxPaliEnd = 0;
		int maxPaliLength = 0;
		char[] str = string.toCharArray();
		for (int c = 0; c < string.length(); c++) {
			int beg = c - 1;
			int end = c + 1;

			while (beg >= 0 && end < string.length() && str[beg] == str[end]) {
				beg--;
				end++;
			}
			/**
			 * When the while loop fails, we are now at a beg and end which do
			 * not constitute a palindrome, hence we revert it back to a state
			 * it was before it stopped being a palindrome
			 */
			beg++;
			end--;

			int currentLength = end - beg;
			if (currentLength > maxPaliLength) {
				mxPaliBegin = beg;
				maxPaliEnd = end;
				maxPaliLength = currentLength;
			}

			/**
			 * So far we only checked for odd length substrings ( i.e we picked
			 * a center and we started with one less and one more to the center
			 * ) , there could be a case when we need to look for centers around
			 * even length substrings ( i.e the center can itself be the
			 * beginning of the substring) within a string. for example string
			 * aaaabb has a max palindrome substring of aaaa , however the while
			 * loop above will only see with a in index=1 will be the center and
			 * the loop terminates when beg becomes <0 resulting in a substring
			 * of length 3
			 * 
			 * Now we need to do the same exercise like above, but this time beg
			 * is same as center and only end is center +1;
			 */
			beg = c;
			end = c + 1;

			while (beg >= 0 && end < string.length() && str[beg] == str[end]) {
				beg--;
				end++;
			}
			/**
			 * When the while loop fails, we are now at a beg and end which do
			 * not constitute a palindrome, hence we revert it back to a state
			 * it was before it stopped being a palindrome
			 */
			beg++;
			end--;

			currentLength = end - beg;
			if (currentLength > maxPaliLength) {
				mxPaliBegin = beg;
				maxPaliEnd = end;
				maxPaliLength = currentLength;
			}

		}

		return string.substring(mxPaliBegin, maxPaliEnd + 1);
	}
}
