package leetcode.reversewordsinstring;

/**
 * 151. Reverse Words in a String Medium
 * 
 * 421
 * 
 * 1990
 * 
 * Favorite
 * 
 * Share Given an input string, reverse the string word by word.
 * 
 * Example:
 * 
 * Input: "the sky is blue", Output: "blue is sky the". Note:
 * 
 * A word is defined as a sequence of non-space characters. Input string may
 * contain leading or trailing spaces. However, your reversed string should not
 * contain leading or trailing spaces. You need to reduce multiple spaces
 * between two words to a single space in the reversed string. Follow up: For C
 * programmers, try to solve it in-place in O(1) space.
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class ReverseWordsInString {
	public String reverseWords(String s) {
		String[] input = s.trim().split("\\s+");

		int end = input.length - 1;
		int first = 0;
		while (first < end) {
			String tem = input[end];
			input[end] = input[first];
			input[first] = tem;
			first++;
			end--;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < input.length; i++) {
			System.out.println("val= " + input[i]);
			sb.append(input[i].trim());
			if (i != input.length - 1) {
				sb.append(" ");
			}
		}
		return sb.toString();

	}

}
