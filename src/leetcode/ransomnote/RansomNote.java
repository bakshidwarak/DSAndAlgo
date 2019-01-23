package leetcode.ransomnote;

/**
 * 383. Ransom Note Easy
 * 
 * 265
 * 
 * 97
 * 
 * Favorite
 * 
 * Share Given an arbitrary ransom note string and another string containing
 * letters from all the magazines, write a function that will return true if the
 * ransom note can be constructed from the magazines ; otherwise, it will return
 * false.
 * 
 * Each letter in the magazine string can only be used once in your ransom note.
 * 
 * Note: You may assume that both strings contain only lowercase letters.
 * 
 * canConstruct("a", "b") -> false canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true Accepted 100.5K Submissions 204.8K
 * 
 * @author dwaraknathbs
 *
 */
public class RansomNote {
	public boolean canConstruct(String ransomNote, String magazine) {

		int[] chars = new int[26];

		for (int i = 0; i < magazine.length(); i++) {
			chars[magazine.charAt(i) - 'a']++;
		}

		for (int i = 0; i < ransomNote.length(); i++) {
			chars[ransomNote.charAt(i) - 'a']--;
		}

		for (int i = 0; i < chars.length; i++) {
			if (chars[i] < 0)
				return false;
		}

		return true;
	}
}
