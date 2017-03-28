package dp.assignment.longestcommonsubsequence;

import java.io.IOException;
import java.util.Scanner;

/**
 * Given two sequences, find the longest subsequence present in both of them.
 * (Not just the length, but the actual string)
 * 
 * The longest common subsequence (LCS) problem is the problem of finding the
 * longest subsequence common to all sequences in a set of sequences (often just
 * two sequences). It differs from problems of finding common substrings: unlike
 * substrings, subsequences are not required to occupy consecutive positions
 * within the original sequences. The longest common subsequence problem is a
 * classic computer science problem, the basis of data comparison programs such
 * as the diff utility, and has applications in bioinformatics. It is also
 * widely used by revision control systems such as Git for reconciling multiple
 * changes made to a revision-controlled collection of files. (Source
 * Wikipedia).
 * 
 * For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of
 * “abcdefg”. So a string of length 'n' has 2^n different possible subsequences.
 * 
 * LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3. LCS for
 * input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
 * 
 * Return empty string if there is no such common subsequence.
 * 
 * @author dwaraknathbs
 *
 */
public class LongestCommonSubsequence {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		String res;
		String _strX;
		try {
			_strX = in.nextLine();
		} catch (Exception e) {
			_strX = null;
		}

		String _strY;
		try {
			_strY = in.nextLine();
		} catch (Exception e) {
			_strY = null;
		}

		res = getLCS(_strX, _strY);
		System.out.println(res);

	}

	/**
	 * Build the cache and traverse the cache to get the common sequence
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	private static String getLCS(String str1, String str2) {

		int cache[][] = new int[str1.length() + 1][str2.length() + 1];

		char[] string1 = str1.toCharArray();
		char[] string2 = str2.toCharArray();
		int m = string1.length;
		int n = string2.length;
		initializeCache(cache, m, n);
		int lcsLength = lcs(string1, string2, m, n, cache);
		System.out.println(lcsLength);
		StringBuilder sb = buildCommonSequenceFromCache(cache, string1, string2);
		/**
		 * We need to reverse the order as we are reading from the end
		 */
		return sb.reverse().toString();
	}

	private static void initializeCache(int[][] cache, int m, int n) {
		for (int i = 0; i < m + 1; i++)
			for (int j = 0; j < n + 1; j++) {
				cache[i][j] = -1;
			}
	}

	/**
	 * Reading from the cache, we can build the string.start with m and n. If
	 * the elements are equal go to i-1 and j-1 ( traverse diagonally). Else
	 * find the max of (i,j-1) and (i-1,j) and take that course
	 * 
	 * @param cache
	 * @param string1
	 * @param string2
	 * @return
	 */
	private static StringBuilder buildCommonSequenceFromCache(int[][] cache, char[] string1, char[] string2) {
		int m = string1.length;
		int n = string2.length;
		StringBuilder sb = new StringBuilder();
		for (int i = m, j = n; i > 0 && j > 0;) {
			if (string1[i - 1] == string2[j - 1]) {
				sb.append(string1[i - 1]);
				i--;
				j--;
			} else {

				if (cache[i - 1][j] > cache[i][j - 1]) {
					i--;
				} else {
					j--;
				}
			}

		}
		return sb;
	}

	/**
	 * Basic idea
	 * 
	 * Compare from the last letters,
	 * 
	 * if they are equal LCS is 1 + LCS of the two strings of length l-1 and m-1
	 * respectively
	 * 
	 * else LCS is max of LCS( of string A minus last char and string B)
	 * 
	 * LCS(string A and String B minus last char)
	 * 
	 * 
	 * 
	 * @param string1
	 * @param string2
	 * @param m
	 * @param n
	 * @param cache
	 * @return
	 */
	private static int lcsRecursive(char[] string1, char[] string2, int m, int n, int[][] cache) {

		if (m == 0 || n == 0) {
			cache[m][n] = 0;
			return 0;
		}

		if (cache[m][n] == -1) {
			if (string1[m - 1] == string2[n - 1]) {
				cache[m][n] = 1 + lcsRecursive(string1, string2, m - 1, n - 1, cache);
			} else {
				cache[m][n] = Math.max(lcsRecursive(string1, string2, m - 1, n, cache),
						lcsRecursive(string1, string2, m, n - 1, cache));
			}
		}
		return cache[m][n];
	}

	/**
	 * Building the cache can be tricky. We get the urge to build from 0,0 to
	 * m,n. However it is convenient to build from 0,0 to m+1,n+1. And we
	 * compare the i-1<sup>th</sup> element to j-1<sup>th</sup> element
	 * 
	 * @param string1
	 * @param string2
	 * @param m
	 * @param n
	 * @param cache
	 * @return
	 */
	private static int lcs(char[] string1, char[] string2, int m, int n, int[][] cache) {

		for (int i = 0; i <= m; i++) {
			cache[i][0] = 0;
		}
		for (int i = 0; i <= n; i++) {
			cache[0][i] = 0;
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (string1[i - 1] == string2[j - 1]) {
					cache[i][j] = 1 + cache[i - 1][j - 1];
				} else {
					cache[i][j] = Math.max(cache[i - 1][j], cache[i][j - 1]);
				}
			}
		}
		return cache[m][n];
	}

}
