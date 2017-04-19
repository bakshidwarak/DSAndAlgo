package leetcode.validanagrams;

/**
 * 
 * 242. Valid Anagram
 * 
 * 
 * Given two strings s and t, write a function to determine if t is an anagram
 * of s.
 * 
 * For example, s = "anagram", t = "nagaram", return true. s = "rat", t = "car",
 * return false.
 * 
 * Note: You may assume the string contains only lowercase alphabets.
 * 
 * Follow up: What if the inputs contain unicode characters? How would you adapt
 * your solution to such case?
 * 
 * @author dwaraknathbs
 *
 */
public class ValidAnagrams {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * There are multiple approaches.
	 * 
	 * 1) We could sort the strings and compare one character at a time. In
	 * which case the complexity is nlogn but no extra space
	 * 
	 * 2)In our case here we could use constant space as it is ASCII( increased
	 * space if it is UTF)
	 * 
	 * Keep count of the frequency of each of the characters in one string, keep
	 * decrementing the count when we see the next string. Finally if the array
	 * is not zero the strings are not an anagram
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isAnagram(String s, String t) {
		int[] chars = new int[256];// If it is Unicode it will be 2^8 or 2^16 or
									// 2^32 depending on the UTF encoding format
		for (int i = 0; i < s.length(); i++) {
			chars[s.charAt(i) - 'a']++;
		}
		for (int i = 0; i < t.length(); i++) {
			chars[t.charAt(i) - 'a']--;
		}
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] != 0)
				return false;
		}
		return true;
	}

}
