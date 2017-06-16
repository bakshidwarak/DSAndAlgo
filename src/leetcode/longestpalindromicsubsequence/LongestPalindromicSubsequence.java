

package leetcode.longestpalindromicsubsequence;

/**
 * Given a string s, find the longest palindromic subsequence's length in s. You
 * may assume that the maximum length of s is 1000. Example 1: Input: "bbbab"
 * Output: 4 One possible longest palindromic subsequence is "bbbb". Example 2:
 * Input: "cbbd" Output: 2 One possible longest palindromic subsequence is "bb".
 * Show Company Tags Show Tags Show Similar Problems
 * 
 * @author dwaraknathbs
 */
public class LongestPalindromicSubsequence {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    public int longestPalindromeSubseq(String s) {
        int[][] cache = new int[s.length()][s.length()];

        return longestPalindromeHelperIterative(s, 0, s.length() - 1, cache);
    }

    public int longestPalindromeHelperRecursionWithMemoiszation(String s, int l, int r, int[][] cache) {
        if (l > r) {
            cache[l][r] = 0;
            return cache[l][r];
        }
        if (l == r) {
            cache[l][r] = 1;
            return cache[l][r];
        } else if (cache[l][r] == -1) {
            if (s.charAt(l) == s.charAt(r)) {
                cache[l][r] = 2 + longestPalindromeHelperRecursionWithMemoiszation(s, l + 1, r - 1, cache);
            } else {
                cache[l][r] = Math.max(longestPalindromeHelperRecursionWithMemoiszation(s, l, r - 1, cache), longestPalindromeHelperRecursionWithMemoiszation(s, l + 1, r, cache));
            }

        }
        return cache[l][r];
    }

    public int longestPalindromeHelperIterative(String s, int l, int r, int[][] cache) {
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = 0; j < s.length(); j++) {
                if (i > j) {
                    cache[i][j] = 0;
                    continue;
                }
                if (i == j) {
                    cache[i][j] = 1;
                    continue;
                }
                if (s.charAt(i) == s.charAt(j)) {
                    cache[i][j] = 2 + cache[i + 1][j - 1];
                } else {
                    cache[i][j] = Math.max(cache[i][j - 1], cache[i + 1][j]);
                }
            }
        }

        return cache[l][r];
    }

}
