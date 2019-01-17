package leetcode.longestsubstringwithatmosttwodistinctchars;

import java.util.HashSet;

/**
 * 159. Longest Substring with At Most Two Distinct Characters Hard
 * 
 * 438
 * 
 * 7
 * 
 * Favorite
 * 
 * Share Given a string s , find the length of the longest substring t that
 * contains at most 2 distinct characters.
 * 
 * Example 1:
 * 
 * Input: "eceba" Output: 3 Explanation: t is "ece" which its length is 3.
 * Example 2:
 * 
 * Input: "ccaabbb" Output: 5 Explanation: t is "aabbb" which its length is 5.
 * 
 * @author dwaraknathbs
 *
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		if (s == null || s.length() == 0)
			return 0;
		HashSet<Character> set = new HashSet<>();
		if (s.length() == 1)
			return 1;
		int left = 0;
		int right = 1;
		set.add(s.charAt(left));
		int max = 0;
		while (right < s.length()) {
			char curr = s.charAt(right);
			if (set.contains(curr)) {
				right++;
				continue;
			}
			if (set.size() == 2) {
				max = Math.max(max, right - left);
				char ch = s.charAt(right - 1);
				left = right - 1;
				while (left >= 0 && s.charAt(left) == ch)
					left--;
				set.remove(s.charAt(left));
				left++;

			}
			set.add(s.charAt(right));
			right++;
		}
		max = Math.max(max, right - left);
		return max;

	}
}
