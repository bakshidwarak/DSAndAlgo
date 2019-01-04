package leetcode.deleteoperationoftwostrings;

import java.util.Arrays;

/**
 * 583. Delete Operation for Two Strings Medium
 * 
 * 581
 * 
 * 17
 * 
 * Favorite
 * 
 * Share Given two words word1 and word2, find the minimum number of steps
 * required to make word1 and word2 the same, where in each step you can delete
 * one character in either string.
 * 
 * Example 1: Input: "sea", "eat" Output: 2 Explanation: You need one step to
 * make "sea" to "ea" and another step to make "eat" to "ea". Note: The length
 * of given words won't exceed 500. Characters in given words can only be
 * lower-case letters.
 * 
 * @author dwaraknathbs
 *
 */
public class DeleteOperationOfTwoStrings {
	public int minDistance(String word1, String word2) {
		if (word1 == null && word2 != null)
			return word2.length();
		if (word2 == null && word1 != null)
			return word1.length();
		if (word1 == null && word2 == null)
			return 0;

		int[][] result = new int[word1.length() + 1][word2.length() + 1];
		for (int i = 0; i < result.length; i++) {
			Arrays.fill(result[i], -1);
		}

		return helper(word1, 0, word2, 0, result);

	}

	public int helper(String word1, int index1, String word2, int index2, int[][] result) {
		if (result[index1][index2] == -1) {
			if (index1 == word1.length()) {
				result[index1][index2] = word2.length() - index2;
			} else if (index2 == word2.length()) {
				result[index1][index2] = word1.length() - index1;
			} else if (word1.charAt(index1) == word2.charAt(index2)) {
				result[index1][index2] = helper(word1, index1 + 1, word2, index2 + 1, result);
			} else {
				result[index1][index2] = 1 + Math.min(helper(word1, index1 + 1, word2, index2, result),
						helper(word1, index1, word2, index2 + 1, result));
			}
		}

		return result[index1][index2];
	}

}
