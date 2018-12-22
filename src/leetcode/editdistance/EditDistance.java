package leetcode.editdistance;

/**
 * 72. Edit Distance Hard
 * 
 * 1583
 * 
 * 20
 * 
 * Favorite
 * 
 * Share Given two words word1 and word2, find the minimum number of operations
 * required to convert word1 to word2.
 * 
 * You have the following 3 operations permitted on a word:
 * 
 * Insert a character Delete a character Replace a character Example 1:
 * 
 * Input: word1 = "horse", word2 = "ros" Output: 3 Explanation: horse -> rorse
 * (replace 'h' with 'r') rorse -> rose (remove 'r') rose -> ros (remove 'e')
 * Example 2:
 * 
 * Input: word1 = "intention", word2 = "execution" Output: 5 Explanation:
 * intention -> inention (remove 't') inention -> enention (replace 'i' with
 * 'e') enention -> exention (replace 'n' with 'x') exention -> exection
 * (replace 'n' with 'c') exection -> execution (insert 'u')
 * 
 * @author dwaraknathbs
 *
 */
public class EditDistance {
	public int minDistance(String word1, String word2) {
		int[][] result = new int[word1.length() + 1][word2.length() + 1];

		for (int i = word1.length(); i >= 0; i--)
			for (int j = word2.length(); j >= 0; j--) {
				if (i == word1.length())
					result[i][j] = word2.length() - j;
				else if (j == word2.length())
					result[i][j] = word1.length() - i;
				else if (word1.charAt(i) == word2.charAt(j)) {
					result[i][j] = result[i + 1][j + 1];
				} else {
					result[i][j] = 1 + Math.min(result[i + 1][j + 1], Math.min(result[i][j + 1], result[i + 1][j]));
				}
			}

		return result[0][0];

	}

	// return distanceHelper(word1,word2,0,0,result);

	public int distanceHelper(String word1, String word2, int index1, int index2, int[][] result) {
		if (result[index1][index2] == -1) {
			if (index1 == word1.length())
				result[index1][index2] = word2.length() - index2;
			else if (index2 == word2.length())
				result[index1][index2] = word1.length() - index1;
			else if (word1.charAt(index1) == word2.charAt(index2)) {
				result[index1][index2] = distanceHelper(word1, word2, index1 + 1, index2 + 1, result);
			} else {
				result[index1][index2] = 1 + Math.min(distanceHelper(word1, word2, index1 + 1, index2 + 1, result),
						Math.min(distanceHelper(word1, word2, index1, index2 + 1, result),
								distanceHelper(word1, word2, index1 + 1, index2, result)));
			}
		}

		return result[index1][index2];

	}
}
