package strings;

/**
 * Substring search ia a critical algorithm to check if a substring exists in a
 * string.
 * 
 * @author dwaraknathbs
 *
 */
public class StringSearch {

	public static void main(String[] args) {
		System.out.println(kmp("aazaabaa", "aab"));

	}

	/**
	 * Brute force algorithm, keep matching one character at a time if found a
	 * mismatch start again comparing from first letter in the pattern.
	 * 
	 * O(mn)
	 * 
	 * @param strText
	 * @param strPattern
	 * @return
	 */
	static boolean bruteForceStringSearch(String strText, String strPattern) {

		char[] original = strText.toCharArray();
		char[] pattern = strPattern.toCharArray();
		int patternStart = -1;

		for (int s = 0; s < original.length - pattern.length; s++) {
			patternStart = s;
			for (int i = s, j = 0; j < pattern.length; i++, j++) {
				if (original[i] != pattern[j]) {
					patternStart = -1;
					break;
				}
			}
			if (patternStart > 0)
				return true;
		}

		return false;
	}

	static int prime = 31;

	/**
	 * Rabin karp is a faster substring search with preprocessing. Idea is to
	 * create a hash of the pattern and keep generating hash for every m letter
	 * string in the text. If the hashses match check character by character (
	 * as there could be hash collisions)
	 * 
	 * Also the hash generated is a Rolling Hash- generate the hash of next
	 * characters from the prev hash value so we get constant time in generating
	 * hash
	 * 
	 * 
	 * worst case time complexity O(mn) this happens when hash function is poor
	 * and we end up comparing all characters every time
	 * 
	 * @param strText
	 * @param strPattern
	 * @return
	 */
	static boolean rabinkarp(String strText, String strPattern) {
		char[] original = strText.toCharArray();
		char[] pattern = strPattern.toCharArray();
		double patternHash = createHash(pattern, pattern.length - 1);
		double wordHash = createHash(original, pattern.length - 1);

		if (patternHash == wordHash && strText.substring(0, strPattern.length()).equals(strPattern))
			return true;

		for (int s = 1; s < original.length - pattern.length; s++) {
			wordHash = getRollingHash(original, s - 1, s + pattern.length - 1, wordHash, pattern.length);
			if (patternHash == wordHash && strText.substring(s, s + pattern.length).equals(strPattern))
				return true;
		}

		return false;
	}

	/**
	 * Get rolling hash- Given the previous hash value and the new character
	 * added to the string window generate the hash
	 * 
	 * @param original
	 * @param outgoingCharIndex
	 * @param incomingCharIndex
	 * @param wordHash
	 * @param patternLength
	 * @return
	 */
	static double getRollingHash(char[] original, int outgoingCharIndex, int incomingCharIndex, double wordHash,
			int patternLength) {
		double hash = wordHash;
		hash = hash - original[outgoingCharIndex];
		hash = hash / prime;
		hash = (hash + original[incomingCharIndex] * Math.pow(prime, patternLength - 1));
		return hash;
	}

	static double createHash(char[] pattern, int end) {
		double hash = 0;

		for (int i = 0; i <= end; i++) {
			hash = (hash + pattern[i] * Math.pow(prime, i));
		}
		return hash;
	}

	/**
	 * KMP is a faster algorithm with O(m+n) complexity. This algorithm too
	 * requires preprocessing. The basic idea is when we compare every character
	 * if a mismatch is found instead of restarting comparison from the first
	 * character of the pattern we make use of the fact that we might have
	 * processed a part of the next window in the previos attempt of comparison
	 * 
	 * We do this by calculating a prefix array. Prefix array contains the
	 * length of the longest prefix which is also a suffix for the string till
	 * that index.
	 * 
	 * While comparing character by character when a mismatch is found we set
	 * the first character of the pattern to be compared to the prefix value at
	 * the index-1
	 * 
	 * @param strText
	 * @param strPattern
	 * @return
	 */
	static boolean kmp(String strText, String strPattern) {

		char[] original = strText.toCharArray();
		char[] pattern = strPattern.toCharArray();
		int[] prefix = generatePrefixArray(pattern);
		int k = 0;
		boolean isPatternFound = false;
		for (int j = 0; j < original.length; j++) {
			while (k > 0 && pattern[k] != original[j]) {
				k = prefix[k - 1];
			}
			if (pattern[k] == original[j]) {
				k++;
			}
			if (k == pattern.length) {
				System.out.println("Pattern occurs at shift" + (j - pattern.length + 1));
				isPatternFound = true;
				k = prefix[k - 1];
			}

		}

		return isPatternFound;
	}

	/**
	 * Generates the prefix array. Basically the length of the longest prefix
	 * that is also a suffix at any given index k
	 * 
	 * @param pattern
	 * @return
	 */
	private static int[] generatePrefixArray(char[] pattern) {
		int[] prefix = new int[pattern.length];
		int k = 0;
		prefix[0] = 0;
		for (int j = 1; j < pattern.length; j++) {
			while (k > 0 && pattern[k] != pattern[j]) {
				k = prefix[k - 1];
			}
			if (pattern[k] == pattern[j]) {
				k++;
			}
			prefix[j] = k;
		}
		return prefix;
	}

}
