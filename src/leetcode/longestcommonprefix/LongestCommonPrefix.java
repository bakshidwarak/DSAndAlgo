package leetcode.longestcommonprefix;

/**
 * 14. Longest Common Prefix Easy 958 1045
 * 
 * 
 * Write a function to find the longest common prefix string amongst an array of
 * strings.
 * 
 * If there is no common prefix, return an empty string "".
 * 
 * Example 1:
 * 
 * Input: ["flower","flow","flight"] Output: "fl" Example 2:
 * 
 * Input: ["dog","racecar","car"] Output: "" Explanation: There is no common
 * prefix among the input strings. Note:
 * 
 * All given inputs are in lowercase letters a-z.
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class LongestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {

		if (strs == null || strs.length == 0)
			return "";

		int count = lcs(strs, 0);

		if (count == 0)
			return "";

		return strs[0].substring(0, count);

	}

	int lcs(String[] strs, int start) {
		if (strs[0] == null || strs[0].isEmpty() || strs[0].length() <= start)
			return 0;
		char ch = strs[0].charAt(start);
		for (int i = 1; i < strs.length; i++) {

			if (strs[i].length() <= start || ch != strs[i].charAt(start))
				return 0;
		}

		return 1 + lcs(strs, start + 1);
	}
}
