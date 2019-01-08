package leetcode.regularexpressionmatching;

import java.util.Arrays;

/**
 * 10. Regular Expression Matching Hard
 * 
 * 2035
 * 
 * 417
 * 
 * Favorite
 * 
 * Share Given an input string (s) and a pattern (p), implement regular
 * expression matching with support for '.' and '*'.
 * 
 * '.' Matches any single character. '*' Matches zero or more of the preceding
 * element. The matching should cover the entire input string (not partial).
 * 
 * Note:
 * 
 * s could be empty and contains only lowercase letters a-z. p could be empty
 * and contains only lowercase letters a-z, and characters like . or *. Example
 * 1:
 * 
 * Input: s = "aa" p = "a" Output: false Explanation: "a" does not match the
 * entire string "aa". Example 2:
 * 
 * Input: s = "aa" p = "a*" Output: true Explanation: '*' means zero or more of
 * the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes
 * "aa". Example 3:
 * 
 * Input: s = "ab" p = ".*" Output: true Explanation: ".*" means "zero or more
 * (*) of any character (.)". Example 4:
 * 
 * Input: s = "aab" p = "c*a*b" Output: true Explanation: c can be repeated 0
 * times, a can be repeated 1 time. Therefore it matches "aab". Example 5:
 * 
 * Input: s = "mississippi" p = "mis*is*p*." Output: false
 * 
 * @author dwaraknathbs
 *
 */
public class RegularExpressionMatching {
	public boolean isMatch(String s, String p) {

		int[][] result = new int[s.length() + 1][p.length() + 1];
		for (int[] res : result) {
			Arrays.fill(res, -1);
		}
		return isMatch(s, s.length(), p, p.length(), result);

	}

	public boolean isMatch(String s, int word, String p, int pat, int[][] result) {

		if (result[word][pat] == -1) {
			if (word <= 0) {
				int i = pat;
				while (i > 0) {
					if (p.charAt(i - 1) == '*') {
						i -= 2;
					} else {
						result[word][pat] = 0;
						break;
					}
				}
				if (i <= 0)
					result[word][pat] = 1;
				else
					result[word][pat] = 0;
			} else if (pat <= 0) {
				result[word][pat] = (word <= 0) ? 1 : 0;
			}

			else if (pat > 0 && s.charAt(word - 1) == p.charAt(pat - 1) || p.charAt(pat - 1) == '.') {
				result[word][pat] = isMatch(s, word - 1, p, pat - 1, result) ? 1 : 0;
			} else if (pat > 0 && p.charAt(pat - 1) == '*') {
				boolean zerooccurance = isMatch(s, word, p, pat - 2, result);
				boolean eatingup = ((s.charAt(word - 1) == p.charAt(pat - 2) || p.charAt(pat - 2) == '.')
						&& isMatch(s, word - 1, p, pat, result));
				result[word][pat] = (zerooccurance || eatingup) ? 1 : 0;
			} else {
				result[word][pat] = 0;
			}

		}

		return result[word][pat] == 1;

	}

}
