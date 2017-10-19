package leetcode.validpalindrome;

/**
 * Given a string, determine if it is a palindrome, considering only
 * alphanumeric characters and ignoring cases.
 * 
 * For example, "A man, a plan, a canal: Panama" is a palindrome. "race a car"
 * is not a palindrome.
 * 
 * Note: Have you consider that the string might be empty? This is a good
 * question to ask during an interview.
 * 
 * For the purpose of this problem, we define empty string as valid palindrome.
 * 
 * @author dwaraknathbs
 *
 */
public class ValidPalindrome {
	public boolean isPalindrome(String s) {
		if (s == null || s.isEmpty())
			return true;
		String input = s.toLowerCase();
		// return isPali(input,0,input.length()-1);
		for (int start = 0, end = input.length() - 1; start <= end;) {
			if (isNotAChar(input.charAt(start)) && isNotAChar(input.charAt(end))) {
				start++;
				end--;
			} else if (isNotAChar(input.charAt(start))) {
				start++;
			} else if (isNotAChar(input.charAt(end))) {
				end--;
			} else if (input.charAt(start) == input.charAt(end)) {
				start++;
				end--;
			} else {
				return false;
			}
		}
		return true;
	}

	public boolean isPali(String input, int start, int end) {
		if (start >= end) {
			return true;
		}

		if (isNotAChar(input.charAt(start)) && isNotAChar(input.charAt(end))) {
			return isPali(input, start + 1, end - 1);
		}
		if (isNotAChar(input.charAt(start))) {
			return isPali(input, start + 1, end);
		}
		if (isNotAChar(input.charAt(end))) {
			return isPali(input, start, end - 1);
		}
		if (input.charAt(start) == input.charAt(end)) {
			return isPali(input, start + 1, end - 1);
		}
		return false;
	}

	public boolean isNotAChar(char ch) {
		return (ch < 'a' || ch > 'z') && (ch < '0' || ch > '9');
	}
}
