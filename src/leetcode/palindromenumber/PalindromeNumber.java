package leetcode.palindromenumber;

/**
 * 9. Palindrome Number Easy 1076 1125
 * 
 * 
 * Determine whether an integer is a palindrome. An integer is a palindrome when
 * it reads the same backward as forward.
 * 
 * Example 1:
 * 
 * Input: 121 Output: true Example 2:
 * 
 * Input: -121 Output: false Explanation: From left to right, it reads -121.
 * From right to left, it becomes 121-. Therefore it is not a palindrome.
 * Example 3:
 * 
 * Input: 10 Output: false Explanation: Reads 01 from right to left. Therefore
 * it is not a palindrome.
 * 
 * @author dwaraknathbs
 *
 */
public class PalindromeNumber {
	public boolean isPalindrome(int x) {
		if (x < 0)
			return false;
		int current = x;
		int reverseNum = 0;
		while (current != 0) {
			int last = current % 10;
			reverseNum = reverseNum * 10 + last;
			current = current / 10;
		}
		return x == reverseNum;

	}
}
