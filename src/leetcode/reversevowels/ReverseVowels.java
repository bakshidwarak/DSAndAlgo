package leetcode.reversevowels;

/**
 * Write a function that takes a string as input and reverse only the vowels of
 * a string.
 * 
 * Example 1: Given s = "hello", return "holle".
 * 
 * Example 2: Given s = "leetcode", return "leotcede".
 * 
 * Note: The vowels does not include the letter "y".
 * 
 * @author dwaraknathbs
 *
 */
public class ReverseVowels {
	public String reverseVowels(String s) {
		char[] input = s.toCharArray();
		int left = 0;
		int right = s.length() - 1;
		while (left < right) {
			if (isVowel(input[left]) && isVowel(input[right])) {
				swap(input, left, right);
				left++;
				right--;
			} else if (isVowel(input[left])) {
				right--;
			} else {
				left++;
			}

		}
		return new String(input);

	}

	public boolean isVowel(char ch) {
		return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I'
				|| ch == 'O' || ch == 'U';
	}

	public void swap(char[] input, int left, int right) {
		char temp = input[left];
		input[left] = input[right];
		input[right] = temp;
	}

}
