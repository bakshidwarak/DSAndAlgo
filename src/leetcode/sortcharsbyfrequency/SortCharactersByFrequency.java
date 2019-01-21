package leetcode.sortcharsbyfrequency;

/**
 * 451. Sort Characters By Frequency Medium
 * 
 * 593
 * 
 * 53
 * 
 * Favorite
 * 
 * Share Given a string, sort it in decreasing order based on the frequency of
 * characters.
 * 
 * Example 1:
 * 
 * Input: "tree"
 * 
 * Output: "eert"
 * 
 * Explanation: 'e' appears twice while 'r' and 't' both appear once. So 'e'
 * must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * Example 2:
 * 
 * Input: "cccaaa"
 * 
 * Output: "cccaaa"
 * 
 * Explanation: Both 'c' and 'a' appear three times, so "aaaccc" is also a valid
 * answer. Note that "cacaca" is incorrect, as the same characters must be
 * together. Example 3:
 * 
 * Input: "Aabb"
 * 
 * Output: "bbAa"
 * 
 * Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect. Note
 * that 'A' and 'a' are treated as two different characters.
 * 
 * @author dwaraknathbs
 *
 */
public class SortCharactersByFrequency {

	public String frequencySort(String s) {
		int[] freq = new int[128];
		int max = 0;
		for (int i = 0; i < s.length(); i++) {
			freq[s.charAt(i)]++;
			max = Math.max(max, freq[s.charAt(i)]);
		}

		StringBuilder sb = new StringBuilder();
		for (int j = max; j > 0; j--) {
			for (int t = 0; t < freq.length; t++) {
				if (freq[t] == j) {
					for (int m = 0; m < j; m++) {
						sb.append((char) t);
					}
				}
			}
		}

		return sb.toString();

	}

}
