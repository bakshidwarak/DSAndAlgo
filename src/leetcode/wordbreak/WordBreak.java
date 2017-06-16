package leetcode.wordbreak;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 
 * 139. Word Break
 * 
 * Given a non-empty string s and a dictionary wordDict containing a list of
 * non-empty words, determine if s can be segmented into a space-separated
 * sequence of one or more dictionary words. You may assume the dictionary does
 * not contain duplicate words.
 * 
 * For example, given s = "leetcode", dict = ["leet", "code"].
 * 
 * Return true because "leetcode" can be segmented as "leet code".
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class WordBreak {

	public boolean wordBreak(String s, List<String> wordDict) {

		int[] cache = new int[s.length() + 1];
		Arrays.fill(cache, -1);
		// return wordBreakHelper(s,0,wordDict);
		// return wordBreakHelperRecursiveWithMemoization(s,0,dict,cache);

		return wordBreakHelperIterative(s, wordDict);

	}

	public boolean wordBreakHelper(String s, int index, List<String> wordDict) {

		boolean result = false;
		for (int i = index; i < s.length(); i++) {
			if (i + 1 < s.length()) {
				result = result
						|| (wordDict.contains(s.substring(index, i + 1)) && wordBreakHelper(s, i + 1, wordDict));
			} else {
				result = result || (wordDict.contains(s.substring(index, i + 1)));
			}
		}
		return result;
	}

	public boolean wordBreakHelperRecursiveWithMemoization(String s, int index, HashSet<String> wordDict, int[] cache) {
		if (cache[index] == -1) {
			boolean result = false;
			for (int i = index; i < s.length(); i++) {
				if (i + 1 < s.length()) {
					result = result || (wordDict.contains(s.substring(index, i + 1))
							&& wordBreakHelperRecursiveWithMemoization(s, i + 1, wordDict, cache));
				} else {
					result = result || (wordDict.contains(s.substring(index, i + 1)));
				}
			}
			cache[index] = result ? 1 : 0;
		}
		return cache[index] == 1;
	}

	public boolean wordBreakHelperIterative(String s, List<String> wordDict) {
		int[] cache = new int[s.length() + 1];
		for (int j = s.length() - 1; j >= 0; j--) {
			boolean result = false;
			for (int i = j; i < s.length(); i++) {
				if (i + 1 < s.length()) {
					result = result || (wordDict.contains(s.substring(j, i + 1)) && (cache[i + 1] == 1));
				} else {
					result = result || (wordDict.contains(s.substring(j, i + 1)));
				}
			}
			cache[j] = result ? 1 : 0;
		}
		return cache[0] == 1;
	}
}