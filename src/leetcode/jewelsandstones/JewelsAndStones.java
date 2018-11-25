package leetcode.jewelsandstones;

/**
 * 771. Jewels and Stones Easy 1062 193
 * 
 * 
 * You're given strings J representing the types of stones that are jewels, and
 * S representing the stones you have. Each character in S is a type of stone
 * you have. You want to know how many of the stones you have are also jewels.
 * 
 * The letters in J are guaranteed distinct, and all characters in J and S are
 * letters. Letters are case sensitive, so "a" is considered a different type of
 * stone from "A".
 * 
 * Example 1:
 * 
 * Input: J = "aA", S = "aAAbbbb" Output: 3 Example 2:
 * 
 * Input: J = "z", S = "ZZ" Output: 0 Note:
 * 
 * S and J will consist of letters and have length at most 50. The characters in
 * J are distinct.
 * 
 * @author dwaraknathbs
 *
 */
public class JewelsAndStones {
	public int numJewelsInStones(String J, String S) {

		boolean jewels[] = new boolean[58];
		int count = 0;
		if (J == null || S == null)
			return count;

		for (char ch : J.toCharArray()) {
			jewels[ch - 'A'] = true;
			;
		}

		for (char ch : S.toCharArray()) {
			if (jewels[ch - 'A'])
				count++;
		}

		return count;
	}

}
