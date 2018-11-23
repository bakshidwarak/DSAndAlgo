package leetcode.longestsubscringwithoutrepeatingchars;

import java.util.Arrays;

/**
 * 3. Longest Substring Without Repeating Characters Medium 4244 198
 * 
 * 
 * Given a string, find the length of the longest substring without repeating
 * characters.
 * 
 * Example 1:
 * 
 * Input: "abcabcbb" Output: 3 Explanation: The answer is "abc", with the length
 * of 3. Example 2:
 * 
 * Input: "bbbbb" Output: 1 Explanation: The answer is "b", with the length of
 * 1. Example 3:
 * 
 * Input: "pwwkew" Output: 3 Explanation: The answer is "wke", with the length
 * of 3. Note that the answer must be a substring, "pwke" is a subsequence and
 * not a substring.
 * 
 * @author dwaraknathbs
 *
 */
public class LongestSubstringWithoutRepeatingChars {
	public int lengthOfLongestSubstring(String s) {

		int[] chars = new int[128];
		Arrays.fill(chars, -1);
		int maxLength = 0;
		int i = 0;
		int last = 0;
		for (; i < s.length(); i++) {
			//If the character is not already add to the map
			if (chars[s.charAt(i)] == -1) {
				chars[s.charAt(i)] = i;
				last = i;
			} else {
				//if a character is seen, calculate the length of the substring so far
				int le = i - last;
				maxLength = Math.max(maxLength, le);
				//The start position of the next substring is the next character after the repeating character. Update it with the latest index
				if (last < chars[s.charAt(i)] + 1)
					last = chars[s.charAt(i)] + 1;
				chars[s.charAt(i)] = i;

			}
		}
		int len = i - last;
		maxLength = Math.max(maxLength, len);

		return maxLength;
	}

}
