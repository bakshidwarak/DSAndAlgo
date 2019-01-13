package leetcode.reversewordsinstringIII;

/**
 * 557. Reverse Words in a String III Easy
 * 
 * 526
 * 
 * 60
 * 
 * Favorite
 * 
 * Share Given a string, you need to reverse the order of characters in each
 * word within a sentence while still preserving whitespace and initial word
 * order.
 * 
 * Example 1: Input: "Let's take LeetCode contest" Output: "s'teL ekat edoCteeL
 * tsetnoc" Note: In the string, each word is separated by single space and
 * there will not be any extra space in the string.
 * 
 * @author dwaraknathbs
 *
 */
public class ReverseWordsInStringIII {
	public String reverseWords(String s) {
		char[] words = s.toCharArray();
		int startIndex = 0;
		for (int i = 0; i < words.length; i++) {

			if (s.charAt(i) == ' ') {
				reverse(words, startIndex, i - 1);
				startIndex = i + 1;
			}
		}
		reverse(words, startIndex, words.length - 1);

		return new String(words);

	}

	public void reverse(char[] words, int start, int end) {

		while (start < end) {
			char tmp = words[start];
			words[start] = words[end];
			words[end] = tmp;
			start++;
			end--;
		}

	}
}
